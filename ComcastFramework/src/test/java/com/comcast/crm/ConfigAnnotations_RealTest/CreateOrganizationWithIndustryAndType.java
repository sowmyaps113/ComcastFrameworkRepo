package com.comcast.crm.ConfigAnnotations_RealTest;

import java.io.IOException;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
import com.crm.generic.BaseUtility.BaseClass;

public class CreateOrganizationWithIndustryAndType extends BaseClass {
	
	@Test
	public void createOrganizationWithIndustryAndTypeTest() throws EncryptedDocumentException, IOException, InterruptedException {
	
		HomePage hp=new HomePage(driver);
		OrganizationsPage op=new OrganizationsPage(driver);
		CreateNewOrganizationPage cnop=new CreateNewOrganizationPage(driver);
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		
		//Read testscript data from Excel File
		String orgName = eLib.getDataFromExcelFile("Organization", 4, 2)+jLib.getRandomNumber();
		String industry = eLib.getDataFromExcelFile("Organization", 4, 3);
		String type = eLib.getDataFromExcelFile("Organization", 4, 4);
				
		//Step2 : Navigate to Organization Module
		hp.getOrgLink().click();
		
		//Step3 : Click on "Create Organization" Button
		op.getCreateNewOrgBtn().click();
		
		//Step4 : Enter all the details and Create New Organization
		cnop.getOrgNameEdit().sendKeys(orgName);
		WebElement wbele1 = cnop.getindustryDropdown();
		Select sel1=new Select(wbele1);
		sel1.selectByVisibleText(industry);
		WebElement wbele2 = cnop.gettypeDropdown();
		Select sel2=new Select(wbele2);
		sel2.selectByVisibleText(type);
		cnop.getSaveBtn().click();
		
		//Step5: Verify the Industry info
		String actIndustry=oip.getIndustryName().getText();
		if(actIndustry.equals(industry))
		{
			System.out.println(industry+ " information is verified==PASS");
		}
		else
		{
			System.out.println(industry+ " information is not verified==FAIL");
		}
		//Step6 : Verify the Type Info
		String actType=oip.getTypeName().getText();
		if(actType.equals(type))
		{
			System.out.println(type+ " information is verified==PASS");
		}
		else
		{
			System.out.println(industry+ " information is not verified==FAIL");
		}
	}
}
		