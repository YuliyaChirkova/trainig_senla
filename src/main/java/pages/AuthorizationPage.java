package pages;

import com.codeborne.selenide.SelenideElement;
import data.User;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthorizationPage {
    private SelenideElement userButton =$(byXpath("//button[text() = 'Войти как пользователь']"));
    private SelenideElement adminButton =$(byXpath("//button[text() = 'Войти как администратор']"));
    private String url = "https://user:senlatest@regoffice.senla.eu/";
    ApplicantDataPage applicantDataPage = new ApplicantDataPage();

    public String getUrl() {
        return url;
    }

    @Step("Открытие страницы авторизации")
    public void openAuthorizationPage() {
        open(url);
    }

    @Step("Войти как пользователь")
    public ApplicantDataPage clickUserButton(){
        userButton.click();
        return applicantDataPage;
    }

    @Step("Войти как администратор")
    public void clickAdminButton(){
        adminButton.click();
    }

    @Step("Открыть страницу и войти как пользователь")
    public void openPageAndClickUserButton(){
        open(url);
        userButton.click();
    }

    @Step("Открыть страницу и войти как администратор")
    public void openPageAndClickAdminButton(){
        open(url);
        adminButton.click();
    }
}
