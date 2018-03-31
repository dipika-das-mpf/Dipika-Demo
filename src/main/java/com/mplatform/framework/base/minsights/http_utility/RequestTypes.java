package com.mplatform.framework.base.minsights.http_utility;

/**
 * Created by mohamed.abdulkadar on 6/18/2017.
 */
public enum RequestTypes {
    getTagId;

    public String getServiceURL() throws Exception{


        switch (this){
            case getTagId:
                return "http://$$hostName$$:8080/tagr_api_trb/turbine/tags/$$tagrTagId$$";
            default:
                throw new AssertionError("Not Supported Service Type : "+this);
        }


    }
}
