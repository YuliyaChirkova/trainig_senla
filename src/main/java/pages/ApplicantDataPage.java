package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.User;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;


public class ApplicantDataPage {

    private SelenideElement applicantLastName = $(byXpath("//label[text()='Фамилия']/following::input[1]"));
    private SelenideElement applicantFirstName = $(byXpath("//label[text()='Имя']/following::input[1]"));
    private SelenideElement applicantMiddleName = $(byXpath("//label[text()='Отчество']/following::input[1]"));
    private SelenideElement applicantPhoneNumber = $(byXpath("//label[text()='Телефон']/following::input[1]"));
    private SelenideElement applicantPassportNumber = $(byXpath("//label[text()='Номер паспорта']/following::input[1]"));
    private SelenideElement nextButton = $(byXpath("//button[text()='Далее']"));
    private SelenideElement userMessage = $(byXpath("//span[text()='Вы вошли как пользователь']"));
    ServiceOptionPage serviceOptionPage = new ServiceOptionPage();

    public SelenideElement getApplicantLastName() {
        return applicantLastName;
    }

    public SelenideElement getApplicantFirstName() {
        return applicantFirstName;
    }

    public SelenideElement getApplicantMiddleName() {
        return applicantMiddleName;
    }

    public SelenideElement getApplicantPhoneNumber() {
        return applicantPhoneNumber;
    }

    public SelenideElement getApplicantPassportNumber() {
        return applicantPassportNumber;
    }

    public SelenideElement getNextButton() {
        return nextButton;
    }

    public SelenideElement getUserMessage() {
        return userMessage;
    }


    @Step("Заполнить все поля формы Данные заявителя")
    public ApplicantDataPage setAllApplicantData(User user) {
        applicantLastName.val(user.getPersonalLastName());
        applicantFirstName.val(user.getPersonalFirstName());
        applicantMiddleName.val(user.getPersonalMiddleName());
        applicantPhoneNumber.val(user.getPersonalPhoneNumber());
        applicantPassportNumber.val(user.getPersonalNumberOfPassport());
        return this;
    }

    @Step("Нажать кнопку Далее")
    public ServiceOptionPage clickNextButton() {
        nextButton.shouldBe(Condition.enabled).click();
        return serviceOptionPage;
    }

    @Step("Проверить, что на странице содержится текст: Вы вошли как пользователь")
    public void checkMessageText() {
        userMessage.shouldHave(Condition.exactText("Вы вошли как пользователь"));
    }
}
