package com.mplatform.framework.controller.minsights;


import com.mplatform.framework.base.Helper;
import com.mplatform.framework.base.Utils;
import com.mplatform.framework.data.minsights.MI_DataVO;
import com.mplatform.framework.model.minsights.MI_ActivityPage;
import com.mplatform.framework.model.minsights.MI_AdvertiserPage;
//import com.mplatform.framework.model.MI_LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.HashMap;


public class MI_CreateAdvertiserController extends Utils {

    //Load Test Data
    public MI_DataVO testDataVO = null;

    static MI_AdvertiserPage advertiserPage = null;
    static MI_ActivityPage activityPage = null;
    //static MI_LoginPage loginpage = null;

    public MI_CreateAdvertiserController(WebDriver driver) throws Exception {
        super(driver);
        advertiserPage = PageFactory.initElements(driver, MI_AdvertiserPage.class);
        activityPage = PageFactory.initElements(driver, MI_ActivityPage.class);
        //loginpage = PageFactory.initElements(driver, MI_LoginPage.class);
    }

    public String createAdvertiser() throws Exception {
        /*launchURL(testDataVO.getProperty("url_hostname_node1"));
        clickObject(loginpage.userName, "Click on UserName Field");
        clickObject(loginpage.password, "Click on Password Field");
        typeValue(loginpage.userName, "User Name", testDataVO.getUserName("SUPERUSER"));

        clickObject(loginpage.userName, "Click on UserName Field");
        typeValue(loginpage.password, "Password", testDataVO.getUserPassword("SUPERUSER"));
        clickObject(loginpage.userName, "Click on UserName Field");
        clickObject(loginpage.loginButton, "MI_Login Button");
*/
        //Select Account
        clickObject(advertiserPage.AccountSelect_Icon,"Account List Drop Down Icon");
        //typeValue(advertiserPage.AccountField,"Enter Default Account",testDataVO.getTestData("DefaultAccount"));
        clickObject(advertiserPage._AccountList+"--"+testDataVO.getTestData("DefaultAccount"),
                "Select Account");
        refreshPage();
        elementExistance(advertiserPage.Setup_And_Admin_Menu_Element(),"Setup and Admin Menu",10);

        System.out.println("Create Advertiser Starting here");
        mouseOver2(advertiserPage.Setup_And_Admin_Menu_Element(),"Setup and Admin Menu");
        //mouseOver(advertiserPage.Advertiser,"Advertiser");
        clickObject(advertiserPage.Advertiser,"Advertiser");
        //clickObject(advertiserPage.Advertiser,"Advertiser");

        refreshPage();

        elementExistance(advertiserPage.CreateAdvertiser, "Create Advertiser Button", 30);
        clickObject(advertiserPage.CreateAdvertiser,"Create Advertiser Button");

        /*//To avoid UI Slowness
        if(isDiplayed(advertiserPage.CreateAdvertiser)){
            clickObject(advertiserPage.CreateAdvertiser,"Create Advertiser Button");
        }else{
            mouseOver(advertiserPage.Setup_And_Admin_Menu_Element(),"Setup and Admin Menu");
            clickObject(advertiserPage.Advertiser,"Advertiser");

            elementExistance(advertiserPage.CreateAdvertiser, "Create Advertiser Button", 60);
            clickObject(advertiserPage.CreateAdvertiser,"Create Advertiser Button");

        }*/




        String strAdvertiserName = testDataVO.getTestData("AdvertiserName")+"_"+Helper.generateUniqueValue();
        typeValue(advertiserPage.EnterAdvertiserName_TextField, "Advertiser Name Text Field",strAdvertiserName);

        clickObject(advertiserPage.SelectAgency_DropDownIcon,"Click Agency List Dropdown");
        clickObject(advertiserPage._agency+"--"+testDataVO.getTestData("Agency"),"Select Agency");


        clickObject(advertiserPage.notes_TextField,"Notes Field");
        typeValue(advertiserPage.notes_TextField,"Notes Field", "Automated Testing Demo");
        clickObject(advertiserPage.AdvertiserSave_Button,"Save Button");

        elementNonExistance(advertiserPage.AdvertiserCreate_Edit_Screen, "Advertiser Create Screen", 60);


        clickObject(advertiserPage.SearchBox,"Search Box`");

        clearNTypeValue(advertiserPage.SearchBox, "Search Box", strAdvertiserName);
        clickObject(advertiserPage._advertiserName+"--"+strAdvertiserName,"Select Advertiser from List screen");

        System.out.println("Create Advertiser Ends here");

        return strAdvertiserName;

    }

