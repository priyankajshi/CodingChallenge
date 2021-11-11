package com.autotests.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    By usernameInput = By.cssSelector("[data-auto='username']");
    By passwordInput = By.cssSelector("[data-auto='password']");
    By loginBtn = By.cssSelector("[data-auto='login']");

    public static final String LOGIN_URL = "https://www.credify.tech/portal/login";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void openLoginPage() {
        openWebPage(LOGIN_URL);
    }

    public void enterEmailAddress(String email) {
        getElement(usernameInput).clear();
        getElement(usernameInput).sendKeys(email);
    }

    public void enterPassword(String password) {
        getElement(passwordInput).clear();
        getElement(passwordInput).sendKeys(password);
    }

    public void enterDetails(String email, String pwd) {
        enterEmailAddress(email);
        enterPassword(pwd);
    }

    public void loginIn() {
        getElement(loginBtn).click();
        waitForPageTitle(OfferPage.PAGE_TITLE);
    }
}
