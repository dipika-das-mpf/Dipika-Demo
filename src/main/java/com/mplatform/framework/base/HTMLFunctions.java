package com.mplatform.framework.base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HTMLFunctions 
{
//	ComponentReusableFunctions objCRFunctions = new ComponentReusableFunctions();
	CommonFunctions objCMNFunctions = new CommonFunctions();
	
	static String strCurrentExecutionFolderPath;
	static String strResultsFilePath;
	static String strModuleResultsFilePath;
	static String strResultsFolder; 
	static String strOLDModuleName;
	static String strModuleStartDate;
	static String strStepResultsFileName;
	static String strModulePageLoadingTimings;
	
	static Date dtTestSuiteStartTime;
	static Date dtPrevStepExecTime;
	static Date dtModuleStartDate;
	
	static int intModuleCount=0;
	
	static Boolean blnModuleStatus;
	
	WebDriver objWebDriver = null;
	
	//===================================HTML REUSABLE FUNCTIONS CONSTRUCTOR==========================================
	public HTMLFunctions(WebDriver objTempWebDriver)
	{
		objWebDriver = objTempWebDriver;
	}
	
	@SuppressWarnings("static-access")
	public boolean CreateResultFile(String strResultsFolderPath) throws Exception
	{
		//=======================CREATING A HTML REPORT FILE==================
		SimpleDateFormat objSimpleDtFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		Date objDate = new Date();
		Calendar objCal = Calendar.getInstance();
		objCal.setTime(objDate);
		String strAppendDateTime = objSimpleDtFormat.format(new Date());
		strAppendDateTime = (strAppendDateTime.replace("/", "-").replace(":", "-")).trim();
		String arrDateTime[] = strAppendDateTime.split(" ");
		
		//this.strResultsFolder = strResultsFolderPath + "/" + arrDateTime[0];
		HTMLFunctions.strResultsFolder = strResultsFolderPath + "/" + strAppendDateTime.replace(" ", "_");
		File objFile = new File(strResultsFolderPath + "/" + arrDateTime[0]);
		if (!objFile.exists()) 
		{
			if (objFile.mkdir()) 
			{
				System.out.println("Directory is created!");
			} else 
			{
				System.out.println("Failed to create directory!");
			}
		}
		
		strResultsFolderPath = strResultsFolderPath + "/" + arrDateTime[0];
		HTMLFunctions.strCurrentExecutionFolderPath = strResultsFolderPath + "/" + strAppendDateTime.replace(" ", "_");
		
		File objFile1 = new File(strResultsFolderPath + "/" + strAppendDateTime.replace(" ", "_"));
		if (!objFile1.exists()) 
		{
			if (objFile1.mkdir()) 
			{
				System.out.println("Directory is created!");
			} else 
			{
				System.out.println("Failed to create directory!");
			}
		}
		
		//File objScreenshotFile = new File(strResultsFolderPath + "/" + arrDateTime[0] + "/Screenshots");
		File objScreenshotFile = new File(strResultsFolderPath + "/" + strAppendDateTime.replace(" ", "_") + "/Screenshots");
		if (!objScreenshotFile.exists()) 
		{
			if (objScreenshotFile.mkdir()) 
			{
				System.out.println("Screenshots directory is created!");
			} else 
			{
				System.out.println("Failed to create Screenshots directory!");
			}
		}			
		
		String strResultsFilePath, strModuleResultsFilePath;
		strResultsFilePath = strResultsFolderPath + "/" + strAppendDateTime.replace(" ", "_") + "/StepRunResult " + strAppendDateTime + ".html";
		HTMLFunctions.strStepResultsFileName = "StepRunResult " + strAppendDateTime + ".html";
		strModuleResultsFilePath = strResultsFolderPath + "/" + strAppendDateTime.replace(" ", "_") + "/ModuleRunResult " + strAppendDateTime + ".html";
		
		PrintWriter objWriterModules = new PrintWriter(strModuleResultsFilePath, "UTF-8");
		PrintWriter objWriter = new PrintWriter(strResultsFilePath, "UTF-8");
		
		objWriter.println("<html>");
		objWriter.println("<title>QA " + GlobalPaths.strGLEnvironment + " QA AUTOMATION STEPWISE RESULT " + strAppendDateTime + "</title>");
		objWriter.println("<body bgcolor = \"WHITE\">");
		objWriter.println("<h3 align = \"CENTER\" style = \"FONT-SIZE:20;COLOR:DARKBLUE\"> " + GlobalPaths.strGLEnvironment + " AUTOMATION SUITE EXECUTION STEPWISE RESULT</h3>");
		objWriter.println("<table border = \"1\" width = \"100%\" cellspacing=\"0\" cellpadding=\"12\" STYLE = \"BORDER-COLOR:BLACK\">");
		objWriter.println("<tr bgcolor = \"BLACK\">");
		objWriter.println("<th style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:14;COLOR:WHITE;WORD-BREAK:BREAK-ALL\" width = \"13%\" >MODULE NAME</th>");
		objWriter.println("<th style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:14;COLOR:WHITE;WORD-BREAK:BREAK-ALL\" width = \"30%\" >TEST CONDITION</th>");
		objWriter.println("<th style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:14;COLOR:WHITE;WORD-BREAK:BREAK-ALL\" width = \"20%\" >EXPECTED RESULT</th>");
		objWriter.println("<th style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:14;COLOR:WHITE;WORD-BREAK:BREAK-ALL\" width = \"20%\" >ACTUAL RESULT</th>");
		objWriter.println("<th style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:14;COLOR:WHITE;WORD-BREAK:BREAK-ALL\" width = \"7%\" >STATUS</th>");
		objWriter.println("<th style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:14;COLOR:WHITE;WORD-BREAK:BREAK-ALL\" width = \"10%\" >TIME TAKEN</th>");
		//objWriter.println("<th>SCREENSHOT</th>");
		objWriter.println("</tr>");	
		objWriter.close();
		
		objWriterModules.println("<html>");
		objWriterModules.println("<title> " + GlobalPaths.strGLEnvironment + " QA AUTOMATION MODULEWISE RESULT " + strAppendDateTime + "</title>");
		objWriterModules.println("<head>");
		objWriterModules.println("<script>");
		objWriterModules.println("function displayHideElement(strButton,strID)");
		objWriterModules.println("{");
		objWriterModules.println("strDispText = (document.getElementById(strButton).innerText).trim();");
		objWriterModules.println("if(strDispText == \"+\")");
		objWriterModules.println("{");
		objWriterModules.println("document.getElementById(strID).style.display=\"block\";");
		objWriterModules.println("document.getElementById(strButton).innerText=\" - \";");
		objWriterModules.println("}");
		objWriterModules.println("else");
		objWriterModules.println("{");
		objWriterModules.println("document.getElementById(strID).style.display=\"none\";");
		objWriterModules.println("document.getElementById(strButton).innerText=\" + \";");
		objWriterModules.println("}");
		objWriterModules.println("}");
		objWriterModules.println("</script>");
		objWriterModules.println("</head>");
		objWriterModules.println("<body bgcolor = \"lightgrey\">");
		objWriterModules.println("<h2 align = \"left\" style = \"FONT-SIZE:13;COLOR:BLACK\">Hi All,</h2>");
		objWriterModules.println("<h2 align = \"left\" style = \"FONT-SIZE:13;COLOR:BLACK\">Please find QA Automation Test Results In " + GlobalPaths.strGLEnvironment + ". Should you have any questions, please contact QA team.</h2>");
		objWriterModules.println("<h4 align = \"CENTER\" style = \"FONT-SIZE:20;COLOR:DARKBLUE\"> " + GlobalPaths.strGLEnvironment + " QA AUTOMATION SUITE EXECUTION MODULEWISE RESULT</h4>");
		objWriterModules.println("<center><table border = \"1\" width = \"75%\" cellspacing=\"0\" cellpadding=\"12\" STYLE = \"BORDER-COLOR:BLACK\">");
		objWriterModules.println("<tr bgcolor = \"#787878\">");
		objWriterModules.println("<th style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:14;COLOR:WHITE;WORD-BREAK:BREAK-ALL\" width = \"5%\" > </th>");
		objWriterModules.println("<th style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:14;COLOR:WHITE;WORD-BREAK:BREAK-ALL\" width = \"30%\" >MODULE NAME</th>");
		objWriterModules.println("<th style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:14;COLOR:WHITE;WORD-BREAK:BREAK-ALL\" width = \"35%\" >MODULE DESCRIPTION</th>");
		objWriterModules.println("<th style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:14;COLOR:WHITE;WORD-BREAK:BREAK-ALL\" width = \"12%\" >STATUS</th>");
		objWriterModules.println("<th style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:14;COLOR:WHITE;WORD-BREAK:BREAK-ALL\" width = \"18%\" >TIME TAKEN</th>");
		objWriterModules.println("</tr>");	
		objWriterModules.close();
		
		HTMLFunctions.strResultsFilePath = strResultsFilePath;
		HTMLFunctions.strModuleResultsFilePath = strModuleResultsFilePath;
		
		HTMLFunctions.dtPrevStepExecTime = new Date();
		HTMLFunctions.dtTestSuiteStartTime = new Date();
		return true;
	}

	public String TakeScreenshot() throws Exception
	{
		String strHTMLResultFolder, strAppendDateTimeUpdate, strScreenshotPath = "";
		
		strAppendDateTimeUpdate = objCMNFunctions.GetDateTimeStringWithSeconds();
		
//		String strResultsFolder = this.strResultsFolder;
		
		String strResultsFolder = HTMLFunctions.strCurrentExecutionFolderPath;
		
		boolean blnAleartExists = false;
	    WebDriverWait objWebDriverWait = new WebDriverWait(objWebDriver, 2);
	    try
	    {
	    	Alert objAlertExists = objWebDriverWait.until(ExpectedConditions.alertIsPresent());
	    	blnAleartExists = true;
	    }
	    catch(Exception e)
	    {
	    	
	    }
	    if(blnAleartExists == true)
	    {
	    	Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
	    	BufferedImage capture = new Robot().createScreenCapture(screenRect);
	    	ImageIO.write(capture, "bmp", new File(strResultsFolder + "/Screenshots/" + 
        			strAppendDateTimeUpdate + ".png"));
	    }
	    else
	    {
	        try
	        {
	            //WebDriver augmentedDriver = new Augmenter().augment(objWebDriver);
	
	            File objScreenshot = ((TakesScreenshot)objWebDriver).
	                                getScreenshotAs(OutputType.FILE);        	
	        	FileUtils.copyFile(objScreenshot, new File(strResultsFolder + "/Screenshots/" + 
	        			strAppendDateTimeUpdate + ".png"));
	        	//augmentedDriver.close();
	        }
	        catch(Exception e)
	        {
	        	System.out.println("Exception occured while taking screenshot: " + e);
	        }
	    }
	        		
		return "Screenshots/" + strAppendDateTimeUpdate + ".png";
	}
	
	@SuppressWarnings("static-access")
	public boolean ReportPassFail(String strStepCondition, String strExpectedResult, String strActualResult) throws Exception
	{
		synchronized (HTMLFunctions.class)
		{	
			SimpleDateFormat objSimpleDtFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
			Date objDate = new Date();
			Calendar objCal = Calendar.getInstance();
			objCal.setTime(objDate);
			String strAppendDateTime = objSimpleDtFormat.format(new Date());
			strAppendDateTime = (strAppendDateTime.replace("/", "-").replace(":", "-")).trim();
			
			PrintWriter objWriter = new PrintWriter(new BufferedWriter(new FileWriter(HTMLFunctions.strResultsFilePath, true)));
			PrintWriter objWriterModule = new PrintWriter(new BufferedWriter(new FileWriter(HTMLFunctions.strModuleResultsFilePath, true)));
			
			Date dtCurrDtTime = new Date();
			String strTimeDifferenceMsg = this.f_HTML_FindTimeDifferences(dtCurrDtTime, HTMLFunctions.dtPrevStepExecTime);
			//long intTimeDifference = dtCurrDtTime.getTime() - this.dtPrevStepExecTime.getTime();
		
			HTMLFunctions.dtPrevStepExecTime = new Date();
			
			objWriter.println("<tr align = \"CENTER\">");
			
			if(strExpectedResult.equalsIgnoreCase("ModuleName") && strActualResult.equalsIgnoreCase("ModuleName"))
			{	
				if(HTMLFunctions.strOLDModuleName != null && HTMLFunctions.strOLDModuleName!="")
				{
					this.ModuleStatusUpdate(false);
				}
				HTMLFunctions.strModulePageLoadingTimings = "";
				HTMLFunctions.dtModuleStartDate = new Date();
				
				HTMLFunctions.strOLDModuleName = strStepCondition;
				objWriterModule.println("<tr align = \"CENTER\">");
				
				HTMLFunctions.strModuleStartDate = objSimpleDtFormat.format(new Date());
				
				HTMLFunctions.intModuleCount = HTMLFunctions.intModuleCount + 1;
				
				objWriterModule.println("<td id=\"ExpandModule" + HTMLFunctions.intModuleCount + "\" bgcolor = \"#A0A0A0\" " +
						"style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:25;COLOR:BLACK;WORD-BREAK:BREAK-ALL\" onClick=\"displayHideElement('ExpandModule" + HTMLFunctions.intModuleCount + "','Module"+ HTMLFunctions.intModuleCount + "')\"> + </td>");
				
				if(strStepCondition.contains("=>"))
				{
					String[] arrModuleNameNDesc = strStepCondition.split("=>");
					
					objWriterModule.println("<td bgcolor = \"Tan\" " +
							"style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;COLOR:BLACK;WORD-BREAK:BREAK-ALL\" >" + 
							"<a href=\"" + HTMLFunctions.strStepResultsFileName + "#Module" + HTMLFunctions.intModuleCount + "\" target=\"_blank\">" + arrModuleNameNDesc[0] + "</a><br>STARTED AT: " + HTMLFunctions.strModuleStartDate + "</td>");	
					
					objWriterModule.println("<td bgcolor = \"PeachPuff\" " +
							"style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;COLOR:BLACK;WORD-BREAK:BREAK-ALL\" >" + 
										arrModuleNameNDesc[1] + "</td>");
					
					objWriter.println("<td bgcolor = \"DARKGREEN\" " +
							"style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;COLOR:WHITE;WORD-BREAK:BREAK-ALL\" >" + 
							"<a id=\"Module" + HTMLFunctions.intModuleCount + "\" >" + arrModuleNameNDesc[0] + "</a><br>STARTED AT: " + HTMLFunctions.strModuleStartDate + "</td>");
				}
				else
				{
					objWriterModule.println("<td bgcolor = \"Tan\" " +
							"style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;COLOR:BLACK;WORD-BREAK:BREAK-ALL\" >" + 
							"<a href=\"" + HTMLFunctions.strStepResultsFileName + "#Module" + HTMLFunctions.intModuleCount + "\" target=\"_blank\">" + strStepCondition + "</a><br>STARTED AT: " + HTMLFunctions.strModuleStartDate + "</td>");	
					
					objWriterModule.println("<td bgcolor = \"PeachPuff\" " +
							"style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;COLOR:BLACK;WORD-BREAK:BREAK-ALL\" > </td>");
					
					objWriter.println("<td bgcolor = \"DARKGREEN\" " +
							"style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;COLOR:WHITE;WORD-BREAK:BREAK-ALL\" >" + 
							"<a id=\"Module" + HTMLFunctions.intModuleCount + "\" >" + strStepCondition + "</a><br>STARTED AT: " + HTMLFunctions.strModuleStartDate + "</td>");
				}
				HTMLFunctions.blnModuleStatus = true;
				System.out.println("*****************************" + strStepCondition + "********************************");
			}
			else
			{
				objWriter.println("<td style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" >,,</td>");
			}
			
			objWriter.println("<td style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" >" + strStepCondition + "</td>");
			objWriter.println("<td style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" >" + strExpectedResult + "</td>");
			objWriter.println("<td style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" >" + strActualResult + "</td>");
			
			if(strExpectedResult.equalsIgnoreCase("PerformanceCapture") && strActualResult.equalsIgnoreCase("PerformanceCapture"))
			{
				String strScreenshot = this.TakeScreenshot();
				if(HTMLFunctions.strModulePageLoadingTimings == "")
				{
					HTMLFunctions.strModulePageLoadingTimings = strStepCondition + "--" + strScreenshot;
				}
				else
				{
					HTMLFunctions.strModulePageLoadingTimings = HTMLFunctions.strModulePageLoadingTimings + "##" + strStepCondition + "--" + strScreenshot;
				}
			}
			
			if(!strExpectedResult.equalsIgnoreCase("ModuleName"))
			{
				//System.out.println(strStepCondition.replaceAll("<[a-zA-Z0-9// '\"@=/[/]()]+>", "") + "\t\"" + strExpectedResult + "\"\t\"" + strActualResult + "\"");
				System.out.println(strStepCondition.replaceAll("(<b>)|(<B>)|(<i>)|(</B>)|(</i>)|(<i title=)", "") + "\t\"" + strExpectedResult + "\"\t\"" + strActualResult + "\"");
			}
			
			if(strActualResult.equalsIgnoreCase(strExpectedResult))
			{
				if(strExpectedResult.equalsIgnoreCase("ModuleName") && strActualResult.equalsIgnoreCase("ModuleName"))
				{
					objWriter.println("<td bgcolor = \"DARKGRAY\" style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" ><b>STARTED</b></td>");
					objWriter.println("<td style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" > </td>");
				}	
				else
				{										//#88D440
					objWriter.println("<td bgcolor = \"DARKGREY\" style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" ><b>PASS</b></td>");
					objWriter.println("<td style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" >" + strTimeDifferenceMsg + "</td>");
				}
				if(HTMLFunctions.blnModuleStatus != false)
						HTMLFunctions.blnModuleStatus = true;
			}
			else
			{
				String strScreenshot = this.TakeScreenshot();
				
				objWriter.println("<td bgcolor = \"RED\" style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" >" + 
						"<b><a target = '_blank' href='" + strScreenshot + "'>FAIL</a></b></td>");
				objWriter.println("<td style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" >" + strTimeDifferenceMsg + "</td>");
				
				HTMLFunctions.blnModuleStatus = false;
				
			}
			objWriter.println("</tr>");
			
		//		objWriter.println("<td style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" >" + strTimeDifferenceMsg + "</td>");
			objWriter.close();
			objWriterModule.close();
			return true;
		}
	}
	public void ModuleStatusUpdate(Boolean blnLastModule) throws Exception
	{
		SimpleDateFormat objSimpleDtFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		Date objDate = new Date();
		Calendar objCal = Calendar.getInstance();
		objCal.setTime(objDate);
		
		Date dtCurrDtTime = new Date();
		String strTimeDifferenceMsg = this.f_HTML_FindTimeDifferences(dtCurrDtTime, HTMLFunctions.dtModuleStartDate);
		//long intTimeDifference = dtCurrDtTime.getTime() - this.dtModuleStartDate.getTime();
		//this.dtPrevStepExecTime = new Date();		
		
		PrintWriter objWriterModule = new PrintWriter(new BufferedWriter(new FileWriter(HTMLFunctions.strModuleResultsFilePath, true)));
				
		if(HTMLFunctions.blnModuleStatus==true)
		{			
			objWriterModule.println("<td bgcolor = \"#88D440\" style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" ><b>PASS</b></td>");
			
		}
		else
		{		
			objWriterModule.println("<td bgcolor = \"RED\" style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" >" + 
					"<b>FAIL</b></td>");
		}
		objWriterModule.println("<td bgcolor = \"SLATEGRAY\" style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" ><b>" + strTimeDifferenceMsg + "</b></td>");
		objWriterModule.println("</tr>");
		
		if(HTMLFunctions.strModulePageLoadingTimings != "")
		{
			objWriterModule.println("<tr><td colspan=5 style=\"padding: 0px;\">");
			String[] arrModulePageLoadingTimings = (this.strModulePageLoadingTimings).split("##");
			objWriterModule.println("<center><table id=\"Module" + HTMLFunctions.intModuleCount + "\" width = \"100%\" cellspacing=\"0\" cellpadding=\"12\" STYLE = \"DISPLAY:NONE;BORDER-COLOR:BLACK\">");
			for(String strCurrPageLoadingTiming: arrModulePageLoadingTimings)
			{
				String[] arrCurrPageLoadingNScreenshot = strCurrPageLoadingTiming.split("--");
				String[] arrCurrPageLoadingTiming = arrCurrPageLoadingNScreenshot[0].split("=>");
				objWriterModule.println("<tr align = \"CENTER\">");
				objWriterModule.println("<td " +
						"style = \"BORDER:NONE;FONT-FAMILY:VERDANA;FONT-SIZE:25;COLOR:BLACK;WORD-BREAK:BREAK-ALL\" width = \"5%\"> </td>");
				objWriterModule.println("<td bgcolor = \"#C0C0C0\" " +
						"style = \"border: 1px solid black;BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;COLOR:BLACK;WORD-BREAK:BREAK-ALL\" width = \"30%\">" + arrCurrPageLoadingTiming[0] + "</td>");	
				
				objWriterModule.println("<td bgcolor = \"#C0C0C0\" style = \"border: 1px solid black;BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" width = \"35%\"><b>" + arrCurrPageLoadingTiming[1] + "</b></td>");				
				
				objWriterModule.println("<td bgcolor = \"#C0C0C0\" style = \"border: 1px solid black;BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;COLOR:BLACK;WORD-BREAK:BREAK-ALL\" width = \"12%\">" + 
						"<b><a target = '_blank' href='" + arrCurrPageLoadingNScreenshot[1] + "'>SCREENSHOT</a></b></td>");
				
				objWriterModule.println("<td " +
						"style = \"BORDER:NONE;FONT-FAMILY:VERDANA;FONT-SIZE:25;COLOR:BLACK;WORD-BREAK:BREAK-ALL\" width = \"18%\"> </td>");
				
				objWriterModule.println("</tr>");
			}
			objWriterModule.println("</table>");
			objWriterModule.println("</td>");
			objWriterModule.println("</tr>");			
		}
		
		//============================OVERALL SUITE TIMELINE==============================
		if(blnLastModule==true)
		{
			String strTotalSuiteTime = "TOTAL SUITE TIME => " + this.f_HTML_FindTimeDifferences(dtCurrDtTime, HTMLFunctions.dtTestSuiteStartTime);
			objWriterModule.println("<tr align = \"CENTER\">");
			objWriterModule.println("<td colspan=5 bgcolor = \"#A0A0A0\" style = \"BORDER-COLOR:BLACK;FONT-FAMILY:VERDANA;FONT-SIZE:12;WORD-BREAK:BREAK-ALL\" ><b>" + strTotalSuiteTime + "</b></td>");
			objWriterModule.println("</tr>");
			objWriterModule.println("</table>");
		}
		
		objWriterModule.close();
	}
	
	public String f_HTML_FindTimeDifferences(Date dtLatestDate, Date dtOldDate) throws Exception
	{
		String strTimeTakenMsg="";
		
		double dblMilliSeconds = (dtLatestDate.getTime() - dtOldDate.getTime());
		double dblSeconds = (dtLatestDate.getTime() - dtOldDate.getTime())/1000;
		int intMinutes=0, intHour=0;
		float intSeconds=0;
		
		if(dblSeconds >= 60)
		{
			intMinutes = (int) (dblSeconds/60);
			intSeconds = (float) (dblMilliSeconds % (60*1000));
			
			if(intMinutes>=60)
			{
				intHour = intMinutes/60;
				intMinutes = intMinutes % 60;
			}
			
		}
		else
		{
			intSeconds = (float) (dblMilliSeconds % (60*1000));
		}
		intSeconds = objCMNFunctions.RoundFloatNumber(intSeconds/1000, 3);
		
		//System.out.println(intSeconds);
		
		if(intHour == 0 && intMinutes == 0)
		{
			strTimeTakenMsg = intSeconds + " seconds";
		}
		else
		{
			if(intHour == 0)
			{
				strTimeTakenMsg = intMinutes + " minutes " + intSeconds + " seconds";
			}
			else
			{
				strTimeTakenMsg = intHour + " hours " + intMinutes + " minutes " + intSeconds + " seconds";
			}
			
		}

		
		return strTimeTakenMsg;
	}

	public static void main(String[] args) throws Exception
	{
//		HTMLReusableFunctions obj = new HTMLReusableFunctions();
//		obj.f_HTML_CreateResultFile("C:/KeywordDrivenFramework/Results");
	}

}
