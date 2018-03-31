package com.mplatform.framework.data.manalytics;

import java.util.HashMap;
import com.mplatform.framework.base.CommonFunctions;

public class MA_MyReportsIndexPageData extends CommonFunctions{


    //====================FIELD VARIABLES DECLARATION=============================
    
    public String strDTReportsTypes = "";
    public String strDTReportName = "";
    public String strDTOutputFileName = "";
    public String strDTOutputFileType = "";
    public String strDTEmailRecipient = "";
    public String strDTScheduling = "";
    public String strDTRunStatus = "";
    public String strDTRunOnDate = "";
    public String strDTRunAtTime = "";
    public String strDTDateRangeSelection = "";
    public String strDTDataSources = "";
    public String strDTAbsoluteDateFrom = "";
    public String strDTAbsoluteTimeFrom = "";
    public String strDTAbsoluteDateUntil = "";
    public String strDTAbsoluteTimeUntil = "";
    public String strDTRelativeDateRangeSelection = "";
    public String strDTLastRelativeDays = "";
    public String strDTLastRelativeMonth = "";
    public String strDTTimeZoneType = "";
    public String strDTTimeZone = "";
    public String strDTWeekStartingDay = "";
    public String strDTSeachAgencies = "";
    public String strDTSelectedAgencies = "";
    public String strDTSearchAdverisers = "";
    public String strDTSelectedAdvertisers = "";
    public String strDTReportDisplayOptions = "";
    public String strDTGoupByAttributes = "";
    public String strDTSelectedMetricsForReport = "";

    public MA_MyReportsIndexPageData(String strParametersNValues) throws Exception
    {
        HashMap<String, String> hmInputDataSet = fetchXLDataNStoreInHashMap(strParametersNValues, "Reports", "InputDataRow", "All");

        strDTReportsTypes = hmInputDataSet.get("ReportsTypes");
        strDTReportName = hmInputDataSet.get("ReportName");
        strDTOutputFileName = hmInputDataSet.get("OutputFileName");
        strDTOutputFileType = hmInputDataSet.get("OutputFileType");
        strDTEmailRecipient = hmInputDataSet.get("EmailRecipients");
        strDTScheduling = hmInputDataSet.get("Scheduling");
        strDTRunStatus = hmInputDataSet.get("RunStatus");
        strDTRunOnDate = hmInputDataSet.get("RunOnDate");
        strDTRunAtTime = hmInputDataSet.get("RunAtTime");
        strDTDateRangeSelection = hmInputDataSet.get("DateRangeSelection");
        strDTDataSources = hmInputDataSet.get("DataSources");
        strDTAbsoluteDateFrom = hmInputDataSet.get("AbsoluteDateFrom");
        strDTAbsoluteTimeFrom = hmInputDataSet.get("AbsoluteTimeFrom");
        strDTAbsoluteDateUntil = hmInputDataSet.get("AbsoluteDateUntil");
        strDTAbsoluteTimeUntil = hmInputDataSet.get("AbsoluteTimeUntil");
        strDTRelativeDateRangeSelection = hmInputDataSet.get("RelativeDateRangeSelection");
        strDTLastRelativeDays = hmInputDataSet.get("LastRelativeDays");
        strDTLastRelativeMonth = hmInputDataSet.get("LastRelativeMonth");
        strDTTimeZoneType = hmInputDataSet.get("TimeZoneType");
        strDTTimeZone = hmInputDataSet.get("TimeZone");
        strDTWeekStartingDay = hmInputDataSet.get("WeekStartingDay");
        strDTSeachAgencies = hmInputDataSet.get("SeachAgencies");
        strDTSelectedAgencies = hmInputDataSet.get("SelectedAgencies");
        strDTSearchAdverisers = hmInputDataSet.get("SearchAdverisers");
        strDTSelectedAdvertisers = hmInputDataSet.get("SelectedAdvertisers");
        strDTReportDisplayOptions = hmInputDataSet.get("ReportDisplayOptions");
        strDTGoupByAttributes = hmInputDataSet.get("GoupByAttributes");
        strDTSelectedMetricsForReport = hmInputDataSet.get("SelectedMetricsForReport");
    }

}
