package testsUI;

import com.codeborne.selenide.Condition;
import dataBaseConnect.JDBCConnection;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestAdministrator extends BeforeAfterEach {

    int staffid;

    @Test
    @DisplayName("Тест: войти как администратор")
    @Feature("Авторизация")
    @Severity(SeverityLevel.BLOCKER)
    @Order(1)
    public void testAdmin() {
        authorizationPage.clickAdminButton();
        adminDataPage.getAdminMessage().shouldHave(Condition.exactText("Вы вошли как администратор"));
        adminDataPage.getAdminLastName().should(Condition.exist);
        adminDataPage.getAdminFirstName().should(Condition.exist);
        adminDataPage.getAdminMiddleName().should(Condition.exist);
        adminDataPage.getAdminPhoneNumber().should(Condition.exist);
        adminDataPage.getAdminPassportNumber().should(Condition.exist);
        adminDataPage.getAdminBirthDate().should(Condition.exist);
    }

    @Test
    @DisplayName("Тест: заполнить данные администратора валидными данными")
    @Feature("Регистрация админа")
    @Severity(SeverityLevel.CRITICAL)
    @Order(2)
    public void testSetAdminData() {
        adminDataPage.setAllAdminData(administrator);
        adminDataPage.getNextButton().shouldBe(Condition.enabled);
    }

    @Test
    @DisplayName("Тест: отправить заявку на регистрацию администратора")
    @Feature("Регистрация админа")
    @Severity(SeverityLevel.CRITICAL)
    @Order(3)
    public void testSendAdminApplication() {
        adminDataPage.clickNextButton();
        applicationListPage.getColumnNumber().shouldHave(Condition.exactText("№"));
        applicationListPage.getColumnApplicant().shouldHave(Condition.exactText("Заявитель"));
        applicationListPage.getColumnType().shouldHave(Condition.exactText("Тип"));
        applicationListPage.getColumnTime().shouldHave(Condition.exactText("Время"));
        applicationListPage.getColumnStatus().shouldHave(Condition.exactText("Статус"));
        applicationListPage.getColumnAction().shouldHave(Condition.exactText("Действие"));
    }

    @Test
    @Order(4)
    @DisplayName("Проверка Данных администратора")
    public void testSelectDataFromStaffSchema() throws SQLException {

        String selectQuery = "select * from staff where surname = '"+ administrator.getPersonalLastName() +"' order by staffid desc limit 1";
        ResultSet rs = JDBCConnection.selectFromTable(selectQuery);
        staffid = rs.getInt("staffid");
        assertAll("Should return inserted data",
                () -> assertEquals("Sergeev", rs.getString("surname")),
                () -> assertEquals("Sergei", rs.getString("name")),
                () -> assertEquals("Sergeevich", rs.getString("middlename")),
                () -> assertEquals("5555555", rs.getString("phonenumber")),
                () -> assertEquals("7777777", rs.getString("passportnumber")));
        JDBCConnection.closeConnection();
    }

    @Test
    @Order(5)
    @DisplayName("Удаление данных из таблицы staff")
    public void testDeleteRequestStaffSchema() {

        String query = "DELETE FROM staff where staffid=" + staffid;
        int actualResult = JDBCConnection.deleteFromTable(query);
        assertEquals(1, actualResult);
        JDBCConnection.closeConnection();
    }

}
