package com.mplatform.framework.controller;

import com.mplatform.framework.controller.minsights.*;
import com.mplatform.framework.data.minsights.MI_DataVO;
import org.openqa.selenium.WebDriver;

/**
 * Created by mohamed.abdulkadar on 6/21/2017.
 */
public class MI_ApplicationController {

    public String strParametersNValues = "";
    WebDriver driver;

    public MI_ApplicationController(WebDriver driver) {
        this.driver = driver;
    }

    public MI_Login login = null;
    public MI_Login NewLogin() throws Exception {
        if (login == null) {
            login = new MI_Login(driver);
        }
        login.testDataVO= new MI_DataVO(this.strParametersNValues);
        return login;
    }

    public MI_CommonController commonController = null;
    public MI_CommonController commonController() throws Exception {
        if (commonController == null) {
            commonController = new MI_CommonController(driver);
        }
        login.testDataVO= new MI_DataVO(this.strParametersNValues);
        return commonController;
    }


    public MI_CreateAdvertiserController createAdvertiser = null;
    public MI_CreateAdvertiserController CreateAdvertiser() throws Exception {
        if (createAdvertiser == null) {
            createAdvertiser = new MI_CreateAdvertiserController(driver);
        }
        createAdvertiser.testDataVO= new MI_DataVO(this.strParametersNValues);
        return createAdvertiser;
    }

    public MI_CreateActivityController createActivity = null;
    public MI_CreateActivityController CreateActivity() throws Exception {
        if (createActivity == null) {
            createActivity = new MI_CreateActivityController(driver);
        }
        createActivity.testDataVO= new MI_DataVO(this.strParametersNValues);
        return createActivity;
    }

    public MI_EditAdvertiserController editAdvertiser = null;
    public MI_EditAdvertiserController EditAdvertiser() throws Exception {
        if (editAdvertiser == null) {
            editAdvertiser = new MI_EditAdvertiserController(driver);
        }
        editAdvertiser.testDataVO= new MI_DataVO(this.strParametersNValues);
        return editAdvertiser;
    }


    public MI_CreateCampaignController createCampaign = null;
    public MI_CreateCampaignController CreateCampaign() throws Exception {
        if (createCampaign == null) {
            createCampaign = new MI_CreateCampaignController(driver);
        }
        createCampaign.tetDataVO=new MI_DataVO(this.strParametersNValues);
        return createCampaign;
    }

    /**
    * Created: Dipika
    * Date: 3rd Jan 2018
    * Desc: Create ILAL model
    */
    public MI_CreateLookLikeModelController createLookLike =null;
    public MI_CreateLookLikeModelController CreateLookLikeModel() throws Exception{
     if  (createLookLike ==null) {
         createLookLike = new MI_CreateLookLikeModelController(driver);
     }
     createLookLike.testDataVO = new MI_DataVO(this.strParametersNValues);
     return createLookLike;
    }


    /**
     * Created: Dipika
     * Date: 26 Jan 2018
     * Desc: Create DataPartner model
     */
    public MI_CreateDataPartnerModelController createDataPartnerModel =null;
    public MI_CreateDataPartnerModelController CreateDataPartnerModel() throws Exception{
        if  (createDataPartnerModel ==null) {
            createDataPartnerModel = new MI_CreateDataPartnerModelController(driver);
        }
        createDataPartnerModel.testDataVO = new MI_DataVO(this.strParametersNValues);
        return createDataPartnerModel;
    }



    /**
     * Created: Dipika
     * Date: 3rd Jan 2018
     * Desc: Create  ILAL
     */
    public MI_CreateCustomSegmentModelController createCustomSegmentModel =null;
    public MI_CreateCustomSegmentModelController CreateCustomSegment() throws Exception{
        if  (createCustomSegmentModel ==null) {
            createCustomSegmentModel = new MI_CreateCustomSegmentModelController(driver);
        }
        createCustomSegmentModel.testDataVO = new MI_DataVO(this.strParametersNValues);
        return createCustomSegmentModel;
    }






    /**
     * Created : VaibhavS
     * Date : 11th Aug 2018
     * Desc : Create Data partner Controller
     */
    public MI_CreateDataPartnerController createDataPartner = null;
    public MI_CreateDataPartnerController CreateDataPartner() throws Exception{
        if (createDataPartner == null){
            createDataPartner = new MI_CreateDataPartnerController(driver);
        }
        createDataPartner.testDataVO =  new MI_DataVO(this.strParametersNValues);
        return createDataPartner;
    }

    /**
     * Created : VaibhavS
     * Date : 11th Aug 2018
     * Desc : Create Live Data Source Controller
     */
    public MI_CreateLiveDataSourceController createLiveDataSource = null;
    public MI_CreateLiveDataSourceController createLiveDataSource() throws Exception {
        if (createLiveDataSource == null) {
            createLiveDataSource = new MI_CreateLiveDataSourceController(driver);
        }
        createLiveDataSource.testDataVO= new MI_DataVO(this.strParametersNValues);
        return createLiveDataSource;
    }

    /**
     * Created : VaibhavS
     * Date : 17th Aug 2018
     * Desc : Edit Data partner Controller
     */
    public MI_EditDataPartnerController editDataPartner = null;
    public MI_EditDataPartnerController editDataPartner() throws Exception{
        if (editDataPartner == null){
            editDataPartner = new MI_EditDataPartnerController(driver);
        }
        editDataPartner.testDataVO =  new MI_DataVO(this.strParametersNValues);
        return editDataPartner;
    }

    /**
     * Created : VaibhavS
     * Date : 21st Aug 2018
     * Desc : Edit Data Source Controller
     */
    public MI_EditLiveDataSourceController editLiveDataSource = null;
    public MI_EditLiveDataSourceController editLiveDataSource() throws Exception{
        if (editLiveDataSource == null){
            editLiveDataSource = new MI_EditLiveDataSourceController(driver);
        }
        editLiveDataSource.testDataVO =  new MI_DataVO(this.strParametersNValues);
        return editLiveDataSource;
    }
}
