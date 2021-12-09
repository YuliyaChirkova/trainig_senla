package testsUI;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

//@Execution(ExecutionMode.CONCURRENT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestAdministrator extends BeforeAfterEach {

    @Test
    @Description("Тест: войти как администратор")
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
    @Description("Тест: заполнить данные администратора валидными данными")
    @Feature("Регистрация админа")
    @Severity(SeverityLevel.CRITICAL)
    @Order(2)
    public void testSetAdminData() {
        adminDataPage.setAllAdminData(administrator);
        adminDataPage.getNextButton().shouldBe(Condition.enabled);
    }

    @Test
    @Description("Тест: отправить заявку на регистрацию администратора")
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
}
