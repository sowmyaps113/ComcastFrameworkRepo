package practice.test;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comcast.crm.generic.fileutility.ExcelUtility;

public class GetProductInfoTest {
	
	@Test(dataProvider = "getData")
	public void getProductInfoTest(String brandName, String productName)
	{  
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://amazon.in");
		
		//Search Product
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName, Keys.ENTER);
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("window.scrollTo(0,300)");
		//Actions act=new Actions(driver);
		//act.scrollByAmount(0,1000);
		//Capture Product Info
		String x="(//span[text()='"+productName+ "']/../../../../div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/a/span/span[2]/span[2])[1]";
		String price=driver.findElement(By.xpath(x)).getText();
		System.out.println(price);
		driver.quit(); 
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{			   
		ExcelUtility eLib=new ExcelUtility();
		int rowCount=eLib.getRowCount("Product");
		
		
		Object[][] objArr=new Object[rowCount][2];
		
		for(int i=0;i<rowCount;i++)
		{
			objArr[i][0] = eLib.getDataFromExcelFile("product", i+1, 0); 
			objArr[i][1] = eLib.getDataFromExcelFile("product", i+1, 1);
		}
		return objArr;
	}
}
