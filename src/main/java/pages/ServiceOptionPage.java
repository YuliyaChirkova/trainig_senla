package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class ServiceOptionPage {
    private SelenideElement marriageApplicationButton =$(byXpath("//button[text() = 'Регистрация брака']"));
    private SelenideElement birthApplicationButton =$(byXpath("//button[text() = 'Регистрация рождения']"));
    private SelenideElement deathApplicationButton =$(byXpath("//button[text() = 'Регистрация смерти']"));

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

    @Step("Проверить наличие всех кнопок")
    public void checkAllButtons(){
        marriageApplicationButton.should(Condition.exist);
        birthApplicationButton.should(Condition.exist);
        deathApplicationButton.should(Condition.exist);
    }

}
