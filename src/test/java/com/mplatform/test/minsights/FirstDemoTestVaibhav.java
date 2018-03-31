package com.mplatform.test.minsights;

import com.mplatform.framework.base.ScriptBase;
import org.testng.annotations.Test;

/**
 * Created by mohamed.abdulkadar on 6/15/2017.
 */
public class FirstDemoTestVaibhav extends ScriptBase {

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
    public void createDataPartner() throws Exception{
        strDTParametersNValues="TestData";
        reportingSetup("01","UserStory ==> Create Data Partner");
        /* Create DataPartner Test*/
        //******mInsights().NewLogin().loginOnExistingLaunchPage();
        mInsights().NewLogin().login();
        //*****mInsights().NewLogin().accountSelectionFromHeader("zNA Test1");
        String datapartnerName = mInsights().CreateDataPartner().createDataPartner();

        reportingSetup("01","UserStory ==> Create Data Source under the above created Partner");
        String datasourceName = mInsights().createLiveDataSource().createLiveDataSource(datapartnerName);

        reportingSetup("02","UserStory ==> Edit Data Partner");
        datapartnerName = mInsights().editDataPartner().editDataPartner(datapartnerName);

        reportingSetup("03","UserStory ==> Edit Data Source");
        datasourceName= mInsights().editLiveDataSource().editLiveDataSource(datapartnerName,datasourceName);

         // Here i am doing currently changes for the share data source and advertiser scope data partner
         datapartnerName = mInsights().CreateDataPartner().createDataPartnerAdvertiserScope(datasourceName);
         datasourceName= mInsights().createLiveDataSource().createShareWithLiveDataSource(datapartnerName);
    }



}
