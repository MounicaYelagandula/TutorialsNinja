package com.TutorialsNinja.qa.Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.TutorialsNinja.qa.TestComponents.Base;
import com.TutorialsNinja.qa.TestComponents.RetryTest;

import pageObjects.HomePage;

public class SearchTestcases extends Base{
	
	@Test(priority=1)
	public void searchWithValidProduct() throws Exception
	{
		HomePage homePageObject=launchApplication();
		homePageObject.search("imac");
		boolean match=homePageObject.verifySearchedProduct("imac");
		Assert.assertTrue(match, "The product you searched does not exist");
	}
	
	@Test(priority=2,retryAnalyzer = RetryTest.class)
	public void searchWithInvalidProduct() throws Exception
	{
		HomePage homePageObject=launchApplication();
		homePageObject.search("honda");
		String msg=homePageObject.getNoSearchResultMessage();
		Assert.assertEquals("There is no product that matches the search criteria.", msg);
		Assert.assertTrue(false);
	}

	
}
