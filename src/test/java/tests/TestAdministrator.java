package tests;

import com.codeborne.selenide.Condition;
import data.Administrator;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import pages.AdminDataPage;
import pages.ApplicationListPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestAdministrator extends BeforeAfterEach{

    AdminDataPage adminDataPage = new AdminDataPage();
    ApplicationListPage applicationListPage = new ApplicationListPage();
    Administrator administrator = new Administrator("Sergei", "Sergeev",
            "Sergeevich", "5555555", "7777777", "03031993");

    @Test
    @Description("Тест: войти как администратор")
    @Feature("Авторизация")
    @Severity(SeverityLevel.BLOCKER)
    public void testAdmin(){
        authorizationPage.clickAdminButton();
        adminDataPage.adminMessage.should(Condition.exist);
    }

    @Test
    @Description("Тест: заполнить данные администратора валидными данными")
    @Feature("Регистрация админа")
    @Severity(SeverityLevel.CRITICAL)
    public void testSetAdminData(){
        authorizationPage.clickAdminButton();
        adminDataPage.setAllAdminData(administrator);
        adminDataPage.checkNextButtonIsEnabled();
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
