package lc.forward.autotests.source.pageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DetailInfoPageAndroid implements DetailInfoPage {

    public AppiumDriver<MobileElement> driver;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/buyButton")
    AndroidElement btnBuy;

    @AndroidFindBy(className = "android.widget.ImageButton")
    AndroidElement btnBack;

    public DetailInfoPageAndroid(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public DetailInfoPage clickBtnBuy() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(btnBuy));
        btnBuy.click();
        return this;
    }

    public DetailInfoPage clickBackButton() {
        new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(btnBuy));
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(btnBack));
        btnBack.click();
        return this;
    }
}
