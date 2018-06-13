package com.test;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

public class demo2{
	@Test
	public void test2(){
		System.out.println("This is demo test");
		AssertJUnit.fail("Failed");
	}

}
