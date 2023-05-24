package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilityMethods {
	public final int IMPLICIT_WAIT_TIME=10; //no need of static beacuse other class is not using these variables(classname.variable)
	public final int EXPLICIT_WAIT_TIME=5;
	public WebDriver driver;
	public WebDriverWait w;
	
	public UtilityMethods(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	public void waitForElementToBeVisible(By ByLocator)
	{
	    w=new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_TIME));
		w.until(ExpectedConditions.visibilityOfElementLocated(ByLocator));
	}
	

}
