package com.mplatform.framework.data.minsights;

import com.mplatform.framework.base.CommonFunctions;

import java.util.HashMap;

public class MI_LoginPageData extends CommonFunctions
{	
	//====================FIELD VARIABLES DECLARATION=============================
	public String strDTURL = "";
	public String strDTUserName = "";
	public String strDTPassword = "";
	public String strDTRole = "";
	public String strDTTimeStamp = "";
	
	public MI_LoginPageData(String strParametersNValues) throws Exception
	{
		
		HashMap<String, String> hmInputDataSet = fetchXLDataNStoreInHashMap(strParametersNValues, "MI_Login", "InputDataRow", "All");
		
		strDTRole = hmInputDataSet.get("Domain");
		strDTURL = hmInputDataSet.get("URL");
		strDTUserName = hmInputDataSet.get("UserName");
		strDTPassword = hmInputDataSet.get("Password");
		strDTTimeStamp = hmInputDataSet.get("TimeStamp");
	}

}

