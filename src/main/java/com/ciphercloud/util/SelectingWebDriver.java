/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/
package com.ciphercloud.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ciphercloud.util.DataSource;

public class SelectingWebDriver {
    private static final Logger log = LoggerFactory.getLogger(SelectingWebDriver.class);

	static String selectbrowser = null;
	public static WebDriver driver;

	public static WebDriver getInstance() {
		WebDriver driver2 = null;
		if (driver == null) {
			selectbrowser = DataSource.browser;
			if(System.getProperty("browser")!=null){
				selectbrowser=System.getProperty("browser");
			}
			if (selectbrowser.equals("firefox")) {
				if (DataSource.localhost.equals("false")) {
					try {
						driver2 = new RemoteWebDriver(new URL(
								"http://"+propFile("GridAddress")+":4444/wd/hub"),
								DesiredCapabilities.firefox());
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}

				} else if (DataSource.localhost.equals("true"))
					try {
						FirefoxProfile profile = new  FirefoxProfile();
						
						driver2 = new FirefoxDriver(new FirefoxBinary(new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe")),profile);
					} catch (Exception localException) {
						localException.printStackTrace();
						log.info(localException.getMessage());
					}
				else {
					System.out
							.println("please provide locahost as true or false");
				}

			} else if (selectbrowser.equals("iexplorer")) {
				if (DataSource.localhost.equals("false")) {
					DesiredCapabilities capability = DesiredCapabilities
							.internetExplorer();
					try {
						driver2 = new RemoteWebDriver(new URL(
								"http://"+propFile("GridAddress")+":4444/wd/hub"), capability);
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}

				} else if (DataSource.localhost.equals("true")) {
					DesiredCapabilities ieCapabilities = DesiredCapabilities
							.internetExplorer();
					ieCapabilities.setCapability("ignoreProtectedModeSettings",
							true);
					ieCapabilities.setJavascriptEnabled(true);
					System.setProperty("webdriver.ie.driver", new File(
							"lib/IEDriverServer.exe")
							.getAbsolutePath());

					driver2 = new InternetExplorerDriver(ieCapabilities);
				} else {
					System.out
							.println("please provide locahost as true or false");
				}

			} else if (selectbrowser.equals("chrome")) {
				
					if (DataSource.localhost.equals("false")) {
						DesiredCapabilities capability = DesiredCapabilities.chrome();
						ChromeOptions options = new ChromeOptions();
					    options.addArguments("--ignore-certificate-errors");
					    capability.setCapability(ChromeOptions.CAPABILITY, options);
						try {
							
						//	driver2 = new AppiumDriver(remoteAddress, desiredCapabilities)
							driver2 = new RemoteWebDriver(new URL(
									"http://"+propFile("GridAddress")+":4444/wd/hub"), capability);
						} catch (MalformedURLException e) {
							e.printStackTrace();
						}

					} else if (DataSource.localhost.equals("true")) {
						System.setProperty("webdriver.chrome.driver","./lib/chromedriver.exe");
						
						DesiredCapabilities capabilities = DesiredCapabilities.chrome();
					    ChromeOptions options = new ChromeOptions();
					    options.addArguments("test-type");
					   // capabilities.setCapability("chrome.binary","<<your chrome path>>");
					    capabilities.setCapability(ChromeOptions.CAPABILITY, options);

					    driver2 = new ChromeDriver(capabilities);
						
						//driver2 = new ChromeDriver();
						driver2.manage().deleteAllCookies();
					} else {
						log.info("please provide locahost as true or false");
					}
				}else {
					log.info("please provide proper brwoser name say  firefox, iExplorer,chrome");
				}
			 
			driver = driver2;
			return driver;
		}
		return driver;
	}

	public static String propFile(String propertiesName) {
		return ((String) DataSource.map.get(propertiesName.trim()));
	}

	public WebDriver startClient() throws IOException {
		return getInstance();
	}

	public static void stopClient() {
		driver.quit();
	}
}