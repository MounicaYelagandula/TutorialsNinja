package com.TutorialsNinja.qa.TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportConfig {
	

	public static ExtentReports getReportObject()
	{
		String path=System.getProperty("user.dir")+"//reports/index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("TutorialsNinja Reports");
		reporter.config().setReportName("Test Results");
		ExtentReports report=new ExtentReports();
		report.attachReporter(reporter);
		report.setSystemInfo("Tester","Mounica");
		return report;		
	}

}
