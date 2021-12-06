package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.Administrator;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class AdminDataPage {

    private SelenideElement adminLastName = $(byXpath("//label[text()='Фамилия']/following::input[1]"));
    private SelenideElement adminFirstName = $(byXpath("//label[text()='Имя']/following::input[1]"));
    private SelenideElement adminMiddleName = $(byXpath("//label[text()='Отчество']/following::input[1]"));
    private SelenideElement adminPhoneNumber = $(byXpath("//label[text()='Телефон']/following::input[1]"));
    private SelenideElement adminPassportNumber = $(byXpath("//label[text()='Номер паспорта']/following::input[1]"));
    private SelenideElement adminBirthDate = $(byXpath("//label[text()='Дата рождения']/following::input[1]"));
    private SelenideElement nextButton = $(byXpath("//button[text()='Далее']"));
    private SelenideElement adminMessage = $(byXpath("//span[text()='Вы вошли как администратор']"));
    ServiceOptionPage serviceOptionPage = new ServiceOptionPage();

    public SelenideElement getAdminLastName() {
        return adminLastName;
    }

    public SelenideElement getAdminFirstName() {
        return adminFirstName;
    }

    public SelenideElement getAdminMiddleName() {
        return adminMiddleName;
    }

    public SelenideElement getAdminPhoneNumber() {
        return adminPhoneNumber;
    }

    public SelenideElement getAdminPassportNumber() {
        return adminPassportNumber;
    }

    public SelenideElement getAdminBirthDate() {
        return adminBirthDate;
    }

    public SelenideElement getNextButton() {
        return nextButton;
    }

    public SelenideElement getAdminMessage() {
        return adminMessage;
    }

    @Step("Заполнить все поля формы Данные регистрации")
    public void setAllAdminData(Administrator administrator) {
        adminLastName.val(administrator.getAdminLastName());
        adminFirstName.val(administrator.getAdminFirstName());
        adminMiddleName.val(administrator.getAdminMiddleName());
        adminPhoneNumber.val(administrator.getAdminPhoneNumber());
        adminPassportNumber.val(administrator.getAdminPassportNumber());
        adminBirthDate.val(administrator.getAdminBirthDate());
    }

    @Step("Нажать кнопку Далее")
    public ServiceOptionPage clickNextButton() {
        nextButton.shouldBe(Condition.enabled).click();
        return serviceOptionPage;
    }
}
