package com.mplatform.framework.base.minsights.http_utility;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Created by mohamed.abdulkadar on 6/18/2017.
 */
public class HttpUtility {

    public static HashMap<String,Object> sendRequest(String domaintStr, LinkedHashMap<String,String> requestHeaderMap,
                                                     LinkedHashMap<String,String> requestParameterMap){

        URIBuilder builder = null;
        HttpResponse httpResponse = null;
        HashMap<String,Object> httpResponseMap = new HashMap<String,Object>();


        builder = builtURI(domaintStr,requestParameterMap);
        httpResponse = httpGet(builder,requestHeaderMap);
        httpResponseMap = parseHttpResponse(httpResponse);

        httpResponseMap.put("requestURL", builder.toString());

        return httpResponseMap;

    }



    public static URIBuilder builtURI(String domainStr,HashMap<String,String> requestParameterMap){

        URIBuilder builder = null;
        List<NameValuePair> queryParam = null;


        try {

            builder = new URIBuilder(domainStr);

            if(requestParameterMap != null) {
                queryParam = construct_uri_parameters(requestParameterMap);
                builder.setParameters(queryParam);
            }
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return builder;


    }

    public static URIBuilder builtURI(String domainStr){

        URIBuilder builder = null;
        List<NameValuePair> queryParam = null;


        try {

            builder = new URIBuilder(domainStr);


        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return builder;


    }

    public static HttpResponse httpGet(URIBuilder builder,LinkedHashMap<String,String> headerMap){

        org.apache.http.client.HttpClient Httpclient = null;
        HttpGet HttpGetrequest = null;
        HttpResponse Httpresponse = null;

        try {
            Httpclient = HttpClientBuilder.create().build();
            HttpGetrequest = new HttpGet(builder.build());

            for(String header:headerMap.keySet()){
                HttpGetrequest.addHeader(header, headerMap.get(header));
            }

            Httpresponse = Httpclient.execute(HttpGetrequest);


        } catch (URISyntaxException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return Httpresponse;

    }

    public static HttpResponse sendGet(HttpGet HttpGetrequest){

        org.apache.http.client.HttpClient Httpclient = null;
        HttpResponse Httpresponse = null;

        try {
            Httpclient = HttpClientBuilder.create().build();
            Httpresponse = Httpclient.execute(HttpGetrequest);


        } catch ( IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return Httpresponse;

    }

    public static HttpGet buildHttpGetRequestObject(String serviceURL,HashMap<String,String> urlQueryParameterMap,
                                                    HashMap<String,String> requestHeadersMap){

        HttpGet HttpGetrequest = null;
        URIBuilder builder = null;

        builder = builtURI(serviceURL,urlQueryParameterMap);

        try {

            HttpGetrequest = new HttpGet(builder.build());

            for(String header:requestHeadersMap.keySet()){
                HttpGetrequest.addHeader(header, requestHeadersMap.get(header));
            }
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return HttpGetrequest;

    }

    public static HttpPost buildHttpPostRequestObject(String serviceURL, HashMap<String,String> urlQueryParameterMap, HashMap<String,String> requestHeadersMap){

        HttpPost HttpPostrequest = null;
        URIBuilder builder = null;



        if(urlQueryParameterMap == null || urlQueryParameterMap.isEmpty()){
            builder = builtURI(serviceURL);
        }else{
            builder = builtURI(serviceURL,urlQueryParameterMap);
        }

        try {

            HttpPostrequest = new HttpPost(builder.build());

            for(String header:requestHeadersMap.keySet()){
                HttpPostrequest.addHeader(header, requestHeadersMap.get(header));
            }
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return HttpPostrequest;

    }

    public static HttpPut buildHttpPutRequestObject(String serviceURL, HashMap<String,String> urlQueryParameterMap, HashMap<String,String> requestHeadersMap){

        HttpPut HttpPutrequest = null;
        URIBuilder builder = null;



        if(urlQueryParameterMap == null || urlQueryParameterMap.isEmpty()){
            builder = builtURI(serviceURL);
        }else{
            builder = builtURI(serviceURL,urlQueryParameterMap);
        }

        try {

            HttpPutrequest = new HttpPut(builder.build());

            for(String header:requestHeadersMap.keySet()){
                HttpPutrequest.addHeader(header, requestHeadersMap.get(header));
            }
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return HttpPutrequest;

    }

    public static HttpResponse httpGet(URIBuilder builder,List<String> requestLists){

        org.apache.http.client.HttpClient Httpclient = null;
        HttpGet HttpGetrequest = null;
        HttpResponse Httpresponse = null;

        try {
            Httpclient = HttpClientBuilder.create().build();
            HttpGetrequest = new HttpGet(builder.build());

            HttpGetrequest.setHeader("Content-type","application/json");


			/*for(String header:headerMap.keySet()){
				HttpGetrequest.addHeader(header, headerMap.get(header));
			}*/

            Httpresponse = Httpclient.execute(HttpGetrequest);


        } catch (URISyntaxException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return Httpresponse;

    }

    public static List<NameValuePair> construct_uri_parameters (HashMap<String,String> mapToConvert) throws URISyntaxException{

        List<NameValuePair> queryParams = new ArrayList<NameValuePair>();

        for (Map.Entry<String, String> entry : mapToConvert.entrySet()) {
            queryParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        return queryParams;

    }

    public static HashMap<String,Object> parseHttpResponse(HttpResponse response){

        HashMap<String,Object> responseMap = new HashMap<String,Object>();


        responseMap.put("code", String.valueOf(response.getStatusLine().getStatusCode()));

        if(((String) responseMap.get("code")).equalsIgnoreCase("200")){
            responseMap.put("status", "PASS");
        }else{
            responseMap.put("status", "FAIL");
        }

        //Parse the HttpResponse Content
        org.apache.http.Header[] headers = response.getAllHeaders();

        for(org.apache.http.Header header : headers)
        {
            System.out.println("Key : " + header.getName()
                    + " ,Value : " + header.getValue());

            responseMap.put(header.getName().toString(),header.getValue().toString());
        }



        responseMap.put("visitorID", ((String) responseMap.get("Set-Cookie")).split(";")[0].split("=")[1].split("\\|")[1]);

        return responseMap;

    }

    public static boolean verifyHttpResponseStatus (HttpResponse response){


        return response.getStatusLine().getStatusCode() == 200;


    }

    public static String parseHttpReponse (HttpResponse response) throws Exception{

        String result = null;

        if(response.getStatusLine().getStatusCode() == 200){

            System.out.println("status "+response.getStatusLine().getStatusCode());

        }else{

            System.out.println("status "+response.getStatusLine().getStatusCode());
            result = "FAIL";
        }

        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        StringBuffer strBuffer = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            strBuffer.append(line);
        }


        Header[] headers = response.getAllHeaders();

        for (Header header : headers) {


            if(header.getName().equalsIgnoreCase("Date")
                    || header.getName().equalsIgnoreCase("Last-Modified")){

                System.out.println( header.getName()+" : "
                        + header.getValue());

            }

        }

        System.out.println("ResponseContent : "+strBuffer.toString());

        result = strBuffer.toString();

        System.out.println("httpEntity : "+response.getEntity());

        for (Header header : headers) {

            System.out.println( header.getName()+" : "
                    + header.getValue());
        }


        return result;

    }

    public static HttpResponse sendPost(HttpPost HttpPostrequest,String jsonBodyRequest){

        org.apache.http.client.HttpClient Httpclient = null;
        HttpResponse Httpresponse = null;

        try {
            Httpclient = HttpClientBuilder.create().build();

            //Add Json Body Request String
            HttpPostrequest.setEntity(new StringEntity(jsonBodyRequest));
            Httpresponse = Httpclient.execute(HttpPostrequest);


        } catch ( IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return Httpresponse;

    }

    public static HttpResponse sendPut(HttpPut HttpPutrequest,String jsonBodyRequest){

        org.apache.http.client.HttpClient Httpclient = null;
        HttpResponse Httpresponse = null;

        try {
            Httpclient = HttpClientBuilder.create().build();

            //Add Json Body Request String
            HttpPutrequest.setEntity(new StringEntity(jsonBodyRequest));
            Httpresponse = Httpclient.execute(HttpPutrequest);


        } catch ( IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return Httpresponse;

    }


    public static HttpResponse triggerPost(URIBuilder builder,HashMap<String,String> headerMap,String jsonBodyRequest){

        org.apache.http.client.HttpClient Httpclient = null;
        HttpPost post = null;
        HttpResponse Httpresponse = null;

        try {
            Httpclient = HttpClientBuilder.create().build();
            post = new HttpPost(builder.build());

            for(String header:headerMap.keySet()){
                post.addHeader(header, headerMap.get(header));
            }

            //Add Json Body Request String
            post.setEntity(new StringEntity(jsonBodyRequest));

            Httpresponse = Httpclient.execute(post);


        } catch (URISyntaxException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return Httpresponse;

    }


    public static HttpResponse triggerPut(URIBuilder builder,HashMap<String,String> headerMap,String jsonBodyRequest){

        org.apache.http.client.HttpClient Httpclient = null;
        HttpPut put = null;
        HttpResponse Httpresponse = null;

        try {
            Httpclient = HttpClientBuilder.create().build();
            put = new HttpPut(builder.build());

            for(String header:headerMap.keySet()){
                put.addHeader(header, headerMap.get(header));
            }

            //Add Json Body Request String
            put.setEntity(new StringEntity(jsonBodyRequest));

            Httpresponse = Httpclient.execute(put);


        } catch (URISyntaxException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return Httpresponse;

    }


}
