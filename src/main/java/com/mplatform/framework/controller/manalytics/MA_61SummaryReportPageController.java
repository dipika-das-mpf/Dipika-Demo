package com.mplatform.framework.controller.manalytics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.mplatform.framework.base.Utils;
import com.mplatform.framework.data.manalytics.MA_MyReportsIndexPageData;
import com.mplatform.framework.model.manalytics.MA_61SummaryReportPageModel;

public class MA_61SummaryReportPageController extends Utils{
	
	MA_61SummaryReportPageModel summaryReport61Page = null;
	public MA_MyReportsIndexPageData myReportsData = null;
	
	public MA_61SummaryReportPageController (WebDriver driver) throws Exception{
		super(driver);
		summaryReport61Page = PageFactory.initElements(driver, MA_61SummaryReportPageModel.class);
	}
	
	public void verifyNavigationTo61ReportPage() throws Exception{
		verifyTextInElement(summaryReport61Page.createReportPageHeader, "61. Report Summary Header", "Create "+myReportsData.strDTReportName);
	}
	public void create61SummaryReport() throws Exception{
		
		String[] agencies = myReportsData.strDTSelectedAgencies.split("--");
		
		typeValue(summaryReport61Page.reportNameInputBox, "report Name Input Box", myReportsData.strDTReportName + sessionTimeStamp);
		typeValue(summaryReport61Page.outputFileName, "Output file Name", myReportsData.strDTOutputFileName);
		selectRadioOrCheckBox(summaryReport61Page._outputFileType +"--"+ myReportsData.strDTOutputFileType, "Output File Type "+myReportsData.strDTOutputFileType, "ON");
		typeValue(summaryReport61Page.recipientEmail, "E-mail recipients", myReportsData.strDTEmailRecipient);
		clickObject(summaryReport61Page._scheduling+"--"+myReportsData.strDTScheduling, "Scheduling => "+myReportsData.strDTScheduling);
		selectRadioOrCheckBox(summaryReport61Page._runStatus+"--"+ myReportsData.strDTRunStatus, "Run Status => "+myReportsData.strDTRunStatus,"ON");
		clickObject(summaryReport61Page._dataSources+"--"+myReportsData.strDTDataSources, "Data Source => "+myReportsData.strDTDataSources);
		
		clickObject(summaryReport61Page._dateRangeSelection+"--"+myReportsData.strDTDateRangeSelection, "Fact Absolute and Relative Date range selection => "+myReportsData.strDTDateRangeSelection);
		selectRadioOrCheckBox(summaryReport61Page._relativeDateRangeSelection+"--"+myReportsData.strDTRelativeDateRangeSelection, "Relative Date Range Selection =>"+myReportsData.strDTRelativeDateRangeSelection, "ON");
		if(myReportsData.strDTRelativeDateRangeSelection.equalsIgnoreCase("lastday")){
				typeValue(summaryReport61Page.lastDaysInput, "Last Relative Days", myReportsData.strDTLastRelativeDays);
			}	
			else if(myReportsData.strDTRelativeDateRangeSelection.equalsIgnoreCase("lastday")){
				typeValue(summaryReport61Page.lastMonthInput, "Last Relative month", myReportsData.strDTLastRelativeMonth);
			}
			else{
				
		}
		clickObject(summaryReport61Page.timeZoneBox, "Time Zone Dropdown");
		scrollToElementNClick(summaryReport61Page._timeZoneType+"--"+myReportsData.strDTTimeZone, "Time Zone Selected is => "+myReportsData.strDTTimeZone);
		clickObject(summaryReport61Page.weekStartingDayBox, "Week Starting Day Box");
		scrollToElementNClick(summaryReport61Page._weekStartingDay+"--"+myReportsData.strDTWeekStartingDay, "Week Strting Day Selected is => "+myReportsData.strDTWeekStartingDay);
		typeValue(summaryReport61Page.searchAgenciesFilter, "Search Term Agency", myReportsData.strDTSeachAgencies);
		clickObject(summaryReport61Page.selectAllFromLeftagencies, "Select All Searched Agencies");
		clickObject(summaryReport61Page.addAllSelectedFromLeftAgencies, "Add All Selected Agencies");

		for(int i=0; i<summaryReport61Page.listOfSelectedAgencies.size(); i++){
			
			verifyEquals("Selected agency => "+(i+1), getTextFromElement(summaryReport61Page.listOfSelectedAgencies.get(i),""), agencies[i]);
		}
		
		clickObject(summaryReport61Page.selectAllFromLeftAdvertisers, "Select All Advertisers");
		clickObject(summaryReport61Page.addAllSelectedFromLeftAdvertisers, "Add All Selected Advertisers");
		checkObjectExists(summaryReport61Page.listOfSelectedAdvertisers.get(0), "All Selected And Added Advertisers");
		verifyEquals("Number Of Added Advertisers", ""+summaryReport61Page.listOfSelectedAdvertisers.size(), "50");
		clickObject(summaryReport61Page.reportDisplayedOption, "Report Displayed Option Box");
		scrollToElementNClick(summaryReport61Page._reportDisplayOption+"--"+myReportsData.strDTReportDisplayOptions, "Report Display Option => "+myReportsData.strDTReportDisplayOptions);
		clickObject(summaryReport61Page._groupByAttributes+"--"+myReportsData.strDTGoupByAttributes, "Group By Attributes => "+myReportsData.strDTGoupByAttributes);
		clickObject(summaryReport61Page._selectedMetricsForReports+"--"+myReportsData.strDTSelectedMetricsForReport, "Selected Metrics For Reports => "+myReportsData.strDTSelectedMetricsForReport);
		clickObject(summaryReport61Page.saveReport, "Save Report Button");
	}

}
