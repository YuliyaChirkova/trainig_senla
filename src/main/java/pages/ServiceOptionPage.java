package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class ServiceOptionPage {
    public SelenideElement marriageApplicationButton =$(byXpath("//button[text() = 'Регистрация брака']"));
    public SelenideElement birthApplicationButton =$(byXpath("//button[text() = 'Регистрация рождения']"));
    public SelenideElement deathApplicationButton =$(byXpath("//button[text() = 'Регистрация смерти']"));

    @Step("Выбрать Регистрацию брака")
    public void clickMarriageApplicationButton(){
        marriageApplicationButton.click();
    }
    @Step("Выбрать Регистрацию рождения")
    public void clickBirthApplicationButton(){
        birthApplicationButton.click();
    }
    @Step("Выбрать Регистрацию смерти")
    public void clickDeathApplicationButton(){
        deathApplicationButton.click();
    }

}
