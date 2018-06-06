package com.test;
import org.testng.annotations.Test;

import utilities.TestUtitlies;

public class demo1 extends TestUtitlies {

	@Test
	public void login_test(){
		//randomString(5);
		System.out.println("Random=>"+randomAlphabetic(20));
		System.out.println("Random=>"+randomNumeric(20));

	}
}
