package com.TutorialsNinja.qa.Testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.TutorialsNinja.qa.TestComponents.BaseTest;

import io.github.bonigarcia.wdm.WebDriverManager;

import pageObjects.AccountPage;
import pageObjects.HomePage;

public class LoginTest1 extends BaseTest {

	@Test(priority=1,dataProvider = "validCredentials" )
	public void LoginTestcase1Valid(String email,String password) throws IOException 
	{
		
		HomePage homepageObject=launchApplication();
		//AccountPage accountPageObject=homepageObject.login("amotoori3@gmail.com", "12345");
		AccountPage accountPageObject=homepageObject.login(email, password);
		//Assert.assertTrue(homepageObject.VerifyLogin()ElementisDisplayed);
		//Assert.assertEquals("My Account", homepageObject.VerifyLogin());
		Assert.assertTrue(homepageObject.VerifyLogin().isDisplayed(),"My Account is not displayed");
		//when testcase fails, the browser is not closing
		
	}
	
	
//	@DataProvider
//	public Object[][] supplytestdataFrombaseTest() throws IOException
//	{
//		File f=null;
//		Object[][] data=dataFromExcel("sheetname", f);
//		return data;
//	}
	
	@Test(priority=2)
	public void LoginTestcase2Invalid() throws IOException 
	{
		HomePage homepageObject=	launchApplication();
		homepageObject.login("xyzabc123"+generateTimestamp()+"@gmail.com", "xyzabc123");
		Assert.assertTrue(homepageObject.returnWarningMessage().isDisplayed(),"Warning message is not");
		
	}
	
	public String generateTimestamp()
	{
		Date date=new Date();
		return date.toString().replace(" ","_").replace(":","_");
	}
	 
	@Test(priority = 3)
	public void LoginTestcase3InvalidEmailOnly() throws IOException
	{
		HomePage homepageObject=	launchApplication();
		homepageObject.login("xyzabc123"+generateTimestamp()+"@gmail.com", "12345");
		Assert.assertTrue(homepageObject.returnWarningMessage().isDisplayed(),"Warning message is not");
		
	}
	
	
}
