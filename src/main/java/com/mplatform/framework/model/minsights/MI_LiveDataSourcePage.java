package com.mplatform.framework.model.minsights;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created : VaibhavS
 * Date : 11th Aug 2018
 * Desc : Data Source object details
 */

public class MI_LiveDataSourcePage extends MI_DataPartnerPage {
    public MI_LiveDataSourcePage(WebDriver driver ){super(driver);}

    @FindBy(id="live-data-source-tab") public WebElement liveDataSource_Tab;
    @FindBy(id="batch-data-source-tab") public WebElement batchDataSource_Tab;

    @FindBy(id="createButton") public WebElement liveDataSource_Create_Button;

    @FindBy(xpath="//div[contains(@class,'ui dimmer modals')]") public WebElement dataSourceCreate_Edit_Screen;

    @FindBy(id="name") public WebElement dataSourceName_TextField;
    @FindBy(xpath="//input[@class='js-input-value']") public WebElement expireAfterDay_TextField;
    @FindBy(id="indexEnabled") public WebElement indexingEnabled_Checkbox;
    @FindBy(xpath="//button[contains(@class,'js-attribute-add ui small')]") public WebElement addAttribute_button;
    @FindBy(id="attributeName") public WebElement attributeName_TextField;
    @FindBy(id="attributeId") public WebElement attributeId_TextField;
    @FindBy(xpath="//a[contains(@class,'js-commit ui small')]") public WebElement addAttribute_Ok_button;
    @FindBy(xpath="//a[contains(@class,'js-cancel ui small')]") public WebElement addAttribute_Cancel_button;
    @FindBy(xpath="//a[@class='js-attribute-edit action-links']") public WebElement editAttribute_button;
    @FindBy(xpath="//a[@class='js-delete-pop action-links']") public WebElement cancelAttribute_button;

    @FindBy(id="saveDataPartner") public WebElement saveDataSource_button;

    // Internal Attributes maps with External Attributes
    public String _MapAttributes_List_External ="//span[@class='js-value-internal'][text()='EXTERNALDATA']/../../input[@class='search']";

    //Edit Datasource object finding and List screen object finding is still pending

    //Data source List Screen
    @FindBy(xpath="//input[@class='js-search-box']") public WebElement searchBox_DataSource;
    public String _datasourceName = "//table[@id='liveDataSourceTable']//div//a[text()='EXTERNALDATA']";

    @FindBy(xpath="//div[@class='read name js-header-read']/span[@class='buttons']/a[@class='js-name-edit action-links']") public WebElement edit_DataSourceName_Link;

    @FindBy(xpath="//a[@class='js-commit-name action-links']") public WebElement editOK_datasourceName_button;
    @FindBy(xpath="//a[@class='js-cancel-name action-links']") public WebElement editCancel_datasourceName_button;

    // Sharewith Account selection from dropdown
    @FindBy(xpath="//div[contains(@class,'js-share-activity inline fields')]//i") public WebElement sharewith_dropdown_icon;
    public String _sharewithAccountName = "//div[contains(@class,'menu transition visible')]//div[text()='EXTERNALDATA']";


}
