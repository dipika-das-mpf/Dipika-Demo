package com.mplatform.test.manalytics;

import org.testng.annotations.Test;
import com.mplatform.framework.base.ScriptBase;

public class CreateReportTest extends ScriptBase{

	@Test(priority = 1)
	public void login() throws Exception{
		reportingSetup("01", "As a user, Navigate to mAnalytics app then login");
		
		strDTParametersNValues = "InputDataRow=>2";
		
		mPlatform().maLogin().loginNdVerifyLandingPage();
	}
	
	@Test(priority = 2)
	public void navigateToAnalyzePage() throws Exception{
		reportingSetup("02", "As a user, Navigate to Analyze Page and verify all reports - MASUI-607");
		
		strDTParametersNValues = "InputDataRow=>2";
		
		mPlatform().maMyReports().navigateToAnalyzePage();
		//mPlatform().maAnalyze().verifyAllReportsInAnalyzePage();
	}
	
	@Test(priority = 3, description= "As a user, Create 61 Report - MASUI-607")
	public void create61SummaryReport() throws Exception{
		reportingSetup("03", "As a user, Create 61 Report - MASUI-607");
		
		strDTParametersNValues = "InputDataRow=>2";
		
		mPlatform().maAnalyze().click61ReportSummary();
		mPlatform().ma61Report().verifyNavigationTo61ReportPage();
		mPlatform().ma61Report().create61SummaryReport();
		
	}
	
	
	
}
