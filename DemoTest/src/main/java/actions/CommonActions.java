package actions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class CommonActions {

	public boolean isElementPresent(WebDriver driver, By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public boolean isElementDisplayed(WebDriver driver, By by) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		if (driver.findElement(by).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isElementEnabled(WebDriver driver, By by) {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		if (driver.findElement(by).isEnabled()) {
			return true;
		} else {
			return false;
		}
	}

	public void clickOnElementByMouseHover(WebDriver driver, By by) {
		if (isElementPresent(driver, by)) {
			new Actions(driver).moveToElement(driver.findElement(by)).click().build().perform();
			System.out.println("Element " + by.toString() + " clicked by mouse hover.");
			// logger.log(LogStatus.INFO,"Element "+by.toString()+" clicked.");

		} else {
			System.out.println("Element " + by.toString() + " not present.");
			// logger.log(LogStatus.FAIL,"Element "+by.toString()+" not present.");
			Assert.fail("Element " + by.toString() + " not present.");
		}
	}
	
	public void mouseHoverOnElement(WebDriver driver, By by) {
		if (isElementPresent(driver, by)) {
			new Actions(driver).moveToElement(driver.findElement(by)).build().perform();
			//System.out.println("Element " + by.toString() + " clicked by mouse hover.");
			// logger.log(LogStatus.INFO,"Element "+by.toString()+" clicked.");

		} else {
			System.out.println("Element " + by.toString() + " not present.");
			// logger.log(LogStatus.FAIL,"Element "+by.toString()+" not present.");
			Assert.fail("Element " + by.toString() + " not present.");
		}
	}

	public void clickOnElement(WebDriver driver, By by) {
		if (isElementPresent(driver, by)) {
			driver.findElement(by).click();
			System.out.println("Element " + by.toString() + " clicked.");
			// logger.log(LogStatus.INFO,"Element "+by.toString()+" clicked.");

		} else {
			System.out.println("Element " + by.toString() + " not present.");
			// logger.log(LogStatus.FAIL,"Element "+by.toString()+" not present.");
			Assert.fail("Element " + by.toString() + " not present.");
		}
	}

	public void clearText(WebDriver driver, By by) {
		if (isElementPresent(driver, by)) {
			driver.findElement(by).clear();
			System.out.println("Textfield " + by.toString() + " cleared.");
			// logger.log(LogStatus.INFO,"Textfield "+by.toString()+" cleared.");

		} else {
			System.out.println("Textfield " + by.toString() + " not present.");
			// logger.log(LogStatus.FAIL,"Element "+by.toString()+" not present.");
			Assert.fail("Textfield " + by.toString() + " not present.");
		}
	}

	public void enterTextInTextField(WebDriver driver, By by, String keysToSend) {
		if (isElementPresent(driver, by)) {
			clickOnElement(driver, by);
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(keysToSend);
			System.out.println("Entered text in Textfield " + by.toString() + ".");
			// logger.log(LogStatus.INFO,"Entered text in Textfield "+by.toString()+".");
		} else {
			System.out.println("Textfield " + by.toString() + " not present.");
			// logger.log(LogStatus.FAIL,"Element "+by.toString()+" not present.");
			Assert.fail("Textfield " + by.toString() + " not present.");
		}
	}

}
