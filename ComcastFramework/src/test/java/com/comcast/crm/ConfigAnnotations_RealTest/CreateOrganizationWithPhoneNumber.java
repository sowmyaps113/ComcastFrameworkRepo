
package com.comcast.crm.ConfigAnnotations_RealTest;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
import com.crm.generic.BaseUtility.BaseClass;

public class CreateOrganizationWithPhoneNumber extends BaseClass {

	@Test
	public void createOrganizationWithPhoneNumber() throws InterruptedException, IOException {
	
		HomePage hp=new HomePage(driver);
		OrganizationsPage op=new OrganizationsPage(driver);
		CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		
		//Read testscript data from Excel File
		FileInputStream fis1=new FileInputStream("./testdata/testScriptdata.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.getSheet("Organization");
		Row row=sh.getRow(7);
		String orgName = eLib.getDataFromExcelFile("Organization", 7, 2)+jLib.getRandomNumber();
		String phoneNumber = eLib.getDataFromExcelFile("Organization", 7, 3);
		
		//Step2 : Navigate to Organization Module
		hp.getOrgLink().click();
		
		//Step3 : Click on "Create Organization" Button
		op.getCreateNewOrgBtn().click();
		
		//Step4 : Enter all the details and Create New Organization
		cnop.getOrgNameEdit().sendKeys(orgName);
		cnop.getphNumEdit().sendKeys(phoneNumber);
		cnop.getSaveBtn().click();
		
		//Step5: Verify the Phone Number info
		String actPhoneNumber=oip.getPhNumdata().getText();
		if(actPhoneNumber.trim().equals(phoneNumber))
		{
			System.out.println(phoneNumber+ " information is verified==PASS");
		}
		else
		{
			System.out.println(phoneNumber+ " information is not verified==FAIL");
		}
	}
}
