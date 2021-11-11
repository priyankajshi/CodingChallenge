package com.autotests.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BasicInfoPage extends BasePage {

    By firstNameInput = By.cssSelector("[data-auto='borrowerFirstName']");
    By lastNameInput = By.cssSelector("[data-auto='borrowerLastName']");
    By streetInput = By.cssSelector("[data-auto='borrowerStreet']");
    By cityInput = By.cssSelector("[data-auto='borrowerCity']");
    By stateInput = By.cssSelector("[data-auto='borrowerState']");
    By zipcodeInput = By.cssSelector("[data-auto='borrowerZipCode']");
    By dobInput = By.cssSelector("[data-auto='borrowerDateOfBirth']");
    By continuePersonalInfoBtn = By.cssSelector("[data-auto='continuePersonalInfo']");

    public static final String HEADER_TEXT = "Let's get started with some basic information";

    public BasicInfoPage(WebDriver driver) {
        super(driver);
    }

    public void enterFirstName(String firstName) {
        getElement(firstNameInput).sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        getElement(lastNameInput).sendKeys(lastName);
    }

    public void enterStreet(String street) {
        getElement(streetInput).sendKeys(street);
    }

    public void enterCity(String city) {
        getElement(cityInput).sendKeys(city);
    }

    public void enterState(String state) {
        getElement(stateInput).sendKeys(state);
    }

    public void enterZipcode(String zipcode) {
        getElement(zipcodeInput).sendKeys(zipcode);
    }

    public void enterDob(String dob) {
        if(validateDOB(dob)) {
            getElement(dobInput).sendKeys(dob);
        } else {
            throw new IllegalArgumentException("Invalid DOB");
        }
    }

    public void clickContinue() {
        jsExecution("document.querySelector('[data-floating]').setAttribute('data-focused', 'false')");
        waitForElementActive(continuePersonalInfoBtn);
        getElement(continuePersonalInfoBtn).click();
        if(!headerText().equals(IncomeInfoPage.HEADER_TEXT)) {
            getElement(continuePersonalInfoBtn).click();
        }
    }

    public void enterBasicDetails(String firstName, String lastName, String street, String city, String state, String zipcode, String dob) {
        enterFirstName(firstName);
        enterLastName(lastName);
        enterCity(city);
        enterStreet(street);
        enterState(state);
        enterZipcode(zipcode);
        enterDob(dob);
    }

    /*
       Validate DOB is within <any valid date before 01/01/2000 and after 01/01/1930>
     */
    public boolean validateDOB(String dob) {
        try {
            Date dobDate = new SimpleDateFormat("MM/dd/yyyy").parse(dob);
            Date validFrom = new SimpleDateFormat("MM/dd/yyyy").parse("01/01/1930");
            Date validTo = new SimpleDateFormat("MM/dd/yyyy").parse("01/01/2000");
            if (dobDate.after(validTo) || dobDate.before(validFrom)) {
                return false;
            }
            return true;
        } catch (ParseException ex) {
            throw new IllegalArgumentException("Invalid Date of Birth");
        }
    }
}
