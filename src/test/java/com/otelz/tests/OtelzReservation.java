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

        //1.+	Uygulama başlatılır ve tanıtım sayfalarında 2 kere sağa kaydırılır ve devam butonuna tıklanır
        swipeH(0.7, 0.3);
        swipeH(0.7, 0.3);
        driver.findElement(By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button")).click();
        swipeH(0.7, 0.3);
        driver.findElement(By.id("com.otelz.mobil:id/materialButtonContinue")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/tv_search_label"))).click();

        //2.+	Otel sekmesinde "Ankara" girilir ve ilk seçenek seçilir

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/search_src_text"))).sendKeys("Ankara");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//*[@resource-id=\"com.otelz.mobil.search:id/tvSearchedItem\"])[1]"))).click();

        //3.+	Tarih sekmesi açılır ve gidiş tarihi 3, dönüş için 5 gün ilerisi seçilir ve arama butonuna basılır.

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/tv_start_date"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@resource-id=\"com.otelz.mobil:id/tv_day_number\" and @text=\"8\"]"))).click();

        driver.findElement(By.xpath("//*[@resource-id=\"com.otelz.mobil:id/tv_day_number\" and @text=\"10\"]")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@resource-id=\"com.otelz.mobil:" +
                "id/tvCheckDaysAndContinueDates\"]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/view_search_background"))).click();

        //4.+	İlk çıkan tesisin isim ve fiyat bilgisi alınır ve tesis sayfası açılır

        System.out.println("Be patient, Test will NOT fail. You are waiting because of a slow server");

        String expectedHotelName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("" +
                "//*[@resource-id='com.otelz.mobil:id/textView21']"))).getAttribute("text");
        System.out.println("expectedHotelName = " + expectedHotelName);

        System.out.println("Be patient, Test will NOT fail. You are waiting because of a slow server");

        String expectedHotelPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("" +
                "//*[@resource-id='com.otelz.mobil:id/tvFinalPrice']"))).getAttribute("text");
        System.out.println("expectedHotelPrice = " + expectedHotelPrice);

        System.out.println("Be patient, Test will NOT fail. You are waiting because of a slow server");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("" +
                "//*[@resource-id='com.otelz.mobil:id/textView21']"))).click();
        //5.+	Tesis sayfasında tesisin ismi ve fiyatı doğrulanır

        String actualHotelName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/" +
                "tvFacilityDetailName"))).getAttribute("text");
        System.out.println("actualHotelName = " + actualHotelName);
        String actualHotelPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/" +
                "tv_best_price_total_price_value"))).getAttribute("text");
        System.out.println("actualHotelPrice = " + actualHotelPrice);
        Assertions.assertTrue(actualHotelName.contains(expectedHotelName), "The actual string does not contain " +
                "the expected string");
        Assertions.assertTrue(actualHotelPrice.contains(expectedHotelPrice.substring(2)), "The actual " +
                "string does not contain the expected string");
        //6.+	"Diğer Oda Seçenekleri" butonuna basılır ve bir oda seçilir

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/btnChooseRoom"))).click();

        swipeV(0.7, 0.3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/tvPersonCount"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/btnMakeReservation"))).click();

        //7.+	Rezervasyon 1. sayfasında kişi bilgileri girilir ve "Sonraki Adım" butonuna tıklanır

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/tvPersonalInfoName")))
                .sendKeys("John");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/tvPersonalInfoSurname")))
                .sendKeys("Doe");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/tvPersonalInfoMail")))
                .sendKeys("johndoe@gmail.com");


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/etPersonalInfoPhoneCode"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Turkey - 90']"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/tvPersonalInfoPhone"))).sendKeys("5555555555");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/tvPersonalInfoCountry"))).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@text='Turkey']"))).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/checkBoxSaveInfo"))).click();
        swipeV(0.5, 0.3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/checkBoxIllumination"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/checkBoxPermission"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil:id/btnPersonalInfoNext2"))).click();

        //8.	Rezervasyon 2. sayfasında kupon alanı açılır ve "APP05" girilir, kullan butonuna tıklanır ve uyarı mesajı geldiği doğrulanır.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil.reservationSteps:id/tvCouponUse"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil.reservationSteps:id/etCouponCode"))).sendKeys("APP05");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.otelz.mobil.reservationSteps:id/button4"))).click();
        WebElement alertMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("android:id/message")));
        Assertions.assertTrue(alertMessage.isDisplayed());


        driver.closeApp();

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