    public HashMap<String,String> createCrossMarketAdvertiser() throws Exception {

        HashMap<String,String> testDataDetails = new HashMap<String,String>();

        System.out.println("Create Cross Market Advertiser Starting here");


        //Select Account
        clickObject(advertiserPage.AccountSelect_Icon,"Account List Drop Down Icon");
       //typeValue(advertiserPage.AccountField,"Enter Default Account",testDataVO.getTestData("DefaultAccount"));
        refreshPage();
        clickObject(advertiserPage._AccountList+"--"+testDataVO.getTestData("DefaultAccount"),
                "Select Account");

        refreshPage();
        elementExistance(advertiserPage.Setup_And_Admin_Menu_Element(),"Setup and Admin Menu",10);

        mouseOver2(advertiserPage.Setup_And_Admin_Menu_Element(),"Setup and Admin Menu");
        //mouseOver(advertiserPage.Advertiser,"Advertiser");
        clickObject(advertiserPage.Advertiser,"Advertiser");
        //clickObject(advertiserPage.Advertiser,"Advertiser");

        refreshPage();

        elementExistance(advertiserPage.CreateAdvertiser, "Create Advertiser Button", 60);
        clickObject(advertiserPage.CreateAdvertiser,"Create Advertiser Button");

        String strAdvertiserName = testDataVO.getTestData("AdvertiserName")+"_"+Helper.generateUniqueValue();
        typeValue(advertiserPage.EnterAdvertiserName_TextField, "Advertiser Name Text Field",strAdvertiserName);

        clickObject(advertiserPage.SelectAgency_DropDownIcon,"Click Agency List Dropdown");
        clickObject(advertiserPage._agency+"--"+testDataVO.getTestData("Agency"),"Select Agency");

        //Select Make This a Cross-Market Advertiser radio button
        javaScriptClickObject(advertiserPage.Advertiser_MakeThisACrossMarketAdvertiser_CheckBox,"Select Make This a Cross-Market Advertiser Radio Button");

        //Check Default Market Selected
        verifyDisplayed(advertiserPage._DefaultAccountSelected+"--"+testDataVO.getTestData("DefaultAccount"), "Default Account Selected", true);

        //Select Additional Markets
        clickObject(advertiserPage.Advertiser_Additional_DropDownIcon,"Click Additional Market DropDown Icon");
        typeValue(advertiserPage.Advertiser_Additional_SearchField, "Additional Market Search Box", testDataVO.getTestData("AdditionalMarket1").trim());
        clickObject(advertiserPage._AdditionalMarketListItem+"--"+testDataVO.getTestData("AdditionalMarket1").trim(),"Select First Additioaal Market");


        clickObject(advertiserPage.notes_TextField,"Notes Field");
        typeValue(advertiserPage.notes_TextField,"Notes Field", "Automated Testing Cross Market");

        clickObject(advertiserPage.AdvertiserSave_Button,"Save Button");

       //Check Warning message
        elementExistance(advertiserPage.CrossMarket_WarningMessage, "Wait for warning message before creating cross advertiser", 10);
        String warningMessage = advertiserPage.CrossMarket_WarningMessage.getText();
        compareStringObject("Warning Message Before Creating Cross Market Advertser",
                new String("You have made this advertiser a cross-market advertiser. Once saved, this cannot be undone and selected accounts cannot be removed. Is this ok?").trim()
                ,
                warningMessage.trim());

        //Click YES SAVE ANYWAY Button to create Cross Market Advertisr
        elementExistance(advertiserPage.CrossMarketAdvertiser_YES_SAVE_ANYWAY_BUTTON, "YES SAVE ANYWAY Button", 15);
        clickObject(advertiserPage.CrossMarketAdvertiser_YES_SAVE_ANYWAY_BUTTON,"YES SAVE ANYWAY Button");
        elementNonExistance(advertiserPage.CrossMarketAdvertiser_YES_SAVE_ANYWAY_BUTTON,"YES SAVE ANYWAY Button",15);

        System.out.println("Submitted Cross Market Advertiser . Now verification of Cross Market Advertiser starts here....");

        //Go to starting point - Advertiser List screen to Verify the Cross Market Advertiser Information
        refreshPage();
        elementExistance(advertiserPage.Setup_And_Admin_Menu_Element(),"Setup and Admin Menu",10);
        mouseOver2(advertiserPage.Setup_And_Admin_Menu_Element(),"Setup and Admin Menu");
        clickObject(advertiserPage.Advertiser,"Advertiser");
        refreshPage();

        elementExistance(advertiserPage.CreateAdvertiser, "Create Advertiser Button", 15);
        elementExistance(advertiserPage.SearchBox, "Search Box", 15);

        //Search Advertiser
        clickObject(advertiserPage.SearchBox,"Search Box`");
        typeValue(advertiserPage.SearchBox, "Search Box", strAdvertiserName);
        elementExistance(advertiserPage._advertiserName+"--"+strAdvertiserName,"Wait Advertiser Search Result",20);

        //Click Advertiser from List
        clickObject(advertiserPage._advertiserName+"--"+strAdvertiserName,"Select Advertiser from List screen");

        //Verify Cross Market Label in BreadCrumb in Activity List Screen
        elementExistance(advertiserPage.CrossMarketAdvertiser_CrossMarket_Label,"Cross Market Label in Activity List Screen",45);

        String crossMarketLabelStr = null;
        try {
            crossMarketLabelStr = advertiserPage.CrossMarketAdvertiser_CrossMarket_Label.getText();

            compareStringObject("Cross Market Label in BreadCrumb", new String("Cross-Market").trim(),
                    crossMarketLabelStr.trim());
        }catch(Exception e){
            logReport("Exception while retrieve Cross Market Label from Activity List Screen",
                    "Cross-Market",e.getMessage());
        }

        //Click on Edit Advertiser Link , after checking element exists
        elementExistance(advertiserPage.Advertiser_EditLink,"Advertiser Edit Link",15);
        clickObject(advertiserPage.Advertiser_EditLink,"Advertiser Edit Link");

        //Verify 'Make This a Cross-Market Advertiser' Displayed
        verifyIsDisplayed(advertiserPage.Advertiser_MakeThisACrossMarketAdvertiser_CheckBox_Checked,
                "Make This a Cross-Market Advertiser");

        //Selected Default Market in Advertiser Edit Screen
        verifyDisplayed(advertiserPage._DefaultAccountSelected+"--"+testDataVO.getTestData("DefaultAccount"),
                "Default Market Selected in Edit Screen", true);

        //Check Additional Markets
        verifyIsDiplayed(advertiserPage._AdditionalMarkets_Selected+"--"+testDataVO.getTestData("AdditionalMarket1"),
                "Additional Market1 information in Edit Screen");
        verifyIsDiplayed(advertiserPage._AdditionalMarkets_Selected+"--"+testDataVO.getTestData("DefaultAccount")+" (default)",
                "Default Market Sinformation in Edit Screen");

        //Verify Cross Market Label in BreadCrumb in Edit Advertiser Screen Screen
        crossMarketLabelStr = advertiserPage.CrossMarketAdvertiser_CrossMarket_Label.getText();
        compareStringObject("Cross Market Label in Advertiser Edit Screen","Cross-Market",
                crossMarketLabelStr.trim());


        /*Tool tips of Cross Market label in Edit Screen*/
        /*mouseOver2(advertiserPage.CrossMarketAdvertiser_CrossMarket_Label,"CrossMarket Label in Edit Screen");
        elementExistance(advertiserPage.CrossMarketAdvertiser_CrossMarket_ToolTip,"Cross market tool tip in edit screen",20);
        String crossMarketTooltip = advertiserPage.CrossMarketAdvertiser_CrossMarket_ToolTip.getText();
        compareStringObject("Cross Market Label in Advertiser Edit Screen",
                "This advertiser is enabled as cross-market. Activities, campaigns and Look-alike models created for this advertiser will be made available for all markets the advertiser is enabled for.",
                crossMarketTooltip);
*/

        clickObject(advertiserPage.CrossIcon_Close_button,"Close Edit Advertiser Screen");

        elementNonExistance(advertiserPage.AdvertiserCreate_Edit_Screen, "Advertiser Create Screen", 20);


        //Store data other test cases
        testDataDetails.put("CrossMarketAdvertiserName",strAdvertiserName);
        testDataDetails.put("DefaultAccount",testDataVO.getTestData("DefaultAccount"));
        testDataDetails.put("AdditionalMarket1",testDataVO.getTestData("AdditionalMarket1").trim());
        testDataDetails.put("Agency",testDataVO.getTestData("Agency"));


        return testDataDetails;

    }

