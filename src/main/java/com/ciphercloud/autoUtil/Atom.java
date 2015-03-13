/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/
package com.ciphercloud.autoUtil;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ciphercloud.autoUtil.Atom;
import com.ciphercloud.util.Asserting;
import com.ciphercloud.util.DataSource;
import com.ciphercloud.util.SelectingWebDriver;


public class Atom
{
  public WebDriver driver = SelectingWebDriver.getInstance();

  DataSource dd = new DataSource();
  public static Atom autoutil;
  LinkedHashMap<String, LinkedHashMap<String, String>> loginCredentials = null;

  public SecureRandom random = new SecureRandom();

  final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

  final Random rand = new Random();

  final Set<String> identifiers = new HashSet<String>();



  public static Atom getInstance()
  {
   // if (autoutil == null) {
      autoutil = new Atom();
  //  }
    return autoutil;
  }

  public void copyloginCredentials(LinkedHashMap<String, LinkedHashMap<String, String>> credentials)
  {
    this.loginCredentials = credentials;
  }

  public void acceptAlert()
  {
    try
    {
      WebElement alert = this.driver.switchTo().activeElement();
      alert.click();
    } catch (Exception localException) {
    }
  }

  public void acceptAlertwithEnter() {
    try {
      WebElement alert = this.driver.switchTo().activeElement();
      alert.sendKeys(new CharSequence[] { Keys.ENTER });
    } catch (Exception localException) {
    }
  }

  public void acceptAlerts() {
    try {
      Alert alert = this.driver.switchTo().alert();
      alert.accept(); } catch (Exception localException) {
    }
  }

  public void activeElement() {
    if (this.driver == null) return;
    try {
      this.driver.manage().timeouts().implicitlyWait(0L, TimeUnit.SECONDS);
      this.driver.findElement(By.xpath("//*[@id='popoup_dialogDiv']//a/span[contains(text(),'close')]")).click();
      acceptAlerts();
      this.driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
    }
    catch (Exception localException) {
    }
  }

  public void selectOptions(String valueToSelect, String path) {
    WebElement ele = null;
    List<WebElement> element = this.driver.findElements(By.xpath(path));

    if (element.size() != 0) {
      for (WebElement webElement : element) {
        if (webElement.isDisplayed()) {
          ele = webElement;
          break;
        }
      }

      for (WebElement option : new Select(ele).getOptions())
        if (option.getText().equals(valueToSelect)) {
          option.click();
          return;
        }
    }
    else {
      Asserting.verifyEquals("null", valueToSelect);
    }
  }

  public void checkbox(LinkedHashMap<String, LinkedHashMap<String, String>> credentials, String path)
  {
    Select s = new Select(this.driver.findElement(By.xpath(path)));
    for (Entry<String, String> entry2 : dataSheet(credentials, "checkbox").entrySet())
      for (WebElement option : this.driver.findElement(By.xpath(path)).findElements(By.tagName("checkbox")))
        if (((String)entry2.getValue()).equals(option.getText()))
          s.selectByVisibleText((String)entry2.getValue());
  }

  public void clickOn(String path)
  {
    Asserting.assertTrue(this.driver.findElement(By.xpath(path)).isDisplayed());
    this.driver.findElement(By.xpath(path)).click(); }

  public void clickLink(String name) {
    this.driver.findElement(By.linkText(name)).click();
  }

  public void clickButton(String path)
  {
    List<WebElement> l1 = this.driver.findElements(By.xpath(path));
    if (l1.size() != 0) {
      for (WebElement el : l1) {
        if (!(el.isDisplayed()))
          continue;
        el.click();
        return;
      }

    }
    else
      Asserting.assertTrue(false);
  }

  public void clickLoginButton(String path)
  {
    try
    {
      sleep();
      this.driver.findElement(By.xpath(path)).click();
      try
      {
        Alert alert = this.driver.switchTo().alert();
        alert.accept();
      } catch (Exception localException) {
      }
      popUpClickOK();
      clickOnAlert();
      javaScriptToKillPopUP();
      waitUntillDisplayed();
    }
    catch (Exception localException1)
    {
    }
  }

  public void javaScriptToKillPopUP()
  {
    try
    {
      ((JavascriptExecutor)this.driver).executeScript("window.onbeforeunload = function(e){};", new Object[0]); } catch (Exception localException) {
    }
  }

  public void clickDisplayedButton(String button, String path) {
    path = path.replace("CONSTANT", button);
    if (isExists(path)) {
      List<WebElement> list = this.driver.findElements(By.xpath(path));
      for (WebElement webElement : list)
        if (webElement.isDisplayed()) {
          webElement.click();
          popUpClickOK();
          waitUntillDisplayed();
          return;
        }
    }
  }

  public int countDisplayedElement(String path)
  {
    int count = 0;
    if (isExists(path))
    {
      List<WebElement> list = this.driver.findElements(By.xpath(path));
      for (WebElement webElement : list) {
        if (webElement.isDisplayed()) {
          ++count;
        }
      }

    }

    return count;
  }

  public void simpleButton(String button, String path) {
    sleep();
    if (this.driver.findElement(By.xpath(path.replace("CONSTANT", button))).isDisplayed()) {
      this.driver.findElement(By.xpath(path.replace("CONSTANT", button))).click();
      try {
        this.driver.switchTo().alert().accept();
        loading(10);
      } catch (Exception localException) {
      }
    }
    else {
      Asserting.assertEquals(null, button);
    }
  }

  public void clickButtonInTable(String headerName, String actionButton)
  {
    if ((actionButton != null) && (headerName != null))
    {
      String xpath = propFile("AEDButtonInTable").replace("CONSTANT", headerName).replace("REPLACE", actionButton);
      String xpath2 = propFile("AEDButtonInTable2").replace("CONSTANT", headerName).replace("REPLACE", actionButton);

      if (isExists(xpath)) {
        this.driver.findElement(By.xpath(xpath)).click();

        popUpClickOK();
      } else if (isExists(xpath2))
      {
        this.driver.findElement(By.xpath(xpath2)).click();
        popUpClickOK();
      } else {
        Asserting.assertEquals(null, headerName);
      }
    } else {
      if (headerName == null) {
        Asserting.assertEquals(null, headerName);
      }
      if (actionButton == null)
        Asserting.assertEquals(null, actionButton);
    }
  }

