package com.comcast.crm.ConfigAnnotations;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.crm.generic.BaseUtility.BaseClass;

public class CreateOrganizationTest extends BaseClass{
	
	@Test
	public void createOrganizationTest()
	{
		System.out.println("Execute Create Organization and Verify");
	}
	
	@Test
	public void createOrganizationWithIndustryTest()
	{
		System.out.println("Execute Create Organization With Industry and Verify");
	}
}
