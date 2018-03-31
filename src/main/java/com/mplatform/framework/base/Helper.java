package com.mplatform.framework.base;

import com.mplatform.framework.data.minsights.MI_UserCredentialsVO;
import org.apache.commons.lang.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by mohamed.abdulkadar on 6/3/2017.
 */
public class Helper {

    public static String readFile(FileReader fileName) throws IOException {
        BufferedReader br = new BufferedReader(fileName);
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append("\n");
                line = br.readLine();

            }
            return sb.toString();
        } finally {
            br.close();
        }
    }

    public static JSONObject createJSONObject(String jsonString){

        JSONObject jsonObj = null;

        if(StringUtils.isNotBlank(jsonString)){

            try {
                jsonObj = new JSONObject(jsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else{
            System.out.println("minsights_user_credentails file is blank, please check...Application Exists...!!");
            System.exit(1);
        }


     return jsonObj;
    }




    public static HashMap<String,MI_UserCredentialsVO> buildMinsightsCredetentails(FileReader minSights_user_credetnails){

        HashMap<String,MI_UserCredentialsVO> userCredentialsMap
                = new HashMap<String,MI_UserCredentialsVO>();

        JSONObject jsonObj = null;
        JSONObject jsonObjTemp = null;

        MI_UserCredentialsVO userCredentailsVO = null;

        try {
            jsonObj = createJSONObject(readFile(minSights_user_credetnails));


            Iterator<?> keys = jsonObj.keys();

            while(keys.hasNext()){
                //Store UserType or any unique KeyValue
                String key = (String)keys.next();

                try {
                    jsonObjTemp = jsonObj.getJSONObject(key);

                    if(jsonObjTemp.has("username")&&jsonObjTemp.has("password")){

                         userCredentailsVO = new MI_UserCredentialsVO(jsonObjTemp.getString("username"),
                                 jsonObjTemp.getString("password"));

                        userCredentialsMap.put(key,userCredentailsVO);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return userCredentialsMap;
    }

    public static HashMap<String,String> readmInsightsCSVDataFile(ArrayList<Object> fileList,
                                                                             String dataColumnName,String cvsSplitBy) throws Exception{

        HashMap<String,String> testDataMap = new HashMap<String,String>();


        String line = null;
        BufferedReader br = null;

        for(Object fileObj : fileList){

            if(fileObj instanceof File) {
                try {
                    br = new BufferedReader(new FileReader((File) fileObj));

                    //to skip first line, and find datacolumn
                    String[] tempStrArray = br.readLine().split(cvsSplitBy);
                    int dataColumnPos = 1;

                    for(int i=1;i<tempStrArray.length;i++){

                        if(StringUtils.contains(tempStrArray[i],dataColumnName)){
                            dataColumnPos = i;
                            i = tempStrArray.length;
                        }
                    }

                    //Store Data in HashMap
                    while ((line = br.readLine()) != null) {

                        testDataMap.put(line.split(cvsSplitBy)[0].trim(),
                                 line.split(cvsSplitBy)[dataColumnPos]);


                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return testDataMap;
    }

    public static HashMap<String,String> readTestDataFile(String fileName, String dataColumnName){

        ArrayList<Object> fileObjList = null;

        HashMap<String,String> testDataMap = new HashMap<String,String>();

        if((StringUtils.isNotEmpty(fileName)&&
                StringUtils.isNotBlank(fileName))
                ||((StringUtils.isNotEmpty(dataColumnName)&&
                  StringUtils.isNotBlank(dataColumnName)))){

            try{
                if(new File(System.getProperty("user.dir")  +File.separator+"input"+File.separator
                       +"mInsights"+File.separator+fileName+".json").exists()){

                    fileObjList = new ArrayList<Object>();
                    fileObjList.add(new File(System.getProperty("user.dir")  +File.separator+"input"+File.separator
                            +"mInsights"+File.separator+fileName+".json"));
                    testDataMap=readmInsightsCSVDataFile(fileObjList,dataColumnName,"\\|");


                }else if(new File(System.getProperty("user.dir")  +File.separator+"input"+File.separator
                        +"mInsights"+File.separator+fileName+".csv").exists()){

                    fileObjList = new ArrayList<Object>();
                    fileObjList.add(new File(System.getProperty("user.dir")  +File.separator+"input"+File.separator
                            +"mInsights"+File.separator+fileName+".csv"));
                    testDataMap=readmInsightsCSVDataFile(fileObjList,dataColumnName,"\\|");

                }else if(new File(System.getProperty("user.dir")  +File.separator+"input"+File.separator
                        +"mInsights"+File.separator+fileName+".txt").exists()){

                    fileObjList = new ArrayList<Object>();
                    fileObjList.add(new File(System.getProperty("user.dir")  +File.separator+"input"+File.separator
                            +"mInsights"+File.separator+fileName+".txt"));
                    testDataMap=readmInsightsCSVDataFile(fileObjList,dataColumnName,"\\|");

                }
                else if(new File(System.getProperty("user.dir")  +File.separator+"input"+File.separator
                        +"mInsights"+File.separator+fileName+".xls").exists()){

                }else if(new File(System.getProperty("user.dir")  +File.separator+"input"+File.separator
                        +"mInsights"+File.separator+fileName+".xlsx").exists()){

                }
            }catch(Exception e){
                e.printStackTrace();
            }



        }

        return testDataMap;

    }


    public static String generateUniqueValue () throws InterruptedException
    {
        Thread.sleep(100);
        final AtomicLong TS = new AtomicLong();
        long micros = System.currentTimeMillis() * 1000;

        long value = TS.get();
        if (micros <= value)
            micros = value+1;
        /*if (TS.compareAndSet(value, micros))
        	System.out.println(micros);*/

        return String.valueOf(micros);
    }

    /**
     * Function - Generate Time stamp string in 'MMM dd,YYYY hh:min:ss a'

     * @return
     */

    public static String generateStringTimeStampWithoutZone ()
    {


        Calendar c = Calendar.getInstance();


        SimpleDateFormat format1 = new SimpleDateFormat("MMM dd, YYYY HH:mm:ss a");

        TimeZone z = c.getTimeZone();
        int offset = z.getRawOffset();
        if(z.inDaylightTime(new Date())){
            offset = offset + z.getDSTSavings();
        }
        int offsetHrs = offset / 1000 / 60 / 60;
        int offsetMins = offset / 1000 / 60 % 60;

	    /*System.out.println("offset: " + offsetHrs);
	    System.out.println("offset: " + offsetMins);*/

        c.add(Calendar.HOUR_OF_DAY, (-offsetHrs));
        c.add(Calendar.MINUTE, (-offsetMins));

        //System.out.println("GMT Time: "+c.getTime());
        String formatted = format1.format(c.getTime());
        // System.out.println(formatted);

        return formatted;
    }

    public static void parseJSONString(String jsonStr,String expected){

        JSONObject jsonObj = null;

        try{

            jsonObj = new JSONObject(jsonObj);



        }catch(Exception e){
            e.printStackTrace();
        }

    }


    public static String getOS(){

        String OS = System.getProperty("os.name").toLowerCase();


        if (OS.indexOf("win") >= 0) {
            return "win";
        } else if (OS.indexOf("mac") >= 0) {
            return "mac";
        } else if (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 ) {
            return "unix";
        } else if (OS.indexOf("sunos") >= 0) {
            return "unix";
        } else {
            return "Your OS is not support!!";
        }
    }


}
