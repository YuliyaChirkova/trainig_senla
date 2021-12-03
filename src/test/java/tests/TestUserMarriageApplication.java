package tests;

import com.codeborne.selenide.Condition;
import data.Administrator;
import data.User;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestUserMarriageApplication extends BeforeAfterEach{


    @Test
    @Description("Тест: войти как пользователь")
    @Feature("Авторизация")
    @Severity(SeverityLevel.BLOCKER)
    @Order(1)
    public void testUser(){
        authorizationPage.clickUserButton()
                .checkMessageText();
        applicantDataPage.checkFieldsAtApplicantDataPage();
    }

    @Test
    @Description("Тест: заполнить обязательные поля в форме Данные заявителя")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(2)
    public void testSetApplicantData(){
        applicantDataPage.setAllApplicantData(userApplicant)
                .checkNextButtonIsEnabled();
    }

    @Test
    @Description("Тест: перейти на страницу Выбора услуги")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(3)
    public void testGetServiceOptionPage(){
        applicantDataPage.clickNextButton()
                .checkAllButtons();
    }

    @Test
    @Description("Тест: выбрать услугу Регистрация брака")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(4)
    public void testGetMarriageApplication() {
        serviceOptionPage.clickMarriageApplicationButton();
        citizenDataPage.checkFieldsAtCitizenDataPage();
    }

    @Test
    @Description("Тест: заполнить все поля в форме Данные гражданина / регистрация брака")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(5)
    public void testSetCitizenDataMarriage(){
        citizenDataPage.setAllCitizenData(userCitizen);
        citizenDataPage.checkNextButtonIsEnabled();
    }

    @Test
    @Description("Тест: перейти на страницу Данные услуги / регистрация брака")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(6)
    public void testGetServiceDataPage(){
        citizenDataPage.clickNextButton();
        serviceDataPage.checkFieldsAtServiceDataPageMarriage();
    }

    @Test
    @Description("Тест: заполнить все поля в форме Данные услуги / регистрация брака")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(7)
    public void testSetServiceDataMarriage(){
        serviceDataPage.setAllMarriageServiceData(userService);
        serviceDataPage.checkFinishButtonIsEnabled();
    }

    @Test
    @Description("Тест: отправить заявку на регистрацию брака ")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(8)
    public void testSendMarriageApplication(){
        serviceDataPage.clickFinishButton()
                .checkMessageText();
    }

    @Test
    @Description("Тест: создать новую заявку со страницы Статус заявки ")
    @Feature("Регистрация")
    @Severity(SeverityLevel.NORMAL)
    @Order(9)
        public void testCreateNewApplication(){
            applicationStatusPage.clickCreateNewApplicationButton();
            applicantDataPage.checkFieldsAtApplicantDataPage();
    }
}
