package com.autotests.ui;

import com.autotests.base.BaseTest;
import com.autotests.ui.pages.BasicInfoPage;
import com.autotests.ui.pages.CreateAccountPage;
import com.autotests.ui.pages.IncomeInfoPage;
import com.autotests.ui.pages.LoginPage;
import com.autotests.ui.pages.LogoutPage;
import com.autotests.ui.pages.MenuPage;
import com.autotests.ui.pages.OfferPage;
import com.autotests.ui.pages.PersonalLoanCardsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Map;

public class ValidateOfferTest extends BaseTest {

	MenuPage menuPage;
	PersonalLoanCardsPage personalLoanCardsPage;
	BasicInfoPage basicInfoPage;
	IncomeInfoPage incomeInfoPage;
	CreateAccountPage createAccountPage;
	OfferPage offerPage;
	LogoutPage logoutPage;
	LoginPage loginPage;

	public static final String BASE_URL = System.getProperty("baseUrl");
	public static final String USERNAME = System.getProperty("username");
	public static final String PASSWORD = System.getProperty("password");

	@BeforeTest
	public void initPages() {
		menuPage = new MenuPage(driver);
		personalLoanCardsPage = new PersonalLoanCardsPage(driver);
		basicInfoPage = new BasicInfoPage(driver);
		incomeInfoPage = new IncomeInfoPage(driver);
		createAccountPage = new CreateAccountPage(driver);
		offerPage = new OfferPage(driver);
		logoutPage = new LogoutPage(driver);
		loginPage = new LoginPage(driver);
	}

	/*
	  Observed there are bugs in the system as per the test case
	  1) DOB is accepted even if its not <any valid date after 01/01/2000 and before 01/01/1930>
	  Constraint is 01/01/1897 - 11/01/2003.
	 */

    @Test
	public void validateLoanOffer() {
		personalLoanCardsPage.openWebPage(BASE_URL);
		personalLoanCardsPage.fillLoanDetails("2000", PersonalLoanCardsPage.LoanPurpose.CREDIT_CARD);
		personalLoanCardsPage.clickCheckRate();
		Assert.assertTrue(basicInfoPage.headerText().contains(basicInfoPage.HEADER_TEXT), "Basic Info page not rendered");

		basicInfoPage.enterBasicDetails("First", "Last", "Test Street", "Oakland", "CA", "94621", "01/04/1967");
		basicInfoPage.clickContinue();
		Assert.assertTrue(incomeInfoPage.headerText().contains(incomeInfoPage.HEADER_TEXT), "Income Info page not rendered");

		incomeInfoPage.enterIncomeDetails("125000", "6000");
		incomeInfoPage.clickContinue();
		Assert.assertTrue(createAccountPage.headerText().contains(CreateAccountPage.HEADER_TEXT), "Create Account page not rendered");

		createAccountPage.enterDetails(USERNAME, PASSWORD);
		createAccountPage.clickCheckRatesBtn();
		createAccountPage.waitForPageTitle(OfferPage.PAGE_TITLE);

		Map loanDetailsBeforeLogin = offerPage.saveLoanDetails(offerPage.getLoanAmt(), offerPage.getDefaultAPR(), offerPage.getDefaultLoanInterest(),
				offerPage.getDefaultLoanTerm(), offerPage.getMonthlyPayment());

		menuPage.openMenu();
		menuPage.signOut();
		Assert.assertTrue(logoutPage.getLogoutMessage().contains("You've been successfully logged out.\n" +
				"See you later."),"Incorrect logout message");

		loginPage.openLoginPage();
		loginPage.enterDetails(USERNAME, PASSWORD);
		loginPage.loginIn();
		Assert.assertTrue(loginPage.getPageTitle().equals(OfferPage.PAGE_TITLE), "Offer page is not loaded");

		Map loanDetailsAfterLogin = offerPage.saveLoanDetails(offerPage.getLoanAmt(), offerPage.getDefaultAPR(), offerPage.getDefaultLoanInterest(),
				offerPage.getDefaultLoanTerm(), offerPage.getMonthlyPayment());
		Assert.assertEquals(loanDetailsBeforeLogin, loanDetailsAfterLogin, "Loan details are different after logging in");
	}
}
