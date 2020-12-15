package lc.forward.autotests.source.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class Action {

    public void swipe(AppiumDriver driver) {
        Dimension dimension = driver.manage().window().getSize();
        int startX = (int) (dimension.width * 0.5);
        int startY = (int) (dimension.height * 0.8);

        int endX = (int) (dimension.width * 0.5);
        int endY = (int) (dimension.height * 0.2);

        TouchAction touch = new TouchAction(driver);
        touch.press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(endX, endY)).release().perform();
    }

    public void moveToElement(AppiumDriver driver, WebElement element) {
        Actions action = new org.openqa.selenium.interactions.Actions(driver);
        action.moveToElement(element).build().perform();
    }
}