  private void popUpClickOK()
  {
    if (isExists(propFile("popupClick"))) {
      this.driver.findElement(By.xpath(propFile("popupClickOK"))).click();
      waitUntillDisplayed();
    }
  }

  public void navigateBack()
  {
    this.driver.navigate().back(); }

  public void navigateForward() {
    this.driver.navigate().forward();
  }

  public void clickButton(String button, String path)
  {
    sleep();
    String xpath = path.replace("CONSTANT", button);

    List<WebElement> l1 = this.driver.findElements(By.xpath(xpath));
    if (l1.size() != 0) {
      for (WebElement el : l1) {
     	 if(el.getText().equals(button)){
       	  System.out.println( el.getText());
           if (!(el.isDisplayed()))
             continue;
           el.click();

           return;
       	 }
         }

    }
    else
      Asserting.assertEquals(null, button);
  }


  
  
  
  
  private void clickOnAlert()
  {
    acceptAlert();
    waitUntillDisplayed();
    acceptAlertwithEnter();
    try {
      this.driver.manage().timeouts().implicitlyWait(0L, TimeUnit.SECONDS);
      this.driver.switchTo().alert();
      this.driver.findElement(By.name("Biotracker")).sendKeys(new CharSequence[] { Keys.ENTER });
      this.driver.manage().timeouts().implicitlyWait(120L, TimeUnit.SECONDS);
    }
    catch (Exception localException)
    {
    }
    this.driver.manage().timeouts().implicitlyWait(120L, TimeUnit.SECONDS); }

  public void clickButtonforPopUp(String button, String path) {
    if (this.driver.findElement(By.xpath(path.replace("CONSTANT", button))).isDisplayed()) {
      this.driver.findElement(By.xpath(path.replace("CONSTANT", button))).click();
      try {
        Alert alert = this.driver.switchTo().alert();
        alert.accept();
      }
      catch (Exception localException)
      {
      }
    }
  }

  public void clickTab(String tab, String path)
  {
    if (isExists(path.replace("CONSTANT", tab)))
      if (this.driver.findElement(By.xpath(path.replace("CONSTANT", tab))).isDisplayed()) {
        this.driver.findElement(By.xpath(path.replace("CONSTANT", tab))).click();
      } else if ((isExists("//*[@id='tabsNext']")) && (this.driver.findElement(By.xpath("//*[@id='tabsNext']")).isDisplayed())) {
        this.driver.findElement(By.xpath("//*[@id='tabsNext']")).click();
        if (this.driver.findElement(By.xpath(path.replace("CONSTANT", tab))).isDisplayed()) {
          this.driver.findElement(By.xpath(path.replace("CONSTANT", tab))).click();
        } else if ((isExists("//*[@id='tabsNext']")) && (this.driver.findElement(By.xpath("//*[@id='tabsNext']")).isDisplayed())) {
          this.driver.findElement(By.xpath("//*[@id='tabsNext']")).click();
          if (this.driver.findElement(By.xpath(path.replace("CONSTANT", tab))).isDisplayed())
            this.driver.findElement(By.xpath(path.replace("CONSTANT", tab))).click();
          else {
            Asserting.assertEquals(null, tab);
          }
        }
      }
      else
      {
        Asserting.assertEquals(null, tab);
      }
    else
      Asserting.assertEquals(null, tab);
  }

  public void checkbox(String field, String label)
  {
    String xpath = propFile("fieldset").replace("CONSTANT", field);
    String xpath2 = propFile("label_withCheckBox").replace("CONSTANT", label);
    if (!(this.driver.findElement(By.xpath(xpath + xpath2)).isSelected()))
      this.driver.findElement(By.xpath(xpath + xpath2)).click();
  }

  public void checkbox(String label) {
    String xpath2 = propFile("label_withCheckBox").replace("CONSTANT", label);
    if (isExists(xpath2)) {
      if (!(this.driver.findElement(By.xpath(xpath2)).isSelected()))
        this.driver.findElement(By.xpath(xpath2)).click();
    }
    else
      Asserting.assertEquals("null", label);
  }

  public boolean verify(String path)
  {
    waitUntillDisplayed(path);
    loading(30);
    if (isExists(path)) {
      return true;
    }
    Asserting.assertEquals(false, true);

    return false;
  }

  public void simpleclickButton(String button, String path) {
    if (this.driver.findElement(By.xpath(path.replace("CONSTANT", button))).isDisplayed())
      this.driver.findElement(By.xpath(path.replace("CONSTANT", button))).click();
  }



  public void verifyPage(String pageTitle, String xpathid, String headerName) {
    String title = this.driver.getTitle().trim();
    String xpath = xpathid.replace("CONSTANT", headerName);
    if (!(title.equals(pageTitle)))
      Asserting.assertEquals(title, pageTitle);
    else if (!(isDisplayed(xpath)))
      Asserting.assertEquals(null, headerName);
  }



  public void loading()
  {
    for (int i = 0; i < 100; ++i) {
      sleep();
      sleep();
      sleep();
    }
  }

  public void loading(int time) {
    for (int i = 0; i < 100; ++i) {
      sleep();
      if (i == time)
        return;
    }
  }

