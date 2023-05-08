package com.TutorialsNinja.qa.Testcases;

import org.testng.annotations.Test;

import com.TutorialsNinja.qa.TestComponents.Base;

import pageObjects.HomePage;

public class RegisterTestcases extends Base {
	
	public static int number=28;
	@Test(priority = 1)
	public void registerTestcase1() throws Throwable
	{
		HomePage homePageObject=launchApplication();
		homePageObject.registerRequiredFields("mounica","yelagandula","mouni.y"+number+"@gmail.com", "9849404055", "12345", "12345");
		number++;
	}
	
	@Test(priority = 2)
	public void registerTestcase2() throws Throwable
	{
		HomePage homePageObject=launchApplication();
		homePageObject.registerAllFields("mounica","yelagandula","mouni.y"+number+"@gmail.com", "9849404055", "12345", "12345");
		number++;
	}
	

}
