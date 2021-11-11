package com.autotests.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Map;

public class OfferPage extends BasePage {

    By loanAmtLbl = By.cssSelector("[data-auto='userLoanAmount']");
    By monthlyPaymentLbl = By.cssSelector("[data-auto='defaultMonthlyPayment']");
    By defaultLoanTermLbl = By.cssSelector("[data-auto='defaultLoanTerm']");
    By defaultAPRLbl = By.cssSelector("[data-auto='defaultAPR']");
    By defaultLoanInterestLbl = By.cssSelector("[data-auto='defaultLoanInterestRate'] div");
    By getDefaultLoanBtn = By.cssSelector("[data-auto='getDefaultLoan']");

    public static final String PAGE_TITLE = "Affordable Online Personal Loans | Upgrade";
    public static final String LOAN_AMOUNT = "LoanAmount";
    public static final String APR = "APR";
    public static final String LOAN_INTEREST = "LoanInterest";
    public static final String LOAN_TERM = "LoanTerm";
    public static final String MONTHLY_PAYMENT = "MonthlyPayment";

    public OfferPage(WebDriver driver) {
        super(driver);
    }

    public String getLoanAmt() {
        return getElement(loanAmtLbl).getText();
    }

    public String getMonthlyPayment() {
        return getElement(monthlyPaymentLbl).getText();
    }

    public String getDefaultAPR() {
        return getElement(defaultAPRLbl).getText();
    }

    public String getDefaultLoanTerm() {
        return getElement(defaultLoanTermLbl).getText();
    }

    public String getDefaultLoanInterest() {
        return getElement(defaultLoanInterestLbl).getText();
    }

    public void clickGetDefaultLoanBtn() {
        getElement(getDefaultLoanBtn).click();
    }

    public Map saveLoanDetails(String loanAmt, String apr, String loanInterest, String loanTerm, String monthlyPayment) {
        Map<String, String> loanDetails = new HashMap<>();
        loanDetails.put(LOAN_AMOUNT, loanAmt);
        loanDetails.put(APR, apr);
        loanDetails.put(LOAN_TERM, loanInterest);
        loanDetails.put(LOAN_INTEREST, loanTerm);
        loanDetails.put(MONTHLY_PAYMENT, monthlyPayment);
        return loanDetails;
    }


}
