package com.mplatform.framework.data.manalytics;

import java.util.HashMap;
import com.mplatform.framework.base.CommonFunctions;

public class MA_AnalyzePageData extends CommonFunctions{


    //====================FIELD VARIABLES DECLARATION=============================
    
	//public String strDTURL = "";
    
    public MA_AnalyzePageData(String strParametersNValues) throws Exception
    {

        HashMap<String, String> hmInputDataSet = fetchXLDataNStoreInHashMap(strParametersNValues, "Login", "InputDataRow", "All");

        //strDTURL = hmInputDataSet.get("Domain");
    }

}
