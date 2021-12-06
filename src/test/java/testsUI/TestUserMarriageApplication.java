package testsUI;

import com.codeborne.selenide.Condition;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;

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
    @Description("Тест: создать новую заявку со страницы Статус заявки ")
    @Feature("Регистрация")
    @Severity(SeverityLevel.NORMAL)
    @Order(9)
    public void testCreateNewApplication() {
        applicationStatusPage.clickCreateNewApplicationButton();
        applicantDataPage.getApplicantLastName().should(Condition.exist);
        applicantDataPage.getApplicantFirstName().should(Condition.exist);
        applicantDataPage.getApplicantMiddleName().should(Condition.exist);
        applicantDataPage.getApplicantPhoneNumber().should(Condition.exist);
        applicantDataPage.getApplicantPassportNumber().should(Condition.exist);
    }
}
