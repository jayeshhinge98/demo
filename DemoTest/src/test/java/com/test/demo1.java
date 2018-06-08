package com.test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import utilities.SuiteBase;

public class demo1 extends SuiteBase {

	@Test
	public void testwithHeadLessBrowser() {
		System.out.println("Browser loaded, title is:=>"+hldriver.getTitle());
		Assert.assertEquals(hldriver.getTitle(), "Google","Title doesn't match");
		hldriver.findElement(By.xpath("//input[@title='Search']")).sendKeys("selenium");;
		hldriver.findElement(By.xpath("//input[@title='Search']")).sendKeys(Keys.ENTER);;
		System.out.println("Browser loaded, title is:=>"+hldriver.getTitle());
		Assert.assertEquals(hldriver.getTitle(), "selenium - Google Search","Title doesn't match");		
	}
	
}
