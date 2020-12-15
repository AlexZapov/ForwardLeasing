package lc.forward.autotests.appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import lc.forward.autotests.source.utils.Property;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static lc.forward.autotests.appium.AppiumController.executionOS;

public class Capabilities {

    public AppiumDriver driver;

    public AppiumDriver setCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        String completeURL = "http://" + Property.readProperty("run.ip") + ":" + Property.readProperty("run.port") + "/wd/hub";
        switch(executionOS){
            case ANDROID:
                capabilities.setCapability("platformName", "Android");
                capabilities.setCapability("deviceName", "Android Device");
                capabilities.setCapability("app", Property.readProperty("app.android.path"));
                capabilities.setCapability("newCommandTimeout", "120000");
                try{
                    return new AndroidDriver<AndroidElement>(new URL(completeURL), capabilities);
                }
                catch (MalformedURLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            case IOS:
                capabilities.setCapability("platformName", "iOS");
                capabilities.setCapability("platformVersion", "14.0");
                capabilities.setCapability("xcodeOrgId", "iOS");
                capabilities.setCapability("xcodeSigningId", "iPhone Developer");
                capabilities.setCapability("useNewWDA", false);
                capabilities.setCapability("deviceName", "iPhone 8 (Александр)");
                capabilities.setCapability("app", "https://ota.kode.ru/2052574/ForwardLeasing.0.0.1.22.dev.ipa");
                capabilities.setCapability("udid", "f41232302663e9070c470b5b84cb6a013925582e");
                capabilities.setCapability("automationName", "XCUITest");
                capabilities.setCapability("xcodeConfigFile", "/Users/azapov/node_modules/appium-webdriveragent/file.xcconfig");
                try {
                    return new IOSDriver<IOSElement>(new URL(completeURL), capabilities);
                }
                catch (MalformedURLException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
        }
        return driver;
    }
}
