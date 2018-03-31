package com.mplatform.framework.data.manalytics;

import com.mplatform.framework.base.CommonFunctions;

import java.util.HashMap;

public class MA_ManageAgenciesPageData extends CommonFunctions{

    //====================FIELD VARIABLES DECLARATION=============================
    public String strDTCountOfExistingAgencies = "";
    public String strDTAgencyName = "";
    public String strDTPlatformLoginName = "";
    public String strDTPlatformLoginType = "";
    public String strDTAssignPlatformLogin = "";



    public MA_ManageAgenciesPageData(String strParametersNValues) throws Exception
    {

        HashMap<String, String> hmInputDataSet = fetchXLDataNStoreInHashMap(strParametersNValues, "Agencies", "InputDataRow", "All");


        strDTCountOfExistingAgencies = hmInputDataSet.get("CountOfExistingAgencies");
        strDTAgencyName = hmInputDataSet.get("AgencyName");
        strDTPlatformLoginName = hmInputDataSet.get("PlatformLoginName");
        strDTPlatformLoginType = hmInputDataSet.get("PlatformLoginType");
        strDTAssignPlatformLogin = hmInputDataSet.get("AssignPlatformLogin");

    }
}
