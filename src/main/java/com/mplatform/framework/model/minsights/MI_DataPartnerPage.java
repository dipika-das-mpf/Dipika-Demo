package com.mplatform.framework.model.minsights;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created : Dipika
 * Date : 26th Oct 2018
 * Desc : Data partner object details
 */
public class MI_DataPartnerPage extends MI_Setup_and_Admin_Menu {

    public MI_DataPartnerPage(WebDriver driver ) {super(driver);}

    @FindBy(xpath="//div[@class=\'ui button js-dataPartner-create\']") public WebElement CreateDataPartner;

    /**
     * Create Mi_DataPartnerPage Modal objects
     */
    @FindBy(xpath="//div[contains(@class,'turbine2 ui fixed modal')]") public WebElement DataPartnerCreate_Edit_Screen;

    @FindBy(id="name") public WebElement dataPartnerName_TextField;
    @FindBy(id="contactName") public WebElement dataPartnerContact_TextField;
    @FindBy(id="contactEmailId") public WebElement contactEmailId_TextField;
    @FindBy(id="saveDataPartner") public WebElement saveButton;
    @FindBy(id="allowDP") public WebElement ftpSelection;

    @FindBy(xpath="//div[@class='eight wide field']//i[@class='dropdown icon']") public WebElement selectdataparnterAdvertiserScope_DropDownIcon;
    //values are 'THESE_ADV" and 'ALL_ADV'
    public String _datapartnerAdvertiserScope = "//div[contains(@class,'selectpicker selection ui')]//div[@data-value='EXTERNALDATA']";
    public String _datapartnerAllAdvertiserScope = "//div[contains(@class,'selectpicker selection ui')" +
            "]//div[@data-value='EXTERNALDATA']";
    @FindBy(xpath="/html/body/div[3]/div/div[2]/div/div[1]/div[1]/div[1]/div/div[2]/div[1]") public WebElement datapartnerAllAdvertiserScope ;


    @FindBy(xpath = "//div[@id='advertiserSelect']/i") public WebElement advertiserSelectOnTheseScope_DropDownIcon;
    @FindBy(xpath = "//div[@id='advertiserSelect']/input[@class='search']") public WebElement advertiserSelectOnTheseScope_Input_TextField;

    @FindBy(id="allowDP") public WebElement batchFileDelivery_Checkbox;
    @FindBy(xpath="//div[contains(@class,'ui compact small message')]") public WebElement batchdeliveryFTPAccount_Text;

    //DataPartner List Screen
    @FindBy(xpath="//input[@class='js-search-box']") public WebElement searchBox_DataPartner;
    public String _datapartnerName = "//table[@id='dataPartnerTable']//td[contains(@class,'sorting_1')]/div//a[text()='EXTERNALDATA']";


    @FindBy(xpath="//a[contains(@class,'js-datapartner-edit ui')]") public WebElement edit_dataPartner_Link;
    @FindBy(xpath="//a[@class='js-name-edit action-links']") public WebElement edit_datapartnerName_Link;

    @FindBy(xpath="//a[@class='js-commit-name action-links']") public WebElement edit_datapartnerName_Ok_Button;
    @FindBy(xpath="//a[@class='js-cancel-name action-links']") public WebElement edit_datapartnerName_Cancel_Button;
}
