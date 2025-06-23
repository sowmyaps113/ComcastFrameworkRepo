
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

public class CreateContactWithSupportDate extends BaseClass {

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
		String lastName = eLib.getDataFromExcelFile("Contact",4,2)+jLib.getRandomNumber();

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
		
		//Step 2 : Navigate to Contact Module
		driver.findElement(By.linkText("Contacts")).click();
		
		//Step 3 : Click on "Create Contact" Button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
		//Capture the startDate and endDate
		String startDate=jLib.getSystemDateYYYYMMDD();
		String endDate=jLib.getRequiredDateYYYYMMDD(30);
		
		//Step 4 : Enter the detail and Create New Contact
		
		driver.findElement(By.name("lastname")).sendKeys(lastName);
		driver.findElement(By.name("support_start_date")).clear(); 
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);
		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//Step 5: Verify Header Message in the Expected Result
		String headerinfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(headerinfo.contains(lastName))
		{
			System.out.println(lastName+" is created==PASS");
		}
		else
		{
			System.out.println(lastName+" is not created==FAIL");
		}
		//Step 6 : Verify the Contact last name in the Expected Result
		String actlastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		if(actlastName.equals(lastName))
		{
			System.out.println(lastName+" information is verified==PASS");
		}
		else
		{
			System.out.println(lastName+" information is not verified==FAIL");
		}
		//Step 7 : Verify the Start date in the Expected Result
		String actStartDate=driver.findElement(By.id("dtlview_Support Start Date")).getText();
		if(actStartDate.equals(startDate))
		{
			System.out.println(startDate+" information is verified==PASS");
		}
		else
		{
			System.out.println(startDate+" information is not verified==FAIL");
		}
		//Step 8 : Verify the End Date in Expected Result
		String actEndDate=driver.findElement(By.id("dtlview_Support End Date")).getText();
		if(actEndDate.equals(endDate))
		{
			System.out.println(endDate+" information is verified==PASS");
		}
		else
		{
			System.out.println(endDate+" information is not verified==FAIL");
		}
		
		//Step 9 : Logout
		WebElement element=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wLib.mouseMoveOnElement(driver, element);
		driver.findElement(By.linkText("Sign Out")).click();
		Thread.sleep(2000);
		driver.quit();
	}
}
