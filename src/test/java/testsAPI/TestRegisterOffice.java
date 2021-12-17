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


    Administrator administrator = new Administrator("Sergei", "Sergeev",
            "Sergeevich", "5555555", "7777777", "2021-12-23");
    int applicationid;

    String jsonBodyUser = "{\n" +
            "  \"mode\": \"wedding\",\n" +
            "  \"personalFirstName\":\t\"zzzzzzzzzzz\",\n" +
            "  \"personalLastName\": \"cruz\",\n" +
            "  \"personalMiddleName\": \"bob\",\n" +
            "  \"personalPhoneNumber\": \"3333333\",\n" +
            "  \"personalNumberOfPassport\": \"444444\",\n" +
            "  \"citizenLastName\": \"fiona\",\n" +
            "  \"citizenFirstName\": \"fiona\",\n" +
            "  \"citizenMiddleName\": \"fiona\",\n" +
            "  \"citizenBirthDate\": \"2021-11-22\",\n" +
            "  \"citizenNumberOfPassport\": \"string\",\n" +
            "  \"citizenGender\": \"string\",\n" +
            "  \"dateOfMarriage\": \"2021-11-22\",\n" +
            "  \"newLastName\": null,\n" +
            "  \"anotherPersonLastName\": \"qaaaa\",\n" +
            "  \"anotherPersonFirstName\": \"string\",\n" +
            "  \"anotherPersonMiddleName\": \"string\",\n" +
            "  \"birth_of_anotoherPerson\": \"2021-11-22\",\n" +
            "  \"anotherPersonPassport\": \"string\",\n" +
            "  \"birth_place\": \"\",\n" +
            "  \"birth_mother\": \"\",\n" +
            "  \"birth_father\": \"\",\n" +
            "  \"death_dateOfDeath\": \"\",\n" +
            "  \"death_placeOfDeath\": \"\"\n" +
            "}";

    String jsonbodyAdmin = "{\n" +
            "  \"dateofbirth\": \"4444\",\n" +
            "  \"personalFirstName\": \"tim\",\n" +
            "  \"personalLastName\": \"tim\",\n" +
            "  \"personalMiddleName\": \"tim\",\n" +
            "  \"personalNumberOfPassport\": \"33333\",\n" +
            "  \"personalPhoneNumber\": \"222222\"\n" +
            "}";

    User marriageApplicationUser = new User.Builder()
            .withMode("wedding")
            .withName("Gustav")
            .withSurname("Klimt")
            .withMiddleName("Klimt")
            .withPhoneNumber("9999999")
            .withPassportNumber("2222222")
            .withCitizenFirstName("Gustav")
            .withCitizenLastName("Klimt")
            .withCitizenMiddleName("Klimt")
            .withCitizenBirthDate("02021892")
            .withCitizenPassportNumber("2222222")
            .withCitizenGender("Male")
            .withMarriageRegistrationDate("1933-11-11")
            .withSpouseNewLastName(null)
            .withSpouseLastName("Brown")
            .withSpouseFirstName("Adele")
            .withSpouseMiddleName("B")
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
                .body(jsonBodyUser)
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
       int applicationid= given().spec(requestSpec)
                .when()
                .body(jsonBodyUser)
                .post(RegisterOfficeEndpoints.CREATE_USER)
                .then()
               .statusCode(200)
                .extract()
                .path ("data.applicationid");
       Log.info("applicationid is " + applicationid);

        String status = given().spec(requestSpec)
                .when()
                .get(RegisterOfficeEndpoints.GET_APPLICATION_STATUS + applicationid)
                .then()
                .statusCode(200)
                .extract()
                .path ("data.statusofapplication");
        Assertions.assertEquals("under consideration", status);
    }

    @Test
    @Order(4)
    @Description("Проверка получения списка заявок")
    public void testGetApplicationsList() {
        given().spec(requestSpec)
                .when()
                .get(RegisterOfficeEndpoints.GET_ALL_APPLICATIONS)
                .prettyPeek()
                .then()
                .statusCode(200);
    }

}
