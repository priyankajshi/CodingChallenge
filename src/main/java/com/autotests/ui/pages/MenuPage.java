package com.autotests.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MenuPage extends BasePage {

    By menuLink = By.cssSelector("div.header-nav>label");
    By signoutLink = By.cssSelector("[href*='logout']");

    public MenuPage(WebDriver driver) {
        super(driver);
    }

    public void openMenu() {
        waitForElementActive(menuLink);
        jsExecution("document.querySelector('div.header-nav>label').click();");
    }

    public void signOut() {
        jsExecution("document.querySelector('[href*=logout]').click();");
    }
}
