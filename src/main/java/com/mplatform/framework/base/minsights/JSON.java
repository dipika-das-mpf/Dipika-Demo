package com.mplatform.framework.base.minsights;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mohamed.abdulkadar on 6/16/2017.
 */
public class JSON {

    public static Object parse(String jsonString) throws JSONException {

        Object obj = jsonString;
        Object jsonObject = null;

        jsonObject = new JSONObject(jsonString);

       return jsonObject;

    }

    public static Object args(Object obj,String argument) throws JSONException {

        JSONObject jsonObj = null;
        JSONArray jsonArray = null;

        if(obj instanceof JSONObject){
            jsonObj = (JSONObject)obj;
            return jsonObj.get(argument);
        }else if(obj instanceof JSONArray){
            jsonArray = (JSONArray) obj;

        }

        return null;
    }
}
