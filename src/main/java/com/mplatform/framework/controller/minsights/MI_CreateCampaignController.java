package com.mplatform.framework.controller.minsights;

import com.mplatform.framework.base.Helper;
import com.mplatform.framework.base.Utils;
import com.mplatform.framework.data.minsights.MI_DataVO;
import com.mplatform.framework.model.minsights.MI_CampaignPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;



/**
 * Created by dipika.das on 7/27/17.
 */
public class MI_CreateCampaignController extends Utils {

    //Load Test Data
    public MI_DataVO tetDataVO = null;

    static MI_CampaignPage campaignPage = null;

    public MI_CreateCampaignController(WebDriver driver) throws Exception {
        super(driver);

        campaignPage = PageFactory.initElements(driver, MI_CampaignPage.class);

    }


    public String createCampaign(String advertiserName) throws Exception {

        System.out.println("Create Campaign Starting here");

        clickObject(campaignPage.Setup_And_Admin_Menu_Element(), "Setup and Admin Menu");
        mouseOver2(campaignPage.Advertiser, "Advertiser");
        clickObject(campaignPage.Advertiser, "Advertiser");

        clickObject(campaignPage.SearchBox, "Advertiser Search Box");
        clearNTypeValue(campaignPage.SearchBox, "Advertiser Search Box", advertiserName);
        clickObject(campaignPage._advertiserName + "--" + advertiserName, "Select Advertiser from List screen");

        clickObject(campaignPage.Campaign_Tab,"Campaign Tab");
        waitTillElementNotDisplayed(campaignPage.Campaign_CreateCampaign_Button, "Create Campaign Button", 30);

        clickObject(campaignPage.Campaign_CreateCampaign_Button, "Create Campaign Button");


        String strCampaignName =tetDataVO.getTestData("CampaignName")+"_"+ Helper.generateUniqueValue();
        typeValue(campaignPage.CampaignName_TextField, "Campaign Name",strCampaignName);

        clickObject(campaignPage.Campaign_Start_Date,"Campaign start Date");
        //scrollToElementNClick(campaignPage._weekStartingDay+"--","Week Start Date");

        typeValue(campaignPage.Campaign_Start_Date,"Campaign start Date",currentDate());

        clickObject(campaignPage.Campaign_End_Date,"Campaign end Date");
        //scrollToElementNClick(campaignPage._weekEndingDay+"--","Week Start Date");
        typeValue(campaignPage.Campaign_End_Date,"Campaign end Date",futureDate());

        clickObject(campaignPage.External_Platform,"External Platform");
        clickObject(campaignPage._externalplatform+"--"+tetDataVO.getTestData("ExternalPlatform"),"Select External Platform");
        clickObject(campaignPage.Campaign_Parameter,"Campaign Parameter");
        clickObject(campaignPage._campaignparameter+"--"+tetDataVO.getTestData("CampaignParameter"),"Select Campaign Parameter");
        //clickObject(campaignPage.External_Parameter_id,"Click External Parameter id");

        String strExternalParameterID =tetDataVO.getTestData("ExternalParameterId")+"_"+ Helper.generateUniqueValue();

        typeValue(campaignPage.External_Parameter_id,"External Parameter id",strExternalParameterID);

        clickObject(campaignPage.notes_CampaignTextField,"Campaign Notes Field");
        typeValue(campaignPage.notes_CampaignTextField,"Campaign Notes Field", "Automated Testing Campaign");

        clickObject(campaignPage.Campaign_Save_Button,"Save Campaign");

        System.out.println("Create Campaign Ends here");
        return strCampaignName;


    }


    public void editCampaign(String strAdvertiserName,String strCampaignName) throws Exception{

        System.out.println("Edit Campaign Starting here");

        //Campaign List Screen
        clickObject(campaignPage.Campaign_SearchBox,"Search Box");
        clearNTypeValue(campaignPage.Campaign_SearchBox, "Search Box",strCampaignName);

        clickObject(campaignPage._Campaign_List_NameColumn+"--"+strCampaignName,"Campaign Name column");

        //Wait for 'edit Campaign' screen appear after clicking Campaign Name
        elementExistance(campaignPage.EditCampaign_Screen,"Campaign Edit Screen",30);

        clickObject(campaignPage.EditCampaign_EditNameLink,"Campaign Name Edit link");

        //Check OK and Cancel button, displayed in Campaign Name Edit Field
        elementExistance(campaignPage.EditCampaign_EditName_OK_button,"Campaign Edit Name Field OK button",2);
        elementExistance(campaignPage.EditCampaign_EditName_Cancel_button,"Campaign Edit Name Field Cancel button",2);

        //Generate new Campaign Name
        strCampaignName = "AutoEdit_"+ Helper.generateUniqueValue();
        clearNTypeValue(campaignPage.EditCampaign_EditName_Text_Field,"Campaign Name Text Field",strCampaignName);
        clickObject(campaignPage.EditCampaign_EditName_OK_button,"Ok button of Campaign Name");


        clickObject(campaignPage.CampaignID_Mapping_ADD_Link,"Add Link of Campaign ID Mapping");


        clickObject(campaignPage.External_Second_Platform,"External Second Platform");
        clickObject(campaignPage.External_Second_Platform_Value,"External Second Platform Value");
        //clickObject(campaignPage._externalplatform+"--"+tetDataVO.getTestData("ExternalSecondPlatform"),"Select External Second Platform");
        clickObject(campaignPage.Campaign_Second_Parameter,"Campaign Second Parameter");
        clickObject(campaignPage._campaignparameter+"--"+tetDataVO.getTestData("CampaignSecondParameter"),"Select Campaign Second Parameter");
        //clickObject(campaignPage.External_Parameter_id,"Click External Parameter id");

        String strExternalSecondParameterID =tetDataVO.getTestData("ExternalSecondParameterId")+"_"+ Helper.generateUniqueValue();

        typeValue(campaignPage.External_Second_Parameter_id,"External Second Parameter id",strExternalSecondParameterID);

        clickObject(campaignPage.Delete_External_PlatForm,"Delete External Platforn row");

        //Save Activity, note : Create Activity screen and Create Advertiser Screen are same object
        clickObject(campaignPage.Campaign_Save_Button,"Save Campaign");


        //Campaign List Screen
        clickObject(campaignPage.Campaign_SearchBox,"Search Box");
        clearNTypeValue(campaignPage.Campaign_SearchBox, "Search Box",strCampaignName);

        clickObject(campaignPage._Campaign_List_NameColumn+"--"+strCampaignName,"Campaign Name column");

        //Wait for 'edit Campaign' screen appear after clicking Campaign Name
        elementExistance(campaignPage.EditCampaign_Screen,"Campaign Edit Screen",60);


        System.out.println("Edit Campaign Ends here");

    }




}