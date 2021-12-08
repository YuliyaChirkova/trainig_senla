package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class ApplicationStatusPage {

    private SelenideElement statusMessage = $(byXpath("//span[text() = 'Ваша заявка отправлена на рассмотрение. ']"));
    private SelenideElement buttonCreateNewApplication = $(byXpath("//button[text() = 'Создать новую заявку']"));

    public SelenideElement getStatusMessage() {
        return statusMessage;
    }

    @Step("Нажать кнопку Создать новую заявку")
    public void clickCreateNewApplicationButton() {
        buttonCreateNewApplication.shouldBe(Condition.enabled).click();
    }
}
