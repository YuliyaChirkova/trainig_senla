package pages;

import com.codeborne.selenide.SelenideElement;
import data.User;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthorizationPage {
    public SelenideElement userButton =$(byText("Войти как пользователь"));
    public SelenideElement adminButton =$(byText("Войти как администратор"));

    @Step("Открытие страницы авторизации")
    public void openAuthorizationPage() {
        open("https://user:senlatest@regoffice.senla.eu/");
    }

    @Step("Войти как пользователь")
    public void setUserButton(){
        userButton.click();
    }

    @Step("Войти как администратор")
    public void setAdminButton(){
        userButton.click();
    }
}
