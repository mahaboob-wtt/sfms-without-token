package com.iftas.pageTest;


import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.sikuli.script.FindFailed;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.iftas.liteners.PropertiesUtility;
import com.qa.util.ConfigDataProvider;



	public class IFSCDirectoryTest extends LaunchandLoginTest {
	Robot robot;
	 Actions actions;

	
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

	@Test(priority=2,description = "Verify Login")
	public void CreateTextMessage() throws InterruptedException, MalformedURLException, AWTException, FindFailed {

		Robot robot=new Robot();
		LaunchandLoginTest.HandlingPopus(); 	

		Thread.sleep(3000);
        
        driver.findElement(By.xpath("//input[@id='uaiUserId']")).sendKeys(PropertiesUtility.properties.getProperty("RequestCreator"));
		  driver.findElement(By.xpath("//input[@id='uaiUserNodeAddrs']")).sendKeys(PropertiesUtility.properties.getProperty("ERIFSC"));
		    			
		  System.out.println("Entered User id and IFSC Code Successfully");
									
		  Thread.sleep(6000);
		
		  driver.findElement(By.id("token_signin")).click();

		  System.out.println("Clicked on Sign-in Button successfully");
		 
		  Thread.sleep(4000);
		  
		//s.click(System.getProperty("user.dir")+"//Images//Browsepfx.png");
		//s.click(System.getProperty("user.dir")+"//Images//pfxfile.png");
		//s.click(System.getProperty("user.dir")+"//Images//pfxfileopen.png");
		//s.click(System.getProperty("user.dir")+"//Images//pfxpasswordfield.png");
		
	
		 s.click(System.getProperty("user.dir")+"//Images//SelectFileImage.png");
		 
		 s.type(System.getProperty("user.dir")+"//PFXFile//RBIH0000000.pfx");
		
		 s.click(System.getProperty("user.dir")+"//Images//pfxpasswordfield.png");
		 
		 s.type("pfxfile123");
		
		 s.click(System.getProperty("user.dir")+"//Images//Okbutton_pfxwindow.png");
		
		 Thread.sleep(10000);
		
			  String homePage=driver.findElement(By.xpath("//p[contains(text(),'Financial and Non Financial Messages')]")).getText();
			  String homepage1="Financial and Non Financial Messages";
			  assertEquals(homepage1,homePage);
			  
			  System.out.println("User landed on Home page");
			
	}
	
	@Test(priority=3,description="Navigating to IFSC Directory")
	public void navIfscDirectory() throws InterruptedException
	{
			 actions = new Actions(driver);
			 Thread.sleep(5000);
			  
			WebElement message=driver.findElement(By.xpath("//li[@class='active has-sub']/a/span[contains(text(),'Others')]"));
			actions.moveToElement(message).build().perform();
			
			actions.moveToElement(driver.findElement(By.cssSelector("#othersMenu > li:nth-child(1) > a:nth-child(1) > span:nth-child(1)"))).click().build().perform();
			driver.findElement(By.xpath("//div[@class='mainContent']/p[contains(text(),'IFSC Directory')]")).isDisplayed();
			
	}
	
	@Test(priority=4,description="Search IFSC")
	public void searchIFSC() throws InterruptedException
	{
			Thread.sleep(2000);
			Select searchOnDropdown=new Select(driver.findElement(By.id("searchBox")));
			
			searchOnDropdown.selectByVisibleText("IFSC");
			
			driver.findElement(By.xpath("//input[@id='textBox']")).sendKeys("IFAR0000001");
			
			driver.findElement(By.xpath("//button[@id='searchButton']")).click();
			
			Thread.sleep(3000);
			String tableResult=driver.findElement(By.xpath("//div[@id='ifscDirectoryTableId_info']")).getText();
			
			assertEquals("Showing 1 to 1 of 1 entries", tableResult,"Table has one matching entry");
			
			String IFSCinResult=driver.findElement(By.xpath("//table[@id='ifscDirectoryTableId']/tbody/tr/td")).getText();
			
			assertEquals("IFAR0000001",IFSCinResult,"IFSC displayed in search is Matched with search key");
			
			driver.findElement(By.id("resetSearch")).click();
			
			if(!(driver.findElement(By.xpath("//input[@id='textBox']")).isEnabled()))
			{
				System.out.println("Value Input field is diabled");
			}
			
			driver.findElement(By.xpath("//div[@class='showHideCol']/a[contains(text(),'Address')]")).click();
			
			try {
			driver.findElement(By.xpath("//table[@id='ifscDirectoryTableId']/thead/tr/th/div[contains(text(),'Address')]"));
			}
			catch(Exception e)
			{
				System.out.println("Address field is removed from table");
			}
			
	}
	
	@Test(priority=5,description="logout")
	public void logoutApp() throws InterruptedException, AWTException, FindFailed
	{
		Thread.sleep(2000);
		WebElement userProfile = driver.findElement(By.xpath("//div[@class='display_user']"));
	
		actions.moveToElement(userProfile).build().perform();
    	Thread.sleep(4000);
		    	
    	actions.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'Logout')]"))).click().build().perform();
    	 	
    			
    	Thread.sleep(10000);
	  
    	 s.click(System.getProperty("user.dir")+"//Images//security check box.png");
         
         
         s.click(System.getProperty("user.dir")+"//Images//Runbutton.png");
	    			 
	}
	
	
	@Test(priority=6,description="close application")
	public void closeApplication(){

		driver.quit();
		System.out.println("Closed Application on clicking Login");
			
		}

	}