  public void loading(String path) {
    WebDriverWait wait = new WebDriverWait(this.driver, 120L);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(path)));
  }



  



  public boolean clickOn_hyperlink(String element, String id) {
    boolean result = false;
    int x = getIndex(propFile("xpath_of_indexRow").replace("REPLACE", id), "ID");
    int count = getCount(propFile("xpath_of_tableColum").replace("CONSTANT", Integer.toString(x)).replace("REPLACE", id));
    for (int i = 1; i <= count; ++i) {
      if (element.trim().equals(this.driver.findElement(By.xpath(propFile("selectCheckBoxName").replace("CONSTANT", Integer.toString(i)).replace("TEMP", Integer.toString(x)).replace("REPLACE", id))).getText().trim())) {
        sleep();
        this.driver.findElement(By.xpath(propFile("selectCheckBoxName").replace("CONSTANT", Integer.toString(i)).replace("TEMP", Integer.toString(x)).replace("REPLACE", id))).click();
        result = true;
        break;
      }

    }

    return result;
  }

  public boolean clickOnhyperlinkin_InTab(LinkedHashMap<String, LinkedHashMap<String, String>> credentials, String primaryKey, String id, int indexOfElement)
  {
    boolean result = false;
    String xpath2 = null;
    xpath2 = propFile("xpath_of_tableColum").replace("CONSTANT", Integer.toString(indexOfElement)).replace("REPLACE", id);
    this.driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
    int count = getCount(xpath2);
    for (Entry<String, String> entry2 : dataSheet(credentials, primaryKey).entrySet()) {
      for (int i = 1; i <= count; ++i)
      {
        if (selectElementToClick(id, indexOfElement, entry2, i, propFile("selectCheckBoxName"))) {
          sleep();
          this.driver.findElement(By.xpath(propFile("selectCheckBoxName").replace("CONSTANT", Integer.toString(i)).replace("TEMP", Integer.toString(indexOfElement)).replace("REPLACE", id))).click(); result = true;
        }
      }
    }
    return result;
  }

  private LinkedHashMap<String, String> dataSheet(LinkedHashMap<String, LinkedHashMap<String, String>> credentials, String parentKey) {
    return ((LinkedHashMap<String, String>)credentials.get(parentKey.trim()));
  }

  private String dataSheet(LinkedHashMap<String, LinkedHashMap<String, String>> credentials, String parentKey, String chieldKey) {
    return ((String)((LinkedHashMap<?, ?>)credentials.get(parentKey.trim())).get(chieldKey.trim()));
  }

  public void defaultActivefiltershouldbeselected() {
    Asserting.verifyEquals(this.driver.findElement(By.xpath("//*[@id='querie1']")).getAttribute("class"), "active");
  }

  private boolean extracted(String xpath, String mouseover)
  {
    boolean exists = false;

    this.driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
    Actions builder = new Actions(this.driver);
    boolean exist = isExists(xpath);
    if (exist) {
      WebElement tagElement = this.driver.findElement(By.xpath(xpath));
      if (tagElement.isDisplayed()) {
        if (mouseover != null)
          if ((mouseover.contains("false")) || (mouseover.contains("False")))
          {
            loading(30);
            tagElement.click();
          } else {
            findReports(xpath, builder, tagElement);
          }
        else {
          findReports(xpath, builder, tagElement);
        }
        exists = true;
      }
    }
    return exists;
  }

  public void fillFields(LinkedHashMap<String, LinkedHashMap<String, String>> credentials, String path)
  {
    for (Entry<String, String> entry2 : dataSheet(credentials, "field").entrySet())
      try {
        this.driver.findElement(By.xpath(path.replace("CONSTANT", propFile((String)entry2.getKey())))).sendKeys(new CharSequence[] { (CharSequence)entry2.getValue() });
      }
      catch (Exception localException)
      {
      }
  }

  public void findLabels(LinkedHashMap<String, LinkedHashMap<String, String>> credentials, String adduserPage) {
    new java.lang.Throwable().getStackTrace()[1].getMethodName();
    try {
      for (Entry<String, String> entry : dataSheet(credentials, "label").entrySet())
        Asserting.verifyTrue(adduserPage.contains((CharSequence)dataSheet(credentials, "label").get(entry.getKey())));
    } catch (Exception localException) {
    }
  }


  
  
  
  
  private void findReports(String xpath, Actions builder, WebElement tagElement) {
    placingTheMouseOverAnElement(builder, tagElement);
    if (xpath.contains("Reports"))
      placingTheMouseOverAnElement(builder, this.driver.findElement(By.xpath(propFile("toolbarspanfor_Reports").replace("CONSTANT", "Location Reports"))));
  }

  public int getCount(String xpath)
  {
    int x = 0;
    if (isExists(xpath)) {
      List<?> list = this.driver.findElements(By.xpath(xpath));
      x = list.size();
    }
    return x; }

  private List<WebElement> getElements(String xpath) {
    List<WebElement> list = null;
    if (isExists(xpath)) {
      list = this.driver.findElements(By.xpath(xpath));
    }
    return list; }

  private int getIndex(String xpath, String element) {
    int z = 0;
    try {
      List<WebElement> list2 = getElements(xpath);
      this.driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
      for (WebElement x : list2)
      {
        if (x.getText().trim().equals(element.trim())) {
          z = list2.indexOf(x);
          ++z;
        }
      }
    } catch (Exception e) {
      Asserting.assertEquals(null, element);
    }
    return z;
  }

  public int getIndexOfElement(String id, String element)
  {
    String xpath = null;
    xpath = propFile("xpath_of_indexRow").replace("REPLACE", id);
    this.driver.manage().timeouts().implicitlyWait(120L, TimeUnit.SECONDS);
    int x = getIndex(xpath, element.trim());
    return x;
  }

  public int getIndexOfElement_InSpecific(String id, String element) {
    String xpath = null;
    xpath = propFile("special_xpath_of_indexRow").replace("REPLACE", id);
    this.driver.manage().timeouts().implicitlyWait(120L, TimeUnit.SECONDS);
    int x = getIndex(xpath, element.trim());
    return x;
  }

  public String getText(String path) {
    if (isExists(path)) {
      return this.driver.findElement(By.xpath(path)).getText();
    }
    return null;
  }

  public void hyperlink(String path)
  {
    this.driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);

    sleep();
    this.driver.findElement(By.xpath("//*[@id='moduleList']/tbody/tr[1]/td[4]/a")).click();
  }

  public boolean isElementPresent(By by)
  {
    try {
      this.driver.findElement(by);
      return true; } catch (NoSuchElementException e) {
    }
    return false;
  }

  public boolean isExists(String xpath)
  {
    loading(10);
    this.driver.manage().timeouts().implicitlyWait(0L, TimeUnit.SECONDS);
    boolean exist = this.driver.findElements(By.xpath(xpath)).size() != 0;
    this.driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
    return exist;
  }

  public void isMessageExists(String xpath, String message) {
    if (isExists(xpath.replace("CONSTANT", message)))
      Asserting.assertTrue(true);
    else
      Asserting.verifyEquals("null", message);
  }

  public boolean isDisplayed(String xpath)
  {
    this.driver.manage().timeouts().implicitlyWait(0L, TimeUnit.SECONDS);
    boolean exist = this.driver.findElement(By.xpath(xpath)).isDisplayed();
    this.driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
    return exist;
  }

  public boolean isSelected(String xpath)
  {
    this.driver.manage().timeouts().implicitlyWait(0L, TimeUnit.SECONDS);
    boolean exist = this.driver.findElement(By.xpath(xpath)).isSelected();
    this.driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
    return exist;
  }

  public boolean isTextPresent(String path, String expectedresult) {
    boolean exist = false;
    this.driver.manage().timeouts().implicitlyWait(120L, TimeUnit.SECONDS);
    loading(path);
    Asserting.assertEquals(this.driver.findElement(By.xpath(path)).getText(), expectedresult);
    if (this.driver.findElement(By.xpath(path)).getText().equals(expectedresult)) {
      exist = true;
    }
    return exist; }

  public void isTitleExist(String expectedresult) {
    this.driver.manage().timeouts().implicitlyWait(120L, TimeUnit.SECONDS);
    if (isExists("//title"))
    {
      Asserting.assertEquals(this.driver.findElement(By.xpath("//title")).getText().trim(), expectedresult);
    }
    else Asserting.assertEquals("null", propFile(expectedresult));
  }

  public void isHeaderExists(String data, String path)
  {
    if (path.contains("CONSTANT")) {
      path = path.replace("CONSTANT", data);
    }

    this.driver.manage().timeouts().implicitlyWait(120L, TimeUnit.SECONDS);
    if (isExists(path))
    {
      Asserting.assertEquals(this.driver.findElement(By.xpath(path)).getText().trim(), data);
    }
    else Asserting.assertEquals("null", data);
  }

  public boolean doTheyExists(String data, String path)
  {
    boolean result = false;

    if (path.contains("CONSTANT")) {
      path = path.replace("CONSTANT", data);
    }

    this.driver.manage().timeouts().implicitlyWait(120L, TimeUnit.SECONDS);
    if (isExists(path)) {
      List<WebElement> l1 = this.driver.findElements(By.xpath(path));
      for (WebElement el : l1) {
        if (!(el.isDisplayed()))
          continue;
        Asserting.assertEquals(el.getText().trim(), data);

        break;
      }

      result = true;
    } else {
      Asserting.assertEquals("null", data);
    }

    return result;
  }

  public void labelwithsearchOption(String labelName, String action)
  {
    String xpath = propFile("label_withbutton").replace("CONSTANT", labelName).replace("REPLACE", action);
    if (isExists(xpath))
      try {
        this.driver.findElement(By.xpath(xpath)).click();
      } catch (Exception e) {
        Asserting.assertEquals(null, labelName);
      }
    else
      Asserting.assertEquals(null, labelName);
  }

  public void labelwithsearchOption(String labelName, String action, Boolean requieredAssertion)
  {
    String xpath = propFile("label_withbutton").replace("CONSTANT", labelName).replace("REPLACE", action);
    if (isExists(xpath)) {
      this.driver.findElement(By.xpath(xpath)).click();
    }
    else if (requieredAssertion.booleanValue())
      Asserting.assertEquals(null, labelName);
    else
      Asserting.verifyEquals(null, labelName);
  }

  public void labelwithsearchOption(String labelName, String action, Boolean requieredAssertion, String addpath)
  {
    String xpath = null;
    xpath = propFile("label_withbutton").replace("CONSTANT", labelName).replace("REPLACE", action);
    if (isExists(addpath + xpath)) {
      this.driver.findElement(By.xpath(addpath + xpath)).click();
    }
    else if (requieredAssertion.booleanValue())
      Asserting.assertEquals(null, labelName);
    else
      Asserting.verifyEquals(null, labelName);
  }

  private void locate(String xpath)
  {
    if (isExists(xpath)) {
      this.driver.manage().timeouts().implicitlyWait(0L, TimeUnit.SECONDS);
      this.driver.findElement(By.xpath(xpath)).click();
      try {
        this.driver.switchTo().alert().accept();
        loading(10);
      } catch (Exception localException1) {
      }
      this.driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
    }
    else {
      try {
        this.driver.manage().timeouts().implicitlyWait(0L, TimeUnit.SECONDS);
        this.driver.findElement(By.xpath(xpath.trim())).click();
        this.driver.switchTo().alert().accept();
        loading(10);
      } catch (Exception e) {
        Asserting.assertEquals("INVALIDPATH", "VALIDPATH");
      }
      this.driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
    }
  }

  public void mouseOver(String element, String mouseover, String elementwithnospan)
  {
    String xpath = propFile("toolbar").replace("CONSTANT", element);
    if ((extracted(xpath, mouseover)) || 
      (spanMouseOver(element, mouseover, elementwithnospan))) return;
    Asserting.assertEquals(null, element);
  }

  public void placingTheMouseOverAnElement(Actions builder, WebElement tagElement)
  {
    this.driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
    Action s = builder.moveToElement(tagElement).build();

    this.driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
    try {
      Thread.currentThread();
      Thread.sleep(100L);
      s.perform();
      this.driver.manage().timeouts().implicitlyWait(120L, TimeUnit.SECONDS);
      Thread.currentThread();
      Thread.sleep(100L);
    } catch (InterruptedException e1) {
      e1.printStackTrace();
    }
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

  private String propFile(String propertiesName) {
    return ((String)DataSource.map.get(propertiesName.trim()));
  }

  public String randomString()
  {
    int length = 9;
    Random rng = new Random();
    String characters = "abcdefghijklmnopqrstuvwxyz";
    char[] text = new char[length];
    for (int i = 0; i < length; ++i)
    {
      text[i] = characters.charAt(rng.nextInt(characters.length()));
    }
    return new String(text);
  }

  public String randomStringwithNumbers()
  {
    return new BigInteger(50, this.random).toString(32);
  }

  public void replaceKeyValue(LinkedHashMap<String, LinkedHashMap<String, String>> credentials, String parentKey, String chieldKey, String replacevalueofakey)
  {
    dataSheet(credentials, parentKey, chieldKey);
    ((LinkedHashMap<String, String>)credentials.get(parentKey)).put(chieldKey, replacevalueofakey);
  }

 



 

 

  public void findTab(String tab) {
    for (int i = 0; i < 10; ++i) {
      if (this.driver.findElement(By.xpath(propFile("tabs_show").replace("CONSTANT", tab))).isDisplayed()) {
        this.driver.findElement(By.xpath(propFile("tabs_show").replace("CONSTANT", tab))).click();
        return; }
      if (this.driver.findElement(By.xpath("//*[@id='tabsNext']")).isDisplayed()) {
        this.driver.findElement(By.xpath("//*[@id='tabsNext']")).click();
      } else {
        Asserting.assertEquals("null", tab);
        return;
      }
    }
  }








 

  

  

  

 



 
  
  public void selectDate(String month, String year, String date, String path) {
    this.driver.findElement(By.xpath(path)).click();
    selectOptions(month, propFile("user_selectMonth"));
    selectOptions(year, propFile("user_selectYear"));
    this.driver.findElement(By.linkText(date)).click();
  }

  public static void main(String[] args)
  {
    new Atom().test();
  }

  public void test() {
  }

  public void selectDropdownOne_In_ShareTab(String id, String selectElement) {
    new Select(this.driver.findElement(By.id(id))).selectByVisibleText(selectElement);
  }

  public void selectDropdownwith_textfield(String labelName, String SelectingValue) {
    String path = propFile("labelvalue").replace("CONSTANT", labelName);
    verifyingDropdownPath(SelectingValue, path);
  }

  public void selectDropdownwith_textfield(String labelName, String SelectingValue, String xtrapath) {
    String path = xtrapath + propFile("labelvalue").replace("CONSTANT", labelName);
    verifyingDropdownPath(SelectingValue, path);
  }

  public void verifyingDropdownPath(String SelectingValue, String path)
  {
    verifyDropdownGeneric(SelectingValue, path);
  }

  private void verifyDropdownGeneric(String SelectingValue, String path)
  {
    ArrayList<Object> list = new ArrayList<Object>();
    if (path != null) {
      list = verifyResult(path);
      if (((Boolean)list.get(0)).booleanValue()) {
        selectRightpath(SelectingValue, list);
      }
      else
      {
        list.clear();
        list = verifyResult(path.replace("[contains(.", "[contains(text()").replace("*')]", "')]"));
        if (((Boolean)list.get(0)).booleanValue()) {
          selectRightpath(SelectingValue, list);
        }
        else
          Asserting.assertEquals(null, SelectingValue);
      }
    }
    else {
      Asserting.assertEquals("path =null", null);
    }
  }

  private void selectRightpath(String SelectingValue, ArrayList<Object> list)
  {
    try
    {
      String bestpath = (String)list.get(1);
      selectElementsfromDropDown(SelectingValue, bestpath);
    }
    catch (Exception localException)
    {
    }
  }

  public void selectElementsfromDropDown(String SelectingValue, String bestpath)
  {
    List<WebElement> element = this.driver.findElements(By.xpath(bestpath));
    for (WebElement element2 : element)
      if (element2.isDisplayed()) {
        loading(10);
        try {
          this.driver.manage().timeouts().implicitlyWait(0L, TimeUnit.SECONDS);
          new Select(element2).selectByVisibleText(SelectingValue);
          break;
        } catch (Exception e) {
          Asserting.verifyEquals("null", SelectingValue);
        }
      }
  }

  public void verifyandSelectbrand_partner(LinkedHashMap<String, LinkedHashMap<String, String>> credentials)
    throws InterruptedException
  {
    if ((!(returnResult(dataSheet(credentials, "label", "brand")))) || (!(returnResult(dataSheet(credentials, "label", "partner"))))) {
      Thread.sleep(100L);
      clickButton(dataSheet(credentials, "select", "admin"), propFile("span"));
      clickButton(dataSheet(credentials, "tabs", "tab1"), propFile("link"));
      clickButton(propFile("gmsSpecialxpath2searchBrand"));
      clickButton(dataSheet(credentials, "label", "brand"), propFile("gmsSpecialxpath2findBrand"));
      clickButton(propFile("gmsSpecialxpath2searchpartner"));
      clickButton(dataSheet(credentials, "label", "partner"), propFile("gmsSpecialxpath2findpartner"));
      clickButton(dataSheet(credentials, "button", "selectChange"), propFile("button"));
    }
  }

  public boolean returnResult(String brand)
  {
    boolean BandPExist = false;
    try
    {
      this.driver.findElement(By.xpath("//div[@id='user-wrapper']//span//div/span[contains(.,'" + brand + "')]"));

      BandPExist = true;
    } catch (NoSuchElementException e) {
      BandPExist = false;
    }

    return BandPExist;
  }

  public void selectDate(String name, String selected)
  {
    WebElement element2 = null;

    List<WebElement> element = this.driver.findElements(By.name(name));

    if (element.size() != 0) {
      for (WebElement webElement : element) {
        if (webElement.isDisplayed()) {
          element2 = webElement;
          break;
        }

      }

      for (WebElement option : new Select(element2).getOptions()) {
        if (option.getText().equals(selected)) {
          option.click();

          return;
        }

      }

    }
    else
    {
      Asserting.verifyEquals(null, name);
    }
  }

  public void dropDown(String label, String LabelValue)
  {
    String path = propFile("dropDownLabel").replace("CONSTANT", label);
   // WebElement element2 = null;
    ////label[contains(., 'How did you hear about us')]/following-sibling::*/select
    verifyDropdownGeneric(LabelValue, path);
   /* List<WebElement> element = this.driver.findElements(By.xpath(path));
    if (element.size() != 0) {
      for (WebElement webElement : element) {
        if (webElement.isDisplayed()) {
          element2 = webElement;
          break;
        }

      }

      try
      {
        new Select(element2).selectByVisibleText(LabelValue);
      }
      catch (Exception e) {
        Asserting.verifyEquals(null, LabelValue, "missing " + LabelValue);
      }

    }
    else
    {
      Asserting.verifyEquals(null, label);
    }*/
  }

  public void selectcheckBox(String label)
  {
    this.driver.findElement(By.xpath(propFile("label").replace("CONSTANT", label))).click();
  }

  public void verifyDropdownText(String label, String labelValue)
  {
    Asserting.verifyEquals(new Select(this.driver.findElement(By.xpath(propFile("labelvalue").replace("CONSTANT", label)))).getFirstSelectedOption().getText().trim(), labelValue);
  }

  public String randomString1()
  {
    StringBuilder builder = new StringBuilder();
    while (builder.toString().length() == 0) {
      int length = this.rand.nextInt(4) + 4;
      for (int i = 0; i < length; ++i)
        builder.append("ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(this.rand.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZ".length())));
      if (this.identifiers.contains(builder.toString()))
        builder = new StringBuilder();
    }
    return builder.toString();
  }

  public void selectElementInMultiModuleLayout(LinkedHashMap<String, LinkedHashMap<String, String>> credentials)
  {
    int count = 0;
    List<?> list = this.driver.findElement(By.xpath("//*[@id='moduleLayout']")).findElements(By.className("cbox"));

    if (list.size() > 0) {
      count = list.size() - 1;
    }
    for (Entry<String, String> entry2 : dataSheet(credentials, "select").entrySet())
      for (int i = 1; i < count; ++i)
        if (((String)entry2.getValue()).equals(this.driver.findElement(By.xpath(propFile("selectCheckBoxName").replace("CONSTANT", Integer.toString(i)))).getText()))
          this.driver.findElement(By.xpath(propFile("selectCheckBoxName").replace("CONSTANT", Integer.toString(i)))).click();
  }

  public boolean selectElementtoClickOn(String id, int x, Map.Entry<String, String> entry2, int i)
  {
    return ((String)entry2.getValue()).trim().equals(this.driver.findElement(By.xpath(propFile("selectCheckBoxName").replace("/a", "").replace("CONSTANT", Integer.toString(i)).replace("TEMP", Integer.toString(x)).replace("REPLACE", id))).getText().trim());
  }

  public boolean selectElementToClick(String id, int indexOfElement, Map.Entry<String, String> entry2, int i, String selectCheckBoxName)
  {
    return ((String)entry2.getValue()).trim().equals(this.driver.findElement(By.xpath(selectCheckBoxName.replace("CONSTANT", Integer.toString(i)).replace("TEMP", Integer.toString(indexOfElement)).replace("REPLACE", id))).getText().trim());
  }

  public void sendkeys(String path, String parmeters) {
    boolean result = isExists(path);
    if (result)
      this.driver.findElement(By.xpath(path)).sendKeys(new CharSequence[] { parmeters });
    else
      Asserting.assertTrue(result);
  }

  public void sendkeys(String path, String element, String parmeters)
  {
    String xpath = path.replace("CONSTANT", element);
    boolean result = isExists(xpath);
    if (result)
      this.driver.findElement(By.xpath(xpath)).sendKeys(new CharSequence[] { parmeters });
    else
      Asserting.assertTrue(result);
  }

  public void uploadFile(String label, String labelValue, String filename)
  {
    String uploadfile = propFile("upload-File").replace("CONSTANT", label).replace("REPLACE", labelValue);

    if (isExists(uploadfile)) {
      this.driver.findElement(By.xpath(uploadfile)).click();
      this.driver.findElement(By.xpath(uploadfile)).sendKeys(new CharSequence[] { filename });
      waitUntillDisplayed();
    }
  }



  public void sleep() {
    try {
      Thread.currentThread(); Thread.sleep(10L);
    }
    catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public boolean spanMouseOver(String element, String mouseover, String elementwithnospan)
  {
    try
    {
      this.driver.findElement(By.xpath(propFile("selectMoreintoolbar"))).click();
    } catch (Exception e) {
      Asserting.assertEquals(null, element, "unable to operate span mouseOver Operation");
    }

    if (elementwithnospan != null) {
      if ((elementwithnospan.contains("true")) || (elementwithnospan.contains("True"))) {
        String xpath = propFile("toolbarwithnospan").replace("CONSTANT", element.trim());
        return extracted(xpath, mouseover);
      }
      String xpath = propFile("toolbarwithspan").replace("CONSTANT", element.trim());
      return extracted(xpath, mouseover);
    }

    String xpath = propFile("toolbarwithspan").replace("CONSTANT", element.trim());
    if (!(isExists(xpath))) {
      xpath = propFile("toolbarwithnospan").replace("CONSTANT", element.trim());
    }

    return extracted(xpath, mouseover);
  }

  public void spanSelect(String element)
  {
    this.driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
    String xpath = null;

    xpath = propFile("toolbarspan").replace("CONSTANT", element.trim().replace("+", " "));
    loading(8);
    boolean exist = isExists(xpath);
    this.driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
    if (exist) {
      locate(xpath);
    } else {
      this.driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);
      xpath = propFile("toolbarspan").replace("CONSTANT", element.trim());
      loading(30);
      exist = isExists(xpath);
      if (exist) {
        locate(xpath);
      }
      else {
        this.driver.manage().timeouts().implicitlyWait(60L, TimeUnit.SECONDS);

        xpath = propFile("toolbarspan").replace("CONSTANT", element.trim() + " ");
        locate(xpath);
      }
    }
    this.driver.manage().timeouts().implicitlyWait(120L, TimeUnit.SECONDS);
    waitUntillDisplayed();
  }

  public void toscrollDown() {
    JavascriptExecutor js = (JavascriptExecutor)this.driver;
    loading(3);
    js.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));", new Object[0]);
  }

  public void maxRecords() {
    new Select(this.driver.findElement(By.xpath(propFile("xpath_of_mainForm_in_multipleForms") + propFile("selectdropdown")))).selectByVisibleText("500");
  }

  public void verifyingLabelsAndClickOnSpecificFields(String labelName, String labelValue, String addpath)
  {
    String path = propFile("labelvalue").replace("CONSTANT", labelName).replace("::*", "::" + addpath);
    if (isExists(path))
      this.driver.findElement(By.xpath(path)).click();
  }

  public void verifyingLabelsAndTextTheFields(String labelName, String labelValue)
  {
    String path = propFile("labelvalue").replace("CONSTANT", labelName);
    
    verifyingDpath(labelName, labelValue, path);
  }

  
  
  public void verifyingLabelsAndTextTheFields(String labelName, String labelValue, boolean requiredAsserting) {
    String path = propFile("labelvalue").replace("CONSTANT", labelName);
    verifyingDpath(labelName, labelValue, path, requiredAsserting);
  }

  public void verifyingLabelsAndTextTheFields(String labelName, String labelValue, String addpath) {
    String path = addpath + propFile("labelvalue").replace("CONSTANT", labelName);
    verifyingDpath(labelName, labelValue, path);
  }

  public void verifyingLabelsAndTextTheFields(String labelName, String labelValue, String addpath, boolean requiredAsserting) {
    String path = addpath + propFile("labelvalue").replace("CONSTANT", labelName);
    verifyingDpath(labelName, labelValue, path, requiredAsserting);
  }

  public boolean verifyingLabelsAndClickCheckBox(String labelName, String path) {
    String xpath = null;
    boolean result = true;
    WebElement element = null;
    if (labelName != null) {
      try {
        xpath = path.replace("::*", "::input[@type='checkbox']").replace("CONSTANT", labelName);
      } catch (Exception e) {
        result = false;
        Asserting.verifyEquals(null, labelName);
      }
      if (result) {
        element = this.driver.findElement(By.xpath(xpath));
        if (element.isSelected())
          element.click();
        else {
          try {
            element.click();
          } catch (Exception e) {
            Asserting.verifyEquals(null, labelName);
          }
        }
      }
    }
    return result;
  }

  public void verifyAndTextTheFields(String labelName, String labelValue) {
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

  private void verifyingDpath(String labelName, String labelValue, String path)
  {
	  
	  List<WebElement> elements = driver.findElements(By.xpath(path));
 	  for (WebElement webElement : elements) {
		
	}
	  
	  
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

  
  private WebElement findDisplayedElement( String xpath,String labelName) {

	    WebElement element = null;
	    List<WebElement> element2 = this.driver.findElements(By.xpath(xpath));
	    for (WebElement element3 : element2) {
	    	String RightElement =null;
	    	//System.out.println(element3.getText());
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
	Asserting.verifyEquals("null", labelName, "unable to find the label :"+ labelName) ;
	return element;
}
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
  
  
  
  
  
  private void verifyingDpath(String labelName, String labelValue, String path, boolean requiredAsserting)
  {
    ArrayList<Object> list = new ArrayList<Object>();
    list = verifyResultpath(path);
    if ((((Boolean)list.get(0)).booleanValue()) && (labelValue != null)) {
      chooseRightElement(labelValue, list);
    }
    else {
      list.clear();
      list = verifyResultpath(path.replace("contains(.,", "contains(text(),").replace("*')]", "')]"));
      if ((((Boolean)list.get(0)).booleanValue()) && (labelValue != null)) {
        chooseRightElement(labelValue, list);
      }
      else if (requiredAsserting)
        Asserting.assertEquals(null, labelName);
      else
        Asserting.verifyEquals(null, labelName);
    }
  }

  private void chooseRightElement(String labelValue, ArrayList<Object> list)
  {
    WebElement bestpath = (WebElement)list.get(1);
    passValues(labelValue, bestpath);
  }

  private void passValues(String labelValue, WebElement bestpath) {
	if(bestpath!=null){
    		
    	
	  try {
    
      bestpath.clear();

      bestpath.sendKeys(new CharSequence[] { labelValue });
    } catch (Exception e) {
      bestpath.sendKeys(new CharSequence[] { labelValue });
    }
  }
	
	
  }


  public void selectDatainTable(LinkedHashMap<String, String> credentialsforCheckbox, LinkedHashMap<String, String> credentialsfordropdown) {}




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

  private WebElement findDisplayedElement(ArrayList<Object> arrayList, String xpath) {
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
  private ArrayList<Object> verifyResult(String path)
  {
    loading(10);
    boolean result = true;
    ArrayList<Object> arrayList = new ArrayList<Object>();
    String xpath = null;
    if ((isExists(path.replace("::*", "::option"))) && (isDisplayed(path.replace("::*", "::option"))))
      xpath = path.replace("::*", "::option");
    else if ((isExists(path.replace("::*", "::select"))) && (isDisplayed(path.replace("::*", "::select"))))
      xpath = path.replace("::*", "::select");
    else if ((isExists(path + "/option")) && (isDisplayed(path + "/option")))
      xpath = path + "/option";
    else if ((isExists(path.replace("::*", "::*/select"))) && (isDisplayed(path.replace("::*", "::*/select")))) {
      xpath = path.replace("::*", "::*/select");
    }
    
    
    else if ((isExists(path)) && (isDisplayed(path)))
      xpath = path;
    else {
      result = false;
    }
    arrayList.add(Boolean.valueOf(result));
    arrayList.add(xpath);
    return arrayList;
  }

  public void waitUntillDisplayed(String path)
  {
    int count = 1000;
    waitUntillDisplay(path, count);
  }

  public void waitUntillDisplayed(String path, int count)
  {
    waitUntillDisplay(path, count);
  }

  private void waitUntillDisplay(String path, int count)
  {
    boolean test = false;

    sleep();
    if (isExists(path))
      for (int i = 0; i < count; ++i) {
        sleep();
        test = this.driver.findElement(By.xpath(path)).isDisplayed();
        if (!(test))
          return;
      }
  }

  public void waitUntillExist(String path)
  {
    boolean test = false;
    for (int i = 0; i < 1000; ++i) {
      sleep();
      test = isExists(path);
      if (!(test))
        return;
    }
  }

  public void waitUntillDisplayed()
  {
    String path = "//*[@id=\"load\"]";
    boolean test = false;
    sleep();
    if (isExists(path))
      for (int i = 0; i < 10000000; ++i) {
        sleep();
        test = this.driver.findElement(By.xpath(path)).isDisplayed();
        if (!(test))
          return;
      }
  }

  public void selectall(String id)
  {
    this.driver.manage().timeouts().implicitlyWait(0L, TimeUnit.SECONDS);
    this.driver.findElement(By.xpath(propFile("selectall_checkbox").replace("CONSTANT", id))).click();

    this.driver.manage().timeouts().implicitlyWait(120L, TimeUnit.SECONDS);
  }

  public void verifyResultMessage(String message)
  {
    try
    {
      loading(10);
      Asserting.verifyEquals(this.driver.findElement(By.xpath(propFile("sliding_message"))).getText(), message);
      loading(10);
    } catch (Exception localException) {
    }
  }

  public void assertResultMessage(String message) {
    try {
      loading(15);
      if (isExists(propFile("sliding_message"))) {
        Asserting.assertEquals(this.driver.findElement(By.xpath(propFile("sliding_message"))).getText(), message);
      }
      else {
        Asserting.assertEquals("", message);
      }
      loading(10);
    }
    catch (Exception localException)
    {
    }
  }

  public void verifytabs(LinkedHashMap<String, LinkedHashMap<String, String>> credentials, String parentKey)
  {
    for (Entry<String, String> entry2 : dataSheet(credentials, parentKey).entrySet()) {
      boolean exist = isExists(propFile("tabs_show").replace("CONSTANT", (CharSequence)entry2.getValue()));
      if (exist) {
        boolean displayed = this.driver.findElement(By.xpath(propFile("tabs_show").replace("CONSTANT", (CharSequence)entry2.getValue()))).isDisplayed();
        if (displayed) {
          Asserting.verifyEquals(this.driver.findElement(By.xpath(propFile("tabs_show").replace("CONSTANT", (CharSequence)entry2.getValue()))).getText(), entry2.getValue());
        }
        else
          Asserting.verifyEquals(null, entry2.getValue());
      }
      else {
        Asserting.verifyEquals(null, entry2.getKey());
      }
    }
  }

  public void verifyingDefaultValues_inSpecific(String labelValue, String path2)
  {
    if (isExists(path2)) {
      Asserting.verifyEquals(this.driver.findElement(By.xpath(path2)).getAttribute("value").trim(), labelValue);
    }
    else
      Asserting.verifyEquals("null", "path");
  }

  public void verifyingLabelsAndTextTheFields_inSpecific(String labelValue, String path2)
  {
    if (isExists(path2)) {
      this.driver.findElement(By.xpath(path2)).clear();

      this.driver.findElement(By.xpath(path2)).sendKeys(new CharSequence[] { labelValue });
    } else {
      Asserting.verifyEquals("null", "path");
    }
  }

  public void verifyDropDownDefaultList(String Name, String value, String path2)
  {
    String path = path2.replace("CONSTANT", Name);
    if (isExists(path))
      Asserting.verifyEquals(this.driver.findElement(By.xpath(path)).getText().trim(), value);
  }

  public void selectDropdownwith_textfield_inSpecific(String SelectingName, String SelectingValue, String path2) {
    String path = path2.replace("CONSTANT", SelectingName);
    verifyDropdownGeneric(SelectingValue, path); }

  public void checkbox_inSpecific(String labelValue, String path2) {
    if (isExists(path2))
    {
      if (labelValue.contains("Alphabets")) {
        if (this.driver.findElement(By.xpath(path2)).isSelected()) {
          this.driver.findElement(By.xpath(path2)).click();
        }
        this.driver.findElement(By.xpath(path2)).click();
      } else if (labelValue.contains("Numbers")) {
        String x = null;
        x = path2.replace("passwordCombination1", "passwordCombination2");
        if (this.driver.findElement(By.xpath(x)).isSelected()) {
          this.driver.findElement(By.xpath(x)).click();
        }
        this.driver.findElement(By.xpath(x)).click();
      }
      else if (labelValue.contains("Special Character")) {
        String x = null;
        x = path2.replace("passwordCombination1", "passwordCombination3");

        if (this.driver.findElement(By.xpath(x)).isSelected()) {
          this.driver.findElement(By.xpath(x)).click();
        }
        this.driver.findElement(By.xpath(x)).click();
      }

    }
    else
      Asserting.verifyEquals("null", "path");
  }

  public void defaultActiveDisplayElement(String xpath)
  {
    if ((!(isExists(xpath))) || (!(isDisplayed(xpath))))
      Asserting.verifyEquals("active", "");
  }

  public void uploadFileInTestDesign(String label, String filename)
  {
    String xpath = propFile("testdesign-uploadxpath").replace("CONSTANT", label);
    if (filename != null) {
      if (isExists(xpath))
        this.driver.findElement(By.xpath(xpath)).sendKeys(new CharSequence[] { filename });
      else
        Asserting.assertEquals("null", label);
    }
    else
      Asserting.assertEquals("invalidFile", filename);
  }

  public  void openBrowser(String url)
  {
    this.driver.get(url);

    this.driver.manage().window().maximize();
    this.driver.manage().timeouts().implicitlyWait(120L, TimeUnit.SECONDS);
  }

  public void closeBrowser()
  {
    autoutil = null;
    SelectingWebDriver.driver = null;
    this.driver.close();
  }

  public void closeCookies()
  {
    this.driver.manage().deleteAllCookies();
  }

  public boolean verifyDropdownTextInTestDesign(String label, String labelValue)
  {
    return new Select(this.driver.findElement(By.xpath(propFile("labelvalue").replace("CONSTANT", label)))).getFirstSelectedOption().getText().trim().equals(labelValue);
  }
  
  public void waitForTitle(String title) {
	  	sleep();
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.titleContains(title));
	}
	
  
  
  
}