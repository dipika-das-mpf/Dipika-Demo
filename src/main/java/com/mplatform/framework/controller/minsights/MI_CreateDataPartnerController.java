package com.mplatform.framework.controller.minsights;

import com.mplatform.framework.base.Helper;
import com.mplatform.framework.base.Utils;
import com.mplatform.framework.data.minsights.MI_DataVO;
import com.mplatform.framework.model.minsights.MI_DataPartnerPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created : Dipika
 * Date : 25th Oct 2018
 * Desc : Data partner Controller
 */

public class MI_CreateDataPartnerController extends Utils {

    //Load Test Data
    public MI_DataVO testDataVO = null;

    static MI_DataPartnerPage dataPartnerPage = null;

    public MI_CreateDataPartnerController(WebDriver driver) throws Exception {
        super(driver);
        dataPartnerPage = PageFactory.initElements(driver, MI_DataPartnerPage.class);
    }

    /**
     * Create Data Partner All Advertiser scope
     * @return strDataPartnerName
     * @throws Exception
     */
    public String createDataPartner() throws Exception {
        //Select Account
        clickObject(dataPartnerPage.AccountSelect_Icon,"Account List Drop Down Icon");
        //typeValue(advertiserPage.AccountField,"Enter Default Account",testDataVO.getTestData("DefaultAccount"));
        clickObject(dataPartnerPage._AccountList+"--"+testDataVO.getTestData("DefaultAccount"),
                "Select Account");

        System.out.println("Create Data Partner Starting here");
        mouseOver(dataPartnerPage.Setup_And_Admin_Menu_Element(),"Setup and Admin Menu");
        clickObject(dataPartnerPage.DataPartnerMenu,"DataPartner");

        refreshPage();

        elementExistance(dataPartnerPage.CreateDataPartner, "Create DataPartner Button", 60);
        clickObject(dataPartnerPage.CreateDataPartner,"Create DataPartner Button");

        String strDataPartnerName = testDataVO.getTestData("DataPartnerName")+"_"+ Helper.generateUniqueValue();
        typeValue(dataPartnerPage.dataPartnerName_TextField, "DataPartner Name Text Field",strDataPartnerName);
        clickObject(dataPartnerPage.selectdataparnterAdvertiserScope_DropDownIcon,"Click On the Advertiser Scope");

        clickObject(dataPartnerPage.datapartnerAllAdvertiserScope ,"Select All Advertiser scope");

        typeValue(dataPartnerPage.dataPartnerContact_TextField,"DataPartner ContactName Text Field","AutomationDataPartner");
        typeValue(dataPartnerPage.contactEmailId_TextField,"DataPartner EmailId Text Field", testDataVO.getUserName("SUPERUSER"));

        clickObject(dataPartnerPage.batchFileDelivery_Checkbox,"click on the Batch Delivery check box");
        clickObject(dataPartnerPage.saveButton,"Click on Save Button");

        // Wait for FTP Account Created message
        waitTillElementDisplayed(dataPartnerPage.batchdeliveryFTPAccount_Text,"FTP Account created Text Message displayed",60);
        clickObject(dataPartnerPage.saveButton,"Click on Save Button");

        // Wait for 60 seconds to close the create screen after click on the save button
        elementNonExistance(dataPartnerPage.DataPartnerCreate_Edit_Screen,"DataPartner Create Screen Closed",10);

        clickObject(dataPartnerPage.searchBox_DataPartner,"Search Box");
        clearNTypeValue(dataPartnerPage.searchBox_DataPartner, "Search Box", strDataPartnerName);

        clickObject(dataPartnerPage._datapartnerName+"--"+strDataPartnerName,"Select DataPartner from List screen");

        System.out.println("Create Data Partner Ends here");

        return strDataPartnerName;
    }

    /**
     * Create Data Partner These Advertiser scope
     * @return strDataPartnerName
     * @throws Exception
     */
    public String createDataPartnerAdvertiserScope(String strAdvertiserName) throws Exception {
        System.out.println("Create Data Partner Single Advertiser Scope Starting here");
        mouseOver(dataPartnerPage.Setup_And_Admin_Menu_Element(),"Setup and Admin Menu");
        clickObject(dataPartnerPage.DataPartnerMenu,"DataPartner");

        refreshPage();

        elementExistance(dataPartnerPage.CreateDataPartner, "Create DataPartner Button", 60);
        clickObject(dataPartnerPage.CreateDataPartner,"Create DataPartner Button");

        String strDataPartnerName = testDataVO.getTestData("DataPartnerName")+"_"+ Helper.generateUniqueValue();
        typeValue(dataPartnerPage.dataPartnerName_TextField, "DataPartner Name Text Field",strDataPartnerName);


        clickObject(dataPartnerPage.selectdataparnterAdvertiserScope_DropDownIcon,"Click On the Advertiser Scope");
        clickObject(dataPartnerPage._datapartnerAdvertiserScope+"--"+"THESE_ADV","Select These Advertiser scope");

        waitTillElementDisplayed(dataPartnerPage.advertiserSelectOnTheseScope_DropDownIcon,"Advertiser Select drop down",30);
        clickObject(dataPartnerPage.advertiserSelectOnTheseScope_DropDownIcon,"click on the Advertiser select dropdown icon");
        clickObject(dataPartnerPage.advertiserSelectOnTheseScope_Input_TextField,"click on the Advertiser input textfield in the dropdown");
        typeValue(dataPartnerPage.advertiserSelectOnTheseScope_Input_TextField,"type the advertiser name",strAdvertiserName);
        clickEnter();

        typeValue(dataPartnerPage.dataPartnerContact_TextField,"DataPartner ContactName Text Field","AutomationDataPartner");
        typeValue(dataPartnerPage.contactEmailId_TextField,"DataPartner EmailId Text Field", testDataVO.getUserName("SUPERUSER"));

        clickObject(dataPartnerPage.batchFileDelivery_Checkbox,"click on the Batch Delivery check box");
        clickObject(dataPartnerPage.saveButton,"Click on Save Button");

        // Wait for FTP Account Created message
        waitTillElementDisplayed(dataPartnerPage.batchdeliveryFTPAccount_Text,"FTP Account created Text Message displayed",60);
        clickObject(dataPartnerPage.saveButton,"Click on Save Button");

        // Wait for 60 seconds to close the create screen after click on the save button
        elementNonExistance(dataPartnerPage.DataPartnerCreate_Edit_Screen,"DataPartner Create Screen Closed",10);

        clickObject(dataPartnerPage.searchBox_DataPartner,"Search Box");
        clearNTypeValue(dataPartnerPage.searchBox_DataPartner, "Search Box", strDataPartnerName);

        clickObject(dataPartnerPage._datapartnerName+"--"+strDataPartnerName,"Select DataPartner from List screen");

        System.out.println("Create Data Partner Single Advertiser Scope Ends here");

        return strDataPartnerName;
    }

}
