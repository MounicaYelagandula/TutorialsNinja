package com.TutorialsNinja.qa.Testcases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

import com.TutorialsNinja.qa.TestComponents.Base;

import io.github.bonigarcia.wdm.WebDriverManager;

import pageObjects.AccountPage;
import pageObjects.HomePage;

public class LoginTest extends Base {

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
	
	@Test(dataProvider = "supplyJsonData")
	public void LoginTestcase4Valid(HashMap<String,String> map) throws IOException 
	{
		
		HomePage homepageObject=launchApplication();
		AccountPage accountPageObject=homepageObject.login(map.get("email"),map.get("password"));
		Assert.assertTrue(homepageObject.VerifyLogin().isDisplayed(),"My Account is not displayed");
		navigateBack();
		//when testcase fails, the browser is not closing
		
	}
	
	@DataProvider
	public Object[][] supplyJsonData() throws IOException
	{
		List<HashMap<String,String>> inputhashmaps=convertJsonToMap(System.getProperty("user.dir")+"\\src\\main\\java\\resourceFiles\\loginData.json");
		return new Object[][]{{inputhashmaps.get(0)},{inputhashmaps.get(1)},{inputhashmaps.get(2)}};
	}
	
}
