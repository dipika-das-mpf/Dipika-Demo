package com.mplatform.framework.controller.minsights;

import com.mplatform.framework.base.Helper;
import com.mplatform.framework.base.Utils;
import com.mplatform.framework.data.minsights.MI_DataVO;
import com.mplatform.framework.model.minsights.MI_ActivityPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;



public class MI_CreateActivityController extends Utils {

    //Load Test Data
    public MI_DataVO testDataVO = null;

    static MI_ActivityPage activityPage = null;

    public MI_CreateActivityController(WebDriver driver) throws Exception {
        super(driver);

        activityPage = PageFactory.initElements(driver, MI_ActivityPage.class);

    }

    public String createActivity(String advertiserName) throws Exception {

        System.out.println("Create Activity Starting here");

        //Select Account
        clickObject(activityPage.AccountSelect_Icon, "Account List Drop Down Icon");
        //typeValue(advertiserPage.AccountField,"Enter Default Account",testDataVO.getTestData("DefaultAccount"));
        clickObject(activityPage._AccountList + "--" + testDataVO.getTestData("DefaultAccount"),
                "Select Account");

        refreshPage();
        elementExistance(activityPage.Setup_And_Admin_Menu_Element(), "Setup and Admin Menu", 10);

        clickObject(activityPage.Setup_And_Admin_Menu_Element(), "Setup and Admin Menu");
        mouseOver2(activityPage.Advertiser, "Advertiser");
        clickObject(activityPage.Advertiser, "Advertiser");

        clickObject(activityPage.SearchBox, "Advertiser Search Box");
        clearNTypeValue(activityPage.SearchBox, "Advertiser Search Box", advertiserName);
        clickObject(activityPage._advertiserName + "--" + advertiserName, "Select Advertiser from List screen");

        waitTillElementNotDisplayed(activityPage.Activity_CreateActivity_Button, "Create Activity Button", 30);

        clickObject(activityPage.Activity_CreateActivity_Button, "Create Activity Button");

        String strActivityName = testDataVO.getTestData("ActivityName") + "_" + Helper.generateUniqueValue();
        typeValue(activityPage.ActivityName_TextField, "Activity Name", strActivityName);

        //Enter First Custom Variable
        typeValue(activityPage.Activity_CustomVariableName_FirstRow, "First Custom Variable Name",
                testDataVO.getTestData("customvarname1"));
        typeValue(activityPage.Activity_CustomVariableTagParameterName_FirstRow, "First TagParameter Name",
                testDataVO.getTestData("customvarTagname1"));

        //Add Row to add 2nd Custom Variable
        clickObject(activityPage.Activity_Add_CustomVariable_Link, "Add Custom Variable Link");

        //Enter 2nd Custom Variable
        typeValue(activityPage.Activity_CustomVariableName_SecondRow, "Second Custom Variable Name",
                testDataVO.getTestData("customvarname2"));
        typeValue(activityPage.Activity_CustomVariableTagParameterName_SecondRow, "Second TagParameter Name",
                testDataVO.getTestData("customvarTagname2"));

        //Enter 'Send Data to Zeus' Information
        typeValue(activityPage.Activity_ClientID_TextField, "Client ID", "123133");
        typeValue(activityPage.Activity_ActionID_TextField, "Client ID", "123133");

        //Save Activity, note : Create Activity screen and Create Advertiser Screen are same object
        clickObject(activityPage.Activity_Save_Button, "Save Activity");
        //elementNonExistance(activityPage.AdvertiserCreate_Edit_Screen, "Create Activity Screen", 45);


        //Activity List Screen
        clickObject(activityPage.Activity_SearchBox, "Search Box");
        clearNTypeValue(activityPage.Activity_SearchBox, "Search Box", strActivityName);

        refreshPage();
        waitTillElementNotDisplayed(activityPage.Campaign_Tab, "Wait to load Campaign page", 30);
        clickObject(activityPage.Campaign_Tab,"Campaign Tab");
        waitTillElementNotDisplayed(activityPage.Campaign_CreateCampaign_Button, "Create Campaign Button", 30);

        clickObject(activityPage.Campaign_CreateCampaign_Button, "Create Campaign Button");


        String strCampaignName =testDataVO.getTestData("CampaignName")+"_"+ Helper.generateUniqueValue();
        typeValue(activityPage.CampaignName_TextField, "Campaign Name",strCampaignName);

        clickObject(activityPage.Campaign_Start_Date,"Campaign start Date");
        //scrollToElementNClick(campaignPage._weekStartingDay+"--","Week Start Date");

        typeValue(activityPage.Campaign_Start_Date,"Campaign start Date",currentDate());

        clickObject(activityPage.Campaign_End_Date,"Campaign end Date");
        //scrollToElementNClick(campaignPage._weekEndingDay+"--","Week Start Date");
        typeValue(activityPage.Campaign_End_Date,"Campaign end Date",futureDate());

        clickObject(activityPage.External_Platform,"External Platform");
        clickObject(activityPage._externalplatform+"--"+testDataVO.getTestData("ExternalPlatform"),"Select External" +
                " Platform");
        clickObject(activityPage.Campaign_Parameter,"Campaign Parameter");
        clickObject(activityPage._campaignparameter+"--"+testDataVO.getTestData("CampaignParameter"),"Select " +
                "Campaign Parameter");
        //clickObject(campaignPage.External_Parameter_id,"Click External Parameter id");

        String strExternalParameterID =testDataVO.getTestData("ExternalParameterId")+"_"+ Helper.generateUniqueValue();

        typeValue(activityPage.External_Parameter_id,"External Parameter id",strExternalParameterID);

        clickObject(activityPage.notes_CampaignTextField,"Campaign Notes Field");
        typeValue(activityPage.notes_CampaignTextField,"Campaign Notes Field", "Automated Testing Campaign");

        clickObject(activityPage.Campaign_Save_Button,"Save Campaign");

        System.out.println("Create Campaign Ends here");


      /*  //Activity TagID generation
        clickObject(activityPage.Activity_List_TagIcon_Column, "Activity List Tag Icon column");
        elementExistance(activityPage.Activity_Copy_Activity_Tag_Code_Screen, "Copy Activity Tag Code Screen", 45);
        clickObject(activityPage.Activity_CopyTagCode_OK_Button, "Activity Tag Code Window OK button");
        elementNonExistance(activityPage.Activity_Copy_Activity_Tag_Code_Screen, "Copy Activity Tag Code Screen", 15);

        //Store Tagr Tag ID of Activity in tagrTagID variable.
        String tagrTagID = getTextInElement(activityPage.Activity_List_TagIDColumn, "Activity List TAG ID Column");

        //Store Activity Key information
        String strActivityKey = getTextInElement(activityPage.Activity_List_ActivityIDColumn, "Activity ID Column");
*/

        //*********Verification of Activity.<activitykey> document stored in Couchbase Configuration bucket*******//
        //Retrieve Activity Document from Couchbase
      /*  String jsonString = testDataVO.getCouchbaseDocument("Configurations","Activity."+strActivityKey);

        //Parse the jsonString , before that check it is not blank and ERROR
        if(!StringUtils.isNotBlank(jsonString) ||    !StringUtils.contains(jsonString,"ERROR")){
            //Convert String into Object and Store it
            storeJSON(strActivityKey,jsonString,"Acx`tivity JSON from Couchbase");
            storeJSONValue("customvariables",strActivityKey, "customvariables");

            compareJSONObject("Activity couchbase Document",testDataVO.getTestData("activity_cb_value"),
                    ((JSONObject)getJSONValue("customvariables")).toString());

        }else{
            logReport("Activity Documentkey","No Document retrieved","false");
        }*/


        //*****Verification Activity Tag in Tagr DB*********************************************************************************************************************//

        //Check TagID displayed in Activity List column
    /*    if((isStringEmpty(tagrTagID))){
            logReport("Activity TagID generated successfully","true","true");
        }else{
            logReport("Activity TagID not generated successfully","true","false");
        }
*/
        //Get Tag from Tagr API
     /*   setApiUserToken(testDataVO.getApiHost("node1"),testDataVO.getApiUserName(),testDataVO.getApiUserPassword());
        String tagJSONstr = getTagID(tagrTagID);
        storeJSON(tagrTagID,tagJSONstr,"Tagr Tag JSON");
        storeJSONValue("tagr_parameters",tagrTagID, "parameters");
*/

        //Compare Tagr Tag values with expected value
      /*  compareStringObject("Tag ID in Tagr ",tagrTagID,((JSONObject)getJSONValue(tagrTagID)).getString("id"));
        compareStringObject("accountId in Tagr ",testDataVO.getTestData("account_id"),((JSONObject)getJSONValue(tagrTagID)).getString("accountId"));
        compareStringObject("eventType in Tagr ","activity",((JSONObject)getJSONValue(tagrTagID)).getString("eventType"));
        compareStringObject("Activitykey in Tagr ",strActivityKey,((JSONObject)getJSONValue(tagrTagID)).getString("activityId"));
        compareStringObject("mirrorGlobal in Tagr ","true",String.valueOf(((JSONObject)getJSONValue(tagrTagID)).getBoolean("mirrorGlobal")));
        compareStringObject("clientIsAdvertiser in Tagr ","true",String.valueOf(((JSONObject)getJSONValue(tagrTagID)).getBoolean("clientIsAdvertiser")));
*/
/*
        if(((JSONArray)getJSONValue("tagr_parameters")).length() > 0){

            for(int i = 0; i<((JSONArray)getJSONValue("tagr_parameters")).length(); i++){

                JSONObject jsonObj = (JSONObject)((JSONArray)getJSONValue("tagr_parameters")).get(i);

                if(jsonObj.has("name")){

                    if(org.apache.commons.lang3.StringUtils.contains(jsonObj.getString("name"),"zeus.migAction")){
                        compareJSONObject("Activity zeus.migAction",testDataVO.getTestData("tagr_zeus_action_parameter"),
                                jsonObj.toString());
                    }else if(org.apache.commons.lang3.StringUtils.contains(jsonObj.getString("name"),"zeus.migClientId")){
                        compareJSONObject("Activity zeus.migClientId",testDataVO.getTestData("tagr_zeus_client_parameter"),
                                jsonObj.toString());

                    }

                }
            }

        }else{
            logReport("Tagr zeus parameter","Not found","false");
        }

        System.out.println("Create Activity Ends here");

        return strActivityName;

    }*/

  /*  public String createCrossMarketActivity(String account,String advertiserName) throws Exception{

        System.out.println("Create Activity Starting here");

        //Select Account
        clickObject(activityPage.AccountSelect_Icon,"Account List Drop Down Icon");
        //typeValue(advertiserPage.AccountField,"Enter Default Account",testDataVO.getTestData("DefaultAccount"));
        clickObject(activityPage._AccountList+"--"+account,
                "Select Account");

        refreshPage();
        elementExistance(activityPage.Setup_And_Admin_Menu_Element(),"Setup and Admin Menu",10);

        clickObject(activityPage.Setup_And_Admin_Menu_Element(),"Setup and Admin Menu");
        mouseOver2(activityPage.Advertiser,"Advertiser");
        clickObject(activityPage.Advertiser,"Advertiser");

        clickObject(activityPage.SearchBox,"Advertiser Search Box");
        clearNTypeValue(activityPage.SearchBox, "Advertiser Search Box", advertiserName);
        clickObject(activityPage._advertiserName+"--"+advertiserName,"Select Advertiser from List screen");

        waitTillElementNotDisplayed(activityPage.Activity_CreateActivity_Button, "Create Activity Button", 30);

        clickObject(activityPage.Activity_CreateActivity_Button,"Create Activity Button");

        String strActivityName =testDataVO.getTestData("ActivityName")+"_"+Helper.generateUniqueValue();
        typeValue(activityPage.ActivityName_TextField, "Activity Name",strActivityName);

        //Enter First Custom Variable
        typeValue(activityPage.Activity_CustomVariableName_FirstRow, "First Custom Variable Name",
                testDataVO.getTestData("customvarname1"));
        typeValue(activityPage.Activity_CustomVariableTagParameterName_FirstRow, "First TagParameter Name",
                testDataVO.getTestData("customvarTagname1"));

        //Add Row to add 2nd Custom Variable
        clickObject(activityPage.Activity_Add_CustomVariable_Link,"Add Custom Variable Link");

        //Enter 2nd Custom Variable
        typeValue(activityPage.Activity_CustomVariableName_SecondRow, "Second Custom Variable Name",
                testDataVO.getTestData("customvarname2"));
        typeValue(activityPage.Activity_CustomVariableTagParameterName_SecondRow, "Second TagParameter Name",
                testDataVO.getTestData("customvarTagname2"));

        //Enter 'Send Data to Zeus' Information
        typeValue(activityPage.Activity_ClientID_TextField, "Client ID","123133");
        typeValue(activityPage.Activity_ActionID_TextField, "Client ID","123133");

        //Save Activity, note : Create Activity screen and Create Advertiser Screen are same object
        clickObject(activityPage.Activity_Save_Button,"Save Activity");
        elementNonExistance(activityPage.AdvertiserCreate_Edit_Screen, "Create Activity Screen", 45);



        //Activity List Screen
        clickObject(activityPage.Activity_SearchBox,"Search Box");
        clearNTypeValue(activityPage.Activity_SearchBox, "Search Box",strActivityName);

        //Activity TagID generation
        clickObject(activityPage.Activity_List_TagIcon_Column,"Activity List Tag Icon column");
        elementExistance(activityPage.Activity_Copy_Activity_Tag_Code_Screen,"Copy Activity Tag Code Screen",45);
        clickObject(activityPage.Activity_CopyTagCode_OK_Button,"Activity Tag Code Window OK button");
        elementNonExistance(activityPage.Activity_Copy_Activity_Tag_Code_Screen,"Copy Activity Tag Code Screen", 15);

        //Store Tagr Tag ID of Activity in tagrTagID variable.
        String tagrTagID = getTextInElement(activityPage.Activity_List_TagIDColumn, "Activity List TAG ID Column") ;

        //Store Activity Key information
        String strActivityKey = getTextInElement(activityPage.Activity_List_ActivityIDColumn, "Activity ID Column") ;
*/

        //*********Verification of Activity.<activitykey> document stored in Couchbase Configuration bucket*******//
        //Retrieve Activity Document from Couchbase
/*        String jsonString = testDataVO.getCouchbaseDocument("Configurations","Activity."+strActivityKey);*/

        //Parse the jsonString , before that check it is not blank and ERROR
       /* if(!StringUtils.isNotBlank(jsonString) ||    !StringUtils.contains(jsonString,"ERROR")){
            //Convert String into Object and Store it
            storeJSON(strActivityKey,jsonString,"Acx`tivity JSON from Couchbase");
            storeJSONValue("customvariables",strActivityKey, "customvariables");

            compareJSONObject("Activity couchbase Document",testDataVO.getTestData("activity_cb_value"),
                    ((JSONObject)getJSONValue("customvariables")).toString());

        }else{
            logReport("Activity Documentkey","No Document retrieved","false");
        }*/


        //*****Verification Activity Tag in Tagr DB*********************************************************************************************************************//
/*
        //Check TagID displayed in Activity List column
        if((isStringEmpty(tagrTagID))){
            logReport("Activity TagID generated successfully","true","true");
        }else{
            logReport("Activity TagID","true","false");
        }

        //Get Tag from Tagr API
        setApiUserToken(testDataVO.getApiHost("node1"),testDataVO.getApiUserName(),testDataVO.getApiUserPassword());
        String tagJSONstr = getTagID(tagrTagID);
        storeJSON(tagrTagID,tagJSONstr,"Tagr Tag JSON");
        storeJSONValue("tagr_parameters",tagrTagID, "parameters");


        //Compare Tagr Tag values with expected value
        compareStringObject("Tag ID in Tagr ",tagrTagID,((JSONObject)getJSONValue(tagrTagID)).getString("id"));
        compareStringObject("accountId in Tagr ",testDataVO.getTestData("account_id"),((JSONObject)getJSONValue(tagrTagID)).getString("accountId"));
        compareStringObject("eventType in Tagr ","activity",((JSONObject)getJSONValue(tagrTagID)).getString("eventType"));
        compareStringObject("Activitykey in Tagr ",strActivityKey,((JSONObject)getJSONValue(tagrTagID)).getString("activityId"));
        compareStringObject("mirrorGlobal in Tagr ","true",String.valueOf(((JSONObject)getJSONValue(tagrTagID)).getBoolean("mirrorGlobal")));
        compareStringObject("clientIsAdvertiser in Tagr ","true",String.valueOf(((JSONObject)getJSONValue(tagrTagID)).getBoolean("clientIsAdvertiser")));


        if(((JSONArray)getJSONValue("tagr_parameters")).length() > 0){

            for(int i = 0; i<((JSONArray)getJSONValue("tagr_parameters")).length(); i++){

                JSONObject jsonObj = (JSONObject)((JSONArray)getJSONValue("tagr_parameters")).get(i);

                if(jsonObj.has("name")){

                    if(org.apache.commons.lang3.StringUtils.contains(jsonObj.getString("name"),"zeus.migAction")){
                        compareJSONObject("Activity zeus.migAction",testDataVO.getTestData("tagr_zeus_action_parameter"),
                                jsonObj.toString());
                    }else if(org.apache.commons.lang3.StringUtils.contains(jsonObj.getString("name"),"zeus.migClientId")){
                        compareJSONObject("Activity zeus.migClientId",testDataVO.getTestData("tagr_zeus_client_parameter"),
                                jsonObj.toString());

                    }

                }
            }

        }else{
            logReport("Tagr zeus parameter","Not found","false");
        }

        System.out.println("Create Activity Ends here");

        return strActivityName;

    }*/


   /* public void editActivity(String strAdvertiserName,String strActivityName) throws Exception{

        System.out.println("Edit Activity Starting here");
      */  //Go to starting point - Advertiser List screen
        /*mouseOver(activityPage.Setup_And_Admin_Menu_Element(),"Setup and Admin Menu");
        clickObject(activityPage.Advertiser,"Advertiser");
        refreshPage();

        elementExistance(activityPage.CreateAdvertiser, "Create Advertiser Button", 60);

        clickObject(activityPage.SearchBox,"Advertiser Search Box");
        clearNTypeValue(activityPage.SearchBox, "Advertiser Search Box", strAdvertiserName);
        clickObject(activityPage._advertiserName+"--"+strAdvertiserName,"Select Advertiser from List screen");

        //Activity List Screen
        clickObject(activityPage.Activity_SearchBox,"Search Box");
        clearNTypeValue(activityPage.Activity_SearchBox, "Search Box",strActivityName);

        clickObject(activityPage._Activity_List_NameColumn+"--"+strActivityName,"Activity Name column");

        //Wait for 'edit Activity' screen appear after clicking Activity Name
        elementExistance(activityPage.EditActivity_Screen,"Activity Edit Screen",30);

        clickObject(activityPage.EditActivity_EditNameLink,"Activity Name Edit link");

        //Check OK and Cancel button, displayed in Activity Name Edit Field
        elementExistance(activityPage.EditActivity_EditName_OK_button,"Activity Edit Name Field OK button",2);
        elementExistance(activityPage.EditActivity_EditName_Cancel_button,"Activity Edit Name Field Cancel button",2);

        //Generate new Advertiser Name
        strActivityName = "AutoEdit_"+ Helper.generateUniqueValue();
        clearNTypeValue(activityPage.EditActivity_EditName_Text_Field,"Activity Name Text Field",strActivityName);
        clickObject(activityPage.EditActivity_EditName_OK_button,"Ok button of Activity Name");

        //Delete 2nd Custom variable value
        clickObject(activityPage.Activity_Delete_CustomVariable_SecondRow,"Custom Variable 2nd Row");

        //Add Row to add 2nd Custom Variable again
        clickObject(activityPage.Activity_Add_CustomVariable_Link,"Add Custom Variable Link");

        //Enter 2nd Custom Variable
        typeValue(activityPage.Activity_CustomVariableName_SecondRow, "Second Custom Variable Name",
                testDataVO.getTestData("Editcustomvarname2"));
        typeValue(activityPage.Activity_CustomVariableTagParameterName_SecondRow, "Second TagParameter Name",
                testDataVO.getTestData("EditcustomvarTagname2"));

        //Update 'Send Data to Zeus' Information
        clearNTypeValue(activityPage.Activity_ClientID_TextField, "Client ID","133324");
        clearNTypeValue(activityPage.Activity_ActionID_TextField, "Client ID","422222");

        //Save Activity, note : Create Activity screen and Create Advertiser Screen are same object
        clickObject(activityPage.Activity_Save_Button,"Save Activity");
        elementNonExistance(activityPage.AdvertiserCreate_Edit_Screen, "Create Activity Screen", 45);

        //Activity List Screen
        clickObject(activityPage.Activity_SearchBox,"Search Box");
        clearNTypeValue(activityPage.Activity_SearchBox, "Search Box",strActivityName);

        //Store Tagr Tag ID of Activity in tagrTagID variable.
        String tagrTagID = getTextInElement(activityPage.Activity_List_TagIDColumn, "Activity List TAG ID Column") ;

        //Store Activity Key information
        String strActivityKey = getTextInElement(activityPage.Activity_List_ActivityIDColumn, "Activity ID Column") ;


        //*********Verification of Activity.<activitykey> document stored in Couchbase Configuration bucket*******/
        //Retrieve Activity Document from Couchbase
     /*   String jsonString = testDataVO.getCouchbaseDocument("Configurations","Activity."+strActivityKey);

        //Verify Activity Data in Couchbase
        verifyActivityCouchbaseValue(jsonString,strActivityKey,testDataVO.getTestData("Editactivity_cb_value"));

        //Verify TagID information in Tagr
        verifyTagInfo_in_Tagr(tagrTagID,testDataVO.getApiHost("node1"),testDataVO.getApiUserName(),testDataVO.getApiUserPassword(),
                testDataVO.getTestData("account_id"),strActivityKey,"133324","422222");
*/


        //Verification of data Activity Edit Screen
       /* clickObject(activityPage._Activity_List_NameColumn+"--"+strActivityName,"Activity Name column");

        //Wait for 'edit Activity' screen appear after clicking Activity Name
        elementExistance(activityPage.EditActivity_Screen,"Activity Edit Screen",30);

        //Check Advertiser Name in Edit Screen dispalyed properly
        verifyTextInElement(activityPage.EditActivity_NameLabel,
                "Activity NAe", strActivityName);
*/
        /*verifyTextInElement(activityPage.Activity_CustomVariableName_FirstRow,
                "First Row Custom Variable Name", testDataVO.getTestData("Editcustomvarname2"));
        verifyTextInElement(activityPage.Activity_CustomVariableName_FirstRow,
                "First Row Custom Variable Tag Parameter", testDataVO.getTestData("EditcustomvarTagname2"));

        verifyTextInElement(activityPage.Activity_CustomVariableName_SecondRow,
                "Second Row Custom Variable Name", testDataVO.getTestData("customvarname2"));
        verifyTextInElement(activityPage.Activity_CustomVariableTagParameterName_SecondRow,
                "Second Row Custom Variable Tag Parameter", testDataVO.getTestData("customvarTagname2"));

        verifyTextInElement(activityPage.Activity_CustomVariableName_SecondRow,
                "Second Row Custom Variable Name", testDataVO.getTestData("Editcustomvarname2"));
        verifyTextInElement(activityPage.Activity_CustomVariableTagParameterName_SecondRow,
                "Second Row Custom Variable Tag Parameter", testDataVO.getTestData("EditcustomvarTagname2"));
*/

       /* verifyTextInElement(activityPage.Activity_ClientID_TextField,
                "Zeus ClientID", "133324");
        verifyTextInElement(activityPage.Activity_ActionID_TextField,
                "Zeus ActionID", "422222");
*/
       /* System.out.println("Edit Activity Ends here");

    }*/


   /* public static void verifyActivityCouchbaseValue(String jsonString,String strActivityKey,String cbExpectedJson) throws Exception{

        //Parse the jsonString , before that check it is not blank and ERROR
        if(!StringUtils.isNotBlank(jsonString) ||    !StringUtils.contains(jsonString,"ERROR")){
            //Convert String into Object and Store it
            storeJSON(strActivityKey,jsonString,"Activity JSON from Couchbase");
            storeJSONValue("customvariables",strActivityKey, "customvariables");

            compareJSONObject("Activity couchbase Document",cbExpectedJson,
                    ((JSONObject)getJSONValue("customvariables")).toString());

        }else{
            logReport("Activity Documentkey","No Document retrieved","false");
        }



    }*/

  /*  public static void verifyTagInfo_in_Tagr(String tagrTagID,String hostname,String apiUserName,String apiUserPassword,
                                                   String account_id,String strActivityKey,
                                                   String zeusClientID,String zeusActionID) throws Exception {
*/
        //*****Verification Activity Tag in Tagr DB*********************************************************************************************************************//

    /*    //Check TagID displayed in Activity List column
        if((isStringEmpty(tagrTagID))){
            logReport("Activity TagID generated successfully","true ","true");
        }else{
            logReport("Activity TagID not generated successfully","true","false");
        }

        //Get Tag from Tagr API
        setApiUserToken(hostname,apiUserName,apiUserPassword);
        String tagJSONstr = getTagID(tagrTagID);
        storeJSON(tagrTagID,tagJSONstr,"Tagr Tag JSON");
        storeJSONValue("tagr_parameters",tagrTagID, "parameters");


        //Compare Tagr Tag values with expected value
        compareStringObject("Tag ID in Tagr ",tagrTagID,((JSONObject)getJSONValue(tagrTagID)).getString("id"));
        compareStringObject("accountId in Tagr ",account_id,((JSONObject)getJSONValue(tagrTagID)).getString("accountId"));
        compareStringObject("eventType in Tagr ","activity",((JSONObject)getJSONValue(tagrTagID)).getString("eventType"));
        compareStringObject("Activitykey in Tagr ",strActivityKey,((JSONObject)getJSONValue(tagrTagID)).getString("activityId"));
        compareStringObject("mirrorGlobal in Tagr ","true",String.valueOf(((JSONObject)getJSONValue(tagrTagID)).getBoolean("mirrorGlobal")));
        compareStringObject("clientIsAdvertiser in Tagr ","true",String.valueOf(((JSONObject)getJSONValue(tagrTagID)).getBoolean("clientIsAdvertiser")));


        if(((JSONArray)getJSONValue("tagr_parameters")).length() > 0){

            for(int i = 0; i<((JSONArray)getJSONValue("tagr_parameters")).length(); i++){

                JSONObject jsonObj = (JSONObject)((JSONArray)getJSONValue("tagr_parameters")).get(i);

                if(jsonObj.has("name")){

                    if(org.apache.commons.lang3.StringUtils.contains(jsonObj.getString("name"),"zeus.migAction")){
                        compareJSONObject("Activity zeus.migAction",zeusActionID,
                                jsonObj.toString());
                    }else if(org.apache.commons.lang3.StringUtils.contains(jsonObj.getString("name"),"zeus.migClientId")){
                        compareJSONObject("Activity zeus.migClientId",zeusClientID,jsonObj.toString());

                    }

                }
            }

        }else{
            logReport("Tagr zeus parameter","Not found","false");
        }

    }*/


        return strActivityName;
    }

}