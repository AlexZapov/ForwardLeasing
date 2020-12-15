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

public class DonePageAndroid implements DonePage {

    public AppiumDriver<MobileElement> driver;
    Action action = new Action();

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/titleText")
    AndroidElement titleText;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/descriptionText")
    AndroidElement description;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/doneButton")
    List<AndroidElement> btnDone;

    public DonePageAndroid(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public DonePage confirmOrder() {
        new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOf(titleText));
        Assert.assertTrue(titleText.getText().matches("Договор № \\d+ и доставка оформлены!"),
                "Actual: " + titleText.getText() + ", Expected: Договор № \\d+ и доставка оформлены!");
        Assert.assertTrue(description.getText().equals("Мы свяжемся с вами для подтверждения адреса и даты доставки. Контролируйте статус в своём профиле. Если остались вопросы – позвоните нам 8-800-770-75-75."),
                "Actual: " + description.getText() + ", Expected: Мы свяжемся с вами для подтверждения адреса и даты доставки. Контролируйте статус в своём профиле. Если остались вопросы – позвоните нам 8-800-770-75-75.");
        return this;
    }

    public DonePage clickDoneButton() {
        while (btnDone.size() < 1) action.swipe(driver);
        btnDone.get(0).click();
        return this;
    }
}
