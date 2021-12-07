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
import io.restassured.response.Response;
import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static org.hamcrest.Matchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestRegisterOffice {

    protected static RequestSpecification requestSpec = RegisterOfficeSpecification.getRequestSpecification();
    protected static File jsonSchemaUser = new File("src/test/resources/jsonSchema/userJSONSchema.json");
    protected static File jsonSchemaAdmin = new File("src/test/resources/jsonSchema/adminJSONSchema.json");
    protected static File responseJsonSchemaUser = new File("src/test/resources/jsonSchema/responceUserJsonSchema.json");
    protected static File responseJsonSchemaAdmin = new File("src/test/resources/jsonSchema/responseAdminJsonSchema.json");
    protected static File responseJsonAllApplications = new File("src/test/resources/jsonSchema/applicationsListSchema.json");

    protected User userMarriege = new User("wedding", "Ealon", "Mask",
            "James", "3333333", "1234567", "Ivanov", "Ivan",
            "Ivanovich", "02021992", "2222222", "male", "08082022", "Mask",
            "Petrova", "Olga", "Petrovna", "2021-12-23",
            "4444444", "string", "string", "string",
            "string", "string");
    Administrator administrator = new Administrator("Sergei", "Sergeev",
            "Sergeevich", "5555555", "7777777", "2021-12-23");

    @Test
    @Order(1)
    @Description("Проверка создания заявки на регистрацию брака, валидация jsonSchema")
    public void testCreateUserValidationJsonScheme() {

        String jsonBody = "";
        try {
            jsonBody = new ObjectMapper().writeValueAsString(userMarriege);
        } catch (JsonProcessingException e) {
            Log.error("Can't create jsonBody", e);
        }
        given().spec(requestSpec)
                .when()
                .body(jsonBody)
                .post(RegisterOfficeEndpoints.CREATE_USER)
                .then()
                .assertThat()
                .statusCode(200)
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
                .statusCode(200)
                .body(matchesJsonSchema(responseJsonSchemaAdmin));

    }

    @Test
    @Order(3)
    @Description("Проверка статуса заявки")
    public void testGetApplicationStatus() {
        given().spec(requestSpec)
                .when()
                .get(RegisterOfficeEndpoints.GET_APPLICATION_STATUS + "5947")
                .prettyPeek()
                .then()
                .body("statusofapplication", equalTo("under consideration"));
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
                .statusCode(200)
                .body(matchesJsonSchema(responseJsonAllApplications));

    }

}
