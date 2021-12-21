package testsAPI;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.Administrator;
import data.User;
import endpoints.RegisterOfficeEndpoints;
import io.qameta.allure.Description;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import utils.Log;
import utils.RegisterOfficeSpecification;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestRegisterOffice {

    protected static RequestSpecification requestSpec = RegisterOfficeSpecification.getRequestSpecification();
    protected static File jsonSchemaUser = new File("src/test/resources/jsonSchema/userJSONSchema.json");
    protected static File jsonSchemaAdmin = new File("src/test/resources/jsonSchema/adminJSONSchema.json");
    protected static File responseJsonSchemaUser = new File("src/test/resources/jsonSchema/responceUserJsonSchema.json");
    protected static File responseJsonSchemaAdmin = new File("src/test/resources/jsonSchema/responseAdminJsonSchema.json");
    protected static File responseJsonAllApplications = new File("src/test/resources/jsonSchema/applicationsListSchema.json");

    Administrator administrator = new Administrator.Builder()
            .withName("Petr")
            .withSurname("Petrov")
            .withMiddleName("Petrovich")
            .withPhoneNumber("5555555")
            .withPassportNumber("7777777")
            .withDateofbirth("2001-12-23")
            .build();

    User marriageApplicationUser = new User.Builder()
            .withMode("wedding")
            .withName("Vaclav")
            .withSurname("Gavel")
            .withMiddleName("Gavel")
            .withPhoneNumber("1111111")
            .withPassportNumber("2222222")
            .withCitizenFirstName("Vaclav")
            .withCitizenLastName("Gavel")
            .withCitizenMiddleName("Gavel")
            .withCitizenBirthDate("1935-02-02")
            .withCitizenPassportNumber("2222222")
            .withCitizenGender("Male")
            .withMarriageRegistrationDate("1933-11-11")
            .withSpouseNewLastName(null)
            .withSpouseLastName("Black")
            .withSpouseFirstName("Nina")
            .withSpouseMiddleName("Maria")
            .withSpouseBirthDate("1904-11-11")
            .withSpousePassportNumber("333333")
            .withBirthPlace("")
            .withMotherName("")
            .withFatherName("")
            .withDeathDate("")
            .withDeathPlace("")
            .build();

    @Test
    @Order(1)
    @Description("Проверка создания заявки на регистрацию брака, валидация jsonSchema")
    public void testCreateUserValidationJsonScheme() {

        String jsonBody = "";
        try {
            jsonBody = new ObjectMapper().writeValueAsString(marriageApplicationUser);
        } catch (JsonProcessingException e) {
            Log.error("Can't create jsonBody", e);
        }

        given().spec(requestSpec)
                .when()
                .body(jsonBody)
                .post(RegisterOfficeEndpoints.CREATE_USER)
                .then()
                .assertThat()
                .body(matchesJsonSchema(responseJsonSchemaUser));
    }

    @Test
    @Order(2)
    @Description("Проверка регистрации администратора, валидация jsonSchema")
    public void testCreateAdminValidationJsonScheme() {

        String jsonBody = "";
        try {
            jsonBody = new ObjectMapper().writeValueAsString(administrator);
        } catch (JsonProcessingException e) {
            Log.error("Can't create jsonBody", e);
        }

        given().spec(requestSpec)
                .when()
                .body(jsonBody)
                .post(RegisterOfficeEndpoints.CREATE_ADMIN)
                .then()
                .assertThat()
                .body(matchesJsonSchema(responseJsonSchemaAdmin));
    }

    @Test
    @Order(3)
    @Description("Проверка статуса заявки")
    public void testGetApplicationStatus() {
        int applicationid = given().spec(requestSpec)
                .when()
                .body(marriageApplicationUser)
                .post(RegisterOfficeEndpoints.CREATE_USER)
                .then()
                .statusCode(200)
                .extract()
                .path("data.applicationid");
        Log.info("applicationid is " + applicationid);

        String status = given().spec(requestSpec)
                .when()
                .get(RegisterOfficeEndpoints.GET_APPLICATION_STATUS + applicationid)
                .then()
                .statusCode(200)
                .extract()
                .path("data.statusofapplication");
        Assertions.assertEquals("under consideration", status);
    }

    @Test
    @Order(4)
    @Description("Проверка получения списка заявок, валидация jsonSchema")
    public void testGetApplicationsList() {
        given().spec(requestSpec)
                .when()
                .get(RegisterOfficeEndpoints.GET_ALL_APPLICATIONS)
                .prettyPeek()
                .then()
                .statusCode(200)
                .body(matchesJsonSchema(responseJsonAllApplications));
    }
}
