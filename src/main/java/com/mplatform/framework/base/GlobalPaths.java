package com.mplatform.framework.base;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GlobalPaths 
{
//	public String strEnvironment = "Production";
	//=========================OBJECT REPOSITORY PATHS DEFINITION==============================	
	public static String strBrowserType = "";
	public static String strGLEnvironment = "";
	
	//=========================DATASHEET PATHS DEFINITION======================================
	public static String strXLInputDatasheetPath="";
	
	//=========================CHROME, IE DRIVER PATH=========================================
	public String strHTMLResultsPath = "";
	public String strXLDriverPath = "";
	public String strSikuliImagesPath = "";
	
	//==============================PROPERTIES OBJECT==========================================
	Properties objProperties = new Properties();
	
	public int intChromeMenubarHeight = 100;
	
	//=========================CONSTRUCTOR FOR GLOBAL PATHS====================================
	public GlobalPaths() throws FileNotFoundException, IOException
	{	
		objProperties.load(new FileReader(System.getProperty("user.dir") + "/Config/CONFIG.properties"));
		//=====================OBJECT REPOSITORY INITIALIZATION================================
		//strXLInputDatasheetPath = objProperties.getProperty("InputDatasheetPath");
		strXLInputDatasheetPath = System.getProperty("user.dir")+objProperties.getProperty("InputDatasheetPath");
		strHTMLResultsPath = objProperties.getProperty("HTMLResultsPath");
		strSikuliImagesPath = objProperties.getProperty("SikuliImagesPath");
		intChromeMenubarHeight = Integer.parseInt(objProperties.getProperty("ChromeMenubarHeight"));
		strGLEnvironment = objProperties.getProperty("Environment");
	}

	public static void main(String[] args) 
	{
		

	}
}