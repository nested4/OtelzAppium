package com.otelz.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class OtelzReservation {

    @Test
    void reservation() throws MalformedURLException {

        //DesiredCapabilities
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 3");
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.0");
        caps.setCapability(MobileCapabilityType.APP, "C:\\Users\\ozgei\\IdeaProjects\\OtelzAppium\\src\\test\\resources\\Otelz-dev.apk" );

        //set URL for the appium server
        URL url = new URL("http://localhost:4723/wd/hub");

        //launch appiumDriver
        AppiumDriver<MobileElement> driver = new AndroidDriver<MobileElement>(url, caps);
        driver.findElements(By.id("com.otelz.mobil:id/textView9"));
        //locate AC element on calculator using AccessibilityId("clear")
//        MobileElement clearElem = driver.findElement(MobileBy.AccessibilityId("clear"));

//        System.out.println("Text of elem: " + clearElem.getText());
//        Assertions.assertTrue(clearElem.isDisplayed());

        //close the app
//        driver.closeApp();

    }



}
/*Android App Otomasyonu
Android apps otomasyonu için Java ile Maven projesi oluşturulmalı, TestNG(veya Junit), Appium kullanılması gerekmektedir.
1.	Uygulama başlatılır ve tanıtım sayfalarında 2 kere sağa kaydırılır ve devam butonuna tıklanır
2.	Otel sekmesinde "Ankara" girilir ve ilk seçenek seçilir
3.	Tarih sekmesi açılır ve gidiş tarihi 3, dönüş için 5 gün ilerisi seçilir ve arama butonuna basılır.
4.	İlk çıkan tesisin isim ve fiyat bilgisi alınır ve tesis sayfası açılır
5.	Tesis sayfasında tesisin ismi ve fiyatı doğrulanır
6.	"Diğer Oda Seçenekleri" butonuna basılır ve bir oda seçilir
7.	Rezervasyon 1. sayfasında kişi bilgileri girilir ve "Sonraki Adım" butonuna tıklanır
8.	Rezervasyon 2. sayfasında kupon alanı açılır ve "APP05" girilir, kullan butonuna tıklanır ve uyarı mesajı geldiği doğrulanır.
*/