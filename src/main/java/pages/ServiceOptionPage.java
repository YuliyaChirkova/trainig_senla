package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class ServiceOptionPage {

    private SelenideElement marriageApplicationButton = $(byXpath("//button[text() = 'Регистрация брака']"));
    private SelenideElement birthApplicationButton = $(byXpath("//button[text() = 'Регистрация рождения']"));
    private SelenideElement deathApplicationButton = $(byXpath("//button[text() = 'Регистрация смерти']"));

    public SelenideElement getMarriageApplicationButton() {
        return marriageApplicationButton;
    }

    public SelenideElement getBirthApplicationButton() {
        return birthApplicationButton;
    }

    public SelenideElement getDeathApplicationButton() {
        return deathApplicationButton;
    }

    @Step("Выбрать Регистрацию брака")
    public void clickMarriageApplicationButton() {
        marriageApplicationButton.click();
    }

    @Step("Выбрать Регистрацию рождения")
    public void clickBirthApplicationButton() {
        birthApplicationButton.click();
    }

    @Step("Выбрать Регистрацию смерти")
    public void clickDeathApplicationButton() {
        deathApplicationButton.click();
    }

}
