package com.autotests.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IncomeInfoPage extends BasePage {

    By additionalIncomeInput = By.cssSelector("[data-auto='borrowerAdditionalIncome']");
    By incomeInput = By.cssSelector("[data-auto='borrowerIncome']");
    By continuePersonalInfoBtn = By.cssSelector("[data-auto='continuePersonalInfo']");

    public static final String HEADER_TEXT = "How much money do you make in a year?";

    public IncomeInfoPage(WebDriver driver) {
        super(driver);
    }

    public void enterAdditionalIncome(String additionalIncome) {
        getElement(additionalIncomeInput).sendKeys(additionalIncome);
    }

    public void enterIncome(String income) {
        getElement(incomeInput).sendKeys(income);
    }

    public void clickContinue() {
        getElement(continuePersonalInfoBtn).submit();
    }

    public void enterIncomeDetails(String income, String additionalIncome) {
        enterIncome(income);
        enterAdditionalIncome(additionalIncome);
    }
}
