package com.comcast.crm.ConfigAnnotations_RealTest;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;

import com.comcast.crm.objectrepositoryutility.ContactInfoPage;
import com.comcast.crm.objectrepositoryutility.ContactsPage;
import com.comcast.crm.objectrepositoryutility.CreateNewContactPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.crm.generic.BaseUtility.BaseClass;

public class CreateContact extends BaseClass{

	//ContactsPage cp=new ContactsPage(driver);
	//CreateNewContactPage cncp=new CreateNewContactPage(driver);
	//ContactInfoPage cip=new ContactInfoPage(driver);

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
}
