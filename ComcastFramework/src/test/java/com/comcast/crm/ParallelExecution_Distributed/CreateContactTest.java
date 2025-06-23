package com.comcast.crm.ParallelExecution_Distributed;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreateNewContactPage;
import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
import com.crm.generic.BaseUtility.BaseClass;

public class CreateContactTest extends BaseClass{

	@Test
	public void createContactTest() throws EncryptedDocumentException, IOException
	{
		ContactsPage cp=new ContactsPage(driver);
		CreateNewContactPage cncp=new CreateNewContactPage(driver);
		ContactInfoPage cip=new ContactInfoPage(driver);
		
		//Read test script data from Excel File
		
		String lastName = eLib.getDataFromExcelFile("Contact",1,2)+jLib.getRandomNumber();
		
		//Step2 : Navigate to Contact Module
		hp.getContactLink().click();
		
		//Step3 : Click on "Create Contact" Button
		cp.getCreateNewContactBtn().click();
		
		//Step4 : Enter the detail and Create New Contact
		cncp.getLastnameEdit().sendKeys(lastName);
		cncp.getSaveBtn().click();
		
		//Step5: Verify Header Message in the Expected Result
		String headerinfo = cip.getContactHeaderMsg().getText();
		if(headerinfo.contains(lastName))
		{
			System.out.println(lastName+" is created==PASS");
		}
		else
		{
			System.out.println(lastName+" is not created==FAIL");
		}
		//Step6 : Verify the Contact last name in the Expected Result
		String actlastName = cip.getLastName().getText();
		if(actlastName.equals(lastName))
		{
			System.out.println(lastName+" information is verified==PASS");
		}
		else
		{
			System.out.println(lastName+" information is not verified==FAIL");
		}
	}
	
	@Test
	public void createContactWithOrganizationTest() throws IOException
	{
		HomePage hp=new HomePage(driver);
		OrganizationsPage op=new OrganizationsPage(driver);
		CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		ContactsPage cp=new ContactsPage(driver);
		CreateNewContactPage cncp=new CreateNewContactPage(driver);
		ContactInfoPage cip=new ContactInfoPage(driver);

		//Read testscript data from Excel File
		String orgName = eLib.getDataFromExcelFile("Contact",7,2)+jLib.getRandomNumber();
		String lastName = eLib.getDataFromExcelFile("Contact",7,3)+jLib.getRandomNumber();
				
		//Step 2 : Navigate to Organization Module
		hp.getOrgLink().click();
		
		//Step 3 : Click on "Create Organization" Button
		op.getCreateNewOrgBtn().click();
		
		//Step 4 : Enter all the details and Create New Organization
		cnop.getOrgNameEdit().sendKeys(orgName);
		cnop.getSaveBtn().click();
		
		//Step 5: Verify Header Message in the Expected Result
		String headerinfo =oip.getOrgName().getText();
		if(headerinfo.contains(orgName))
		{
			System.out.println(orgName+" header is verified==PASS");
		}
		else
		{
			System.out.println(orgName+" header is not verified==FAIL");
		}
		//Step 6 : Navigate to Contact Module
		hp.getContactLink().click();
		
		//Step 7 : Click on "Create Contact" Button
		cp.getCreateNewContactBtn().click();
		
		//Step 8 : Enter the detail and Create New Contact
		cncp.getLastnameEdit().sendKeys(lastName);
		
		//Step 9 : Select the created Organization Name by clicking on the lookup icon present near the Organization Name Textfield in the Create Contact Page
		cncp.getOrgNameLookupIcon().click();
		
		//Step 10 : Switch to Child Window
		wLib.switchToTabOnURL(driver, "module=Accounts");
		
		cncp.getOrgSearchEdit().sendKeys(orgName);
		cncp.getOrgSearchBtn().click();
		driver.findElement(By.linkText(""+orgName+"")).click();
		
		//Step 11 : Switch to Parent Window
		wLib.switchToTabOnURL(driver, "Contacts&action");
		
		cncp.getSaveBtn().click();
		
		//Step 12: Verify Header Message in the Expected Result
		headerinfo = cip.getContactHeaderMsg().getText();
		System.out.println(headerinfo);
		
		//Step 13 : Verify the Organization Name in the Organization Name Text Field is similar to the created Organization
		String actOrgName = cip.getOrgName().getText();
		System.out.println(actOrgName);
		if(actOrgName.trim().equals(orgName))
		{
			System.out.println(orgName+" information is verified==PASS");
		}
		else
		{
			System.out.println(orgName+" information is not verified==FAIL");
		}		
	}
	
	@Test
	public void createContactWithSupportDateTest() throws EncryptedDocumentException, IOException, InterruptedException
	{
		HomePage hp=new HomePage(driver);
		ContactsPage cp=new ContactsPage(driver);
		CreateNewContactPage cncp=new CreateNewContactPage(driver);
		ContactInfoPage cip=new ContactInfoPage(driver);

		//Read testscript data from Excel File
		String lastName = eLib.getDataFromExcelFile("Contact",4,2)+jLib.getRandomNumber();

	
		//Step 2 : Navigate to Contact Module
		hp.getContactLink().click();
		
		//Step 3 : Click on "Create Contact" Button
		cp.getCreateNewContactBtn().click();
		
		//Capture the startDate and endDate
		String startDate=jLib.getSystemDateYYYYMMDD();
		String endDate=jLib.getRequiredDateYYYYMMDD(30);
		
		//Step 4 : Enter the detail and Create New Contact
		
		cncp.getLastnameEdit().sendKeys(lastName);
		cncp.getStartDateEdit().clear(); 
		cncp.getStartDateEdit().sendKeys(startDate);
		cncp.getEndDateEdit().clear();
		cncp.getEndDateEdit().sendKeys(endDate);
		Thread.sleep(5000);
		cncp.getSaveBtn().click();
		
		//Step 5: Verify Header Message in the Expected Result
		String headerinfo = cip.getContactHeaderMsg().getText();
		if(headerinfo.contains(lastName))
		{
			System.out.println(lastName+" is created==PASS");
		}
		else
		{
			System.out.println(lastName+" is not created==FAIL");
		}
		//Step 6 : Verify the Contact last name in the Expected Result
		String actlastName = cip.getLastName().getText();
		if(actlastName.equals(lastName))
		{
			System.out.println(lastName+" information is verified==PASS");
		}
		else
		{
			System.out.println(lastName+" information is not verified==FAIL");
		}
		//Step 7 : Verify the Start date in the Expected Result
		String actStartDate=cip.getStartDateInfo().getText();
		if(actStartDate.equals(startDate))
		{
			System.out.println(startDate+" information is verified==PASS");
		}
		else
		{
			System.out.println(startDate+" information is not verified==FAIL");
		}
		//Step 8 : Verify the End Date in Expected Result
		String actEndDate=cip.getEndDateInfo().getText();
		if(actEndDate.equals(endDate))
		{
			System.out.println(endDate+" information is verified==PASS");
		}
		else
		{
			System.out.println(endDate+" information is not verified==FAIL");
		}	
	}
}
