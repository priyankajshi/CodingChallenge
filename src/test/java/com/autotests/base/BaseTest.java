package com.autotests.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.autotests.ui.lib.DriverFactory;
import com.autotests.ui.pages.BasePage;

public class BaseTest {

    public WebDriver driver;
    public BasePage page;

    @BeforeTest
    public void setUpTest() {
        try {
            driver = new DriverFactory().setup();
            if(driver == null) {
                throw new IllegalAccessException("Driver is null");
            }
            page  = new BasePage(driver);
        } catch (Exception e) {
            Logger.getLogger(BaseTest.class).error("Test set up failed");
        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
