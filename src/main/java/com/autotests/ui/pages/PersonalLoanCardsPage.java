package com.autotests.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class PersonalLoanCardsPage extends BasePage {

    By loanAmtInput = By.name("desiredAmount");
    By loanPurposeSelect = By.name("loan-purpose");
    By checkRateBtn = By.cssSelector("[data-auto='CheckYourRate']");

    public enum LoanPurpose {
        CREDIT_CARD,
        DEBT_CONSOLIDATION,
        HOME_IMPROVEMENT,
        SMALL_BUSINESS,
        LARGE_PURCHASE,
        OTHER;
    }

    public PersonalLoanCardsPage(WebDriver driver) {
        super(driver);
    }

    public void enterLoanAmt(String loanAmt) {
        getElement(loanAmtInput).sendKeys(loanAmt);
    }

    public void selectLoanPurpose(LoanPurpose purpose) {
        new Select(getElement(loanPurposeSelect)).selectByValue(purpose.name());
    }

    public void clickCheckRate() {
        getElement(checkRateBtn).click();
    }

    public void fillLoanDetails(String loanAmt, LoanPurpose purpose) {
        enterLoanAmt(loanAmt);
        selectLoanPurpose(purpose);
    }
}
