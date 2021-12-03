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
    @DisplayName("Отправка SELECT запроса для проверки канала, статуса заявки, типа заявки")
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
    @DisplayName("Отправка SELECT запроса для проверки фамилии, имени, отчества, номера паспорта, пола")
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
    @DisplayName("Отправка DELETE запроса для удаления данных из таблицы application")
    public void testDeleteRequest() {
        String query = "DELETE FROM application WHERE applicantid = 6751";
        JDBCConnection.deleteFromTable(query);
        String selectQuery = "SELECT channel, statusofapplication, kindofapplication FROM applications WHERE applicantid = 6751";
        ResultSet rs = JDBCConnection.selectFromTable(selectQuery);
        assertAll("Should return null data",
                () -> assertNull(rs.getString("channel")),
                () -> assertNull(rs.getString("statusofapplication")),
                () -> assertNull( rs.getString("kindofapplication")));
    }
}
