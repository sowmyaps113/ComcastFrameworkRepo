package com.comcast.crm.generic.webdriverutility;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class UtilityClassObject {

	public static ThreadLocal<ExtentTest> test=new ThreadLocal<ExtentTest>();
	public static ThreadLocal<WebDriver> driver=new ThreadLocal<WebDriver>();

	public static ExtentTest getTest()
	{
		return test.get(); //This method will give ExtentTest object for the execution of Multiple threads to achieve Parallel Execution. It will share the object
	} 
	
	public static void setTest(ExtentTest actTest)
	{
		test.set(actTest);
	}
	public static WebDriver getDriver()
	{
		return driver.get(); //This method will give driver object for the execution of Multiple threads to achieve Parallel Execution. It will share the object
	}
	public static void setDriver(WebDriver actDriver)
	{
		driver.set(actDriver);
	}
	
}
