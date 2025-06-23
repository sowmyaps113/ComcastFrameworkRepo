package com.comcast.crm.CodingStandards;

/**
 * @author admin
 * 
 */

import org.testng.annotations.Test;

import com.crm.generic.BaseUtility.BaseClass;

public class SearchContactTest extends BaseClass {
	/**
	 * Scenario : login()==> navigateContact==>createContact()==>Verify
	 */
	
	@Test
	public void searchContactTest()
	{
		/*Step1 : Login to App*/
		LoginPage lp = new LoginPage(driver);
		lp.loginToApp("url", "username", "password");
	}
	

}
