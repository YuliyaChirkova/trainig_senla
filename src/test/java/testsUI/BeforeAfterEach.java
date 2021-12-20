package testsUI;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.Administrator;
import data.User;
import dataBaseConnect.JDBCConnection;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class BeforeAfterEach {

    AuthorizationPage authorizationPage = new AuthorizationPage();
    ApplicantDataPage applicantDataPage = new ApplicantDataPage();
    ServiceOptionPage serviceOptionPage = new ServiceOptionPage();
    CitizenDataPage citizenDataPage = new CitizenDataPage();
    ServiceDataPage serviceDataPage = new ServiceDataPage();
    ApplicationStatusPage applicationStatusPage = new ApplicationStatusPage();
    AdminDataPage adminDataPage = new AdminDataPage();
    ApplicationListPage applicationListPage = new ApplicationListPage();

    User marriageApplicationUser = new User.Builder()
            .withName("Gustav")
            .withSurname("Klimt")
            .withMiddleName("Klimt")
            .withPhoneNumber("1111111")
            .withPassportNumber("2222222")
            .withCitizenFirstName("Gustav")
            .withCitizenLastName("Klimt")
            .withCitizenMiddleName("Klimt")
            .withCitizenBirthDate("02021892")
            .withCitizenPassportNumber("2222222")
            .withCitizenGender("Male")
            .withMarriageRegistrationDate("03031933")
            .withSpouseNewLastName("Klimt")
            .withSpouseLastName("Brown")
            .withSpouseFirstName("Adele")
            .withSpouseMiddleName("B")
            .withSpouseBirthDate("01011904")
            .withSpousePassportNumber("333333")
            .withMode("wedding")
            .build();

    User birthApplicationUser = new User.Builder()
            .withName("Klod")
            .withSurname("Mone")
            .withMiddleName("MoneMone")
            .withPhoneNumber("4444444")
            .withPassportNumber("5555555")
            .withCitizenFirstName("Klod")
            .withCitizenLastName("Mone")
            .withCitizenMiddleName("MoneMone")
            .withCitizenBirthDate("04041888")
            .withCitizenPassportNumber("5555555")
            .withCitizenGender("Male")
            .withBirthPlace("Paris")
            .withMotherName("Matilda")
            .withFatherName("Peter")
            .build();

    User deathApplicationUser = new User.Builder()
            .withName("Frida")
            .withSurname("Kahlo")
            .withMiddleName("Frida")
            .withPhoneNumber("7777777")
            .withPassportNumber("8888888")
            .withCitizenFirstName("Frida")
            .withCitizenLastName("Kahlo")
            .withCitizenMiddleName("Frida")
            .withCitizenBirthDate("06071907")
            .withCitizenPassportNumber("8888888")
            .withCitizenGender("Female")
            .withDeathPlace("Madrid")
            .withDeathDate("13071954")
            .build();

    Administrator administrator = new Administrator.Builder()
            .withName("Sergei")
            .withSurname("Sergeev")
            .withMiddleName("Sergeevich")
            .withPhoneNumber("5555555")
            .withPassportNumber("7777777")
            .withDateofbirth("2021-12-23")
            .build();

    protected int applicantid;
    protected int citizenid;

    public  int getApplicantID(String applicantSurname) throws SQLException {
        String selectQuery ="select * from applicants where surname='" + applicantSurname+ "' order by applicantid desc limit 1";
        ResultSet rs = JDBCConnection.selectFromTable(selectQuery);
        applicantid = rs.getInt("applicantid");
        JDBCConnection.closeConnection();
        return applicantid;
    }

    public  int getCitizenID(String applicantSurname) throws SQLException {
        String selectQuery ="select citizenid from applications where applicantid=(select applicantid from applicants where surname='" + applicantSurname + "' order by applicantid desc limit 1)";// order by applicantid desc limit 1
        ResultSet rs = JDBCConnection.selectFromTable(selectQuery);
        citizenid = rs.getInt("citizenid");
        JDBCConnection.closeConnection();
        return citizenid;
    }

//    @BeforeAll
//    public void setUp() {
//        SelenideLogger.addListener("AllureSelenide",
//                new AllureSelenide().screenshots(true).savePageSource(false));
//        Configuration.startMaximized = true;
//
//        Configuration.browserBinary = "/usr/bin/google-chrome";
//        Configuration.browserVersion = "96.0";
//
//        Configuration.browserCapabilities.setCapability("--headless", true);
//        Configuration.browserCapabilities.setCapability("--no-sandbox", true);
//        Configuration.browserCapabilities.setCapability("useAutomationExtension", true);
//        Configuration.browserCapabilities.setCapability("/usr/bin/google-chrome", true);
//        Configuration.browserCapabilities.setCapability("--disable-dev-shm-usage", true);
//        authorizationPage.openAuthorizationPage();
//        webdriver().shouldHave(url(authorizationPage.getUrl()));
//    }

    @BeforeAll
    public void setUp()  {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(true).savePageSource(false));

        Configuration.remote ="http://localhost:4444/wd/hub";
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability( "enableVNC",  true);
        capabilities.setCapability( "enableVideo",  true);
        Configuration.browserCapabilities = capabilities;
        authorizationPage.openAuthorizationPage();
        webdriver().shouldHave(url(authorizationPage.getUrl()));
    }

    @AfterAll
    public void tearDown() {
        closeWebDriver();
    }

}


