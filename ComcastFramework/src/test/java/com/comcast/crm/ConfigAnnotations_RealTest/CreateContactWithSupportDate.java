
package com.comcast.crm.ConfigAnnotations_RealTest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreateNewContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.crm.generic.BaseUtility.BaseClass;

public class CreateContactWithSupportDate extends BaseClass {

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
