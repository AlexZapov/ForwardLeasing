package lc.forward.autotests.appium;

import io.appium.java_client.AppiumDriver;
import java.util.concurrent.TimeUnit;

public class AppiumController {

    public static OS executionOS = OS.ANDROID;

    public enum OS {
        ANDROID,
        IOS,
    }

    public static AppiumController instance = new AppiumController();
    public AppiumDriver driver;

    public void start() {
        if (driver != null) {
            return;
        }

        driver = new Capabilities().setCapabilities();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public void stop() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
