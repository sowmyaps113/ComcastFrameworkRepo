package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContactPage {
	
	public WebDriver driver;

	public WebElement getOrgSearchEdit() {
		return orgSearchEdit;
	}

	public WebElement getOrgSearchBtn() {
		return orgSearchBtn;
	}

	public WebElement getOrgNameLookupIcon() {
		return orgNameLookupIcon;
	}

		public WebElement getLastnameEdit() {
		return lastnameEdit;
	}

	public WebElement getOrgNameEdit() {
		return orgNameEdit;
	}

	public WebElement getStartDateEdit() {
		return startDateEdit;
	}

	public WebElement getEndDateEdit() {
		return endDateEdit;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}

	@FindBy(name="lastname")
	private WebElement lastnameEdit;
	
	@FindBy(name="account_name")
	private WebElement orgNameEdit;
	
	@FindBy(id="jscal_field_support_start_date")
	private WebElement startDateEdit;
	
	@FindBy(id="jscal_field_support_end_date")
	private WebElement endDateEdit;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(xpath="//img[@title='Select']")
	private WebElement orgNameLookupIcon;
	
	@FindBy(id="search_txt")
	private WebElement orgSearchEdit;
	
	@FindBy(name="search")
	private WebElement orgSearchBtn;
	
	public CreateNewContactPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
}
