package lc.forward.autotests.source.pageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OnboardingPageAndroid implements OnboardingPage {

    public AppiumDriver<MobileElement> driver;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/firstContinueButton")
    AndroidElement btnFirstContinue;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Далее']")
    AndroidElement btnSecondContinue;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='Поехали']")
    AndroidElement btnThirdContinue;

    public OnboardingPageAndroid(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public OnboardingPage clickFirstContinueButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(btnFirstContinue));
        btnFirstContinue.click();
        return this;
    }

    public OnboardingPage clickSecondContinueButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(btnSecondContinue));
        btnSecondContinue.click();
        return this;
    }

    public OnboardingPage clickThirdContinueButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(btnThirdContinue));
        btnThirdContinue.click();
        return this;
    }
}
