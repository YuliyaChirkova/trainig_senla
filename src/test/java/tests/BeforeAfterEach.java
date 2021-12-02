package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.AuthorizationPage;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;


public class BeforeAfterEach {
    AuthorizationPage authorizationPage = new AuthorizationPage();
    @BeforeEach
    public void setUp() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().screenshots(true).savePageSource(false));
        Configuration.startMaximized = true;
       // Configuration.browser = "firefox";
        authorizationPage.openAuthorizationPage();
        webdriver().shouldHave(url(authorizationPage.url));
    }

    @AfterAll
    public void tearDown() {
        closeWebDriver();
    }
}
