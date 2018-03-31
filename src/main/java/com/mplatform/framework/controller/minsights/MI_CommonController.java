package com.mplatform.framework.controller.minsights;

import com.mplatform.framework.base.Utils;
import com.mplatform.framework.model.minsights.MI_ActivityPage;
import com.mplatform.framework.model.minsights.MI_AdvertiserPage;
import com.mplatform.framework.model.minsights.MI_CampaignPage;
import com.mplatform.framework.model.minsights.MI_MainHeader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by mohamed.abdulkadar on 8/15/2017.
 */
public class MI_CommonController extends Utils {

    static  MI_MainHeader mainHeaderPage = null;
    static MI_AdvertiserPage advertiserPagePage = null;
    static MI_CampaignPage campaignPage = null;

    public MI_CommonController(WebDriver driver) throws Exception {
        super(driver);

        mainHeaderPage = PageFactory.initElements(driver, MI_MainHeader.class);
        advertiserPagePage = PageFactory.initElements(driver, MI_AdvertiserPage.class);
        campaignPage = PageFactory.initElements(driver, MI_CampaignPage.class);

    }

    public void selectAccount(String message,String accountName) throws Exception{

        //Select Account
        logReport(message,"true","true");
        clickObject(mainHeaderPage.AccountSelect_Icon,"Account List Drop Down Icon");
        //typeValue(advertiserPage.AccountField,"Enter Default Account",testDataVO.getTestData("DefaultAccount"));
        clickObject(mainHeaderPage._AccountList+"--"+accountName,
                "Select Account");

        refreshPage();


    }

    public void goToAdvertiserListScreen() throws Exception{

        clickObject(advertiserPagePage.Setup_And_Admin_Menu_Element(), "Setup and Admin Menu");
        mouseOver2(advertiserPagePage.Advertiser, "Advertiser");
        clickObject(advertiserPagePage.Advertiser, "Advertiser");
        refreshPage();

        elementExistance(advertiserPagePage.CreateAdvertiser, "Create Advertiser Button", 60);
        //clickObject(advertiserPagePage.CreateAdvertiser,"Create Advertiser Button");


    }

    public void selectAdvertiser(String strAdvertiserName) throws Exception{

        //Search Advertiser
        clickObject(advertiserPagePage.SearchBox,"Search Box`");
        typeValue(advertiserPagePage.SearchBox, "Search Box", strAdvertiserName);
        elementExistance(advertiserPagePage._advertiserName+"--"+strAdvertiserName,"Wait Advertiser Search Result",20);

        //Click Advertiser from List
        clickObject(advertiserPagePage._advertiserName+"--"+strAdvertiserName,"Select Advertiser from List screen");


    }
    public void selectCampaignTab() throws Exception{

        clickObject(campaignPage.Campaign_Tab,"Campaign Tab");
        waitTillElementNotDisplayed(campaignPage.Campaign_CreateCampaign_Button, "Create Campaign Button", 30);


    }

    public void printMessage(String message)throws Exception{
        logReport(message,
                "true","true");
    }

    public void refreshScreen()throws Exception{
        refreshPage();
    }




}
