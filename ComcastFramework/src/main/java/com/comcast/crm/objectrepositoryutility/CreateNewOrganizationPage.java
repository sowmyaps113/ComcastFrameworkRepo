package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewOrganizationPage {
	
	public WebDriver driver;
	
	@FindBy(name="accountname")
	private WebElement orgNameEdit;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(name="industry")
	private WebElement industryDropdown;
	
	@FindBy(name="accounttype")
	private WebElement typeDropdown;
	
	@FindBy(id="phone")
	private WebElement phNumEdit;
	
	public WebElement getOrgNameEdit() {
		return orgNameEdit;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public WebElement getindustryDropdown() {
		return industryDropdown;
	}
	
	public WebElement gettypeDropdown() {
		return typeDropdown;
	}
	
	public WebElement getphNumEdit() {
		return phNumEdit;
	}

	public CreateNewOrganizationPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void createOrg(String orgName)
	{
		orgNameEdit.sendKeys(orgName);
		saveBtn.click();
	}
	
	public void createOrgWithIndustry(String orgName, String industry)
	{
		orgNameEdit.sendKeys(orgName);
		Select sel=new Select(industryDropdown);
		sel.selectByVisibleText(industry);
		saveBtn.click();
	}
	 public void createOrgWithType(String orgName, String type)
	 { 
		orgNameEdit.sendKeys(orgName);
		Select sel=new Select(typeDropdown);
		sel.selectByVisibleText(type);
		saveBtn.click();
	 }
}
