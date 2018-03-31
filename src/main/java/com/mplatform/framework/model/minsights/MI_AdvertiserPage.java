package com.mplatform.framework.model.minsights;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

/**
 * Created by mohamed.abdulkadar on 6/13/2017.
 */
public class MI_AdvertiserPage extends MI_Setup_and_Admin_Menu {

    public MI_AdvertiserPage(WebDriver driver) {
        super(driver);
    }
    /*public WebElement Setup_And_Admin_Menu_Element(){

        return driver.findElement(By.xpath("//div[@class='ui five item attached mainmenu menu']//div[@class='ui container']/div[4]"));
    }

    @FindBy(id="menuAdvertiser") public WebElement MI_AdvertiserPage;*/

    @FindBy(xpath="//button[@class=\"js-advertiser-create ui button\" ]") public WebElement CreateAdvertiser;

    //Create MI_AdvertiserPage Page

    @FindBy(xpath="//div[contains(@class,'turbine2 ui fixed modal')]") public WebElement AdvertiserCreate_Edit_Screen;
    @FindBy(id="nameAdvertiser") public WebElement EnterAdvertiserName_TextField;
    @FindBy(xpath="//div[@class='two fields']/div[@class='field']/div/i[@class='dropdown icon']") public WebElement SelectAgency_DropDownIcon;
    @FindBy(xpath="//div[@class='two fields']/div[@class='field']/div/div[1]") public Select SelectAgency_Lists;

    public String _agency = "//div[contains(@class,'js-agency-dd active')]//div[contains(@class,'menu')]/div[contains(text(),'EXTERNALDATA')]";

    //Create MI_AdvertiserPage Page Cross Market Section
    @FindBy(xpath="//div[contains(@class,'checkbox js-cross-account-cb')]/label") public WebElement CrossMarketAdvertiser_Checkbox;
    @FindBy(xpath="//div[contains(@class,'js-additional-account-dd')]/i[@class='dropdown icon']") public WebElement AdditionalMarkets_DropDownIcon;
    @FindBy(xpath="//div[contains(@class,'js-additional-account-dd')]/i[@class='dropdown icon']") public Select AdditionalMarkets_Lists;
    @FindBy(xpath="//div[contains(@class,'js-default-account-dd')]/span") public WebElement DefaultMarket;
    @FindBy(id="notesAdvertiser") public WebElement notes_TextField;
    @FindBy(xpath="//div[contains(@class,'js-save-button')]") public WebElement AdvertiserSave_Button;


    //Advertiser List Screen
    @FindBy(xpath="//input[@class='js-search-box']") public WebElement SearchBox;
    public String _advertiserName = " //table[@id='advertiserList']//td[contains(@class,'adv-title-name')]/div/a[text()='EXTERNALDATA']";


    //Advertiser Edit Screen
    //@FindBy(how = How.CLASS_NAME, using="js-advertiser-edit ui tiny action-links") public WebElement Advertiser_EditLink;
    @FindBy(xpath="//div/a[contains(@class,'js-advertiser-edit ui tiny action-links')]") public WebElement Advertiser_EditLink;

    /*@FindBy(how = How.CLASS_NAME, using="green circle checked turbine-status toggle turbine-status icon")
    public WebElement EditAdvertiser_Status_Icon_Active;*/
    @FindBy(xpath="//div/span[contains(@class,'js-status-icon-circle circular-status-icon')]/i[contains(@class,'green circle checked turbine-status toggle turbine-status icon')]")
    public WebElement EditAdvertiser_Status_Icon_Active;

    /*@FindBy(how = How.CLASS_NAME, using="grey circle unchecked turbine-status toggle turbine-status icon")
    public WebElement EditAdvertiser_Status_Icon_InActive;*/
    @FindBy(xpath="//div/span[contains(@class,'js-status-icon-circle circular-status-icon')]/i[contains(@class,'grey circle unchecked turbine-status toggle turbine-status icon')]")
    public WebElement EditAdvertiser_Status_Icon_InActive;

