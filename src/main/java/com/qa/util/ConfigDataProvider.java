package com.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.iftas.liteners.PropertiesUtility;





public class ConfigDataProvider {
//	Properties pro;
//	
//	
//	
//	public ConfigDataProvider() {
//		
//		
//		File src = new File("./Config/Config.properties");
//		
//		try {
//			FileInputStream fis = new FileInputStream(src);
//			
//			
//			pro = new Properties();
//			pro.load(fis);
//		} catch (Exception e) {
//		
//			System.out.println("Not able to load config file"+e.getMessage());
//		}
//		
//	}
	
	
//	public String getDataFromConfig(String keyToSearch) {
//		return pro.getProperty(keyToSearch);
//		
//	}
	
	public String getBrowser() {
		return PropertiesUtility.properties.getProperty("Browser");
		
	}
	
     public String getqaURL() {
	  return PropertiesUtility.properties.getProperty("qaURL");
	}
     
     public String getuatURL() {
   	  return PropertiesUtility.properties.getProperty("uatURL");
   	}
     
     public String getprodURL() {
   	  return PropertiesUtility.properties.getProperty("prodURL");
   	}
 	
     
	
	
}



