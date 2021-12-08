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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.sikuli.script.FindFailed;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.iftas.liteners.PropertiesUtility;
import com.qa.util.ConfigDataProvider;



public class AddingUserRolesTest extends LaunchandLoginTest {
	
	
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

		@Test(priority=2,description="Verify Login")
		public void loginApp() throws InterruptedException, AWTException, MalformedURLException, FindFailed
		{
			
			LaunchandLoginTest.HandlingPopus(); 	

			driver.findElement(By.xpath("//input[@id='uaiUserId']")).sendKeys(PropertiesUtility.properties.getProperty("RequestCreator"));
			  driver.findElement(By.xpath("//input[@id='uaiUserNodeAddrs']")).sendKeys(PropertiesUtility.properties.getProperty("ERIFSC"));
			    			
			  System.out.println("Entered User id and IFSC Code Successfully");
										
			  Thread.sleep(6000);
			
			  driver.findElement(By.id("token_signin")).click();

			  System.out.println("Clicked on Sign-in Button successfully");
			 
			  Thread.sleep(7000);
			  
			//s.click(System.getProperty("user.dir")+"/Images/Browsepfx.png");
			//s.click(System.getProperty("user.dir")+"/Images/pfxfile.png");
			//s.click(System.getProperty("user.dir")+"/Images//pfxfileopen.png");
			//s.click(System.getProperty("user.dir")+"//Images//pfxpasswordfield.png");
			
		
			 s.click(System.getProperty("/Images/SelectFileImage.png"));
			 
			 s.type(System.getProperty("/PFXFile/RBIH0000000.pfx"));
			
			 s.click(System.getProperty("/Images/pfxpasswordfield.png"));
			 
			 s.type("pfxfile123");
			
			 s.click(System.getProperty("/Images/Okbutton_pfxwindow.png"));
			
			 Thread.sleep(10000);
			
			             
         // System.out.println(driver.getWindowHandle()+" parent window");
			 actions=new Actions(driver);
			  Thread.sleep(8000); 
			
				  String homePage=driver.findElement(By.xpath("//p[contains(text(),'Financial and Non Financial Messages')]")).getText();
				  String homepage1="Financial and Non Financial Messages";
				  assertEquals(homepage1,homePage);
				  
				  System.out.println("User landed on Home page");
		}
		
		
		
		@Test(priority=3,description = "Create Roles Profile ")
		public void createUserGroup() throws InterruptedException, MalformedURLException, AWTException { 
			actions = new Actions(driver);
				  
				WebElement message=driver.findElement(By.xpath("//li[@class='active has-sub']/a/span[contains(text(),'User Administration')]"));
				actions.moveToElement(message).build().perform();
				
				
				
				actions.moveToElement(driver.findElement(By.cssSelector("li.active:nth-child(3) > ul:nth-child(3) > li:nth-child(2) > a:nth-child(1) > span:nth-child(1)"))).click().build().perform();
				
				
				if(driver.findElement(By.xpath("//p[contains(text(),'Roles')]")).isDisplayed())
					System.out.println("User Landed on Roles Page");
					
				//Click on Add button to create Roles group
				driver.findElement(By.id("addRoleButton")).click();
				
				//Code to generate 3 digit random number
				int x = ((int)(Math.random() * 1000000)) % 1000;
				
				String grpName="Test"+x;
				System.out.println(grpName);
				
				//Add the Login group name with generated random number to avoid duplicates
				driver.findElement(By.cssSelector("#roleDesc")).sendKeys(grpName);
				
			driver.findElement(By.cssSelector(".displayAllFunctions > li:nth-child(1) > label:nth-child(2)")).click();
			driver.findElement(By.cssSelector(".displayAllFunctions > li:nth-child(2) > label:nth-child(2)")).click();
					
				//Click Add button to create the Roles group
				driver.findElement(By.cssSelector("#addId")).click();
				
				
				
				//verification for successful creation of login group
				assertEquals("Role added/modified successfully",driver.findElement(By.cssSelector("#toggleSuccess")).getText());
							
				driver.findElement(By.cssSelector(".styled-button-anchor")).click();
				
				    	
		}
		
		@Test(priority=4)
		public void logoutApp() throws InterruptedException, AWTException, FindFailed
		{
			WebElement userProfile = driver.findElement(By.xpath("//div[@class='display_user']"));
			
			actions.moveToElement(userProfile).build().perform();
	    	
			Thread.sleep(7000);
			    	
	    	actions.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'Logout')]"))).click().build().perform();
	    	 	
	    			
	    	Thread.sleep(10000);
		  
	    	s.click(System.getProperty("/Images/security check box.png"));
            
            s.click(System.getProperty("/Images/Runbutton.png"));
		    			 
		}
		
		@Test(priority=5)
		public void closeApplication(){

			driver.quit();
			System.out.println("Closed Application on clicking Login");
				
			}

	}

