package com.iftas.pageTest;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.sikuli.script.FindFailed;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.background.JavaScriptExecutor;
import com.iftas.liteners.PropertiesUtility;


public class ERRequestCreationTest extends LaunchandLoginTest {

	Actions actions;
	String refnumber;
	@Test(priority=1,description = "Launching SFMS application")
	public void LaunchingApplication() throws MalformedURLException {
		
				
		
	DesiredCapabilities capabilities = DesiredCapabilities.firefox();
	  capabilities.setPlatform(Platform.LINUX);
	  PropertiesUtility.loadApplicationProperties();
	  //config = new ConfigDataProvider(); 
	  driver = new RemoteWebDriver(new URL(PropertiesUtility.properties.getProperty("environment.url")),capabilities);
	  System.out.println("driver loaded.................ra worst");
	  
	  System.out.println(driver);
	  driver.navigate().to(PropertiesUtility.properties.getProperty("application.url"));
	 
	driver.manage().window().maximize();
	System.out.println("Application launched successfully");
	String tittle = driver.getTitle();
	System.out.println("tittle :" + tittle);
	Assert.assertEquals("Token Login", driver.getTitle());
	System.out.println("Verified Title Successfully");

	}
	

	
	  @Test(priority = 2, description = "Create Enhanced Reconciliation Request")
	  public void createERRequest() throws MalformedURLException, AWTException,
	  InterruptedException, FindFailed {
	  
	  LaunchandLoginTest.HandlingPopus();
	  
	  Thread.sleep(3000);
	  
	  driver.findElement(By.xpath("//input[@id='uaiUserId']"))
	  .sendKeys(PropertiesUtility.properties.getProperty("RequestCreator"));
	  driver.findElement(By.xpath("//input[@id='uaiUserNodeAddrs']"))
	  .sendKeys(PropertiesUtility.properties.getProperty("ERIFSC"));
	  
	  System.out.println("Entered User id and IFSC Code Successfully");
	  
	  Thread.sleep(6000);
	  
	  driver.findElement(By.id("token_signin")).click();
	  
	  System.out.println("Clicked on Sign-in Button successfully");
	  
	  Thread.sleep(4000);
	  
	  // s.click(System.getProperty("user.dir")+"/Images/Browsepfx.png"); //
	  //s.click(System.getProperty("user.dir")+"/Images/pfxfile.png"); //
	  //s.click(System.getProperty("user.dir")+"/Images/pfxfileopen.png"); //
	  
	  s.click(System.getProperty("Images/pfxpasswordfield.png"));
	  
	  s.click(System.getProperty("Images/SelectFileImage.png"));
	  
	  s.type(System.getProperty("PFXFile/RBIH0000000.pfx"));
	  
	  s.click(System.getProperty("Images/pfxpasswordfield.png"));
	  
	  s.type("pfxfile123");
	  
	  s.click(System.getProperty("Images/Okbutton_pfxwindow.png"));
	  
	  Thread.sleep(10000);
	  
	  String homePage = driver.findElement(By.
	  xpath("//p[contains(text(),'Financial and Non Financial Messages')]"))
	  .getText(); String homepage1 = "Financial and Non Financial Messages";
	  assertEquals(homepage1, homePage);
	  
	  actions = new Actions(driver); Thread.sleep(5000);
	  
	  WebElement message = driver .findElement(By.
	  xpath("//li[@class='active has-sub']/a/span[contains(text(),'Others')]"));
	  actions.moveToElement(message).build().perform();
	  
	  actions.moveToElement(driver .findElement(By.
	  cssSelector("#othersMenu > li:nth-child(7) > a:nth-child(2) > span:nth-child(1)"
	  ))) .click().build().perform();
	  
	  actions.moveToElement(driver.findElement(By
	  .xpath("//li[@class='active has-sub']/child::ul/li/a/span[contains(text(),'Reconciliation Request')]"
	  ))) .click().build().perform();
	  
	  driver.findElement(By.xpath("//input[@value='singleBank']")).click();
	  
	  driver.findElement(By.xpath("//input[@id='reconIFSC']")).sendKeys(
	  "IFDK0000001");
	  
	  Select s1 = new Select(driver.findElement(By.cssSelector("#msgTypeSelect")));
	  s1.selectByVisibleText("RTGS");
	  
	  Calendar calendar = Calendar.getInstance(TimeZone.getDefault()); 
	  // Get Current Day as a number 
	  
	  int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
	  System.out.println("Today Int: " + todayInt + "\n"); 
	  // Integer to String Conversion 
	  String todayStr = Integer.toString(todayInt);
	  System.out.println("Today Str: " + todayStr + "\n");
	  
	  driver.findElement(By.xpath("//input[@id='reconReqFromDate']")).click();
	  
	  // driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']/tbody/tr/td[contains(text(),'ui-datepicker-today']/a[contains(text(),'"+todayStr+"']"));
	  
	  
	  // selecting current date
	  driver.findElement(By.cssSelector(".ui-state-highlight")).click();
	  
	  // Selecting current time - 1 hour 
	  DateFormat hrs = new SimpleDateFormat("kk");
	  
	  Date d = new Date(System.currentTimeMillis() - 3600 * 1000);
	  
	  String timeanHourago = hrs.format(d);
	  
	  s1 = new Select(driver.findElement(By.id("fromHours")));
	  s1.selectByVisibleText(timeanHourago);
	  
	  // Code to generate a random number between 0(inclusive) to 45(exclusing)
	  Random ran = new Random(); 
	  int num = ran.nextInt(10); 
	  String mins = Integer.toString(num);
	  
	  s1 = new Select(driver.findElement(By.id("fromMins")));
	  s1.selectByVisibleText("0" + mins);
	  
	  driver.findElement(By.xpath("//input[@id='reconReqToDate']")).click();
	  
	  //driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']/tbody/tr/td[contains(text(),'ui-datepicker-today']/a[contains(text(),'"+todayStr+"']"));
	  
	  // selecting current date
	  driver.findElement(By.cssSelector("a.ui-state-default")).click();
	  
	  s1 = new Select(driver.findElement(By.id("toHours")));
	  s1.selectByVisibleText(timeanHourago);
	  
	  //Code to generate a random number between 0(inclusive) to 45(exclusing) 
	  num = num + 15; String tomins = Integer.toString(num);
	  
	  s1 = new Select(driver.findElement(By.id("toMins")));
	  s1.selectByVisibleText(tomins);
	  
	  driver.findElement(By.cssSelector("#crtReconReqBtn")).click();
	  
	  assertEquals(
	  "Enhanced Reconciliation Request created successfully, kindly authorize the message with another user."
	  , driver.findElement(By.id("toggleSuccess")).getText());
	  
	  // System.out.println(driver.getWindowHandle()+" parent window");
	  
	  Thread.sleep(2000);
	  
	  WebElement message1 = driver .findElement(By.
	  xpath("//li[@class='active has-sub']/a/span[contains(text(),'Others')]"));
	  actions.moveToElement(message1).build().perform();
	  
	  actions.moveToElement(driver .findElement(By.
	  cssSelector("#othersMenu > li:nth-child(7) > a:nth-child(2) > span:nth-child(1)"
	  ))) .click().build().perform();
	  
	  actions.moveToElement(driver.findElement(By.xpath(
	  "//li[@class='active has-sub']/child::ul/li/a/span[contains(text(),'Reconciliation Request Action Listing')]"
	  ))) .click().build().perform();
	  
	  Thread.sleep(10000);
	  
	  s.click(System.getProperty("/Images/security check box.png"));
	  
	  s.click(System.getProperty("/Images/Runbutton.png"));
	  
	  driver.findElement(By.xpath(
	  "//table[@id='enhcdRcnReqListingTableId']/tbody/tr[1]")).click();
	  
	  refnumber = driver.findElement(By.xpath(
	  "//table[@id='enhcdRcnReqListingTableId']/tbody/tr[1]/td[1]")) .getText();
	  
	  System.out.println("The Generated Reference Number :"+refnumber);
	  
	  
	  LaunchandLoginTest.logOut(); 
	  
	  }
	 
