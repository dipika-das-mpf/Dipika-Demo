package com.mplatform.framework.controller.manalytics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.mplatform.framework.base.Utils;
import com.mplatform.framework.data.manalytics.MA_AnalyzePageData;
import com.mplatform.framework.data.manalytics.MA_MyReportsIndexPageData;
import com.mplatform.framework.model.manalytics.MA_AnalyzePageModel;

public class MA_AnalyzePageController extends Utils{
	
	MA_AnalyzePageModel analyzePage = null;
    public MA_AnalyzePageData analyzeData = null;
    public MA_MyReportsIndexPageData myReportsData = null;
	
	public MA_AnalyzePageController (WebDriver driver) throws Exception{
		super(driver);
        analyzePage = PageFactory.initElements(driver, MA_AnalyzePageModel.class);
	}
	
	public void verifyAllReportsInAnalyzePage() throws Exception{
		String[] reportsTypes = myReportsData.strDTReportsTypes.split("--");	
		for(int i=0; i < analyzePage.allReports.size(); i++){
			verifyEquals("Report "+(i+1), analyzePage.allReports.get(i)
									.getText(), reportsTypes[i]);
		}
		
	}
	
	public void click61ReportSummary() throws Exception{
		clickObject(analyzePage.summaryReport61, "61. Summary Report");
	}
	
	
	
	
	

}
