package com.comcast.crm.CodingStandards;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

/**
 * @author Sowmya
 * 
 * Contains Login page elements and business library like login()
 */

public class LoginPage extends WebDriverUtility{ //Rule-1 Create a separate Java class
						
	public WebDriver driver;
	//Rule-2 Object Identification  
	
	@FindBy(xpath="//input[@name='user_name']")
	private WebElement usernameEdit;

	@FindBy(name="user_password")
	private WebElement passwordEdit;
	
	@FindBy(id="submitButton")  
	private WebElement loginBtn;

	//Rule-3 Object Initialization
	public LoginPage(WebDriver driver) {
		System.out.println(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	//Rule-4 Object Encapsulation

	public WebElement getUsernameEdit() {
		return usernameEdit;
	}

	public WebElement getPasswordEdit() {
		return passwordEdit;
	}

	public WebElement getLoginBtn() {
		return loginBtn;
	}
	
/**
 * Login to application based on url, username and password arguments
 * @param url
 * @param username
 * @param password
 */
	
	//Rule-5 Provide Action
	public void loginToApp(String url, String username, String password)
	{ 	
		waitForPageToLoad(driver);
		driver.manage().window().maximize();
		driver.get(url);
		
		usernameEdit.sendKeys(username);
		passwordEdit.sendKeys(password);
		loginBtn.click();
	}

}
