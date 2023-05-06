package com.TutorialsNinja.qa.Testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.TutorialsNinja.qa.TestComponents.BaseTest;

import pageObjects.HomePage;

public class SearchTestcases extends BaseTest {
	
	@Test(priority=1)
	public void searchWithValidProduct() throws Exception
	{
		HomePage homePageObject=launchApplication();
		homePageObject.search("imac");
		boolean match=homePageObject.verifySearchedProduct("imac");
		Assert.assertTrue(match, "The product you searched does not exist");
	}
	
	@Test(priority=2)
	public void searchWithInvalidProduct() throws Exception
	{
		HomePage homePageObject=launchApplication();
		homePageObject.search("honda");
		String msg=homePageObject.getNoSearchResultMessage();
		Assert.assertEquals("There is no product that matches the search criteria.", msg);
	}

	
}
