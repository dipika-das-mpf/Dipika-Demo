package com.mplatform.framework.controller;

import com.mplatform.framework.controller.manalytics.MA_61SummaryReportPageController;
import com.mplatform.framework.controller.manalytics.MA_AnalyzePageController;
import com.mplatform.framework.controller.manalytics.MA_LoginPageController;
import com.mplatform.framework.controller.manalytics.MA_MyReportsIndexPageController;
import com.mplatform.framework.controller.minsights.MI_LoginPageController;
import com.mplatform.framework.data.manalytics.MA_AnalyzePageData;
import com.mplatform.framework.data.manalytics.MA_LoginPageData;
import com.mplatform.framework.data.manalytics.MA_MyReportsIndexPageData;
import com.mplatform.framework.data.minsights.MI_LoginPageData;
import com.mplatform.framework.controller.manalytics.MA_ManageAgenciesPageController;
import com.mplatform.framework.data.manalytics.MA_ManageAgenciesPageData;
import org.openqa.selenium.WebDriver;

public class ApplicationController {
	public String strParametersNValues = "";
	WebDriver driver;

	//>>------------------------------mInsights Controllers 01-----------------------------<<
	public MI_LoginPageController milogin = null;


	//>>------------------------------mAnalytics Controllers 01----------------------------<<
	public MA_LoginPageController malogin = null;
	public MA_MyReportsIndexPageController maMyReports = null;
	public MA_AnalyzePageController maAnalyze = null;
	public MA_61SummaryReportPageController ma61Report = null;
	public MA_ManageAgenciesPageController maManageAgencies = null;


	public ApplicationController(WebDriver driver) {
		this.driver = driver;
	}

	/*public LoginController login = null;
	public LoginController MI_Login() throws Exception {
		if (login == null) {
			login = new LoginController(driver);
		}
		login.loginData = new LoginData(this.strParametersNValues);
		return login;
	}*/

	//>>------------------------------mInsights Controllers 02-----------------------------<<
	public MI_LoginPageController miLogin() throws Exception {
		if (milogin == null) {
			milogin = new MI_LoginPageController(driver);
		}
		milogin.loginData = new MI_LoginPageData(this.strParametersNValues);
		return milogin;
	}

	//>>------------------------------mAnalytics Controllers 02----------------------------<<
	public MA_LoginPageController maLogin() throws Exception {
		if (malogin == null) {
			malogin = new MA_LoginPageController(driver);
		}
		malogin.maLoginData = new MA_LoginPageData(this.strParametersNValues);
		return malogin;
	}

	public MA_MyReportsIndexPageController maMyReports() throws Exception {
		if (maMyReports == null) {
			maMyReports = new MA_MyReportsIndexPageController(driver);
		}
		maMyReports.myReportsData = new MA_MyReportsIndexPageData(this.strParametersNValues);
		return maMyReports;
	}

	public MA_AnalyzePageController maAnalyze() throws Exception {
		if (maAnalyze == null) {
			maAnalyze = new MA_AnalyzePageController(driver);
		}
		maAnalyze.analyzeData = new MA_AnalyzePageData(this.strParametersNValues);
		maAnalyze.myReportsData = new MA_MyReportsIndexPageData(this.strParametersNValues);
		return maAnalyze;
	}

	public MA_61SummaryReportPageController ma61Report() throws Exception {
		if (ma61Report == null) {
			ma61Report = new MA_61SummaryReportPageController(driver);
		}
		ma61Report.myReportsData = new MA_MyReportsIndexPageData(this.strParametersNValues);
		return ma61Report;
	}

	public MA_ManageAgenciesPageController maManageAgencies() throws Exception {
		if (maManageAgencies == null) {
			maManageAgencies = new MA_ManageAgenciesPageController(driver);
		}
		maManageAgencies.manageAgenciesData = new MA_ManageAgenciesPageData(this.strParametersNValues);
		maManageAgencies.maLoginData = new MA_LoginPageData(this.strParametersNValues);
		return maManageAgencies;
	}


}