    /*
    * @purpose - Verify Cross Market Advertiser details in Additional Market (i.e. Account)
    * */
    public void verifyCrossMarketAdvertiser(String account,HashMap<String,String> testDataDetails) throws Exception{

        System.out.println("Verify CrossMarket Advertiser Information in the Cross Market Account Starting here");

        //Select Account
        selectAccount (account);

        //Select Advertiser
        searchAdvertiser (testDataDetails.get("CrossMarketAdvertiserName"));


        //Verify 'Cross-Market' Check Displayed in Advertiser List screen
        verifyIsDisplayed(advertiserPage.AdvertiserList_Column_CrossMarket_Checked,
                "Cross Market Checked for Advertiser");

        //Click Advertiser from List
        clickObject(advertiserPage._advertiserName+"--"+testDataDetails.get("CrossMarketAdvertiserName"),
                "Select Advertiser from List screen");

        //Click on Edit Advertiser Link , after checking element exists
        elementExistance(advertiserPage.Advertiser_EditLink,"Advertiser Edit Link",15);
        clickObject(advertiserPage.Advertiser_EditLink,"Advertiser Edit Link");

        //Verify 'Make This a Cross-Market Advertiser' Displayed
        verifyIsDisplayed(advertiserPage.Advertiser_MakeThisACrossMarketAdvertiser_CheckBox_Checked,
                "Make This a Cross-Market Advertiser");

        //Selected Default Market in Advertiser Edit Screen
        verifyDisplayed(advertiserPage._DefaultAccountSelected+"--"+testDataVO.getTestData("DefaultAccount"),
                "Default Market Selected in Edit Screen", true);

        //Check Additional Markets
        verifyIsDiplayed(advertiserPage._AdditionalMarkets_Selected+"--"+testDataVO.getTestData("AdditionalMarket1"),
                "Additional Market1 information in Edit Screen");
        verifyIsDiplayed(advertiserPage._AdditionalMarkets_Selected+"--"+testDataVO.getTestData("DefaultAccount")+" (default)",
                "Default Market Sinformation in Edit Screen");

        //Verify Cross Market Label in BreadCrumb in Edit Advertiser Screen Screen
        String crossMarketLabelStr = advertiserPage.CrossMarketAdvertiser_CrossMarket_Label.getText();
        compareStringObject("Cross Market Label in Advertiser Edit Screen","Cross-Market",
                crossMarketLabelStr.trim());

        //Close the Advertiser Page
        clickObject(advertiserPage.CrossIcon_Close_button,"Close Advertiser Screen");


        searchActivity(testDataDetails.get("crossMarketAdvertiserActivity"));
        /*Verify Activity Tag in List screen*/
        verifyActivityTag();
        selectActivity(testDataDetails.get("crossMarketAdvertiserActivity"));

        //Check Advertiser Name in Edit Screen dispalyed properly
        verifyIsDiplayed(activityPage._EditActivity_NameLabel+"--"+testDataDetails.get("crossMarketAdvertiserActivity"),
                "Activity Name");
        clickObject(activityPage.CrossIcon_Close_button,"Close EditActivity Screen");




    }

