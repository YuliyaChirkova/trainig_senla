package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.User;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class ServiceDataPage {
    private SelenideElement marriageDataRegistration =$(byXpath("//label[text()='Дата регистрации']/following::input[1]"));
    private SelenideElement spouseNewLastName =$(byXpath("//label[text()='Новая фамилия']/following::input[1]"));
    private SelenideElement spouseLastName =$(byXpath("//label[text()='Фамилия супруга/и']/following::input[1]"));
    private SelenideElement spouseFirstName =$(byXpath("//label[text()='Имя супруга/и']/following::input[1]"));
    private SelenideElement spouseMiddleName =$(byXpath("//label[text()='Отчество супруга/и']/following::input[1]"));
    private SelenideElement spouseBirthDate =$(byXpath("//label[text()='Дата рождения супруга/и']/following::input[1]"));
    private SelenideElement spousePassportNumber =$(byXpath("//label[text()='Номер паспорта супруга/и']/following::input[1]"));
    private SelenideElement finishButton =$(byXpath("//button [text()='Завершить']"));
    private SelenideElement birthPlace =$(byXpath("//label[text()='Место рождения']/following::input[1]"));
    private SelenideElement motherName =$(byXpath("//label[text()='Мать']/following::input[1]"));
    private SelenideElement fatherName =$(byXpath("//label[text()='Отец']/following::input[1]"));
    private SelenideElement deathPlace =$(byXpath("//label[text()='Место смерти']/following::input[1]"));
    private SelenideElement deathDate =$(byXpath("//label[text()='Дата смерти']/following::input[1]"));
    ApplicationStatusPage applicationStatusPage = new ApplicationStatusPage();

    @Step("Проверить наличие всех полей в форме Данные гражданина / регистрация брака")
    public void checkFieldsAtServiceDataPageMarriage(){
        marriageDataRegistration.should(Condition.exist);
        spouseNewLastName.should(Condition.exist);
        spouseLastName.should(Condition.exist);
        spouseFirstName.should(Condition.exist);
        spouseMiddleName.should(Condition.exist);
        spouseBirthDate.should(Condition.exist);
        spousePassportNumber.should(Condition.exist);
    }

    @Step("Проверить наличие всех полей в форме Данные гражданина / регистрация рождения")
    public void checkFieldsAtServiceDataPageBirth(){
        birthPlace.should(Condition.exist);
        motherName.should(Condition.exist);
        fatherName.should(Condition.exist);
    }

    @Step("Проверить наличие всех полей в форме Данные гражданина / регистрация смерти")
    public void checkFieldsAtServiceDataPageDeath(){
        deathPlace.should(Condition.exist);
        deathDate.should(Condition.exist);
    }
    @Step("Заполнить все поля формы Данные гражданина / регистрация брака")
    public void setAllMarriageServiceData(User user){
        marriageDataRegistration.val(user.getMarriageRegistrationDate());
        spouseNewLastName.val(user.getSpouseNewLastName());
        spouseLastName.val(user.getSpouseLastName());
        spouseFirstName.val(user.getSpouseFirstName());
        spouseMiddleName.val(user.getSpouseMiddleName());
        spouseBirthDate.val(user.getSpouseBirthDate());
        spousePassportNumber.val(user.getSpousePassportNumber());
    }

    @Step("Заполнить все поля формы Данные гражданина / регистрация рождения")
    public void setAllBirthServiceData(User user){
        birthPlace.val(user.getBirthPlace());
        motherName.val(user.getMotherName());
        fatherName.val(user.getFatherName());
    }

    @Step("Заполнить все поля формы Данные гражданина / регистрация смерти")
    public void setAllDeathServiceData(User user){
        deathPlace.val(user.getDeathPlace());
        deathDate.val(user.getDeathDate());

    }

    @Step("Нажать кнопку Завершить")
    public ApplicationStatusPage clickFinishButton(){
        finishButton.shouldBe(Condition.enabled).click();
        return applicationStatusPage;
    }

    @Step("Проверить, что кнопка Завершить кликабельна")
    public void checkFinishButtonIsEnabled(){
        finishButton.shouldBe(Condition.enabled);
    }
}
