package com.comcast.crm.Orgtest;

import java.io.IOException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.LoginPage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;

public class DeleteOrganization {
	public static void main(String[] args) throws IOException, InterruptedException {

		//Create Object
		FileUtility fLib =new FileUtility();
		ExcelUtility eLib=new ExcelUtility();
		JavaUtility jLib=new JavaUtility();
		WebDriverUtility wLib=new WebDriverUtility();
	
		//Read common data from Property File
		String BROWSER=fLib.getDataFromPropertiesFile("browser");
		String URL=fLib.getDataFromPropertiesFile("url");
		String USERNAME=fLib.getDataFromPropertiesFile("username");
		String PASSWORD=fLib.getDataFromPropertiesFile("password");
	
		//Generate the Random Number
		Random random=new Random();
		int randomInt=random.nextInt(1000);
			
		//Read test script data from Excel File
		String orgName = eLib.getDataFromExcelFile("Organization", 10, 2)+jLib.getRandomNumber();
		
		WebDriver driver=null; 
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
		//Step1 : Login to app
		driver.get(URL);
		driver.manage().window().maximize();
		wLib.waitForPageToLoad(driver);
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp(URL,USERNAME, PASSWORD); //multiple element access
	
		//Step2 : Navigate to Organization Module
		HomePage hp=new HomePage(driver);
		hp.getOrgLink().click();
	
		//Step3 : Click on "Create Organization" Button
		OrganizationsPage op=new OrganizationsPage(driver);
		op.getCreateNewOrgBtn().click();
		
		//Step4 : Enter all the details and Create New Organization
		CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		cnop.createOrg(orgName);
		
		//Step5: Verify Header Message in the Expected Result
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String headerinfo=oip.getHeaderMsg().getText();
		if(headerinfo.contains(orgName))
		{
			System.out.println(orgName+" is created==PASS");
		}
		else
		{ 
			System.out.println(orgName+" is not created==FAIL");
		}
		
		//Step 6 : Go back to Organizations Page
		hp.getOrgLink().click();
		
		//Step 7 : Search for Organization
		op.getSearchEdit().sendKeys(orgName);
		wLib.select(op.getSearchDropdown(), "Organization Name");
		op.getSearchBtn().click();
		
		//Step 8 : In Dynamic Web Table, select and delete Organization
		driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]//a[text()='del']")).click();
		Thread.sleep(2000);
		wLib.switchtoAlertAndAccept(driver);
		System.out.println(orgName+" is deleted==PASS");
		
		//Step 9 : Logout
		hp.signOut();
		driver.quit();
	}	
}

