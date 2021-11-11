package com.autotests.ui.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	WebDriverWait wait;
	WebDriver driver;
	By header = By.cssSelector("div h1");

	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public WebElement getElement(By locator) {
		WebElement element= null;
		try {
			waitForElementPresent(locator);
			element = driver.findElement(locator);
			return element;
		}
		catch(Exception e) {
			Logger.getLogger(BasePage.class).error("Error getting element: " + locator.toString());
			e.printStackTrace();
		}
		
		return element;
	}

	public void waitForElementPresent(By locator) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}
		catch(Exception e) {
			Logger.getLogger(BasePage.class).error("Error while waiting for the element:" + locator.toString());
		}
		 
	}

	public void waitForElementActive(By locator) {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		}
		catch(Exception e) {
			Logger.getLogger(BasePage.class).error("Error while waiting for the element:" + locator.toString());
		}

	}

	public void waitForPageTitle(String title) {
		try {
			wait.until(ExpectedConditions.titleContains(title));
		}
		catch(Exception e) {
			Logger.getLogger(BasePage.class).error("Error while waiting page title:" + title);
		}
		
	}

	public String headerText() {
		return driver.findElement(header).getText();
	}

	public void jsExecution(String script) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(script);
	}

	public void openWebPage(String url) {
		driver.get(url);
	}

	public void clearTextBox(WebElement webElement) {
		webElement.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
	}
	
}
