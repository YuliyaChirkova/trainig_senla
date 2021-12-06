package testsDB;

import dataBaseConnect.JDBCConnection;
import org.junit.jupiter.api.*;
import java.sql.ResultSet;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestDataBase {

    @Test
    @Order(1)
    @DisplayName("Сделать запрос в БД для проверки канала подачи заявки, статуса заявки, типа заявки")
    public void testSelectDataFromApplicationSchema() {
        String selectQuery = "SELECT * FROM applications WHERE applicantid = 6795";
        ResultSet rs = JDBCConnection.selectFromTable(selectQuery);
        assertAll("Should return inserted data",
                () -> assertEquals("web", rs.getString("channel")),
                () -> assertEquals("under consideration", rs.getString("statusofapplication")),
                () -> assertEquals("Получение свидетельства о браке", rs.getString("kindofapplication")));
    }

    @Test
    @Order(2)
    @DisplayName("Сделать запрос в БД для проверки Данных гражданина: фамилии, имени, отчества, номера паспорта, пола")
    public void testSelectDataFromCitizensSchema() {
        String selectQuery = "select * from citizens c where citizenid=6069";
        ResultSet rs = JDBCConnection.selectFromTable(selectQuery);
        assertAll("Should return inserted data",
                () -> assertEquals("Константинов", rs.getString("surname")),
                () -> assertEquals("Константин", rs.getString("name")),
                () -> assertEquals("Константинович", rs.getString("middlename")),
                () -> assertEquals("1111111", rs.getString("passportnumber")),
                () -> assertEquals("1111111", rs.getString("gender")));
    }

    @Test
    @Order(3)
    @DisplayName("Сделать запрос в БД для проверки Данных заявителя: фамилии, имени, отчества, номера паспорта, номера телефона")
    public void testSelectDataFromApplicantsSchema() {
        String selectQuery = "select * from applicants where applicantid=6795";
        ResultSet rs = JDBCConnection.selectFromTable(selectQuery);
        assertAll("Should return inserted data",
                () -> assertEquals("Константинов", rs.getString("surname")),
                () -> assertEquals("Константин", rs.getString("name")),
                () -> assertEquals("Константинович", rs.getString("middlename")),
                () -> assertEquals("1111111", rs.getString("passportnumber")),
                () -> assertEquals("1111111", rs.getString("phonenumber")));
    }


    @Test
    @Order(4)
    @DisplayName("Сделать запрос в БД для проверки Данных услуги: даты регистрации брака, фамилии супруги, имени супруги, отчества супруги, даты рождения супруги, номера паспорта")
    public void testSelectDataFromMarriagesertificatesSchema() {
        String selectQuery = "select * from merrigecertificates where citizenid=6069";
        ResultSet rs = JDBCConnection.selectFromTable(selectQuery);
        assertAll("Should return inserted data",
                () -> assertEquals("2021-12-07", rs.getString("dateofmerrige")),
                () -> assertEquals("Иванова", rs.getString("surnameofspouse")),
                () -> assertEquals("Константинова", rs.getString("newsurnameofspouse")),
                () -> assertEquals("катерина", rs.getString("nameofspouse")),
                () -> assertEquals("константиновна", rs.getString("middlenameofspouse")),
                () -> assertEquals("2021-12-02", rs.getString("dateofbirthofspouse")),
                () -> assertEquals("1111111", rs.getString("passportnumberofspouse")));
    }

    @Test
    @Order(5)
    @DisplayName("Отправка DELETE запроса для удаления данных из таблицы application")
    public void testDeleteRequestFromApplicationSchema() {
        String query = "DELETE FROM applications WHERE applicantid = 6832";
         JDBCConnection.deleteFromTable(query);

        String selectQuery = "SELECT * FROM applications WHERE applicantid = 6832";
        ResultSet rs = JDBCConnection.selectFromTable(selectQuery);
        assertAll("Should return null data",
                () -> assertNull(rs.getString("channel")),
                () -> assertNull(rs.getString("statusofapplication")),
                () -> assertNull( rs.getString("kindofapplication")));
    }

    @Test
    @Order(6)
    @DisplayName("Отправка DELETE запроса для удаления данных из таблицы citizens")
    public void testDeleteRequestFromCitizensSchema() {
        String query = "DELETE FROM citizens c where citizenid=6069";
        JDBCConnection.deleteFromTable(query);
        String selectQuery = "select * from citizens c where citizenid=6069";
        ResultSet rs = JDBCConnection.selectFromTable(selectQuery);
        assertAll("Should return null data",
                () -> assertNull(rs.getString("surname")),
                () -> assertNull(rs.getString("name")),
                () -> assertNull( rs.getString("middlename")),
                () -> assertNull(rs.getString("passportnumber")),
                () -> assertNull(rs.getString("gender")));
    }

    @Test
    @Order(7)
    @DisplayName("Отправка DELETE запроса для удаления данных из таблицы applicants")
    public void testDeleteRequestFromApplicantsSchema() {
        String query = "DELETE FROM from applicants where applicantid=6795";
        JDBCConnection.deleteFromTable(query);
        String selectQuery = "select * from applicants where applicantid=6795";
        ResultSet rs = JDBCConnection.selectFromTable(selectQuery);
        assertAll("Should return null data",
                () -> assertNull(rs.getString("surname")),
                () -> assertNull(rs.getString("name")),
                () -> assertNull( rs.getString("middlename")),
                () -> assertNull(rs.getString("passportnumber")),
                () -> assertNull(rs.getString("phonenumber")));
    }

    @Test
    @Order(8)
    @DisplayName("Отправка DELETE запроса для удаления данных из таблицы merrigecertificates")
    public void testDeleteRequestMarriagesertificatesSchema() {
        String query = "DELETE FROM merrigecertificates where citizenid=6069";
        JDBCConnection.deleteFromTable(query);
        String selectQuery = "select * from merrigecertificates where citizenid=6069";
        ResultSet rs = JDBCConnection.selectFromTable(selectQuery);
        assertAll("Should return null data",
                () -> Assertions.assertNull(rs.getString("dateofmerrige")),
                () -> Assertions.assertNull(rs.getString("surnameofspouse")),
                () -> Assertions.assertNull(rs.getString("newsurnameofspouse")),
                () -> Assertions.assertNull(rs.getString("nameofspouse")),
                () -> Assertions.assertNull(rs.getString("middlenameofspouse")),
                () -> Assertions.assertNull(rs.getString("dateofbirthofspouse")),
                () -> Assertions.assertNull( rs.getString("passportnumberofspouse")));
    }
}
