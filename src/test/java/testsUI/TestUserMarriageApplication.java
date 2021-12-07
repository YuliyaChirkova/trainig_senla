package testsUI;

import com.codeborne.selenide.Condition;
import data.User;
import dataBaseConnect.JDBCConnection;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestUserMarriageApplication extends BeforeAfterEach {


    @Test
    @Description("Тест: войти как пользователь")
    @Feature("Авторизация")
    @Severity(SeverityLevel.BLOCKER)
    @Order(1)
    public void testUser() {
        authorizationPage.clickUserButton()
                .checkMessageText();
        applicantDataPage.getApplicantLastName().should(Condition.exist);
        applicantDataPage.getApplicantFirstName().should(Condition.exist);
        applicantDataPage.getApplicantMiddleName().should(Condition.exist);
        applicantDataPage.getApplicantPhoneNumber().should(Condition.exist);
        applicantDataPage.getApplicantPassportNumber().should(Condition.exist);
    }

    @Test
    @Description("Тест: заполнить обязательные поля в форме Данные заявителя")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(2)
    public void testSetApplicantData() {
        applicantDataPage.setAllApplicantData(userApplicant)
                .getNextButton().shouldBe(Condition.enabled);
    }

    @Test
    @Description("Тест: перейти на страницу Выбора услуги")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(3)
    public void testGetServiceOptionPage() {
        applicantDataPage.clickNextButton();
        serviceOptionPage.getMarriageApplicationButton().should(Condition.exist);
        serviceOptionPage.getBirthApplicationButton().should(Condition.exist);
        serviceOptionPage.getDeathApplicationButton().should(Condition.exist);
    }

    @Test
    @Description("Тест: выбрать услугу Регистрация брака")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(4)
    public void testGetMarriageApplication() {
        serviceOptionPage.clickMarriageApplicationButton();
        citizenDataPage.getCitizenLastName().should(Condition.exist);
        citizenDataPage.getCitizenFirstName().should(Condition.exist);
        citizenDataPage.getCitizenMiddleName().should(Condition.exist);
        citizenDataPage.getCitizenBirthDate().should(Condition.exist);
        citizenDataPage.getCitizenPassportNumber().should(Condition.exist);
        citizenDataPage.getCitizenGender().should(Condition.exist);
    }

    @Test
    @Description("Тест: заполнить все поля в форме Данные гражданина / регистрация брака")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(5)
    public void testSetCitizenDataMarriage() {
        citizenDataPage.setAllCitizenData(userCitizen);
        citizenDataPage.getNextButton().shouldBe(Condition.enabled);
    }

    @Test
    @Description("Тест: перейти на страницу Данные услуги / регистрация брака")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(6)
    public void testGetServiceDataPage() {
        citizenDataPage.clickNextButton();
        serviceDataPage.getMarriageDataRegistration().should(Condition.exist);
        serviceDataPage.getSpouseNewLastName().should(Condition.exist);
        serviceDataPage.getSpouseLastName().should(Condition.exist);
        serviceDataPage.getSpouseFirstName().should(Condition.exist);
        serviceDataPage.getSpouseMiddleName().should(Condition.exist);
        serviceDataPage.getSpousePassportNumber().should(Condition.exist);
    }

    @Test
    @Description("Тест: заполнить все поля в форме Данные услуги / регистрация брака")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(7)
    public void testSetServiceDataMarriage() {
        serviceDataPage.setAllMarriageServiceData(userService);
        serviceDataPage.getFinishButton().shouldBe(Condition.enabled);
    }

    @Test
    @Description("Тест: отправить заявку на регистрацию брака ")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(8)
    public void testSendMarriageApplication() {
        serviceDataPage.clickFinishButton()
       .getStatusMessage().shouldHave(Condition.exactText("Ваша заявка отправлена на рассмотрение. "));
    }

      @Test
    @Order(9)
    @DisplayName("Проверка статуса заявки, типа заявки")
    public void testSelectDataFromApplicationSchema() {

        try {
            applicantid = getApplicantID(userApplicant.getApplicantLastName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String selectQuery = "SELECT * FROM applications WHERE applicantid =" +applicantid;
        ResultSet rs = JDBCConnection.selectFromTable(selectQuery);
        assertAll("Should return inserted data",
                () -> assertEquals("under consideration", rs.getString("statusofapplication")),
                () -> assertEquals("Получение свидетельства о браке", rs.getString("kindofapplication")));
    }

    @Test
    @Order(10)
    @DisplayName("Проверка Данных гражданина: фамилии, имени, отчества, номера паспорта, пола")
    public void testSelectDataFromCitizensSchema() {

        try {
            citizenid = getCitizenID(userApplicant.getApplicantLastName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String selectQuery = "select * from citizens c where citizenid=" + citizenid;
        ResultSet rs = JDBCConnection.selectFromTable(selectQuery);
        assertAll("Should return inserted data",
                () -> assertEquals("Ivan", rs.getString("surname")),
                () -> assertEquals("Ivanov", rs.getString("name")),
                () -> assertEquals("Ivanovich", rs.getString("middlename")),
                () -> assertEquals("2222222", rs.getString("passportnumber")),
                () -> assertEquals("male", rs.getString("gender")));
    }

    @Test
    @Order(11)
    @DisplayName("Проверка Данных заявителя: фамилии, имени, отчества, номера паспорта, номера телефона")
    public void testSelectDataFromApplicantsSchema() {

        try {
            applicantid = getApplicantID(userApplicant.getApplicantLastName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String selectQuery = "select * from applicants where applicantid=" +applicantid;
        ResultSet rs = JDBCConnection.selectFromTable(selectQuery);
        assertAll("Should return inserted data",
                () -> assertEquals("Mask", rs.getString("surname")),
                () -> assertEquals("Ealon", rs.getString("name")),
                () -> assertEquals("James", rs.getString("middlename")),
                () -> assertEquals("1234567", rs.getString("passportnumber")),
                () -> assertEquals("3333333", rs.getString("phonenumber")));
    }

    @Test
    @Order(12)
    @DisplayName("Проверка Данных услуги: даты регистрации брака, фамилии супруги, имени супруги, отчества супруги, даты рождения супруги, номера паспорта")
    public void testSelectDataFromMarriagesertificatesSchema() {

        try {
            citizenid = getCitizenID(userApplicant.getApplicantLastName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String selectQuery = "select * from merrigecertificates where citizenid=" +citizenid;
        ResultSet rs = JDBCConnection.selectFromTable(selectQuery);
        assertAll("Should return inserted data",
                () -> assertEquals("2022-08-08", rs.getString("dateofmerrige")),
                () -> assertEquals("Petrova", rs.getString("surnameofspouse")),
                () -> assertEquals("Mask", rs.getString("newsurnameofspouse")),
                () -> assertEquals("Olga", rs.getString("nameofspouse")),
                () -> assertEquals("Petrovna", rs.getString("middlenameofspouse")),
                () -> assertEquals("1996-12-13", rs.getString("dateofbirthofspouse")),
                () -> assertEquals("4444444", rs.getString("passportnumberofspouse")));
    }

    @Test
    @Order(13)
    @DisplayName("Удаление данных из таблицы application")
    public void testDeleteRequestFromApplicationSchema() {

        try {
            applicantid = getApplicantID(userApplicant.getApplicantLastName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String query = "DELETE FROM applications WHERE applicantid =" +applicantid;
        JDBCConnection.deleteFromTable(query);
        String selectQuery = "SELECT * FROM applications WHERE applicantid =" +applicantid;
        ResultSet rs = JDBCConnection.selectFromTable(selectQuery);
          assertAll("Should return null data",
                () -> assertNull(rs.getString("statusofapplication")),
                () -> assertNull(rs.getString("kindofapplication")));
    }

    @Test
    @Order(14)
    @DisplayName("Удаление данных из таблицы citizens")
    public void testDeleteRequestFromCitizensSchema() {

        try {
            citizenid = getCitizenID(userApplicant.getApplicantLastName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String query = "DELETE FROM citizens c where citizenid=" +citizenid;
        JDBCConnection.deleteFromTable(query);
        String selectQuery = "select * from citizens c where citizenid =" +citizenid;
        ResultSet rs = JDBCConnection.selectFromTable(selectQuery);
        assertAll("Should return null data",
                () -> assertNull(rs.getString("surname")),
                () -> assertNull(rs.getString("name")),
                () -> assertNull(rs.getString("middlename")),
                () -> assertNull(rs.getString("passportnumber")),
                () -> assertNull(rs.getString("gender")));
    }

    @Test
    @Order(15)
    @DisplayName("Удаление данных из таблицы applicants")
    public void testDeleteRequestFromApplicantsSchema() {

        try {
            applicantid = getApplicantID(userApplicant.getApplicantLastName());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "DELETE FROM applicants where applicantid=" +applicantid;
        JDBCConnection.deleteFromTable(query);
        String selectQuery = "select * from applicants where applicantid=" +applicantid;
        ResultSet rs = JDBCConnection.selectFromTable(selectQuery);
        assertAll("Should return null data",
                () -> assertNull(rs.getString("surname")),
                () -> assertNull(rs.getString("name")),
                () -> assertNull(rs.getString("middlename")),
                () -> assertNull(rs.getString("passportnumber")),
                () -> assertNull(rs.getString("phonenumber")));
    }

    @Test
    @Order(16)
    @DisplayName("Удаление данных из таблицы merrigecertificates")
    public void testDeleteRequestMarriagesertificatesSchema() {

        try {
            citizenid = getCitizenID(userApplicant.getApplicantLastName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String query = "DELETE FROM merrigecertificates where citizenid=" +citizenid;
        JDBCConnection.deleteFromTable(query);
        String selectQuery = "select * from merrigecertificates where citizenid=" +citizenid;
        ResultSet rs = JDBCConnection.selectFromTable(selectQuery);
        assertAll("Should return null data",
                () -> Assertions.assertNull(rs.getString("dateofmerrige")),
                () -> Assertions.assertNull(rs.getString("surnameofspouse")),
                () -> Assertions.assertNull(rs.getString("newsurnameofspouse")),
                () -> Assertions.assertNull(rs.getString("nameofspouse")),
                () -> Assertions.assertNull(rs.getString("middlenameofspouse")),
                () -> Assertions.assertNull(rs.getString("dateofbirthofspouse")),
                () -> Assertions.assertNull(rs.getString("passportnumberofspouse")));
    }
    @Test
    @Description("Тест: создать новую заявку со страницы Статус заявки ")
    @Feature("Регистрация")
    @Severity(SeverityLevel.NORMAL)
    @Order(13)
    public void testCreateNewApplication() {
        applicationStatusPage.clickCreateNewApplicationButton();
        applicantDataPage.getApplicantLastName().should(Condition.exist);
        applicantDataPage.getApplicantFirstName().should(Condition.exist);
        applicantDataPage.getApplicantMiddleName().should(Condition.exist);
        applicantDataPage.getApplicantPhoneNumber().should(Condition.exist);
        applicantDataPage.getApplicantPassportNumber().should(Condition.exist);
    }
}
