package com.comcast.crm.contacttest;

import java.io.IOException;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.crm.generic.BaseUtility.BaseClass;

public class CreateContactWithOrganization extends BaseClass {

	public static void main(String[] args) throws InterruptedException, IOException {

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
		String orgName = eLib.getDataFromExcelFile("Contact",7,2)+jLib.getRandomNumber();
		String lastName = eLib.getDataFromExcelFile("Contact",7,3)+jLib.getRandomNumber();
				
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
		//Step 1 : Login to application
		driver.get(URL);
		driver.manage().window().maximize();
		wLib.waitForPageToLoad(driver);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		//Step 2 : Navigate to Organization Module
		driver.findElement(By.linkText("Organizations")).click();
		
		//Step 3 : Click on "Create Organization" Button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		
		//Step 4 : Enter all the details and Create New Organization
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 5: Verify Header Message in the Expected Result
		String headerinfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerinfo.contains(orgName))
		{
			System.out.println(orgName+" header is verified==PASS");
		}
		else
		{
			System.out.println(orgName+" header is not verified==FAIL");
		}
		//Step 6 : Navigate to Contact Module
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step 7 : Click on "Create Contact" Button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//Step 8 : Enter the detail and Create New Contact
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		
		//Step 9 : Select the created Organization Name by clicking on the lookup icon present near the Organization Name Textfield in the Create Contact Page
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
		
		//Step 10 : Switch to Child Window
		wLib.switchToTabOnURL(driver, "module=Accounts");
		
		driver.findElement(By.id("search_txt")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
		
		//Step 11 : Switch to Parent Window
		wLib.switchToTabOnURL(driver, "Contacts&action");
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 12: Verify Header Message in the Expected Result
		headerinfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		System.out.println(headerinfo);
		if(headerinfo.contains(lastName))
		{
			System.out.println(orgName+" header is verified==PASS");
		}
		else
		{
			System.out.println(orgName+" header is not verified==FAIL");
		}
		
		//Step 13 : Verify the Organization Name in the Organization Name Text Field is similar to the created Organization
		String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		System.out.println(actOrgName);
		if(actOrgName.trim().equals(orgName))
		{
			System.out.println(orgName+" information is verified==PASS");
		}
		else
		{
			System.out.println(orgName+" information is not verified==FAIL");
		}
		
		//Step 14 : Logout	
		WebElement element=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wLib.mouseMoveOnElement(driver, element);
		driver.findElement(By.linkText("Sign Out")).click();
		Thread.sleep(2000);
		driver.quit();
		

	}

}
