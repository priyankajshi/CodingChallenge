package com.autotests.ui.lib;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private static WebDriver driver;
    private static final String CHROME_PROPERTY = "webdriver.chrome.driver";
    private static final String CHROME_DRIVER_PATH = "/src/main/resources/drivers/chromedriver";

    private WebDriver initDriver() {
            System.setProperty(CHROME_PROPERTY, System.getProperty("user.dir") + CHROME_DRIVER_PATH);
            return this.driver = new ChromeDriver();
    }

    public static WebDriver getDriver() {
        if(driver != null) {
            return driver;
        } else {
            return null;
        }
    }

    public WebDriver setup() {
        initDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}
