package com.mplatform.framework.controller.minsights;

import com.mplatform.framework.base.Helper;
import com.mplatform.framework.base.Utils;
import com.mplatform.framework.data.minsights.MI_DataVO;
import com.mplatform.framework.model.minsights.MI_LiveDataSourcePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created : VaibhavS
 * Date : 11th Aug 2018
 * Desc : Data Source
 */
public class MI_CreateLiveDataSourceController extends Utils{

    //Load Test Data
    public MI_DataVO testDataVO = null;

    static MI_LiveDataSourcePage liveDataSourcePage = null;

    public MI_CreateLiveDataSourceController(WebDriver driver) throws Exception {
        super(driver);

        liveDataSourcePage = PageFactory.initElements(driver, MI_LiveDataSourcePage.class);
    }

    /**
     * Create Live Data Source
     * @return strLiveDataSourceName
     * @throws Exception
     */
    public String createLiveDataSource(String datapartnerName) throws Exception {

       System.out.println("Create DataSource Starting here");

       clickObject(liveDataSourcePage.Setup_And_Admin_Menu_Element(), "Setup and Admin Menu");
       mouseOver(liveDataSourcePage.DataPartnerMenu, "DataPartnerMenu");
       clickObject(liveDataSourcePage.DataPartnerMenu, "DataPartner Menu Option Clicked");

       elementExistance(liveDataSourcePage.CreateDataPartner, "Create DataPartner Button", 5);
       clickObject(liveDataSourcePage.searchBox_DataPartner, "DataPartner Search Box");
       clearNTypeValue(liveDataSourcePage.searchBox_DataPartner, "DataPartner Search Box", datapartnerName);
       clickObject(liveDataSourcePage._datapartnerName + "--" + datapartnerName, "Select DataPartner from List screen");

       waitTillElementDisplayed(liveDataSourcePage.liveDataSource_Create_Button, "Create Live Data Source Button", 10);
       clickObject(liveDataSourcePage.liveDataSource_Create_Button, "click on the Create Live Data Source Button");

       waitTillElementDisplayed(liveDataSourcePage.dataSourceCreate_Edit_Screen,"Wait for Live data source screen",45);
       String strLiveDataSourceName = testDataVO.getTestData("LiveDataSourceName") + "_" + Helper.generateUniqueValue();
       typeValue(liveDataSourcePage.dataSourceName_TextField, "LiveDataSourceName", strLiveDataSourceName);

       clickObject(liveDataSourcePage.indexingEnabled_Checkbox,"Click on the indexing Enabled");

       clickObject(liveDataSourcePage.addAttribute_button,"Click on the Add Attribute button");

       // Wait for 60 seconds to close the create screen after click on the save button
       elementExistance(liveDataSourcePage.attributeName_TextField,"AttributeName Text field",5);

       //Enter First Custom Variable
       typeValue(liveDataSourcePage.attributeName_TextField, "First Attribute Name","AGE");
       typeValue(liveDataSourcePage.attributeId_TextField, "First Attribute Id","AGE");

       // Wait for 60 seconds to close the create screen after click on the save button
       elementExistance(liveDataSourcePage._MapAttributes_List_External+"--"+"18-20","External Map Field Present",5);

       // Mapped External Attributes Values to Internal Value
       clickObject(liveDataSourcePage._MapAttributes_List_External+"--"+"18-20","Click on the 18-20 text box");
       typeValue(liveDataSourcePage._MapAttributes_List_External+"--"+"18-20","Map Value","19");
       clickObject(liveDataSourcePage._MapAttributes_List_External+"--"+"21-24","Click on the 21-24 text box");

       clickObject(liveDataSourcePage._MapAttributes_List_External+"--"+"18-20","Click on the 18-20 text box");
       typeValue(liveDataSourcePage._MapAttributes_List_External+"--"+"18-20","Map Value","20");

       clickObject(liveDataSourcePage._MapAttributes_List_External+"--"+"30-34","Click on the 30-34 text box");
       typeValue(liveDataSourcePage._MapAttributes_List_External+"--"+"30-34","Map Value","32");

       clickObject(liveDataSourcePage._MapAttributes_List_External+"--"+"40-44","Click on the 40-44 text box");
       typeValue(liveDataSourcePage._MapAttributes_List_External+"--"+"40-44","Map Value","43");

       clickObject(liveDataSourcePage.addAttribute_Ok_button,"Click on the OK button");
       clickObject(liveDataSourcePage.saveDataSource_button,"Click on the Data Source Save Button");

       System.out.println("Create DataSource End here");

       return strLiveDataSourceName;
   }

