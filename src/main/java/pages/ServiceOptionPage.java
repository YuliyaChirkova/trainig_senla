package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ServiceOptionPage {
    public SelenideElement marriageApplicationButton =$(byText("Регистрация брака"));
    public SelenideElement birthApplicationButton =$(byText("Регистрация рождения"));
    public SelenideElement deathApplicationButton =$(byText("Регистрация рождения"));

}
