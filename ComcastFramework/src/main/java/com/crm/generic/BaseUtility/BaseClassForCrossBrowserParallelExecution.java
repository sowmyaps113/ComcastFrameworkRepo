package com.crm.generic.BaseUtility;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.comcast.crm.generic.databaseutility.DataBaseUtility;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;

public class BaseClassForCrossBrowserParallelExecution { 
	
	//Create Object
	public FileUtility fLib =new FileUtility();
	public ExcelUtility eLib=new ExcelUtility();
	public JavaUtility jLib=new JavaUtility();
	public WebDriverUtility wLib=new WebDriverUtility();
	public DataBaseUtility dbLib=new DataBaseUtility();
	public WebDriver driver=null;
	public static WebDriver sdriver=null; //used in ListenerImplementationClass
	public LoginPage lp;
	public HomePage hp;
	
	@BeforeSuite(groups={"SmokeTest","RegressionTest"})
	public void configBS()
	{
		System.out.println("===Connect to Database, Report Config===");
		dbLib.getDbConnection();
	}
	
	@Parameters("BROWSER")
	@BeforeClass(groups={"SmokeTest","RegressionTest"})
	public void configBC(String browser) throws IOException
	{
		System.out.println("==Launch Browser==");
		String BROWSER=browser;
		if(BROWSER.equals("Chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(BROWSER.equals("Firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(BROWSER.equals("Edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			driver=new ChromeDriver();
		}
		sdriver=driver;
		lp=new LoginPage(driver);
		hp=new HomePage(driver);
	} 
	
	@BeforeMethod(groups={"SmokeTest","RegressionTest"})
	public void configBM() throws IOException
	{
		System.out.println("==Login==");
		String URL=fLib.getDataFromPropertiesFile("url");
		String USERNAME=fLib.getDataFromPropertiesFile("username");
		String PASSWORD=fLib.getDataFromPropertiesFile("password");
		lp.loginToApp(URL, USERNAME, PASSWORD);
	}
	
	@AfterMethod(groups={"SmokeTest","RegressionTest"})
	public void configAM()
	{ 
		hp=new HomePage(driver);
		System.out.println("==Logout==");
		hp.signOut();
	}
	
	@AfterClass(groups={"SmokeTest","RegressionTest"})
	public void configAC()
	{
		System.out.println("==Close Browser==");
		driver.quit();
	}
	
	@AfterSuite(groups={"SmokeTest","RegressionTest"})  
	public void configAS() throws SQLException
	{
		System.out.println("===Close Database, Report Backup===");
		dbLib.closeConnection();
	}
	

}
