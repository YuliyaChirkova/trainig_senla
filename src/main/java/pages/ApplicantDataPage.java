package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.User;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ApplicantDataPage {
    public SelenideElement applicantLastName =$(byCssSelector("#TextInputField-1"));
    public SelenideElement applicantFirstName =$(byCssSelector("#TextInputField-2"));
    public SelenideElement applicantMiddleName =$(byCssSelector("#TextInputField-3"));
    public SelenideElement applicantPhoneNumber =$(byCssSelector("#TextInputField-4"));
    public SelenideElement applicantPassportNumber =$(byCssSelector("#TextInputField-5"));
    public SelenideElement nextButton =$(byText("Далее"));


    @Step("Заполнить все поля формы Данные заявителя")
    public void setAllApplicantData(User user){
        applicantLastName.val(user.getApplicantLastName());
        applicantFirstName.val(user.getApplicantFirstName());
        applicantMiddleName.val(user.getApplicantMiddleName());
        applicantPhoneNumber.val(user.getUserPhoneNumber());
        applicantPassportNumber.val(user.getApplicantPassportNumber());
    }
    @Step("Нажать кнопку Далее")
    public void clickNextButton(){
        nextButton.shouldBe(Condition.enabled).click();
    }
}
