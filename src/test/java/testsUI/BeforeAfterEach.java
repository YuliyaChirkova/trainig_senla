package testsUI;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import data.Administrator;
import data.User;
import dataBaseConnect.JDBCConnection;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import static com.codeborne.selenide.Configuration.browserVersion;
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
    public void setUp() throws MalformedURLException {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(true).savePageSource(false));
        Configuration.startMaximized = true;

        Configuration.browserBinary = "/opt/google/chrome/google-chrome";//"/usr/bin/google-chrome";
        Configuration.browserVersion = "96.0";

        Configuration.browserCapabilities.setCapability("--headless", true);
        Configuration.browserCapabilities.setCapability("--no-sandbox", true);
        Configuration.browserCapabilities.setCapability("useAutomationExtension", true);
        Configuration.browserCapabilities.setCapability("/usr/bin/google-chrome", true);
        Configuration.browserCapabilities.setCapability("--disable-dev-shm-usage", true);
        authorizationPage.openAuthorizationPage(); // здесь вызов метода open(url)
        webdriver().shouldHave(url(authorizationPage.getUrl()));
    }



//    @BeforeAll
//    public void setUp() throws MalformedURLException {
//        SelenideLogger.addListener("AllureSelenide",
//                new AllureSelenide().screenshots(true).savePageSource(false));
//       // Url удалённого веб драйвера
//        Configuration.remote = "http://10.0.75.1:4444/wd/hub";
//        //Определяем какой браузер будем использовать
//        Configuration.browser = "chrome";
//        //Размер окна браузера
//        Configuration.browserSize = "1920x1080";
//        //Создаём объект класса DesiredCapabilities, используется как настройка  вашей конфигурации с помощью пары ключ-значение
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        //Включить поддержку отображения экрана браузера во время выполнения теста
//        capabilities.setCapability( "enableVNC",  true);
//        //Включение записи видео в процессе выполнения тестов
//        capabilities.setCapability( "enableVideo",  true);
//        //Переопределяем Browser capabilities
//        Configuration.browserCapabilities = capabilities;
//
//        authorizationPage.openAuthorizationPage(); // здесь вызов метода open(url)
//      //  webdriver().shouldHave(url(authorizationPage.getUrl()));
//    }

    @AfterAll
    public void tearDown() {
        closeWebDriver();
    }
}


