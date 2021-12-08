package com.iftas.pageTest;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.iftas.liteners.PropertiesUtility;
import com.qa.util.ConfigDataProvider;



public class VerifyLoginTest extends LaunchandLoginTest {
	
	  WebDriver driver;
      Actions action;

	
	@Test(priority=1,description = "Launching SFMS application")
	public void LaunchingApplication() throws MalformedURLException {
		// Launch website
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
		  capabilities.setPlatform(Platform.LINUX);
		  PropertiesUtility.loadApplicationProperties();
		 // config = new ConfigDataProvider(); 
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

    
	@Test(priority=2,description="Empty Credentials")
	public void loginValidations() throws MalformedURLException, AWTException, InterruptedException, FindFailed
	{
		
		LaunchandLoginTest.HandlingPopus(); 	

		Thread.sleep(5000);

		driver.findElement(By.id("token_signin")).click();

		String alert=driver.switchTo().alert().getText();
		
		assertEquals(alert,"Ifsc or User Id is not valid");
		Thread.sleep(3000);
		if(alert.equalsIgnoreCase("Ifsc or User Id is not valid"))
		{
		//s.click(System.getProperty("user.dir")+"/Images/OkButton_LoginValidationp.png");
			driver.switchTo().alert().accept();
		}
		
		Thread.sleep(5000);

	}
	
	@Test(priority=3,description="Login With Userid")
	public void loginwithUserId() throws MalformedURLException, AWTException, InterruptedException, FindFailed
	{
		
		//s.click(System.getProperty("user.dir")+"/Images/EnterUserIdfield.png");
		
		driver.findElement(By.id("uaiUserId")).sendKeys("SUSER1");
		//s.type("SUSER1");
		
		Thread.sleep(2000);
		driver.findElement(By.id("token_signin")).click();

		String alert=driver.switchTo().alert().getText();
		
		assertEquals(alert,"Ifsc or User Id is not valid");
		
		if(alert.equalsIgnoreCase("Ifsc or User Id is not valid"))
		{
			//s.click(System.getProperty("user.dir")+"/Images/OkButton_LoginValidationp.png");
			driver.switchTo().alert().accept();
		}
		Thread.sleep(5000);

		driver.findElement(By.id("uaiUserId")).clear();
		
		Thread.sleep(5000);

	}
	
	@Test(priority=4,description="With IFSC Code")
	public void loginwithIfsc() throws MalformedURLException, AWTException, InterruptedException, FindFailed
	{
		
		driver.findElement(By.id("uaiUserNodeAddrs")).sendKeys("IFDK00000SC");
		
		driver.findElement(By.id("token_signin")).click();

		String alert=driver.switchTo().alert().getText();
		
		assertEquals(alert,"Ifsc or User Id is not valid");
		
		if(alert.equalsIgnoreCase("Ifsc or User Id is not valid"))
		{
			//s.click(System.getProperty("user.dir")+"/Images/OkButton_LoginValidationp.png");
			driver.switchTo().alert().accept();
		}
		
		Thread.sleep(2000);
		
		driver.findElement(By.id("uaiUserNodeAddrs")).clear();
		
		Thread.sleep(5000);

	}
	
	@Test(priority=5,description="Login with IFSC belongs to different bank")
	public void loginwithDifferentIFSC() throws MalformedURLException, AWTException, InterruptedException, FindFailed
	{
		
		driver.findElement(By.id("uaiUserId")).sendKeys("SUSER2");
		
		driver.findElement(By.id("uaiUserNodeAddrs")).sendKeys("IFSV0000001");
		
		driver.findElement(By.id("token_signin")).click();

				
		Thread.sleep(5000);

		s.click("Images/SelectFileImage.png");
		 
		 s.type("PFXFile/RBIH0000000.pfx");
		
		 s.click("Images/pfxpasswordfield.png");
		 
		 s.type("pfxfile123");
		
		 s.click("Images/Okbutton_pfxwindow.png");
		
		 Thread.sleep(10000);
		  
		 s.click("Images/security check box.png");
         
         
         s.click("Images/Runbutton.png"); 
		  
	       
	      Thread.sleep(2000); 
	       String validation=driver.findElement(By.id("uaiUser.errors")).getText();
	       
	       assertEquals(validation,"Invalid User Id/IFSC/Password");
	       Thread.sleep(3000);
	}
	
	@Test(priority=6,description="Clearing Login Fields")
	public void clearFeilds()
	{
		
		driver.findElement(By.id("uaiUserId")).clear();
		
		driver.findElement(By.id("uaiUserNodeAddrs")).clear();
		
	}
	
	
	@Test(priority=7,description = "Verify Login")
	public void VerifyLogin() throws InterruptedException, MalformedURLException, AWTException, FindFailed {

			
					
			driver.findElement(By.id("uaiUserId")).sendKeys("SUSER1");
			 
			driver.findElement(By.id("uaiUserNodeAddrs")).sendKeys("IFDK0000001");

			driver.findElement(By.id("token_signin")).click();

			System.out.println("Clicked on Sign-in Button successfully");

			Thread.sleep(5000);
			s.click("Images/SelectFileImage.png");
			 
			 s.type("PFXFile/RBIH0000000.pfx");
			
			 s.click("Images/pfxpasswordfield.png");
			 
			 s.type("pfxfile123");
			
			 s.click("Images/Okbutton_pfxwindow.png");
			
			  
			  
			  Thread.sleep(10000);
			  
			  String homePage=driver.findElement(By.xpath("//p[contains(text(),'Financial and Non Financial Messages')]")).getText();
			  String homepage1="Financial and Non Financial Messages";
			  assertEquals(homepage1,homePage);
			  
			  System.out.println("User landed on Home page");
			  
			  Actions actions = new Actions(driver);
			 
			  WebElement userProfile = driver.findElement(By.xpath("//div[@class='display_user']"));
				
				actions.moveToElement(userProfile).build().perform();
		    	Thread.sleep(4000);
				    	
		    	actions.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'Logout')]"))).click().build().perform();
		    	 	
		    			
		    	Thread.sleep(10000);
			  
		    	s.click("Images/security check box.png");
		         
		        s.click("Images/Runbutton.png"); 

	}
	
	@Test(priority=8)
	public void closeApplication(){
		driver.quit();
		System.out.println("Closed Application on clicking Login");
			
		}

	}
