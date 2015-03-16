/**
 * 
 */
package com.ciphercloud.portal.scripts;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.ciphercloud.driver.CipherAtom;
import com.ciphercloud.util.DataSource;
import com.ciphercloud.util.SelectingWebDriver;



/**
 * @author bhupesh.b
 *
 */
public class Login_Implementation  {

	
	WebDriver driver =SelectingWebDriver.getInstance();
	CipherAtom cloudDriver=CipherAtom.getInstance();
	DataSource dd= new DataSource();
	
	
	// for testing 
	public void signIn(LinkedHashMap<String, LinkedHashMap<String, String>> credentials){}

	
	public String propFile(String propertiesName){
		return DataSource.map.get(propertiesName.trim());
	}

	public String dataSheet(LinkedHashMap<String, LinkedHashMap<String, String>> credentials,String parentKey,String chieldKey){
		return credentials.get(parentKey.trim()).get(chieldKey.trim());
	}
	
	public LinkedHashMap<String, String> dataSheet(LinkedHashMap<String, LinkedHashMap<String, String>> credentials,String parentKey){
		return credentials.get(parentKey.trim());
	}


	
	
	public void cac_login(
			LinkedHashMap<String, LinkedHashMap<String, String>> credentials) {
		
		
		cloudDriver.openBrowser(dataSheet(credentials,"url","url"));
		cloudDriver.waitForTitle(dataSheet(credentials, "title", "title"));	
				for(Entry<String, String> entry : dataSheet(credentials, "field").entrySet())
		cloudDriver.verifyAndTextTheFields(entry.getKey(), entry.getValue());
		cloudDriver.clickButton(dataSheet(credentials, "button", "login"), propFile("button"));
		cloudDriver.waitTime(3000);
	}

	
	
	
	public void cac_configureAD(
			LinkedHashMap<String, LinkedHashMap<String, String>> credentials) {
		cloudDriver.waitForTitle(dataSheet(credentials, "title", "title"));
		cloudDriver.clickButton(dataSheet(credentials, "button", "home"), propFile("link"));
		cloudDriver.checkbox(dataSheet(credentials, "checkbox", "selectcheckbox"));
		for(Entry<String, String> entry : dataSheet(credentials, "field").entrySet())
		cloudDriver.verifyingLabelsAndTextTheFields(entry.getKey(), entry.getValue());
		cloudDriver.clickButton(dataSheet(credentials, "button", "connectAD"), propFile("button"));
		for(Entry<String, String> entry : dataSheet(credentials, "label").entrySet())
		cloudDriver.verifyingLabelsAndTextTheFields(entry.getKey(), entry.getValue());
		cloudDriver.clickButton(dataSheet(credentials, "button", "connect"), propFile("button"));
		cloudDriver.waitTime(3000);

	}

	public void cac_logout(
			LinkedHashMap<String, LinkedHashMap<String, String>> credentials) {
		cloudDriver.waitTime(200);
		cloudDriver.mouseOver(propFile("logoutXpath"));
		cloudDriver.clickButton(dataSheet(credentials, "button", "logout"), propFile("link"));
		cloudDriver.closeBrowser();
	}


	public void cac_endUserAuth(
			LinkedHashMap<String, LinkedHashMap<String, String>> credentials) {
	
		cloudDriver.clickButton("End User Authentication", propFile("link"));
		cloudDriver.waitForTitle(dataSheet(credentials, "title", "title"));
		for(Entry<String, String> entry : dataSheet(credentials, "field").entrySet())
			cloudDriver.verifyingLabelsAndTextTheFields(entry.getKey(), entry.getValue());
			cloudDriver.clickButton(dataSheet(credentials, "button", "connectAD"), propFile("button"));
			for(Entry<String, String> entry : dataSheet(credentials, "label").entrySet())
			cloudDriver.verifyingLabelsAndTextTheFields(entry.getKey(), entry.getValue());
			cloudDriver.clickButton(dataSheet(credentials, "button", "connect"), propFile("button"));
	}
	
	
	
	
	
	
}
