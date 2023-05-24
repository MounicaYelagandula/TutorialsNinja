package com.TutorialsNinja.qa.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTest implements IRetryAnalyzer {

	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		int count=0, maxTry=2;
		if(count<maxTry)
		{
			count++;
			return true;
		}
		return false;
	}

}
