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

public class ConfirmOrderPageAndroid implements ConfirmOrderPage {

    public AppiumDriver<MobileElement> driver;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/titleText")
    AndroidElement titleText;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/doneButton")
    AndroidElement btnDone;

    @AndroidFindBy(className = "android.widget.TextView")
    AndroidElement text;

    public ConfirmOrderPageAndroid(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public ConfirmOrderPage confirmDelivery() {
        new WebDriverWait(driver, 15).until(ExpectedConditions.textToBePresentInElement(text, "Оплата успешно зарегистрирована!"));
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(titleText));
        Assert.assertTrue(titleText.getText().equals("Подпишите соглашение о передаче товара"));
        return this;
    }

    public ConfirmOrderPage clickDoneButton() {
        btnDone.click();
        return this;
    }
}
