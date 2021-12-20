package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.User;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class CitizenDataPage {

    private SelenideElement message =$(byXpath("//span[text() = 'Регистрация брака']"));
    private SelenideElement citizenLastName =$(byXpath("//label[text()='Фамилия']/following::input[1]"));
    private SelenideElement citizenFirstName =$(byXpath("//label[text()='Имя']/following::input[1]"));
    private SelenideElement citizenMiddleName =$(byXpath("//label[text()='Отчество']/following::input[1]"));
    private SelenideElement citizenBirthDate =$(byXpath("//label[text()='Дата рождения']/following::input[1]"));
    private SelenideElement citizenPassportNumber =$(byXpath("//label[text()='Номер паспорта']/following::input[1]"));
    private SelenideElement citizenGender =$(byXpath("//label[text()='Пол']/following::input[1]"));
    private SelenideElement nextButton =$(byXpath("//button [text()='Далее']"));

    public SelenideElement getMessage() {
        return message;
    }

    public SelenideElement getCitizenLastName() {
        return citizenLastName;
    }

    public SelenideElement getCitizenFirstName() {
        return citizenFirstName;
    }

    public SelenideElement getCitizenMiddleName() {
        return citizenMiddleName;
    }

    public SelenideElement getCitizenBirthDate() {
        return citizenBirthDate;
    }

    public SelenideElement getCitizenPassportNumber() {
        return citizenPassportNumber;
    }

    public SelenideElement getCitizenGender() {
        return citizenGender;
    }

    public SelenideElement getNextButton() {
        return nextButton;
    }

    @Step("Заполнить все поля формы Данные гражданина")
    public void setAllCitizenData(User user){
        citizenLastName.val(user.getCitizenLastName());
        citizenFirstName.val(user.getCitizenFirstName());
        citizenMiddleName.val(user.getCitizenMiddleName());
        citizenBirthDate.val(user.getCitizenBirthDate());
        citizenPassportNumber.val(user.getCitizenNumberOfPassport());
        citizenGender.val(user.getCitizenGender());
    }

    @Step("Нажать кнопку Далее")
    public void clickNextButton(){
        nextButton.shouldBe(Condition.enabled).click();
    }


}
