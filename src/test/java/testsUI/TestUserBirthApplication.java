package testsUI;

import com.codeborne.selenide.Condition;
import dataBaseConnect.JDBCConnection;
import io.qameta.allure.Description;
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
public class TestUserBirthApplication extends BeforeAfterEach {

    @Test
    @DisplayName("Тест: войти как пользователь")
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
    @DisplayName("Тест: заполнить обязательные поля в форме Данные заявителя")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(2)
    public void testSetApplicantData() {
        applicantDataPage.setAllApplicantData(userApplicant)
                .getNextButton().shouldBe(Condition.enabled);
    }

    @Test
    @DisplayName("Тест: перейти на страницу Выбора услуги")
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
    @DisplayName("Тест: выбрать услугу Регистрация рождения")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(4)
    public void testGetBirthApplication() {
        serviceOptionPage.clickBirthApplicationButton();
        citizenDataPage.getCitizenLastName().should(Condition.exist);
        citizenDataPage.getCitizenFirstName().should(Condition.exist);
        citizenDataPage.getCitizenMiddleName().should(Condition.exist);
        citizenDataPage.getCitizenBirthDate().should(Condition.exist);
        citizenDataPage.getCitizenPassportNumber().should(Condition.exist);
        citizenDataPage.getCitizenGender().should(Condition.exist);
    }

    @Test
    @DisplayName("Тест: заполнить все поля в форме Данные гражданина / регистрация рождения")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(5)
    public void testSetCitizenDataBirth() {
        citizenDataPage.setAllCitizenData(userCitizen);
        citizenDataPage.getNextButton().shouldBe(Condition.enabled);
    }

    @Test
    @DisplayName("Тест: перейти на страницу Данные услуги / регистрация рождения")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(6)
    public void testGetServiceDataPageBirth() {
        citizenDataPage.clickNextButton();
        serviceDataPage.getBirthPlace().should(Condition.exist);
        serviceDataPage.getMotherName().should(Condition.exist);
        serviceDataPage.getFatherName().should(Condition.exist);
    }

    @Test
    @DisplayName("Тест: заполнить все поля в форме Данные услуги / регистрация рождения")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(7)
    public void testSetServiceDataBirth() {
        serviceDataPage.setAllBirthServiceData(userService);
        serviceDataPage.getFinishButton().shouldBe(Condition.enabled);
    }

    @Test
    @DisplayName("Тест: отправить заявку на регистрацию рождения ")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(8)
    public void testSendBirthApplication() {
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
                () -> assertEquals("Получение свидетельства о рождении", rs.getString("kindofapplication")));
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
    @DisplayName("Проверка Данных услуги: места рождения, имени матери, имени отца")
    public void testSelectDataFromBirthcertificatesSchema() {

        try {
            citizenid = getCitizenID(userApplicant.getApplicantLastName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String selectQuery = "select * from birthcertificates where citizenid=" +citizenid;
        ResultSet rs = JDBCConnection.selectFromTable(selectQuery);
        assertAll("Should return inserted data",
                () -> assertEquals("Brest", rs.getString("placeofbirth")),
                () -> assertEquals("Nonna", rs.getString("mother")),
                () -> assertEquals("Sebastian", rs.getString("father")));
    }

    @Test
    @Order(13)
    @DisplayName("Удаление данных из таблицы birthcertificates")
    public void testDeleteRequestBirthcertificatesSchema() throws SQLException {

        try {
            citizenid = getCitizenID(userApplicant.getApplicantLastName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String query = "DELETE FROM birthcertificates where citizenid=" + citizenid;
        int actualResult = JDBCConnection.deleteFromTable(query);
        assertEquals(1, actualResult);
    }

    @Test
    @Order(14)
    @DisplayName("Удаление данных из таблицы application")
    public void testDeleteRequestFromApplicationSchema() {

        try {
            applicantid = getApplicantID(userApplicant.getApplicantLastName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String query = "DELETE FROM applications WHERE applicantid =" +applicantid;
        int actualResult = JDBCConnection.deleteFromTable(query);
        assertEquals(1, actualResult);
    }

    @Test
    @Order(15)
    @DisplayName("Удаление данных из таблицы citizens")
    public void testDeleteRequestFromCitizensSchema() {

        try {
            citizenid = getCitizenID(userApplicant.getApplicantLastName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String query = "DELETE FROM citizens c where citizenid=" +citizenid;
        int actualResult = JDBCConnection.deleteFromTable(query);
        assertEquals(1, actualResult);
    }

    @Test
    @Order(16)
    @DisplayName("Удаление данных из таблицы applicants")
    public void testDeleteRequestFromApplicantsSchema() {

        try {
            applicantid = getApplicantID(userApplicant.getApplicantLastName());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "DELETE FROM applicants where applicantid=" +applicantid;
        int actualResult = JDBCConnection.deleteFromTable(query);
        assertEquals(1, actualResult);
    }

    @Test
    @DisplayName("Тест: создать новую заявку со страницы Статус заявки ")
    @Feature("Регистрация")
    @Severity(SeverityLevel.NORMAL)
    @Order(17)
    public void testCreateNewApplication() {
        applicationStatusPage.clickCreateNewApplicationButton();
        applicantDataPage.getApplicantLastName().should(Condition.exist);
        applicantDataPage.getApplicantFirstName().should(Condition.exist);
        applicantDataPage.getApplicantMiddleName().should(Condition.exist);
        applicantDataPage.getApplicantPhoneNumber().should(Condition.exist);
        applicantDataPage.getApplicantPassportNumber().should(Condition.exist);
    }
}
