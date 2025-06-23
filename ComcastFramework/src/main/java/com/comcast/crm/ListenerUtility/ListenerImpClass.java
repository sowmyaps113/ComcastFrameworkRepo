 package com.comcast.crm.ListenerUtility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;
import com.crm.generic.BaseUtility.BaseClass;

public class ListenerImpClass implements ITestListener, ISuiteListener {

	public static ExtentSparkReporter spark;
	public static ExtentReports report;
	public ExtentTest test;
	
	@Override
	public void onStart(ISuite suite) {
		
		System.out.println("Report Configuration");
		
		String time=new Date().toString().replace(" ", "_").replace(":", "_");
		
		//Spark Report Configuration
		spark=new ExtentSparkReporter("./AdvanceReport/RealTestCaseReport"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);
		
		//Add Environment Information 
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-10");
		report.setSystemInfo("BROWSER", "CHROME-100 ");
		
	}

	@Override
	public void onFinish(ISuite suite) {
		System.out.println("Report Backup");
		//Save the Extent Report and take a Backup
		report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		//Create a Test case in the Extent Report
		System.out.println("==== ====>"+result.getMethod().getMethodName()+"====START====");
		test=report.createTest(result.getMethod().getMethodName()); 
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName()+"====STARTED====");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("==== ====>"+result.getMethod().getMethodName()+"====SUCCESS==END====");
		test.log(Status.PASS, result.getMethod().getMethodName()+"====COMPLETED====");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		String testName=result.getMethod().getMethodName();
		TakesScreenshot ts=(TakesScreenshot)(BaseClass.sdriver);
		String filePath=ts.getScreenshotAs(OutputType.BASE64);
		
		
		String time=new Date().toString().replace(" ", "_").replace(":", "_");
		test.addScreenCaptureFromBase64String(filePath, testName+"_"+time);
		test.log(Status.FAIL,result.getMethod().getMethodName()+"====FAILED====");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
	}	
}
