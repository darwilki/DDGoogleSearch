package DDGoogleSearch.DDGoogleSearch;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Lib.ExcelDataConfig;

public class gSearchTest1 {
	
	WebDriver driver;
	
	@Test(dataProvider="GoogleSearchCriteria", priority=0)
	public void gSearchChrome(String searchtext) throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\darwilki\\Documents\\Selenium\\Downloads\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://www.google.co.uk");
		
		driver.findElement(By.xpath("//input[@name='q']")).clear();
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys(searchtext);
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys(Keys.RETURN);
		//driver.findElement(By.xpath("//form[@id='tsf']")).click();
		
		Thread.sleep(5000);
		
		System.out.println(driver.getTitle());
		Assert.assertTrue(driver.getTitle().contains(searchtext +" - Google Search"), "Failure to return Google search results");
		System.out.println("Chrome Browser - Google Search results returned successfully");
		
	}
	
	
	@Test(dataProvider="GoogleSearchCriteria", priority=1)
	public void gSearchFireFox(String searchtext) throws InterruptedException
	{
		System.setProperty("webdriver.gecko.driver","C:\\Users\\darwilki\\Documents\\Selenium\\Downloads\\Drivers\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://www.google.co.uk");
		
		driver.findElement(By.xpath("//input[@name='q']")).clear();
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys(searchtext);
		driver.findElement(By.xpath("//input[@name='q']")).sendKeys(Keys.RETURN);
		//driver.findElement(By.xpath("//form[@id='tsf']")).click();
		
		Thread.sleep(5000);
		
		System.out.println(driver.getTitle());
		Assert.assertTrue(driver.getTitle().contains(searchtext +" - Google Search"), "Failure to return Google search results");
		System.out.println("Firefox Browser - Google Search results returned successfully");
		
	}
	
	
	//@Test(dataProvider="GoogleSearchCriteria", priority=2)
	//public void gSearchInternetExplorer(String searchtext) throws InterruptedException
	//{
		//System.setProperty("webdriver.ie.driver","C:\\Users\\darwilki\\Documents\\Selenium\\Downloads\\Drivers\\IEDriverServer.exe");
		//WebDriver driver = new InternetExplorerDriver();
		//driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//driver.get("https://www.google.co.uk");
		
		//driver.findElement(By.xpath("//input[@name='q']")).clear();
		//driver.findElement(By.xpath("//input[@name='q']")).sendKeys(searchtext);
		//driver.findElement(By.xpath("//input[@name='q']")).sendKeys(Keys.RETURN);
		//driver.findElement(By.xpath("//form[@id='tsf']")).click();
		
		//Thread.sleep(5000);
		
		//System.out.println(driver.getTitle());
		//Assert.assertTrue(driver.getTitle().contains(searchtext +" - Google Search"), "Failure to return Google search results");
		//System.out.println("Internet Explorer Browser - Google Search results returned successfully");
		
	//}
	
	
	
	@AfterMethod
	public void teardown()
	{ 
			driver.close();
	} 
		
	
	@DataProvider(name="GoogleSearchCriteria")
	public Object[][] GSearchCriteria()
	{
		ExcelDataConfig config=new ExcelDataConfig("C:\\Users\\darwilki\\eclipse-workspace\\DDGoogleSearch\\Test Data\\Test Data.xlsx");
		
		int rows=config.getRowCount(0);
		
		Object[][] data=new Object[rows][1];
		
		for (int i=0; i<rows; i++)
		{
			data[i][0]=config.getData(0, i, 0);
		}
		
		return data;
	}
	

}
