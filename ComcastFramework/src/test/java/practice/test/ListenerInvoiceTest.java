package practice.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.generic.BaseUtility.BaseClass;
//@Listeners(com.comcast.crm.ListenerUtility.ListenerImplementionClass.class)
public class ListenerInvoiceTest extends BaseClass {

	@Test
	public void createInvoiceTest()
	{
		System.out.println("Execute CreateInvoiceTest");
		String actTitle=driver.getTitle();
		Assert.assertEquals(actTitle, "Login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}
	
	@Test
	public void createInvoiceWithContactTest()
	{
		System.out.println("Execute CreateInvoiceWithContactTest");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}
}
