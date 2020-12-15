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

import java.util.List;

public class CurrentContractAndroid implements CurrentContract {

    public AppiumDriver<MobileElement> driver;
    Action action = new Action();

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/ordersRecycler")
    AndroidElement recyclerTitle;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/orderContinueButton")
    List<AndroidElement> btnContinue;

    public CurrentContractAndroid(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void clickContinueButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(recyclerTitle));
        while (btnContinue.size() == 1) action.swipe(driver);
        btnContinue.get(1).click();
    }
}
