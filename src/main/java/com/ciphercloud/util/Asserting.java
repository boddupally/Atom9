
package com.ciphercloud.util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.ScreenshotException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;

import com.ciphercloud.suite.BeforeAppEx;
import com.ciphercloud.util.SelectingWebDriver;

public class Asserting {
    private static final Logger log = LoggerFactory.getLogger(Asserting.class);

	private static Map<ITestResult, List<Throwable>> verificationFailuresMap = new  LinkedHashMap<ITestResult, List<Throwable>>();

	public static void assertTrue(boolean condition) {
		Assert.assertTrue(condition);
	}

	public static void assertTrue(boolean condition, String message) {
		Assert.assertTrue(condition, message);
	}

	public static void assertFalse(boolean condition) {
		Assert.assertFalse(condition);
	}

	public static void assertFalse(boolean condition, String message) {
		Assert.assertFalse(condition, message);
	}

	public static void assertEquals(boolean actual, boolean expected) {
		Assert.assertEquals(actual, expected);
	}

	public static void assertEquals(Object actual, Object expected) {
		Assert.assertEquals(actual, expected);
	}

	public static void assertEquals(Object actual[], Object expected[]) {
		Assert.assertEquals(actual, expected);
	}

	public static void assertEquals(Object actual, Object expected,	String message) {
		Assert.assertEquals(actual, expected, message);
	}

	public static void verifyTrue(boolean condition) {
		try {
			assertTrue(condition);
		} catch (Throwable e) {
			screenShot();
			addVerificationFailure(e);
		}
	}

	public static void verifyTrue(boolean condition, String message) {
		try {
			assertTrue(condition, message);
		} catch (Throwable e) {
			screenShot(message);
			addVerificationFailure(e);
		}
	}

	public static void verifyFalse(boolean condition) {
		try {
			assertFalse(condition);
		} catch (Throwable e) {
			screenShot();
			addVerificationFailure(e);
		}
	}

	public static void verifyFalse(boolean condition, String message) {
		try {
			assertFalse(condition, message);
		} catch (Throwable e) {
			screenShot(message);
			addVerificationFailure(e);
		}
	}

	public static void verifyEquals(boolean actual, boolean expected) {
		try {
			assertEquals(actual, expected);
		} catch (Throwable e) {
			screenShot();
			addVerificationFailure(e);
		}
	}

	public static void verifyEquals(boolean actual, boolean expected,
			String message) {
		try {
			assertEquals(actual, expected);
		} catch (Throwable e) {
			screenShot(message);
			addVerificationFailure(e);
		}
	}

	public static void verifyEquals(Object actual, Object expected) {
		try {
			assertEquals(actual, expected);
		} catch (Throwable e) {
			screenShot();
			addVerificationFailure(e);
		}
	}

	public static void verifyEquals(Object actual, Object expected,
			String message) {
		try {
			assertEquals(actual, expected);
		} catch (Throwable e) {
			screenShot(message);
			addVerificationFailure(e);
		}
	}

	public static void verifyEquals(Object actual[], Object expected[]) {
		try {
			assertEquals(actual, expected);
		} catch (Throwable e) {
			screenShot();
			addVerificationFailure(e);
		}
	}

	public static void verifyEquals(Object actual[], Object expected[],
			String messag) {
		try {
			assertEquals(actual, expected);
		} catch (Throwable e) {
			screenShot(messag);
			addVerificationFailure(e);
		}
	}

	public static void fail(String message) {
		Assert.fail(message);
	}

	public static List<Throwable>  getVerificationFailures() {
		List<Throwable> verificationFailures = verificationFailuresMap.get(Reporter.getCurrentTestResult());
		return verificationFailures == null ? new ArrayList<Throwable>() : verificationFailures;
	}

	public static void addVerificationFailure(Throwable e) {
		List<Throwable> verificationFailures = getVerificationFailures();
		verificationFailuresMap.put(Reporter.getCurrentTestResult(), verificationFailures);
		verificationFailures.add(e);
	}

	public static void screenShot(String message) {
		String methodName = Reporter.getCurrentTestResult().getName();
		getScreenshot((new StringBuilder(String.valueOf(methodName))).append("_").append(message.trim().replace(" ", "")).append("_").toString());
	}

	public static void screenShot() {
		String methodName = Reporter.getCurrentTestResult().getName();
		getScreenshot(methodName);
	}

	private static void getScreenshot(String methodName) {
		WebDriver driver = SelectingWebDriver.getInstance();
		log.info("screenShot at asserting");
		String fileName = null;
		try {
			Thread.sleep(100L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			
			
			
	        
			/*String date = (new StringBuilder(String.valueOf((new SimpleDateFormat("ddMMyyyyhhmmss")).format(Calendar.getInstance().getTime())))).append(".png").toString();
			fileName = (new StringBuilder(String.valueOf(methodName))).append(date).toString();*/
			try {
				try {
					fileName=methodName + "_"+new SimpleDateFormat("ddMMyyyyhhmmss").format(Calendar.getInstance().getTime())+".png";  
			        
			        File f2=  new File("./src/test/resources/Screenshot/"+fileName);
			        
						if (f2.exists()) {
							f2.delete();
							f2.createNewFile();
						}
						FileUtils.copyFile(	(File) ((TakesScreenshot) (new Augmenter()).augment(driver)).getScreenshotAs(OutputType.FILE), f2);
						Reporter.log((new StringBuilder(" &amp;<a href='./src/test/resources/Screenshot/")).append(fileName)
								.append("' height='100' width='100' /&amp;>  View Screenshot :")
								.append(methodName).append(" &amp;</a&amp;>")
								.toString());
					
				} catch (WebDriverException webdriverexception) {
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (ScreenshotException se) {
			se.printStackTrace();
		}
	}

}