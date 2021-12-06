package testsUI;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestUserDeathApplication extends BeforeAfterEach {

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
    @Description("Тест: выбрать услугу Регистрация смерти")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(4)
    public void testGetDeathApplication() {
        serviceOptionPage.clickDeathApplicationButton();
        citizenDataPage.getCitizenLastName().should(Condition.exist);
        citizenDataPage.getCitizenFirstName().should(Condition.exist);
        citizenDataPage.getCitizenMiddleName().should(Condition.exist);
        citizenDataPage.getCitizenBirthDate().should(Condition.exist);
        citizenDataPage.getCitizenPassportNumber().should(Condition.exist);
        citizenDataPage.getCitizenGender().should(Condition.exist);
    }

    @Test
    @Description("Тест: заполнить все поля в форме Данные гражданина / регистрация смерти")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(5)
    public void testSetCitizenDataDeath() {
        citizenDataPage.setAllCitizenData(userCitizen);
        citizenDataPage.getNextButton().shouldBe(Condition.enabled);
    }

    @Test
    @Description("Тест: перейти на страницу Данные услуги / регистрация смерти")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(6)
    public void testGetServiceDataPageDeath() {
        citizenDataPage.clickNextButton();
        serviceDataPage.getDeathPlace().should(Condition.exist);
        serviceDataPage.getDeathDate().should(Condition.exist);
    }

    @Test
    @Description("Тест: заполнить все поля в форме Данные услуги / регистрация смерти")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(7)
    public void testSetServiceDataDeath() {
        serviceDataPage.setAllDeathServiceData(userService);
        serviceDataPage.getFinishButton().shouldBe(Condition.enabled);
    }

    @Test
    @Description("Тест: отправить заявку на регистрацию смерти ")
    @Feature("Регистрация")
    @Severity(SeverityLevel.CRITICAL)
    @Order(8)
    public void testSendDeathApplication() {
        serviceDataPage.clickFinishButton()
                .getStatusMessage().shouldHave(Condition.exactText("Ваша заявка отправлена на рассмотрение. "));
    }

}
