package com.otelz.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class OtelzReservation {
    AppiumDriver<MobileElement> driver;
    WebDriverWait wait;

    @Test
    void reservation() throws MalformedURLException {

        //DesiredCapabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 5");
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.0");
        caps.setCapability(MobileCapabilityType.APP, "C:\\Users\\ozgei\\IdeaProjects\\OtelzAppium\\src\\test\\resources\\Otelz-dev.apk");

        //set URL for the appium server
        URL url = new URL("http://localhost:4723/wd/hub");

        //launch appiumDriver
        driver = new AndroidDriver<MobileElement>(url, caps);
        wait = new WebDriverWait(driver, 30);
        swipeH(0.7, 0.3);
        swipeH(0.7, 0.3);
        driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).click();
        swipeH(0.7, 0.3);
        driver.findElement(By.id("com.otelz.mobil:id/materialButtonContinue")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/tv_search_label"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/search_src_text"))).sendKeys("Ankara");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget." +
                "FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android." +
                "widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/tv_start_date"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout" +
                "/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout" +
                "/android.view.ViewGroup/android.widget.RelativeLayout[1]/androidx.recyclerview.widget.RecyclerView/android." +
                "widget.LinearLayout[1]/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget." +
                "LinearLayout[8]/android.widget.TextView"))).click();

        driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android." +
                "widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android." +
                "widget.RelativeLayout[1]/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android." +
                "widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[10]/android.widget." +
                "TextView")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/" +
                "android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/" +
                "android.view.ViewGroup/android.widget.RelativeLayout[1]/androidx.recyclerview.widget.RecyclerView/android.widget." +
                "LinearLayout[2]"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/view_search_background"))).click();

        System.out.println("Be patient, Test will NOT fail. You are waiting because of a slow server");
        String expectedHotelName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/" +
                "android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/" +
                "android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/" +
                "android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.TextView[1]"))).getAttribute("text");
        System.out.println("expectedHotelName = " + expectedHotelName);

        System.out.println("Be patient, Test will NOT fail. You are waiting because of a slow server");
        String expectedHotelPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/" +
                "android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/" +
                "android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/" +
                "androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.view.ViewGroup/" +
                "android.widget.RelativeLayout/android.widget.TextView[3]"))).getAttribute("text");
        System.out.println("expectedHotelPrice = " + expectedHotelPrice);

        System.out.println("Be patient, Test will NOT fail. You are waiting because of a slow server");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/" +
                "android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/" +
                "android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/" +
                "androidx.recyclerview.widget.RecyclerView/android.widget.FrameLayout[1]/android.view.ViewGroup/" +
                "android.widget.RelativeLayout/android.widget.TextView[3]"))).click();

        String actualHotelName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/" +
                "tvFacilityDetailName"))).getAttribute("text");
        System.out.println("actualHotelName = " + actualHotelName);
        String actualHotelPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/" +
                "tv_best_price_total_price_value"))).getAttribute("text");
        System.out.println("actualHotelPrice = " + actualHotelPrice);
        Assertions.assertTrue(actualHotelName.contains(expectedHotelName), "The actual string does not contain the expected string");
        Assertions.assertTrue(actualHotelPrice.contains(expectedHotelPrice.substring(2)), "The actual string does not contain the expected string");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/btnChooseRoom"))).click();

        swipeV(0.7, 0.3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/tvPersonCount"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/btnMakeReservation"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/tvPersonalInfoName"))).sendKeys("John");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/tvPersonalInfoSurname"))).sendKeys("Doe");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/tvPersonalInfoMail"))).sendKeys("johndoe@gmail.com");


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/etPersonalInfoPhoneCode"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/hierarchy/android.widget.FrameLayout/" +
                "android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/" +
                "android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/androidx.recyclerview.widget." +
                "RecyclerView/android.widget.RelativeLayout[1]/android.widget.TextView"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/tvPersonalInfoPhone"))).sendKeys("5555555555");
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/tvPersonalInfoCountry"))).sendKeys("Turkey");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/tvPersonalInfoCountry"))).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("/hierarchy/android.widget.FrameLayout/android.widget." +
//                "LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget." +
//                "FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/androidx.recyclerview.widget.RecyclerView/android." +
//                "widget.RelativeLayout[1]/android.widget.TextView\n"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Turkey']"))).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/checkBoxSaveInfo"))).click();
        swipeV(0.5, 0.3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/checkBoxIllumination"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/checkBoxPermission"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/btnPersonalInfoNext2"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil.reservationSteps:id/tvCouponUse"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil.reservationSteps:id/etCouponCode"))).sendKeys("APP05");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil.reservationSteps:id/button4"))).click();
        WebElement alertMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/message")));
        Assertions.assertTrue(alertMessage.isDisplayed());
        //        MobileElement clearElem = driver.findElement(MobileBy.AccessibilityId("clear"));

//        System.out.println("Text of elem: " + clearElem.getText());
//        Assertions.assertTrue(clearElem.isDisplayed());

        //close the app
//        driver.closeApp();

    }

    /**
     * ekranda swipe islemi yapar
     *
     * @param startPoint yüzde olarak 0, 1 arasi double sayi
     * @param stopPoint  yüzde olarak 0, 1 arasi double
     */
    public void swipeH(double startPoint, double stopPoint) {
        int w = driver.manage().window().getSize().width;
        int h = driver.manage().window().getSize().height;

        new TouchAction<>(driver)
                .press(PointOption.point((int) (w * startPoint), h / 2))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point((int) (w * stopPoint), h / 2))
                .release()
                .perform();
    }

    /**
     * ekranda swipe islemi yapar
     *
     * @param startPoint yüzde olarak 0, 1 arasi double sayi
     * @param stopPoint  yüzde olarak 0, 1 arasi double
     */
    public void swipeV(double startPoint, double stopPoint) {
        int w = driver.manage().window().getSize().width;
        int h = driver.manage().window().getSize().height;

        new TouchAction<>(driver)
                .press(PointOption.point(w / 2, (int) (h * startPoint)))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(w / 2, (int) (h * stopPoint)))
                .release()
                .perform();
    }

}
/*Android App Otomasyonu
Android apps otomasyonu için Java ile Maven projesi oluşturulmalı, TestNG(veya Junit), Appium kullanılması gerekmektedir.
1.+	Uygulama başlatılır ve tanıtım sayfalarında 2 kere sağa kaydırılır ve devam butonuna tıklanır
2.+	Otel sekmesinde "Ankara" girilir ve ilk seçenek seçilir
3.+	Tarih sekmesi açılır ve gidiş tarihi 3, dönüş için 5 gün ilerisi seçilir ve arama butonuna basılır.
4.+	İlk çıkan tesisin isim ve fiyat bilgisi alınır ve tesis sayfası açılır
5.+	Tesis sayfasında tesisin ismi ve fiyatı doğrulanır
6.+	"Diğer Oda Seçenekleri" butonuna basılır ve bir oda seçilir
7.+	Rezervasyon 1. sayfasında kişi bilgileri girilir ve "Sonraki Adım" butonuna tıklanır
8.	Rezervasyon 2. sayfasında kupon alanı açılır ve "APP05" girilir, kullan butonuna tıklanır ve uyarı mesajı geldiği doğrulanır.
*/