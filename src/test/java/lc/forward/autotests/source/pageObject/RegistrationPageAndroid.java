package lc.forward.autotests.source.pageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPageAndroid implements RegistrationPage {

    public AppiumDriver<MobileElement> driver;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Регистрация']")
    AndroidElement registerText;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/phoneEditText")
    AndroidElement editPhone;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/mailEditText")
    AndroidElement editMail;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/consentCheckbox")
    AndroidElement consentCheckbox;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/nextButton")
    AndroidElement btnNext;

    public RegistrationPageAndroid(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private void enterPhoneNumber(String phone) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(registerText));
        editPhone.click();
        editPhone.sendKeys(phone);
        driver.hideKeyboard();
    }

    private void enterMailAdress(String mail) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(registerText));
        editMail.click();
        editMail.sendKeys(mail);
        driver.hideKeyboard();
    }

    private void clickConsentCheckbox() {
        consentCheckbox.click();
    }

    public void fillForm(String phone, String mail) {
        enterPhoneNumber(phone);
        enterMailAdress(mail);
        clickConsentCheckbox();
    }

    public void clickNextButton() {
        Actions action = new Actions(driver);
        action.moveToElement(btnNext).build().perform();
        btnNext.click();
    }
}
