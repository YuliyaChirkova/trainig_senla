package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class ApplicationListPage {

    private SelenideElement columnNumber = $(byXpath("//th[text()='№']"));
    private SelenideElement columnApplicant = $(byXpath("//th[text()='Заявитель']"));
    private SelenideElement columnType = $(byXpath("//th[text()='Тип']"));
    private SelenideElement columnTime = $(byXpath("//th[text()='Время']"));
    private SelenideElement columnStatus = $(byXpath("//th[text()='Статус']"));
    private SelenideElement columnAction = $(byXpath("//th[text()='Действие']"));

    public SelenideElement getColumnNumber() {
        return columnNumber;
    }

    public SelenideElement getColumnApplicant() {
        return columnApplicant;
    }

    public SelenideElement getColumnType() {
        return columnType;
    }

    public SelenideElement getColumnTime() {
        return columnTime;
    }

    public SelenideElement getColumnStatus() {
        return columnStatus;
    }

    public SelenideElement getColumnAction() {
        return columnAction;
    }
}
