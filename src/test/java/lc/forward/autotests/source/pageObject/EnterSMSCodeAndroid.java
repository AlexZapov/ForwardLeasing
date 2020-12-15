package lc.forward.autotests.source.pageObject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class EnterSMSCodeAndroid implements EnterSMSCode {

    public AppiumDriver<MobileElement> driver;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/pinCodeView1")
    AndroidElement pinCode1;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/pinCodeView2")
    AndroidElement pinCode2;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/pinCodeView3")
    AndroidElement pinCode3;

    @AndroidFindBy(id = "lc.forward.app.kode.dev:id/pinCodeView4")
    AndroidElement pinCode4;

    public EnterSMSCodeAndroid(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public EnterSMSCode enterSMS(String s1, String s2, String s3, String s4) {
        pinCode1.sendKeys(s1);
        pinCode2.sendKeys(s2);
        pinCode3.sendKeys(s3);
        pinCode4.sendKeys(s4);
        return this;
    }
}
