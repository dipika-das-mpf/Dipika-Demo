package com.mplatform.framework.model.minsights;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by mohamed.abdulkadar on 6/16/2017.
 */
public class MI_ActivityPage extends MI_AdvertiserPage {

    public MI_ActivityPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id="activities-tab") public WebElement Activities_Tab;
    @FindBy(id="createButton") public WebElement Activity_CreateActivity_Button;

    @FindBy(id="name") public WebElement ActivityName_TextField;
    @FindBy(id="indexEnabled") public WebElement EnableIndexing_CheckBox;

    @FindBy(xpath="//div[contains(@class,'js-cv-list field')]/div[1]//input[contains(@class,'js-input-name')]")
    public WebElement Activity_CustomVariableName_FirstRow;

    @FindBy(xpath="//div[contains(@class,'js-cv-list field')]/div[1]//input[contains(@class,'js-input-key')]")
    public WebElement Activity_CustomVariableTagParameterName_FirstRow;

    @FindBy(xpath="//div[contains(@class,'js-cv-list field')]/div[1]//input[contains(@class,'js-input-key')]")
    public WebElement Activity_Delete_CustomVariable_FirstRow;

    @FindBy(xpath="//div/a[contains(@class,'js-variable-remove')]") public WebElement Clear_CusomtVariable;

    @FindBy(xpath="//div[contains(@class,'js-cv-list field')]/div[2]//input[contains(@class,'js-input-name')]")
    public WebElement Activity_CustomVariableName_SecondRow;

    @FindBy(xpath="//div[contains(@class,'js-cv-list field')]/div[2]//input[contains(@class,'js-input-key')]")
    public WebElement Activity_CustomVariableTagParameterName_SecondRow;

    @FindBy(xpath="//div[contains(@class,'js-cv-list field')]/div[3]//input[contains(@class,'js-input-name')]")
    public WebElement Activity_CustomVariableName_ThirdRow;

    @FindBy(xpath="//div[contains(@class,'js-cv-list field')]/div[3]//input[contains(@class,'js-input-key')]")
    public WebElement Activity_CustomVariableTagParameterName_ThirdRow;

    @FindBy(xpath="//div[contains(@class,'js-cv-list field')]/div[2]/div[3]/div/a")
    public WebElement Activity_Delete_CustomVariable_SecondRow;

    @FindBy(xpath="//div[contains(@class,'js-add-buttons field')]/a") public WebElement Activity_Add_CustomVariable_Link;

    @FindBy(xpath="//input[contains(@name,'newActivityClientInput')]") public WebElement Activity_ClientID_TextField;

    @FindBy(xpath="//input[contains(@name,'newActivityActionInput')]") public WebElement Activity_ActionID_TextField;

    @FindBy(xpath="//input[contains(@id,'account_selection_dynamic')]") public WebElement Activity_Dynamic_RadioButton;

    @FindBy(xpath="//input[contains(@id,'account_selection_static')]") public WebElement Activity_Static_RadioButton;

    @FindBy(xpath="//div[contains(@class,'js-share-activity inline fields')]//div/i[contains(@class,'dropdown')]")
    public WebElement Activity_ShareActivity_List_Dropdown;

    public String _Activity_sharedActivty = "//div[contains(@class,'js-share-activity inline fields')]//div[contains(@class,'menu')]/div[text()='EXTERNALDATA";

    @FindBy(xpath="//div[contains(@class,'js-save-button')]")
    public WebElement Activity_Save_Button;



    //Activity List page
    @FindBy(xpath="//input[contains(@class,'js-search-box')]") public WebElement Activity_SearchBox;
    public String _Activity_List_NameColumn ="//table[@id='activityTable']//tr/td/div/a[text()='EXTERNALDATA']";
    @FindBy(xpath="//table[@id='activityTable']//tr/td[2]//i[contains(@class,'js-activity-tag turbine tag link icon')]") public WebElement Activity_List_TagIcon_Column;
        @FindBy(xpath="//table[@id='activityTable']//tr/td[3]/span") public WebElement Activity_List_ActivityIDColumn;
    @FindBy(xpath="//table[@id='activityTable']//tr/td[5]/span") public WebElement Activity_List_TagIDColumn;



    @FindBy(xpath="//div[contains(@class,'ui modal turbine2')]//div[contains(text(),'Copy Activity Tag Code')]")
    public WebElement Activity_Copy_Activity_Tag_Code_Screen;
    @FindBy(xpath="//button[contains(@class,'js-tag-copy ui basic blue button')]") public WebElement Activity_Copy_Tag_Code_Button;
    @FindBy(xpath="//button[contains(@class,'ui ok primary button')]") public WebElement Activity_CopyTagCode_OK_Button;



    //Activity Edit Page
    @FindBy(xpath="//div[contains(@class,'turbine2 ui fixed modal')]//div[contains(text(),'Edit Activity')]")
    public WebElement EditActivity_Screen;
    @FindBy(xpath="//div[@class='read name js-header-read']/span[@class='js-name-read header']")
    public WebElement EditActivity_NameLabel;
    public String _EditActivity_NameLabel="//div[@class='read name js-header-read']/span[text()='EXTERNALDATA']";
    @FindBy(xpath="//div[contains(@class,'entity subtext js-subtext subheader-title')]")
    public WebElement Activity_EditScreen_subheader;
    @FindBy(how = How.XPATH, using="//div[@class='read name js-header-read']/span[@class='buttons']/a[@class='js-name-edit action-links']")
    public WebElement EditActivity_EditNameLink;
    @FindBy(how = How.XPATH, using="//div[@class='edit name ui form js-header-edit']/input[@class='js-name ui input']")
    public WebElement EditActivity_EditName_Text_Field;
    @FindBy(how = How.XPATH, using="//div[@class='edit name ui form js-header-edit']/span[@class='js-edit-buttons buttons']/a[@class='js-commit-name action-links']")
    public WebElement EditActivity_EditName_OK_button;
    @FindBy(how = How.XPATH, using="//div[@class='edit name ui form js-header-edit']/span[@class='js-edit-buttons buttons']/a[@class='js-cancel-name action-links']")
    public WebElement EditActivity_EditName_Cancel_button;

    /*Cross Market Activity*/
    @FindBy(how = How.XPATH, using="//div[contains(@class,'js-cross-account cross-account-adv')]")
    public WebElement CrossMarketActivity_CrossMarket_Label;


    /*Close Icon*/
    @FindBy(how = How.XPATH, using="//div[contains(@class,'turbine2 ui')]/i[@class='close icon']")
    public WebElement EditActivity_Screen_Close_button;






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
