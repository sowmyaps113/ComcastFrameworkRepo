package com.comcast.crm.Orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.crm.generic.BaseUtility.BaseClass;

public class CreateOrganizationWithIndustryAndType extends BaseClass {

	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
		
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
		String orgName = eLib.getDataFromExcelFile("Organization", 4, 2)+jLib.getRandomNumber();
		String industry = eLib.getDataFromExcelFile("Organization", 4, 3);
		String type = eLib.getDataFromExcelFile("Organization", 4, 4);
				
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
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Step2 : Navigate to Organization Module
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step3 : Click on "Create Organization" Button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//Step4 : Enter all the details and Create New Organization
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		WebElement wbsele1 = driver.findElement(By.name("industry"));
		Select sel1=new Select(wbsele1);
		sel1.selectByVisibleText(industry);
		WebElement wbsele2 = driver.findElement(By.name("accounttype"));
		Select sel2=new Select(wbsele2);
		sel2.selectByVisibleText(type);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step5: Verify the Industry info
		String actIndustry=driver.findElement(By.id("dtlview_Industry")).getText();
		if(actIndustry.equals(industry))
		{
			System.out.println(industry+ " information is verified==PASS");
		}
		else
		{
			System.out.println(industry+ " information is not verified==FAIL");
		}
		//Step6 : Verify the Type Info
		String actType=driver.findElement(By.id("dtlview_Type")).getText();
		if(actType.equals(type))
		{
			System.out.println(type+ " information is verified==PASS");
		}
		else
		{
			System.out.println(industry+ " information is not verified==FAIL");
		}
		
		//Step7 : Logout
		WebElement element=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wLib.mouseMoveOnElement(driver, element);
		driver.findElement(By.linkText("Sign Out")).click();
		Thread.sleep(2000);
		driver.quit();
	}
}
		