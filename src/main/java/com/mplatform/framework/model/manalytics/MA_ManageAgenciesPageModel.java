package com.mplatform.framework.model.manalytics;

import com.mplatform.framework.model.ModelBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MA_ManageAgenciesPageModel extends ModelBase {

    public MA_ManageAgenciesPageModel (WebDriver driver) {
        super(driver);
    }


    //===================================Static Objects Identifiers===========================

    @FindBy(xpath=".//*[@id='nav-view']//i[@class='content icon large']") public WebElement hamburgerMenu;
    @FindBy (xpath=".//*[@id='new-agency']") public WebElement createAgencyBtn;

    public String manage_agencies = ".//*[@id='nav-view']//a[@title='Agencies']";
    public String existingAgencyCount = ".//*[@id='content']//strong[@class='agency-count']";
    public String agencyNameInput = ".//*[@id='content']//input[@name='agencyname']";
    public String saveAgency = ".//*[@id='apply-btn']";
    public String saveAgencyAndExit = ".//*[@id='save-btn']";
    public String cancelAgency = ".//*[@id='cancel-btn']";
    public String createPlatformLoginBtn = ".//*[@id='add-new-platform']";
    public String platformLoginInput = ".//*[@id='new-platform']//input[@name='platformname']";
    public String platformLoginTypeDropdown = ".//*[@id='platformtype']/i";
    public String platformLoginUserName = ".//*[@id='new-platform']//input[@name='username']";
    public String platformLoginPassword = ".//*[@id='new-platform']//input[@name='password']";
    public String externalIdLld = ".//*[@id='new-platform']//input[@name='externalidlld']";
    public String savePlatformLoginBtn = ".//*[@id='new-platform']//*[@type='submit']";
    public String cancelPlatformLoginBtn = ".//*[@id='new-platform']//*[contains(text(),'Cancel')]";

    public String searchAgencyInputBox = ".//*[@id='agency-filter']";
    public String advertisersTab = ".//*[@id='content']//a[@data-tab='advertisers']";
    public String assignPlatformLoginBtn = ".//*[@id='assign-platform']";
    public String searchPlatformLoginInput = ".//*[@id='platform-search']//input[@name='search-platform-login']";
    public String assignBtn = ".//*[@id='platform-search']//a[contains(text(),'assign')]";



//    public WebElement cancel(){
//        return driver.findElement(By.xpath("//*[@id='reset-password-form']//button[contains(text(),'Cancel')]"));
//    }
//    public WebElement login(){
//     return driver.findElement(By.id("login-btn"));
//    }


    //===================================Dynamic Objects Identifiers===========================


    public String _reportName = ".//span[contains(text(),'EXTERNALDATA1')]//a[contains(text(),'EXTERNALDATA2')]";
    public String _platformLoginType = ".//*[@id='platformtype']//*[contains(text(),'EXTERNALDATA')][1]";
    public String _platformLoginId = ".//*[contains(text(),'EXTERNALDATA')]/..//td[1]";
    public String _platformLoginName = ".//*[contains(text(),'EXTERNALDATA')]/..//td[2]";
    public String _platformLoginTypeName = ".//*[contains(text(),'EXTERNALDATA')]/..//td[3]";
    public String _platformLoginUsername = ".//*[contains(text(),'EXTERNALDATA')]/..//td[4]";
    public String _platformLoginCode = ".//*[contains(text(),'EXTERNALDATA')]/..//td[5]";
    public String _platformLoginZeusOrgKey = ".//*[contains(text(),'EXTERNALDATA')]/..//td[6]//i";
    public String _platformLoginDateCreated = ".//*[contains(text(),'EXTERNALDATA')]/..//td[7]";
    public String _platformLoginDateCreated_Edit = ".//*[contains(text(),'EXTERNALDATA')]/..//td[7]//i";
    public String _platformLoginEdit = ".//*[contains(text(),'EXTERNALDATA')]/..//td[8]//i";
    public String _platformLoginTag = ".//*[contains(text(),'EXTERNALDATA')]/..//td[9]//i";
    public String _pickAssignedPlatformLogin = ".//*[@id='platform-search']//*[contains(text(),'EXTERNALDATA')]";

    public String _editSearchedAgency = ".//*[contains(text(),'EXTERNALDATA')]/../../..//*[@class='write icon']";
}
