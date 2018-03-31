package com.mplatform.framework.model.minsights;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MI_CustomSegmentModelPage extends MI_Audience_Menu {


    public MI_CustomSegmentModelPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div/div[contains(@class,'menu')]/a[@id='menuLookalikeModel']")
    public WebElement LookLikeModelMenu;

    @FindBy(xpath = "//button[@class=\"js-model-create ui button\" ]")
    public WebElement CreateLookLikeModel;


    /**
     * Create Mi_DataPartnerPage Modal objects
     */


    @FindBy(id = "modelName")
    public WebElement lookLikeModelName_TextField;

    @FindBy(id = "activateSegment")
    public WebElement saveLookLikeButton;
    @FindBy(xpath = "html/body/div[3]/div/div[1]/div[3]/div[3]/div/div[1]/div/div/input[2]") public WebElement selectLookLikeAdvertiserScope_DropDownIcon;

    @FindBy(xpath = "html/body/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div/div/div[1" +
            "]/div[1]/div/div[2]/div/a")
    public WebElement selectLookLikeAttributeScope_DropDownIcon;

    @FindBy(xpath = "html/body/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div/div/div[1]/div[1]/div[3]/div[2]/div[2]/div[1]/div/div/input[2]")
    public
    WebElement selectLookLikeActivityAttributeScope_DropDownIcon;
    @FindBy(xpath = "html/body/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div/div/div[1]/div[1]/div[4]/div/div[2]/div[1]")
    public WebElement attributeactivitydurationcondition;



    @FindBy(xpath = "html/body/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div/div/div[1]/div[1]/div[4]/div/input[2]")
    public WebElement selectLookLikeActivityDurationAttributeScope_DropDownIcon;

    @FindBy(xpath = "html/body/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div/div/div[1]/div[1]/div[3]/div[2]/div[2]/div[1]/div/div/div[2]/div[1]")
    public WebElement attributeactivitycondition;



    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[2]/div[4]/div/div/div[2]/div/div/div[1]/div[1]/div/div[2]/div/a")
    public  WebElement selectLookLikeCustomVariableAttributeScope_DropDownIcon;
    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[2]/div[4]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/div[2]/div/div/div/div/div[2]/div/div/div/a[2]") public WebElement attributecustomvariablecondition;

    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[2]/div[4]/div/div/div[2]/div/div/div[1]/div[1]/div[3]/div/div[2]/div[1]/div/input[2]")
    public WebElement selectLookLikeCustomVariableConditionSet_DropDownIcon;

    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[2]/div[4]/div/div/div[2]/div/div/div" +
            "[1]/div[1]/div[3]/div/div[2]/div[1]/div/div[2]/div[1]")
    public WebElement attributecustomvariableValuecondition;;

    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[2]/div[4]/div/div/div[2]/div/div/div[1]/div[1]/div[4]/div/input[2]")
    public WebElement selectLookLikeCustomVariabledurationConditionSet_DropDownIcon;


    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[2]/div[4]/div/div/div[2]/div/div/div[1]/div[1]/div[4]/div/div[2]/div[2]")
    public WebElement attributecustomvariableValuedurationcondition;;

    @FindBy(id = "custTextVal")
    public  WebElement CustomVariableConditionMoreValue;





    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[5]/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div/div/div[1]/div[1]/div/div[2]/div/a")
    public  WebElement selectLookLikeCustomVariableAttributeScope_DropDownIcon_Exclude;

    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[5]/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div/div/div[1]/div[1]/div/div[2]/div/div/div/div/div[2]/div/div/div/a[2]") public WebElement attributecustomvariablecondition_Exclude;

    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[5]/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div/div/div[1]/div[1]/div[3]/div/div[2]/div[1]/div/input[2]")
    public WebElement selectLookLikeCustomVariableConditionSet_DropDownIcon_Exclude;

    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[5]/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div/div/div[1]/div[1]/div[3]/div/div[2]/div[1]/div/div[2]/div[1]")
    public WebElement attributecustomvariableValuecondition_Exclude;;

    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[5]/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div/div/div[1]/div[1]/div[4]/div/input[2]")
    public WebElement selectLookLikeCustomVariabledurationConditionSet_DropDownIcon_Exclude;


    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[5]/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div/div/div[1]/div[1]/div[4]/div/div[2]/div[3]")
    public WebElement attributecustomvariableValuedurationcondition_Exclude;
    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[5]/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div/div/div" +
            "[1]/div[1]/div[5]")
    public  WebElement CustomVariableConditionMoreValue_Exclude;

    //*[@id="custTextVal"]
    //values are 'THESE_ADV" and 'ALL_ADV'
    public String looklikeAdvertiserScope = "html/body/div[3]/div/div[1]/div[3]/div[3]/div/div[1]/div/div/div[2]/div[1]";

    public String attributecondition = "html/body/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[2]/div[2]/div/div/div[2]/div/div/div[1]/div[1]/div[1]/div[2]/div/div/div/div/div[2]/div/div/div/a[1]";
    //DataPartner List Screen
    @FindBy(xpath = "//input[@class='js-search-box']")
    public WebElement searchBox_DataPartner;
    public String _looklikeName = "//table[@id='modelingtable']//td[contains(@class,'lal-model-name')]/div//a[text()" +
            "='EXTERNALDATA']";
    @FindBy(xpath="//div[contains(@class,'main-head-wrap')]") public WebElement LookLike_Create_Edit_Screen;

    public  String conditionsetlink = "/html/body/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[2]/div[2]/div/div/div[4]/a";

    public WebElement edit_dataPartner_Link;
    @FindBy(xpath = "//a[@class='js-name-edit action-links']")
    public WebElement edit_datapartnerName_Link;

    @FindBy(xpath = "//a[@class='js-commit-name action-links']")
    public WebElement edit_datapartnerName_Ok_Button;
    @FindBy(xpath = "//a[@class='js-cancel-name action-links']")
    public WebElement edit_datapartnerName_Cancel_Button;




}
