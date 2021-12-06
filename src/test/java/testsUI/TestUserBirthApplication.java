package testsUI;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestUserBirthApplication extends BeforeAfterEach{

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
    @Description("Тест: выбрать услугу Регистрация рождения")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(4)
    public void testGetBirthApplication() {
        serviceOptionPage.clickBirthApplicationButton();
        citizenDataPage.checkFieldsAtCitizenDataPage();
    }

    @Test
    @Description("Тест: заполнить все поля в форме Данные гражданина / регистрация рождения")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(5)
    public void testSetCitizenDataBirth(){
        citizenDataPage.setAllCitizenData(userCitizen);
        citizenDataPage.checkNextButtonIsEnabled();
    }

    @Test
    @Description("Тест: перейти на страницу Данные услуги / регистрация рождения")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(6)
    public void testGetServiceDataPageBirth(){
        citizenDataPage.clickNextButton();
        serviceDataPage.checkFieldsAtServiceDataPageBirth();
    }

    @Test
    @Description("Тест: заполнить все поля в форме Данные услуги / регистрация рождения")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(7)
    public void testSetServiceDataBirth(){
        serviceDataPage.setAllBirthServiceData(userService);
        serviceDataPage.checkFinishButtonIsEnabled();
    }

    @Test
    @Description("Тест: отправить заявку на регистрацию рождения ")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(8)
    public void testSendBirthApplication(){
        serviceDataPage.clickFinishButton()
                .checkMessageText();
    }
}
