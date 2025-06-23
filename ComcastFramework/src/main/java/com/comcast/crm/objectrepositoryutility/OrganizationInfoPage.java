package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {

	public WebDriver driver;
	
	public WebElement getPhNumdata() {
		return phNumdata;
	}

	public WebElement getOrgName() {
		return orgName;
	}

	public WebElement getHeaderMsg() {
		return headerMsg;
	}
	
	public WebElement getTypeName() {
		return typeName;
	}

	public WebElement getIndustryName() {
		return industryName;
	}
	
	@FindBy(className="dvHeaderText")
	private WebElement headerMsg;
	
	@FindBy(id="mouseArea_Organization Name")
	private WebElement orgName;
	
	@FindBy(id="dtlview_Industry")
	private WebElement industryName;
	
	@FindBy(id="dtlview_Type")
	private WebElement typeName;
	
	@FindBy(id="mouseArea_Phone")
	private WebElement phNumdata;

	public OrganizationInfoPage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
}
