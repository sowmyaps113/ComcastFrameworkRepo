package com.comcast.crm.ConfigAnnotations_RealTest;

import java.io.IOException;

import org.testng.annotations.Test;

import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
import com.crm.generic.BaseUtility.BaseClass;

public class CreateOrganization extends BaseClass {

	@Test
	public void createOrganizationTest() throws IOException, InterruptedException {
				
		HomePage hp=new HomePage(driver);
		OrganizationsPage op=new OrganizationsPage(driver);
		CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		
		//Read testscript data from Excel File
		String orgName = eLib.getDataFromExcelFile("Organization", 1, 2)+jLib.getRandomNumber();
				
		//Step2 : Navigate to Organization Module
		hp.getOrgLink().click();
		
		//Step3 : Click on "Create Organization" Button
		op.getCreateNewOrgBtn().click();
		
		//Step4 : Enter all the details and Create New Organization
		cnop.createOrg(orgName);
		
		//Step5: Verify Header Message in the Expected Result
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
	}
}
