package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.User;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class CitizenDataPage {

    public SelenideElement message =$(byXpath("//span[text() = 'Регистрация брака']"));
    public SelenideElement citizenLastName =$(byXpath("//input [@id='TextInputField-6']"));
    public SelenideElement citizenFirstName =$(byXpath("//input [@id='TextInputField-7']"));
    public SelenideElement citizenMiddleName =$(byXpath("//input [@id='TextInputField-8']"));
    public SelenideElement citizenBirthDate =$(byXpath("//input [@id='TextInputField-9']"));
    public SelenideElement citizenPassportNumber =$(byXpath("//input [@id='TextInputField-10']"));
    public SelenideElement citizenGender =$(byXpath("//input [@id='TextInputField-11']"));
    public SelenideElement nextButton =$(byXpath("//button [text()='Далее']"));

    @Step("Заполнить все поля формы Данные гражданина")
    public void setAllCitizenData(User user){
        citizenLastName.val(user.getCitizenLastName());
        citizenFirstName.val(user.getCitizenFirstName());
        citizenMiddleName.val(user.getCitizenMiddleName());
        citizenBirthDate.val(user.getCitizenBirthDate());
        citizenPassportNumber.val(user.getCitizenPassportNumber());
        citizenGender.val(user.getCitizenGender());
    }
    @Step("Нажать кнопку Далее")
    public void clickNextButton(){
        nextButton.shouldBe(Condition.enabled).click();
    }
}
