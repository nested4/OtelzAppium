package com.otelz.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Actions {
    AppiumDriver<?> driver;
    WebDriverWait wait;

    public static Actions action = new Actions();

    private Actions() {
        driver = Driver.getDriver();
        wait = new WebDriverWait(driver, 10);
    }

    public void click(By locator){
        waitFor(locator, Conditions.clickable).click();
    }

    public void sendkeys(By locator, String text){
        waitFor(locator, Conditions.visibility).sendKeys(text);
    }

    public MobileElement waitFor(By locator, Conditions condition){
        switch (condition){
            case clickable -> {
                return (MobileElement) wait.until(ExpectedConditions.elementToBeClickable(locator));
            }
            case visibility -> {
                return (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            }
            case invisibility -> {
                 wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
                 return null;
            }
            default -> {
                return (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            }
        }
    }

    public MobileElement getElement(By locator){
        return waitFor(locator, Conditions.exist);
    }

    public boolean getAttribute(By locator, ElementStatus elementStatus){
        String status = elementStatus.toString().replace("_", "-");
        return Boolean.parseBoolean(waitFor(locator, Conditions.visibility).getAttribute(status));
    }

    public void swipeUntil(By locator, double startPoint, double endPoint){
        while (driver.findElements(locator).size() <= 0)
            swipeV(startPoint, endPoint);
    }

    public void swipeUntil(By locator, double boundry){
        double startPoint = boundry > .5 ? boundry : 1 - boundry;
        double endPoint = 1 - startPoint;
        swipeUntil(locator, startPoint, endPoint);
    }

    public void swipeUntil(By locator){
        swipeUntil(locator, .7, .3);
    }


    /**
     * ekranda swipe islemi yapar
     * @param startPoint yüzde olarak 0, 1 arasi double sayi
     * @param stopPoint  yüzde olarak 0, 1 arasi double
     */
    public void swipeV(double startPoint, double stopPoint){
        int w = driver.manage().window().getSize().width;
        int h = driver.manage().window().getSize().height;

        new TouchAction<>(driver)
                .press(PointOption.point(w/2, (int)(h*startPoint)))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(w/2, (int)(h*stopPoint)))
                .release()
                .perform();
    }

    /**
     * ekranda swipe islemi yapar
     * @param startPoint yüzde olarak 0, 1 arasi double sayi
     * @param stopPoint  yüzde olarak 0, 1 arasi double
     */
    public void swipeH(double startPoint, double stopPoint){
        int w = driver.manage().window().getSize().width;
        int h = driver.manage().window().getSize().height;

        new TouchAction<>(driver)
                .press(PointOption.point((int)(w*startPoint), h/2))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point((int)(w*stopPoint), h/2))
                .release()
                .perform();
    }
}
