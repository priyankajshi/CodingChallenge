package com.autotests.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreateAccountPage extends BasePage {

    By emailInput = By.name("username");
    By passwordInput = By.name("password");
    By agreementCheck = By.xpath("//input[@name='agreements']/following-sibling::div[1]");
    By checkRatesBtn = By.cssSelector("[data-auto='submitPersonalInfo']");
    By dataError = By.cssSelector("div[data-error*='Password']");

    public static final String HEADER_TEXT = "Last step before you get your rate";

    public CreateAccountPage(WebDriver driver) {
        super(driver);
    }

    public void enterEmailAddress(String email) {
        getElement(emailInput).sendKeys(email);
    }

    public void enterPassword(String password) {
        if(validatePwd(password)) {
            getElement(passwordInput).sendKeys(password);
        } else {
            throw new IllegalArgumentException("Password is weak");
        }
    }

    public void setAgreementCheck() {
        WebElement agreementCheckBox = getElement(agreementCheck);
        waitForElementActive(agreementCheck);
        if(!agreementCheckBox.isSelected()) {
            agreementCheckBox.click();
        }
    }

    public void clickCheckRatesBtn() {
        getElement(checkRatesBtn).click();
    }

    public void enterDetails(String email, String pwd) {
        enterEmailAddress(email);
        enterPassword(pwd);
        setAgreementCheck();
    }

    public boolean dataErrorPresent() {
        if(getElement(dataError) == null) {
            return false;
        }
        return getElement(dataError).isDisplayed();
    }

    /*
        Validate password is strong
        Strong password: 8 characters long, contain at least one number, one uppercase letter, and one lower case letter.
     */
    public boolean validatePwd(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$");
    }
}
