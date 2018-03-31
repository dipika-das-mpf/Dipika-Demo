package com.mplatform.framework.controller.minsights;

import com.mplatform.framework.base.Helper;
import com.mplatform.framework.base.Utils;
import com.mplatform.framework.data.minsights.MI_DataVO;
import com.mplatform.framework.model.minsights.MI_LookLikeModelPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.testng.annotations.Test;



public class MI_CreateLookLikeModelController extends Utils {

        @Test
        public void TestVerifyDB(){

            try {

                // This will load the driver
                Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
                System.out.println("Driver Loaded");



                // This will connect with Database , getConnection taking three argument
                //  first argument Test_Oracle is the dsn which you can change as per your system,
                // second argument is username and third argument is password

                Connection con=DriverManager.getConnection("jdbc:odbc:Test_Oracle",               "system","selenium");

                System.out.println("Connection created");

                // This will create statement
                Statement smt=con.createStatement();

                System.out.println("Statement created");

                // Now execute the query, here facebook is the table which I have created in DB

                ResultSet rs=  smt.executeQuery("Select * from Facebook");

                System.out.println("Query Executed");

                // Iterate the resultset now

                while(rs.next()){

                    String uname=    rs.getString("username");
                    String pass=    rs.getString("password");
                    String email=    rs.getString("email");

                    System.out.println("Uname is "+uname+" password is "+pass+" email id is "+email);

                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }


        }







    //Load Test Data
    public MI_DataVO testDataVO = null;

    static MI_LookLikeModelPage LookLikeModelPage = null;

    public MI_CreateLookLikeModelController(WebDriver driver) throws Exception {
        super(driver);

        LookLikeModelPage = PageFactory.initElements(driver, MI_LookLikeModelPage.class);
    }


/**
 * Created : Dipika
 * Date : 05 Jan 2018
 * Desc : Looklike Controller
 */


    /**
     * Create Data Partner All Advertiser scope
     * @return strDataPartnerName
     * @throws Exception
     */
    public String createLooklike() throws Exception {
        //Select Account
        clickObject(LookLikeModelPage.AccountSelect_Icon,"Account List Drop Down Icon");
        //typeValue(advertiserPage.AccountField,"Enter Default Account",testDataVO.getTestData("DefaultAccount"));
        clickObject(LookLikeModelPage._AccountList+"--"+testDataVO.getTestData("DefaultAccount"),
                "Select Account");

        refreshPage();

        System.out.println("Create Looklike Starting here");
        mouseOver(LookLikeModelPage.Audience_Menu_Element(),"LookLike Menu");
        clickObject(LookLikeModelPage.LookLikeModelMenu,"LookLike Menu");


        elementExistance(LookLikeModelPage.CreateLookLikeModel, "Create LookLike Model Button", 60);
        clickObject(LookLikeModelPage.CreateLookLikeModel,"Create LookLike Model Button");

        elementExistance(LookLikeModelPage.lookLikeModelName_TextField, "LookLike Text Filed to load",
                30);


        String strLookLikeModelName = testDataVO.getTestData("LookLikeName")+"_"+ Helper.generateUniqueValue();
        typeValue(LookLikeModelPage.lookLikeModelName_TextField, "LookLikeModel Name Text Field",strLookLikeModelName);

        elementExistance(LookLikeModelPage.selectLookLikeAdvertiserScope_DropDownIcon, "Advertiser Select Scope icon",
                60);

        clickObject(LookLikeModelPage.selectLookLikeAdvertiserScope_DropDownIcon,"Click On the Advertiser Select Scope");

        elementExistance(LookLikeModelPage.looklikeAdvertiserScope, "Advertiser Select Scope icon",
                60);


        clickObject(LookLikeModelPage.looklikeAdvertiserScope,"Select Different Advertiser Scope option");

        clickEnter();





        clickObject(LookLikeModelPage.selectLookLikeAttributeScope_DropDownIcon,"Click On the Attribute Scope");

        clickObject(LookLikeModelPage.attributecondition, "Select Attribute in condition set of LookLike model");


        clickObject(LookLikeModelPage.selectLookLikeActivityAttributeScope_DropDownIcon,"Click On the Activity of " +
                "Advertiser for that Attribute in Condition Set Scope");

        elementExistance(LookLikeModelPage.attributeactivitycondition, "Wait for Attribute in condition set of LookLike " +
                        "model load",
                30);

        clickObject(LookLikeModelPage.attributeactivitycondition, "Select Activity for Attribute in condition set of" +
                " LookLike model");

        elementExistance(LookLikeModelPage.selectLookLikeActivityDurationAttributeScope_DropDownIcon, "Wait for Attribute Activity in condition set" +
                        " of LookLike model load",
                30);

        clickObject(LookLikeModelPage.selectLookLikeActivityDurationAttributeScope_DropDownIcon,"Click On the " +
                "Activity Duration of Advertiser for that Attribute in Condition Set Scope");

       // elementExistance(LookLikeModelPage.selectLookLikeActivityDurationAttributeScope_DropDownIcon, "Wait for " +
        //                "Attribute in condition set of LookLike " +
         //               "model load",mn
         //       10);


        clickObject(LookLikeModelPage.attributeactivitydurationcondition, "Select Activity Duration Value for " +
                "Attribute in condition set of" +
                " LookLike model");


        elementExistance(LookLikeModelPage.conditionsetlink, "Wait for insert condition set open",
                30);


        clickObject(LookLikeModelPage.conditionsetlink,"Click On the insert condition set link");

        clickObject(LookLikeModelPage.selectLookLikeCustomVariableAttributeScope_DropDownIcon,"Click On the Custom " +
                "Variable" +
                " of " +
                "Advertiser for that Attribute in Condition Set Scope");

        clickObject(LookLikeModelPage.attributecustomvariablecondition, "Select Custom Variable for Attribute in " +
                "condition " +
                "set of" +
                " LookLike model");

        elementExistance(LookLikeModelPage.selectLookLikeCustomVariableConditionSet_DropDownIcon, "Wait for Attribute Activity in condition set" +
                        " of LookLike model load",
                30);

        clickObject(LookLikeModelPage.selectLookLikeCustomVariableConditionSet_DropDownIcon,"Click On the " +
                "Custom Variable option in Attribute dropdown in Condition Set");
        clickObject(LookLikeModelPage.attributecustomvariableValuecondition,"Click On the custom variable value" +
                " in attribute dropdown in condition set");


        elementExistance(LookLikeModelPage.selectLookLikeCustomVariabledurationConditionSet_DropDownIcon, "Wait for " +
                        "Custom Variable Option to load in condition set",
                30);


        clickObject(LookLikeModelPage.selectLookLikeCustomVariabledurationConditionSet_DropDownIcon,"Click On the " +
                "Custom Variable option in Attribute dropdown in Condition Set");
        elementExistance(LookLikeModelPage.attributecustomvariableValuedurationcondition, "Wait for " +
                        "Custom Variable Value to load in condition set",
                30);



        clickObject(LookLikeModelPage.attributecustomvariableValuedurationcondition,"Click On the custom variable " +
                "value" +
                " in attribute dropdown in condition set");

        elementExistance(LookLikeModelPage.CustomVariableConditionMoreValue, "Wait for " +
                        "Custom Variable's More Value to load in condition set",
                30);

        typeValue(LookLikeModelPage.CustomVariableConditionMoreValue, "Custom Variable More Option data", testDataVO
                .getTestData
                ("CustomMoreData"));



/*

        //Exclude Condition Sets

        clickObject(LookLikeModelPage.selectLookLikeCustomVariableAttributeScope_DropDownIcon_Exclude,"Click On the " +
                "Custom " +
                "Variable" +
                " of " +
                "Advertiser for that Attribute in Condition Set Scope");

        clickObject(LookLikeModelPage.attributecustomvariablecondition_Exclude, "Select Custom Variable for " +
                "Attribute in " +
                "condition " +
                "set of" +
                " LookLike model");

        elementExistance(LookLikeModelPage.selectLookLikeCustomVariableConditionSet_DropDownIcon_Exclude, "Wait for " +
                        "Attribute Activity in condition set" +
                        " of LookLike model load",
                30);

        clickObject(LookLikeModelPage.selectLookLikeCustomVariableConditionSet_DropDownIcon_Exclude,"Click On the " +
                "Custom Variable option in Attribute dropdown in Condition Set");
        clickObject(LookLikeModelPage.attributecustomvariableValuecondition_Exclude,"Click On the custom variable value" +
                " in attribute dropdown in condition set");


        elementExistance(LookLikeModelPage.selectLookLikeCustomVariabledurationConditionSet_DropDownIcon_Exclude,
                "Wait for " +
                        "Custom Variable Option to load in condition set",
                30);


        clickObject(LookLikeModelPage.selectLookLikeCustomVariabledurationConditionSet_DropDownIcon_Exclude,"Click " +
                "On the " +
                "Custom Variable option in Attribute dropdown in Condition Set");
        elementExistance(LookLikeModelPage.attributecustomvariableValuedurationcondition_Exclude, "Wait for " +
                        "Custom Variable Value to load in condition set",
                30);



        clickObject(LookLikeModelPage.attributecustomvariableValuedurationcondition_Exclude,"Click On the custom " +
                "variable " +
                "value" +
                " in attribute dropdown in condition set");

        elementExistance(LookLikeModelPage.CustomVariableConditionMoreValue_Exclude, "Wait for " +
                        "Custom Variable's More Value to load in condition set",
                40);

        clickObject(LookLikeModelPage.CustomVariableConditionMoreValue_Exclude,"Click On the custom " +
                "variable " +
                "value" +
                " in attribute dropdown in condition set");

        elementExistance(LookLikeModelPage.CustomVariableConditionMoreValue_Exclude, "Wait for " +
                        "Custom Variable's More Value to load in condition set",
                40);

        typeValue(LookLikeModelPage.CustomVariableConditionMoreValue_Exclude, "Custom Variable More Option data",
                testDataVO
                .getTestData
                        ("CustomMoreDataExclude"));
*/




        elementExistance(LookLikeModelPage.saveLookLikeButton, "Wait for Save button displayed",
                40);

        clickObject(LookLikeModelPage.saveLookLikeButton,"Click on Save Button");

        // Wait for 60 seconds to close the create screen after click on the save button
        elementNonExistance(LookLikeModelPage.LookLike_Create_Edit_Screen,"LookLike Create Screen Closed",10);

        clickObject(LookLikeModelPage.searchBox_DataPartner,"Search Box");
        clearNTypeValue(LookLikeModelPage.searchBox_DataPartner, "Search Box", strLookLikeModelName);
        //elementNonExistance(LookLikeModelPage.LookLike_Create_Edit_Screen,"LookLike Create Screen Closed",30);
        clickObject(LookLikeModelPage._looklikeName+"--"+strLookLikeModelName,"Select LookLike from List " +
                "screen");

        // Wait for 60 seconds to close the create screen after click on the save button


        System.out.println("Create Look Like Model Ends here");

        return strLookLikeModelName;
    }


}




