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

public class PassportPageAndroid implements PassportPage {

    public AppiumDriver<MobileElement> driver;

    @AndroidFindBy(className = "android.widget.ImageButton")
    AndroidElement btnBack;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/titleText")
    AndroidElement titleText;

    public PassportPageAndroid(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public PassportPage checkPassportPage() {
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(titleText));
        Assert.assertTrue(titleText.getText().equals("Сначала отсканируйте паспорт"),
                "Actual: " + titleText.getText() + ", Expected: Сначала отсканируйте паспорт");
        return this;
    }

    public PassportPage clickBackButton() {
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(btnBack));
        btnBack.click();
        return this;
    }
}
