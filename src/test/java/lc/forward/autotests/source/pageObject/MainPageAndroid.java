package lc.forward.autotests.source.pageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPageAndroid implements MainPage {

    public AppiumDriver<MobileElement> driver;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Каталог']")
    AndroidElement catalogPageName;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/productImage")
    AndroidElement catalogImage;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/menuItemProfile")
    AndroidElement btnProfile;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/menuItemCatalog")
    AndroidElement btnCatalog;

    public MainPageAndroid(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public MainPage clickCatalogImage() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(catalogPageName));
        catalogImage.click();
        return this;
    }

    public MainPage clickProfileButton() {
        btnProfile.click();
        return this;
    }

    public MainPage clickCatalogButton() {
        btnCatalog.click();
        return this;
    }
}
