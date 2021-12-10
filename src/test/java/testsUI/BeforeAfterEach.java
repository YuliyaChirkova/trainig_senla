package testsUI;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.Administrator;
import data.User;
import dataBaseConnect.JDBCConnection;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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

    User userApplicant = new User("Ealon", "Mask",
            "James", "3333333", "1234567");
    User userCitizen = new User("Ivanov", "Ivan",
            "Ivanovich", "02021992", "2222222", "male");
    User userService = new User("08082022", "Mask",
            "Petrova", "Olga", "Petrovna", "13131996",
            "4444444", "Brest", "Nonna", "Sebastian",
            "Deli", "01012011");

    Administrator administrator = new Administrator("Sergei", "Sergeev",
            "Sergeevich", "5555555", "7777777", "03031993");

    protected int applicantid;
    protected int citizenid;
    protected WebDriver driver;


    public  int getApplicantID(String applicantSurname) throws SQLException {
        String selectQuery ="select * from applicants where surname='" + applicantSurname+ "' order by applicantid desc limit 1";
        ResultSet rs = JDBCConnection.selectFromTable(selectQuery);
        applicantid = rs.getInt("applicantid");
        return applicantid;
    }

    public  int getCitizenID(String applicantSurname) throws SQLException {
        String selectQuery ="select citizenid from applications where applicantid=(select applicantid from applicants where surname='" + applicantSurname + "' order by applicantid desc limit 1) order by applicantid desc limit 1";
        ResultSet rs = JDBCConnection.selectFromTable(selectQuery);
        citizenid = rs.getInt("citizenid");
        return citizenid;
    }

    @BeforeAll
    public void setUp() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(true).savePageSource(false));
//        Configuration.startMaximized = true;

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver2.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");

//        options.addArguments("start-maximized"); // open Browser in maximized mode
//        options.addArguments("disable-infobars"); // disabling infobars
//        options.addArguments("--disable-extensions"); // disabling extensions
//        options.addArguments("--disable-gpu"); // applicable to windows os only
//        options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
//        options.addArguments("--no-sandbox"); // Bypass OS security model
        options.setExperimentalOption("useAutomationExtension", false);
        driver = new ChromeDriver(options);
//        driver.get("https://user:senlatest@regoffice.senla.eu/");
       authorizationPage.openAuthorizationPage();
        webdriver().shouldHave(url(authorizationPage.getUrl()));
    }

    @AfterAll
    public void tearDown() {
        closeWebDriver();
    }
}
