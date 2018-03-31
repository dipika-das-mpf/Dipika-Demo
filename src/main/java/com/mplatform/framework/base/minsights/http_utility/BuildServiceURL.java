package com.mplatform.framework.base.minsights.http_utility;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;

import java.util.HashMap;

/**
 * Created by mohamed.abdulkadar on 6/18/2017.
 */
public class BuildServiceURL {

    public static HttpGet buildGetRequestObject(String hostname, String serviceURL,
                                                HashMap<String,String> serviceurl,HashMap<String,String> parameters)
    throws Exception{

        HttpGet httpRequestVO = null;

        HashMap<String,String> serviceUrlParameterMap = null;
        HashMap<String,String> urlQueryParameterMap = null;
        HashMap<String,String> headerMap = new HashMap<String,String>();

        headerMap.put("Content-type","application/json");

        try {


            if (serviceurl != null && !(serviceurl.isEmpty())) {

                serviceUrlParameterMap = new HashMap<String, String>();
                serviceUrlParameterMap = serviceurl;

            }

            if (parameters != null && !(parameters.isEmpty())) {

                urlQueryParameterMap = new HashMap<String, String>();
                urlQueryParameterMap = parameters;

            }

            //Replace correct hostname in serviceURL
            String strURL = replaceServiceURLParameters(serviceUrlParameterMap,serviceURL,hostname);

            URIBuilder builder = HttpUtility.builtURI(strURL,urlQueryParameterMap);

            httpRequestVO = HttpUtility.buildHttpGetRequestObject(strURL,urlQueryParameterMap,headerMap);


        }catch(Exception e){
            e.printStackTrace();

        }

        return httpRequestVO;

    }

    public static String replaceServiceURLParameters(HashMap<String,String> serviceUrlParameterMap,String strURL,String hostname){



        if((serviceUrlParameterMap != null) && !(serviceUrlParameterMap.isEmpty())){

            if(strURL.contains("$$hostName$$")){
                strURL = strURL.replace("$$hostName$$",hostname);
            }


            if(strURL.contains("$$AccountID$$")){
                strURL = strURL.replace("$$AccountID$$",serviceUrlParameterMap.get("accountkey"));
            }

            if(strURL.contains("$$AdvertiserID$$")){
                strURL = strURL.replace("$$AdvertiserID$$",serviceUrlParameterMap.get("advertiserkey"));
            }

            if(strURL.contains("$$model_key$$")){
                strURL = strURL.replace("$$model_key$$",serviceUrlParameterMap.get("modelkey"));
            }

            if(strURL.contains("$$model_instance_key$$")){
                strURL = strURL.replace("$$model_instance_key$$",serviceUrlParameterMap.get("model_instance_key"));
            }

            if(strURL.contains("$$tagrTagId$$")){
                strURL = strURL.replace("$$tagrTagId$$",serviceUrlParameterMap.get("tagrTagId"));
            }


        }



        return strURL;


    }
}
