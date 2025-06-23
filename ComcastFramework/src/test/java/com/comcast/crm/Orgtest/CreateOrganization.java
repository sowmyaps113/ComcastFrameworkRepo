package com.comcast.crm.Orgtest;

import java.io.IOException;
import java.util.Random;

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
import com.crm.generic.BaseUtility.BaseClass;

public class CreateOrganization extends BaseClass {

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
		
				
		//Read testscript data from Excel File
		String orgName = eLib.getDataFromExcelFile("Organization", 1, 2)+jLib.getRandomNumber();
				
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
	
		//Step6 : Verify the Organization Name in the Expected Result
		String actOrgName=oip.getOrgName().getText();
		if(actOrgName.trim().equals(orgName))
		{
			System.out.println(orgName+" information is created==PASS");
		}
		else
		{
			System.out.println(orgName+" information is not created==FAIL");
		}
		
		//Step7 : Logout
		hp.signOut();
		driver.quit();
	}
}
