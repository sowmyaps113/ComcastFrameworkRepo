package com.comcast.crm.ParallelExecution_Distributed;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.comcast.crm.objectrepositoryutility.CreateNewOrganizationPage;
import com.comcast.crm.objectrepositoryutility.HomePage;
import com.comcast.crm.objectrepositoryutility.OrganizationInfoPage;
import com.comcast.crm.objectrepositoryutility.OrganizationsPage;
import com.crm.generic.BaseUtility.BaseClass;

public class CreateOrgTest extends BaseClass{

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
