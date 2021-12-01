package tests;

import com.codeborne.selenide.Condition;
import data.User;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.*;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;
import pages.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestClass extends BeforeAfterEach{
    AuthorizationPage authorizationPage = new AuthorizationPage();
    ApplicantDataPage applicantDataPage = new ApplicantDataPage();
    ServiceOptionPage serviceOptionPage = new ServiceOptionPage();
    CitizenDataPage citizenDataPage = new CitizenDataPage();
    ServiceDataPage serviceDataPage = new ServiceDataPage();
    ApplicationStatusPage applicationStatusPage = new ApplicationStatusPage();


    User userApplicant = new User("Ealon", "Mask",
            "James", "3333333", "1234567");
    User userCitizen = new User("Ivanov", "Ivan",
            "Ivanovich", "02021992", "2222222","male");
    User userService = new User("08082022", "Mask",
            "Petrova", "Olga", "Petrovna", "13131996", "4444444");

    @Test
    @Description("Тест: войти как пользователь")
    public void testUser(){
        authorizationPage.clickUserButton();
        authorizationPage.userMessage.should(Condition.exist);
    }

    @Test
    @Description("Тест: войти как администратор")
    public void testAdmin(){
        authorizationPage.clickAdminButton();
        authorizationPage.adminMessage.should(Condition.exist);
    }

    @Test
    @Description("Тест: заполнить обязательные поля в форме Данные заявителя")
    public void setApplicantData(){
        authorizationPage.openAuthorizationPage();
        authorizationPage.clickUserButton();
        applicantDataPage.setAllApplicantData(userApplicant);
        applicantDataPage.nextButton.shouldBe(Condition.enabled);
    }
    @Test
    @Description("Тест: перейти на страницу Выбора услуги")
    public void getServiceOptionPage(){
        authorizationPage.openAuthorizationPage();
        authorizationPage.clickUserButton();
        applicantDataPage.setAllApplicantData(userApplicant);
        applicantDataPage.clickNextButton();
        //проверка:
        serviceOptionPage.marriageApplicationButton.should(Condition.exist);
        serviceOptionPage.birthApplicationButton.should(Condition.exist);
        serviceOptionPage.deathApplicationButton.should(Condition.exist);
    }

    @Test
    @Description("Тест: выбрать услугу Регистрация брака")
    public void getMarriageApplication() {
        authorizationPage.openAuthorizationPage();
        authorizationPage.clickUserButton();
        applicantDataPage.setAllApplicantData(userApplicant);
        applicantDataPage.clickNextButton();
        serviceOptionPage.marriageApplicationButton.should(Condition.exist);
        serviceOptionPage.clickMarriageApplicationButton();
        //проверка
    }

    @Test
    @Description("Тест: заполнить все поля в форме Данные гражданина")
    public void setCitizenData(){
        authorizationPage.openAuthorizationPage();
        authorizationPage.clickUserButton();
        applicantDataPage.setAllApplicantData(userApplicant);
        applicantDataPage.clickNextButton();
        serviceOptionPage.clickMarriageApplicationButton();
        citizenDataPage.setAllCitizenData(userCitizen);
        citizenDataPage.nextButton.shouldBe(Condition.enabled);
    }

    @Test
    @Description("Тест: перейти на страницу Данные услуги")
    public void getServiceDataPage(){
      authorizationPage.openAuthorizationPage();
        authorizationPage.clickUserButton();
        applicantDataPage.setAllApplicantData(userApplicant);
        applicantDataPage.clickNextButton();
        serviceOptionPage.clickMarriageApplicationButton();
        citizenDataPage.setAllCitizenData(userCitizen);
       // citizenDataPage.nextButton.shouldBe(Condition.enabled);
        citizenDataPage.clickNextButton();
        //проверка
    }

    @Test
    @Description("Тест: заполнить все поля в форме Данные услуги")
    public void setServiceData(){
        authorizationPage.openAuthorizationPage();
        authorizationPage.clickUserButton();
        applicantDataPage.setAllApplicantData(userApplicant);
        applicantDataPage.clickNextButton();
        serviceOptionPage.clickMarriageApplicationButton();
        citizenDataPage.setAllCitizenData(userCitizen);
        citizenDataPage.clickNextButton();
        serviceDataPage.setAllServiceData(userService);
        serviceDataPage.finishButton.should(Condition.enabled);
    }
    @Test
    @Description("Тест: отправить заявку на регистрацию брака ")
    public void sendMarriageApplication(){
        authorizationPage.openAuthorizationPage();
        authorizationPage.clickUserButton();
        applicantDataPage.setAllApplicantData(userApplicant);
        applicantDataPage.clickNextButton();
        serviceOptionPage.clickMarriageApplicationButton();
        citizenDataPage.setAllCitizenData(userCitizen);
        citizenDataPage.clickNextButton();
        serviceDataPage.setAllServiceData(userService);
        serviceDataPage.clickFinishButton()
                .statusMessage.should(Condition.exist);
        applicationStatusPage.statusMessage.should(Condition.exist);
    }

}
