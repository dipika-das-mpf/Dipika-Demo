package com.mplatform.test.minsights;

import com.mplatform.framework.base.ScriptBase;
import org.testng.annotations.Test;

import java.util.HashMap;



public class FirstDemoTest extends ScriptBase {

   @Test(priority = 1)
       public void createNewAdvertiser() throws Exception {


    HashMap <String, String> testDataDetails = new HashMap <String, String>();

    strDTParametersNValues = "TestData";

    mInsights().NewLogin().login();

    reportingSetup("01", "UserStroy## MIPUI-3247 ==> As a user, Create New Advertiser, Activity and Campaign ");

    // Create Advertiser Test//

    String advertiserRegName = mInsights().CreateAdvertiser().createAdvertiser();
    String activityRegName = mInsights().CreateActivity().createActivity(advertiserRegName);

    reportingSetup("02", "UserStroy## MIPUI-3247 ==> As a user, Create New Data Partner, Live Data Source ");
       String datapartnerName = mInsights().CreateDataPartner().createDataPartner();
       String datasourceName = mInsights().createLiveDataSource().createLiveDataSource(datapartnerName);

    /* reportingSetup("02", "UserStroy## MIPUI-MIPUI-1547 ==> As a user, Create/Edit Cross Market Advertiser, Activity and Campaign ");

        // Create Advertiser Test//
        mInsights().commonController().printMessage("Create New Cross Market Advertiser");
        testDataDetails = mInsights().CreateAdvertiser().createCrossMarketAdvertiser();

        mInsights().commonController().printMessage("Create New Regular Activity for Cross Market Advertiser");
        String activityName = mInsights().CreateActivity().createActivity(testDataDetails.get("CrossMarketAdvertiserName"));

        //Store CrossMarket Activity created in Default Account in HashMap
        testDataDetails.put("crossMarketAdvertiserActivity",activityName);

        mInsights().commonController().printMessage("Verify Cross Market Advertiser inforation in Additional Market Account");
        mInsights().CreateAdvertiser().verifyCrossMarketAdvertiser(testDataDetails.get("AdditionalMarket1"),
                testDataDetails);

        mInsights().commonController().printMessage("Create New Regular Activity for Cross Market Advertiser in Additional Market Account ");
        String crossMarketActivityName2 = mInsights().CreateActivity().createCrossMarketActivity(testDataDetails.get("AdditionalMarket1"),
                testDataDetails.get("CrossMarketAdvertiserName"));

        //Store CrossMarket Activity created in AdditionalMarket Account in HashMap
        testDataDetails.put("crossMarketAdvertiserActivity2",crossMarketActivityName2);
        mInsights().commonController().printMessage("Verify activity created for Cross Market Advertiser in Additional Market Account in Default Account");
        mInsights().CreateAdvertiser().verifyCrossMarketAdvertiser(testDataDetails.get("DefaultAccount"),
                testDataDetails);

        //Create New Campaign in Default Account for Cross Advertiser
        mInsights().commonController().printMessage("Create New Campaign in Cross Market ADvertiser in Default Account");
        mInsights().commonController().refreshScreen();
        mInsights().commonController().selectAccount("Select Default Market Account to create Campaign",
                testDataDetails.get("DefaultAccount"));
        mInsights().commonController().goToAdvertiserListScreen();
        mInsights().commonController().selectAdvertiser(testDataDetails.get("CrossMarketAdvertiserName"));
        String  campaignName = mInsights().CreateCampaign().createCampaign(testDataDetails.get("CrossMarketAdvertiserName"));

        //Edit Campaign which is created in Default Account in Additional Market account of Cross Advertiser
        mInsights().commonController().printMessage("Edit Campaign created in Default Account in Additional Market Account");
        mInsights().commonController().refreshScreen();
        mInsights().commonController().selectAccount("Select Additional Market Account to Edit Campaign",
                testDataDetails.get("AdditionalMarket1"));
        mInsights().commonController().goToAdvertiserListScreen();
        mInsights().commonController().selectAdvertiser(testDataDetails.get("CrossMarketAdvertiserName"));
        mInsights().commonController().selectCampaignTab();
        mInsights().CreateCampaign().editCampaign(testDataDetails.get("CrossMarketAdvertiserName"),
                campaignName);

        //Create New Campaign  in Additional Market account for Cross Advertiser
        mInsights().commonController().printMessage("Create New Campaign in Cross Market ADvertiser in Additional Account");
        mInsights().commonController().refreshScreen();
        mInsights().commonController().selectAccount("Select Additional Market Account to create Campaign",
                testDataDetails.get("AdditionalMarket1"));
        mInsights().commonController().goToAdvertiserListScreen();
        mInsights().commonController().selectAdvertiser(testDataDetails.get("CrossMarketAdvertiserName"));
        String campaignName2 = mInsights().CreateCampaign().createCampaign(testDataDetails.get("CrossMarketAdvertiserName"));

        //Edit Campaign which is created in Additional Market Account in Default Market account for Cross Advertiser
        mInsights().commonController().printMessage("Edit Campaign created in Additional Account in Default Market Account");
        mInsights().commonController().refreshScreen();
        mInsights().commonController().selectAccount("Select Default Market Account to Edit Campaign , which is created in Additonal Market",
                testDataDetails.get("DefaultAccount"));
        mInsights().commonController().goToAdvertiserListScreen();
        mInsights().commonController().selectAdvertiser(testDataDetails.get("CrossMarketAdvertiserName"));
        mInsights().commonController().selectCampaignTab();
        mInsights().CreateCampaign().editCampaign(testDataDetails.get("CrossMarketAdvertiserName"),
                campaignName2);
    }
*/

    /*
      Created : VaibhavS
      Date : 11th Aug 2017
      Desc : OKTA User Integration verification


/*
    @Test(priority = 1)
    public void forgotPasswordVerification() throws Exception{
        reportingSetup("01","UserStory ## MITUI-1031/957 ==> Integration of OKTA in ACM Portal using OKTA APIs");
        strDTParametersNValues = "TestData";
        mInsights().NewLogin().forgotPassword();
    }
*/

    /*
      Created : Dipika
      Date : 3rd Jan 2018
      Desc : ILAL create/edit
     */
  /* @Test(priority = 2)
    public void createLooklikeModel() throws Exception{
        strDTParametersNValues="TestData";
        reportingSetup("03","UserStory ==> Create Looklike Model");
        *//* Create LooklikeModel Test*//*
        //******mInsights().NewLogin().loginOnExistingLaunchPage();
        mInsights().NewLogin().login();
        //*****mInsights().NewLogin().accountSelectionFromHeader("zNA Test1");
        String LookLikeModel = mInsights().CreateLookLikeModel().createLooklike();

    }*/

    //   @Test(priority = 3)
//    public void createDataPartnerModel() throws Exception{
//        strDTParametersNValues="TestData";
    //       reportingSetup("01","UserStory ==> Create DataPartner Model");
//        /* Create DataPartnerModel Test*/
    //******mInsights().NewLogin().loginOnExistingLaunchPage();
//        mInsights().NewLogin().login();
//        //*****mInsights().NewLogin().accountSelectionFromHeader("zNA Test1");
//        String DataPartnerModel = mInsights().CreateDataPartnerModel().createDataPartnerModel();
//    }


    //  @Test(priority = 4)
    //  public void createCustomSegmentModel() throws Exception{
    //      strDTParametersNValues="TestData";
    //     reportingSetup("01","UserStory ==> Create Custom Audience Model");
//
    //      mInsights().NewLogin().login();
    //    //*****mInsights().NewLogin().accountSelectionFromHeader("zNA Test1");
    //String CustomSegmentModel = mInsights().CreateCustomSegmentModel().createCustomSegmentModel();


//    }







    /*
     Created : Dipika
     Date : 181th Oct 2017
     Desc : Data Partner and Data Source create/edit
    */


    /*@Test(priority = 4)
    public void createDataPartner() throws Exception{
        strDTParametersNValues="TestData";
        reportingSetup("01","UserStory ==> Create Data Partner");
        *//* Create DataPartner Test*//*
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
    */

   }

}
