package com.mplatform.framework.controller.minsights;

import com.mplatform.framework.base.Helper;
import com.mplatform.framework.base.Utils;
import com.mplatform.framework.data.minsights.MI_DataVO;
import com.mplatform.framework.model.minsights.MI_AdvertiserPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by mohamed.abdulkadar on 6/30/2017.
 */
public class MI_EditAdvertiserController extends Utils {

    //Load Test Data
    public MI_DataVO testDataVO = null;

    static MI_AdvertiserPage advertiserPage = null;


    public MI_EditAdvertiserController(WebDriver driver) throws Exception {
        super(driver);
        advertiserPage = PageFactory.initElements(driver, MI_AdvertiserPage.class);
    }

    public String editAdvertiser(String strAdvertiserName) throws Exception{

        System.out.println("Edit Advertiser Starting here");

        //Go to starting point - Advertiser List screen
        mouseOver(advertiserPage.Setup_And_Admin_Menu_Element(),"Setup and Admin Menu");
        clickObject(advertiserPage.Advertiser,"Advertiser");
        refreshPage();


        elementExistance(advertiserPage.CreateAdvertiser, "Create Advertiser Button", 60);
        elementExistance(advertiserPage.SearchBox, "Search Box", 20);

        //Search Advertiser
        clickObject(advertiserPage.SearchBox,"Search Box`");
        typeValue(advertiserPage.SearchBox, "Search Box", strAdvertiserName);
        elementExistance(advertiserPage._advertiserName+"--"+strAdvertiserName,"Wait Advertiser Search Result",45);

        //Store Advertiser Key information
        String strAdvertiserKey = getTextInElement(advertiserPage.AdvertiserList_Column_ID, "Advertiser ID Column") ;
        //Store Account Name
        String strAccountName = getTextInElement(advertiserPage.AccountField, "Account Name") ;

        //Click Advertiser from List
        clickObject(advertiserPage._advertiserName+"--"+strAdvertiserName,"Select Advertiser from List screen");

        //Click on Edit Advertiser Link , after checking element exists
        elementExistance(advertiserPage.Advertiser_EditLink,"Advertiser Edit Link",60);
        clickObject(advertiserPage.Advertiser_EditLink,"Advertiser Edit Link");

        //Wait for 'edit Advertiser' screen open
        elementExistance(advertiserPage.EditAdvertiser_Screen,"Advertiser Edit Screen",60);
        //Wait for 'edit' link in 'Edit Advertiser' screen
        elementExistance(advertiserPage.EditAdvertiser_EditNameLink,"Advertiser Edit Name Link",10);
        clickObject(advertiserPage.EditAdvertiser_EditNameLink,"Advertiser Edit Name Link");

        //Check OK and Cancel button, displayed in Advertiser Name Edit Field
        elementExistance(advertiserPage.EditAdvertiser_EditName_OK_button,"Advertiser Edit Name Field OK button",2);
        elementExistance(advertiserPage.EditAdvertiser_EditName_Cancel_button,"Advertiser Edit Name Field Cancel button",2);

        //Generate new Advertiser Name
        strAdvertiserName = "AutoEdit_"+ Helper.generateUniqueValue();
        //Clear existing value in AdvertiserName Text Field and enter new value
        clearNTypeValue(advertiserPage.EnterAdvertiserName_TextField,"Advertiser Name Text Field",strAdvertiserName);
        clickObject(advertiserPage.EditAdvertiser_EditName_OK_button,"Ok button of Advertiser Name");

        //Check Agency Name dispalyed properly
        verifyTextInElement(advertiserPage.EditAdvertiser_SelectedAgency,
                "Agency Name in Edit Advertiser Screen", testDataVO.getTestData("Agency"));

        //Click on Agency List Dropdown icon in Edit Advertiser Screen
        clickObject(advertiserPage.SelectAgency_DropDownIcon,"Click Agency List Dropdown in Edit Advertiser Screen");
        clickObject(advertiserPage._agency+"--"+testDataVO.getTestData("EditAgency"),"Select Agency in Edit Advertiser Screen");

        //Check Notes dispalyed properly
        verifyTextInElement(advertiserPage.notes_TextField,
                "Notes Text in Edit Advertiser Screen", "Automated Testing Demo");

        //Clear existing value in AdvertiserName Text Field and enter new value
        String updateNotes="update Advertisername by editing AdvertisreName "+strAdvertiserName;
        clearNTypeValue(advertiserPage.notes_TextField,"Notes Text in Edit Advertiser Screen",updateNotes);

        clickObject(advertiserPage.AdvertiserSave_Button,"Save Button");

        //Wait for 'edit Advertiser' screen disappear after clicking Save Button
        elementNonExistance(advertiserPage.EditAdvertiser_Screen,"Advertiser Edit Screen",60);

        //Check Advdertiser Name in Advertiser BreadCrumb
        verifyTextInElement(advertiserPage.EditAdvertiser_AdvertiserBreadCrumb,
                "Advertiser Name in Advertiser Breadcrumb", strAdvertiserName+" ("+testDataVO.getTestData("EditAgency")+")");



        /*Verification of Edit Advertiser Information*/

        //Click on Edit Advertiser Link, open the Advertiser in Edit mode
         clickObject(advertiserPage.Advertiser_EditLink,"Advertiser Edit Link");
        elementExistance(advertiserPage.EditAdvertiser_Screen,"Advertiser Edit Screen",30);

        //Verify updated advertiser name
        verifyTextInElement(advertiserPage.EditAdvertiser_AdvertiserNameLabel,
                "Advertiser Name Label in Edit Screen", strAdvertiserName);

        //Verify Account and Advertiser Key Information
        verifyTextInElement(advertiserPage.EditAdvertiser_Account_Advertiserkey_Label,
                "Advertiser Account and Advertiser ID Label", strAccountName+" | ID "+strAdvertiserKey);


        //Check Agency Name dispalyed properly
        verifyTextInElement(advertiserPage.EditAdvertiser_SelectedAgency,
                "Agency Name in Edit Advertiser Screen", testDataVO.getTestData("EditAgency"));

        //Check Notes dispalyed properly
        verifyTextInElement(advertiserPage.notes_TextField,
                "Notes Text in Edit Advertiser Screen", updateNotes);

        clickObject(advertiserPage.AdvertiserScreen_Close_CrossIcon,"AdvertiserScreen_Close_CrossIcon");


        System.out.println("Edit Advertiser Ends here");

        return strAdvertiserName;
    }
}
