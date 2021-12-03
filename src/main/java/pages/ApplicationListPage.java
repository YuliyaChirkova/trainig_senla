package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class ApplicationListPage {

    private SelenideElement columnNumber =$(byXpath("//th[text()='№']"));
    private SelenideElement columnApplicant =$(byXpath("//th[text()='Заявитель']"));
    private SelenideElement columnType =$(byXpath("//th[text()='Тип']"));
    private SelenideElement columnTime =$(byXpath("//th[text()='Время']"));
    private SelenideElement columnStatus =$(byXpath("//th[text()='Статус']"));
    private SelenideElement columnAction =$(byXpath("//th[text()='Действие']"));

    @Step("Проверить, что в таблице заявок присутствуют все необходимые колонки")
    public void checkTableColumn(){
        columnNumber.shouldHave(Condition.exactText("№"));
        columnApplicant.shouldHave(Condition.exactText("Заявитель"));
        columnType.shouldHave(Condition.exactText("Тип"));
        columnTime.shouldHave(Condition.exactText("Время"));
        columnStatus.shouldHave(Condition.exactText("Статус"));
        columnAction.shouldHave(Condition.exactText("Действие"));
    }

}
