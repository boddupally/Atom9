/**
 * 
 */
package com.ciphercloud.portal.scripts;

import java.util.LinkedHashMap;
import java.util.Map.Entry;
import org.openqa.selenium.WebDriver;
import com.ciphercloud.autoUtil.Atom;
import com.ciphercloud.util.DataSource;
import com.ciphercloud.util.SelectingWebDriver;



/**
 * @author bhupesh.b
 *
 */
public class Login_Implementation  {

	
	WebDriver driver =SelectingWebDriver.getInstance();
	Atom autoutil=Atom.getInstance();
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


	
	
	public void cac_SignIn(
			LinkedHashMap<String, LinkedHashMap<String, String>> credentials) {
		
		
		autoutil.openBrowser(dataSheet(credentials,"url","url"));
		autoutil.waitForTitle(dataSheet(credentials, "title", "title"));	
		for(Entry<String, String> entry : dataSheet(credentials, "field").entrySet())
		autoutil.verifyAndTextTheFields(entry.getKey(), entry.getValue());
		autoutil.clickButton(dataSheet(credentials, "button", "login"), propFile("button"));

	}

	
	
	
	public void cac_configureAD(
			LinkedHashMap<String, LinkedHashMap<String, String>> credentials) {
		autoutil.waitForTitle(dataSheet(credentials, "title", "title"));
		for(Entry<String, String> entry : dataSheet(credentials, "field").entrySet())
		autoutil.verifyingLabelsAndTextTheFields(entry.getKey(), entry.getValue());
		autoutil.clickButton(dataSheet(credentials, "button", "connectAD"), propFile("button"));
		for(Entry<String, String> entry : dataSheet(credentials, "label").entrySet())
		autoutil.verifyingLabelsAndTextTheFields(entry.getKey(), entry.getValue());
		autoutil.clickButton(dataSheet(credentials, "button", "connect"), propFile("button"));
		autoutil.waitTime(3000);

	}


	
}
