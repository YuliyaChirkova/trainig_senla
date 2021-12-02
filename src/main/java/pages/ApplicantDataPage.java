package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.User;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ApplicantDataPage {

    public SelenideElement applicantLastName =$(byXpath("//label[text()='Фамилия']/following::input[1]"));
    public SelenideElement applicantFirstName =$(byXpath("//label[text()='Имя']/following::input[1]"));
    public SelenideElement applicantMiddleName =$(byXpath("//label[text()='Отчество']/following::input[1]"));
    public SelenideElement applicantPhoneNumber =$(byXpath("//label[text()='Телефон']/following::input[1]"));
    public SelenideElement applicantPassportNumber =$(byXpath("//label[text()='Номер паспорта']/following::input[1]"));
    public SelenideElement nextButton =$(byXpath("//button[text()='Далее']"));
    public SelenideElement userMessage =$(byXpath("//span[text()='Вы вошли как пользователь']"));
    ServiceOptionPage serviceOptionPage = new ServiceOptionPage();

   @Step("Проверить наличие всех полей формы Данные заявителя")
   public void checkFieldsAtApplicantDataPage(){
       applicantLastName.should(Condition.exist);
       applicantFirstName.should(Condition.exist);
       applicantMiddleName.should(Condition.exist);
       applicantPhoneNumber.should(Condition.exist);
       applicantPassportNumber.should(Condition.exist);
   }

    @Step("Заполнить все поля формы Данные заявителя")
    public ApplicantDataPage setAllApplicantData(User user){
        applicantLastName.val(user.getApplicantLastName());
        applicantFirstName.val(user.getApplicantFirstName());
        applicantMiddleName.val(user.getApplicantMiddleName());
        applicantPhoneNumber.val(user.getUserPhoneNumber());
        applicantPassportNumber.val(user.getApplicantPassportNumber());
        return this;
    }
    @Step("Нажать кнопку Далее")
    public ServiceOptionPage clickNextButton(){
        nextButton.shouldBe(Condition.enabled).click();
        return serviceOptionPage;
    }
}
