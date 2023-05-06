package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.UtilityMethods;

public class HomePage extends UtilityMethods {
	
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);	//PageFactory.initElements(driver, HomePage.class);
		//we are asking to initialize the locators of this class using driver instance
		
	}
	
	@FindBy(className = "caret")
	WebElement MyAccountDropdown;
	
	@FindBy(linkText = "Login")
	WebElement LoginLink;
	
	@FindBy(id="input-email")
	WebElement EmailId;
	
	@FindBy(id="input-password")
	WebElement Password;
	
	@FindBy(css="[value='Login']")
	WebElement loginButton;
	
	@FindBy(xpath="//div[@id='content']/h2[1]")
	WebElement MyAccountTextToVerify;
	
	@FindBy(css="div[class*='alert-danger']")
	WebElement warningMessage;
	
	public void goToMyAccountDropdown()
	{
		MyAccountDropdown.click();
	}
	public AccountPage login(String inputEmailid,String inputPassword)
	{
		goToMyAccountDropdown();   //MyAccountDropdown.click();
		LoginLink.click();
		EmailId.sendKeys(inputEmailid);
		Password.sendKeys(inputPassword);
		loginButton.click();
		AccountPage accountPageObject=new AccountPage(driver);
		return accountPageObject;
	}
	
	public WebElement VerifyLogin()
	{
		return MyAccountTextToVerify;
	}
	
	public WebElement returnWarningMessage()
	{
		return warningMessage;
	}
	
	@FindBy(linkText = "Register")
	WebElement registerLink;
	
	@FindBy(id="input-firstname")
	WebElement firstName;
	
	@FindBy(id="input-lastname")
	WebElement lastName;
	
	@FindBy(id="input-telephone")
	WebElement Telephone;
	
	@FindBy(id="input-confirm")
	WebElement confirmPassword;
	
	@FindBy(xpath="//div[@class='pull-right']/input[@type='checkbox']")
	WebElement checkboxToTick;
	
	@FindBy(css="[value='Continue']")
	WebElement ContinueButton;
	public void registerRequiredFields(String firstName,String lastName,String Email,String telephone,String pwd,String confirmpwd)
	{
		MyAccountDropdown.click();
		registerLink.click();
		this.firstName.sendKeys(firstName);
		this.lastName.sendKeys(lastName);
		EmailId.sendKeys(Email);
		Telephone.sendKeys(telephone);
		Password.sendKeys(pwd);
		confirmPassword.sendKeys(confirmpwd);
		checkboxToTick.click();
		ContinueButton.click();
	}
	
	@FindBy(xpath="//input[@name='newsletter'] [@value='1']")
	WebElement subscribeRadioButton;
	public void registerAllFields(String firstName,String lastName,String Email,String telephone,String pwd,String confirmpwd)
	{
		MyAccountDropdown.click();
		registerLink.click();
		this.firstName.sendKeys(firstName);
		this.lastName.sendKeys(lastName);
		EmailId.sendKeys(Email);
		Telephone.sendKeys(telephone);
		Password.sendKeys(pwd);
		confirmPassword.sendKeys(confirmpwd);
		subscribeRadioButton.click();
		checkboxToTick.click();
		ContinueButton.click();
	}
	
	@FindBy(name="search")
	WebElement searchBox;
	
	@FindBy(xpath="//div[@class='product-thumb']//h4")
	WebElement searchResultElement;
	public void search(String productName)
	{
		searchBox.sendKeys(productName,Keys.ENTER);
	}
	
	public boolean verifySearchedProduct(String productName)
	{
		return searchResultElement.getText().equalsIgnoreCase(productName);
	}
	
	@FindBy(xpath="//div[@id='content']/h2/following-sibling::p")
	WebElement noSearchResultMessage;
	
	By SearchResultByLocator=By.xpath("//div[@id='content']/h2/following-sibling::p");
	public String getNoSearchResultMessage()
	{
		waitForElementToBeVisible(SearchResultByLocator);
		return noSearchResultMessage.getText();
	}
}
