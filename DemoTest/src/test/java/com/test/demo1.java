package com.test;

//import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import actions.CommonActions;
import utilities.SuiteBase;
import utilities.TestUtitlies;

public class demo1 extends SuiteBase {
	CommonActions ca = new CommonActions();
	TestUtitlies tu = new TestUtitlies();

	@Test(enabled = false)
	public void testwithHeadLessBrowser() {
		// System.out.println("Browser loaded, title is:=>"+hldriver.getTitle());
		// Assert.assertEquals(hldriver.getTitle(), "Google","Title doesn't match");
		// hldriver.findElement(By.xpath("//input[@title='Search']")).sendKeys("selenium");;
		// hldriver.findElement(By.xpath("//input[@title='Search']")).sendKeys(Keys.ENTER);;
		// System.out.println("Browser loaded, title is:=>"+hldriver.getTitle());
		// Assert.assertEquals(hldriver.getTitle(), "selenium - Google Search","Title
		// doesn't match");
	}

	@Test(priority = 1, alwaysRun=true)
	public void loginCRM() {
		System.out.println("Title is=>" + chdriver.getTitle());
		Assert.assertEquals(chdriver.getTitle(),
				"CRMPRO - CRM software for customer relationship management, sales, and support."

				, "Page is not loaded yet");
		System.out.println("Page loaded successfully");
		chdriver.findElement(By.name("username")).sendKeys("jayeshhinge");
		chdriver.findElement(By.name("password")).sendKeys("figmd123");

		Actions action = new Actions(chdriver);
		// JavascriptExecutor js = (JavascriptExecutor) chdriver;

		if (ca.isElementPresent(chdriver, By.xpath("//div[@class='intercom-borderless-dismiss-button']/span"))) {
			System.out.println("Button displays");
			ca.clickOnElement(chdriver, By.xpath("//div[@class='intercom-borderless-dismiss-button']/span"));
			chdriver.findElement(By.xpath("//input[@type='submit']/parent::div")).click(); // *[@type='submit']
			if (chdriver.getTitle().equals("CRMPRO")) {
				System.out.println("Agent logged into application successfully.");
			} else {

				action.moveToElement(
						chdriver.findElement(By.xpath("//div[@class='intercom-borderless-dismiss-button']/span")))
						.click().build().perform();
				action.moveToElement(chdriver.findElement(By.xpath("//input[@type='submit']/parent::div"))).click()
						.build().perform();
			}

		} else {
			System.out.println("Button is not present");

			// chdriver.findElement(By.xpath("//input[@type='submit']/parent::div")).click();
			if (chdriver.getTitle().equals("CRMPRO")) {
				Assert.assertEquals(chdriver.getTitle(), "CRMPRO", "Agent not able to logged into application.");
				System.out.println("Agent logged into application successfully.");
			} else {
				chwait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.name("intercom-borderless-frame")));
				System.out.println("Frame is available.");
				// chdriver.switchTo().frame("intercom-borderless-frame");
				// chwait.until(ExpectedConditions
				// .elementToBeClickable(By.xpath("//div[@class='intercom-borderless-dismiss-button']/span")));
				ca.clickOnElement(chdriver, By.xpath("//div[@class='intercom-borderless-dismiss-button']/span"));
				chdriver.switchTo().defaultContent();
				chdriver.findElement(By.name("password")).click();
				chdriver.findElement(By.name("password")).sendKeys(Keys.ENTER);
				if (chdriver.getTitle().equals("CRMPRO")) {
					System.out.println("Agent logged into application successfully.");
				} else {
					System.out.println("Agent login failed.");
					Assert.fail("Agent login failed.");
				}
				// Robot rb = new Robot();
				// rb.keyPress(KeyEvent.VK_ENTER);
				// js.executeScript("arguments[0].click()",
				// chdriver.findElement(By.xpath("//input[@type='submit']/parent::div")));
			}
		}

		// for (int i = 0; i < array.length; i++) {
		// for (int j = 0; j < array.length; j++) {
		// function();
		// }
		// }

	}

	@Test(priority = 2)
	public void moveToPopUpCloseAndReturnToMainBrowser() throws IOException {
		chdriver.switchTo().frame("mainpanel");
		String parentWindow = chdriver.getWindowHandle();
		ca.clickOnElement(chdriver, By.xpath("//li[@class='navMenuDefaultCompany']/a"));
		Set<String> listOfWindows = chdriver.getWindowHandles();
		if (listOfWindows.size() == 1) {
			System.out.println("Child window not pop-up.");
			Assert.fail("Child window doesn't poped-up.");
		} else {
			System.out.println("Pop-up populated, Number of window handles are: " + listOfWindows.size());
			Iterator<String> it = listOfWindows.iterator();
			String childWindow = null;
			while (it.hasNext()) {
				childWindow = (String) it.next();
			}
			chdriver.switchTo().window(childWindow);
			File file = new File("src/main/resources/textfile.txt");
			Properties properties = new Properties();
			InputStream inStream1 = new FileInputStream("src/main/resources/property.properties");
			OutputStream outStream = new FileOutputStream("src/main/resources/textfile.txt");
			String actualString = chdriver.findElement(By.xpath("//tr[2]/td[1]")).getText();
			outStream.write(actualString.getBytes());
			Scanner inStream = new Scanner(file);
			// System.out.println("File contents are: ");
			// while (inStream.hasNext()) {
			// System.out.print(inStream.next() + " ");
			// }
			System.out.println("Contents on pop-up are: " + actualString);
			Assert.assertEquals(actualString,
					"The \"Default Company\" button is a feature that works when you are viewing a Company record. By clicking this link, you are telling the system that you would like all views to default to this company, which can filter out information and only show information having to do with this particular record.\nIf you have a default company selected, the name of the company will show up on this button, and any subsequent records created will be made with this company information. Click this button from any screen to return to the default company record view. Click out of the company tab to clear this feature and default to viewing all information with no default company selected.",
					"Pop-up text doesn't match");
			System.out.println("PASS: Contents from pop-up are matching properly.");
			chdriver.close();
			inStream.close();
			inStream1.close();
			outStream.close();
			chdriver.switchTo().window(parentWindow);

		}
	}

	@Test(priority = 3)
	public void openPageTocreateContact() {
		chdriver.switchTo().frame("mainpanel");
		// ca.clickOnElement(chdriver, By.xpath("//a[@title='Contacts']"));
		ca.mouseHoverOnElement(chdriver, By.xpath("//a[@title='Contacts']"));
		ca.clickOnElement(chdriver, By.xpath("//a[@title='New Contact']"));
		if (ca.isElementPresent(chdriver, By.xpath("//legend[contains(text(),'Contact Information')]"))) {
			System.out.println("Create Contact page opened successfully.");
		} else {
			System.out.println("Unable to open Create Contact page.");
			Assert.fail("Unable to open Create Contact page.");
		}
	}

	@Test(priority = 4, description = "This is description for 5th test")
	public void verifySaveButtonWithoutEnteringContactDetails() {
		// openPageTocreateContact();
		ca.clickOnElement(chdriver, By.xpath("//form/table/tbody/tr/td/input[@type='submit'][1]"));
		if (isAlertPresent()) {
			Alert alert = chdriver.switchTo().alert();
			String alertText = alert.getText();
			System.out.println("Alert Text is: " + alertText);
			Assert.assertEquals(alertText, "Please enter a first name\nPlease enter a surname\n",
					"Alert text doesn't matches");
			alert.accept();
		} else {
			System.out.println("Alert is not present.");
		}

	}

	@Test(priority = 20, enabled = false)
	public void logout() {

		chdriver.switchTo().frame("mainpanel");
		chdriver.findElement(By.xpath("//*[contains(text(),'Logout')]")).click();
		Assert.assertEquals(chdriver.getTitle(),
				"CRMPRO - CRM software for customer relationship management, sales, and support.",
				"Agent not able to logout from application.");
		System.out.println("Agent logout from application successfully");
	}

	boolean isAlertPresent() {
		try {
			chdriver.switchTo().alert();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

}
