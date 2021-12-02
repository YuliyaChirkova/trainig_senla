package tests;

import com.codeborne.selenide.Condition;
import data.Administrator;
import data.User;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.*;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestClass extends BeforeAfterEach{
    AuthorizationPage authorizationPage = new AuthorizationPage();
    ApplicantDataPage applicantDataPage = new ApplicantDataPage();
    ServiceOptionPage serviceOptionPage = new ServiceOptionPage();
    CitizenDataPage citizenDataPage = new CitizenDataPage();
    ServiceDataPage serviceDataPage = new ServiceDataPage();
    ApplicationStatusPage applicationStatusPage = new ApplicationStatusPage();
    AdminDataPage adminDataPage = new AdminDataPage();
    ApplicationListPage applicationListPage = new ApplicationListPage();


    User userApplicant = new User("Ealon", "Mask",
            "James", "3333333", "1234567");
    User userCitizen = new User("Ivanov", "Ivan",
            "Ivanovich", "02021992", "2222222","male");
    User userService = new User("08082022", "Mask",
            "Petrova", "Olga", "Petrovna", "13131996",
            "4444444", "Brest", "Nonna", "Sebastian",
            "Deli", "01012011");
    Administrator administrator = new Administrator("Sergei", "Sergeev",
            "Sergeevich", "5555555", "7777777", "03031993");

    @Test
    @Description("Тест: войти как пользователь")
    @Feature("Авторизация")
    @Severity(SeverityLevel.BLOCKER)
    public void testUser(){
        authorizationPage.clickUserButton();
        applicantDataPage.userMessage.should(Condition.exist);
    }

    @Test
    @Description("Тест: войти как администратор")
    @Feature("Авторизация")
    @Severity(SeverityLevel.BLOCKER)
    public void testAdmin(){
        authorizationPage.clickAdminButton();
        adminDataPage.adminMessage.should(Condition.exist);
    }

    @Test
    @Description("Тест: заполнить обязательные поля в форме Данные заявителя")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    public void testSetApplicantData(){
        authorizationPage.clickUserButton()
                .setAllApplicantData(userApplicant).
                nextButton.shouldBe(Condition.enabled);
    }

    @Test
    @Description("Тест: перейти на страницу Выбора услуги")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetServiceOptionPage(){
        authorizationPage.clickUserButton()
                .setAllApplicantData(userApplicant)
                .clickNextButton()
                .checkAllButtons();
    }

    @Test
    @Description("Тест: выбрать услугу Регистрация брака")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetMarriageApplication() {
        authorizationPage.clickUserButton();
        applicantDataPage.setAllApplicantData(userApplicant);
        applicantDataPage.clickNextButton();
        serviceOptionPage.clickMarriageApplicationButton();
        citizenDataPage.checkFieldsAtCitizenDataPage();
    }

    @Test
    @Description("Тест: заполнить все поля в форме Данные гражданина / регистрация брака")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    public void testSetCitizenDataMarriage(){
        authorizationPage.clickUserButton();
        applicantDataPage.setAllApplicantData(userApplicant);
        applicantDataPage.clickNextButton();
        serviceOptionPage.clickMarriageApplicationButton();
        citizenDataPage.setAllCitizenData(userCitizen);
        citizenDataPage.nextButton.shouldBe(Condition.enabled);
    }

    @Test
    @Description("Тест: перейти на страницу Данные услуги / регистрация брака")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetServiceDataPage(){
        authorizationPage.clickUserButton();
        applicantDataPage.setAllApplicantData(userApplicant);
        applicantDataPage.clickNextButton();
        serviceOptionPage.clickMarriageApplicationButton();
        citizenDataPage.setAllCitizenData(userCitizen);
        citizenDataPage.clickNextButton();
        serviceDataPage.checkFieldsAtServiceDataPageMarriage();
    }

    @Test
    @Description("Тест: заполнить все поля в форме Данные услуги / регистрация брака")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    public void testSetServiceDataMarriage(){
        authorizationPage.clickUserButton();
        applicantDataPage.setAllApplicantData(userApplicant);
        applicantDataPage.clickNextButton();
        serviceOptionPage.clickMarriageApplicationButton();
        citizenDataPage.setAllCitizenData(userCitizen);
        citizenDataPage.clickNextButton();
        serviceDataPage.setAllMarriageServiceData(userService);
        serviceDataPage.finishButton.should(Condition.enabled);
    }

    @Test
    @Description("Тест: отправить заявку на регистрацию брака ")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    public void testSendMarriageApplication(){
        authorizationPage.clickUserButton();
        applicantDataPage.setAllApplicantData(userApplicant);
        applicantDataPage.clickNextButton();
        serviceOptionPage.clickMarriageApplicationButton();
        citizenDataPage.setAllCitizenData(userCitizen);
        citizenDataPage.clickNextButton();
        serviceDataPage.setAllMarriageServiceData(userService);
        serviceDataPage.clickFinishButton()
                .statusMessage.should(Condition.exist);
    }

    @Test
    @Description("Тест: создать новую заявку со страницы Статус заявки ")
    @Feature("Регистрация")
    @Severity(SeverityLevel.NORMAL)
        public void testCreateNewApplication(){
            authorizationPage.clickUserButton();
            applicantDataPage.setAllApplicantData(userApplicant);
            applicantDataPage.clickNextButton();
            serviceOptionPage.clickMarriageApplicationButton();
            citizenDataPage.setAllCitizenData(userCitizen);
            citizenDataPage.clickNextButton();
            serviceDataPage.setAllMarriageServiceData(userService);
            serviceDataPage.clickFinishButton();
            applicationStatusPage.buttonCreateNewApplication.click();
            applicantDataPage.checkFieldsAtApplicantDataPage();
    }

    @Test
    @Description("Тест: выбрать услугу Регистрация рождения")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetBirthApplication() {
        authorizationPage.clickUserButton();
        applicantDataPage.setAllApplicantData(userApplicant);
        applicantDataPage.clickNextButton();
        serviceOptionPage.clickBirthApplicationButton();
        citizenDataPage.checkFieldsAtCitizenDataPage();
    }

    @Test
    @Description("Тест: заполнить все поля в форме Данные гражданина / регистрация рождения")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    public void testSetCitizenDataBirth(){
        authorizationPage.clickUserButton();
        applicantDataPage.setAllApplicantData(userApplicant);
        applicantDataPage.clickNextButton();
        serviceOptionPage.clickBirthApplicationButton();
        citizenDataPage.setAllCitizenData(userCitizen);
        citizenDataPage.nextButton.shouldBe(Condition.enabled);
    }

    @Test
    @Description("Тест: перейти на страницу Данные услуги / регистрация рождения")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetServiceDataPageBirth(){
        authorizationPage.clickUserButton();
        applicantDataPage.setAllApplicantData(userApplicant);
        applicantDataPage.clickNextButton();
        serviceOptionPage.clickBirthApplicationButton();
        citizenDataPage.setAllCitizenData(userCitizen);
        citizenDataPage.clickNextButton();
        serviceDataPage.checkFieldsAtServiceDataPageBirth();
    }
    @Test
    @Description("Тест: заполнить все поля в форме Данные услуги / регистрация рождения")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    public void testSetServiceDataBirth(){
        authorizationPage.clickUserButton();
        applicantDataPage.setAllApplicantData(userApplicant);
        applicantDataPage.clickNextButton();
        serviceOptionPage.clickBirthApplicationButton();
        citizenDataPage.setAllCitizenData(userCitizen);
        citizenDataPage.clickNextButton();
        serviceDataPage.setAllBirthServiceData(userService);
        serviceDataPage.finishButton.should(Condition.enabled);
    }

    @Test
    @Description("Тест: отправить заявку на регистрацию рождения ")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    public void testSendBirthApplication(){
        authorizationPage.clickUserButton();
        applicantDataPage.setAllApplicantData(userApplicant);
        applicantDataPage.clickNextButton();
        serviceOptionPage.clickBirthApplicationButton();
        citizenDataPage.setAllCitizenData(userCitizen);
        citizenDataPage.clickNextButton();
        serviceDataPage.setAllBirthServiceData(userService);
        serviceDataPage.clickFinishButton()
                .statusMessage.should(Condition.exist);
    }

    @Test
    @Description("Тест: выбрать услугу Регистрация смерти")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetDeathApplication() {
        authorizationPage.clickUserButton();
        applicantDataPage.setAllApplicantData(userApplicant);
        applicantDataPage.clickNextButton();
        serviceOptionPage.clickDeathApplicationButton();
        citizenDataPage.checkFieldsAtCitizenDataPage();
    }

    @Test
    @Description("Тест: заполнить все поля в форме Данные гражданина / регистрация смерти")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    public void testSetCitizenDataDeath(){
        authorizationPage.clickUserButton();
        applicantDataPage.setAllApplicantData(userApplicant);
        applicantDataPage.clickNextButton();
        serviceOptionPage.clickDeathApplicationButton();
        citizenDataPage.setAllCitizenData(userCitizen);
        citizenDataPage.nextButton.shouldBe(Condition.enabled);
    }

    @Test
    @Description("Тест: перейти на страницу Данные услуги / регистрация смерти")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    public void testGetServiceDataPageDeath(){
        authorizationPage.clickUserButton();
        applicantDataPage.setAllApplicantData(userApplicant);
        applicantDataPage.clickNextButton();
        serviceOptionPage.clickDeathApplicationButton();
        citizenDataPage.setAllCitizenData(userCitizen);
        citizenDataPage.clickNextButton();
        serviceDataPage.checkFieldsAtServiceDataPageDeath();
    }
    @Test
    @Description("Тест: заполнить все поля в форме Данные услуги / регистрация смерти")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    public void testSetServiceDataDeath(){
        authorizationPage.clickUserButton();
        applicantDataPage.setAllApplicantData(userApplicant);
        applicantDataPage.clickNextButton();
        serviceOptionPage.clickDeathApplicationButton();
        citizenDataPage.setAllCitizenData(userCitizen);
        citizenDataPage.clickNextButton();
        serviceDataPage.setAllDeathServiceData(userService);
        serviceDataPage.finishButton.should(Condition.enabled);
    }

    @Test
    @Description("Тест: отправить заявку на регистрацию смерти ")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    public void testSendDeathApplication(){
        authorizationPage.clickUserButton();
        applicantDataPage.setAllApplicantData(userApplicant);
        applicantDataPage.clickNextButton();
        serviceOptionPage.clickDeathApplicationButton();
        citizenDataPage.setAllCitizenData(userCitizen);
        citizenDataPage.clickNextButton();
        serviceDataPage.setAllDeathServiceData(userService);
        serviceDataPage.clickFinishButton()
                .statusMessage.should(Condition.exist);
    }

    @Test
    @Description("Тест: заполнить данные администратора валидными данными")
    @Feature("Регистрация админа")
    @Severity(SeverityLevel.CRITICAL)
    public void testSetAdminData(){
        authorizationPage.clickAdminButton();
        adminDataPage.setAllAdminData(administrator);
        adminDataPage.nextButton.shouldBe(Condition.enabled);
    }

    @Test
    @Description("Тест: отправить заявку на регистрацию администратора")
    @Feature("Регистрация админа")
    @Severity(SeverityLevel.CRITICAL)
    public void testSendAdminApplication(){
        authorizationPage.clickAdminButton();
        adminDataPage.setAllAdminData(administrator);
        adminDataPage.clickNextButton();
        applicationListPage.table.should(Condition.exist);
    }
}
