package com.mplatform.framework.controller.manalytics;

import com.mplatform.framework.base.CommonFunctions;
import com.mplatform.framework.base.Utils;
import com.mplatform.framework.data.manalytics.MA_LoginPageData;
import com.mplatform.framework.data.manalytics.MA_ManageAgenciesPageData;
import com.mplatform.framework.model.manalytics.MA_ManageAgenciesPageModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import java.util.HashMap;

public class MA_ManageAgenciesPageController extends Utils {

    MA_ManageAgenciesPageModel manageAgenciesPage = null;
    public MA_ManageAgenciesPageData manageAgenciesData = null;
    public MA_LoginPageData maLoginData = null;
    CommonFunctions commonFunctions = new CommonFunctions();
    HashMap<String, String> hmOutputData = new HashMap<String, String>();

    public MA_ManageAgenciesPageController(WebDriver driver) throws Exception {
        super(driver);
        manageAgenciesPage = PageFactory.initElements(driver, MA_ManageAgenciesPageModel.class);
    }

    public MA_ManageAgenciesPageController navigateToManageAgencyPage(int rowNum) throws Exception
    {
        mouseOverNClick(manageAgenciesPage.hamburgerMenu,"Hamburger Navigation Menu");
        clickObject(manageAgenciesPage.manage_agencies, "Manage Agencies");
        String agencyCount = getTextFromElement(manageAgenciesPage.existingAgencyCount, "Get Existing Agencies Count");
        System.out.println(""+agencyCount);
        hmOutputData.put("CountOfExistingAgencies", ""+agencyCount);
        commonFunctions.storeHashMapDataInExcel(hmOutputData, "Agencies", false, rowNum);
        objHTMLFunction.ReportPassFail("Current Agencies Count is =>"+agencyCount, "true", "true");

        return this;
    }

    public MA_ManageAgenciesPageController createNewAgencyAndSave() throws Exception
    {
        clickObject(manageAgenciesPage.createAgencyBtn, "Create Agency Btn");
        typeValue(manageAgenciesPage.agencyNameInput, "Agency Name", manageAgenciesData.strDTAgencyName+sessionTimeStamp);
        return this;
    }

    public MA_ManageAgenciesPageController createPlatformLoginAndSave() throws Exception
    {
        delayFor(30);
        clickObject(manageAgenciesPage.createPlatformLoginBtn, "Create Platform Login Btn");
        typeValue(manageAgenciesPage.platformLoginInput, "Platform Login Name", manageAgenciesData.strDTPlatformLoginName+sessionTimeStamp);
        delayFor(5);
        customClickReport(manageAgenciesPage.platformLoginTypeDropdown, "Platform Login Type List", 2);
        clickObject(manageAgenciesPage._platformLoginType+"--"+manageAgenciesData.strDTPlatformLoginType,
                "Selected Platform Login Type is => "+manageAgenciesData.strDTPlatformLoginType);
        typeValue(manageAgenciesPage.platformLoginUserName, "Platform Login Username", maLoginData.strDTUserName);
        typeValue(manageAgenciesPage.platformLoginPassword, "Platform Login Password", maLoginData.strDTPassword);
        clickObject(manageAgenciesPage.savePlatformLoginBtn, "Save Platform Login");

        return this;
    }

    public MA_ManageAgenciesPageController verifyCreatedPlLoginThenSaveChangesAndVerifyNdExit(int rowNum) throws Exception
    {

        if(elementExistance(manageAgenciesPage._platformLoginName+"--"+manageAgenciesData.strDTPlatformLoginName+sessionTimeStamp,
                    "Newly Created Platform Login", 2)){
            verifyTextInElement(manageAgenciesPage._platformLoginId+"--"+manageAgenciesData.strDTPlatformLoginName+sessionTimeStamp,
                    "Platform Login Newly Created ID", "New");
            verifyTextInElement(manageAgenciesPage._platformLoginTypeName+"--"+manageAgenciesData.strDTPlatformLoginName+sessionTimeStamp,
                    "Platform Login Newly Created Type", manageAgenciesData.strDTPlatformLoginType);
            verifyTextInElement(manageAgenciesPage._platformLoginUsername+"--"+manageAgenciesData.strDTPlatformLoginName+sessionTimeStamp,
                    "Platform Login Newly Created ID", maLoginData.strDTUserName);
            verifyAttributeInElement(manageAgenciesPage._platformLoginDateCreated_Edit+"--"+manageAgenciesData.strDTPlatformLoginName+sessionTimeStamp,
                    "Date Created", "class", "remove icon");
            verifyAttributeInElement(manageAgenciesPage._platformLoginZeusOrgKey+"--"+manageAgenciesData.strDTPlatformLoginName+sessionTimeStamp,
                    "UnLink", "class", "unlink icon");
            verifyAttributeInElement(manageAgenciesPage._platformLoginDateCreated_Edit+"--"+manageAgenciesData.strDTPlatformLoginName+sessionTimeStamp,
                    "Remove", "class", "remove icon");
            delayFor(5);
            clickObject(manageAgenciesPage.saveAgency, "Save Agency Changes");

            String platformLoginId = getTextFromElement(manageAgenciesPage._platformLoginId+"--"+manageAgenciesData.strDTPlatformLoginName+sessionTimeStamp,
                    "Platform Login ID ID");
            hmOutputData.put("PlatformLoginId", ""+platformLoginId);
            commonFunctions.storeHashMapDataInExcel(hmOutputData, "Agencies", false, rowNum);

            verifyTextInElement(manageAgenciesPage._platformLoginTypeName+"--"+manageAgenciesData.strDTPlatformLoginName+sessionTimeStamp,
                    "Platform Login Newly Created Type", manageAgenciesData.strDTPlatformLoginType);
            verifyTextInElement(manageAgenciesPage._platformLoginUsername+"--"+manageAgenciesData.strDTPlatformLoginName+sessionTimeStamp,
                    "Platform Login Newly Created ID", maLoginData.strDTUserName);
//            verifyTextInElement(manageAgenciesPage._platformLoginDateCreated+"--"+manageAgenciesData.strDTPlatformLoginName+sessionTimeStamp,
//                    "Date Created", todayDate());
            verifyAttributeInElement(manageAgenciesPage._platformLoginEdit+"--"+manageAgenciesData.strDTPlatformLoginName+sessionTimeStamp,
                    "Edit", "class", "write icon");
            verifyAttributeInElement(manageAgenciesPage._platformLoginTag+"--"+manageAgenciesData.strDTPlatformLoginName+sessionTimeStamp,
                    "Tag", "class", "tags icon");
            clickObject(manageAgenciesPage.saveAgencyAndExit, "Save And Exit Agency");
        }else {
            objHTMLFunction.ReportPassFail("The Newly Created Platform Login Is NOT Displayed In The Agency Page", "True", "False");
        }
        return this;
    }

