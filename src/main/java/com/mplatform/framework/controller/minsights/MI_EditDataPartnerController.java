package com.mplatform.framework.controller.minsights;

import com.mplatform.framework.base.Helper;
import com.mplatform.framework.base.Utils;
import com.mplatform.framework.data.minsights.MI_DataVO;
import com.mplatform.framework.model.minsights.MI_DataPartnerPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created : VaibhavS
 * Date : 16th Aug 2018
 * Desc : Data partner Controller Edit Screen
 */

public class MI_EditDataPartnerController extends Utils {

    //Load Test Data
    public MI_DataVO testDataVO = null;

    static MI_DataPartnerPage dataPartnerPage = null;

    public MI_EditDataPartnerController(WebDriver driver) throws Exception{
        super(driver);
        dataPartnerPage = PageFactory.initElements(driver,MI_DataPartnerPage.class);
    }

    public String editDataPartner(String strDataPartnerName) throws Exception {
        System.out.println("Edit Data Partner Starting Here");

        //Go to starting point - Datapartner List screen
        mouseOver(dataPartnerPage.Setup_And_Admin_Menu_Element(),"Setup and Admin Menu");
        clickObject(dataPartnerPage.DataPartnerMenu,"Click on Data Partner Menu");
        refreshPage();

        elementExistance(dataPartnerPage.CreateDataPartner, "Create DataPartner Button", 60);
        elementExistance(dataPartnerPage.searchBox_DataPartner, "Search Box", 20);

        //Search datapartner
        clickObject(dataPartnerPage.searchBox_DataPartner,"Search Box`");
        clearNTypeValue(dataPartnerPage.searchBox_DataPartner, "Search Box", strDataPartnerName);

        elementExistance(dataPartnerPage._datapartnerName+"--"+strDataPartnerName,"Wait Data Partner Search Result",45);
        //Click DataPartner from List
        clickObject(dataPartnerPage._datapartnerName+"--"+strDataPartnerName,"Select DataPartner from List screen");

        elementExistance(dataPartnerPage.edit_dataPartner_Link,"Wait till Edit link available on breadcrumb",60);
        clickObject(dataPartnerPage.edit_dataPartner_Link,"Click on the edit link of available breadcrumb");

        //Wait for 'edit Data partner' screen open
        elementExistance(dataPartnerPage.DataPartnerCreate_Edit_Screen,"Data Partner Edit Screen",60);

        //Wait for 'edit' link in 'Edit datapartner name' screen
        elementExistance(dataPartnerPage.edit_datapartnerName_Link,"Data partner name Edit Link",10);
        clickObject(dataPartnerPage.edit_datapartnerName_Link,"Data Partner Edit Name Link");


        //Check OK and Cancel button, displayed in datapartner Name Edit Field
        elementExistance(dataPartnerPage.edit_datapartnerName_Ok_Button,"Data Partner Edit Name Field OK button",2);
        elementExistance(dataPartnerPage.edit_datapartnerName_Cancel_Button,"Data Partner Edit Name Field Cancel button",2);

        System.out.println("Updating "+ strDataPartnerName+" datapartner name as :");

        //Generate new datapartner Name
        strDataPartnerName = "AutoEdit_"+ Helper.generateUniqueValue();

        System.out.println( strDataPartnerName+" Edited data partner name");

        //Clear existing value in AdvertiserName Text Field and enter new value
        clearNTypeValue(dataPartnerPage.dataPartnerName_TextField,"Data Partner Name Text Field",strDataPartnerName);
        clickObject(dataPartnerPage.edit_datapartnerName_Ok_Button,"Click on Ok button of Data Partner Name");

        clearNTypeValue(dataPartnerPage.dataPartnerContact_TextField,"DataPartner ContactName Text Field","Update AutomationDataPartner");
        clickObject(dataPartnerPage.saveButton,"Click on Save Button");

        // Wait for 60 seconds to close the create screen after click on the save button
        elementNonExistance(dataPartnerPage.DataPartnerCreate_Edit_Screen,"DataPartner Edit Screen Closed",10);


        //Go to starting point - Datapartner List screen
        mouseOver(dataPartnerPage.Setup_And_Admin_Menu_Element(),"Setup and Admin Menu");
        clickObject(dataPartnerPage.DataPartnerMenu,"Click on Data Partner Menu");
        refreshPage();

        elementExistance(dataPartnerPage.CreateDataPartner, "Create DataPartner Button", 60);
        elementExistance(dataPartnerPage.searchBox_DataPartner, "Search Box", 20);


        clickObject(dataPartnerPage.searchBox_DataPartner,"Search Box");
        clearNTypeValue(dataPartnerPage.searchBox_DataPartner, "Search Box", strDataPartnerName);

        clickObject(dataPartnerPage._datapartnerName+"--"+strDataPartnerName,"Select DataPartner from List screen");

        System.out.println("Edit Data Partner Ends Here");

        return strDataPartnerName;
    }

}