   /* @FindBy(how = How.CLASS_NAME, using="js-advertiser-breadcrumb")
    public WebElement EditAdvertiser_BreadCrumb_AdvertiserName;*/
   /*@FindBy(xpath="//div/span[contains(@class,'js-advertiser-breadcrumb')]")
   public WebElement EditAdvertiser_BreadCrumb_AdvertiserName;*/

    @FindBy(xpath="//div[contains(@class,'turbine2 ui fixed modal')]//div[contains(text(),'Edit Advertiser')]")
    public WebElement EditAdvertiser_Screen;


    @FindBy(xpath="//div//a[contains(@class,'js-name-edit action-links')]")
    public WebElement EditAdvertiser_EditNameLink;

    /*@FindBy(how = How.CLASS_NAME, using="js-commit-name action-links") public WebElement EditAdvertiser_EditName_OK_button;*/
    @FindBy(how = How.XPATH, using="//div[@class='edit name ui form js-header-edit']/span[@class='js-edit-buttons buttons']/a[@class='js-commit-name action-links']")
    public WebElement EditAdvertiser_EditName_OK_button;

    /*@FindBy(how = How.CLASS_NAME, using="js-cancel-name action-links") public WebElement EditAdvertiser_EditName_Cancel_button;*/
    @FindBy(how = How.XPATH, using="//div[@class='edit name ui form js-header-edit']/span[@class='js-edit-buttons buttons']/a[@class='js-cancel-name action-links']")
    public WebElement EditAdvertiser_EditName_Cancel_button;

    @FindBy(how = How.CLASS_NAME, using="entity subtext js-modal-subheader") public WebElement EditAdvertiser_Account_Advertiserkey_Label;

    @FindBy(xpath="//div[contains(@class,'item ui fluid selection dropdown js-agency-dd')]/span")
    public WebElement EditAdvertiser_SelectedAgency;

    @FindBy(how = How.XPATH, using="//div/span[contains(@class,'js-advertiser-breadcrumb')]")
    public WebElement EditAdvertiser_AdvertiserBreadCrumb;
    @FindBy(how = How.XPATH, using="//div[@class='read name js-header-read']/span[@class='js-name-read header']")
    public WebElement EditAdvertiser_AdvertiserNameLabel;


    /*Advertiser List */
    @FindBy(how = How.XPATH, using="//table[@id='advertiserList']//tbody/tr/td[1]/div/span/i[contains(@class,'green circle checked turbine-status toggle turbine-status icon')]")
    public WebElement AdvertiserList_Column_Status_ActiveIcon;
    @FindBy(how = How.XPATH, using="//table[@id='advertiserList']//tbody/tr/td[1]/div/span/i[contains(@class,'gray circle checked turbine-status toggle turbine-status icon')]")
    public WebElement AdvertiserList_Column_Status_InactiveIcon;
    @FindBy(how = How.XPATH, using="//table[@id='advertiserList']//tbody/tr/td[1]/span/div/label")
    public WebElement AdvertiserList_Column_Status_Toggle;
    @FindBy(how = How.XPATH, using="//table[@id='advertiserList']//tbody/tr/td[1]/span/div/div[@class='menuOptions']/div[@class='actionWrap']/a[@class='small ui button blue']")
    public WebElement AdvertiserList_Column_Status_Toggle_YES_Button;
    @FindBy(how = How.XPATH, using="//table[@id='advertiserList']//tbody/tr/td[1]/span/div/div[@class='menuOptions']/div[@class='actionWrap']/a[@class='small ui button blue basic']")
    public WebElement AdvertiserList_Column_Status_Toggle_NO_Button;
    @FindBy(how = How.XPATH, using="//table[@id='advertiserList']//tbody/tr/td[2]/i[@class='check icon']")
    public WebElement AdvertiserList_Column_CrossMarket_Checked;
    @FindBy(how = How.XPATH, using="//table[@id='advertiserList']//tbody/tr/td[3]")
    public WebElement AdvertiserList_Column_ID;
    @FindBy(how = How.XPATH, using="//table[@id='advertiserList']//tbody/tr/td[4]/i[@class='check icon']")
    public WebElement AdvertiserList_Column_SHARED_Checked;
    @FindBy(how = How.XPATH, using="//table[@id='advertiserList']//tbody/tr/td[5]")
    public WebElement AdvertiserList_Column_Agency;
    @FindBy(how = How.XPATH, using="//table[@id='advertiserList']//tbody/tr/td[6]/a")
    public WebElement AdvertiserList_Column_Models;
    @FindBy(how = How.XPATH, using="//table[@id='advertiserList']//tbody/tr/td[7]/a")
    public WebElement AdvertiserList_Column_Campaigns;
    @FindBy(how = How.XPATH, using="//table[@id='advertiserList']//tbody/tr/td[8]/a")
    public WebElement AdvertiserList_Column_Activities;
    @FindBy(how = How.XPATH, using="//div/i[@class='close icon']")
    public WebElement AdvertiserScreen_Close_CrossIcon;


