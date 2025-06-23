package practice.test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ListenerSimCardActivate {
	
	@Test(retryAnalyzer =  com.comcast.crm.ListenerUtility.RetryListenerImplementationClass.class )
	public void activateSim()
	{
		System.out.println("Execute Activate Sim");
		Assert.assertEquals("", "Login");
		System.out.println("step-1");
		System.out.println("step-2");
		System.out.println("step-3");
		System.out.println("step-4");
	}
}
