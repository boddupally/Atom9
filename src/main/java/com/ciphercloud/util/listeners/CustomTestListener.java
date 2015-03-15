package com.ciphercloud.util.listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.ScreenshotException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IInvokedMethod;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.internal.Utils;

import com.ciphercloud.util.Asserting;
import com.ciphercloud.util.SelectingWebDriver;
import com.ciphercloud.util.listeners.TestListenerAdapter;

public class CustomTestListener extends TestListenerAdapter {

    private static final Logger log = LoggerFactory.getLogger(CustomTestListener.class);

	//static Logger log =Util4Modules.log();
@Override
	public void afterInvocation(IInvokedMethod method, ITestResult result) {
	
	//log.info(method.getTestMethod()+" :afterTest Execution ");
	Reporter.setCurrentTestResult(result);
		if (method.isTestMethod()) {
			//log.info("result.getStatus());
			if(result.getStatus()==2){
				screenShot(result);
			}
			
			List<Throwable> verificationFailures = Asserting.getVerificationFailures();
			//if there are verification failures...
			if (verificationFailures.size() > 0) {
				//set the test to failed
				result.setStatus(ITestResult.FAILURE);
				//if there is an assertion failure add it to verificationFailures
				if (result.getThrowable() != null) {
					verificationFailures.add(result.getThrowable());
				}
				int size = verificationFailures.size();
				//if there's only one failure just set that
				if (size == 1) {
					result.setThrowable(verificationFailures.get(0));
					
				} else {
					//create a failure message with all failures and stack traces (except last failure)
					StringBuffer failureMessage = new StringBuffer("Multiple failures (").append(size).append("):\n\n");
					for (int i = 0; i < size-1; i++) {
						String error=null;
						failureMessage.append("Failure ").append(i+1).append(" of ").append(size).append(":\n");
						Throwable t = verificationFailures.get(i);
						String StackTrace = Utils.stackTrace(t, false)[1];
						if(StackTrace.contains(": expected [")){
							error=StackTrace.substring(StackTrace.indexOf(":")+1, StackTrace.indexOf("\n"));
							failureMessage.append(error).append("\n\n");
						}else{
							error=StackTrace.substring(StackTrace.indexOf(":")+1, StackTrace.indexOf("\n"));
							failureMessage.append(StackTrace).append("\n\n");
						}
	
						
					}
					//final failure
					Throwable last = verificationFailures.get(size-1);
					
					String temp=null;
					temp =last.getMessage();
					failureMessage.append("Failure ").append(size).append(" of ").append(size).append(":\n");
					if(!last.toString().contains(": expected [")){
						try {
							failureMessage.append(last.getMessage().subSequence(0, temp.indexOf(":")));
						} catch (Exception e) {
							log.info("null pointer in customtestLi");			
							e.printStackTrace();
						}

					}else{
						
					if(temp.contains("expected [")){
						//error=temp.substring(temp.indexOf("expected ["), temp.indexOf("[null]"));
						failureMessage.append(temp).append("\n\n");
					}else{
						failureMessage.append(temp);
					}

					}
					
					//set merged throwable
					Throwable merged = new Throwable(failureMessage.toString());
					merged.setStackTrace(last.getStackTrace());
					//merged.setStackTrace(stackTrace)
					result.setThrowable(merged);
					
				}
			}
		}
	}


	
	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult result){
		try {
			//log.info(method.getTestMethod()+" :beforeTest Execution ");
			Thread.currentThread();
			Thread.sleep(400);
			
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		//resolvingInternalIssue();
	}
	
	

	/**
	 * @param result
	 */
	public void screenShot(ITestResult result) {

		genericScreenShot(result.getName());
	}


	  private int m_count = 0;
		 
	    public void onTestFailure(ITestResult tr) {
	        log(tr.getName()+ "--Test method failed\n");
	    }
		 
	    public void onTestSkipped(ITestResult tr) {
	        log(tr.getName()+ "--Test method skipped\n");
	    }
		 
	    public void onTestSuccess(ITestResult tr) {
	        log(tr.getName()+ "--Test method success\n");
	    }
		 
	    private void log(String string) {
	        System.out.print(string);
	        if (++m_count % 40 == 0) {
		    log.info("");
	        }
	    }
	/**
	 * @param result
	 */
	public void genericScreenShot(String result) {
		//resolvingInternalIssue();
		WebDriver driver =SelectingWebDriver.getInstance();
		log.info("screenShot at customTest");
		//String s = (String) ((JavascriptExecutor) driver).executeScript("return navigator.userAgent;");
		//log.info("s);
		String fileName=null;
	//testng-results.xml

		 try {
		        
		    	fileName=result + "_"+new SimpleDateFormat("ddMMyyyyhhmmss").format(Calendar.getInstance().getTime())+".png";  
		        
		        		        File f2=  new File("./src/test/resources/Screenshot/"+fileName);
		           try {
   
		        	   FileUtils.copyFile(((TakesScreenshot)new Augmenter().augment(driver)).getScreenshotAs(OutputType.FILE), f2);
	        		   Reporter.log(" &amp;<a href='"+"ScreenShot/"+fileName+"' height='100' width='100' /&amp;>  View Screenshot :"+result+" &amp;</a&amp;>");
		        	   
		       
		           } catch (IOException e) {
		               e.printStackTrace();
		           }
		   } catch (ScreenshotException se) {
		       se.printStackTrace();
		   }
	}
	
}
