package tests;

import com.codeborne.selenide.Condition;
import data.User;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import pages.ApplicantDataPage;
import pages.AuthorizationPage;
import pages.ServiceOptionPage;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestClass extends BeforeAfterEach{
    AuthorizationPage authorizationPage = new AuthorizationPage();
    ApplicantDataPage applicantDataPage = new ApplicantDataPage();
    ServiceOptionPage serviceDataPage = new ServiceOptionPage();
    User userApplicant = new User("Ealon", "Mask", "James", "3333333", "1234567");



    @Test
    @Description("Тест: войти как пользователь")
    public void testUser(){
        authorizationPage.openAuthorizationPage();
        authorizationPage.setUserButton();
    }

    @Test
    @Description("Тест: войти как администратор")
    public void testAdmin(){
        authorizationPage.openAuthorizationPage();
        authorizationPage.setAdminButton();
    }

    @Test
    @Description("Тест: заполнить обязательные поля в форме Данные заявителя")
    public void setApplicantData(){
        authorizationPage.openAuthorizationPage();
        authorizationPage.setUserButton();
        applicantDataPage.setAllApplicantData(userApplicant);
        applicantDataPage.nextButton.shouldBe(Condition.enabled);
    }
    @Test
    @Description("Тест: перейти на страницу Выбора услуги")
    public void getServiceDataPage(){
        authorizationPage.openAuthorizationPage();
        authorizationPage.setUserButton();
        applicantDataPage.setAllApplicantData(userApplicant);
        applicantDataPage.clickNextButton();
        serviceDataPage.marriageApplicationButton.should(Condition.exist);
        serviceDataPage.birthApplicationButton.should(Condition.exist);
        serviceDataPage.deathApplicationButton.should(Condition.exist);
    }

}
