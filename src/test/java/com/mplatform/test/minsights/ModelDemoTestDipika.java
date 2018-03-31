package com.mplatform.test.minsights;

import com.mplatform.framework.base.ScriptBase;
import org.testng.annotations.Test;

public class ModelDemoTestDipika extends ScriptBase {

    /**  @Test(priority = 1)
    public void createNewAdvertiser() throws Exception {

    reportingSetup("01", "UserStroy## MIPUI-1443 ==> As a user, Create/Edit New Advertiser and Activity ");

    strDTParametersNValues = "TestData";

    // Create Advertiser Test//
    mInsights().NewLogin().login();
    String advertiserName = mInsights().CreateAdvertiser().createAdvertiser();
    String activityName = mInsights().CreateActivity().createActivity(advertiserName);

    //Edit Advertiser Test//
    advertiserName = mInsights().EditAdvertiser().editAdvertiser(advertiserName);

    //Edit Activity Test//
    mInsights().CreateActivity().editActivity(advertiserName,activityName);
    }*/


    /*
      Created : VaibhavS
      Date : 11th Aug 2017
      Desc : OKTA User Integration verification
     */

/*
    @Test(priority = 1)
    public void forgotPasswordVerification() throws Exception{
        reportingSetup("01","UserStory ## MITUI-1031/957 ==> Integration of OKTA in ACM Portal using OKTA APIs");
        strDTParametersNValues = "TestData";
        mInsights().NewLogin().forgotPassword();
    }
*/

    /*
      Created : VaibhavS
      Date : 11th Aug 2017
      Desc : Data Partner and Data Source create/edit
     */
    @Test(priority = 2)
    public void createLooklikeModel() throws Exception{
        strDTParametersNValues="TestData";
        reportingSetup("01","UserStory ==> Create Looklike Model");
        /* Create LooklikeModel Test*/
        //******mInsights().NewLogin().loginOnExistingLaunchPage();
        mInsights().NewLogin().login();
        //*****mInsights().NewLogin().accountSelectionFromHeader("zNA Test1");
        String LookLikeModel = mInsights().CreateLookLikeModel().createLooklike();

        reportingSetup("01","UserStory ==> Create Regular Looklike model with specific Advertiser");
        String datasourceName = mInsights().createLiveDataSource().createLiveDataSource(LookLikeModel);

        reportingSetup("02","UserStory ==> Edit Regular Looklike model with specific Advertiser");
        LookLikeModel = mInsights().editDataPartner().editDataPartner(LookLikeModel);

    }




}
