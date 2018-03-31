package com.mplatform.framework.base;

import com.mplatform.framework.controller.ApplicationController;
import com.mplatform.framework.controller.MI_ApplicationController;
import io.appium.java_client.AppiumDriver;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriverLogLevel;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;


import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class ScriptBase
{


	Properties objProperties = new Properties();
	{
		try
		{
			//if(StringUtils.contains(System.getProperty("env").toLowerCase()))


			if(StringUtils.contains(System.getProperty("project"),"minsights")) {

				switch (System.getProperty("env")) {
					case "QA1":
						//objProperties.load(new FileReader(System.getProperty("user.dir") + "/Config/QA1_CONFIG.properties"));
						objProperties.load(new FileReader(System.getProperty("user.dir") +File.separator
								+"Config"+File.separator+"minsights"+File.separator+"QA1_CONFIG.properties"));
						break;
					case "INT":
						//objProperties.load(new FileReader(System.getProperty("user.dir") + "/Config/INT_CONFIG.properties"));
						objProperties.load(new FileReader(System.getProperty("user.dir") +File.separator
								+"Config"+File.separator+"minsights"+File.separator+"INT_CONFIG.properties"));
						break;
					case "STG":
						//objProperties.load(new FileReader(System.getProperty("user.dir") + "/Config/STG_CONFIG.properties"));
						objProperties.load(new FileReader(System.getProperty("user.dir") +File.separator
								+"Config"+File.separator+"minsights"+File.separator+"STG_CONFIG.properties"));
						break;
					case "DEV":
						//objProperties.load(new FileReader(System.getProperty("user.dir") + "/Config/DEV_CONFIG.properties"));
						objProperties.load(new FileReader(System.getProperty("user.dir") +File.separator
								+"Config"+File.separator+"minsights"+File.separator+"DEV_CONFIG.properties"));
						break;
					default:
						System.out.println("Pass correct environment parameter -Devn");
						System.exit(1);

				}
			}else{

				objProperties.load(new FileReader(System.getProperty("user.dir") + "/Config/CONFIG.properties"));
			}


		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

   /* // Code, to get mInsights User Credentials
    HashMap<String,MI_UserCredentialsVO> mInsightsUserCredentailMap = null;
	FileReader mInsights_user_credentials = null;	{

	     if(StringUtils.contains(System.getProperty("project"),"minsights")){

			 try {


				 switch(System.getProperty("env")){
					 case "QA1" :
						 mInsights_user_credentials = new FileReader(System.getProperty("user.dir")+
								 File.separator+"minsights_userCredentails"+File.separator+"qa1_minsights_user_credentails.json");
						 break;
					 case "INT" :
						 mInsights_user_credentials = new FileReader(System.getProperty("user.dir")+
								 File.separator+"minsights_userCredentails"+File.separator+"int_minsights_user_credentails.json");
						 break;
					 case "STG" :
						 mInsights_user_credentials = new FileReader(System.getProperty("user.dir")+
								 File.separator+"minsights_userCredentails"+File.separator+"stg_minsights_user_credentails.json");
						 break;
					 case "DEV" :
						 mInsights_user_credentials = new FileReader(System.getProperty("user.dir")+
								 File.separator+"minsights_userCredentails"+File.separator+"dev_minsights_user_credentails.json");
						 break;
					 default:
						 System.out.println("minsights user credentails file is not loaded.");
						 //System.exit(1);

				 }

				 mInsightsUserCredentailMap = Helper.buildMinsightsCredetentails(mInsights_user_credentials);

			 } catch (FileNotFoundException e) {
				 System.out.println("minsights user credentails file is not find exception.");
			 	e.printStackTrace();
			 }

		 }




	}
*/

	/*public String mInsightsTestData = null;
	public static HashMap<String,String> readMinsightsTestData(String testDataFileName,String dataColumnName){

		return Helper.readTestDataFile(testDataFileName,dataColumnName);
	}*/



	//==========================BROWSER VARIABLES============================================
	public static final String CHROME_DRIVER_KEY = "webdriver.chrome.driver";
	public static final String IE_DRIVER_KEY = "webdriver.ie.driver";
	public static final String IE_DRIVER_EXE = "IEDriverServer.exe";
	public static final String IE_DRIVER_LOG_FILE_PROPERTY = "webdriver.ie.driver.logfile";
	public static final String IE_DRIVER_LOG_LEVEL_PROPERTY = "webdriver.ie.driver.loglevel";
	public static final String JAVA_TEMP_DIR = "java.io.tmpdir";
	public static final String HTMLUNIT_DRIVER_KEY = "webdriver.htmlunit.driver";

	public static WebDriver driver = null;
	public static String browser = null;
	public ApplicationController mPlatform = null;
	public MI_ApplicationController mInsights= null;
	public String strDTParametersNValues = "";

	Logger log = Logger.getLogger("devpinoyLogger");

	public static HTMLFunctions htmlFunctions= null;

	GlobalPaths objGLPaths = null;
	public static String ConfigFile = null;
	//	public Properties config;

	public enum BROWSER
	{
		firefox, ie, chrome, androidweb, androidweb1, androidnative, iosweb, iosnative, htmlUnitDriver
	}

	@Parameters({"browser", "remoteurl","bversion","OS","useCloudEnv"})
	@BeforeSuite
	public void LaunchBrowser(String browser, String strDeviceURL, String bversion, String OS,  Boolean useCloudEnv) throws Exception
	{
		WebDriver localDriver = null;

		if(useCloudEnv==true)
		{
			String userName = AppConstant.SAUCE_USERNAME;
			String key = AppConstant.SAUCE_KEY;

			PropertyConfigurator.configure(System.getProperty("user.dir") + "/Config/log4j.properties");


			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("platform", OS);
			cap.setBrowserName(browser);
			cap.setCapability("version", bversion);
			cap.setCapability("name", "Remote File Upload using Selenium 2's FileDetectors");
			localDriver = new RemoteWebDriver(new URL("http://"+userName+":"+key+"@ondemand.saucelabs.com:80/wd/hub"), cap);
			((RemoteWebDriver) localDriver).setFileDetector(new LocalFileDetector());
		}
		else if(useCloudEnv==false)
		{

			objGLPaths = new GlobalPaths();
			GlobalPaths.strBrowserType = browser;
			BROWSER selectedBrowser = null;
			selectedBrowser = BROWSER.valueOf(browser);
			if (selectedBrowser == null) {
				throw new RuntimeException("Unknown browser variable specified!!!!!!");
			}
			ScriptBase.browser = browser;
			ScriptBase.ConfigFile = ConfigFile;


			switch(Helper.getOS()){
				case "win":
					System.setProperty("webdriver.chrome.driver", AppConstant.WinChromeDriverPath);
					System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")
							+File.separator+"Lib"+File.separator+"win"+File.separator+"geckodriver.exe");
					break;
				case "mac":
					System.setProperty("webdriver.chrome.driver", AppConstant.MacChromeDriverPath);
					System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")
							+File.separator+"Lib"+File.separator+"mac"+File.separator+"geckodriver");
					break;
				case "unix":
					System.setProperty("webdriver.chrome.driver", AppConstant.UnixChromeDriverPath);
					System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")
							+File.separator+"Lib"+File.separator+"lin"+File.separator+"geckodriver");
					break;

				default:
					System.out.println("Please check Operating System, We are supporting only Unix/Linux");
					System.exit(1);
			}

/*
			System.setProperty("webdriver.chrome.driver", AppConstant.ChromeDriverPath);
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")
					+File.separator+"Lib"+File.separator+"geckodriver.exe");*/

			DesiredCapabilities capabilities= null;

			ProfilesIni profile = null;
			FirefoxProfile firefoxprofile = null;

			try
			{
				switch (selectedBrowser)
				{
					case firefox:
					{
						firefoxprofile = new FirefoxProfile();
						firefoxprofile.setPreference("security.insecure_password.ui.enabled",false);
					/*capabilities=DesiredCapabilities.firefox();
					capabilities.setCapability("marionette", true);
					capabilities.setCapability("acceptInsecureCerts", true);
*/					localDriver = new FirefoxDriver(firefoxprofile);
						localDriver.manage().window().maximize();
						break;
					}
					case htmlUnitDriver:
					{
						localDriver = new HtmlUnitDriver();
						((HtmlUnitDriver) localDriver).setJavascriptEnabled(true);
						File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
						FileUtils.copyFile(scrFile, new File("/Users/ahmedneinae/Documents/mplatform-qa-automation/ScreenShot"));
						break;
					}
					case chrome:
					{
						System.setProperty(CHROME_DRIVER_KEY,  AppConstant.ChromeDriverPath);

						PropertyConfigurator.configure(System.getProperty("user.dir") + "/Config/log4j.properties");


						Map<String, Object> prefs = new HashMap<String, Object>();
						prefs.put("download.default_directory", AppConstant.DownloadedFiles);
						DesiredCapabilities caps = DesiredCapabilities.chrome();
						ChromeOptions options = new ChromeOptions();
						options.addArguments("--disable-infobars");
						//options.addArguments("-incognito");
						//options.addArguments("start-maximized");


						options.addArguments("--verbose");
						options.addArguments("--whitelisted-ips='10.90.101.183'");
						options.addArguments("--proxy-server=10.90.101.183:8080");

						//WebDriver driver = new ChromeDriver(options);
						//driver.get("http://10.90.101.183:8080/");



						options.setExperimentalOption("prefs", prefs);
						caps.setCapability(ChromeOptions.CAPABILITY, options);
						localDriver = new ChromeDriver(caps);
						localDriver.manage().window().maximize();
						options.addArguments("--start-maximized");
						break;

					}
					case ie:
					{
						String IELogFilePath = System.getProperty(JAVA_TEMP_DIR) + "ie_msg.log";
						System.setProperty(IE_DRIVER_LOG_FILE_PROPERTY, IELogFilePath);
						System.setProperty(IE_DRIVER_LOG_LEVEL_PROPERTY, InternetExplorerDriverLogLevel.DEBUG.name());
						System.setProperty(IE_DRIVER_KEY, IE_DRIVER_EXE);
						localDriver = new CustomIEDriver();
						break;
					}

					case androidweb:
					{
						String[] arrDeviceURL = strDeviceURL.split("##");
						capabilities = new DesiredCapabilities();
						capabilities = DesiredCapabilities.android();
					/*capabilities.setCapability("browserName", "chrome");
					capabilities.setCapability("platformName", "Android");
					capabilities.setCapability("platformVersion", "4.4.2");
					capabilities.setCapability("automationName", "Appium");
					capabilities.setCapability("device", "Android");
					capabilities.setCapability("udid", arrDeviceURL[1]);
					capabilities.setCapability("deviceName", arrDeviceURL[1]);
					capabilities.setCapability("app", "chrome");*/

						capabilities.setCapability("browserName", "Browser");
						capabilities.setCapability("platformName", "Android");
						capabilities.setCapability("platformVersion", "4.4.4");
						capabilities.setCapability("automationName", "Appium");
						capabilities.setCapability("device", "Android");
						capabilities.setCapability("udid", arrDeviceURL[1]);
						capabilities.setCapability("app", "chrome");

						//localDriver = new AppiumDriver(new URL("http://" + arrDeviceURL[0] + "/wd/hub"), capabilities);


						break;
					}

					case androidnative:
					{
						String[] arrDeviceURL = strDeviceURL.split("##");
						capabilities = new DesiredCapabilities();
						capabilities.setCapability(CapabilityType.BROWSER_NAME, "Android");
						capabilities.setCapability(CapabilityType.VERSION, "4.4.2");
						capabilities.setCapability("udid", arrDeviceURL[1]);
						capabilities.setCapability("deviceName", arrDeviceURL[1]);
						//		            capabilities.setCapability(CapabilityType.PLATFORM, "Android");
						capabilities.setCapability("platformName", "Android");
						capabilities.setCapability("appPackage", objProperties.getProperty("AppPackage"));
						capabilities.setCapability("appActivity", objProperties.getProperty("AppActivity"));
						//localDriver = new AppiumDriver(new URL("http://" + arrDeviceURL[0] + "/wd/hub"), capabilities);

						break;

					}
					case iosnative:
					{
						capabilities = new DesiredCapabilities();
						capabilities.setCapability("appium-version", "1.0");
						capabilities.setCapability("platformName", "iOS");
						capabilities.setCapability("platformVersion", "7.1");
						capabilities.setCapability("deviceName", "iPad");
						capabilities.setCapability("app", objProperties.getProperty(".AppPath"));
						//					capabilities.setCapability("ipa", objProperties.getProperty(".ipaPath"));
						capabilities.setCapability(CapabilityType.BROWSER_NAME, "safari");
						//localDriver = new AppiumDriver(new URL(objProperties.getProperty("RemoteDriverURL")), capabilities);
						break;
					}
					case iosweb:
					{
						String[] arrDeviceURL = strDeviceURL.split("##");

					/*DesiredCapabilities capabilities = new DesiredCapabilities();
					capabilities.setCapability("appium-version", "1.4.8");
					capabilities.setCapability("platformName", "iOS");
					capabilities.setCapability("platformVersion", "8.4");
					capabilities.setCapability("deviceName", "iPhone 6");*/
						//					capabilities.setCapability("ipa", objProperties.getProperty(".ipaPath"));
						//localDriver = new AppiumDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
						//localDriver = new AppiumDriver(new URL("http://" + arrDeviceURL[0] + "/wd/hub"), capabilities);
						//Thread.sleep(5000);
						DesiredCapabilities caps = new DesiredCapabilities();
						caps.setCapability("platformName", "iOS");
						caps.setCapability("platformVersion", "7.1"); //Replace this with your iOS version
						caps.setCapability("deviceName", "iPad Air"); //Replace this with your simulator/device version
						//caps.setCapability("app", "/Applications/Xcode.app/Contents/Developer/Platforms/iPhoneSimulator.platform/Developer/SDKs/iPhoneSimulator.sdk/Applications/Web.app"); //Replace this with app path in your system
						caps.setCapability("app", "/Users/ahmedneinae/Desktop/TestApp.app"); //Replace this with app path in your system
						localDriver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
						break;
					}

					default:
					{
						localDriver = new FirefoxDriver();
					}
				}
				log.debug("Opening "+browser+" Browser");
			}
			catch (Throwable exp)
			{
				Reporter.log("Exception in browser initialization!!! : " + exp.getMessage());
			}
		}
		ScriptBase.driver = localDriver;

		if(browser.contentEquals("chrome"))
		{
			//	Runtime.getRuntime().exec(objProperties.getProperty("ChromeAutoITPath"));
		}
		else if (browser.contentEquals("firefox"))
		{
			//	Runtime.getRuntime().exec(System.getProperty("user.dir") + "/AutoITFiles/FirefoxProxy.exe");
		}

		if(!browser.equalsIgnoreCase("androidnative") && !browser.equalsIgnoreCase("iosnative") && !browser.equalsIgnoreCase("iosweb"))
		{
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		}

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		htmlFunctions= new HTMLFunctions(ScriptBase.driver);
		htmlFunctions.CreateResultFile(System.getProperty("user.dir") + "/Results");
		//		log= Logger.getLogger("ReportBuilder");

	}

	public ApplicationController mPlatform()
	{
		if(mPlatform ==  null)
		{
			mPlatform = new ApplicationController(driver);
		}
		mPlatform.strParametersNValues = strDTParametersNValues;
		return mPlatform;
	}

	public MI_ApplicationController mInsights()
	{
		if(mInsights ==  null)
		{
			mInsights = new MI_ApplicationController(driver);
		}
		mInsights.strParametersNValues = strDTParametersNValues;
		return mInsights;
	}


	@Parameters({"TriggerEmail","EmailList"})
	@AfterSuite
	public void TearDownSuite(String TriggerEmail, String EmailList) throws Exception
	{
//		cleanTestSuite();
		driver.quit();
		htmlFunctions.ModuleStatusUpdate(true);
		log.debug("Closing Browser");
		if(TriggerEmail.equalsIgnoreCase("Y"))
		{
			objProperties.load(new FileReader(System.getProperty("user.dir") + "/Config/CONFIG.properties"));
			new MailHTMLReports(objProperties.getProperty(EmailList));
		}
	}

	public void reportingSetup(String  HTML_TestOrder, String HTML_Description) throws Exception
	{
		htmlFunctions.ReportPassFail("Scenario_"+HTML_TestOrder+"=>" + HTML_Description, "ModuleName", "ModuleName");
	}



	public Properties getObjProperties() {
		return objProperties;
	}





}