    public static void selectAccount (String accountStr)throws Exception{

        //Select Account
        clickObject(advertiserPage.AccountSelect_Icon,"Account List Drop Down Icon");
        //typeValue(advertiserPage.AccountField,"Enter Default Account",testDataVO.getTestData("DefaultAccount"));
        clickObject(advertiserPage._AccountList+"--"+accountStr,
                "Select Account");

        refreshPage();

    }

    public  void searchAdvertiser (String advertiserName)throws Exception{

        elementExistance(advertiserPage.Setup_And_Admin_Menu_Element(),"Setup and Admin Menu",10);

        mouseOver2(advertiserPage.Setup_And_Admin_Menu_Element(),"Setup and Admin Menu");
        //mouseOver(advertiserPage.Advertiser,"Advertiser");
        clickObject(advertiserPage.Advertiser,"Advertiser");
        //clickObject(advertiserPage.Advertiser,"Advertiser");

        refreshPage();

        elementExistance(advertiserPage.CreateAdvertiser, "Create Advertiser Button", 15);
        elementExistance(advertiserPage.SearchBox, "Search Box", 15);

        //Search Advertiser
        clickObject(advertiserPage.SearchBox,"Search Box`");
        typeValue(advertiserPage.SearchBox, "Search Box", advertiserName);
        elementExistance(advertiserPage._advertiserName+"--"+advertiserName,"Wait Advertiser Search Result",20);


    }

