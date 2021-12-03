package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.Administrator;
import data.User;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class AdminDataPage {

    private SelenideElement adminLastName =$(byXpath("//label[text()='Фамилия']/following::input[1]"));
    private SelenideElement adminFirstName =$(byXpath("//label[text()='Имя']/following::input[1]"));
    private SelenideElement adminMiddleName =$(byXpath("//label[text()='Отчество']/following::input[1]"));
    private SelenideElement adminPhoneNumber =$(byXpath("//label[text()='Телефон']/following::input[1]"));
    private SelenideElement adminPassportNumber =$(byXpath("//label[text()='Номер паспорта']/following::input[1]"));
    private SelenideElement adminBirthDate =$(byXpath("//label[text()='Дата рождения']/following::input[1]"));
    private SelenideElement nextButton =$(byXpath("//button[text()='Далее']"));
    private SelenideElement adminMessage =$(byXpath("//span[text()='Вы вошли как администратор']"));
    ServiceOptionPage serviceOptionPage = new ServiceOptionPage();

    @Step("Проверить наличие всех полей формы Данные регистрации")
    public void checkFieldsAtApplicantDataPage(){
        adminLastName.should(Condition.exist);
        adminFirstName.should(Condition.exist);
        adminMiddleName.should(Condition.exist);
        adminPhoneNumber.should(Condition.exist);
        adminPassportNumber.should(Condition.exist);
        adminBirthDate.should(Condition.exist);
    }

    @Step("Заполнить все поля формы Данные регистрации")
    public void setAllAdminData(Administrator administrator){
        adminLastName.val(administrator.getAdminLastName());
        adminFirstName.val(administrator.getAdminFirstName());
        adminMiddleName.val(administrator.getAdminMiddleName());
        adminPhoneNumber.val(administrator.getAdminPhoneNumber());
        adminPassportNumber.val(administrator.getAdminPassportNumber());
        adminBirthDate.val(administrator.getAdminBirthDate());
    }

    @Step("Нажать кнопку Далее")
    public ServiceOptionPage clickNextButton(){
        nextButton.shouldBe(Condition.enabled).click();
        return serviceOptionPage;
    }

    @Step("Проверить, что кнопка Далее кликабельна")
    public void checkNextButtonIsEnabled(){
        nextButton.shouldBe(Condition.enabled);
    }

    @Step("Проверить, что на странице содержится текст: Вы вошли как администратор")
    public void checkMessageText(){
        adminMessage.shouldHave(Condition.exactText("Вы вошли как администратор"));
    }
}
