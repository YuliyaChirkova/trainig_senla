package testsDB;

import dataBaseConnect.JDBCConnection;
import org.junit.jupiter.api.*;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        String selectQuery = "SELECT channel, statusofapplication, kindofapplication FROM applications WHERE applicantid = 6751";
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
        String selectQuery = "select * from citizens c where citizenid=6025";
        ResultSet rs = JDBCConnection.selectFromTable(selectQuery);
        assertAll("Should return inserted data",
                () -> assertEquals("222222222", rs.getString("surname")),
                () -> assertEquals("222222222222", rs.getString("name")),
                () -> assertEquals("2222222222222", rs.getString("middlename")),
                () -> assertEquals("2222222222", rs.getString("passportnumber")),
                () -> assertEquals("2222222222222", rs.getString("gender")));
    }

    @Test
    @Order(3)
    @DisplayName("Сделать запрос в БД для проверки Данных заявителя: фамилии, имени, отчества, номера паспорта, номера телефона")
    public void testSelectDataFromApplicantsSchema() {
        String selectQuery = "select * from applicants where applicantid=6751";
        ResultSet rs = JDBCConnection.selectFromTable(selectQuery);
        assertAll("Should return inserted data",
                () -> assertEquals("222222222", rs.getString("surname")),
                () -> assertEquals("222222222222", rs.getString("name")),
                () -> assertEquals("2222222222222", rs.getString("middlename")),
                () -> assertEquals("2222222222", rs.getString("passportnumber")),
                () -> assertEquals("2222222222222", rs.getString("phonenumber")));
    }


    @Test
    @Order(4)
    @DisplayName("Сделать запрос в БД для проверки Данных услуги: даты регистрации брака, фамилии супруги, имени супруги, отчества супруги, даты рождения супруги, номера паспорта")
    public void testSelectDataFromMarriagesertificatesSchema() {
        String selectQuery = "select * from merrigecertificates where citizenid=6025";
        ResultSet rs = JDBCConnection.selectFromTable(selectQuery);
        assertAll("Should return inserted data",
                () -> assertEquals("222222222", rs.getString("dateofmarrige")),
                () -> assertEquals("222222222222", rs.getString("surnameofspause")),
                () -> assertEquals("2222222222222", rs.getString("newsurnameofspause")),
                () -> assertEquals("2222222222", rs.getString("name")),
                () -> assertEquals("2222222222222", rs.getString("middlename")),
                () -> assertEquals("2222222222", rs.getString("dateofbirth")),
                () -> assertEquals("2222222222", rs.getString("passportnumber")));
    }

    @Test
    @Order(5)
    @DisplayName("Отправка DELETE запроса для удаления данных из таблицы application")
    public void testDeleteRequestFromApplicationSchema() {
        String query = "DELETE FROM application WHERE applicantid = 6751";
        // JDBCConnection.deleteFromTable(query);
        int row =JDBCConnection.deleteFromTable(query);
        assertEquals(1, row);

        //второй вариант проверки
        String selectQuery = "SELECT * FROM applications WHERE applicantid = 6751";
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
        String query = "DELETE FROM citizens c where citizenid=6025";
        JDBCConnection.deleteFromTable(query);
        String selectQuery = "select * from citizens c where citizenid=6025";
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
        String query = "DELETE FROM from applicants where applicantid=6751";
        JDBCConnection.deleteFromTable(query);
        String selectQuery = "select * from applicants where applicantid=6751";
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
        String query = "DELETE FROM merrigecertificates where citizenid=6025";
        JDBCConnection.deleteFromTable(query);
        String selectQuery = "select * from merrigecertificates where citizenid=6025";
        ResultSet rs = JDBCConnection.selectFromTable(selectQuery);
        assertAll("Should return null data",
                () -> assertNull(rs.getString("dateofmarrige")),
                () -> assertNull(rs.getString("surnameofspause")),
                () -> assertNull(rs.getString("newsurnameofspause")),
                () -> assertNull(rs.getString("nameofspouse")),
                () -> assertNull(rs.getString("middlenameofspouse")),
                () -> assertNull(rs.getString("dateofbirthofspouse")),
                () -> assertNull( rs.getString("passportnumberofspouse")));
    }
}
