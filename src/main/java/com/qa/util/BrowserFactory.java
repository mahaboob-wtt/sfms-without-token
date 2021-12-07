package com.qa.util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory {
	
	 WebDriver driver;
	
	
	public static WebDriver launchBrowser(WebDriver driver, String browserName, String url) {
		
		if(browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.chrome.driver", "./Browser/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", "./Browser/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		}
		else if(browserName.equalsIgnoreCase("IE")){
			System.setProperty("webdriver.ie.driver", "/Browser./IEDriverServer.exe");
			driver = new InternetExplorerDriver();  
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		}
		else {
			System.out.println("We do not support this browser");
		}
			
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		return driver;
			}
	
	public static void quitBrowser(WebDriver driver) {
		driver.quit();
	}
		
	}