    public  void selectAdvertiser (String advertiserName)throws Exception{

        elementExistance(advertiserPage.Setup_And_Admin_Menu_Element(),"Setup and Admin Menu",10);

        mouseOver2(advertiserPage.Setup_And_Admin_Menu_Element(),"Setup and Admin Menu");
        //mouseOver(advertiserPage.Advertiser,"Advertiser");
        clickObject(advertiserPage.Advertiser,"Advertiser");
        //clickObject(advertiserPage.Advertiser,"Advertiser");

        refreshPage();

        elementExistance(advertiserPage.CreateAdvertiser, "Create Advertiser Button", 15);
        elementExistance(advertiserPage.SearchBox, "Search Box", 15);

        //Search Advertiser
        clickObject(advertiserPage.SearchBox,"Search Box`");
        typeValue(advertiserPage.SearchBox, "Search Box", advertiserName);
        elementExistance(advertiserPage._advertiserName+"--"+advertiserName,"Wait Advertiser Search Result",20);

        //Click Advertiser from List
        clickObject(advertiserPage._advertiserName+"--"+advertiserName,"Select Advertiser from List screen");

    }

    public void searchActivity(String activityName)throws Exception{

        waitTillElementNotDisplayed(activityPage.Activity_CreateActivity_Button, "Create Activity Button", 30);

        //Activity List Screen
        clickObject(activityPage.Activity_SearchBox,"Search Box");
        clearNTypeValue(activityPage.Activity_SearchBox, "Search Box",activityName);

    }

    public void selectActivity(String activityName)throws Exception{

        clickObject(activityPage._Activity_List_NameColumn+"--"+activityName,"Activity Name column");

        //Wait for 'edit Activity' screen appear after clicking Activity Name
        elementExistance(activityPage.EditActivity_Screen,"Activity Edit Screen",30);

    }

    public void verifyActivityTag() throws Exception{

        //Store Tagr Tag ID of Activity in tagrTagID variable.
        String tagrTagID = getTextInElement(activityPage.Activity_List_TagIDColumn, "Activity List TAG ID Column") ;

        //Check TagID displayed in Activity List column
        if((isStringEmpty(tagrTagID))){
            logReport("Activity Tag ID generated successfully",
                    "true","true");
        }else{
            logReport("Activity TagID not generated","true","false");
        }
    }




}
