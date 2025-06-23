package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	
	public WebDriver driver;
	
	@FindBy(xpath="//img[@alt='Create Organization...']")
	private WebElement createNewOrgBtn;
	

	@FindBy(name="search_text")
	private WebElement searchEdit;
	
	@FindBy(id="bas_searchfield")
	private WebElement searchDropdown;
	
	@FindBy(name="submit")
	private WebElement searchBtn;
	
	public WebElement getCreateNewOrgBtn() {
		return createNewOrgBtn;
	}
	
	
	public WebElement getSearchEdit() {
		return searchEdit;
	}

	public WebElement getSearchDropdown() {
		return searchDropdown;
	}
	
	public WebElement getSearchBtn() {
		return searchBtn;
	}
	                                 
	public OrganizationsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
}
