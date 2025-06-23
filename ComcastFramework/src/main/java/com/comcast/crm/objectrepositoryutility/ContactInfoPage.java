package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {
	
	public WebDriver driver;
	
	public WebElement getLastName() {
		return lastName;
	}

	public WebElement getContactHeaderMsg() {
		return contactHeaderMsg;
	}

	public WebElement getOrgName() {
		return orgName;
	}

	public WebElement getStartDateInfo() {
		return startDateInfo;
	}

	public WebElement getEndDateInfo() {
		return endDateInfo;
	}

	@FindBy(id="dtlview_Last Name")
	private WebElement lastName;
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement contactHeaderMsg;
	
	@FindBy(id="mouseArea_Organization Name")
	private WebElement orgName;
	
	@FindBy(id="dtlview_Support Start Date")
	private WebElement startDateInfo;
	
	@FindBy(id="dtlview_Support End Date")
	private WebElement endDateInfo;
	
	public ContactInfoPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
}
