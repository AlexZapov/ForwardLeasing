package lc.forward.autotests.source.pageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lc.forward.autotests.source.utils.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ClientDetailPageAndroid implements ClientDetailPage {

    public AppiumDriver<MobileElement> driver;
    Action action = new Action();

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/phoneEditText")
    AndroidElement editPhone;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/mailEditText")
    AndroidElement editMail;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/digitalSignatureCheckbox")
    AndroidElement digitalSignatureCheckbox;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/consentCheckbox")
    AndroidElement consentCheckbox;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/continueButton")
    AndroidElement btnContinue;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/nextButton")
    AndroidElement btnNext;

    public ClientDetailPageAndroid(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private void enterPhoneNumber(String phone) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(editPhone));
        editPhone.click();
        editPhone.sendKeys(phone);
        driver.hideKeyboard();
    }

    private void enterMailAdress(String mail) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(editMail));
        editMail.click();
        editMail.sendKeys(mail);
        driver.hideKeyboard();
    }

    private void clickDigitalSignatureCheckbox() {
        digitalSignatureCheckbox.click();
    }

    private void clickConsentCheckbox() {
        consentCheckbox.click();
    }

    public ClientDetailPage fillAndCheckForm(String phone, String mail) {
        enterPhoneNumber(phone);
        enterMailAdress(mail);
        clickDigitalSignatureCheckbox();
        clickConsentCheckbox();
        return this;
    }

    public ClientDetailPage clickContinueButton() {
        action.moveToElement(driver, btnContinue);
        btnContinue.click();
        return this;
    }

    public ClientDetailPage clickNextButton() {
        action.moveToElement(driver, btnNext);
        btnNext.click();
        return this;
    }
}
