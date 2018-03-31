package com.mplatform.framework.base.minsights;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.*;
import com.mplatform.framework.base.minsights.api.LoginOutputVO;
import com.mplatform.framework.base.minsights.api.LoginRequestVO;


import java.io.IOException;
import java.util.HashMap;

/**
 * Created by mohamed.abdulkadar on 6/18/2017.
 */
public class ObjectConversion {

    ObjectMapper mapper = null;

    public ObjectConversion(){
        mapper = new ObjectMapper();
        mapper.configure(MapperFeature.USE_ANNOTATIONS, false);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.setVisibility(PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.IS_GETTER, JsonAutoDetect.Visibility.NONE);
        mapper.setVisibility(PropertyAccessor.SETTER, JsonAutoDetect.Visibility.NONE);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(MapperFeature.USE_STATIC_TYPING, true);
        mapper.disable(SerializationFeature.INDENT_OUTPUT);


        //mapper.configure(SerializationConfig.Feature.SORT_PROPERTIES_ALPHABETICALLY, true);
        //mapper.enable(SerializationFeature.;
        // mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public  String convertObjectToString (Object inputVO){
        LoginRequestVO loginReqVO = null;
        LoginOutputVO loginOutVO = null;

        String strResult = null;
        try {

            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            if(inputVO instanceof LoginRequestVO){

                loginReqVO = (LoginRequestVO) inputVO;
                strResult = mapper.writer().writeValueAsString(loginReqVO);
            }else if(inputVO instanceof LoginOutputVO){

            }

        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();        }
            catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();        }


        return strResult;

    }


    public  LoginOutputVO convertLoginOutStringToVO (String strInput){

        LoginOutputVO loginOutVO = null;

        try {
            loginOutVO  = mapper.readValue(strInput, LoginOutputVO.class);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //return "Controller Object Conversion IOException : "+e.getMessage();
        }catch (Exception e){
            e.printStackTrace();
            //return "Controller Object Conversion Exception : "+e.getMessage();
        }
        return loginOutVO;



    }

    public   String convertHashMapObjToString(HashMap<String,String>
                                                     hashmapObject ){


        String outputString = null;



        //Convert Object to JSON String
        try {
           // outputString = mapper.writeValueAsString(hashmapObject);
            outputString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(hashmapObject);

            System.out.println(outputString);

        } catch (JsonGenerationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return outputString;

    }

}