	@Test(priority = 3,dependsOnMethods = {"createERRequest"}, description="Verifying the Enhanced Reconciliation Request")
	public void verifyERRequest() throws InterruptedException, FindFailed, MalformedURLException, AWTException
	{
				
		driver.findElement(By.xpath("//input[@id='uaiUserId']"))
				.sendKeys(PropertiesUtility.properties.getProperty("RequestAccorRej"));
		driver.findElement(By.xpath("//input[@id='uaiUserNodeAddrs']"))
				.sendKeys(PropertiesUtility.properties.getProperty("ERIFSC"));

		System.out.println("Entered User id and IFSC Code Successfully");

		Thread.sleep(6000);

		driver.findElement(By.id("token_signin")).click();

		System.out.println("Clicked on Sign-in Button successfully");

		Thread.sleep(4000);

		s.click(System.getProperty("Images/SelectFileImage.png"));

		s.type(System.getProperty("PFXFile/RBIH0000000.pfx"));

		s.click(System.getProperty("Images/pfxpasswordfield.png"));

		s.type("pfxfile123");

		s.click(System.getProperty("Images/Okbutton_pfxwindow.png"));

		Thread.sleep(10000);

		String homePage = driver.findElement(By.xpath("//p[contains(text(),'Financial and Non Financial Messages')]"))
				.getText();
		String homepage1 = "Financial and Non Financial Messages";
		assertEquals(homepage1, homePage);

		actions = new Actions(driver);
		Thread.sleep(5000);

		WebElement message = driver
				.findElement(By.xpath("//li[@class='active has-sub']/a/span[contains(text(),'Others')]"));
		actions.moveToElement(message).build().perform();

		actions.moveToElement(driver
				.findElement(By.cssSelector("#othersMenu > li:nth-child(7) > a:nth-child(2) > span:nth-child(1)")))
				.click().build().perform();

		actions.moveToElement(driver.findElement(By.xpath(
				"//li[@class='active has-sub']/child::ul/li/a/span[contains(text(),'Reconciliation Request Action Listing')]")))
				.click().build().perform();
		
		Thread.sleep(20000);

		s.click(System.getProperty("Images/security check box.png"));

		s.click(System.getProperty("Images/Runbutton.png"));

		//driver.findElement(By.xpath("//table[@id='enhcdRcnReqListingTableId']/tbody/tr[1]")).click();

		driver.findElement(By.xpath("//table[@id='enhcdRcnReqListingTableId']/tbody/tr/td[contains(text(),'"+refnumber+"')]")).click();
				Thread.sleep(4000);
		WebElement approvebuton=driver.findElement(By.xpath("//button[@class='ui-button ui-corner-all ui-widget' and contains(text(),'Approve')]"));
		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", approvebuton);
	
		
		Thread.sleep(10000);
		
		driver.switchTo().alert().accept();
		
		Thread.sleep(5000);
		
		assertEquals("Request approved Sucessfully",driver.findElement(By.id("statusMsg")).getText());
		
		driver.findElement(By.cssSelector("#toggleSuccess > span:nth-child(2) > button:nth-child(1)")).click();
		
		message = driver
				.findElement(By.xpath("//li[@class='active has-sub']/a/span[contains(text(),'Others')]"));
		actions.moveToElement(message).build().perform();

		actions.moveToElement(driver
				.findElement(By.cssSelector("#othersMenu > li:nth-child(7) > a:nth-child(2) > span:nth-child(1)")))
				.click().build().perform();

		actions.moveToElement(driver.findElement(By.xpath(
				"//li[@class='active has-sub']/child::ul/li/a/span[contains(text(),'Reconciliation Request Listing')]")))
				.click().build().perform();
	
		driver.findElement(By.xpath("//table[@id='enhcdRcnReqListingTableId']/tbody/tr/td[contains(text(),'"+refnumber+"')]")).click();
		
			
		LaunchandLoginTest.logOut();
		
	}
	
	
	
	@Test(priority = 4, description = "close the browser")
	public void closeBrowser()
	{
		driver.quit();
	}
}

