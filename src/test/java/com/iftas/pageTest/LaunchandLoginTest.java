package com.iftas.pageTest;


import java.awt.AWTException;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;

public class LaunchandLoginTest {
	
	static WebDriver driver;
	static Screen s;
	static Actions actions;
	
	//@Test(description = "Handling Allow Pop-ups and Security Pop-ups")
    public static void HandlingPopus() throws MalformedURLException, AWTException, InterruptedException, FindFailed 
    {
         
          
		Thread.sleep(5000);
		s = new Screen();

		try {

			s.click(System.getProperty("user.dir") + "/Images/NewAllowplugin.png");

			s.click(System.getProperty("user.dir") + "/Images/AllowingPp.png");

			Thread.sleep(10000);

			s.click(System.getProperty("user.dir") + "/Images/security check box.png");

			s.click(System.getProperty("user.dir") + "/Images/Runbutton.png");

		} catch (FindFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Thread.sleep(10000);

		driver.findElement(By.xpath("//div[@id='redirect_admin']/a")).click();

		Thread.sleep(6000);
		// driver.findElement(By.xpath("//div[@id='redirect_password']/a")).click();
		driver.findElement(By.xpath("//div[@class='text-right']/a[@class='clickhere']")).click();

		Thread.sleep(7000);

		s.click(System.getProperty("user.dir") + "/Images/security check box.png");

		s.click(System.getProperty("user.dir") + "/Images/Runbutton.png");
            
                          
    }

    
    public static void logOut() throws MalformedURLException, InterruptedException, FindFailed {
    	
		actions = new Actions(driver);

		WebElement userProfile = driver.findElement(By.xpath("//div[@class='display_user']"));

		actions.moveToElement(userProfile).build().perform();
		Thread.sleep(4000);

		actions.moveToElement(driver.findElement(By.xpath("//a[contains(text(),'Logout')]"))).click().build().perform();

		Thread.sleep(10000);

		s.click(System.getProperty("user.dir") + "/Images/security check box.png");

		s.click(System.getProperty("user.dir") + "/Images/Runbutton.png");
	
	}
}
