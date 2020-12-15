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

public class OrderEndPageAndroid implements OrderEndPage {

    public AppiumDriver<MobileElement> driver;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/orderApprovedText")
    AndroidElement orderApprovedText;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/continueButton")
    AndroidElement continueButton;

    public OrderEndPageAndroid(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public OrderEndPage checkFrameText() {
        new WebDriverWait(driver, 150).until(ExpectedConditions.visibilityOf(orderApprovedText));
        Assert.assertTrue(orderApprovedText.getText().equals("Заявка одобрена!"),
                "Actual: " + orderApprovedText.getText() + ", Expected: Заявка одобрена!");
        return this;
    }

    public OrderEndPage clickContinueButton() {
        continueButton.click();
        return this;
    }
}
