package com.autotests.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutPage extends BasePage {

    By logoutMessageLbl = By.cssSelector("[data-auto='logoutMessage']");

    public LogoutPage(WebDriver driver) {
        super(driver);
    }

    public String getLogoutMessage() {
        return getElement(logoutMessageLbl).getText();
    }
}
