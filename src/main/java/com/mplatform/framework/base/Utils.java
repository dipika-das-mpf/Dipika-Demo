package com.mplatform.framework.base;

//import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

import com.mplatform.framework.base.minsights.JSON;
import com.mplatform.framework.base.minsights.ObjectConversion;
import com.mplatform.framework.base.minsights.http_utility.HttpUtility;
import com.mplatform.framework.base.minsights.http_utility.RequestTypes;
import com.mplatform.framework.base.minsights.api.LoginOutputVO;
import com.mplatform.framework.base.minsights.api.LoginRequestVO;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.*;
import org.sikuli.api.DesktopScreenRegion;
import org.sikuli.api.MultiStateTarget;
import org.sikuli.api.ScreenRegion;
import org.sikuli.api.robot.desktop.DesktopMouse;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.mplatform.framework.base.minsights.http_utility.BuildServiceURL.buildGetRequestObject;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Utils {

	protected static WebDriver driver;
	
	public Properties objCMPProperties = new Properties();
	static CommonFunctions objCMNFunctions = new CommonFunctions();
	GlobalPaths objGLPaths = null;
	private static final DateFormat PreferredTimeFormatter2 = new SimpleDateFormat("MMddyyhhmmss");
	public static String sessionTimeStamp = convertToPreferredFormatForTimeStamp(System.currentTimeMillis());
	public static Robot robot;
	protected StringBuffer verificationErrors;
	protected static Logger logger;
	
	//static WebDriverBackedSelenium objWBBackedSelenium = null;
	JavascriptExecutor objJSExecutor = null;
	static String  strErrorMsg = "";
	static String strObjectXPATH = "";
	String strObjectSourceForDragDrop = "";
	String strObjectDestinationForDragDrop = "";
	public static HTMLFunctions objHTMLFunction = null;

	private static HashMap<String,Object> storeJSONObjectMap = new HashMap<String,Object>();


	public Utils(){
		
	}


	// ========================================CONSTRUCTOR FOR COMPONENT REUSABLE FUNCTIONS=================
	public Utils(WebDriver objTempWebDriver) throws Exception {
		objHTMLFunction = new HTMLFunctions(objTempWebDriver);
		objGLPaths = new GlobalPaths();
		// ==========================INITIALIZE THE WEBDRIVER OBJECT INSIDE COMPONENT REUSABLE FUNCTIONS======
		driver = objTempWebDriver;
		if (!(GlobalPaths.strBrowserType).equalsIgnoreCase("androidnative")
				&& !(GlobalPaths.strBrowserType).equalsIgnoreCase("iosnative")) {
			//objWBBackedSelenium = new WebDriverBackedSelenium(driver, "http://10.90.103.200/home.htm");
		}
		objJSExecutor = (JavascriptExecutor) driver;
	}
	
	//################################################################################################################

	// ======================================== Close Application =======================
	public Boolean closeApplication() throws Exception {
		try {
			String strWindowTitle = driver.getTitle();
			driver.close();
			
			// System.out.println("Browser window with title <i>" +
			// strWindowTitle + "</i> closed");
			objHTMLFunction.ReportPassFail("Browser window with title <i>" + strWindowTitle + "</i> closed", "true",
					"true");
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			// System.err.println("Unable to close the browser window.<BR>Error
			// message=>" + strErrorMsg);
			// System.out.println(strErrorMsg);
			
			objHTMLFunction.ReportPassFail("Unable to close the browser window.<BR>Error message=>" + strErrorMsg,
					"true", "false");
			return false;
		}
	}



	// ========================================FIND ELEMENT USING XPATH AND RETURN IT=======================
	public static WebElement findElementNReturn01(String strObjDetails) throws Exception {
		try {
			WebElement objWebElement = driver.findElement(By.xpath(strObjDetails));
			return objWebElement;
		} catch (StaleElementReferenceException objException) {
			System.out.println(objCMNFunctions.GetExceptionNDisplay(objException, true));
			return null;
		}
	}
	
	// ========================================FIND ELEMENT USING XPATH AND RETURN IT FLUENT WAIT=======================
	public static WebElement findElementNReturn(String strObjDetails) throws Exception {
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).
		            withTimeout(15, TimeUnit.SECONDS).
		            pollingEvery(500,TimeUnit.MILLISECONDS);
		    return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(strObjDetails))));
		} catch (StaleElementReferenceException objException) {
			System.out.println(objCMNFunctions.GetExceptionNDisplay(objException, true));
			return null;
		}
	}
	// ========================================FIND ELEMENT USING XPATH AND RETURN IT FLUENT WAIT CLICKABILITY=======================
	public static WebElement findClicableElementNReturn(String strObjDetails) throws Exception {
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(15, TimeUnit.SECONDS)
		            .pollingEvery(500,TimeUnit.MILLISECONDS)
		            .ignoring(NoSuchElementException.class);

			//wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(strObjDetails))));
			return wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(strObjDetails))));

		} catch (StaleElementReferenceException objException) {
			System.out.println(objCMNFunctions.GetExceptionNDisplay(objException, true));
			return null;
		}
	}
	// ========================================FIND ELEMENT USING XPATH AND RETURN IT FLUENT WAIT VISIBILITY=======================
	public static WebElement findVisibilityElementNReturn(String strObjDetails) throws Exception {
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
					.withTimeout(15, TimeUnit.SECONDS)
					.pollingEvery(500,TimeUnit.MILLISECONDS)
					.ignoring(NoSuchElementException.class);


			return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(strObjDetails))));

		} catch (StaleElementReferenceException objException) {
			System.out.println(objCMNFunctions.GetExceptionNDisplay(objException, true));
			return null;
		}
	}
	// ========================================FIND MULTIPLE ELEMENTS USING XPATH AND RETURN THE COLLECTION - For Static xPath=======================
			public List<WebElement> findMultipleElementsNReturn(String strObjDetails) throws Exception {
				try {
					Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).
				            withTimeout(10, TimeUnit.SECONDS).
				            pollingEvery(500,TimeUnit.MILLISECONDS)
				            .ignoring(NoSuchElementException.class);
					return wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath(strObjDetails))));
				} catch (StaleElementReferenceException objException) {
					System.out.println(objCMNFunctions.GetExceptionNDisplay(objException, true));
					return null;
				}
			}
			
			
	// ========================================FIND MULTIPLE ELEMENTS USING XPATH AND RETURN THE COLLECTION - For dynamic xPath=======================
		public List<WebElement> findMultipleElementsNReturn_dynamic(String objectXPath) throws Exception {
			strObjectXPATH = replaceExternalDataInObject(objectXPath);
			try {
				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).
			            withTimeout(10, TimeUnit.SECONDS).
			            pollingEvery(500,TimeUnit.MILLISECONDS)
			            .ignoring(NoSuchElementException.class);
				return wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath(strObjectXPATH))));
			} catch (StaleElementReferenceException objException) {
				System.out.println(objCMNFunctions.GetExceptionNDisplay(objException, true));
				return null;
			}
		}
		
		
		/*public static WebElement findElementNReturnExperimentInvisiblity(String strElementXPATH) throws Exception {
			strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
			try {
				Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
			            .withTimeout(5, TimeUnit.SECONDS)
			            .pollingEvery(1,TimeUnit.SECONDS)
			            .ignoring(NoSuchElementException.class);
			    return wait.until(ExpectedConditions.invisibilityOfElementLocated(findElemet(strElementXPATH)));
			} catch (Exception objException) {
				//System.out.println(objCMNFunctions.GetExceptionNDisplay(objException, true));
				return null;
			}
		}*/
		
	// ========================================FIND ELEMENT USING XPATH AND RETURN IT=======================
	public WebElement findElementNReturnXPATH(String strElementXPATH) throws Exception {
		strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		try {
			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).
		            withTimeout(7, TimeUnit.SECONDS).
		            pollingEvery(100,TimeUnit.MILLISECONDS)
		            .ignoring(NoSuchElementException.class);
		    return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(strObjectXPATH))));
		} catch (StaleElementReferenceException objException) {
			System.out.println(objCMNFunctions.GetExceptionNDisplay(objException, true));
			return null; //TODO return an empty list
		}
	}

	// ========================================FIND ELEMENT USING XPATH AND RETURN IT=======================
	public String getRowElementTextForValueFromTable(WebElement objWebElement, String strSearchText) throws Exception {
		String[] arrSearchText = strSearchText.split("##");
		int intExpSearchCount = 1;

		if (strSearchText.contains("##")) {
			intExpSearchCount = Integer.parseInt(arrSearchText[1]);
		}
		try {
			List<WebElement> clxnRows = objWebElement.findElements(By.tagName("tr"));

			int intDispSearchCount = 0;
			String strCurrRowText = "";
			for (WebElement eltCurrRow : clxnRows) {
				strCurrRowText = eltCurrRow.getText();
				if (strCurrRowText.contains(arrSearchText[0])) {
					intDispSearchCount = intDispSearchCount + 1;

					if (intDispSearchCount == intExpSearchCount)
						return strCurrRowText;
				}
			}
			return "";
		} catch (Exception objException) {
			System.out.println(objCMNFunctions.GetExceptionNDisplay(objException, true));
			return "";
		}
	}

	// ========================================FIND MULTIPLE ELEMENTS USING XPATH AND RETURN THE COLLECTION=======================
	public int findElementNReturnIndex(String strElementXPATH, String strObjectName, String strAttributeNValue)
			throws Exception {
		strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		WebElement objCurrElement = null;

		String[] arrPropNValue = strAttributeNValue.split("=>");
		String strDispPropValue = "";

		try {
			List<WebElement> objCLXNWebElements = driver.findElements(By.xpath(strObjectXPATH));
			for (int intElement = 0; intElement < objCLXNWebElements.size(); intElement++) {
				objCurrElement = objCLXNWebElements.get(intElement);

				if (arrPropNValue[0].equalsIgnoreCase("text")) {
					strDispPropValue = objCurrElement.getText().trim();
				} else {
					strDispPropValue = objCurrElement.getAttribute(arrPropNValue[0].replace("ATTRIBUTE", ""));
				}
				if (strDispPropValue.contains(arrPropNValue[1]))
					return intElement;
			}
			return -1;
		} catch (Exception objException) {
			System.out.println(objCMNFunctions.GetExceptionNDisplay(objException, true));
			return -1;
		}
	}
	
	//################################################################################################################
	
	
	// ========================================IDENTIFY THE COUNT OF ELEMENTS=======================

		public int Count_No_Of_Element(String Xpath) {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

			int temp = driver.findElements(By.xpath(Xpath)).size();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return temp;
		}

	// ========================================FIND ELEMENT USING XPATH AND RETURN IT=======================
		public static Boolean launchURL(String strURL) throws Exception {
			try {
				driver.get(strURL);
				//driver.navigate().to(strURL);
			        return true;
			} catch (Exception objException) {
				strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
						objHTMLFunction.ReportPassFail(
						"Unable to launch URL=>" + strURL + " in browser.<BR>Error message=>" + strErrorMsg, "true",
						"false");
				return false;
			}
		}

	// ========================================CLICK OBJECT=================================================
		public static Boolean clickObject(WebElement objWebElement, String strObjectName) throws Exception {
			try {
				
				String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
				strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
				Thread.sleep(500);
				objWebElement.click();

				objHTMLFunction.ReportPassFail(
						"<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> clicked", "true",
						"true");
				return true;
			} catch (Exception objException) {
				strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);

				objException.printStackTrace();
				
				objHTMLFunction.ReportPassFail(strObjectName + " couldn't be clicked. <br> Error message=>" + strErrorMsg,
						"true", "false");
				return false;
			}
		}

	// ========================================JAVA Script CLICK OBJECT=================================================
	public static Boolean javaScriptClickObject(WebElement objWebElement, String strObjectName) throws Exception {
		try {

			String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
			strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
			Thread.sleep(500);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();",objWebElement);

			objHTMLFunction.ReportPassFail(
					"<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> clicked", "true",
					"true");
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);

			objException.printStackTrace();

			objHTMLFunction.ReportPassFail(strObjectName + " couldn't be clicked. <br> Error message=>" + strErrorMsg,
					"true", "false");
			return false;
		}
	}

	// ========================================CLICK OBJECT=================================================
	/*public Boolean clickObjectWOException(WebElement objWebElement, String strObjectName) throws Exception {
		try {
			String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
			strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
			objWebElement.click();

			
			System.out.println(
					"<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> clicked");
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			System.err.println(strObjectName + " couldn't be clicked. <br> Error message=>" + strErrorMsg);
			
			return false;
		}
	}*/

	// ========================================CLICK OBJECT=================================================
	public static Boolean clickObject(String strElementXPATH, String strObjectName) throws Exception {
		strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		try {
			WebElement objWebElement = findClicableElementNReturn(strObjectXPATH);
			objWebElement.click();
			
			objHTMLFunction.ReportPassFail(
					"<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> clicked", "true",
					"true");
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			objHTMLFunction.ReportPassFail("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName
					+ "</i>" + " couldn't be clicked. <br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}

	// ========================================CLICK OBJECT=================================================
	public Boolean clickObject_canvas(String strElementXPATH, String strObjectName) throws Exception {
		strObjectXPATH = replaceExternalDataInObject_canvas(strElementXPATH);
		try {
			WebElement objWebElement = findElementNReturn(strObjectXPATH);
			objWebElement.click();
			
			objHTMLFunction.ReportPassFail(
					"<i title=\"" + strObjectXPATH.replace("\";", "'") + "\">" + strObjectName + "</i> clicked", "true",
					"true");
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			objHTMLFunction.ReportPassFail("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName
					+ "</i>" + " couldn't be clicked. <br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}

	// ========================================Tap Element Mobile Testing=================================================
	public Boolean tapElement(WebElement objWebElement, String strObjectName) throws Exception {
		try {
			String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
			strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
			((AppiumDriver) driver).tap(1, objWebElement, 1);
			// ((AppiumDriver)driver).tap(1, objWebElement, 1);

			// System.out.println("Element <i title=\"" +
			// strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>
			// tapped");
			objHTMLFunction.ReportPassFail(
					"Element <i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> tapped",
					"true", "true");
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			// System.err.println("Element " + strObjectName + " couldn't be
			// Tapped. <br> Error message=>" + strErrorMsg);
			
			objHTMLFunction.ReportPassFail(
					"Element " + strObjectName + " couldn't be Tapped. <br> Error message=>" + strErrorMsg, "true",
					"false");
			return false;
		}
	}

	
	   //=========================================GET APPIUM Locator============================================= 
	/*
	   public WebElement GetAppiumElement(String strLocator) { 
	   strLocator = strLocator.substring(1, strLocator.length() - 1); 
	   WebElement tempElement = null; String[] arrLocators = strLocator.split(":", 2);
	   if(arrLocators[0].equalsIgnoreCase("id")) { tempElement =
	   ((AppiumDriver)driver).findElementById(arrLocators[1].trim()); } else
	   if(arrLocators[0].equalsIgnoreCase("name")) { tempElement =
	   ((AppiumDriver)driver).findElementByName(arrLocators[1].trim()); 
	   } return
	   tempElement; }
	 */

	// ========================================TYPE VALUE IN OBJECT=================================================
	public static Boolean typeValue(WebElement objWebElement, String strObjectName, String strInputValue) throws Exception {
		try {
			String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
			strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
			(new WebDriverWait(driver, 3)).until(ExpectedConditions.elementToBeClickable(objWebElement));
			
			objWebElement.sendKeys(strInputValue);

			if (!(GlobalPaths.strBrowserType).equalsIgnoreCase("androidnative")
					&& !(GlobalPaths.strBrowserType).equalsIgnoreCase("iosnative")) {
				if (objWebElement.getAttribute("type").equalsIgnoreCase("password")) {
					strInputValue = StringUtils.repeat("*", strInputValue.length());
				}
			}

			objHTMLFunction.ReportPassFail("Value typed in element <i title=\"" + strObjectXPATH.replace("\"", "'")
					+ "\">" + strObjectName + "</i>=>" + strInputValue, "true", "true");
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			
			objHTMLFunction.ReportPassFail("Unable to type value '<B>" + strInputValue + "</B>' in element "
					+ strObjectName + "<br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}

	// ========================================TYPE VALUE IN OBJECT=================================================
	public static Boolean typeValue(String strElementXPATH, String strObjectName, String strInputValue) throws Exception {
		strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		try {
			WebElement objWebElement = findElementNReturn(strObjectXPATH);
			objWebElement.sendKeys(strInputValue);

			if (!(GlobalPaths.strBrowserType).equalsIgnoreCase("androidnative")
					&& !(GlobalPaths.strBrowserType).equalsIgnoreCase("iosnative")) {
				if (objWebElement.getAttribute("type").equalsIgnoreCase("password")) {
					strInputValue = StringUtils.repeat("*", strInputValue.length());
				}
			}

			objHTMLFunction.ReportPassFail("Value typed in element <i title=\"" + strObjectXPATH.replace("\"", "'")
					+ "\">" + strObjectName + "</i>=>" + strInputValue, "true", "true");
			
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			
			objHTMLFunction.ReportPassFail("Unable to type value '<B>" + strInputValue + "</B>' in element "
					+ "<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>"
					+ "<br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}

	// ========================================TYPE VALUE IN OBJECT=================================================
	public static Boolean clearNTypeValue(WebElement objWebElement, String strObjectName, String strInputValue) throws Exception 
	{
		try {
			(new WebDriverWait(driver, 3)).until(ExpectedConditions.elementToBeClickable(objWebElement));
			String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
			strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
			objWebElement.clear();
			objWebElement.sendKeys(strInputValue);
			objHTMLFunction.ReportPassFail("Value '" + strInputValue + "' typed in element <i title=\""
					+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> after clearing the text",
					"true", "true");
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			objHTMLFunction.ReportPassFail("Unable to clear and type value '<B>" + strInputValue + "</B>' in element "
					+ strObjectName + ".<br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}

	// ========================================clear VALUE FROM OBJECT=================================================

	public static Boolean clearValue(WebElement objWebElement, String strObjectName) throws Exception {
		try {
			String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
			strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
			objWebElement.clear();
			// objWebElement.sendKeys(strInputValue);
			objHTMLFunction.ReportPassFail("Value '" + "" + "' typed in element <i title=\""
					+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> after clearing the text",
					"true", "true");
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			objHTMLFunction.ReportPassFail("Unable to clear and type value '<B>" + "" + "</B>' in element "
					+ strObjectName + ".<br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}

	// ========================================clear VALUE FROM OBJECT=================================================

	public static Boolean clearValue(String strElementXPATH, String strObjectName) throws Exception {
		strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		try {
			WebElement objWebElement = findElementNReturn(strObjectXPATH);
			objWebElement.clear();

			// objWebElement.sendKeys(strInputValue);
			objHTMLFunction.ReportPassFail("Value '" + "" + "' typed in element <i title=\""
							+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> after clearing the text",
					"true", "true");
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			objHTMLFunction.ReportPassFail("Unable to clear and type value '<B>" + "" + "</B>' in element "
					+ strObjectName + ".<br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}

	// ========================================TYPE VALUE IN OBJECT=================================================
	public static Boolean clearNTypeValue(String strElementXPATH, String strObjectName, String strInputValue)
			throws Exception {
		strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		try {
			WebElement objWebElement = findElementNReturn(strObjectXPATH);
			objWebElement.clear();
			objWebElement.sendKeys(strInputValue);
			objHTMLFunction.ReportPassFail("Value '" + strInputValue + "' typed in element <i title=\""
					+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> after clearing the text",
					"true", "true");
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			objHTMLFunction.ReportPassFail("Unable to clear and type value '<B>" + strInputValue + "</B>' in element "
					+ "<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>"
					+ ".<br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}

	// ========================================WAITS TILL THE OBJECT IS CLICKABLE=================================================
	public Boolean waitTillElementEnabled(WebElement objWebElement, String strObjectName, int intWaitTime)
			throws Exception {
		try {
			(new WebDriverWait(driver, intWaitTime)).until(ExpectedConditions.elementToBeClickable(objWebElement));

			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			System.out.println("Error while waiting for the element" + strObjectName
					+ " to be enabled.<br> Error message=>" + strErrorMsg);
			return false;
		}
	}

	// ========================================WAITS TILL THE OBJECT IS CLICKABLE=================================================
	public static Boolean waitTillElementDisplayed(WebElement objWebElement, String strObjectName, int intWaitTime)
			throws Exception {
		try {
			(new WebDriverWait(driver, intWaitTime)).until(ExpectedConditions.visibilityOf(objWebElement));
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			System.out.println(strErrorMsg);
			return false;
		}
	}

	// ========================================WAITS TILL THE OBJECT IS CLICKABLE=================================================
	public boolean waitTillElementNotDisplayed(String strElementXPATH, String strObjectName, int intWaitTime)
			throws Exception {
		strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		int intSeconds = 0;
		WebElement objWebElement;
		while (intSeconds <= intWaitTime) {
			try {
				objWebElement = findElementNReturn(strObjectXPATH);
				if (objWebElement.getSize().height == 0)
					return true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				objWebElement = null;
			}

			if (objWebElement == null)
				return true;
			else
				intSeconds = intSeconds + 1;
		}
		return false;
	}

	// ========================================WAITS TILL THE OBJECT IS CLICKABLE=================================================
	public boolean waitTillElementNotDisplayed(WebElement webElement, String strObjectName, int intWaitTime)
			throws Exception {

		int intSeconds = 0;
		WebElement objWebElement;
		while (intSeconds <= intWaitTime) {
			try {

				if (webElement.getSize().height == 0)
					return true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
				objWebElement = null;
			}

			if (webElement == null)
				return true;
			else
				intSeconds = intSeconds + 1;
		}
		return false;
	}

	// ========================================WAITS TILL THE OBJECT IS CLICKABLE=================================================
	public Boolean waitTillElementEnabled(String strElementXPATH, String strObjectName, int intWaitTime)
			throws Exception {
		strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		try {
			WebElement objWebElement = findElementNReturn(strObjectXPATH);
			(new WebDriverWait(driver, intWaitTime)).until(ExpectedConditions.elementToBeClickable(objWebElement));

			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			// System.out.println("Error while waiting for the element" + "<i
			// title=\"" + strObjectXPATH.replace("\"", "'") + "\">" +
			// strObjectName + "</i>" + " to be enabled.<br> Error message=>" +
			// strErrorMsg);
			
			objHTMLFunction.ReportPassFail(
					"Error while waiting for the element" + "<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">"
							+ strObjectName + "</i>" + " to be enabled.<br> Error message=>" + strErrorMsg,
					"true", "false");
			return false;
		}
	}

	// ========================================WAITS TILL THE OBJECT IS CLICKABLE=================================================
	public static Boolean waitTillElementDisplayed(String strElementXPATH, String strObjectName, int intWaitTime)
			throws Exception {
		 strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		try {
			(new WebDriverWait(driver, intWaitTime))
					.until(ExpectedConditions.presenceOfElementLocated(By.xpath(strObjectXPATH)));
			objHTMLFunction.ReportPassFail(
					"The element" + "<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">"
							+ strObjectName + "</i>" + " is displayed as expected.<br>",
					"true", "true");
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			objHTMLFunction.ReportPassFail(
					"Error while waiting for the element" + "<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">"
							+ strObjectName + "</i>" + " to be displayed.<br> Error message=>" + strErrorMsg,
					"true", "false");
			return false;
		}
	}

	// ========================================CHECK IF OBJECT EXISTS=================================================
	public static Boolean checkObjectExists(WebElement objWebElement, String strObjectName) throws Exception {
		try {
			(new WebDriverWait(driver, 3)).until(ExpectedConditions.elementToBeClickable(objWebElement));
			String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
			strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
			Boolean blnObjectExists = waitTillElementDisplayed(objWebElement, strObjectName, 10);
			if (blnObjectExists == true) {
				
				objHTMLFunction.ReportPassFail(
						"<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> displayed",
						"true", "true");
			} else {
				
				objHTMLFunction.ReportPassFail(
						"<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName
								+ "</i> not displayed in the current page, but it is expected to be displayed",
						"true", "false");
			}
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			objHTMLFunction.ReportPassFail(
					"<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName
							+ "</i> not displayed in the current page, but it is expected to be displayed",
					"true", "false");
			return false;
		}
	}

	// ========================================CHECK IF OBJECT EXISTS=================================================
	public static Boolean checkObjectExists(String strElementXPATH, String strObjectName) throws Exception {
		strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		try {
			Boolean blnObjectExists = waitTillElementDisplayed(strObjectXPATH, strObjectName, 10);
			if (blnObjectExists == true) {
				
				objHTMLFunction.ReportPassFail(
						"<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> displayed",
						"true", "true");
			} else {
				objHTMLFunction.ReportPassFail(
						"<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName
								+ "</i> not displayed in the current page, but it is expected to be displayed",
						"true", "fail");

			}
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			objHTMLFunction
					.ReportPassFail(
							"Error while checking for the element " + "<i title=\"" + strObjectXPATH.replace("\"", "'")
									+ "\">" + strObjectName + "</i>" + " existence.<br> Error message=>" + strErrorMsg,
							"true", "fail");
			return false;
		}
	}

	// ========================================CHECK IF OBJECT NOT EXISTS===================================================
	public Boolean checkObjectNotExists(WebElement objWebElement, String strObjectName) throws Exception {
		try {
			String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
			strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
			Boolean blnObjectExists = waitTillElementEnabled(objWebElement, strObjectName, 3);
			if (blnObjectExists == false) {
				
				objHTMLFunction.ReportPassFail("Element <i title=\"" + strObjectXPATH.replace("\"", "'") + "\">"
						+ strObjectName + "</i> not displayed in the current page as expected", "true", "true");
				return true;
			} else {
				
				objHTMLFunction.ReportPassFail(
						"Element <i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName
								+ "</i> displayed in the current page, but it is not expected to be displayed",
						"true", "fail");
				return false;
			}

		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			objHTMLFunction.ReportPassFail("Error while checking for the object=>" + strObjectName
					+ " existence.<br> Error message=>" + strErrorMsg, "true", "fail");
			return false;
		}
	}

	// =======================================SWITCH TO NEW WINDOW By Title================================================================

	public String switchWindowByTitle(String titleToMatch) throws Exception {

		String currentWindow = driver.getWindowHandle();
		Set<String> windows = driver.getWindowHandles();

		for (String item : windows) {
			System.out.println(item);
			if (item.contentEquals(item)) {
				driver.switchTo().window(item);
				String title = driver.getTitle();
				if (title.contains(titleToMatch)) {
					break;
				}
			}
		}

		String strNewWindowTitle = driver.getTitle();
		objHTMLFunction.ReportPassFail("Moved to the newly opened window with title=><i>" + strNewWindowTitle + "</i>",
				"True", "True");
		

		return currentWindow;
	}

	// =======================================SWITCH TO NEW WINDOW================================================================
	public Boolean switchToNewWindow() throws Exception {
		try {
			if (driver.getWindowHandles().size() > 1) {
				for (String objWindowHandle : driver.getWindowHandles()) {
					driver.switchTo().window(objWindowHandle);
				}
				String strNewWindowTitle = driver.getTitle();
				objHTMLFunction.ReportPassFail(
						"Moved to the newly opened window with title=><i>" + strNewWindowTitle + "</i>", "true",
						"true");
			}
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			objHTMLFunction.ReportPassFail(
					"Error while switching to newly opened window.<br> Error message=>" + strErrorMsg, "true", "fail");
			return false;
		}
	}

	// =======================================SWITCH TO NEW WINDOW================================================================
	public Boolean switchToParentWindow() throws Exception {
		try {
			for (String objWindowHandle : driver.getWindowHandles()) {
				driver.switchTo().window(objWindowHandle);
				break;
			}
			String strNewWindowTitle = driver.getTitle();
			
			objHTMLFunction.ReportPassFail("Moved to the parent window with title=><i>" + strNewWindowTitle + "</i>",
					"true", "true");
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			objHTMLFunction.ReportPassFail(
					"Error while switching to the parent window.<br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}

	// =======================================SWITCH TO NEW FRAME================================================================
	public boolean switchToFrame(WebElement objWebElement, String strObjectName) throws Exception {
		try {
			String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
			strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
			driver.switchTo().frame(objWebElement);

			
			objHTMLFunction.ReportPassFail("Switched to new frame=><i title=\"" + strObjectXPATH.replace("\"", "'")
					+ "\">" + strObjectName + "</i>", "true", "true");
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			objHTMLFunction.ReportPassFail(
					"Error while switching to new frame=>" + strObjectName + ".<br> Error message=>" + strErrorMsg,
					"true", "fail");
			return false;
		}
	}

	// =======================================SWITCH TO NEW FRAME================================================================
	public boolean switchToDefaultPage() throws Exception {
		try {
			driver.switchTo().defaultContent();
			String strDefaultPageTitle = driver.getTitle();

			
			objHTMLFunction.ReportPassFail("Switched to default page with title=><i>" + strDefaultPageTitle + "</i>",
					"true", "true");
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			objHTMLFunction.ReportPassFail("Error while switching to default page.<br> Error message=>" + strErrorMsg,
					"true", "false");
			return false;
		}
	}

	// ==================================CLICK ON CO-ORDINATES============================================
	public boolean clickOnCoordinates(String strInputValue) throws Exception {
		Robot objRobot = new Robot();
		String[] arrCoordinates = strInputValue.split("--");

		try {
			objRobot.mouseMove(Integer.parseInt(arrCoordinates[0]), Integer.parseInt(arrCoordinates[1]));
			objRobot.mousePress(InputEvent.BUTTON1_MASK);
			objRobot.mouseRelease(InputEvent.BUTTON1_MASK);

			
			objHTMLFunction.ReportPassFail(
					"Mouse pointer moved to coordinates <B>{" + strInputValue + "}</B> and clicked", "true", "true");
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			objHTMLFunction.ReportPassFail("Error while clicking on the coordiantes <B>{" + strInputValue
					+ "}</B>.<br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}

	// ==================================MOUSEOVER OVER AN OBJECT============================================
	public boolean mouseOver(String strElementXPATH, String strObjectName) throws Exception {

		strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		try {
			WebElement objWebElement = findElementNReturn(strObjectXPATH);
			Actions builder = new Actions(driver);
			builder.moveToElement(objWebElement);
			Action objAction = builder.build();
			objAction.perform();

			objHTMLFunction.ReportPassFail("Mouse pointer moved to the element <i title=\""
					+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>", "true", "true");
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			
			objHTMLFunction.ReportPassFail("Error while moving the mouse pointer over the element " + strObjectName
					+ ".<br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}

	// ==================================MOUSEOVER OVER AN OBJECT============================================
	public boolean mouseOver(WebElement objWebElement, String strObjectName) throws Exception {

		try {
			String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
			strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
			Actions builder = new Actions(driver);
			builder.moveToElement(objWebElement);
			Action objAction = builder.build();
			objAction.perform();

			objHTMLFunction.ReportPassFail("Mouse pointer moved to the element <i title=\""
					+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>", "true", "true");
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			
			objHTMLFunction.ReportPassFail("Error while moving the mouse pointer over the element " + strObjectName
					+ ".<br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}

	// ==================================MOUSEOVER OVER AN OBJECT (NEW Implementation)============================================
	public boolean mouseOver2(WebElement objWebElement, String strObjectName) throws Exception {

		try {
			String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
			strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
			Actions builder = new Actions(driver);
			builder.moveToElement(objWebElement).build().perform();


			objHTMLFunction.ReportPassFail("Mouse pointer moved to the element <i title=\""
					+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>", "true", "true");
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);

			objHTMLFunction.ReportPassFail("Error while moving the mouse pointer over the element " + strObjectName
					+ ".<br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}


	// ==================================MOUSEOVER OVER AN OBJECT============================================
	public boolean mouseOverNClick(WebElement objWebElement, String strObjectName) throws Exception {
		try {
			String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
			strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);

			Actions builder = new Actions(driver);
			builder.moveToElement(objWebElement);
			Action objAction = builder.build();
			objAction.perform();

			Actions builder1 = new Actions(driver);
			builder1.click();
			Action objAction1 = builder1.build();
			objAction1.perform();

			
			objHTMLFunction.ReportPassFail("Mouse pointer moved to the element <i title=\""
					+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>, and it is clicked", "true",
					"true");
			
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			objHTMLFunction.ReportPassFail("Error while moving the mouse pointer over the element " + strObjectName
					+ " to click it.<br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}

	// ==================================MOUSEOVER OVER AN OBJECT============================================
	public boolean doubleClick(WebElement objWebElement, String strObjectName) throws Exception {
		try {
			String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
			strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);

			Actions builder = new Actions(driver);
			builder.doubleClick(objWebElement);
			Action objAction = builder.build();
			objAction.perform();

			
			objHTMLFunction.ReportPassFail("Double click event has been done in the element <i title=\""
					+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>", "true", "true");
			
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			objHTMLFunction.ReportPassFail("Error while doing double click event on the element " + strObjectName
					+ "</i>.<br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}

	// ==================================SELECT VALUE FROM LISTBOX BY VISIBLE TEXT============================================
	public static boolean selectValueByTextFromList(String strElementXPATH, String strObjectName, String strInputValue)
			throws Exception {
		strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		try {
			WebElement objWebElement = findElementNReturn01(strObjectXPATH);
			
			Select objSelectCountry = new Select(objWebElement);
			objSelectCountry.selectByVisibleText(strInputValue);

			
			objHTMLFunction.ReportPassFail("<B>" + strInputValue + "</B> selected from the element <i title=\""
					+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>", "true", "true");
			
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			objHTMLFunction.ReportPassFail("Error while selecting <B>" + strInputValue + "</B> from the element "
					+ strObjectName + ".<br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}

	// ==================================SELECT VALUE FROM LISTBOX BY VISIBLE TEXT============================================
	public static boolean selectValueByTextFromList(WebElement objWebElement, String strObjectName, String strInputValue)
			throws Exception {
		try {
			String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
			strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
			Select objSelectCountry = new Select(objWebElement);
			objSelectCountry.selectByVisibleText(strInputValue);

			objHTMLFunction.ReportPassFail("<B>" + strInputValue + "</B> selected from the element <i title=\""
					+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>", "true", "true");
			
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			
			objHTMLFunction.ReportPassFail("Error while selecting <B>" + strInputValue + "</B> from the element "
					+ strObjectName + ".<br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}

	// ==================================SELECT VALUE FROM LISTBOX BY VISIBLE TEXT============================================
	public boolean selectValueByValueFromList(WebElement objWebElement, String strObjectName, String strInputValue)
			throws Exception {
		try {
			String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
			strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
			Select objSelectCountry = new Select(objWebElement);
			objSelectCountry.selectByValue(strInputValue);

			objHTMLFunction.ReportPassFail("<B>" + strInputValue + "</B> selected from the element <i title=\""
					+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>", "true", "true");
			
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			objHTMLFunction.ReportPassFail("Error while selecting <B>" + strInputValue + "</B> from the element "
					+ strObjectName + ".<br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}

	// ==================================SELECT VALUE FROM LISTBOX BY INDEX============================================
	public boolean selectValueByIndexFromList(WebElement objWebElement, String strObjectName, int strInputValue)
			throws Exception {
		try {
			String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
			strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
			Select objSelectCountry = new Select(objWebElement);
			objSelectCountry.selectByIndex(strInputValue);

			objHTMLFunction.ReportPassFail("<B>" + strInputValue + "</B> selected from the element <i title=\""
					+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>", "true", "true");
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			
			objHTMLFunction.ReportPassFail("Error while selecting <B>" + strInputValue + "</B> from the element "
					+ strObjectName + "</i>.<br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}

	// ==================================CHECK SELECTED VALUE IN LIST BOX============================================
	public boolean checkSelectedValueInList(WebElement objWebElement, String strObjectName, String strExpectedText)
			throws Exception {
		String strDisplayedText = "";

		try {
			String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
			strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
			Select objSelectCountry = new Select(objWebElement);
			strDisplayedText = objSelectCountry.getFirstSelectedOption().getText().trim();

			if (strDisplayedText.equalsIgnoreCase(strExpectedText)) {
				
				objHTMLFunction.ReportPassFail(
						"Expected option <B>" + strExpectedText + "</B> matches with the actual option <B>"
								+ strDisplayedText + "</B> selected in the list element <i title=\""
								+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>",
						strExpectedText, strDisplayedText);
				
				return true;
			} else {
				
				objHTMLFunction.ReportPassFail(
						"Expected option <B>" + strExpectedText + "</B> doesn't match with the actual option <B>"
								+ strDisplayedText + "</B> selected in the list element <i title=\""
								+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>",
						strExpectedText, strDisplayedText);
				
				return false;
			}
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			
			objHTMLFunction.ReportPassFail("Error while fetching the selected option from the list element "
					+ strObjectName + "</i>.<br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}

	// ==================================ACCEPT ALERT============================================
	public boolean acceptAlert() throws Exception {
		String strAlertText;
		try {
			Alert objAlert = driver.switchTo().alert();
			strAlertText = objAlert.getText();
			objAlert.accept();

			objHTMLFunction.ReportPassFail("Alert with text <i>" + strAlertText + "</i> accepted", "true", "true");
			
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			
			objHTMLFunction.ReportPassFail("Error while accepting the alert.<br> Error message=>" + strErrorMsg, "true",
					"false");
			return false;
		}
	}

	// ==================================DISMISS ALERT============================================
	public boolean dismissAlert() throws Exception {
		String strAlertText;
		try {
			Alert objAlert = driver.switchTo().alert();
			strAlertText = objAlert.getText();
			objAlert.dismiss();
			
			objHTMLFunction.ReportPassFail("Alert with text <i>" + strAlertText + "</i> dismissed", "true", "true");
			
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			
			objHTMLFunction.ReportPassFail("Error while dismissing the alert.<br> Error message=>" + strErrorMsg,
					"true", "false");
			return false;
		}
	}

	// ==================================VERIFY ALERT TEXT============================================
	public boolean verifyAlertText(String strExpectedText) throws Exception {
		String strAlertText;
		try {
			Alert objAlert = driver.switchTo().alert();
			strAlertText = objAlert.getText();
			if (strAlertText.equalsIgnoreCase(strExpectedText)) {
				
				objHTMLFunction.ReportPassFail("Verifying the text displayed in alert, ExpectedText=>'"
						+ strExpectedText + "' AlertText=>" + strAlertText, "true", "true");
			} else {
				
				objHTMLFunction.ReportPassFail("Verifying the text displayed in alert, ExpectedText=>'"
						+ strExpectedText + "' AlertText=>" + strAlertText, "true", "false");
			}
			

			return strAlertText.equalsIgnoreCase(strExpectedText);

		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			
			
			objHTMLFunction.ReportPassFail(
					"Error while fetching the text from the alert.<br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}

	// ==================================VERIFY PAGE TITLE============================================

	public boolean verifyPageTitle(String strObjectName, String strExpectedText) throws Exception {
		String strDisplayedText = "";
		try {
			String strObjectXPATH = ((driver.getTitle().toString()));
			strDisplayedText = strObjectXPATH.trim();

			if (strDisplayedText.equalsIgnoreCase(strExpectedText)) {
				
				objHTMLFunction.ReportPassFail(
						"Verification of the text displayed in the element <i title=\""
								+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>",
						strExpectedText, strDisplayedText);
			} else {
				
				objHTMLFunction.ReportPassFail(
						"Verification of the text displayed in the title of the page => <i title=\""
								+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>",
						strExpectedText, strDisplayedText);
			}

			return strDisplayedText.equalsIgnoreCase(strExpectedText);
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			
			objHTMLFunction.ReportPassFail(
					"Error while fetching the text from the title of the page => " + strObjectName + "</i>.<br> Error message=>",
					"true", "false");
			return false;
		}
	}

	// ==================================VERIFY TEXT DISPLAYED IN AN ELEMENT============================================
	public static boolean verifyTextInElement(WebElement objWebElement, String strObjectName, String strExpectedText) throws Exception
	{
		String strDisplayedText = "";
		try
		{
			String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
			strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
			strDisplayedText = objWebElement.getText().trim().replaceAll("\\s+", " ");

			if (strDisplayedText.equalsIgnoreCase(strExpectedText)) 
			{				
				objHTMLFunction.ReportPassFail("Verification of the text displayed in the element <i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>", strExpectedText, strDisplayedText);
			} 
			else 
			{			
				objHTMLFunction.ReportPassFail("Verification of the text displayed in the element <i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>", strExpectedText, strDisplayedText);
			}
			return strDisplayedText.equalsIgnoreCase(strExpectedText);
		}
		catch (Exception objException) 
		{
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			
			objHTMLFunction.ReportPassFail("Error while fetching the text from the element " + strObjectName + "</i>.<br> Error message=>","true", "false");
			return false;
		}
	}

	// ==================================GET TEXT DISPLAYED IN AN ELEMENT============================================
	public static String getTextInElement(WebElement objWebElement, String strObjectName, String strExpectedText) throws Exception
	{
		String strDisplayedText = "";
		try
		{

			String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
			strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
			strDisplayedText = objWebElement.getText().trim();

			if(StringUtils.isNotBlank(strDisplayedText)){
				objHTMLFunction.ReportPassFail("Retrieved text displayed in the element <i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>", strExpectedText, strDisplayedText);
			}else{
				objHTMLFunction.ReportPassFail("Retrieved text displayed in the element <i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>", strExpectedText, strDisplayedText);
			}


			return strDisplayedText;
		}
		catch (Exception objException)
		{
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);

			objHTMLFunction.ReportPassFail("Error while fetching the text from the element " + strObjectName + "</i>.<br> Error message=>","true", "false");
			return null;
		}
	}

	// ==================================GET TEXT DISPLAYED IN AN ELEMENT============================================
	public static String getTextInElement(WebElement objWebElement, String strObjectName) throws Exception
	{
		String strDisplayedText = "";
		try
		{

			String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
			strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
			strDisplayedText = objWebElement.getText().trim();

			if(StringUtils.isNotBlank(strDisplayedText)){
				objHTMLFunction.ReportPassFail("Retrieved text displayed in the element <i title=\"" + strDisplayedText + "\">" + strObjectName + "</i>",
						"true", "true");
			}else{
				objHTMLFunction.ReportPassFail("Retrieved text displayed in the element <i title=\"" + strDisplayedText + "\">" + strObjectName + "</i>",
						"true", "false");
			}


			return strDisplayedText;
		}
		catch (Exception objException)
		{
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);

			objHTMLFunction.ReportPassFail("Error while fetching the text from the element " + strObjectName + "</i>.<br> Error message=>","true", "false");
			return null;
		}
	}


	// ==================================CHECK STRING VARIABLE IS EMPTY============================================
	public static boolean isStringEmpty(String strInputValue) throws Exception
	{

		if(StringUtils.isNotBlank(strInputValue)){
			//objHTMLFunction.ReportPassFail("String  <i title=\" is not Empty >" + strInputValue + "</i>", "true", "true");
			return true;
		}else{
			//objHTMLFunction.ReportPassFail("String  <i title=\" is Empty >" + strInputValue + "</i>", "true", "false");
			return false;
		}





	}

	// ==================================VERIFY TEXT DISPLAYED IN AN ELEMENT============================================
	public static boolean verifyTextInElement(String strElementXPATH, String strObjectName, String strExpectedText)
			throws Exception {
		String strDisplayedText = "";
		strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		try {
			WebElement objWebElement = findElementNReturn(strObjectXPATH);
			strDisplayedText = objWebElement.getText().trim().replaceAll("\\s+", " ");

			if (strDisplayedText.equalsIgnoreCase(strExpectedText)) {
				
				objHTMLFunction.ReportPassFail(
						"Verification of the text displayed in the element <i title=\""
								+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>",
						strExpectedText, strDisplayedText);
			} else {
				
				objHTMLFunction.ReportPassFail(
						"Verification of the text displayed in the element <i title=\""
								+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>",
						strExpectedText, strDisplayedText);
			}
			
			return strDisplayedText.equalsIgnoreCase(strExpectedText);
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			
			objHTMLFunction.ReportPassFail("Error while fetching the text from the element " + "<i title=\""
					+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>" + "</i>.<br> Error message=>",
					"true", "false");
			return false;
		}
	}

	// ==================================VERIFY TEXT DISPLAYED IN AN ELEMENT============================================
	public static String getTextFromElement(WebElement objWebElement, String strObjectName) throws Exception {
		String strDisplayedText = "";
		try {
			String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
			strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
			strDisplayedText = objWebElement.getText().trim().replaceAll("\\s+", " ");

			return strDisplayedText;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			
			
			objHTMLFunction.ReportPassFail("Error while fetching the text from the element " + strObjectName
					+ "</i>.<br> Error message=>" + strErrorMsg, "true", "false");
			return "";
		}
	}

	// ==================================VERIFY TEXT DISPLAYED IN AN ELEMENT============================================
	public static String getTextFromElement(String strElementXPATH, String strObjectName) throws Exception {
		String strDisplayedText = "";
		strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		try {
			WebElement objWebElement = findElementNReturn(strObjectXPATH);
			strDisplayedText = objWebElement.getText().trim().replaceAll("\\s+", " ");

			return strDisplayedText;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			
			objHTMLFunction.ReportPassFail("Error while fetching the text from the element " + "<i title=\""
					+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>" + "</i>.<br> Error message=>"
					+ strErrorMsg, "true", "false");
			return "";
		}
	}

	// ==================================VERIFY TEXT DISPLAYED IN AN ELEMENT============================================
	public String getAttributeFromElement(WebElement objWebElement, String strObjectName, String strAttributeName)
			throws Exception {
		String strDisplayedText = "";
		try {
			String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
			strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
			strDisplayedText = objWebElement.getAttribute(strAttributeName).trim().replaceAll("\\s+", " ");

			return strDisplayedText;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			
			objHTMLFunction.ReportPassFail("Error while fetching the attribute " + strAttributeName
					+ " from the element " + strObjectName + "</i>.<br> Error message=>" + strErrorMsg, "true",
					"false");
			return "";
		}
	}

	// ==================================VERIFY TEXT DISPLAYED IN AN ELEMENT============================================
	public String getAttributeFromElement(String strElementXPATH, String strObjectName, String strAttributeName)
			throws Exception {
		String strDisplayedText = "";
		strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		try {
			WebElement objWebElement = findElementNReturn(strObjectXPATH);
			strDisplayedText = objWebElement.getAttribute(strAttributeName).trim().replaceAll("\\s+", " ");

			return strDisplayedText;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			
			objHTMLFunction.ReportPassFail("Error while fetching the attribute " + strAttributeName
					+ " from the element " + "<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName
					+ "</i>" + "</i>.<br> Error message=>" + strErrorMsg, "true", "false");
			return "";
		}
	}

	// ==================================VERIFY DISPLAYED ATTRIBUTE IN AN ELEMENT============================================
	public static boolean verifyAttributeInElement(WebElement objWebElement, String strObjectName, String strAttributeName,
			String strExpectedText) throws Exception {
		String strDisplayedText = "";
		try {
			(new WebDriverWait(driver, 3)).until(ExpectedConditions.elementToBeClickable(objWebElement));
			String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
			strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
			strDisplayedText = objWebElement.getAttribute(strAttributeName).trim().replaceAll("\\s+", " ");

			if (strDisplayedText.equalsIgnoreCase(strExpectedText)) {
				
				objHTMLFunction.ReportPassFail("Attribute <i>" + strAttributeName + "</i> expected value <b>"
						+ strExpectedText + "</b> matches with the actual value <b>" + strDisplayedText
						+ "</b> displayed in the element <i title=\"" + strObjectXPATH.replace("\"", "'") + "\">"
						+ strObjectName + "</i>", strExpectedText, strDisplayedText);
				return true;
			} else {
				
				objHTMLFunction.ReportPassFail("Attribute <i>" + strAttributeName + "</i> expected value <b>"
						+ strExpectedText + "</b> doesn't match with the actual value <b>" + strDisplayedText
						+ "</b> displayed in the element <i title=\"" + strObjectXPATH.replace("\"", "'") + "\">"
						+ strObjectName + "</i>", strExpectedText, strDisplayedText);
				return false;
			}
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			
			objHTMLFunction.ReportPassFail("Error while fetching the attribute " + strAttributeName + " from element "
					+ strObjectName + ".<br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}

	// ==================================VERIFY DISPLAYED ATTRIBUTE IN AN ELEMENT============================================
	public static boolean verifyAttributeInElement(String strElementXPATH, String strObjectName, String strAttributeName,
			String strExpectedText) throws Exception {
		String strDisplayedText = "";
		strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		try {
			WebElement objWebElement = findElementNReturn(strObjectXPATH);
			strDisplayedText = objWebElement.getAttribute(strAttributeName).trim().replaceAll("\\s+", " ");

			
			if (strDisplayedText.equalsIgnoreCase(strExpectedText)) {
				
				objHTMLFunction.ReportPassFail("Attribute <i>" + strAttributeName + "</i> expected value <b>"
						+ strExpectedText + "</b> matches with the actual value <b>" + strDisplayedText
						+ "</b> displayed in the element <i title=\"" + strObjectXPATH.replace("\"", "'") + "\">"
						+ strObjectName + "</i>", strExpectedText, strDisplayedText);
				return true;
			} else {
				
				objHTMLFunction.ReportPassFail("Attribute <i>" + strAttributeName + "</i> expected value <b>"
						+ strExpectedText + "</b> doesn't match with the actual value <b>" + strDisplayedText
						+ "</b> displayed in the element <i title=\"" + strObjectXPATH.replace("\"", "'") + "\">"
						+ strObjectName + "</i>", strExpectedText, strDisplayedText);
				return false;
			}
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			
			objHTMLFunction.ReportPassFail("Error while fetching the attribute " + strAttributeName + " from element "
					+ "<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>"
					+ ".<br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}

	// ==================================SET RADIO OR CHECBOX ON/OFF============================================
	public static boolean selectRadioOrCheckBox(WebElement objWebElement, final String strObjectName, String strONOFF) throws Exception {
		boolean strIsObjectSelected;
		try {
			String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
			strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
			strIsObjectSelected = objWebElement.isSelected();
			if (strONOFF == "") {
				strONOFF = "OFF";
			}
			if (strONOFF.equalsIgnoreCase("ON")) {
				if (strIsObjectSelected == false) {
					objWebElement.click();
				}
			} else {
				if (strIsObjectSelected == true) {
					objWebElement.click();
				}
			}
			
			objHTMLFunction.ReportPassFail("Object <i title=\"" + strObjectXPATH.replace("\"", "'") + "\">"
					+ strObjectName + "</i> is set <b>" + strONOFF + "</b>", "true", "true");
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			
			objHTMLFunction.ReportPassFail("Unable to set <b>" + strONOFF + "</b> in element " + strObjectName
					+ ".<BR>Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}

	// ==================================SET RADIO OR CHECBOX ON/OFF============================================
	public static boolean selectRadioOrCheckBox(String strElementXPATH, final String strObjectName, String strONOFF)
			throws Exception {
		boolean strIsObjectSelected;
		strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		try {
			WebElement objWebElement = findElementNReturn01(strObjectXPATH);
			strIsObjectSelected = objWebElement.isSelected();
			if (strONOFF == "") {
				strONOFF = "OFF";
			}
			if (strONOFF.equalsIgnoreCase("ON")) {
				if (strIsObjectSelected == false) {
					objWebElement.click();
				}
			} else {
				if (strIsObjectSelected == true) {
					objWebElement.click();
				}
			}
			
			objHTMLFunction.ReportPassFail("Object <i title=\"" + strObjectXPATH.replace("\"", "'") + "\">"
					+ strObjectName + "</i> is set <b>" + strONOFF + "</b>", "true", "true");
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			
			objHTMLFunction.ReportPassFail("Unable to set <b>" + strONOFF + "</b> in element " + "<i title=\""
					+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>" + ".<BR>Error message=>"
					+ strErrorMsg, "true", "false");
			return false;
		}
	}

	// ==================================CHECK IF RADIO GROUP OR CHECKBOX CHECKED============================================
	public boolean checkElementSelected(WebElement objWebElement, String strObjectName, Boolean blnExpObjectSelected)
			throws Exception {
		String strExpSelection = "not selected";

		if (blnExpObjectSelected == true) {
			strExpSelection = "selected";
		}
		try {
			String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
			strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
			Boolean blnDispObjectSelected = objWebElement.isSelected();

			if (blnDispObjectSelected == blnExpObjectSelected) {
				if (blnExpObjectSelected == true) {
					
					objHTMLFunction.ReportPassFail("Element <i title=\"" + strObjectXPATH.replace("\"", "'") + "\">"
							+ strObjectName + "</i> is <b>selected</b> as expected", "true", "true");
					
				} else {
					
					objHTMLFunction.ReportPassFail("Element <i title=\"" + strObjectXPATH.replace("\"", "'") + "\">"
							+ strObjectName + "</i> is <b>not selected</b> as expected", "true", "true");
					
				}
				return true;

			} else {
				if (blnExpObjectSelected == true) {
					
					objHTMLFunction.ReportPassFail(
							"Element <i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName
									+ "</i> is not selected, but it is expected to be <b>selected</b>",
							"true", "false");
					
				} else {
					
					objHTMLFunction
							.ReportPassFail(
									"Element <i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName
											+ "</i> is selected, but it is expected <b>not to be selected</b>",
									"true", "true");
					
				}
				return true;
			}
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			objHTMLFunction.ReportPassFail("Error while checking if the element " + strObjectName + " is "
					+ strExpSelection + ".<br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}


	// ==================================CHECK IF Dynamic Element Checked============================================
	public boolean checkElementSelected(String strElementXPATH, String strObjectName, Boolean blnExpObjectSelected)
			throws Exception {

		strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		String strExpSelection = "not selected";

		if (blnExpObjectSelected == true) {
			strExpSelection = "selected";
		}
		try {

			WebElement objWebElement = findClicableElementNReturn(strObjectXPATH);
			Boolean blnDispObjectSelected = objWebElement.isSelected();

			if (blnDispObjectSelected == blnExpObjectSelected) {
				if (blnExpObjectSelected == true) {

					objHTMLFunction.ReportPassFail("Element <i title=\"" + strObjectXPATH.replace("\"", "'") + "\">"
							+ strObjectName + "</i> is <b>selected</b> as expected", "true", "true");

				} else {

					objHTMLFunction.ReportPassFail("Element <i title=\"" + strObjectXPATH.replace("\"", "'") + "\">"
							+ strObjectName + "</i> is <b>not selected</b> as expected", "true", "true");

				}
				return true;

			} else {
				if (blnExpObjectSelected == true) {

					objHTMLFunction.ReportPassFail(
							"Element <i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName
									+ "</i> is not selected, but it is expected to be <b>selected</b>",
							"true", "false");

				} else {

					objHTMLFunction
							.ReportPassFail(
									"Element <i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName
											+ "</i> is selected, but it is expected <b>not to be selected</b>",
									"true", "true");

				}
				return true;
			}
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			objHTMLFunction.ReportPassFail("Error while checking if the element " + strObjectName + " is "
					+ strExpSelection + ".<br> Error message=>" + strErrorMsg, "true", "false");
			objException.printStackTrace();
			return false;
		}
	}

	// =========================================TYPE VALUE USING ACTION BUILDER=========================================
	public static Boolean typeValueUsingActionBuilder(String strInputValue) throws Exception {
		try {
			Actions builder = new Actions(driver);
			builder.sendKeys(strInputValue);
			Action objAction = builder.build();
			objAction.perform();

			
			// System.out.println("Value=><b>" + strInputValue + "</b> typed");
			objHTMLFunction.ReportPassFail("Value=><b>" + strInputValue + "</b> typed", "true", "true");
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			
			objHTMLFunction.ReportPassFail(
					"Error while typing the value <b>" + strInputValue + "</b>.<br> Error message=>" + strErrorMsg,
					"true", "false");
			return false;
		}
	}

	// =========================================TYPE VALUE USING ACTION BUILDER=========================================
	public static Boolean typeValueUsingJSExecutor(WebElement objWebElement, String strObjectName, String strInputValue)
			throws Exception {
		try {
			String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
			strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].value=\"" + strInputValue + "\";", objWebElement);
			
			objHTMLFunction.ReportPassFail("Value <b>" + strInputValue + "</b> typed in element <i title="
					+ strObjectXPATH + ">" + strObjectName + "</i>", "true", "true");
			
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			
			objHTMLFunction.ReportPassFail("Error while typing the value <b>" + strInputValue + "</b> in object "
					+ strObjectName + ".<br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}

	// =========================================TYPE VALUE USING ACTION BUILDER=========================================
	public static Boolean clickObjectUsingJSExecutor(WebElement objWebElement, String strObjectName) throws Exception {
		try {
			String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
			strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", objWebElement);
			
			objHTMLFunction.ReportPassFail(
					"<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> clicked", "true",
					"true");
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			
			objHTMLFunction.ReportPassFail(strObjectName + " couldn't be clicked. <br> Error message=>" + strErrorMsg,
					"true", "false");
			return false;
		}
	}

	// ==========================================CHECK IF ALERT PRESENT AND ACCEPT IT===================================
	public static Boolean checkNAcceptAlert(int intWaitTime) throws Exception {
		Boolean blnAleartExists = false;
		try {
			WebDriverWait wait = new WebDriverWait(driver, intWaitTime);
	        wait.until(ExpectedConditions.alertIsPresent());
			try {
				blnAleartExists = true;
			} catch (Exception e) {

			}
			if (blnAleartExists == true) {
				Alert objAlert = driver.switchTo().alert();
				String strAlertText = objAlert.getText();
				objAlert.accept();
				objHTMLFunction.ReportPassFail("Alert with text <i>" + strAlertText + "</i> appeard and it is accepted",
						"true", "true");
			}
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			objHTMLFunction.ReportPassFail("There is no alert expected, please proceed.<br> message=>" + "Alert is not supposed to show in this step as expected", "true",
					"true");
			return true;
		}
	}

	// ==================SCROLL TO ELEMENT AND CLICK=========================
	public Boolean scrollToElementNClick(WebElement objWebElement, String strObjectName) throws Exception {
		try {
			String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
			strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", objWebElement);
			objWebElement.click();
			
			objHTMLFunction.ReportPassFail("Scrolled to object=><i title=\"" + strObjectXPATH.replace("\"", "'") + "\">"
					+ strObjectName + "</i> and clicked", "true", "true");
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			
			objHTMLFunction.ReportPassFail("Unable to scroll to object=><i>" + strObjectName
					+ "</i> and click. <br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}

	// ==================SCROLL TO ELEMENT AND CLICK=========================
	public Boolean scrollToElementNClick(String strElementXPATH, String strObjectName) throws Exception {
		strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		try {
			WebElement objWebElement = findElementNReturn(strObjectXPATH);
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", objWebElement);
			objWebElement.click();
			
			objHTMLFunction.ReportPassFail("Scrolled to object=><i title=\"" + strObjectXPATH.replace("\"", "'") + "\">"
					+ strObjectName + "</i> and clicked", "true", "true");
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			
			objHTMLFunction.ReportPassFail("Unable to scroll to object=><i title=\"" + strObjectXPATH.replace("\"", "'")
					+ "\">" + strObjectName + "</i> and click. <br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}

	// ===========================================CAPTURE PAGE LOADING TRANSACTION========================================
	@SuppressWarnings("deprecation")
	public Boolean capturePageLoadingTransaction(String strLoadingPageName) throws Exception {
		try {
			//objWBBackedSelenium.waitForPageToLoad("10");
			Long lngLoadURLStart = (Long) objJSExecutor
					.executeScript("return window.performance.timing.navigationStart;");
			Long lngLoadURLEnd = (Long) objJSExecutor.executeScript("return window.performance.timing.loadEventEnd;");

			
			String strTimeTakenMsg = "";

			double dblMilliSeconds = (lngLoadURLEnd - lngLoadURLStart);
			double dblSeconds = (lngLoadURLEnd - lngLoadURLStart) / 1000;
			int intMinutes = 0, intHour = 0;
			float intSeconds = 0;

			if (dblSeconds >= 60) {
				intMinutes = (int) (dblSeconds / 60);
				intSeconds = (float) (dblMilliSeconds % (60 * 1000));
				if (intMinutes >= 60) {
					intHour = intMinutes / 60;
					intMinutes = intMinutes % 60;
				}
			} else {
				intSeconds = (float) (dblMilliSeconds % (60 * 1000));
			}
			intSeconds = objCMNFunctions.RoundFloatNumber(intSeconds / 1000, 3);

			// System.out.println(intSeconds);

			if (intHour == 0 && intMinutes == 0) {
				strTimeTakenMsg = intSeconds + " seconds";
			} else {
				if (intHour == 0) {
					strTimeTakenMsg = intMinutes + " minutes " + intSeconds + " seconds";
				} else {
					strTimeTakenMsg = intHour + " hours " + intMinutes + " minutes " + intSeconds + " seconds";
				}
			}
			objHTMLFunction.ReportPassFail(strLoadingPageName + "=>" + strTimeTakenMsg, "PerformanceCapture",
					"PerformanceCapture");
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			System.out.println(strErrorMsg);
			objHTMLFunction.ReportPassFail("Error while capturing page load transaction for page '" + strLoadingPageName
					+ "'.<br> Error message=>" + strErrorMsg, "True", "False");
			return false;
		}

	}

	// ==========================================SPLIT AND REPLACE EXTERNAL DATA========================================
	public static String replaceExternalDataInObject(String strObjectNameNXPATH) {
		String strObjXPATH = "";
		String[] arrObjectNameNRepString = strObjectNameNXPATH.split("--");
		String strTempXPATH = arrObjectNameNRepString[0];
		strObjXPATH = strTempXPATH;

		if (arrObjectNameNRepString.length == 2) {
			strObjXPATH = strTempXPATH.replaceAll("EXTERNALDATA", arrObjectNameNRepString[1]);
		} else if (arrObjectNameNRepString.length > 2) {
			for (int intExtData = 1; intExtData < arrObjectNameNRepString.length; intExtData++) {
				strObjXPATH = strObjXPATH.replaceAll("EXTERNALDATA" + intExtData, arrObjectNameNRepString[intExtData]);
			}
		}
		return strObjXPATH;
	}

	// ==========================================SPLIT AND REPLACE EXTERNAL DATA========================================
	public String replaceExternalDataInObject_canvas(String strObjectNameNXPATH) {
		String strObjXPATH = "";
		String[] arrObjectNameNRepString = strObjectNameNXPATH.split("--");
		String strTempXPATH = arrObjectNameNRepString[0];
		strObjXPATH = strTempXPATH;

		if (arrObjectNameNRepString.length > 2) {

			strObjXPATH = strTempXPATH.replaceFirst("EXTERNALDATA", arrObjectNameNRepString[1]);

			strObjXPATH = strObjXPATH.replaceFirst("EXTERNALDATA", arrObjectNameNRepString[2]);

		}
		return strObjXPATH;
	}

	/*
	 * #########################################################################
	 * SIKULI FUNCTIONS WITH ATU REPORTS
	 * #########################################################################
	 */

	public void clickSikuliImageInsideFromRight(Pattern pattern, double matchingPercentage, int intImageIndex)
			throws FindFailed, AWTException {
		Screen screen = new Screen();
		screen.wait(pattern.similar((float) matchingPercentage), 20);

		Iterator<Match> itrMatches = screen.findAll(pattern.similar((float) matchingPercentage));

		Match mtchCorrectImage = null;
		int intDispMatches = -1;
		while (itrMatches.hasNext() && intDispMatches < intImageIndex) {
			mtchCorrectImage = itrMatches.next();
			intDispMatches = intDispMatches + 1;
		}
		int intX = mtchCorrectImage.getX();
		int intY = mtchCorrectImage.getY();
		int intH = mtchCorrectImage.getH();
		int intW = mtchCorrectImage.getW();

		System.out.println(intX + ", " + intY + ", " + intH + ", " + intW);

		Robot robot = new Robot();
		robot.mouseMove(intX + intW - 20, intY + 20);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	public void clickSikuliImage(Pattern pattern, double matchingPercentage, int intImageIndex)
			throws FindFailed, AWTException {
		Screen screen = new Screen();
		screen.wait(pattern.similar((float) matchingPercentage), 20);

		Iterator<Match> itrMatches = screen.findAll(pattern.similar((float) matchingPercentage));

		Match mtchCorrectImage = null;
		int intDispMatches = -1;
		while (itrMatches.hasNext() && intDispMatches < intImageIndex) {
			mtchCorrectImage = itrMatches.next();
			intDispMatches = intDispMatches + 1;
		}
		mtchCorrectImage.click();
	}

	// ============ For scrolling to element =============================================
	public static void scrollToElement(WebDriver driver, String strElementXPATH) throws Exception {
		
		strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		WebElement element = findElementNReturn(strObjectXPATH);
		
		Locatable locatableElement = (Locatable) element;
		Point p = locatableElement.getCoordinates().inViewPort();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int yAxis = (p.getY() - 100) > 0 ? (p.getY() - 100) : 0;
		js.executeScript("window.scrollTo(" + p.getX() + "," + yAxis + ");");
	}

	// ============= For writing using robot =============================================
	public static void writeUsingRobot(String string) throws AWTException {
		robot = new Robot();
		robot.setAutoDelay(40);
		robot.setAutoWaitForIdle(true);
		robot.delay(100);
		type(string);
		// robot.delay(50);
		// type(KeyEvent.VK_DOWN);
		robot.delay(500);
		// System.exit(0);
	}

	// ======== private method for writeUsingRobot ========================================
	private static void type(String s) {
		byte[] bytes = s.getBytes();
		for (byte b : bytes) {
			int code = b;
			// keycode only handles [A-Z] (which is ASCII decimal [65-90])
			if (code > 96 && code < 123)
				code = code - 32;
			robot.delay(40);
			robot.keyPress(code);
			robot.keyRelease(code);
		}
	}

	public static String getCurrentTime(String simpleDateFormat) {
		final SimpleDateFormat dateFormat = new SimpleDateFormat(simpleDateFormat);
		final String dateString = dateFormat.format(Calendar.getInstance().getTime());
		return dateString;
	}

	public static String convertDateFromGivenDateToGivenFormat(String date, String simpleDateFormat)
			throws ParseException {
		final SimpleDateFormat dateFormat = new SimpleDateFormat(simpleDateFormat);
		Date datex = dateFormat.parse(date);
		final String dateString = dateFormat.format(datex);
		return dateString;
	}

	public static String getNextMonthDate() {
		String currentTime = getCurrentTime("MM/dd/yyyy");
		String todayInDate = currentTime.substring(currentTime.indexOf("/") + 1, currentTime.lastIndexOf("/"));
		String currentMonth = currentTime.substring(0, currentTime.indexOf("/"));
		String currentYear = currentTime.substring(currentTime.lastIndexOf("/") + 1);
		int currentMonthInt = Integer.parseInt(currentMonth);
		int addMonth = 1;
		int nextMonth = currentMonthInt + addMonth;
		if (currentMonthInt == 12) {
			nextMonth = 1;
		}
		String nextMonthStr;
		if (nextMonth < 10) {
			nextMonthStr = "0" + nextMonth;
		} else {
			nextMonthStr = "" + nextMonth;
		}
		String futureDate = nextMonthStr + "/" + todayInDate + "/" + currentYear;
		return futureDate;
	}

	public static int lastDayOfCurrentMonthInt() {
		return Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static String getNextMinuteInTime() {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, 1);
		SimpleDateFormat df = new SimpleDateFormat("hh:mma");
		return df.format(now.getTime());
	}

	public static String addMinutesToCurrentTime(int addMinute) {
		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, addMinute);
		SimpleDateFormat df = new SimpleDateFormat("hh:mma");
		return df.format(now.getTime());
	}


	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}

	public void screenClickSimilar(Pattern pattern, double d) throws FindFailed {
		Screen screen = new Screen();
		screen.wait(pattern.similar((float) d), 20);
		screen.click(pattern.similar((float) d));
	}

	public boolean screenClickSimilar(Pattern pattern) throws FindFailed {
		Screen screen = new Screen();
		for (int i = 0; i <= 2; i++) {
			try {
				screen.click(pattern);
				return true;
			} catch (Exception e) {
				e.getMessage();
			}
		}
		return false;
	}

	public boolean screenClickSimilar(Pattern pattern, int waitTime, double d) throws FindFailed {
		Screen screen = new Screen();
		for (int i = 0; i <= 2; i++) {
			try {
				screen.wait(pattern.similar((float) d), waitTime);
				screen.click(pattern.similar((float) d));
				return true;
			} catch (Exception e) {
				e.getMessage();
				waitTime = 2;
			}
		}
		return false;
	}

	public boolean screenClickSimilar(Pattern pattern, int waitTime) throws FindFailed {
		Screen screen = new Screen();
		for (int i = 0; i <= 2; i++) {
			try {
				screen.wait(pattern, waitTime);
				screen.click(pattern);
				return true;
			} catch (Exception e) {
				e.getMessage();
				waitTime = 2;
			}
		}
		return false;
	}

	public void screenClickToRight(Pattern pattern, double d) throws FindFailed {
		Screen screen = new Screen();
		screen.wait(pattern.similar((float) d), 20);
		screen.right(20).click(pattern.similar((float) d));
		// screen.find(pattern.similar((float)d)).left(20).click();
	}

	public void screenClickBelow(Pattern pattern, double d) throws FindFailed {
		Screen screen = new Screen();
		screen.below(10).click(pattern.similar((float) d));
	}

	public boolean screenExistsSimilar(Pattern pattern, double d) throws FindFailed {
		Screen screen = new Screen();
		Match match = null;
		for (int i = 0; i <= 2; i++) {
			try {
				match = screen.exists(pattern);
			} catch (Exception e) {
				e.getMessage();
			}
		}
		double db = 0;
		boolean ses = false;
		try {
			db = match.getScore();
		} catch (Exception e) {
		}
		ses = db > d;
		db = 0;
		return ses;
	}

	public void findMultiStateImageAndClick(MultiStateTarget target) throws InterruptedException {
		ScreenRegion r = findAndReturnMultiStateImage(target);
		DesktopMouse mouse = new DesktopMouse();
		waiting(2);
		mouse.click(r.getCenter());
	}

	public ScreenRegion findAndReturnMultiStateImage(MultiStateTarget target) {
		target.setMinScore(0.80);
		ScreenRegion s = new DesktopScreenRegion();
		ScreenRegion r = s.find(target);
		return r;
	}

	public void pasteFromClipboard(String clipBoardData) throws AWTException {
		setClipboardData(clipBoardData);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	public void pasteFromClipboard(String clipBoardData, String sessionTimeStamp)
			throws AWTException, InterruptedException {
		setClipboardData(clipBoardData + sessionTimeStamp + ".txt");
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_A);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		waiting(2);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	public static void setClipboardData(String data) {
		StringSelection stringSelection = new StringSelection(data);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
	}

	public void navigateTo(String url) {
		driver.navigate().to(url);
	}

	public void maximizeWindow() {
		driver.manage().window().maximize();
	}

	public static void refreshPage() {
		driver.navigate().refresh();
		delayFor(4);
	}

	public void refreshPageF5() {
		Actions actionObject = new Actions(driver);
		actionObject.keyDown(Keys.CONTROL).sendKeys(Keys.F5).keyUp(Keys.CONTROL).perform();
	}

	public void navigateBack() {
		driver.navigate().back();
	}

	public void deleteAllCookies() {
		driver.manage().deleteAllCookies();
	}

	public void switchToAlertAccept() {
		driver.switchTo().alert().accept();
	}

	public void waiting(int seconds) throws InterruptedException {
	}

	// Soft Assertions
	@Test(enabled = false)
	public void verifyTrue(Boolean b, String msg) {
		try {
			Assert.assertTrue(b.booleanValue());
		} catch (Error e) {
			verificationErrors.append(e + ", msg" + msg);
			Reporter.log(msg + "," + e.getMessage());
			logger.info(msg + "," + e.getMessage() + "(:getMessage)");
			logger.info(msg + "," + e.getCause() + "(:getCause)");
		}
	}

	@Test(enabled = false)
	public void verifyNotNull(WebElement b, String msg) {
		try {
			Assert.assertNotNull(b);
		} catch (Error e) {
			verificationErrors.append(e + ", msg-" + msg);
			Reporter.log(msg + ", " + e.getMessage());
			logger.info(msg + "," + e.getMessage() + "(:getMessage)");
			logger.info(msg + "," + e.getCause() + "(:getCause)");
		} catch (NoSuchElementException nsee) {
			verificationErrors.append("NoSuchElementException" + ", msg-" + msg);
			Reporter.log(msg + ", " + nsee.getMessage());
			logger.info(msg + "," + nsee.getMessage() + "(:getMessage)");
			logger.info(msg + "," + nsee.getCause() + "(:getCause)");
		}
	}

	@Test(enabled = false)
	public void verifyNotNullAndDisplayed(WebElement b, String msg) throws InterruptedException {
		boolean elementB = false;
		try {
			Assert.assertNotNull(b);
			elementB = b.isDisplayed();
			if (elementB == false) {
				Assert.fail();
				verificationErrors.append("Not displayed" + ", msg-" + msg);
			}
		} catch (Error e) {
			verificationErrors.append(e + "," + e.getMessage() + ", msg-" + msg);
			Reporter.log(msg + ", " + e.getMessage());
			logger.info(msg + ", " + e.getMessage());
		} catch (NoSuchElementException nsee) {
			verificationErrors.append("NoSuchElementException" + ", msg-" + msg);
			Reporter.log(msg + ", " + nsee.getMessage());
			logger.info(msg + "," + nsee.getMessage() + "(:getMessage)");
			logger.info(msg + "," + nsee.getCause() + "(:getCause)");
		}

	}

	// ==================================CHECK IF Dynamic Element Displayed============================================
	public boolean verifyDisplayed(String strElementXPATH, String strObjectName, Boolean blnExpObjectSelected)
			throws Exception {




		strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		String strExpSelection = "not selected";

		if (blnExpObjectSelected == true) {
			strExpSelection = "selected";
		}
		try {

			WebElement objWebElement = findClicableElementNReturn(strObjectXPATH);
			Boolean blnDispObjectSelected = objWebElement.isDisplayed();

			if (blnDispObjectSelected == blnExpObjectSelected) {
				if (blnExpObjectSelected == true) {

					objHTMLFunction.ReportPassFail("Element <i title=\"" + strObjectXPATH.replace("\"", "'") + "\">"
							+ strObjectName + "</i> is <b>Displayed</b> as expected", "true", "true");

				} else {

					objHTMLFunction.ReportPassFail("Element <i title=\"" + strObjectXPATH.replace("\"", "'") + "\">"
							+ strObjectName + "</i> is <b>not Displayed</b> as expected", "true", "true");

				}
				return true;

			} else {
				if (blnExpObjectSelected == true) {

					objHTMLFunction.ReportPassFail(
							"Element <i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName
									+ "</i> is not Displayed, but it is expected to be <b>Displayed</b>",
							"true", "false");

				} else {

					objHTMLFunction
							.ReportPassFail(
									"Element <i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName
											+ "</i> is Displayed, but it is expected <b>not to be Displayed</b>",
									"true", "true");

				}
				return true;
			}
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			objHTMLFunction.ReportPassFail("Error while checking if the element " + strObjectName + " is "
					+ strExpSelection + ".<br> Error message=>" + strErrorMsg, "true", "false");
			objException.printStackTrace();
			return false;
		}
	}

	@Test(enabled = false)
	public void verifyFalse(Boolean b, String msg) {
		try {
			Assert.assertFalse(b.booleanValue());
		} catch (Error e) {
			verificationErrors.append(e + ", msg" + msg);
			Reporter.log(msg + ", " + e.getMessage());
			logger.info(msg + "," + e.getMessage() + "(:getMessage)");
			logger.info(msg + "," + e.getCause() + "(:getCause)");
		}
	}

	@Test(enabled = false)
	public static void verifyEquals(String strObjectName, String strDisplayedText, String strExpectedText) throws Exception {
		try {
			assertEquals(strDisplayedText, strExpectedText);
			objHTMLFunction
					.ReportPassFail(
							"Verification of the text displayed in the element <i title=\""
									+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>",
							strExpectedText, strDisplayedText);
		} catch (Error e) {
			
			objHTMLFunction
					.ReportPassFail(
							"Verification of the text displayed in the element <i title=\""
									+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>",
							strExpectedText, strDisplayedText);
		}
	}

	@Test(enabled = false)
	public void verifyEquals(String msg, Object obj1, Object obj2) {
		try {
			assertEquals(obj1, obj2);
		} catch (Error e) {
			verificationErrors.append(e);
			Reporter.log(msg + ", " + e.getMessage());
			logger.info(msg + "," + e.getMessage() + "(:getMessage)");
			logger.info(msg + "," + e.getCause() + "(:getCause)");
		} catch (NoSuchElementException nsee) {
			verificationErrors.append("NoSuchElementException" + ", msg-" + msg);
			Reporter.log(msg + ", " + nsee.getMessage());
			logger.info(msg + ", " + nsee.getMessage());
		}
	}

	@Test(enabled = false)
	public void verifyEquals(String msg, String s1[], String s2[]) {
		try {
			assertEquals(s1, s2);
		} catch (Error e) {
			verificationErrors.append(e + ", msg-" + msg);
			Reporter.log(msg + ", " + e.getMessage());
			logger.info(msg + "," + e.getMessage() + "(:getMessage)");
			logger.info(msg + "," + e.getCause() + "(:getCause)");
		}
	}

	@Test(enabled = false)
	public void verifyEquals(String msg, Object s1[], Object s2[]) {
		try {
			assertEquals(((Object) (s1)), ((Object) (s2)));
		} catch (Error e) {
			verificationErrors.append(e + ", msg-" + msg);
			Reporter.log(msg + ", " + e.getMessage());
			logger.info(msg + "," + e.getMessage() + "(:getMessage)");
			logger.info(msg + "," + e.getCause() + "(:getCause)");
		} catch (NoSuchElementException nsee) {
			verificationErrors.append("NoSuchElementException" + ", msg-" + msg);
			Reporter.log(msg + ", " + nsee.getMessage());
			logger.info(msg + "," + nsee.getMessage() + "(:getMessage)");
			logger.info(msg + "," + nsee.getCause() + "(:getCause)");
		}
	}

	@Test(enabled = false)
	public void clearVerificationErrors() {
		verificationErrors = new StringBuffer();
	}

	@Test(enabled = false)
	public void checkForVerificationErrors() {
		String verificationErrorString = verificationErrors.toString();
		System.out.println("verificationErrorString: " + verificationErrorString);

		if (!verificationErrorString.isEmpty()) {
			System.out.println("getClass().getSimpleName(): " + getClass().getSimpleName());
			Assert.assertTrue(verificationErrorString.isEmpty());
		}
		clearVerificationErrors();
	}

	@Test(enabled = false)
	public void fail(String verificationErrorString) {
		Assert.assertTrue(verificationErrorString.contains(""));
	}

	public static String convertToPreferredFormatForTimeStamp(Long unixtime) {
		Date localDate = convertunixtime(unixtime);
		return "_" + PreferredTimeFormatter2.format(localDate);
	}

	public static Date convertunixtime(Long unixtime) {
		java.util.Date time = new java.util.Date(unixtime);
		return time;
	}

	// ================== Check Element Existence Then Do Action ========================================

	@Test(enabled = false)
	public static Boolean elementExistance(By strFindElementBy, String strObjectName, int intWaitTime) throws Exception {
		try {
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			new WebDriverWait(driver, intWaitTime).until(ExpectedConditions.presenceOfElementLocated(strFindElementBy));
			
			objHTMLFunction.ReportPassFail("Element " + strObjectName + " Is Displayed In The Expected Time", "True",
					"True");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return true;
		} catch (Exception objException) {
			objHTMLFunction.ReportPassFail("Element " + strObjectName + " Is NOT Displayed In The Expected Time",
					"True", "True");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return false;
		}
	}



	@Test(enabled = false)
	public static Boolean elementExistance(String strElementXPATH, String strObjectName, int intWaitTime) throws Exception {
		strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		try {
			WebElement objWebElement = findElementNReturn01(strObjectXPATH);
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			new WebDriverWait(driver, intWaitTime).until(ExpectedConditions.visibilityOf(objWebElement));
						objHTMLFunction.ReportPassFail("Element " + strObjectName + " Is Displayed In The Expected Time", "True", "True");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return true;
		} catch (Exception objException) {
			objHTMLFunction.ReportPassFail("Element " + strObjectName + " Is NOT Displayed In The Expected Time", "True", "True");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return false;
		}
	}

	@Test(enabled = false)
	public static Boolean elementNonExistance(WebElement objWebElement, String strObjectName, int intWaitTime) throws Exception {
		//strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		try {
			//WebElement objWebElement = findElementNReturn01(strObjectXPATH);
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			new WebDriverWait(driver, intWaitTime).until(ExpectedConditions.invisibilityOf(objWebElement));
			objHTMLFunction.ReportPassFail("Element " + strObjectName + " Is Displayed In The Expected Time", "True", "True");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return true;
		} catch (Exception objException) {
			objHTMLFunction.ReportPassFail("Element " + strObjectName + " Is NOT Displayed In The Expected Time", "True", "True");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return false;
		}
	}

	@Test(enabled = false)
	public static Boolean elementExistance(WebElement objWebElement, String strObjectName, int intWaitTime) throws Exception {
		// strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		try {
			// WebElement objWebElement = findElementNReturn(strObjectXPATH);
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			new WebDriverWait(driver, intWaitTime).until(ExpectedConditions.visibilityOf(objWebElement));
			objHTMLFunction.ReportPassFail("Element " + strObjectName + " Is Displayed In The Expected Time", "True", "True");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return true;
		} catch (Exception objException) {
			objHTMLFunction.ReportPassFail("Element " + strObjectName + " Is NOT Displayed In The Expected Time",
					"True", "false");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return false;
		}
	}
	
	@Test(enabled = false)
	public static Boolean elementExistanceNoReport(WebElement objWebElement, int intWaitTime) throws Exception {
		try {
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
			new WebDriverWait(driver, intWaitTime).until(ExpectedConditions.visibilityOf(objWebElement));
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return true;
		} catch (Exception objException) {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			return false;
		}
	}

	// ======================== Explicit Wait ========================================
	@Test(enabled = false)
	public static void delayFor(long millis) {
		try {
			Thread.sleep(millis*100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// ======================== Alternative Select ========================================
	public void SelectElement(WebElement element, String item) {

		Select combo = new Select(element);
		List<WebElement> list = combo.getOptions();
		for (WebElement e : list) {
			System.out.println(e.getText());
			if (e.getText().contentEquals(item)) {
				e.click();
			}
		}
	}

	public List<WebElement> objListWebelements(String lnkAddedEvent) {
		try {
			List<WebElement> itemElemList = driver.findElements(By.xpath(lnkAddedEvent));
			return itemElemList;
		} catch (Exception e) {
			return null;
		}
	}

	// =============== DRAG AND DROP ===========================================
	public boolean dragDropElements(String strSourceElementXPATH, String strDestinationElementXPATH, String strObjectName) throws Exception 
	{
		strObjectSourceForDragDrop = replaceExternalDataInObject(strSourceElementXPATH);
		strObjectDestinationForDragDrop = replaceExternalDataInObject(strDestinationElementXPATH);
		try {
			WebElement objSourceWebElement = findElementNReturn(strObjectSourceForDragDrop);
			WebElement objDestinationWebElement = findElementNReturn(strObjectDestinationForDragDrop);
			Actions builder = new Actions(driver);
			Action dragAndDrop = builder.clickAndHold(objSourceWebElement).moveToElement(objDestinationWebElement)
					.release(objDestinationWebElement).build();
			dragAndDrop.perform();
			
			objHTMLFunction.ReportPassFail("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">"
					+ objSourceWebElement + "</i> Drag & Drop", "true", "true");
			return true;
		} catch (Exception objSourceWebElement) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objSourceWebElement, true);
			
			objHTMLFunction
					.ReportPassFail(
							"<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + objSourceWebElement + "</i>"
									+ " couldn't be Drag & Dropped. <br> Error message=>" + strErrorMsg,
							"true", "false");
			return false;
		}

	}
	
	
	// =============== DRAG AND DROP ===========================================
    public boolean dragDropElements(WebElement objSourceWebElement, WebElement objDestinationWebElement,
            String strObjectName) throws Exception {

        try {
            Actions builder = new Actions(driver);
            Action dragAndDrop = builder.clickAndHold(objSourceWebElement).moveToElement(objDestinationWebElement)
                    .release(objDestinationWebElement).build();
            dragAndDrop.perform();

            objHTMLFunction.ReportPassFail("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">"
                    + objSourceWebElement + "</i> Drag & Drop", "true", "true");

            return true;
        } catch (Exception objSourceWebElement1) {
            strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objSourceWebElement1, true);

            objHTMLFunction
                    .ReportPassFail(
                            "<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + objSourceWebElement1 + "</i>"
                                    + " couldn't be Drag & Dropped. <br> Error message=>" + strErrorMsg,
                            "true", "false");
            return false;
        }

    }

	// ============ For scrolling to element =============================================
	public static Boolean scrollToElement(String strElementXPATH, String strObjectName, WebDriver driver) throws Exception {

		strObjectXPATH = replaceExternalDataInObject(strElementXPATH);

		try {
			WebElement objWebElement = findElementNReturn(strObjectXPATH);

			Locatable element = (Locatable) objWebElement;
			Point p = element.getCoordinates().inViewPort();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			int yAxis = (p.getY() - 100) > 0 ? (p.getY() - 100) : 0;
			js.executeScript("window.scrollTo(" + p.getX() + "," + yAxis + ");");

			objHTMLFunction.ReportPassFail("Element " + strObjectName + " Is Displayed In The Expected Time", "True",
					"True");
			return true;
		} catch (Exception objException) {
			objHTMLFunction.ReportPassFail("Element " + strObjectName + " Is NOT Displayed In The Expected Time",
					"True", "True");
			return false;
		}
	}

	// =========================================CLICK OBJECT USING JAVA SCRIPT EXECUTER X-PATH=========================================
	public static Boolean clickObjectUsingJSExecutor(String strElementXPATH, String strObjectName) throws Exception {
		strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		try {
			WebElement objWebElement = findElementNReturn(strObjectXPATH);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", objWebElement);
			objHTMLFunction.ReportPassFail(
					"<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> clicked", "true",
					"true");
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			
			objHTMLFunction.ReportPassFail(strObjectName + " couldn't be clicked. <br> Error message=>" + strErrorMsg,
					"true", "false");
			return false;
		}
	}

	// =========================================Check if object exists without failing story================================
	public Boolean checkObjectExistsWithNoFail(String strElementXPATH, String strObjectName) throws Exception {
		strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		Boolean blnObjectExists = false;
		try {
			blnObjectExists = waitTillElementDisplayed(strObjectXPATH, strObjectName, 1);
		} catch (Exception objException) {
			return false;
		}
		return blnObjectExists;
	}

	// ========================================TYPE VALUE IN OBJECT-canvas=================================================
	public Boolean typeValue_canvas(String strElementXPATH, String strObjectName, String strInputValue)
			throws Exception {
		strObjectXPATH = replaceExternalDataInObject_canvas(strElementXPATH);
		try {
			WebElement objWebElement = findElementNReturn(strObjectXPATH);
			objWebElement.sendKeys(strInputValue);

			if (!(GlobalPaths.strBrowserType).equalsIgnoreCase("androidnative")
					&& !(GlobalPaths.strBrowserType).equalsIgnoreCase("iosnative")) {
				if (objWebElement.getAttribute("type").equalsIgnoreCase("password")) {
					strInputValue = StringUtils.repeat("*", strInputValue.length());
				}
			}
			objHTMLFunction.ReportPassFail("Value typed in element <i title=\"" + strObjectXPATH.replace("\"", "'")
					+ "\">" + strObjectName + "</i>=>" + strInputValue, "true", "true");
			System.out.println("Value typed in element <i title=\"" + strObjectXPATH.replace("\"", "'") + "\">"
					+ strObjectName + "</i>=>" + strInputValue);
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			System.out.println("Unable to type value '<B>" + strInputValue + "</B>' in element " + "<i title=\""
					+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>" + "<br> Error message=>"
					+ strErrorMsg);
			objHTMLFunction.ReportPassFail("Unable to type value '<B>" + strInputValue + "</B>' in element "
					+ "<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>"
					+ "<br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}
	
	// ========================================TYPE VALUE IN OBJECT-canvas=================================================
	
	public static String uploadFile(String assetName) throws FileNotFoundException {
		String absPath = String.format("%s/assets/%s", System.getProperty("user.dir"), assetName);
		File assetFile = new File(absPath);

		if (assetFile.exists() && assetFile.canRead()) {
			return absPath;
		} else {
			throw new FileNotFoundException(String.format("File %s does not exist", absPath));
		}
	}

	// =============== DRAG AND DROP USING ROBOT XPATH===========================================
	public boolean dragDropElementUsingRobot(String strSourceElementXPATH, String strDestinationElementXPATH, String strObjectName) throws Exception 
	{
		strObjectSourceForDragDrop = replaceExternalDataInObject(strSourceElementXPATH);
		strObjectDestinationForDragDrop = replaceExternalDataInObject(strDestinationElementXPATH);
		try {
			WebElement objSourceWebElement = findElementNReturn(strObjectSourceForDragDrop);
			WebElement objDestinationWebElement = findElementNReturn(strObjectDestinationForDragDrop);

			Robot objrobot = new Robot();
			Actions builder = new Actions(driver);
	        
	        objrobot.setAutoDelay(500);

			Dimension objSourceSize = objSourceWebElement.getSize();
			Dimension objDestinationSize = objDestinationWebElement.getSize();
			Point objSourceLocation = objSourceWebElement.getLocation();
			Point objDestinationLocation = objDestinationWebElement.getLocation();

			objDestinationLocation.x += objDestinationSize.width / 2;
			objDestinationLocation.y += objDestinationSize.height / 2 + 120;
			objSourceLocation.x += objSourceSize.width / 2;
			objSourceLocation.y += objSourceSize.height / 2 + 110;
			objrobot.mouseMove(objSourceLocation.x, objSourceLocation.y);
			
			//====New Addition====
			delayFor(2);
			builder.clickAndHold(objSourceWebElement)
			.build().perform();
			objrobot.setAutoDelay(500);
			objrobot.mousePress(InputEvent.BUTTON1_MASK);
			//====================
			
			objrobot.mousePress(InputEvent.BUTTON1_MASK);
			
			objrobot.mouseMove(((objDestinationLocation.x - objSourceLocation.x) / 2) + objSourceLocation.x,
					((objDestinationLocation.y - objSourceLocation.y) / 2) + objSourceLocation.y);
			objrobot.mouseMove(objDestinationLocation.x, objDestinationLocation.y);
			objrobot.mouseRelease(InputEvent.BUTTON1_MASK);
			
			
			objHTMLFunction.ReportPassFail("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">"
					+ objSourceWebElement + "</i> Drag & Drop", "true", "true");
			
			return true;
		} catch (Exception objSourceWebElement) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objSourceWebElement, true);
			
			
			objHTMLFunction
					.ReportPassFail(
							"<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + objSourceWebElement + "</i>"
									+ " couldn't be Drag & Dropped. <br> Error message=>" + strErrorMsg,
							"true", "false");
			return false;
		}
	}

	// =============== DRAG AND DROP USING ROBOT WEBELEMENT===========================================
	public boolean dragDropElementUsingRobot(WebElement objSourceWebElement, WebElement objDestinationWebElement, String strObjectName) throws Exception 
	{
		try {
			(new WebDriverWait(driver, 3)).until(ExpectedConditions.elementToBeClickable(objSourceWebElement));
			Robot objrobot = new Robot();
			Actions builder = new Actions(driver);
	        
	        objrobot.setAutoDelay(500);

			Dimension objSourceSize = objSourceWebElement.getSize();
			Dimension objDestinationSize = objDestinationWebElement.getSize();
			Point objSourceLocation = objSourceWebElement.getLocation();
			Point objDestinationLocation = objDestinationWebElement.getLocation();

			objDestinationLocation.x += objDestinationSize.width / 2;
			objDestinationLocation.y += objDestinationSize.height / 2 + 50;
			objSourceLocation.x += objSourceSize.width / 2;
			objSourceLocation.y += objSourceSize.height / 2 + 85;
			objrobot.mouseMove(objSourceLocation.x, objSourceLocation.y);
			
			//====New Addition====
			//delayFor(1);
			builder.clickAndHold(objSourceWebElement)
			.build().perform();
			objrobot.setAutoDelay(500);
			objrobot.mousePress(InputEvent.BUTTON1_MASK);
			//====================
			objrobot.mousePress(InputEvent.BUTTON1_MASK);
			
			objrobot.mouseMove(((objDestinationLocation.x - objSourceLocation.x) / 2) + objSourceLocation.x,
					((objDestinationLocation.y - objSourceLocation.y) / 2) + objSourceLocation.y);
			objrobot.mouseMove(objDestinationLocation.x, objDestinationLocation.y);
			objrobot.mouseRelease(InputEvent.BUTTON1_MASK);
			
			
			objHTMLFunction.ReportPassFail("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">"
					+ strObjectName + "</i> Drag & Drop", "true", "true");
			
			return true;
		} catch (Exception objSourceWebElement1) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objSourceWebElement1, true);
			
			
			objHTMLFunction
					.ReportPassFail(
							"<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>"
									+ " couldn't be Drag & Dropped. <br> Error message=>" + strErrorMsg,
							"true", "false");
			return false;
		}
	}
	
	// ============================== Pause =================================
	
	public boolean pause(){
		try{
			JavascriptExecutor js = (JavascriptExecutor) driver;
			 js.executeScript("function f(){console.log('Paused 4000 MilliSeconds');} window.setTimeout(f, 4000);");
			   System.out.println("Pause was applied for 4000 MilliSeconds");
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	// =============== DRAG AND DROP USING JavaScript Vanilla===========================================
	public boolean simulateDragAndDropJS(WebElement elementToDrag, WebElement target, String strObjectName) throws Exception {
		  try{
		   JavascriptExecutor js = (JavascriptExecutor) driver;
		   js.executeScript("function createEvent(typeOfEvent) {\n" +
		                   "var event = document.createEvent(\"CustomEvent\");\n" +
		                   "event.initCustomEvent(typeOfEvent, true, true, null);\n" +
			               "   event.dataTransfer = {\n" +
			               "       data: {},\n" +
			               "       setData: function (key, value) {\n" +
			               "           this.data[key] = value;\n" +
			               "       },\n" +
			               "       getData: function (key) {\n" +
			               "           return this.data[key];\n" +
			               "       }\n" +
			               "   };\n" +
			               "   return event;\n" +
			               "}\n" +
			               "\n" +
			               "function dispatchEvent(element, event, transferData) {\n" +
			               "   if (transferData !== undefined) {\n" +
			               "       event.dataTransfer = transferData;\n" +
			               "   }\n" +
			               "   if (element.dispatchEvent) {\n" +
			               "       element.dispatchEvent(event);\n" +
			               "   } else if (element.fireEvent) {\n" +
			               "       element.fireEvent(\"on\" + event.type, event);\n" +
			               "   }\n" +
			               "}\n" +
			               "\n" +
			               "function simulateHTML5DragAndDrop(element, target) {\n" +
			               "   var dragStartEvent = createEvent('dragstart');\n" +
			               "   dispatchEvent(element, dragStartEvent);\n" +
			               "   var dropEvent = createEvent('drop');\n" +
			               "   dispatchEvent(target, dropEvent, dragStartEvent.dataTransfer);\n" +
			               "   var dragEndEvent = createEvent('dragend'); \n" +
			               "   dispatchEvent(element, dragEndEvent, dropEvent.dataTransfer);\n" +
			               "}\n" +
			               "\n" +
			               "var elementToDrag = arguments[0];\n" +
			               "var target = arguments[1];\n" +
			               "simulateHTML5DragAndDrop(elementToDrag,target);", elementToDrag, target);
		   				objHTMLFunction.ReportPassFail("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">"
					+ strObjectName + "</i> Drag & Drop", "true", "true");
		   		return true;
		  }catch(Exception objSourceWebElement1){
			  strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objSourceWebElement1, true);
			  objHTMLFunction.ReportPassFail("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>"
								+ " couldn't be Drag & Dropped. <br> Error message=>" + strErrorMsg, "true", "false");
			  	return false;
		  }
		}
	// =============== DRAG AND DROP USING JavaScript Built In Function ===========================================
	
	public static boolean dragAndDropOneServoRBElement(String elementToDrag, String bucket)
			throws Exception {
		try {
			delayFor(1);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("Servo.vent.trigger('insights:reports:builder:autoadd', ['" + elementToDrag + "'], '" + bucket + "');");
			objHTMLFunction.ReportPassFail("<i title=\"Element \"> " + elementToDrag + " Was</i> <b>Dragged & Dropped.</b>", "true", "true");
			return true;
		} catch (Exception objSourceWebElement1) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objSourceWebElement1, true);
			objHTMLFunction.ReportPassFail("<i title=\"Element \"> " + elementToDrag + "</i>"
					+ "<b>couldn't be Dragged & Dropped.</b> <br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}
	
	// =============== DRAG AND DROP Multiple Elements USING JavaScript Built In Function ===========================================
	public static boolean dragAndDropMultipleServoRBElements(String[] elementsToDrag, String bucket)
			throws Exception {
		try {
			delayFor(1);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("Servo.vent.trigger('insights:reports:builder:autoadd', ['" + String.join("','", elementsToDrag) + "'], '" + bucket + "');");
			objHTMLFunction.ReportPassFail("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + String.join("','", elementsToDrag).replace("','", " - ") + "</i> <br> <b>Dragged & Dropped.</b>", "true", "true");
			return true;
		} catch (Exception objSourceWebElement1) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objSourceWebElement1, true);
			objHTMLFunction.ReportPassFail("<i title=\"" + String.join("','", elementsToDrag).replace("','", "<br>") + "</i>"+ "<br> <b>couldn't be Dragged & Dropped.</b> <br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}
	
	

	// ==================================SELECT VALUE FROM LISTBOX BY VISIBLE TEXT============================================
	public boolean selectValueByValueFromList(String strElementXPATH, String strObjectName, String strInputValue)
			throws Exception {
		try {
			WebElement objWebElement = findElementNReturn(strObjectXPATH);

			Select objSelectCountry = new Select(objWebElement);
			objSelectCountry.selectByValue(strInputValue);
			objHTMLFunction.ReportPassFail("<B>" + strInputValue + "</B> selected from the element <i title=\""
					+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>", "true", "true");
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			objHTMLFunction.ReportPassFail("Error while selecting <B>" + strInputValue + "</B> from the element "
					+ strObjectName + ".<br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}

	// ========================================TYPE VALUE IN OBJECT=================================================
	public Boolean selectNTypeValue(WebElement objWebElement, String strObjectName, String strInputValue)
			throws Exception {
		try {
			
			objWebElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), strInputValue);
			
			objHTMLFunction.ReportPassFail("Value '" + strInputValue + "' typed in element <i title=\""
					+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> after clearing the text",
					"true", "true");
			
			return true;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			

			objHTMLFunction.ReportPassFail("Unable to clear and type value '<B>" + strInputValue + "</B>' in element "
					+ strObjectName + ".<br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}
	}
	
	// ========================================Send Tab Keys=================================================

	public void performTABkey() {
		Actions actionObject = new Actions(driver);
		actionObject.sendKeys(Keys.TAB).perform();
	}
	
	public static void performENTERkey() {
		Actions actionObject = new Actions(driver);
		actionObject.sendKeys(Keys.ENTER).perform();
	}
	
	public static void performDELETEkey() {
		Actions actionObject = new Actions(driver);
		actionObject.sendKeys(Keys.DELETE).perform();
	}
	

	// ========================================Verify Text Availability=======================================
	
	public boolean verifyTextAvailability(WebElement objWebElement, String strObjectName) throws Exception {
		String strDisplayedText = "";
		try {
			String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
			strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
			strDisplayedText = objWebElement.getText().trim().replaceAll("\\s+", " ");
			boolean result = true;

			int length = strDisplayedText.length();

			if (length > 1) {
				
				objHTMLFunction.ReportPassFail(
						"Verification of the text displayed in the element <i title=\""
								+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>",
						strDisplayedText, "Length is " + length);
			} else {
				
				objHTMLFunction.ReportPassFail(
						"Verification of the text displayed in the element <i title=\""
								+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>",
						strDisplayedText, "Length is " + length);
				result = false;
			}

			return result;
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
		
			objHTMLFunction.ReportPassFail(
					"Error while fetching the text from the element " + strObjectName + "</i>.<br> Error message=>",
					"true", "false");
			return false;
		}
	}
	
	// ========================================Verify Not Equals=================================================

	@Test(enabled = false)
	public void verifyNotEquals(String s1, String s2, String msg) throws Exception {
		try {
			Assert.assertNotEquals(s1, s2);
			objHTMLFunction.ReportPassFail("Verification of the text displayed in the element <i title=\""
					+ strObjectXPATH.replace("\"", "'") + "\">" + msg + "</i>", s1, s2);
			

		} catch (Error e) {
			verificationErrors.append(e.getMessage() + ", msg" + msg);

			objHTMLFunction.ReportPassFail("Verification of the text displayed in the element <i title=\""
					+ strObjectXPATH.replace("\"", "'") + "\">" + msg + "</i>", s1, s2);

			
			Reporter.log(msg + ", " + e.getMessage());
			logger.info(msg + "," + e.getMessage() + "(:getMessage)");
			logger.info(msg + "," + e.getCause() + "(:getCause)");
		}
	}
	
	// ========================================Highlight Text In Element=================================================

	public Boolean heighlightTextareaInElement(WebElement objWebElement, String strObjectName) throws Exception {
		try {
			objWebElement.sendKeys(Keys.chord(Keys.CONTROL, "a"));

			objHTMLFunction.ReportPassFail("Highlight Text In Element <br>" + strObjectName, "True", "True");

			return true;
		} catch (Exception objException) {
			objHTMLFunction.ReportPassFail("Highlight Text In Element <br>" + strObjectName, "True", "False");

			return false;
		}
	}
// ========================================Highlight Text In Element=================================================

	public static Boolean heighlightTextareaInElement(String strElementXPATH, String strObjectName) throws Exception {
		strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		try {
			WebElement objWebElement = findElementNReturn(strObjectXPATH);
			objWebElement.sendKeys(Keys.chord(Keys.COMMAND, "a"));

			objHTMLFunction.ReportPassFail("Highlight Text In Element <br>" + strObjectName, "True", "True");

			return true;
		} catch (Exception objException) {
			objHTMLFunction.ReportPassFail("Highlight Text In Element <br>" + strObjectName, "True", "False");

			return false;
		}
	}
	// ========================================Cut Text From Element To ClipBoard=================================================
	
	@Test(enabled = false)
	public Boolean cutTextFromElementToClipBoard(WebElement objWebElement, String strObjectName) throws Exception {
		try {
			objWebElement.sendKeys(Keys.chord(Keys.CONTROL, "x"));

			objHTMLFunction.ReportPassFail("Cut Text From Element <br>" + strObjectName, "True", "True");

			return true;
		} catch (Exception objException) {
			objHTMLFunction.ReportPassFail("Cut Text From Element <br>" + strObjectName, "True", "False");

			return false;
		}
	}

	@Test(enabled = false)
	public Boolean pasteTextFromClipBoardToElement(WebElement objWebElement, String strObjectName) throws Exception {
		try {
			objWebElement.sendKeys(Keys.chord(Keys.CONTROL, "v"));

			objHTMLFunction.ReportPassFail("Paste Text Into Element <br>" + strObjectName, "True", "True");

			return true;
		} catch (Exception objException) {
			objHTMLFunction.ReportPassFail("Paste Text Into Element <br>" + strObjectName, "True", "False");

			return false;
		}
	}

	public void clickEnter() throws Exception {

		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	public void clickEscape() throws Exception {

		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_ESCAPE);

		robot.keyRelease(KeyEvent.VK_ESCAPE);
	}
	
	public void fullScreenWindow_Mac() throws Exception 
	{
		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_META);

		robot.keyPress(KeyEvent.VK_CONTROL);

		robot.keyPress(KeyEvent.VK_F);

		robot.keyRelease(KeyEvent.VK_F);

		robot.keyRelease(KeyEvent.VK_CONTROL);

		robot.keyRelease(KeyEvent.VK_META);
	}
	
	public void clickDelete() throws Exception {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_BACK_SPACE);

		robot.keyRelease(KeyEvent.VK_BACK_SPACE);
	}
	
	public void clickTab() throws Exception {

		Robot robot = new Robot();

		robot.keyPress(KeyEvent.VK_TAB);

		robot.keyRelease(KeyEvent.VK_TAB);
	}

	// ==================================VERIFY PART OF TEXT DISPLAYED IN AN ELEMENT============================================
	public boolean verifyPartOfTextInElement(WebElement objWebElement, String strObjectName, String strExpectedText)
			throws Exception {
		String strDisplayedText = "";
		try {
			strDisplayedText = objWebElement.getText().trim().replaceAll("\\s+", " ");
			String strObjectXPATH = "";
			if (objWebElement.toString().contains("->")) {
				strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
				strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
			}

			if (strDisplayedText.contains(strExpectedText)) {
				
				objHTMLFunction.ReportPassFail("Verification of the expected text=>" + strExpectedText
						+ " matched with the actual text=>" + strDisplayedText + " displayed in the element <i title=\""
						+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>", "True", "True");
			} else {
				
				objHTMLFunction.ReportPassFail(
						"Verification of the text displayed in the element <i title=\""
								+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>",
						strExpectedText, strDisplayedText);
			}
			
			return strDisplayedText.equalsIgnoreCase(strExpectedText);
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			
			objHTMLFunction.ReportPassFail(
					"Error while fetching the text from the element " + strObjectName + "</i>.<br> Error message=>",
					"true", "false");
			return false;
		}
	}

	// ==================================VERIFY PART OF TEXT DISPLAYED IN AN ELEMENT============================================
	public boolean verifyPartOfTextInElement(String strElementXPATH, String strObjectName, String strExpectedText)
			throws Exception {
		String strDisplayedText = "";
		strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
		try {
			WebElement objWebElement = findElementNReturn(strObjectXPATH);
			strDisplayedText = objWebElement.getText().trim().replaceAll("\\s+", " ");

			if (strDisplayedText.contains(strExpectedText)) {
				
				objHTMLFunction.ReportPassFail("Verification of the expected text=>" + strExpectedText
						+ " matched with the actual text=>" + strDisplayedText + " displayed in the element <i title=\""
						+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>", "True", "True");
			} else {
				
				objHTMLFunction.ReportPassFail(
						"Verification of the text displayed in the element <i title=\""
								+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>",
						strExpectedText, strDisplayedText);
			}
			
			return strDisplayedText.equalsIgnoreCase(strExpectedText);
		} catch (Exception objException) {
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			
			objHTMLFunction.ReportPassFail("Error while fetching the text from the element " + "<i title=\""
					+ strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>" + "</i>.<br> Error message=>",
					"true", "false");
			return false;
		}
	}

	public static String returnsDateWithRandomNumber() {
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		currentDate.add(Calendar.DAY_OF_MONTH, 30);
		String futureDate = formatter.format(currentDate.getTime());
		//return (futureDate + " " + d);
		return (futureDate);
	}

	public static void getCurrentDateAndTime() {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		System.out.println("TimeStamp: " + timeStamp);
	}

	public static String returnCurrentDateAndTime() {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		return timeStamp;
	}

	public static String dateWithRand() {
		String withDate = Utils.returnsDateWithRandomNumber();
		return withDate;
	}

	public static String currentDate() {
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		String currentDateFormat = formatter.format(currentDate.getTime());
		return currentDateFormat;
	}

	public static String dateFormater(String actualDate) throws ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(actualDate);
		String actualFormatedDate = new SimpleDateFormat("MM/dd/yyyy").format(date);
		return actualFormatedDate;
	}

	public static String futureDate() {
		Calendar currentDate = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		currentDate.add(Calendar.DAY_OF_MONTH, 30);
		String futureDate = formatter.format(currentDate.getTime());
		return futureDate;
	}

	public static void click(WebElement element) throws NullPointerException {
		ready(element).click();
	}

	public static void clear(WebElement element) throws NullPointerException {
		ready(element).clear();
	}

	public static void sendKeys(WebElement element, String text) throws NullPointerException {
		ready(element).sendKeys(text);
	}

	protected static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver d) {
		driver = d;
	}

	public static WebElement ready(WebElement element) throws NullPointerException {
		WebDriver driver = getDriver();
		if (driver != null) {
			WebDriverWait wait = new WebDriverWait(driver, 20);
			return wait.until(ExpectedConditions.elementToBeClickable(element));
		} else {
			throw new NullPointerException("Utils: Please set driver before using.");
		}
	}
	
	public static void takeScreenShot(String fileName) {
		try {
			File scrnsht = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrnsht,
					new File(String.format("%s/assets/%s", System.getProperty("user.dir"), fileName)));
			System.out.println("screesnshot taken");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void perforrmClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).click().perform();
	}

	public static void customClick(WebElement element, int secondsToWait)
			throws NullPointerException, InterruptedException {

		for (int t = 0; t <= secondsToWait * 10; t++) {
			try {
				element.click();
				break;
			} catch (WebDriverException ex) {
				ex.printStackTrace();

			}
		}
	}

    public static boolean customSedndKeyReport(String strElementXPATH, int secondsToWait, String keyToSend,String strObjectName)
            throws Exception {
        strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
        try {
            for (int t = 0; t <= secondsToWait * 10; t++) {
                WebElement objWebElement = findClicableElementNReturn(strObjectXPATH);
                objWebElement.sendKeys(keyToSend);
                break;
            }
            objHTMLFunction.ReportPassFail("Value typed in element <i title=\"" + strObjectXPATH.replace("\"", "'")
                    + "\">" + strObjectName + "</i>=>" + keyToSend, "true", "true");
            return true;
        } catch (Exception e) {
            strErrorMsg = objCMNFunctions.GetExceptionNDisplay(e, true);
            objHTMLFunction.ReportPassFail("Value typed in element <i title=\"" + strObjectXPATH.replace("\"", "'")
                    + "\">" + strObjectName + "</i>=>" + keyToSend, "true", "false");
            return false;
        }
    }

    public static boolean customClearNTypeReport(String strElementXPATH, int secondsToWait, String keyToSend,String strObjectName)
            throws NullPointerException, InterruptedException,Exception {
        strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
        try {
            for (int t = 0; t <= secondsToWait * 10; t++) {
                WebElement objWebElement = findClicableElementNReturn(strObjectXPATH);

                objWebElement.sendKeys(keyToSend);
                break;
            }
            objHTMLFunction.ReportPassFail("Value typed in element <i title=\"" + strObjectXPATH.replace("\"", "'")
                    + "\">" + strObjectName + "</i>=>" + keyToSend, "true", "true");
            return true;
        } catch (Exception e) {
            strErrorMsg = objCMNFunctions.GetExceptionNDisplay(e, true);
            objHTMLFunction.ReportPassFail("Value typed in element <i title=\"" + strObjectXPATH.replace("\"", "'")
                    + "\">" + strObjectName + "</i>=>" + keyToSend, "true", "false");
            return false;
        }
    }

    public static boolean customClickReport(String strElementXPATH, String strObjectName,int secondsToWait)
            throws Exception {
        strObjectXPATH = replaceExternalDataInObject(strElementXPATH);
        try {
            for (int t = 0; t <= secondsToWait * 10; t++) {
                WebElement objWebElement = findClicableElementNReturn(strObjectXPATH);
                objWebElement.click();
                break;
            }
            objHTMLFunction.ReportPassFail(
                    "<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> clicked", "true",
                    "true");
            return true;

            } catch (Exception e) {
                strErrorMsg = objCMNFunctions.GetExceptionNDisplay(e, true);
                objHTMLFunction.ReportPassFail(strObjectName + " couldn't be clicked. <br> Error message=>" + strErrorMsg,
                        "true", "false");
            return false;
            }


    }

	public static void customSedndKeyReport(WebElement element, int secondsToWait, String keyToSend,String strObjectName)
			throws NullPointerException, InterruptedException,Exception {

		for (int t = 0; t <= secondsToWait * 10; t++) {
			try {
				element.sendKeys(keyToSend);
				objHTMLFunction.ReportPassFail("Value typed in element <i title=\"" + strObjectXPATH.replace("\"", "'")
						+ "\">" + strObjectName + "</i>=>" + keyToSend, "true", "true");
				break;
			} catch (WebDriverException ex) {
				ex.printStackTrace();
				objHTMLFunction.ReportPassFail("Value typed in element <i title=\"" + strObjectXPATH.replace("\"", "'")
						+ "\">" + strObjectName + "</i>=>" + keyToSend, "true", "true");

			}
		}
	}
	public static boolean customClickReport(WebElement element, String strObjectName,int secondsToWait)
			throws Exception {
	try{
		for (int t = 0; t <= secondsToWait * 10; t++) {
			try {
				element.click();
				break;
			} catch (WebDriverException ex) {
				ex.printStackTrace();

			}
		}
		objHTMLFunction.ReportPassFail(
				"<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> clicked", "true",
				"true");
		return true;
	}catch(Exception objException) {
	strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
	objHTMLFunction.ReportPassFail(strObjectName + " couldn't be clicked. <br> Error message=>" + strErrorMsg,
			"true", "false");
	return false;
	}
	}
	public static void customSedndKey(WebElement element, int secondsToWait, String keyToSend)
			throws NullPointerException, InterruptedException {

		for (int t = 0; t <= secondsToWait * 10; t++) {
			try {
				element.sendKeys(keyToSend);
				break;
			} catch (WebDriverException ex) {
				ex.printStackTrace();

			}
		}
	}

	/*
	 * Scroll down
	 */
	public static void scrollDown(WebElement draggablePartOfScrollbar, int numberOfPixelsToDragTheScrollbarDown) {
		Actions dragger = new Actions(driver);

		// drag downwards
		try {
			// this causes a gradual drag of the scroll bar, 10 units at a
			// time
			dragger.moveToElement(draggablePartOfScrollbar).clickAndHold()
					.moveByOffset(0, numberOfPixelsToDragTheScrollbarDown).release().perform();

		} catch (Exception e1) {
		}
	}

	//Convert time to a 12 hour time
	public static String convertTimeTo12Hour(String time) {
		String newTime;
		String ampm;
		String[] hourMin = time.split(":");
		int hour = Integer.parseInt(hourMin[0]);
		if (hour > 11) {
			hour = hour - 12;
			ampm = " pm";
		} else {
			ampm = " am";
		}
		newTime = hour + ":" + hourMin[1] + ampm;
		return newTime;
	}
	
	
	// ############## Wait until JS and JQUERY to load #################
	
		public static boolean waitForJSandJQueryToLoad()
		{
		    WebDriverWait wait = new WebDriverWait(driver, 30);

		    // wait for jQuery to load
		    ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>()
		    {
		      @Override
		      public Boolean apply(WebDriver driver) 
		      {
		        try 
		        {
		          return ((Long)((JavascriptExecutor)driver).executeScript("return jQuery.active") == 0);
		        }
		        catch (Exception e) 
		        {
		          // no jQuery present
		          return true;
		        }
		      }
		    };

		    // wait for Javascript to load
		    ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() 
		    {
		      @Override
		      public Boolean apply(WebDriver driver) 
		      {
		        return ((JavascriptExecutor)driver).executeScript("return document.readyState")
		        .toString().equals("complete");
		      }
		    };

		  return wait.until(jQueryLoad) && wait.until(jsLoad);
		}
		
	// ==================================SELECT VALUE FROM LISTBOX BY VISIBLE TEXT============================================
    public static boolean selectValueByValueFromList1(String objWebElement, String strObjectName, String strInputValue)
            throws Exception {
      try {

        //String strObjectXPATH = ((objWebElement.toString()).split("->"))[1];
       // strObjectXPATH = strObjectXPATH.substring(0, strObjectXPATH.length() - 1);
        List<WebElement> objCLXNWebElements = driver.findElements(By.xpath(objWebElement));

        for (int i = 0; i <= objCLXNWebElements.size(); i++) {
          System.out.println(objCLXNWebElements.get(i).getText());
          if(objCLXNWebElements.get(i).getText().equals(strInputValue)){
            objCLXNWebElements.get(i).click();
            break;
          }
          
        }
        objHTMLFunction.ReportPassFail("<B>" + strInputValue + "</B> selected from the element <i title=\""
            + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i>", "true", "true");
        return true;
    } catch (Exception objException) {
      objHTMLFunction.ReportPassFail("Error while selecting <B>" + strInputValue + "</B> from the element "
          + strObjectName + ".<br> Error message=>" + strErrorMsg, "true", "false");
      return false;
    }  
         
    }

    //*************** FluentWaitForSingleElement ********************
    
    public WebElement fluentWaitById_visibility(String id){
    	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).
                withTimeout(5, TimeUnit.SECONDS).
                pollingEvery(1000,TimeUnit.MILLISECONDS);
        return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id(id))));
    }
    public WebElement fluentWaitById_clickibility(String id){
    	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).
                withTimeout(5, TimeUnit.SECONDS).
                pollingEvery(1000,TimeUnit.MILLISECONDS);
        return wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.id(id))));
    }
    public WebElement fluentWaitByXPath_visibility(String strElementXPATH){
    	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).
                withTimeout(5, TimeUnit.SECONDS).
                pollingEvery(1000,TimeUnit.MILLISECONDS);
        return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(strElementXPATH))));
    }
    public WebElement fluentWaitByXPath_clickibility(String strElementXPATH){
    	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).
                withTimeout(5, TimeUnit.SECONDS).
                pollingEvery(1000,TimeUnit.MILLISECONDS);
        return wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(strElementXPATH))));
    }
    public WebElement fluentWaitByCSS_visibility(String css){
    	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).
                withTimeout(5, TimeUnit.SECONDS).
                pollingEvery(1000,TimeUnit.MILLISECONDS);
        return wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(css))));
    }
    public WebElement fluentWaitByCSS_clickibility(String css){
    	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).
                withTimeout(5, TimeUnit.SECONDS).
                pollingEvery(1000,TimeUnit.MILLISECONDS);
        return wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(css))));
    }
    
  //*************** FluentWaitForListOfElements ********************
    
    public WebElement fluentWaitById_visibility_list(String id){
    	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).
                withTimeout(5, TimeUnit.SECONDS).
                pollingEvery(1000,TimeUnit.MILLISECONDS);
        return (WebElement) wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.id(id))));
    }
    
    public WebElement fluentWaitByXPath_visibility_list(String strElementXPATH){
    	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).
                withTimeout(5, TimeUnit.SECONDS).
                pollingEvery(1000,TimeUnit.MILLISECONDS);
    	return (WebElement) wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath(strElementXPATH))));
    }
    
    public WebElement fluentWaitByCSS_visibility_list(String css){
    	Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).
                withTimeout(5, TimeUnit.SECONDS).
                pollingEvery(1000,TimeUnit.MILLISECONDS);
    	return (WebElement) wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.cssSelector(css))));
    }


    public static boolean verifyIsDiplayed(String strElementXPATH,String strObjectName) throws Exception{

		strObjectXPATH = replaceExternalDataInObject(strElementXPATH);

		try {
			WebElement objWebElement = findClicableElementNReturn(strObjectXPATH);
			if (objWebElement.isDisplayed() ) {
				objHTMLFunction.ReportPassFail(
						"<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> clicked", "true",
						"true");
				return true;
			}
		}catch(Exception objException){
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			objHTMLFunction.ReportPassFail("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName
					+ "</i>" + " couldn't be clicked. <br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}

		return false;
	}

	public static boolean verifyIsDiplayedWithVisibility(String strElementXPATH,String strObjectName) throws Exception{

		strObjectXPATH = replaceExternalDataInObject(strElementXPATH);

		try {
			WebElement objWebElement = driver.findElement(By.xpath(strObjectXPATH));
			if (objWebElement.isDisplayed() ) {
				objHTMLFunction.ReportPassFail(
						"<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> clicked", "true",
						"true");
				return true;
			}
		}catch(Exception objException){
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			objHTMLFunction.ReportPassFail("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName
					+ "</i>" + " is not visible. <br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}

		return false;
	}



	public static boolean verifyIsDisplayed(WebElement objWebElement,String strObjectName) throws Exception{


		try {
			if (objWebElement.isDisplayed() ) {
				objHTMLFunction.ReportPassFail(
						"<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName + "</i> clicked", "true",
						"true");
				return true;
			}
		}catch(Exception objException){
			strErrorMsg = objCMNFunctions.GetExceptionNDisplay(objException, true);
			objHTMLFunction.ReportPassFail("<i title=\"" + strObjectXPATH.replace("\"", "'") + "\">" + strObjectName
					+ "</i>" + " couldn't be clicked. <br> Error message=>" + strErrorMsg, "true", "false");
			return false;
		}

		return false;
	}

	public static boolean isDiplayed(WebElement webElement){

		if(webElement.isDisplayed() ){
			return true;
		}

		return false;
	}
    
    		// >>>>>-------------------------- DATE FUNCTIONS ----------------------------------<<<<<
    
  //================================week start date==========================
    
	public String Dateverification(){
		Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        int currentDate = c.get(Calendar.DAY_OF_MONTH);
        int mondayNo = c.get(Calendar.DAY_OF_MONTH)-c.get(Calendar.DAY_OF_WEEK)+2;
        c.set(Calendar.DAY_OF_MONTH,mondayNo);
        int pastDate = c.get(Calendar.DAY_OF_MONTH);
        SimpleDateFormat smm = new SimpleDateFormat("MMM dd, yyyy");
        System.out.println("Monday date====> " + smm.format(c.getTime()));
        System.out.println("Difference between current and Monday date ====>"+(currentDate-pastDate));
        String week_startdate= smm.format(c.getTime());
        
          return week_startdate;
	}
	
	//================================Current date("EEEE MMM DD")==========================
	
	public String CurrDateverification() throws ParseException{
		SimpleDateFormat smm = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curr_date=smm.format(date);
		Date MyDate = smm.parse(curr_date);
		smm.applyPattern("EEEE MMM dd");
		String WDate = smm.format(MyDate);
		
		return WDate;
	} 
	//================================Current date("MM/DD/YYYY")==========================
	public String CurrDateverificationgen() throws ParseException{				
		SimpleDateFormat smm = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String curr_date=smm.format(date);
		Date MyDate = smm.parse(curr_date);
		smm.applyPattern("MM/dd/yyyy");
		String currDate = smm.format(MyDate);
			
		return currDate;
	} 
	
	//================================Week start date normal format=========================
	public String Dateverificationnormal()
	{
		Calendar c = Calendar.getInstance();
	    c.setTime(new Date());
	    int currentDate = c.get(Calendar.DAY_OF_MONTH);
	    int mondayNo = c.get(Calendar.DAY_OF_MONTH)-c.get(Calendar.DAY_OF_WEEK)+2;
	    c.set(Calendar.DAY_OF_MONTH,mondayNo);
	    int pastDate = c.get(Calendar.DAY_OF_MONTH);
	    SimpleDateFormat smm = new SimpleDateFormat("MM/dd/yyyy");
	//  System.out.println("Monday date====> " + smm.format(c.getTime()));
	//  System.out.println("Difference between current and Monday date ====>"+(currentDate-pastDate));
	    String NormalFormatWeekStrtDt= smm.format(c.getTime());
	  
	    return NormalFormatWeekStrtDt;
	}
  		
  	public String todayName() throws ParseException
  	{
  		Calendar c = Calendar.getInstance();
  		c.add(Calendar.DAY_OF_WEEK, 0);
  		Date today = c.getTime();
  		SimpleDateFormat smm = new SimpleDateFormat("dd/MM/yyyy");
  		String today_day = smm.format(today);
  		Date MyDate = smm.parse(today_day);
  		smm.applyPattern("EEEE MMM dd");
  		String toDay = smm.format(MyDate);
  		String dayName = toDay.substring(0, 10);
  		int spacePos = dayName.indexOf(" ");
  		//if (spacePos > 0) {
  	    String todayIs = dayName.substring(0, spacePos - 0);
  	    System.out.println("Today Is ==> " + todayIs.toUpperCase());
  		//}
  	    return todayIs;
  	}
  	
  	public String todayDate() throws ParseException
  	{
  		SimpleDateFormat smm = new SimpleDateFormat("dd/MM/yyyy");
  		Date date = new Date();
  		Date tomorrow = new Date(date.getTime());
  		String curr_date=smm.format(tomorrow);
  		Date MyDate = smm.parse(curr_date);
  		smm.applyPattern("yyyy-MM-dd");
  		String nextDay = smm.format(MyDate);
  		System.out.println("Today Date is ==> " + nextDay.toUpperCase());
  		return nextDay;
  	}
  	
  	public String nextDayDate() throws ParseException
  	{
  		SimpleDateFormat smm = new SimpleDateFormat("dd/MM/yyyy");
  		Date date = new Date();
  		Date tomorrow = new Date(date.getTime() + (1000 * 60 * 60 * 24));
  		String curr_date=smm.format(tomorrow);
  						
  		Date MyDate = smm.parse(curr_date);
  		smm.applyPattern("MM/dd/yyyy");
  		String nextDay = smm.format(MyDate);
  		System.out.println("Tomorrow Date is ==> " + nextDay.toUpperCase());
  		return nextDay;
  	}
  	
  	public String nextDayName() throws ParseException
  	{
  		Calendar c = Calendar.getInstance();
  		c.add(Calendar.DAY_OF_WEEK, 1);
  		Date tomorrow = c.getTime();
  		SimpleDateFormat smm = new SimpleDateFormat("dd/MM/yyyy");
  		String tomorrow_day = smm.format(tomorrow);
  		Date MyDate = smm.parse(tomorrow_day);
  		smm.applyPattern("EEEE MMM dd");
  		String nextDay = smm.format(MyDate);
  		String dayName = nextDay.substring(0, 10);
  		int spacePos = dayName.indexOf(" ");
  		//if (spacePos > 0) {
  	    String tomorrowIs = dayName.substring(0, spacePos - 0);
  	    System.out.println("Tomorrow is ==> " + tomorrowIs.toUpperCase());
  		//}
  	    return tomorrowIs;
  	}
  	
  	public String assignmentCreationDay() throws ParseException
  	{
  		Calendar c = Calendar.getInstance();
	      c.setTime(new Date());
	      int currentDate = c.get(Calendar.DAY_OF_MONTH);
	      int mondayNo = c.get(Calendar.DAY_OF_MONTH)-c.get(Calendar.DAY_OF_WEEK)+2;
	      c.set(Calendar.DAY_OF_MONTH,mondayNo);
	      int pastDate = c.get(Calendar.DAY_OF_MONTH);
	      SimpleDateFormat smm = new SimpleDateFormat("MM/dd/yyyy");
	      smm.applyPattern("EEEE MMM dd");
	      System.out.println("Difference between current and Assignment Creation Date Is ====> "+(currentDate-pastDate));
	      String NormalFormatWeekStrtDt= smm.format(c.getTime());
	      int spacePos = NormalFormatWeekStrtDt.indexOf(" ");
  	    String assignmentDayIs = NormalFormatWeekStrtDt.substring(0, spacePos - 0);
          System.out.println("Day Of Assignment Creation Is ====> " +assignmentDayIs);
          return assignmentDayIs;
  	}
  	
  	public String assignmentCreationDate() throws ParseException
  	{
  		Calendar c = Calendar.getInstance();
  	    c.setTime(new Date());
  	    int currentDate = c.get(Calendar.DAY_OF_MONTH);
  	    int mondayNo = c.get(Calendar.DAY_OF_MONTH)-c.get(Calendar.DAY_OF_WEEK)+2;
  	    c.set(Calendar.DAY_OF_MONTH,mondayNo);
  	    int pastDate = c.get(Calendar.DAY_OF_MONTH);
  	    SimpleDateFormat smm = new SimpleDateFormat("MMM dd, yyyy");
  	    System.out.println("Monday date====> " + smm.format(c.getTime()));
  	    System.out.println("Difference between current and Monday date ====>"+(currentDate-pastDate));
  	    String week_startdate= smm.format(c.getTime());
  	    
  	    return week_startdate;
  	}

	// ==================================LOG PASS/FAIL REPORT============================================
	public static void logReport(String strValue,String expectedResult, String actualResult)
			throws Exception {

			objHTMLFunction.ReportPassFail("<B>" + strValue + "</B>  <i title=\""
					+ strValue + "\">" + strValue + "</i>", expectedResult, actualResult);


	}



	// >>>>>--------------------------------------------------------------------------------<<<<<


 // ===========================MAIN METHOD==============================================
 		public static void main(String[] args) {

 		}

  ///=========================Parse JSON and Store in HashMap ===================================================
	public static void storeJSON(String key,String jsonString,String jsonName){

		Object result = null;

		try {
			result = JSON.parse(jsonString);
			storeJSONObjectMap.put(key,result);

		} catch (JSONException e) {
			e.printStackTrace();
			try {
				objHTMLFunction.ReportPassFail(jsonName+"<B> json input String : " + jsonString + "</B> Exception "
                        + key + ".<br> Error message=>" + e.toString(), "true", "false");
			} catch (Exception e1) {
				e1.printStackTrace();

			}
		}
	}

	///=========================Get and Store argument value from stored JSONObject ===================================================

    public static void storeJSONValue(String key,String lookForKey, String arugment){

		Object result = null;

		try {
			result = JSON.args(storeJSONObjectMap.get(lookForKey),arugment);
			storeJSONObjectMap.put(key,result);



		} catch (JSONException e) {
			e.printStackTrace();
			try {
				objHTMLFunction.ReportPassFail(arugment+"<B> argument value stored in  : " + lookForKey + "</B> Exception "
						+ key + ".<br> Error message=>" + e.toString(), "true", "false");
			} catch (Exception e1) {
				e1.printStackTrace();

			}
		}

	}

	///=========================Retrieve value from stored JSONObject ===================================================

	public static Object getJSONValue(String key){

		Object result = null;

		try {

			result = storeJSONObjectMap.get(key);


		} catch (Exception e) {
			e.printStackTrace();
			try {
				objHTMLFunction.ReportPassFail("getJSONValue <B>  Retrieve Stored JSON value</B> " +
						"<br> Error message=>" + e.toString(), "true", "false");
			} catch (Exception e1) {
				e1.printStackTrace();

			}
		}

		return result;

	}

	///=========================Compare Two HashMaps ===================================================
	public static void compareHashMaps(HashMap<String,String> expected,HashMap<String,String> actual){

    	try {
			assertTrue(expected.equals(actual));
			objHTMLFunction.ReportPassFail(" HashMap Comparsion <B> Two HashMaps Equal </B> Exception" ,
					"true", "true");
		}catch(AssertionError ae){
			try {
				objHTMLFunction.ReportPassFail(" HashMap Comparsion <B> Two HashMap are not Equal </B> Exception" , "true", "false");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		catch(Exception e){
			try {
				objHTMLFunction.ReportPassFail(" HashMap Comparsion <B> Two HashMap are not Equal </B> Exception" , "true", "false");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	///=========================Compare Two JSONObects ===================================================
	/*public static void compareJSONObject(String expected,String actual){

		final ObjectMapper mapper = new ObjectMapper();

		try {
			final JsonNode tree1 = mapper.readTree(expected);
			final JsonNode tree2 = mapper.readTree(actual);
			assertTrue(tree1.equals(tree2));
			objHTMLFunction.ReportPassFail(" JSON Object Comparsion <B> are Equal </B> Exception" ,
					"true", "true");
		}catch(AssertionError ae){
			try {
				ae.printStackTrace();
				objHTMLFunction.ReportPassFail(" JSON Object Comparsion <B>  are not Equal </B> Exception" , "true", "false");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		catch(Exception e){
			try {
				e.printStackTrace();
				objHTMLFunction.ReportPassFail(" JSON Object Comparsion <B> Two JSON are not Equal </B> Exception : "+e.toString() , "true", "false");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}*/

	public static void compareJSONObject(String nameOfComparsion,String expected,String actual){

		System.out.println("expected : "+expected);
		System.out.println("actual : "+actual);

		try {

			JSONAssert.assertEquals(expected, actual, true);
			objHTMLFunction.ReportPassFail(nameOfComparsion +"  Object Comparsion <B> are Equal </B> " ,
					"true", "true");
		}catch(AssertionError ae){
			try {
				ae.printStackTrace();
				objHTMLFunction.ReportPassFail(nameOfComparsion +" JSON Object Comparsion <B>  are not Equal </B> Exception" , "true", "false");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		catch(Exception e){
			try {
				e.printStackTrace();
				objHTMLFunction.ReportPassFail(" JSON Object Comparsion <B> Two JSON are not Equal </B> Exception : "+e.toString() , "true", "false");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}

	///=========================Compare String ===================================================
	public static void compareStringObject(String nameOfComparsion,String expected,String actual){

		System.out.println("expected : "+expected);
		System.out.println("actual : "+actual);

		try {

			assertEquals(expected.trim(),actual.trim());
			objHTMLFunction.ReportPassFail(nameOfComparsion+" Object Comparsion <B> are Equal </B> " ,
					expected, actual);
		}catch(AssertionError ae){
			try {
				ae.printStackTrace();
				objHTMLFunction.ReportPassFail(nameOfComparsion +"  Object Comparsion <B>  are not Equal </B> Exception" ,
						expected, actual);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		catch(Exception e){
			try {
				e.printStackTrace();
				objHTMLFunction.ReportPassFail(nameOfComparsion +"  Exception <B> Two String Comparsion </B> Exception : "+e.getMessage() ,
						"true", "false");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

	}


	///=========================API - Get User Token and Store it ===================================================

    private static LoginOutputVO apiTokenObj = null;
	private static String apiHost = null;
	public static LoginOutputVO setApiUserToken(String hostname,String username,String password){

		ObjectConversion objectConversion = new ObjectConversion();

		HttpResponse httpResponse = null;

		String servceURL = new String("http://"+hostname+":8080/xaxis/acm/login");

		//set API Hostname
		apiHost = hostname;


		HashMap<String,String> headerMap = new HashMap<String,String>();
		headerMap.put("Content-type","application/json");

		LoginRequestVO loginReqVO = new LoginRequestVO(username,password);

		httpResponse = HttpUtility.triggerPost(HttpUtility.builtURI(servceURL,null),headerMap,
				objectConversion.convertObjectToString(loginReqVO));

		try {

			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				BufferedReader rd = new BufferedReader(
						new InputStreamReader(httpResponse.getEntity().getContent()));

				StringBuffer strBuffer = new StringBuffer();
				String line = "";
				while ((line = rd.readLine()) != null) {
					strBuffer.append(line);
				}

				System.out.println("Api Token Details : "+strBuffer.toString());

				if (!(strBuffer.toString() == null)) {

					apiTokenObj = new LoginOutputVO();
					apiTokenObj = objectConversion.convertLoginOutStringToVO(strBuffer.toString());
				}else{
					objHTMLFunction.ReportPassFail(" API MI_Login <B> Failed, responsue is null </B> Exception" ,
							"true", "false");
				}


			} else {
				System.out.println("login failed.");
				objHTMLFunction.ReportPassFail(" API MI_Login <B> Failed </B> Exception" ,
						"true", "false");

			}
		}catch(Exception e){
			e.printStackTrace();
			try {
				objHTMLFunction.ReportPassFail(" API MI_Login <B> General </B> Exception" ,
                        "true", "false");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}

		return apiTokenObj;
	}

	///=========================API - Get Tagr TAG ID ===================================================
	public static String getTagID(String tagID){

		HttpGet httpGetVO = null;
		HttpResponse httpResponse = null;

		HashMap<String,String> serviceURLParameters = new HashMap<String,String>();

		serviceURLParameters.put("tagrTagId",tagID);

		try {

			httpGetVO = buildGetRequestObject(apiHost, RequestTypes.getTagId.getServiceURL(),
					serviceURLParameters,null);
			httpGetVO.addHeader("AcmToken",apiTokenObj.getAccess_token());
			httpResponse = HttpUtility.sendGet(httpGetVO);
			objHTMLFunction.ReportPassFail(" Get Tagid <B> Success no </B> Exception" ,
					"true", "true");

			return HttpUtility.parseHttpReponse(httpResponse);


		}catch(Exception e){

			try {
				objHTMLFunction.ReportPassFail(" Get Tagid <B> Failed. </B> Exception + \".<br> Error message=>\" "
								+e.toString()+ "</br>" ,
                        "true", "false");
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
		return null;
	}



	///=========================Convert HashMap into String ===================================================
     public static void convertMapToJSONString(HashMap<String,String> map){

		//return ObjectConversion.convertHashMapObjToString(map);
	 }

}
