package com.mplatform.test.manalytics;

import com.mplatform.framework.base.ScriptBase;
import org.testng.annotations.Test;

public class Manage_Agencies extends ScriptBase{

    @Test(priority = 1)
    public void login() throws Exception{
        reportingSetup("01", "As a user, Navigate to mAnalytics app then login");

        strDTParametersNValues = "InputDataRow=>2";

        mPlatform().maLogin().loginNdVerifyLandingPage();
    }

//    @Test(priority = 2)
//    public void navigateToManageAgencies() throws Exception{
//        reportingSetup("02", "As a user, Navigate to Manage Agencies");
//
//        strDTParametersNValues = "InputDataRow=>2";
//        String[] arrDTParametersNValues = strDTParametersNValues.split("=>");
//
//        mPlatform().maManageAgencies().navigateToManageAgencyPage(Integer.parseInt(arrDTParametersNValues[1]));
//
//    }
//
//    @Test(priority = 3)
//    public void createNewAgencyWithPlatformLoginThenSaveNdExit() throws Exception{
//        reportingSetup("03", "As a user, create an agency with platform login. Save and exit agency changes then verify");
//
//        strDTParametersNValues = "InputDataRow=>2";
//        String[] arrDTParametersNValues = strDTParametersNValues.split("=>");
//        mPlatform().maManageAgencies().createNewAgencyAndSave();
//        mPlatform().maManageAgencies().createPlatformLoginAndSave();
//        mPlatform().maManageAgencies().verifyCreatedPlLoginThenSaveChangesAndVerifyNdExit(Integer.parseInt(arrDTParametersNValues[1]));
//    }
//
//    @Test(priority = 4)
//    public void editAgencyAndAssignExistingPlatformLogin() throws Exception {
//        reportingSetup("04", "As a user, edit agency and assign existing platform login then verify");
//
//        strDTParametersNValues = "InputDataRow=>2";
//        mPlatform().maManageAgencies().validateAgenciesCountAfterCreatingNewAgency();
//        mPlatform().maManageAgencies().searchAgencyThenAssignPlatformLoginAndVerify();
//    }
//
//    @Test(priority = 5)
//    public void saveChangesAndCancelThenSearchAgencyAndVerify() throws Exception {
//        reportingSetup("05", "As a user, save agency changes then cancel. Search agency then verify content");
//
//        strDTParametersNValues = "InputDataRow=>2";
//        mPlatform().maManageAgencies().saveChangesAndCancel();
//        mPlatform().maManageAgencies().searchAgencyAndVerifyAllContect();
//    }




}
