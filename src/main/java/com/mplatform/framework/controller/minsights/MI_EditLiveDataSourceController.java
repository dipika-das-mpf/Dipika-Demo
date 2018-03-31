package com.mplatform.framework.controller.minsights;


import com.mplatform.framework.base.Helper;
import com.mplatform.framework.base.Utils;
import com.mplatform.framework.data.minsights.MI_DataVO;
import com.mplatform.framework.model.minsights.MI_LiveDataSourcePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created : VaibhavS
 * Date : 20th Aug 2018
 * Desc : Live Data Source Edit
 */
public class MI_EditLiveDataSourceController extends Utils {
    //Load Test Data
    public MI_DataVO testDataVO = null;

    static MI_LiveDataSourcePage liveDataSourcePage = null;

    public MI_EditLiveDataSourceController(WebDriver driver) throws Exception{
        super(driver);

        liveDataSourcePage = PageFactory.initElements(driver,MI_LiveDataSourcePage.class);
    }


    public String editLiveDataSource (String strdataPartnerName,String strdataSourceName) throws Exception{
        System.out.println("Edit DataSource Starting here");

        //Go to starting point - Datapartner List screen
        mouseOver(liveDataSourcePage.Setup_And_Admin_Menu_Element(),"Setup and Admin Menu");
        clickObject(liveDataSourcePage.DataPartnerMenu,"Click on Data Partner Menu");
        refreshPage();

        elementExistance(liveDataSourcePage.CreateDataPartner, "Create DataPartner Button", 60);
        elementExistance(liveDataSourcePage.searchBox_DataPartner, "Search Box", 20);

        //Search datapartner
        clickObject(liveDataSourcePage.searchBox_DataPartner,"Search Box`");
        clearNTypeValue(liveDataSourcePage.searchBox_DataPartner, "Search Box", strdataPartnerName);

        elementExistance(liveDataSourcePage._datapartnerName+"--"+strdataPartnerName,"Wait Data Partner Search Result",45);
        //Click DataPartner from List
        clickObject(liveDataSourcePage._datapartnerName+"--"+strdataPartnerName,"Select DataPartner from List screen");

        elementExistance(liveDataSourcePage.liveDataSource_Create_Button,"Create DataSource Button",30);

        // Search datasource
        clickObject(liveDataSourcePage.searchBox_DataSource,"Datasource Search Box");
        clearNTypeValue(liveDataSourcePage.searchBox_DataSource,"Type Data Source Name",strdataSourceName);

        elementExistance(liveDataSourcePage._datasourceName+"--"+strdataSourceName,"Wait Data Source Search Result",45);

        //Click DataPartner from List
        clickObject(liveDataSourcePage._datasourceName+"--"+strdataSourceName,"Select DataPartner from List screen");

        waitTillElementDisplayed(liveDataSourcePage.dataSourceCreate_Edit_Screen,"Wait for Live data source screen",45);
        clickObject(liveDataSourcePage.edit_DataSourceName_Link,"Click on the data source name edit link");

        //Check OK and Cancel button, displayed in datapartner Name Edit Field
        elementExistance(liveDataSourcePage.editOK_datasourceName_button,"Data Source Edit Name Field OK button",2);
        elementExistance(liveDataSourcePage.editCancel_datasourceName_button,"Data Source Edit Name Field Cancel button",2);

        System.out.println("Updating "+ strdataSourceName+" datasource name as :");

        //Generate new datapartner Name
        strdataSourceName = "AutoEdit_"+ Helper.generateUniqueValue();

        System.out.println( strdataSourceName+" Edited data Source name");

        clearNTypeValue(liveDataSourcePage.dataSourceName_TextField,"Data Source Name ", strdataSourceName);
        clickObject(liveDataSourcePage.editOK_datasourceName_button,"click OK Button");

        clickObject(liveDataSourcePage.editAttribute_button,"Click on Attribute Button");

        clickObject(liveDataSourcePage._MapAttributes_List_External+"--"+"21-24","Click on the 21-24 text box");
        typeValue(liveDataSourcePage._MapAttributes_List_External+"--"+"21-24","Map Value","24");

        clickObject(liveDataSourcePage._MapAttributes_List_External+"--"+"50-54","Click on the 50-54 text box");
        typeValue(liveDataSourcePage._MapAttributes_List_External+"--"+"50-54","Map Value","51");

        clickObject(liveDataSourcePage.addAttribute_Ok_button,"Click on the OK button");
        clickObject(liveDataSourcePage.saveDataSource_button,"Click on the Data Source Save Button");
        System.out.println("Edit DataSource Ends here");


        return strdataSourceName;

    }

}
