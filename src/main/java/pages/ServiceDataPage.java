package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.User;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class ServiceDataPage {
    public SelenideElement marriageDataRegistration =$(byXpath("//input [@id='TextInputField-12']"));
    public SelenideElement spouseNewLastName =$(byXpath("//input [@id='TextInputField-13']"));
    public SelenideElement spouseLastName =$(byXpath("//input [@id='TextInputField-14']"));
    public SelenideElement spouseFirstName =$(byXpath("//input [@id='TextInputField-15']"));
    public SelenideElement spouseMiddleName =$(byXpath("//input [@id='TextInputField-16']"));
    public SelenideElement spouseBirthDate =$(byXpath("//input [@id='TextInputField-17']"));
    public SelenideElement spousePassportNumber =$(byXpath("//input [@id='TextInputField-18']"));
    public SelenideElement finishButton =$(byXpath("//button [text()='Завершить']"));
    ApplicationStatusPage applicationStatusPage = new ApplicationStatusPage();

    @Step("Заполнить все поля формы Данные гражданина")
    public void setAllServiceData(User user){
        marriageDataRegistration.val(user.getMarriageRegistrationDate());
        spouseNewLastName.val(user.getSpouseNewLastName());
        spouseLastName.val(user.getSpouseLastName());
        spouseFirstName.val(user.getSpouseFirstName());
        spouseMiddleName.val(user.getSpouseMiddleName());
        spouseBirthDate.val(user.getSpouseBirthDate());
        spousePassportNumber.val(user.getSpousePassportNumber());
    }
    /*@Step("Нажать кнопку Завершить")
    public void clickFinishButton(){
        finishButton.shouldBe(Condition.enabled).click();
    }*/

    @Step("Нажать кнопку Завершить")
    public ApplicationStatusPage clickFinishButton(){
        finishButton.shouldBe(Condition.enabled).click();
        return applicationStatusPage;
    }
}
