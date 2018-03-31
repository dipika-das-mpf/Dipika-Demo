package com.mplatform.framework.data.manalytics;


import com.mplatform.framework.base.CommonFunctions;

import java.util.HashMap;

public class MA_LoginPageData extends CommonFunctions{


    //====================FIELD VARIABLES DECLARATION=============================
    public String strDTURL = "";
    public String strDTUserName = "";
    public String strDTPassword = "";
    public String strDTDomain = "";
    public String strDTLandingPageName="";
    public String strDTTimeStamp = "";
    public String strDTAccount = "";
    

    public MA_LoginPageData(String strParametersNValues) throws Exception
    {

        HashMap<String, String> hmInputDataSet = fetchXLDataNStoreInHashMap(strParametersNValues, "Login", "InputDataRow", "All");

        strDTDomain = hmInputDataSet.get("Domain");
        strDTURL = hmInputDataSet.get("URL");
        strDTUserName = hmInputDataSet.get("UserName");
        strDTPassword = hmInputDataSet.get("Password");
        strDTLandingPageName= hmInputDataSet.get("LandingPageName");
        strDTTimeStamp = hmInputDataSet.get("TimeStamp");
        strDTAccount = hmInputDataSet.get("Account");
    }

}