    /*Cross Advertiser*/
    @FindBy(how = How.XPATH, using="//div[@class='field js-cross-account-cb-field']//div[contains(@class,'ui checkbox js-cross-account-cb')]/label")
    public WebElement Advertiser_MakeThisACrossMarketAdvertiser_CheckBox;
    @FindBy(how = How.XPATH, using="//div[@class='field js-cross-account-cb-field']//div[contains(@class,'ui checkbox js-cross-account-cb')]/input[@type='checkbox' and @checked='checked']")
    public WebElement Advertiser_MakeThisACrossMarketAdvertiser_CheckBox_Checked;
    @FindBy(how = How.XPATH, using="//div[@class='field js-cross-account-cb-field']//div[contains(@class,'ui checkbox js-cross-account-cb')]/label")
    public WebElement Advertiser_MakeThisACrossMarketAdvertiser_CheckBox_CheckedLabel;

    @FindBy(how = How.XPATH, using="//div[@class='two fields js-cross-account-fields']//div[@class='item ui fluid dropdown selection js-default-account-dd']/i")
    public WebElement Advertiser_DefaultMarket_DropDownIcon;
    public String _DefaultMarketListItem = "//div[@class='two fields js-cross-account-fields']/div[2]//div[contains(@class,'item ui fluid dropdown selection js-default-account-dd')]/div[contains(@class,'menu')]/div[contains(text(),'EXTERNALDATA')]";

    @FindBy(how = How.XPATH, using="//div[@class='two fields js-cross-account-fields']//div[@class='item ui fluid search selection dropdown multiple js-additional-account-dd']/i")
    public WebElement Advertiser_Additional_DropDownIcon;
    @FindBy(how = How.XPATH, using="//div[contains(@class,'item ui fluid search selection dropdown multiple js-additional-account-dd')]/input[2]")
    public WebElement Advertiser_Additional_SearchField;
    public String _AdditionalMarketListItem = "//div[@class='two fields js-cross-account-fields']/div[1]//div[contains(@class,'item ui fluid search selection dropdown multiple js-additional-account-dd')]/div[contains(@class,'menu')]/div[text()='EXTERNALDATA']";

    @FindBy(how = How.XPATH, using="//div[@class='ui message js-message warning warning-msg']")
    public WebElement CrossMarket_WarningMessage;
    @FindBy(how = How.XPATH, using="//div[@class='ui primary approve button js-confirm-button']")
    public WebElement CrossMarketAdvertiser_YES_SAVE_ANYWAY_BUTTON;
    @FindBy(how = How.XPATH, using="//div[contains(@class,'ui label cross-account-adv')]")
    public WebElement CrossMarketAdvertiser_CrossMarket_Label;
    @FindBy(how = How.XPATH, using="//div[@class='turbine breadcrumb-bar']//div[@class='ui breadcrumb']/div[4]")
    public WebElement CrossMarketAdvertiser_CrossMarket_ToolTip;

    public String _DefaultAccountSelected = "//div[@class='item ui fluid dropdown selection js-default-account-dd']/span[contains(text(),'EXTERNALDATA')]";
    public String _AdditionalMarkets_Selected = "//div[@class='ui field added-account-list']/ul/li[contains(text(),'EXTERNALDATA')]";













}
