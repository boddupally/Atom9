/**
 * 
 */
package com.ciphercloud.portal.testcases;

import java.lang.reflect.Method;
import java.util.LinkedHashMap;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ciphercloud.portal.scripts.Login_Implementation;
import com.ciphercloud.util.DataSource;

/**
 * @author bhupesh.b
 *
 */

public class Login {
	//static Logger log =Util4Modules.log();
	Login_Implementation  login = null;
	DataSource dd = new DataSource();

	public void getInstance() {
		if(login==null)
			login = new Login_Implementation();
	}


//data providers	
	@DataProvider(parallel=true)
	public Object[][] DataProvider(Method m){
		Object[][] object =null;
		object =dd.dataDrive4Iteration(m.getName());
		return object;
	}
	

	

@Test(dataProvider="DataProvider",priority=1,groups = { "login" ,"Independent"})
	public void cac_login(
			LinkedHashMap<String, LinkedHashMap<String, String>> credentials) {
	getInstance();login.cac_login(credentials);
	}


@Test(dataProvider="DataProvider",priority=1,groups = { "configAD" ,"Independent"})
public void cac_configureAD(
		LinkedHashMap<String, LinkedHashMap<String, String>> credentials) {
	getInstance();login.cac_configureAD(credentials);
}

@Test(dataProvider="DataProvider",groups = { "endAuth" ,"Independent"})
public void cac_endUserAuth(
		LinkedHashMap<String, LinkedHashMap<String, String>> credentials) {
	getInstance();login.cac_endUserAuth(credentials);
}

@Test(dataProvider="DataProvider",groups = { "logout" ,"Independent"})
public void cac_logout(
		LinkedHashMap<String, LinkedHashMap<String, String>> credentials) {
	getInstance();login.cac_logout(credentials);
}


}
