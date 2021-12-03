package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestUserDeathApplication extends BeforeAfterEach{

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
    @Description("Тест: выбрать услугу Регистрация смерти")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(4)
    public void testGetDeathApplication() {
        serviceOptionPage.clickDeathApplicationButton();
        citizenDataPage.checkFieldsAtCitizenDataPage();
    }

    @Test
    @Description("Тест: заполнить все поля в форме Данные гражданина / регистрация смерти")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(5)
    public void testSetCitizenDataDeath(){
        citizenDataPage.setAllCitizenData(userCitizen);
        citizenDataPage.checkNextButtonIsEnabled();
    }

    @Test
    @Description("Тест: перейти на страницу Данные услуги / регистрация смерти")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(6)
    public void testGetServiceDataPageDeath(){
        citizenDataPage.clickNextButton();
        serviceDataPage.checkFieldsAtServiceDataPageDeath();
    }

    @Test
    @Description("Тест: заполнить все поля в форме Данные услуги / регистрация смерти")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(7)
    public void testSetServiceDataDeath(){
        serviceDataPage.setAllDeathServiceData(userService);
        serviceDataPage.checkFinishButtonIsEnabled();
    }

    @Test
    @Description("Тест: отправить заявку на регистрацию смерти ")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(8)
    public void testSendDeathApplication(){
        serviceDataPage.clickFinishButton()
                .checkMessageText();
    }

}
