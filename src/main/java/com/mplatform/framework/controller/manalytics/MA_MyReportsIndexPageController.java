package com.mplatform.framework.controller.manalytics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.mplatform.framework.base.Utils;
import com.mplatform.framework.data.manalytics.MA_MyReportsIndexPageData;
import com.mplatform.framework.model.manalytics.MA_MyReportsIndexPageModel;

public class MA_MyReportsIndexPageController extends Utils{

	MA_MyReportsIndexPageModel myReportsModel = null;
    public MA_MyReportsIndexPageData myReportsData = null;

    public MA_MyReportsIndexPageController(WebDriver driver) throws Exception {
        super(driver);
        myReportsModel = PageFactory.initElements(driver, MA_MyReportsIndexPageModel.class);
    }
    
    public void navigateToAnalyzePage() throws Exception{
    	mouseOverNClick(myReportsModel.hamburgerMenu, "Hamburger Menu");
    	clickObject(myReportsModel.analyzeNavMenu, "Analyze Nav Menu");
    }
    

}
