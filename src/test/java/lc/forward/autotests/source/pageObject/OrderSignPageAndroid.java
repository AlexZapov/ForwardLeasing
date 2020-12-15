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

public class OrderSignPageAndroid implements OrderSignPage {

    public AppiumDriver<MobileElement> driver;
    Action action = new Action();

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/titleToolbar")
    AndroidElement title;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/imageView")
    AndroidElement orderImage;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/infoImage")
    List<AndroidElement> infoImage;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/consentCheckbox")
    List<AndroidElement> consentCheckbox;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/signContractButton")
    List<AndroidElement> btnSign;

    public OrderSignPageAndroid(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public OrderSignPage checkOrderInfo() {
        new WebDriverWait(driver, 60).until(ExpectedConditions.visibilityOf(orderImage));
        Assert.assertTrue(title.getText().equals("Подписание договора"),
                "Actual: " + title.getText() + ", Expected: Подписание договора");
        while(infoImage.size() < 7) action.swipe(driver);
        while (consentCheckbox.size() < 1) { action.swipe(driver); }
        consentCheckbox.get(0).click();
        return this;
    }

    public OrderSignPage clickSignButtin() {
        while (btnSign.size() < 1) action.swipe(driver);
        btnSign.get(0).click();
        return this;
    }
}
