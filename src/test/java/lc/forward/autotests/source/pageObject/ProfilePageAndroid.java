package lc.forward.autotests.source.pageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ProfilePageAndroid implements ProfilePage {

    public AppiumDriver<MobileElement> driver;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/titleToolbar")
    AndroidElement titleToolbar;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/phoneEditText")
    AndroidElement editPhone;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/pinCodeEditText")
    AndroidElement enterPIN;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/signInButton")
    AndroidElement btnSignIn;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/registrationText")
    AndroidElement registrationButton;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/profileWelcomeText")
    AndroidElement welcomeText;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/topButton")
    AndroidElement btnConfirmOrder;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/bottomButton")
    AndroidElement btnCancelOrder;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/statusTextView")
    AndroidElement statusText;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/progressRatingBar")
    AndroidElement progressBar;

    public ProfilePageAndroid(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private void enterPhoneNumber(String phone) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(editPhone));
        editPhone.click();
        editPhone.sendKeys(phone);
        driver.hideKeyboard();
    }

    private void enterPIN(String PIN) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(enterPIN));
        enterPIN.click();
        enterPIN.sendKeys(PIN);
        driver.hideKeyboard();
    }

    public ProfilePageAndroid fillLoginFields(String phone, String PIN) {
        enterPhoneNumber(phone);
        enterPIN(PIN);
        return this;
    }

    public ProfilePageAndroid registrationButtonClick() {
        registrationButton.click();
        return this;
    }

    public ProfilePageAndroid signInButtonClick() {
        btnSignIn.click();
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(welcomeText));
        Assert.assertTrue(welcomeText.getText().contains(", c возвращением"),
                "Actual: " + welcomeText.getText() + ", Expected: %Username%, с возвращением");
        return this;
    }

    public ProfilePageAndroid clickConfirmButton() {
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(btnConfirmOrder));
        btnConfirmOrder.click();
        return this;
    }

    public ProfilePageAndroid checkDeliveryOrder(String status) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(welcomeText));
        Assert.assertTrue(welcomeText.getText().contains(", c возвращением"),
                "Actual: " + welcomeText.getText() + ", Expected: %Username%, с возвращением");
        Assert.assertTrue(statusText.getText().equals(status),
                "Actual: " + statusText.getText() + ", Expected: " + status);
        Assert.assertTrue(progressBar.isDisplayed(), "Actual: " + progressBar.isDisplayed() + ", Expected: true");
        return this;
    }
}