    /**
     * Create Live Data Source with Share Account
     * @return strLiveDataSourceName
     * @throws Exception
     */
    public String createShareWithLiveDataSource(String datapartnerName) throws Exception {

        String strShareAccountName = "zNA Test1.1";

        System.out.println("Create Shared Account DataSource Starting here");

        clickObject(liveDataSourcePage.Setup_And_Admin_Menu_Element(), "Setup and Admin Menu");
        mouseOver(liveDataSourcePage.DataPartnerMenu, "DataPartnerMenu");
        clickObject(liveDataSourcePage.DataPartnerMenu, "DataPartner Menu Option Clicked");

        elementExistance(liveDataSourcePage.CreateDataPartner, "Create DataPartner Button", 5);
        clickObject(liveDataSourcePage.searchBox_DataPartner, "DataPartner Search Box");
        clearNTypeValue(liveDataSourcePage.searchBox_DataPartner, "DataPartner Search Box", datapartnerName);
        clickObject(liveDataSourcePage._datapartnerName + "--" + datapartnerName, "Select DataPartner from List screen");

        waitTillElementDisplayed(liveDataSourcePage.liveDataSource_Create_Button, "Create Live Data Source Button", 10);
        clickObject(liveDataSourcePage.liveDataSource_Create_Button, "click on the Create Live Data Source Button");

        waitTillElementDisplayed(liveDataSourcePage.dataSourceCreate_Edit_Screen,"Wait for Live data source screen",45);
        String strLiveDataSourceName = testDataVO.getTestData("LiveDataSourceName") + "_" + Helper.generateUniqueValue();
        typeValue(liveDataSourcePage.dataSourceName_TextField, "LiveDataSourceName", strLiveDataSourceName);

        clickObject(liveDataSourcePage.indexingEnabled_Checkbox,"Click on the indexing Enabled");

        clickObject(liveDataSourcePage.addAttribute_button,"Click on the Add Attribute button");

        // Wait for 60 seconds to close the create screen after click on the save button
        elementExistance(liveDataSourcePage.attributeName_TextField,"AttributeName Text field",5);

        //Enter First Custom Variable
        typeValue(liveDataSourcePage.attributeName_TextField, "First Attribute Name","AGE");
        typeValue(liveDataSourcePage.attributeId_TextField, "First Attribute Id","AGE");

        // Wait for 60 seconds to close the create screen after click on the save button
        elementExistance(liveDataSourcePage._MapAttributes_List_External+"--"+"18-20","External Map Field Present",5);

        // Mapped External Attributes Values to Internal Value
        clickObject(liveDataSourcePage._MapAttributes_List_External+"--"+"18-20","Click on the 18-20 text box");
        typeValue(liveDataSourcePage._MapAttributes_List_External+"--"+"18-20","Map Value","19");
        clickObject(liveDataSourcePage._MapAttributes_List_External+"--"+"21-24","Click on the 21-24 text box");

        clickObject(liveDataSourcePage._MapAttributes_List_External+"--"+"18-20","Click on the 18-20 text box");
        typeValue(liveDataSourcePage._MapAttributes_List_External+"--"+"18-20","Map Value","20");

        clickObject(liveDataSourcePage._MapAttributes_List_External+"--"+"30-34","Click on the 30-34 text box");
        typeValue(liveDataSourcePage._MapAttributes_List_External+"--"+"30-34","Map Value","32");

        clickObject(liveDataSourcePage._MapAttributes_List_External+"--"+"40-44","Click on the 40-44 text box");
        typeValue(liveDataSourcePage._MapAttributes_List_External+"--"+"40-44","Map Value","43");

        clickObject(liveDataSourcePage.addAttribute_Ok_button,"Click on the OK button");

        //Added condition for to make data source as shared account data source -- start here
         clickObject(liveDataSourcePage.sharewith_dropdown_icon,"click on the sharewith dropdown");
         clickObject(liveDataSourcePage._sharewithAccountName+"--"+strShareAccountName," select sharewith account name");
        //Added condition for to make data source as shared account data source -- end here

        clickObject(liveDataSourcePage.saveDataSource_button,"Click on the Data Source Save Button");

        System.out.println("Create DataSource End here");

        return strLiveDataSourceName;
    }

   }
