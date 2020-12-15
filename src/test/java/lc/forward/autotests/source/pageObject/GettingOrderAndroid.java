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

import java.util.List;

public class GettingOrderAndroid implements GettingOrder {

    public AppiumDriver<MobileElement> driver;
    Action action = new Action();

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/switchView")
    AndroidElement switcher;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/leftText")
    AndroidElement deliveryButton;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/rightText")
    AndroidElement selfDeliveryButton;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/titleText")
    AndroidElement titleText;

    @AndroidFindBy(className = "android.widget.Button")
    List<AndroidElement> btn;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/continueButton")
    AndroidElement btnContinue;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/chooseStoreButton")
    AndroidElement btnChooseStore;

    public GettingOrderAndroid(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public GettingOrder clickDeliveryButton() {
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(switcher));
        deliveryButton.click();
        return this;
    }

    public GettingOrder clickSelfDeliveryButton() {
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(switcher));
        selfDeliveryButton.click();
        return this;
    }

    public GettingOrder checkDeliveryAdress() {
        new WebDriverWait(driver, 25).until(ExpectedConditions.visibilityOf(titleText));
        Assert.assertTrue(titleText.getText().equals("Оформление доставки"),
                "Actual: " + titleText.getText() + ", Expected: Оформление доставки");
        return this;
    }

    public GettingOrder clickContinueButton() {
        btnContinue.click();
        return this;
    }

    public GettingOrder clickChooseStoreButton() {
        while (btn.size() != 2) action.swipe(driver);
        btnChooseStore.click();
        return this;
    }
}
