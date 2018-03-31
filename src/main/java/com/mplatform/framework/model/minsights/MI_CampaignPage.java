package com.mplatform.framework.model.minsights;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by dipika.das on 7/27/17.
 */
public class MI_CampaignPage extends MI_ActivityPage{

    public MI_CampaignPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id="campaigns") public WebElement Campaign_Tab;
    @FindBy(id="createButton") public WebElement Campaign_CreateCampaign_Button;

    @FindBy(id="name") public WebElement CampaignName_TextField;

    @FindBy(id=".//*[contains(text(),'Week Starting Day')]/..//i[@class='dropdown icon']")
    public WebElement weekStartingDayBox;

    @FindBy(id="startDate")
    public WebElement Campaign_Start_Date;

    @FindBy(id="endDate")
    public WebElement Campaign_End_Date;

    @FindBy(xpath="html/body/div[3]/div/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div")
    public WebElement External_Platform;

    @FindBy(id="parameterId")
    public WebElement Campaign_Parameter;

    @FindBy(xpath="html/body/div[3]/div/div[2]/div/div[2]/div/div/div[2]/div/div[3]/input")
    public  WebElement External_Parameter_id;

    @FindBy(id="description") public WebElement notes_CampaignTextField;


    @FindBy(xpath="//div[contains(@class,'js-save-button')]")
    public WebElement Campaign_Save_Button;




    //Campaign List Screen

    @FindBy(xpath = "//input[contains(@class,'js-search-box')]") public WebElement Campaign_SearchBox;
    public String _Campaign_List_NameColumn ="//table[@id='campaignTable']//tr/td/a[text()='EXTERNALDATA']";


    //Edit Campaign Page

    @FindBy(xpath = "//div[contains(@class,'turbine2 ui fixed modal')]//div[contains(text(),'Edit Campaign')]")
    public WebElement EditCampaign_Screen;
    @FindBy(how = How.XPATH, using="//div[@class='read name js-header-read']/span[@class='buttons']/a[@class='js-name-edit action-links']")
    public WebElement EditCampaign_EditNameLink;

    @FindBy(how = How.XPATH, using="//div[@class='edit name ui form js-header-edit']/input[@class='js-name ui input']")
    public WebElement EditCampaign_EditName_Text_Field;
    @FindBy(how = How.XPATH, using="//div[@class='edit name ui form js-header-edit']/span[@class='js-edit-buttons buttons']/a[@class='js-commit-name action-links']")
    public WebElement EditCampaign_EditName_OK_button;
    @FindBy(how = How.XPATH, using="//div[@class='edit name ui form js-header-edit']/span[@class='js-edit-buttons buttons']/a[@class='js-cancel-name action-links']")
    public WebElement EditCampaign_EditName_Cancel_button;

    @FindBy(xpath ="//div[contains(@class,'js-add-buttons')]/a") public WebElement CampaignID_Mapping_ADD_Link;


    @FindBy(xpath="html/body/div[3]/div/div[2]/div/div[2]/div/div/div[2]/div[2]/div[1]/div")
    public WebElement External_Second_Platform;

    @FindBy(xpath="html/body/div[3]/div/div[2]/div/div[2]/div/div/div[2]/div[2]/div[1]/div/div[2]/div[5]")
    public WebElement External_Second_Platform_Value;

    @FindBy(xpath="html/body/div[3]/div/div[2]/div/div[2]/div/div/div[2]/div[2]/div[2]/div")
    public WebElement Campaign_Second_Parameter;

    @FindBy(xpath="html/body/div[3]/div/div[2]/div/div[2]/div/div/div[2]/div[2]/div[3]/input")
    public  WebElement External_Second_Parameter_id;

    @FindBy(xpath = "html/body/div[3]/div/div[2]/div/div[2]/div/div/div[2]/div[2]/div[4]/a") public WebElement Delete_External_PlatForm;




    public String _externalplatform = "//div[contains(@class,'js-platform')]//div[contains(@class,'menu')]/div[contains(text(),'EXTERNALDATA')]";
    public String _campaignparameter = "//div[contains(@class,'js-parameter')]//div[contains(@class,'menu')]/div[contains(text(),'EXTERNALDATA')]";
    public String _secondexternalplatform = "//div[contains(@class,'js-platform')]//div[contains(@class,'menu')]/div[contains(@class,'item','EXTERNALDATA')]";
    public String _secondcampaignparameter = "//div[contains(@class,'js-parameter')]//div[contains(@class,'menu')]/div[contains(text(),'EXTERNALDATA2')]";


}