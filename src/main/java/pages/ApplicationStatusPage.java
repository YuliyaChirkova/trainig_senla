package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class ApplicationStatusPage {
    public SelenideElement statusMessage =$(byXpath("//span[text() = 'Ваша заявка отправлена на рассмотрение. ']"));
}
