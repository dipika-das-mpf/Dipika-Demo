package com.mplatform.framework.base;

public class AppConstant 
{

	//=========================SAUCELAB PATHS=======================================
	
    public static final String SAUCE_USERNAME = "ahmedneinae";
    public static final String SAUCE_KEY = "0c0bd9ab-192a-46a5-b62c-9d433b356e3e";
		
	//=========================PATHS=======================================

	public static final String ChromeDriverPath = System.getProperty("user.dir") + "/Lib/chromedriver";
	public static final String WinChromeDriverPath = System.getProperty("user.dir") + "/Lib/win/chromedriver";
	public static final String MacChromeDriverPath = System.getProperty("user.dir") + "/Lib/mac/chromedriver";
	public static final String UnixChromeDriverPath = System.getProperty("user.dir") + "/Lib/lin/chromedriver";
	public static final String ChromeAutoITPath=System.getProperty("user.dir") + "/ChromeProxy.exe";
	public static final String FileUploadParentPath=System.getProperty("user.dir") + "/FilesToUpload/";
	public static final String SikuliImagesPath=System.getProperty("user.dir") + "/SikuliImages/";
	public static final String HTMLResultsPath=System.getProperty("user.dir") + "/Results";
	public static final String DownloadedFiles=System.getProperty("user.dir") + "/DownloadedFiles";
	
	//=========================MOBILE APPLICATION PROPERTIES================

	public static final String AppPackage = "com.android.calculator2";
	public static final String AppActivity = "com.android.calculator2.Calculator";

	//=========================ORACLE DATABASE CONNECTIVITY================

	public static final String DBURL = "jdbc:mysql://10.221.0.169:1521/SPRKEQST";
	public static final String Username = "cmsrep";
	public static final String Password = "CMSREPEQST";

	//==========================QUERYING DATABASE===========================

	public static final String Query = "select * from evtmsgjob.e_event";

}