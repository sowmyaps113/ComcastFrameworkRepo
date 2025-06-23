package com.comcast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {

	public WebDriver driver;

	public WebElement getCreateNewContactBtn() {
		return createNewContactBtn;
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

	@FindBy(xpath="//img[@title='Create Contact...']")
	private WebElement createNewContactBtn;
	
	@FindBy(name="search_text")
	private WebElement searchEdit;
	
	@FindBy(id="bas_searchfield")
	private WebElement searchDropdown;
	
	@FindBy(name="submit")
	private WebElement searchBtn;
	
	public ContactsPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
}
