package com.mplatform.framework.base;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class CommonFunctions 
{

	//======================================SPLIT THE PARAMETERS INTO KEYS AND VALUES AND STORE THEM IN HASHMAP=============================
	public HashMap<String, String> SplitNStoreParamsInHashMap(String strParameters)
	{
		HashMap<String, String> objHashMap = new HashMap<String, String>();
		if(strParameters != "" && strParameters != null)
		{
			String[] arrKeysNValues = strParameters.split("--");
			
			for(int intArrElt = 0; intArrElt < arrKeysNValues.length; intArrElt++)
			{
				if(arrKeysNValues[intArrElt] != "" && arrKeysNValues[intArrElt] != null)
				{
					String[]  arrCurrKeyNValue = arrKeysNValues[intArrElt].split("=>"); 
					objHashMap.put(arrCurrKeyNValue[0], arrCurrKeyNValue[1]);
				}
			}
		}
		return objHashMap;
	}
	
	//========================================FIND COLUMN NUMBER IN EXCEL SHEET==============================
	public int FindColumnNoInExcel(HSSFSheet objSH, String strColumnName, int intRowNum) throws Exception
	{
		intRowNum = intRowNum - 1;
		HSSFRow objRow = objSH.getRow(intRowNum);
		
		int intLastCellNum;
		String strAvailCellValue;
		
		try
		{
			intLastCellNum = objRow.getLastCellNum();
			for(int intCell = 0; intCell < intLastCellNum; intCell++)
			{
				strAvailCellValue = objSH.getRow(intRowNum).getCell(intCell).getStringCellValue();
				if(strAvailCellValue.equalsIgnoreCase(strColumnName))
				{
					return intCell;
				}
			}
		}
		catch(Exception e)
		{
			return -1;
		}
		return -1;
	}	
	
	//=========================================FINDS IN WHICH ROW THE SEACHED VALUE IS FOUND IN THE COLUMN IN EXCEL===============
	public int FindRowNoForValueInExcel(HSSFSheet objSH, String strColumnName, String strSearchValue) throws Exception
	{
		int intXLColumn, intXLRowCount;
		intXLColumn = this.FindColumnNoInExcel(objSH, strColumnName, 1);
		
		String strAvailCellValue;
		Cell objXLCell;  
		try
		{
			intXLRowCount = objSH.getLastRowNum();
			for(int intRow = 0; intRow < intXLRowCount + 1; intRow++)
			{
				objXLCell = objSH.getRow(intRow).getCell(intXLColumn);
				try
				{
					strAvailCellValue = objXLCell.getStringCellValue();
				}
				catch(NullPointerException e)
				{
					strAvailCellValue = "";
				}

				
				if(strAvailCellValue.equalsIgnoreCase(strSearchValue))
				{
					return intRow;
				}
			}
		}
		catch(Exception e)
		{
			//System.out.println("Exception " + e);
			return -1;
		}
		return -1;
	}
	
	//=========================================FINDS IN WHICH ROW THE SEACHED VALUE IS FOUND IN THE COLUMN IN EXCEL===============
	public int FindRowNoForValueInExcel(String strSheetName, String strColumnName, String strSearchValue) throws Exception
	{
		GlobalPaths objGLPaths = new GlobalPaths();
    	HSSFWorkbook objWB = new HSSFWorkbook(new FileInputStream(GlobalPaths.strXLInputDatasheetPath));
    	HSSFSheet objSH = objWB.getSheet(strSheetName);
    	
		int intXLColumn, intXLRowCount;
		intXLColumn = this.FindColumnNoInExcel(objSH, strColumnName, 1);
		
		String strAvailCellValue;
		Cell objXLCell;  
		try
		{
			intXLRowCount = objSH.getLastRowNum();
			for(int intRow = 0; intRow < intXLRowCount + 1; intRow++)
			{
				objXLCell = objSH.getRow(intRow).getCell(intXLColumn);
				try
				{
					strAvailCellValue = objXLCell.getStringCellValue();
				}
				catch(NullPointerException e)
				{
					strAvailCellValue = "";
				}

				
				if(strAvailCellValue.equalsIgnoreCase(strSearchValue))
				{
					return intRow;
				}
			}
		}
		catch(Exception e)
		{
			//System.out.println("Exception " + e);
			return -1;
		}
		return -1;
	}
	
	
	//=========================================GET VALUE FROM A ROW AND COLUMN FROM EXCEL SHEET===============
	public String GetCellValueForRowNum(HSSFSheet objSH, String strColumnName, int intRowNum) throws Exception
	{
		intRowNum = intRowNum - 1;
		int intXLColumn;			
		intXLColumn = this.FindColumnNoInExcel(objSH, strColumnName, 1);
		
		String strAvailCellValue;
		Cell objXLCell;  
		try
		{
			objXLCell = objSH.getRow(intRowNum).getCell(intXLColumn);
			strAvailCellValue = objXLCell.getStringCellValue();
			return strAvailCellValue.trim();
		}
		catch(Exception e)
		{
			//System.out.println("Exception " + e);
			return "";
		}
	}
	
	//=========================================SET VALUE FROM A ROW AND COLUMN FROM EXCEL SHEET===============
	public Boolean SetCellValueForRowNum(HSSFSheet objSH, String strColumnName, int intRowNum, String strValue) throws Exception
	{
		//intRowNum = intRowNum - 1;
		int intRowCount, intColumnCount, intColumnNum;
		intRowCount = objSH.getLastRowNum();
		
		HSSFRow objRow = null;
		
		intColumnNum = this.FindColumnNoInExcel(objSH, strColumnName, 1);
		
		if(intRowCount >= intRowNum)
		{
			if(objSH.getRow(intRowNum) != null)
			{
				objRow = objSH.getRow(intRowNum);
			}
			else
			{
				objRow = objSH.createRow(intRowNum);
			}
			
			if(objRow.getCell(intColumnNum) != null)
			{
				objRow.getCell(intColumnNum).setCellValue(strValue);
			}
			else
			{
				objRow.createCell(intColumnNum).setCellValue(strValue);
			}
			return true;
		}
		else
		{
			if(objSH.getRow(intRowNum) != null)
			{
				objRow = objSH.getRow(intRowNum);
			}
			else
			{
				objRow = objSH.createRow(intRowNum);
			}			
			objRow.createCell(intColumnNum).setCellValue(strValue);
			return true;
		}
	}
	
	//=================================GETS VALUE FROM A CELL IN EXCEL AND RETURNS IT============================
	public String GetCellValueInExcel(HSSFSheet objSH, int intRow, int intColumn)
	{
		String strCellValue;
		try
		{
			strCellValue = objSH.getRow(intRow).getCell(intColumn).getStringCellValue().trim();
		}
		catch(Exception e)
		{
			strCellValue = "";
		}
		return strCellValue;
	}
	
	//===================================ADDS NEW COLUMN IN AN EXCEL SHEET=====================================
	public int AddColumnInExcel(HSSFSheet objSH, String strColumnName, int intRowNum)
	{
		intRowNum = intRowNum - 1;
		HSSFRow objRow = objSH.getRow(intRowNum);
		
		int intRowCount, intColumnNo, intColumnCount;
		try
		{
			intColumnCount = objRow.getLastCellNum();
			intColumnNo = FindColumnNoInExcel(objSH, strColumnName, intRowNum + 1);
			if(intColumnNo == -1)
			{
				objSH.getRow(intRowNum).createCell(intColumnCount).setCellValue(strColumnName);
				return intColumnCount;
			}
			else
			{
				return intColumnNo;
			}
		}
		catch(Exception e)
		{
			objSH.createRow(0).createCell(0).setCellValue(strColumnName);
			return 0;
		}
		
	}
	
	//==============================SETS A VALUE IN AN EXCEL CELL===============================================
	public boolean SetCellValueInExcel(HSSFSheet objSH, int intRowNum, int intColumnNum, String strValue)
	{
		intRowNum = intRowNum - 1;
		int intRowCount, intColumnCount;
		intRowCount = objSH.getLastRowNum();
		
		if(intRowCount >= intRowNum)
		{
			if(objSH.getRow(intRowNum) != null)
			{
				objSH.getRow(intRowNum).createCell(intColumnNum).setCellValue(strValue);
			}
			else
			{
				objSH.createRow(intRowNum).createCell(intColumnNum).setCellValue(strValue);
			}
			return true;
		}
		else
		{
			objSH.createRow(intRowNum).createCell(intColumnNum).setCellValue(strValue);
			return true;
		}
	}
	
	//========================GET EXCEPTION MESSAGE AND DISPLAY IT===========================================
	public String GetExceptionNDisplay(Exception objException, boolean blnIsDisplay) throws Exception
	{
		String strException = objException.getMessage();
		if(strException != null)
		{
			String[] arrException = strException.split("\n");
			if(blnIsDisplay == true)
			{
				System.out.println("Exception occurred " + arrException[0]);
			}
			return "<font color='blue'>" + arrException[0] + "</font>";
		}
		else
		{
			return "<font color='blue'>No specific error message thrown from driver for the current step. Check error message in previous steps</font>";
		}
	}
	
	//==========================RETURN CURRENT DATE TIME STRING===============================================
	public String GetDateTimeString()
	{
		SimpleDateFormat objSimpleDtFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		Date objDate = new Date();
		Calendar objCal = Calendar.getInstance();
		objCal.setTime(objDate);
		String strAppendDateTime = objSimpleDtFormat.format(new Date());
		strAppendDateTime = (strAppendDateTime.replace("/", "-").replace(":", "-")).trim();	
		return strAppendDateTime;
	}
	
	
	//==========================RETURN CURRENT DATE TIME STRING===============================================
	public String GetDateTimeStringWithSeconds()
	{
		SimpleDateFormat objSimpleDtFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
		Date objDate = new Date();
		Calendar objCal = Calendar.getInstance();
		objCal.setTime(objDate);
		String strAppendDateTime = objSimpleDtFormat.format(new Date());
		strAppendDateTime = (strAppendDateTime.replace("/", "-").replace(":", "-")).trim();	
		return strAppendDateTime;
	}
	
	//==========================ROUND FLOAT NUMBERS TO DESIRED DECIMALS========================================	
    public float RoundFloatNumber(float fltInputNo, int intNoOfDecimals) {
        BigDecimal bd = new BigDecimal(Float.toString(fltInputNo));
        bd = bd.setScale(intNoOfDecimals, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
    
  //==========================ROUND FLOAT NUMBERS TO DESIRED DECIMALS========================================	
    public double RoundDoubleNumber(double fltInputNo, int intNoOfDecimals) {
        BigDecimal bd = new BigDecimal(Double.toString(fltInputNo));
        bd = bd.setScale(intNoOfDecimals, BigDecimal.ROUND_HALF_UP);
        return bd.doubleValue();
    }
    
    //==========================GET MONTH NAME FROM DATE=======================================================
    public String GetMonthName(Date objDate, Boolean blnIsShortName)
    {
    	Calendar objCalendar = Calendar.getInstance();
    	objCalendar.setTime(objDate);
    	
    	int intMonth = objCalendar.get(Calendar.MONTH);
    	String[] arrMonthNames;
    	
    	DateFormatSymbols objDtFormatSymbols = new DateFormatSymbols();
    	
    	if(blnIsShortName == true)
    	{
    		arrMonthNames = objDtFormatSymbols.getShortMonths();
    	}
    	else
    	{
    		arrMonthNames = objDtFormatSymbols.getMonths();
    	}
    	return arrMonthNames[intMonth];
    }
    
    //==========================GET MONTH NAME FROM DATE=======================================================
    public int GetYear(Date objDate)
    {
    	Calendar objCalendar = Calendar.getInstance();
    	objCalendar.setTime(objDate);
    	
    	int intYear = objCalendar.get(Calendar.YEAR);
    	return intYear;
    }
    
    //==========================GET MONTH NAME FROM INT=======================================================
    public String GetMonthName(int intMonthNo, Boolean blnIsShortName)
    {
    	String[] arrMonthNames;
    	
    	DateFormatSymbols objDtFormatSymbols = new DateFormatSymbols();
    	
    	if(blnIsShortName == true)
    	{
    		arrMonthNames = objDtFormatSymbols.getShortMonths();
    	}
    	else
    	{
    		arrMonthNames = objDtFormatSymbols.getMonths();
    	}
    	return arrMonthNames[intMonthNo];
    }  
    
    //==========================GET MONTH NAME FROM INT=======================================================
    public int GetMonthIndexForName(String strMonthName, Boolean blnIsShortName)
    {
    	String[] arrMonthNames;
    	
    	DateFormatSymbols objDtFormatSymbols = new DateFormatSymbols();
    	
    	if(blnIsShortName == true)
    	{
    		arrMonthNames = objDtFormatSymbols.getShortMonths();
    	}
    	else
    	{
    		arrMonthNames = objDtFormatSymbols.getMonths();
    	}
    	
    	for(int intMonth=0;intMonth<arrMonthNames.length;intMonth++)
    	{
    		if(strMonthName.equalsIgnoreCase(arrMonthNames[intMonth]))
    		{
    			return intMonth;
    		}
    	}
    	return -1;
    }    
    
    //==========================GET WEEKDAY NAME FROM DATE=======================================================
    public String GetWeekdayName(Date objDate, Boolean blnIsShortName)
    {
    	Calendar objCalendar = Calendar.getInstance();
    	objCalendar.setTime(objDate);
    	
    	int intWeekday = objCalendar.get(Calendar.DAY_OF_WEEK);
    	String[] arrWeekdayNames;
    	
    	DateFormatSymbols objDtFormatSymbols = new DateFormatSymbols();
    	
    	if(blnIsShortName == true)
    	{
    		arrWeekdayNames = objDtFormatSymbols.getShortWeekdays();
    	}
    	else
    	{
    		arrWeekdayNames = objDtFormatSymbols.getWeekdays();
    	}
    	return arrWeekdayNames[intWeekday];
    }  
    
    //==========================GET WEEKDAY NAME FROM INT=======================================================
    public String GetWeekdayName(int intWeekdatNo, Boolean blnIsShortName)
    {
    	String[] arrWeekdayNames;
    	
    	DateFormatSymbols objDtFormatSymbols = new DateFormatSymbols();
    	
    	if(blnIsShortName == true)
    	{
    		arrWeekdayNames = objDtFormatSymbols.getShortWeekdays();
    	}
    	else
    	{
    		arrWeekdayNames = objDtFormatSymbols.getWeekdays();
    	}
    	return arrWeekdayNames[intWeekdatNo];
    }     
    
    //==========================GET MAXIMUM NO OF DAYS IN A MONTH===============================================
    public int GetMaxNoDaysInAMonth(String strMonthName, int intYear)
    {
    	Calendar objCalendar = new GregorianCalendar(intYear, GetMonthIndexForName(strMonthName, false), 1);
    	return objCalendar.getActualMaximum(Calendar.DAY_OF_MONTH); 
    }
    
    //==========================WRITE EXCEL OUTPUT STREAM=======================================================
    public Boolean WriteXLOutputStream(HSSFWorkbook objWB, String strXLPath) throws Exception
    {
    	try
    	{
    		OutputStream objOutputStream = new FileOutputStream(strXLPath);
    		objWB.write(objOutputStream);
    		objOutputStream.flush();
    		objOutputStream.close();
    		return true;
    	}
    	catch(Exception objException)
    	{
    		System.out.println(GetExceptionNDisplay(objException, true));
    		return false;
    	}
    }
    
    //==========================SORT INTEGER ARRAY=======================================================
    public String[] SortNReturnArray(String[] arrArrayInput, String strSortingOrder)
    {
    	String strTempInput = "";
    	for(int intElement = 0; intElement < arrArrayInput.length; intElement++)
    	{
    		for(int intNextElt = intElement + 1; intNextElt < arrArrayInput.length; intNextElt++)
    		{
    			if(strSortingOrder.equalsIgnoreCase("Ascending"))
    			{
    				if(arrArrayInput[intElement].compareToIgnoreCase(arrArrayInput[intNextElt]) > 0)
    				{
    					strTempInput = arrArrayInput[intElement];
    					arrArrayInput[intElement] = arrArrayInput[intNextElt];
    					arrArrayInput[intNextElt] = strTempInput;
    				}
    			}
    			else if(strSortingOrder.equalsIgnoreCase("Descending"))
    			{
    				if(arrArrayInput[intElement].compareToIgnoreCase(arrArrayInput[intNextElt]) < 0)
    				{
    					strTempInput = arrArrayInput[intElement];
    					arrArrayInput[intElement] = arrArrayInput[intNextElt];
    					arrArrayInput[intNextElt] = strTempInput;
    				}
    			}    			
    		}
    	}
    	return arrArrayInput;
    }   
    
    //==========================SORT INTEGER ARRAY=======================================================
    public int[] SortNReturnArray(int[] arrArrayInput, String strSortingOrder)
    {
    	int strTempInput = 0;
    	for(int intElement = 0; intElement < arrArrayInput.length; intElement++)
    	{
    		for(int intNextElt = intElement + 1; intNextElt < arrArrayInput.length; intNextElt++)
    		{
    			if(strSortingOrder.equalsIgnoreCase("Ascending"))
    			{
    				if(arrArrayInput[intElement] > arrArrayInput[intNextElt])
    				{
    					strTempInput = arrArrayInput[intElement];
    					arrArrayInput[intElement] = arrArrayInput[intNextElt];
    					arrArrayInput[intNextElt] = strTempInput;
    				}
    			}
    			else if(strSortingOrder.equalsIgnoreCase("Descending"))
    			{
    				if(arrArrayInput[intElement] < arrArrayInput[intNextElt])
    				{
    					strTempInput = arrArrayInput[intElement];
    					arrArrayInput[intElement] = arrArrayInput[intNextElt];
    					arrArrayInput[intNextElt] = strTempInput;
    				}
    			}    			
    		}
    	}
    	return arrArrayInput;
    } 
        
    //==========================REMOVE TRAILING ZEROS FROM A FLOAT=======================================================
    public <object> object RemoveTrailingZerosInFloat(float fltInput)
    {
    	String strFloatNumber = Float.toString(fltInput);
    	String arrFloatNumber[] = strFloatNumber.split("\\.");
    	char[] arrDecimals = arrFloatNumber[1].toCharArray();
    	char chrCurrentChar;
    	String strReturnFloat = "";
    	
    	String strAllZeros = StringUtils.repeat("0", arrDecimals.length);
    	
    	if(arrFloatNumber[1].equalsIgnoreCase(strAllZeros))
    	{
    		return (object) new Integer(arrFloatNumber[0]);
    	}
    	else
    	{
    		int intPositionNonZero = -1;
    		for(int intChar = arrDecimals.length - 1; intChar >= 0; intChar--)
    		{
    			chrCurrentChar = arrDecimals[intChar];
    			if(chrCurrentChar != 0)
    			{
    				intPositionNonZero = intChar;
    				break;
    			}
    		}
    		strReturnFloat = arrFloatNumber[0] + ".";
    		
    		for(int intChar = 0; intChar <= intPositionNonZero; intChar++)
    		{
    			strReturnFloat = strReturnFloat + arrDecimals[intChar];
    		}
    		return (object) new Float(strReturnFloat);
    	}
    }
    
    //==========================FETCH EXCEL DATA AND ADD THEM IN HASHMAP================================
    public HashMap<String, String> fetchXLDataNStoreInHashMap(String strParametersNValues, String strSheetName, String strDataRow, String strColumnsCommaSeparated) throws Exception
    {
    	GlobalPaths objGLPaths = new GlobalPaths();
    	HSSFWorkbook objWB = new HSSFWorkbook(new FileInputStream(GlobalPaths.strXLInputDatasheetPath));
    	HSSFSheet objSHInputSheet = objWB.getSheet(strSheetName);
    	
		HashMap<String, String> hmParamsNValues = SplitNStoreParamsInHashMap(strParametersNValues);
		int intParamInputRow = Integer.parseInt(hmParamsNValues.get(strDataRow));
				
		if(strColumnsCommaSeparated.equalsIgnoreCase("All"))
		{
			String strAllColumns = "";
			int intLastCellNum = objSHInputSheet.getRow(0).getLastCellNum();
			for(int intCell = 0; intCell < intLastCellNum; intCell++)
			{
				if(strAllColumns == "")
					strAllColumns = objSHInputSheet.getRow(0).getCell(intCell).getStringCellValue();
				else
					strAllColumns = strAllColumns + "," + objSHInputSheet.getRow(0).getCell(intCell).getStringCellValue();
			}
			strColumnsCommaSeparated = strAllColumns;
		}
		
		String[] arrColumnNames = strColumnsCommaSeparated.split(",");
		HashMap<String, String> hmInputDataSet = new HashMap<String, String>();
		String strCurrCellValue = "";
		for(String strColumnName: arrColumnNames)
		{
			strCurrCellValue = GetCellValueForRowNum(objSHInputSheet, strColumnName, intParamInputRow);
			if(strCurrCellValue.contains("REPDATETIME"))
			{
				strCurrCellValue = strCurrCellValue.replace("REPDATETIME", (GetDateTimeString().replace("-", "")).replace(" ", ""));
			}
			hmInputDataSet.put(strColumnName, strCurrCellValue);
		}
		
		return hmInputDataSet;
    }
    
    //==========================FETCH EXCEL DATA AND ADD THEM IN HASHMAP================================
    public HashMap<String, String> findRowNumNFetchDataForHashMap(String strSheetName, String strSearchColumnName, String strSearchValue, String strColumnsCommaSeparated) throws Exception
    {
    	GlobalPaths objGLPaths = new GlobalPaths();
    	HSSFWorkbook objWB = new HSSFWorkbook(new FileInputStream(GlobalPaths.strXLInputDatasheetPath));
    	HSSFSheet objSHInputSheet = objWB.getSheet(strSheetName);
    	
		int intParamInputRow = FindRowNoForValueInExcel(objSHInputSheet, strSearchColumnName, strSearchValue);
		if(intParamInputRow >= 0)
		{
			intParamInputRow = intParamInputRow + 1;
			if(strColumnsCommaSeparated.equalsIgnoreCase("All"))
			{
				String strAllColumns = "";
				int intLastCellNum = objSHInputSheet.getRow(0).getLastCellNum();
				for(int intCell = 0; intCell < intLastCellNum; intCell++)
				{
					if(strAllColumns == "")
						strAllColumns = objSHInputSheet.getRow(0).getCell(intCell).getStringCellValue();
					else
						strAllColumns = strAllColumns + "," + objSHInputSheet.getRow(0).getCell(intCell).getStringCellValue();
				}
				strColumnsCommaSeparated = strAllColumns;
			}
			
			String[] arrColumnNames = strColumnsCommaSeparated.split(",");
			HashMap<String, String> hmInputDataSet = new HashMap<String, String>();
			String strCurrCellValue = "";
			for(String strColumnName: arrColumnNames)
			{
				strCurrCellValue = GetCellValueForRowNum(objSHInputSheet, strColumnName, intParamInputRow);
				if(strCurrCellValue.contains("REPDATETIME"))
				{
					strCurrCellValue = strCurrCellValue.replace("REPDATETIME", (GetDateTimeString().replace("-", "")).replace(" ", ""));
				}
				hmInputDataSet.put(strColumnName, strCurrCellValue);
			}
			
			return hmInputDataSet;
		}
		else
		{
			return null;
		}
    }

    
    //==========================FETCH EXCEL DATA AND ADD THEM IN HASHMAP================================
    public void storeHashMapDataInExcel(HashMap<String, String> hmOutputDataSet, String strSheetName, boolean blnForceNewLine, int intRowNum) throws Exception
    {
    	GlobalPaths objGLPaths = new GlobalPaths();
    	HSSFWorkbook objWB = new HSSFWorkbook(new FileInputStream(GlobalPaths.strXLInputDatasheetPath));
    	HSSFSheet objSHOutputSheet = objWB.getSheet(strSheetName);
    	
    	String[] arrColumnNames = getHashMapKeysInStringArray(hmOutputDataSet);
    	
    	int intOUTRowNum = 0;
    	if(intRowNum <= 0)
    	{
	    	if(blnForceNewLine == true)
	    	{
	    		intOUTRowNum = objSHOutputSheet.getLastRowNum() + 1;
	    	}
	    	else
	    	{
				intOUTRowNum = FindRowNoForValueInExcel(objSHOutputSheet, arrColumnNames[0], hmOutputDataSet.get(arrColumnNames[0]));
				if(intOUTRowNum < 0)
				{
					intOUTRowNum = objSHOutputSheet.getLastRowNum() + 1;
				}
	    	}
    	}
    	else
    	{
    		intOUTRowNum = intRowNum - 1;
    	}
    	
    	for(String strColumnName: arrColumnNames)
    	{
    		SetCellValueForRowNum(objSHOutputSheet, strColumnName, intOUTRowNum, hmOutputDataSet.get(strColumnName));
    	}
    	WriteXLOutputStream(objWB, GlobalPaths.strXLInputDatasheetPath);

    }
    
    //==========================FETCH EXCEL DATA AND ADD THEM IN HASHMAP================================
    public String[] getHashMapKeysInStringArray(HashMap<String, String> hmOutputDataSet) throws Exception
    {
    	String[] arrStrings = new String[hmOutputDataSet.keySet().size()];
    	
    	Set<String> clxnStrings = hmOutputDataSet.keySet();
    	int intKey = 0;
		for(String strInput: clxnStrings)
    	{
    		arrStrings[intKey] = strInput;
    		intKey = intKey + 1;
    	}
    	
    	return arrStrings;
    }
	
    //==========================CONNECT TO DB=======================================================
    /*
     * @Ahmed, why it is not parametrize ?
     */
    public void ConnectDB() throws Exception
    {
/*    	Properties objProperties = new Properties();
    	objProperties.load(new FileReader(System.getProperty("user.dir") + "/Config/CONFIG.properties"));
	    String url1 =objProperties.getProperty("DBURL");
	    String dbClass = "com.mysql.jdbc.Driver";
	    String username = objProperties.getProperty("Username");
	    String password = objProperties.getProperty("Password");
	    String Query = objProperties.getProperty("Query");
	
	    Class.forName(dbClass).newInstance();
	    Connection con = DriverManager.getConnection(url1,username, password);
	             Statement stmt = (Statement) con.createStatement();
	             ResultSet result = (ResultSet) stmt.executeQuery("query");
	
	             System.out.println(result);*/
    	

	     Properties objProperties = new Properties();
	     objProperties.load(new FileReader(System.getProperty("user.dir") + "/Config/CONFIG.properties"));
         String url1 =objProperties.getProperty("DBURL");
         String dbClass = "oracle.jdbc.driver.OracleDriver";
		 String username = objProperties.getProperty("Username");
		 String password = objProperties.getProperty("Password");
		 String Query = objProperties.getProperty("Query");
		 
		 Class.forName(dbClass).newInstance();
         Connection con = DriverManager.getConnection(url1,username, password);
         Statement stmt = con.createStatement();
         ResultSet raw = stmt.executeQuery("SELECT EVENT_ID from evtmsgjob.e_event e where e.section_id = 515636835");
         String raw1 = raw.toString();
         String [] result=raw1.split("@");
         System.out.println(result[1]);
             
    }
    
    
	public static void main(String[] args) throws Exception 
	{
//		String strVal = "";//"Name=>Poorna";
//		
//		HashMap objHashMap = new CommonReusableFunctions().f_CRF_SplitNStoreParamsInHashMap(strVal);
//		
//		System.out.println(objHashMap.size());
//		CommonFunctions objCMNFuncs = new CommonFunctions();
//		objCMNFuncs.ConnectDB();
		
		/*String s = " (a) 123";
		String[] arr = s.split("\\(a\\)");
		
		System.out.println(arr[1]);*/
		
		String strCurrOptionValue = "a_s";
		
//		strCurrOptionValue = strCurrOptionValue.replaceAll("[.;]$", "");
		
		System.out.println(strCurrOptionValue.split("_").length);
		
		/*String[] arrScores = new String[2];
		arrScores[0] = "100";
		arrScores[1] = "50";
		String[] arrNewScores = objCMNFuncs.SortNReturnArray(arrScores, "Descending");
		
		System.out.println(arrNewScores[0] + "\n" + arrNewScores[1]);*/
//		System.out.println(objCMNFuncs.f_CMNMATH_RemoveTrailingZerosInFloat((float) 45.00001540001));
//		
//		Object obj = objCMNFuncs.f_CMNMATH_RemoveTrailingZerosInFloat((float) 45.00001540001);
//		
//		System.out.println(obj.getClass().toString());
		
//		ArrayList objArrList = new ArrayList();
//		HashMap objHashMap = new HashMap();
//		objHashMap.put("Name", "Poorna");
//		
//		objArrList.add("a");
//		objArrList.add(objHashMap);
//		
//		HashMap objHM = (HashMap) objArrList.get(1);
//		System.out.println(objHM.get("Name"));
		//System.out.println(objCMNFuncs.f_CMNMATH_RoundFloatNumber((float)217/3, 1));
		
//		float fltValue;
//		fltValue = 4/2;
//		System.out.println(fltValue);
		
		//============================================VERIFY SORTING==========================================
//		String[] arrNames = {"Poorna",  "Jawahar", "Prahash"};
//		arrNames= objCMNFuncs.SortNReturnArray(arrNames, "Ascending");
//		for(int intElt = 0; intElt < arrNames.length; intElt++)
//		{
//			System.out.println(arrNames[intElt]);
//		}
//		
//		int[] arrNumbers = {25, 70, 15};
//		arrNumbers= objCMNFuncs.SortNReturnArray(arrNumbers, "Ascending");
//		for(int intElt = 0; intElt < arrNumbers.length; intElt++)
//		{
//			System.out.println(arrNumbers[intElt]);
//		}
//		
//		System.out.println(objCMNFuncs.f_CMNDATE_GetMaxNoDaysInAMonth("September", 2014));
//		System.out.println(objCMNFuncs.f_CMNDATE_GetMonthIndexForName("September", false));
//		System.out.println(objCMNFuncs.f_CMNDATE_GetMonthIndexForName("January", false));
//		
//		System.out.println(objCMNFuncs.f_CMNDATE_GetMonthName(1, false));
//		System.out.println(objCMNFuncs.f_CMNDATE_GetMonthName(new Date(), false));
		
//		System.out.println(new CommonReusableFunctions().f_CMNDATE_GetMonthName(new Date(), true));
//		System.out.println(new CommonReusableFunctions().f_CMNDATE_GetWeekdayName(new Date(), true));
//		
//		System.out.println(new CommonReusableFunctions().f_CMNDATE_GetMonthName(new Date(), false));
//		System.out.println(new CommonReusableFunctions().f_CMNDATE_GetWeekdayName(new Date(), false));

	}

}
