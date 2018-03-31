package com.mplatform.framework.controller.minsights;

import com.mplatform.framework.base.Helper;
import com.mplatform.framework.base.Utils;
import com.mplatform.framework.data.minsights.MI_DataVO;
import com.mplatform.framework.model.minsights.MI_DataPartnerModelPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class MI_CreateDataPartnerModelController extends Utils{




    //Load Test Data
    public MI_DataVO testDataVO = null;

    static MI_DataPartnerModelPage DataPartnerModelPage = null;

    public MI_CreateDataPartnerModelController(WebDriver driver) throws Exception {
        super(driver);

        DataPartnerModelPage = PageFactory.initElements(driver, MI_DataPartnerModelPage.class);
    }


/**
 * Created : Dipika
 * Date : 05 Jan 2018
 * Desc : DataPartner
 */
    public String createDataPartnerModel() throws Exception {
        //Select Account
        clickObject(DataPartnerModelPage.AccountSelect_Icon,"Account List Drop Down Icon");
        //typeValue(advertiserPage.AccountField,"Enter Default Account",testDataVO.getTestData("DefaultAccount"));
        clickObject(DataPartnerModelPage._AccountList+"--"+testDataVO.getTestData("DefaultAccount"),
                "Select Account");

        refreshPage();

        System.out.println("Create DataPartner Starting here");
        mouseOver(DataPartnerModelPage.Audience_Menu_Element(),"DataPartner Menu");
        clickObject(DataPartnerModelPage.DataPartnerModelMenu,"DataPartner Menu");


        elementExistance(DataPartnerModelPage.CreateDataPartnerModel, "Create DataPartner Model Button", 60);
        clickObject(DataPartnerModelPage.CreateDataPartnerModel,"Create DataPartner Model Button");

        elementExistance(DataPartnerModelPage.datapartnereModelName_TextField, "DataPartner Text Filed to load",
                60);


        String strDataPartnerModelName = testDataVO.getTestData("DataPartnerName")+"_"+ Helper.generateUniqueValue();
        typeValue(DataPartnerModelPage.datapartnereModelName_TextField, "DataPartnerModel Name Text Field",
                strDataPartnerModelName);

        elementExistance(DataPartnerModelPage.datapartnerAdvertiserScope, "Advertiser Select Scope icon",
                30);


        clickObject(DataPartnerModelPage.datapartnerAdvertiserScope,"Select Different Advertiser Scope option");

        elementExistance(DataPartnerModelPage.selectdatapartnerAdvertiser, "Advertiser Select Scope",
                60);


        clickObject(DataPartnerModelPage.selectdatapartnerAdvertiser,"Select Specific Advertiser");


        clickObject(DataPartnerModelPage.selectDataPartneCondition_DropDownIcon,"Click On the Data Partner icon " +
                "in condition Set");

        clickObject(DataPartnerModelPage.selectDataPartneCondition_DropDown, "Click on the Data Partner in condition " +
                "Set");



        elementExistance(DataPartnerModelPage.selectDataSourceCondition_DropDownIcon, "Wait for Data source dropdown display " +
                        "in Condition Set",
                30);
        clickObject(DataPartnerModelPage.selectDataSourceCondition_DropDownIcon,"Click On the Data Source icon " +
                "in condition Set");

        clickObject(DataPartnerModelPage.selectDataSourceCondition_DropDown, "Click on the Data Source Dropdown in " +
                "condition Set");



        elementExistance(DataPartnerModelPage.Attributeselecttype_icon, "Wait to load Attribute Type dropdown in Condition Set",
                30);
        clickObject(DataPartnerModelPage.Attributeselecttype_icon,"Click on type of Attribute icon " +
                "in condition Set");

        clickObject(DataPartnerModelPage.Attributeselecttype_dropdown, "Click on Attribute from Dropdown in " +
                "condition Set");





        elementExistance(DataPartnerModelPage.Selectattribute_icon, "Wait to load Attributes dropdown in " +
                        "Condition Set",
                30);
        clickObject(DataPartnerModelPage.Selectattribute_icon,"Click on Attribute icon " +
                "in condition Set");

        clickObject(DataPartnerModelPage.Selectattribute_dropdown, "Click on Attribute from Dropdown in " +
                "condition Set");



        elementExistance(DataPartnerModelPage.Attributesinternaltype_icon, "Wait to load Internal Attributes type " +
                        "dropdown in Condition Set",
                30);
        clickObject(DataPartnerModelPage.Attributesinternaltype_icon,"Click on Internal Attribute type icon " +
                "in condition Set");

        clickObject(DataPartnerModelPage.Attributesinternaltype_dropdown, "Click on Internal Attribute from Dropdown" +
                " in " +
                "condition Set");


        elementExistance(DataPartnerModelPage.Attributesinternalvalue_icon, "Wait to load Internal Attributes Value " +
                        "dropdown in Condition Set",
                30);
        clickObject(DataPartnerModelPage.Attributesinternalvalue_icon,"Click on Internal Attribute type icon " +
                "in condition Set");

        clickObject(DataPartnerModelPage.Attributesinternalvalue_dropdown, "Click on Internal Attribute from Dropdown" +
                " in " +
                "condition Set");



     //   clickObject(DataPartnerModelPage.Inserconditionset_link, "Click on inersert condition Set link");


//Add multiple Condition Set
//        clickObject(DataPartnerModelPage.selectDataPartneCondition_DropDownIcon,"Click On the Data Partner icon " +
//                "in condition Set");
//
//        clickObject(DataPartnerModelPage.selectDataPartneCondition_DropDown, "Click on the Data Partner in condition " +
//                "Set");
//
//
//
//        elementExistance(DataPartnerModelPage.selectDataSourceCondition_DropDownIcon, "Wait for Data source dropdown display " +
//                        "in Condition Set",
//                30);
//        clickObject(DataPartnerModelPage.selectDataSourceCondition_DropDownIcon,"Click On the Data Source icon " +
//                "in condition Set");
//
//        clickObject(DataPartnerModelPage.selectDataSourceCondition_DropDown, "Click on the Data Source Dropdown in " +
//                "condition Set");
//
//
//
//        elementExistance(DataPartnerModelPage.Attributeselecttype_icon, "Wait to load Attribute Type dropdown in Condition Set",
//                30);
//        clickObject(DataPartnerModelPage.Attributeselecttype_icon,"Click on type of Attribute icon " +
//                "in condition Set");
//
//        clickObject(DataPartnerModelPage.Attributeselecttype_dropdown, "Click on Attribute from Dropdown in " +
//                "condition Set");
//
//
//
//
//
//        elementExistance(DataPartnerModelPage.Selectattribute_icon, "Wait to load Attributes dropdown in " +
//                        "Condition Set",
//                30);
//        clickObject(DataPartnerModelPage.Selectattribute_icon,"Click on Attribute icon " +
//                "in condition Set");
//
//        clickObject(DataPartnerModelPage.Selectattribute_dropdown, "Click on Attribute from Dropdown in " +
//                "condition Set");
//
//
//
//        elementExistance(DataPartnerModelPage.Attributesinternaltype_icon, "Wait to load Internal Attributes type " +
//                        "dropdown in Condition Set",
//                30);
//        clickObject(DataPartnerModelPage.Attributesinternaltype_icon,"Click on Internal Attribute type icon " +
//                "in condition Set");
//
//
//
//        elementExistance(DataPartnerModelPage.Attributesinternalvalue_icon, "Wait to load Internal Attributes Value " +
//                        "dropdown in Condition Set",
//                30);
//        clickObject(DataPartnerModelPage.Attributesinternalvalue_icon,"Click on Internal Attribute type icon " +
//                "in condition Set");
//
//
//
//        typeValue(DataPartnerModelPage.Attributesinternalvalue_icon,"External Value", "test123");



       // clickObject(DataPartnerModelPage.Attributesinternalvalue_dropdown, "Click on Internal Attribute from " +);








        elementExistance(DataPartnerModelPage.datapartnersave_button, "Wait to Scroll Save button",
                30);


        clickObject(DataPartnerModelPage.datapartnersave_button,"Click on Data Partner Save button" +
               " set");



        // Wait for 60 seconds to close the create screen after click on the save button
        elementNonExistance(DataPartnerModelPage.DataPartner_Create_Edit_Screen,"DataPartner Create Screen Closed",10);

        clickObject(DataPartnerModelPage.searchBox_DataPartner,"Search Box");
        clearNTypeValue(DataPartnerModelPage.searchBox_DataPartner, "Search Box", strDataPartnerModelName);







        elementExistance(DataPartnerModelPage.searchBox_DataPartner, "Create DataPartner Model Button", 60);
        clickObject(DataPartnerModelPage.CreateDataPartnerModel,"Create DataPartner Model Button");

        elementExistance(DataPartnerModelPage.datapartnereModelName_TextField, "DataPartner Text Filed to load",
                60);


        String strDataPartnerModelName2 = testDataVO.getTestData("DataPartnerName")+"_"+ Helper.generateUniqueValue();
        typeValue(DataPartnerModelPage.datapartnereModelName_TextField, "DataPartnerModel Name Text Field",
                strDataPartnerModelName2);



        elementExistance(DataPartnerModelPage.datapartnerAdvertiserTypeScope, "Advertisertype  Select Scope Type icon",
                30);


        clickObject(DataPartnerModelPage.datapartnerAdvertiserTypeScope,"Select Different Advertiser Scope option");

        elementExistance(DataPartnerModelPage.selectdatapartnerAlldvertiser, "Advertiser Select Scope type",
                60);


        clickObject(DataPartnerModelPage.selectdatapartnerAlldvertiser,"Select All Advertiser from dropdown");


        clickObject(DataPartnerModelPage.selectDataPartneCondition_DropDownIcon,"Click On the Data Partner icon " +
                "in condition Set");

        clickObject(DataPartnerModelPage.selectDataPartneCondition2_DropDown, "Click on the Data Partner in " +
                "condition " +
                "Set");



        elementExistance(DataPartnerModelPage.selectDataSourceCondition_DropDownIcon, "Wait for Data source dropdown display " +
                        "in Condition Set",
                30);
        clickObject(DataPartnerModelPage.selectDataSourceCondition_DropDownIcon,"Click On the Data Source icon " +
                "in condition Set");

        clickObject(DataPartnerModelPage.selectDataSourceCondition2_DropDown, "Click on the Data Source Dropdown in " +
                "condition Set");



        elementExistance(DataPartnerModelPage.Attributeselecttype_icon, "Wait to load Attribute Type dropdown in Condition Set",
                30);
        clickObject(DataPartnerModelPage.Attributeselecttype_icon,"Click on type of Attribute icon " +
                "in condition Set");

        clickObject(DataPartnerModelPage.Attributeselecttype_dropdown, "Click on Attribute from Dropdown in " +
                "condition Set");





        elementExistance(DataPartnerModelPage.Selectattribute_icon, "Wait to load Attributes dropdown in " +
                        "Condition Set",
                30);
        clickObject(DataPartnerModelPage.Selectattribute_icon,"Click on Attribute icon " +
                "in condition Set");

        clickObject(DataPartnerModelPage.Selectattribute_dropdown, "Click on Attribute from Dropdown in " +
                "condition Set");



        elementExistance(DataPartnerModelPage.Attributesinternaltype_icon, "Wait to load Internal Attributes type " +
                        "dropdown in Condition Set",
                30);
        clickObject(DataPartnerModelPage.Attributesinternaltype_icon,"Click on Internal Attribute type icon " +
                "in condition Set");

        clickObject(DataPartnerModelPage.Attributesinternaltype_dropdown, "Click on Internal Attribute from Dropdown" +
                " in " +
                "condition Set");


        elementExistance(DataPartnerModelPage.Attributesinternalvalue_icon, "Wait to load Internal Attributes Value " +
                        "dropdown in Condition Set",
                30);
        clickObject(DataPartnerModelPage.Attributesinternalvalue_icon,"Click on Internal Attribute type icon " +
                "in condition Set");

        clickObject(DataPartnerModelPage.Attributesinternalvalue_dropdown, "Click on Internal Attribute from Dropdown" +
                " in " +
                "condition Set");





        elementExistance(DataPartnerModelPage.Datapartnercondition_exernalicon, "Wait to load Internal Attributes " +
                        "Value " +
                        "dropdown in Condition Set",
                30);
        clickObject(DataPartnerModelPage.Datapartnercondition_exernalicon,"Click on Internal Attribute type icon " +
                "in condition Set");

        clickObject(DataPartnerModelPage.Datapartnercondition_exernal, "Click on Internal Attribute from Dropdown" +
                " in " +
                "condition Set");



        elementExistance(DataPartnerModelPage.Datasourcecondition_exernalicon, "Wait to load Internal Attributes " +
                        "Value " +
                        "dropdown in Condition Set",
                30);
        clickObject(DataPartnerModelPage.Datasourcecondition_exernalicon,"Click on Internal Attribute type icon " +
                "in condition Set");

        clickObject(DataPartnerModelPage.Datasourcecondition_exernal, "Click on Internal Attribute from Dropdown" +
                " in " +
                "condition Set");



        elementExistance(DataPartnerModelPage.Operatorcondition_exernalicon, "Wait to load Internal Attributes " +
                        "Value " +
                        "dropdown in Condition Set",
                30);
        clickObject(DataPartnerModelPage.Operatorcondition_exernalicon,"Click on Internal Attribute type icon " +
                "in condition Set");

        clickObject(DataPartnerModelPage.Operatorcondition_exernal, "Click on Internal Attribute from Dropdown" +
                " in " +
                "condition Set");








        elementExistance(DataPartnerModelPage.datapartnersave_button, "Wait to Scroll Save button",
                30);


        clickObject(DataPartnerModelPage.datapartnersave_button,"Click on Data Partner Save button" +
                " set");



        // Wait for 60 seconds to close the create screen after click on the save button
        elementNonExistance(DataPartnerModelPage.DataPartner_Create_Edit_Screen,"DataPartner Create Screen Closed",10);

        clickObject(DataPartnerModelPage.searchBox_DataPartner,"Search Box");
        clearNTypeValue(DataPartnerModelPage.searchBox_DataPartner, "Search Box", strDataPartnerModelName);



        //elementNonExistance(LookLikeModelPage.LookLike_Create_Edit_Screen,"LookLike Create Screen Closed",30);
        clickObject(DataPartnerModelPage._datapartnerName+"--"+strDataPartnerModelName,"Select DataPartner Model " +
                "from List screen");



        elementExistance(DataPartnerModelPage.DataPartner_Create_Edit_Screen, "Wait to load Edit Screen of " +
                        "DataPartner",
                30);
        // Wait for 60 seconds to close the create screen after click on the save button

        clickObject(DataPartnerModelPage.close_icon,"Click on Data Partner Close button" +
                " set");


        System.out.println("Create Data Partner Model Ends here");

        return strDataPartnerModelName;
    }



}
