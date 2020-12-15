package lc.forward.autotests.source.pageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lc.forward.autotests.source.utils.Action;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ReviewPageAndroid implements ReviewPage {

    public AppiumDriver<MobileElement> driver;
    Action action = new Action();

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/avatarImage")
    AndroidElement avatar;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/surnameEditText")
    AndroidElement surname;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/nameEditText")
    AndroidElement name;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/patronymicEditText")
    AndroidElement middleName;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/birthDateEditText")
    AndroidElement birthday;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/birthPlaceEditText")
    AndroidElement birthPlace;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/passportEditText")
    AndroidElement passport;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/passportDateEditText")
    AndroidElement passportDate;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/passportIssuedEditText")
    AndroidElement passportIssue;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/departmentCodeEditText")
    AndroidElement passportDepartmentCode;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/registrationAddressEditText")
    AndroidElement adress;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/occupationEditText")
    AndroidElement workingType;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/setFirstOccupationButton")
    AndroidElement workingTypeButton;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/setAddressButton")
    AndroidElement adressButton;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/averageSalaryEditText")
    AndroidElement salary;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/doneButton")
    AndroidElement btnDone;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/registrationAddressFlatEditText")
    AndroidElement flat;


    public ReviewPageAndroid(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private void checkSurname(String surnameText) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(avatar));
        Assert.assertEquals(surname.getText(), surnameText.toUpperCase());
    }

    private void checkName(String nameText) {
        Assert.assertEquals(name.getText(), nameText.toUpperCase());
    }

    private void checkMiddleName(String middleNameText) {
        Assert.assertEquals(middleName.getText(), middleNameText.toUpperCase());
    }

    private void checkBitrhday(String birthdayText) {
        Assert.assertEquals(birthday.getText(), birthdayText);
    }

    private void checkBirthPlace(String birthPlaceText) {
        action.swipe(driver);
        Assert.assertEquals(birthPlace.getText(), birthPlaceText.toUpperCase());
    }

    private void checkPassport() {
        Assert.assertNotNull(passport.getText());
    }

    private void checkPassportIssue(String passportIssueText) {
        Assert.assertEquals(passportIssue.getText(), passportIssueText.toUpperCase());
    }

    private void checkPassportDepCode(String passportDepCodeText) {
        action.swipe(driver);
        Assert.assertEquals(passportDepartmentCode.getText(), passportDepCodeText);
    }

    private void checkPassportDate(String passportDateText) {
        Assert.assertEquals(passportDate.getText(), passportDateText);
    }

    private void enterAdress() {
        adressButton.click();
    }

    private void enterWorkingType() {
        workingTypeButton.click();
    }

    private void enterSalary(String text) {
        salary.setValue(text);
    }

    public ReviewPage checkFields(String surname, String name, String middleName, String birthday, String birthPlace,
                            String passportDate, String passportIssue, String passportDepCode) {
        checkSurname(surname);
        checkName(name);
        checkMiddleName(middleName);
        checkBitrhday(birthday);
        checkBirthPlace(birthPlace);
        checkPassport();
        checkPassportIssue(passportIssue);
        checkPassportDate(passportDate);
        checkPassportDepCode(passportDepCode);
        return this;
    }

    public ReviewPage enterFields(Integer salaryText) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(avatar));
        enterAdress();
        enterWorkingType();
        action.swipe(driver);
        action.swipe(driver);
        action.swipe(driver);
        enterSalary(salaryText.toString());
        return this;
    }

    public ReviewPage clickButtonDone() {
        btnDone.click();
        return this;
    }
}
