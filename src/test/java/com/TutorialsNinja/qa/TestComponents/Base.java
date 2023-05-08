package com.TutorialsNinja.qa.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.HomePage;

public class Base {
	public WebDriver driver;
	public Properties p;
	
	
	public void loadPropertiesFile() throws IOException
	{
		p=new Properties();
		File fl=new File(System.getProperty("user.dir")+"\\src\\main\\java\\resourceFiles\\configuration.properties");
		FileInputStream fileinputstream=new FileInputStream(fl);
		p.load(fileinputstream);
	}
	public WebDriver initializeDriver() throws IOException
	{
		//WebDriverManager.chromedriver().setup();
		
		//String browserName="chrome";
		loadPropertiesFile();
		String browserName=p.getProperty("browser");
		ChromeOptions co=new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		if(browserName.equalsIgnoreCase("chrome"))
			driver=new ChromeDriver(co);
		else if(browserName.equalsIgnoreCase("firefox"))
			driver=new FirefoxDriver();
		else if(browserName.equals("edge"))
			driver=new EdgeDriver();
		return driver;
	}
	
	public HomePage launchApplication() throws IOException 
	{
		
			driver=initializeDriver();
			String url=p.getProperty("url");
		driver.get(url);
		driver.manage().window().maximize();
		HomePage homePageObject=new HomePage(driver);
		return homePageObject;
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	@DataProvider(name="validCredentials")
	public Object[][] supplyTestData() throws IOException
	{
		
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\resourceFiles\\TestData.xlsx");
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		XSSFSheet sheet=workbook.getSheet("LoginTestcase1Valid");
		int rowcount=sheet.getPhysicalNumberOfRows();
		int columncount=sheet.getRow(0).getLastCellNum();
		Object[][] data=new Object[rowcount-1][columncount];
		DataFormatter formatter=new DataFormatter();
		for(int i=0;i<rowcount-1;i++)
		{
			Row currentRow=sheet.getRow(i+1);
			for(int j=0;j<columncount;j++)
			{
				data[i][j]=formatter.formatCellValue(currentRow.getCell(j));
			}
		}
		//return new Object[][] { {"email1","pwd1"}, {"email2","pwd2"}, {"email3","pwd3"} };
		return data;
	}
	
	
//	public Object[][] dataFromExcel(String sheetname, File f) throws IOException
//	{
//		
//		FileInputStream fis=new FileInputStream(f);
//		XSSFWorkbook workbook=new XSSFWorkbook(fis);
//		XSSFSheet sheet=workbook.getSheet(sheetname);
//		int rowcount=sheet.getPhysicalNumberOfRows();
//		int columncount=sheet.getRow(0).getLastCellNum();
//		Object[][] data=new Object[rowcount-1][columncount];
//		DataFormatter formatter=new DataFormatter();
//		for(int i=0;i<rowcount-1;i++)
//		{
//			Row currentRow=sheet.getRow(i+1);
//			for(int j=0;j<columncount;j++)
//			{
//				data[i][j]=formatter.formatCellValue(currentRow.getCell(j));
//			}
//		}
//		//return new Object[][] { {"email1","pwd1"}, {"email2","pwd2"}, {"email3","pwd3"} };
//		return data;
//	}
	
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		File screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir")+"/screenshots/"+testCaseName+".png"));
		return System.getProperty("user.dir")+"/screenshots/"+testCaseName+".png";
	}
	
}