    public MA_ManageAgenciesPageController validateAgenciesCountAfterCreatingNewAgency() throws Exception
    {
        String currentAgencyCount = getTextFromElement(manageAgenciesPage.existingAgencyCount, "Current Agency Count");
        int intCurrentAgencyCount = Integer.parseInt(currentAgencyCount);
        int intPreviousAgencyCount = Integer.parseInt(manageAgenciesData.strDTCountOfExistingAgencies) + 1 ;
        if( intCurrentAgencyCount == intPreviousAgencyCount){
            objHTMLFunction.ReportPassFail("Count of current agencies has increased by one after creating new agency", "True", "True");
        }else if((Integer.parseInt(manageAgenciesPage.existingAgencyCount)) > (Integer.parseInt(manageAgenciesData.strDTCountOfExistingAgencies))+1){
            objHTMLFunction.ReportPassFail("Count of current agencies has increased after creating new agency", "True", "True");
        }else{
            objHTMLFunction.ReportPassFail("Count of current agencies has NOT been increased after creating new agency", "True", "False");
        }

        return this;
    }

    public MA_ManageAgenciesPageController searchAgencyThenAssignPlatformLoginAndVerify() throws Exception
    {
        typeValue(manageAgenciesPage.searchAgencyInputBox, "Search Created Agency", manageAgenciesData.strDTAgencyName+sessionTimeStamp);
        if(elementExistance(manageAgenciesPage._editSearchedAgency+"--"+manageAgenciesData.strDTAgencyName,"Searched Agency", 1)){
            clickObject(manageAgenciesPage._editSearchedAgency+"--"+manageAgenciesData.strDTAgencyName, "Edit Agency => "+manageAgenciesData.strDTAgencyName+sessionTimeStamp);
            checkObjectExists(manageAgenciesPage.advertisersTab, "Advertiser Tab");
            refreshPage();
            clickObject(manageAgenciesPage.assignPlatformLoginBtn, "Assign Platform Login Tab");
            typeValue(manageAgenciesPage.searchPlatformLoginInput, "Search To Assign Platform Login", manageAgenciesData.strDTAssignPlatformLogin);
            delayFor(2);
            clickObject(manageAgenciesPage._pickAssignedPlatformLogin+"--"+manageAgenciesData.strDTAssignPlatformLogin, "Select Platform Login To Assign");
            clickObject(manageAgenciesPage.assignBtn, "Assign Searched Platform Login");
            verifyAttributeInElement(manageAgenciesPage._platformLoginDateCreated_Edit+"--"+manageAgenciesData.strDTAssignPlatformLogin,
                    "Edit", "class", "write icon");
            verifyAttributeInElement(manageAgenciesPage._platformLoginEdit+"--"+manageAgenciesData.strDTAssignPlatformLogin,
                    "UnLink", "class", "unlink icon");
            verifyAttributeInElement(manageAgenciesPage._platformLoginTag+"--"+manageAgenciesData.strDTAssignPlatformLogin,
                    "Remove", "class","remove icon");
        }else{
            objHTMLFunction.ReportPassFail("The Newly Created Agency NOT Displayed In Agencies Page", "True", "False");
        }
        return this;
    }

    public MA_ManageAgenciesPageController saveChangesAndCancel() throws Exception {
        clickObject(manageAgenciesPage.saveAgency, "Save Agency");
        delayFor(20);
        customClickReport(manageAgenciesPage.cancelAgency, "CancelButton",3);
        return this;
    }

    public MA_ManageAgenciesPageController searchAgencyAndVerifyAllContect() throws Exception
    {
        typeValue(manageAgenciesPage.searchAgencyInputBox, "Search Created Agency", manageAgenciesData.strDTAgencyName+sessionTimeStamp);
        clickObject(manageAgenciesPage._editSearchedAgency+"--"+manageAgenciesData.strDTAgencyName,
                "Edit Agency => "+manageAgenciesData.strDTAgencyName+sessionTimeStamp);
//        verifyAttributeInElement(manageAgenciesPage._platformLoginDateCreated_Edit+"--"+manageAgenciesData.strDTAssignPlatformLogin,
//                "Edit", "class", todayDate());
        verifyAttributeInElement(manageAgenciesPage._platformLoginEdit+"--"+manageAgenciesData.strDTAssignPlatformLogin,
                "Edit", "class", "write icon");
        verifyAttributeInElement(manageAgenciesPage._platformLoginTag+"--"+manageAgenciesData.strDTAssignPlatformLogin,
                "Tag", "class","tags icon");
        return this;
    }
}
