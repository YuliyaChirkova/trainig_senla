package testsUI;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestAdministrator extends BeforeAfterEach{

    @Test
    @Description("Тест: войти как администратор")
    @Feature("Авторизация")
    @Severity(SeverityLevel.BLOCKER)
    @Order(1)
    public void testAdmin(){
        authorizationPage.clickAdminButton();
        adminDataPage.checkMessageText();
        adminDataPage.checkFieldsAtApplicantDataPage();
    }

    @Test
    @Description("Тест: заполнить данные администратора валидными данными")
    @Feature("Регистрация админа")
    @Severity(SeverityLevel.CRITICAL)
    @Order(2)
    public void testSetAdminData(){
        adminDataPage.setAllAdminData(administrator);
        adminDataPage.checkNextButtonIsEnabled();
    }

    @Test
    @Description("Тест: отправить заявку на регистрацию администратора")
    @Feature("Регистрация админа")
    @Severity(SeverityLevel.CRITICAL)
    @Order(3)
    public void testSendAdminApplication(){
        adminDataPage.clickNextButton();
        applicationListPage.checkTableColumn();
    }
}
