package com.mplatform.framework.model.minsights;

import com.mplatform.framework.model.ModelBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MI_DataPartnerModelPage extends MI_Audience_Menu  {

    public MI_DataPartnerModelPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div/div[contains(@class,'menu')]/a[@id='menuDataPartnerModel']")
    public WebElement DataPartnerModelMenu;

    @FindBy(xpath = "//button[@class=\"js-model-create ui button\" ]")
    public WebElement CreateDataPartnerModel;


    /**
     * Create Mi_DataPartnerPage Modal objects
     */


    @FindBy(id = "modelName")
    public WebElement datapartnereModelName_TextField;

    @FindBy(xpath = "html/body/div[3]/div/div[1]/div[3]/div[3]/div/div[1]/div/div/input[2]") public WebElement selectLookLikeAdvertiserScope_DropDownIcon;


   @FindBy(xpath = "/html/body/div[3]/div/div[1]/div[4]/div[3]/div/div[2]/div[2]/div/div[2]/div[175]")
   public WebElement selectdatapartnerAdvertiser;

    @FindBy(id= "advertiserSelect")
    public  WebElement datapartnerAdvertiserScope;

    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[3]/div[2]/div/div[1]/div[2]/div/div/div[1]/div[1]/div[2]/div/div[2]")
    public  WebElement datapartnerdropdownCondition;

    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[3]/div[2]/div/div[1]/div[2]/div/div/div[1" +
        "]/div[1]/div[2]/div/input[2]")

    public WebElement selectDataPartneCondition_DropDownIcon;

    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[3]/div[2]/div/div[1]/div[2]/div/div" +
            "/div[1]/div[1]/div[2]/div/div[2]/div[141]")

    public WebElement selectDataPartneCondition_DropDown;

    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[3]/div[2]/div/div[1]/div[2]/div/div/div[1]/div[1]/div[2]/div/div[2]/div[139]")

    public WebElement selectDataPartneCondition2_DropDown;

    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[3]/div[2]/div/div[1]/div[2]/div/div/div[1]/div[1]/div[3]/div/input[2]")
    public WebElement selectDataSourceCondition_DropDownIcon;

    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[3]/div[2]/div/div[1]/div[2]/div/div/div[1]/div[1]/div[3]/div/div[2]/div[1]")
    public WebElement selectDataSourceCondition_DropDown;

    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[3]/div[2]/div/div[1]/div[2]/div/div/div[1]/div[1]/div[3]/div/div[2]/div[2]")
    public WebElement selectDataSourceCondition2_DropDown;


    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[3]/div[2]/div/div[1]/div[2]/div/div/div[1]/div[1]/div[4]/div/input[2]")
    public WebElement Attributeselecttype_icon;

    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[3]/div[2]/div/div[1]/div[2]/div/div/div[1]/div[1]/div[4]/div/div[2]/div[1]")
    public WebElement Attributeselecttype_dropdown;

    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[3]/div[2]/div/div[1]/div[2]/div/div/div[1]/div[1]/div[5]/div/input[2]")
    public WebElement Selectattribute_icon;

    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[3]/div[2]/div/div[1]/div[2]/div/div/div[1]/div[1]/div[5]/div/div[2]/div[1]")
    public WebElement Selectattribute_dropdown;



    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[3]/div[2]/div/div[1]/div[2]/div/div/div[1]/div[1]/div[6]/div/input[2]")
    public WebElement Attributesinternaltype_icon;

    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[3]/div[2]/div/div[1]/div[2]/div/div/div[1]/div[1]/div[6]/div/div[2]/div[1]")
     public WebElement Attributesinternaltype_dropdown;

    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[3]/div[2]/div/div[1]/div[2]/div/div/div[1]/div[1]/div[8]/div/div[1]")
    public WebElement Attributesinternalvalue_icon;

    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[3]/div[2]/div/div[1]/div[2]/div/div/div[1]/div[1]/div[8]/div/div[2]/div[2]")
    public WebElement Attributesinternalvalue_dropdown;


 //click on inser condition set to add multiple condition

  @FindBy(xpath = "//div/a[contains(@class,'subset-links')]")
    public WebElement Inserconditionset_link;
///html/body/div[3]/div/div[2]/div/div[3]/div[2]/div/div/div[3]/div[2]/div/div[3]/a

    @FindBy(id = "activateSegment")
    public WebElement datapartnersave_button;


    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[5]/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div/div/div[1]/div[1]/div[2]/div/input[2]")
    public WebElement Datapartnercondition_exernalicon;


    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[5]/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div/div/div[1]/div[1]/div[2]/div/div[2]/div[1]")
    public WebElement Datapartnercondition_exernal;


    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[5]/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div/div/div[1]/div[1]/div[3]/div/input[2]")
    public WebElement Datasourcecondition_exernalicon;


    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[5]/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div/div/div[1]/div[1]/div[3]/div/div[2]/div[3]")
    public WebElement Datasourcecondition_exernal;

    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[5]/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div/div/div[1]/div[1]/div[5]/div/input[2]")
    public WebElement Operatorcondition_exernalicon;

    @FindBy(xpath = "/html/body/div[3]/div/div[2]/div/div[5]/div[2]/div/div/div[2]/div[2]/div/div[1]/div[2]/div/div/div[1]/div[1]/div[5]/div/div[2]/div[3]")
    public WebElement Operatorcondition_exernal;




    //DataPartner List Screen
    @FindBy(xpath = "//input[@class='js-search-box']")
    public WebElement searchBox_DataPartner;

    public String _datapartnerName = "//table[@id='modelingtable']//tbody/tr/td[contains(@class,'')]/div/a[text()='EXTERNALDATA']";
    @FindBy(xpath="//div/h1[contains(@class,'main-head-wrap')]") public WebElement DataPartner_Create_Edit_Screen;

    @FindBy(xpath = "/html/body/div[3]/div/div[1]/div[4]/div[3]/div/div[1]/div")
    public WebElement datapartnerAdvertiserTypeScope;


    @FindBy(xpath = "/html/body/div[3]/div/div[1]/div[4]/div[3]/div/div[1]/div/div[2]/div[1]")
    public WebElement selectdatapartnerAlldvertiser;


    @FindBy(xpath = "/html/body/div[3]/div/i")
    public WebElement close_icon;

}
