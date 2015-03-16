/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/
package com.ciphercloud.driver;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ciphercloud.driver.CipherAtom;
import com.ciphercloud.util.Asserting;
import com.ciphercloud.util.DataSource;
import com.ciphercloud.util.SelectingWebDriver;


public class CipherAtom
{
	
    private static final Logger log = LoggerFactory.getLogger(CipherAtom.class);

  public WebDriver driver = SelectingWebDriver.getInstance();

  DataSource dd = new DataSource();
  public static CipherAtom autoutil;
  LinkedHashMap<String, LinkedHashMap<String, String>> loginCredentials = null;

  public static CipherAtom getInstance()
  {
   // if (autoutil == null) {
      autoutil = new CipherAtom();
  //  }
    return autoutil;
  }

  
  public  void openBrowser(String url)
  {
    try {
		this.driver.get(url);
		log.info("opened browser with url:"+url);
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(120L, TimeUnit.SECONDS);
	} catch (Exception e) {
		log.error("unable to open Browser with url", url);		
	}
  }
	
  
  public void waitForTitle(String title) {
	  	
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.titleContains(title));
		sleep();
	}
	
  public void sleep() {
	    try {
	      Thread.currentThread(); Thread.sleep(10L);
	    }
	    catch (InterruptedException e) {
	      e.printStackTrace();
	    }
	  }
  
  private String propFile(String propertiesName) {
	    return ((String)DataSource.map.get(propertiesName.trim()));
	  }
  
  public void verifyAndTextTheFields(String labelName, String labelValue) {
	  log.info("verifying label:"+labelName+" and inserting its value  : "+labelValue);
	  
	    String xpath = propFile("label_pholder").replace("CONSTANT", labelName);

	    List<WebElement> list = this.driver.findElements(By.xpath(xpath));
	    if (list.size() != 0)
	    {
	      for (WebElement wl : list) {
	        if (wl.isDisplayed()) {
	          wl.sendKeys(new CharSequence[] { labelValue });
	        }
	      }

	    }
	    else
	      Asserting.verifyEquals("null", labelValue);
	  }
  
  public void clickButton(String button, String path)
  {

	  
    String xpath = path.replace("CONSTANT", button);

    List<WebElement> l1 = this.driver.findElements(By.xpath(xpath));
    if (l1.size() != 0) {
      for (WebElement el : l1) {
     	 if(el.getText().equals(button)){
           if (!(el.isDisplayed()))
             continue;
           el.click();
     	  log.info("clicked the button:"+button);
     	 sleep();
           return;
       	 }
         }

    }
    else
      Asserting.assertEquals(null, button);
  }
  
  
  
  
  public void verifyingLabelsAndTextTheFields(String labelName, String labelValue)
  {
	  log.info("verifying label:"+labelName+" and inserting its value  : "+labelValue);

    String path = propFile("labelvalue").replace("CONSTANT", labelName);
    
    verifyingDpath(labelName, labelValue, path);
  }
  private void verifyingDpath(String labelName, String labelValue, String path)
  {
	  
		  
    ArrayList<Object> list = new ArrayList<Object>();

    list = verifyResultpath(path, labelName);
    if ((((Boolean)list.get(0)).booleanValue()) && (labelValue != null)) {
      chooseRightElement(labelValue, list);
    }
    else
    {
      list.clear();
      list = verifyResultpath(path.replace("contains(.,", "contains(text(),").replace("*')]", "')]"));
      if ((((Boolean)list.get(0)).booleanValue()) && (labelValue != null)) {
        chooseRightElement(labelValue, list);
      }
      else
      {
        Asserting.verifyEquals(null, labelName);
      }
    }
  }
  
  private ArrayList<Object> verifyResultpath(String path)
  {
    boolean result = true;
    WebElement element = null;
    ArrayList<Object> arrayList = new ArrayList<Object>();
    String xpath = null;

    if (isExists(path.replace("::*", "::input[@type='text']")))
    {
      xpath = path.replace("::*", "::input[@type='text']");
      element = findDisplayedElement( xpath);
    }else if (isExists(path.replace("::*", "::*//input"))) {
        xpath = path.replace("::*", "::*//input");
        element = findDisplayedElement( xpath);
      }
    else if (isExists(path.replace("::*", "::input"))) {
      xpath = path.replace("::*", "::input");
      element = findDisplayedElement( xpath);
    } 
    else if (isExists(path.replace("::*", "::textarea"))) {
      xpath = path.replace("::*", "::textarea ");
      element = findDisplayedElement( xpath);
    }
    
    else if ((isExists(path)) && (isDisplayed(path))) {
      xpath = path;
    } else {
      result = false;
    }

    arrayList.add(Boolean.valueOf(result));
    arrayList.add(element);
    return arrayList; }
  
  
  private WebElement findDisplayedElement( String xpath) {

	    WebElement element = null;
	    List<WebElement> element2 = this.driver.findElements(By.xpath(xpath));
	    for (WebElement element3 : element2) {
	      if (!(element3.isDisplayed())) continue;
	      try {
	        element = element3;
	      }
	      catch (Exception localException)
	      {
	      }
	    }

	    return element;
	  }
  
  
  
  
  private void chooseRightElement(String labelValue, ArrayList<Object> list)
  {
    WebElement bestpath = (WebElement)list.get(1);
    passValues(labelValue, bestpath);
  }
  
  private ArrayList<Object> verifyResultpath(String path,String labelName )
  {
    boolean result = true;
    WebElement element = null;
    ArrayList<Object> arrayList = new ArrayList<Object>();
    String xpath = null;
    if (isExists(path.replace("::*", "::*//input"))) {
        xpath = path.replace("::*", "::*//input");
        element = findDisplayedElement( xpath,labelName );
      }
    else  if (isExists(path.replace("::*", "::input[@type='text']")))
    {
    xpath = path.replace("::*", "::input[@type='text']");
      element = findDisplayedElement( xpath,labelName );
    }
     else if (isExists(path.replace("::*", "::input"))) {
      xpath = path.replace("::*", "::input");
      element = findDisplayedElement( xpath,labelName );
    }
    else if (isExists(path.replace("::*", "::textarea"))) {
      xpath = path.replace("::*", "::textarea ");
      element = findDisplayedElement( xpath,labelName );
    }
   
    else if ((isExists(path)) && (isDisplayed(path))) {
      xpath = path;
    } else {
      result = false;
    }

    arrayList.add(Boolean.valueOf(result));
    arrayList.add(element);
    return arrayList; }
  
  public boolean isExists(String xpath)
  {
    loading(10);
    this.driver.manage().timeouts().implicitlyWait(0L, TimeUnit.SECONDS);
    boolean exist = this.driver.findElements(By.xpath(xpath)).size() != 0;
    this.driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
    return exist;
  }
  
  public void loading(int time) {
	    for (int i = 0; i < 100; ++i) {
	      sleep();
	      if (i == time)
	        return;
	    }
	  }
  
  
  private WebElement findDisplayedElement( String xpath,String labelName) {

	    WebElement element = null;
	    List<WebElement> element2 = this.driver.findElements(By.xpath(xpath));
	    for (WebElement element3 : element2) {
	    	String RightElement =null;
	    	//log.info("element3.getText());
		    List<WebElement> element4 = this.driver.findElements(By.xpath(propFile("justlabel").replace("CONSTANT", labelName)));
for (WebElement webElement : element4) {
	
	      if (!(element3.isDisplayed())) continue;
	      try {
 	  if(webElement.getText().trim().equals(labelName)){
	    		  	  element = element3;
	    		  	RightElement= webElement.getText().trim();
	    		  	  break;
	    	  }
	    	  
	      }
	      catch (Exception localException)
	      {
	      }
}

if(RightElement!=null && RightElement.equals(labelName))
	    break;
	    }
if(element!=null){
	return element;
}
else{	
	Asserting.verifyEquals("null", labelName) ;
	return element;
}
	  }
  
  
  
  
  public boolean isDisplayed(String xpath)
  {
    this.driver.manage().timeouts().implicitlyWait(0L, TimeUnit.SECONDS);
    boolean exist = this.driver.findElement(By.xpath(xpath)).isDisplayed();
    this.driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
    return exist;
  }

  
  public void waitTime(int x)
  {
    try
    {
      Thread.sleep(x);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
  
  public void checkbox(String label){
		String xpath2=propFile("label_withCheckBox").replace("CONSTANT", label);
		if(isExists(xpath2)){
			if(!driver.findElement(By.xpath(xpath2)).isSelected()){
				driver.findElement(By.xpath(xpath2)).click();
			}
		}else{
			Asserting.assertEquals("null", label);
		}
		
	}
	public void mouseOver(String xpath) {
		Actions action = new Actions(driver);
		WebElement we = driver.findElement(By.xpath(xpath));
		action.moveToElement(we).build().perform();
	}
  
  public void closeBrowser()
  {
    autoutil = null;
    SelectingWebDriver.driver = null;
    this.driver.close();
  }
  private void passValues(String labelValue, WebElement bestpath) {
		if(bestpath!=null){
	    		
	    	
		  try {
	    
	      bestpath.clear();

	      bestpath.sendKeys(new CharSequence[] { labelValue });
	      log.info("inserted the value:"+labelValue);
	    } catch (Exception e) {
	      bestpath.sendKeys(new CharSequence[] { labelValue });
	      log.info("inserted the value:"+labelValue);
	    }
	  }
   }}