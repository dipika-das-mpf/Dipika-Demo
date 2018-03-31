package com.mplatform.framework.data.minsights;

import com.mplatform.framework.base.Helper;
import com.mplatform.framework.base.initializer.CouchbaseClient;
import com.mplatform.framework.base.initializer.DbClients;
import com.mplatform.framework.base.initializer.HttpClient;
import com.mplatform.framework.base.initializer.Initializer;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by mohamed.abdulkadar on 6/3/2017.
 */
public class MI_DataVO extends Helper {

    HashMap<String,String> testDataMap = null;
    HashMap<String,MI_UserCredentialsVO> mInsightsUserCredentailMap = null;
    Properties objProperties = null;
    Initializer clients = null;



    public MI_DataVO(String testDataFileName){

        this.testDataMap = readTestDataFile(testDataFileName,System.getProperty("env"));
        this.mInsightsUserCredentailMap = Helper.buildMinsightsCredetentails(getCredentialsFile());
        this.objProperties = loadProperties();
        this.clients = getClientInitailizer();


    }

    //Read Credentails Data based on Environment
    public static FileReader  getCredentialsFile(){

        FileReader mInsights_user_credentials = null;

        if(StringUtils.contains(System.getProperty("project"),"minsights")){
            try {

                switch(System.getProperty("env")){
                    case "QA1" :
                        mInsights_user_credentials = new FileReader(System.getProperty("user.dir")+
                                File.separator+"minsights_userCredentails"+File.separator+"qa1_minsights_user_credentails.json");
                        break;
                    case "INT" :
                        mInsights_user_credentials = new FileReader(System.getProperty("user.dir")+
                                File.separator+"minsights_userCredentails"+File.separator+"int_minsights_user_credentails.json");
                        break;
                    case "STG" :
                        mInsights_user_credentials = new FileReader(System.getProperty("user.dir")+
                                File.separator+"minsights_userCredentails"+File.separator+"stg_minsights_user_credentails.json");
                        break;
                    case "DEV" :
                        mInsights_user_credentials = new FileReader(System.getProperty("user.dir")+
                                File.separator+"minsights_userCredentails"+File.separator+"dev_minsights_user_credentails.json");
                        break;
                    default:
                        System.out.println("minsights user credentails file is not loaded.");
                        //System.exit(1);

                }


            } catch (FileNotFoundException e) {
                System.out.println("minsights user credentails file is not find exception.");
                e.printStackTrace();
            }

        }

        return mInsights_user_credentials;
    }


    //Load Property data based on Environment
    public static Properties loadProperties(){

        Properties objProperties = new Properties();

        try
        {
            //if(StringUtils.contains(System.getProperty("env").toLowerCase()))


            if(StringUtils.contains(System.getProperty("project"),"minsights")) {

                switch (System.getProperty("env")) {
                    case "QA1":
                        //objProperties.load(new FileReader(System.getProperty("user.dir") + "/Config/QA1_CONFIG.properties"));
                        objProperties.load(new FileReader(System.getProperty("user.dir") +File.separator
                                +"Config"+File.separator+"minsights"+File.separator+"QA1_CONFIG.properties"));
                        break;
                    case "INT":
                        //objProperties.load(new FileReader(System.getProperty("user.dir") + "/Config/INT_CONFIG.properties"));
                        objProperties.load(new FileReader(System.getProperty("user.dir") +File.separator
                                +"Config"+File.separator+"minsights"+File.separator+"INT_CONFIG.properties"));
                        break;
                    case "STG":
                        //objProperties.load(new FileReader(System.getProperty("user.dir") + "/Config/STG_CONFIG.properties"));
                        objProperties.load(new FileReader(System.getProperty("user.dir") +File.separator
                                +"Config"+File.separator+"minsights"+File.separator+"STG_CONFIG.properties"));
                        break;
                    case "DEV":
                        //objProperties.load(new FileReader(System.getProperty("user.dir") + "/Config/DEV_CONFIG.properties"));
                        objProperties.load(new FileReader(System.getProperty("user.dir") +File.separator
                                +"Config"+File.separator+"minsights"+File.separator+"DEV_CONFIG.properties"));
                        break;
                    default:
                        System.out.println("Pass correct environment parameter -Devn");
                        System.exit(1);

                }
            }else{

                objProperties.load(new FileReader(System.getProperty("user.dir") + "/Config/CONFIG.properties"));
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return objProperties;
    }

    private Initializer getClientInitailizer(){

        Initializer initializer = new Initializer(new DbClients(),new DbClients(),new DbClients(),new DbClients(),
                new CouchbaseClient(), new HttpClient());

        return initializer;

    }

    public String getTestData(String key) {

        String value = getTestDataMap().get(key);
        return value.trim();
    }

    private void setTestDataMap(HashMap<String, String> testDataMap) {
        this.testDataMap = testDataMap;
    }

    public HashMap<String, String> getTestDataMap() {
        return this.testDataMap;
    }

    private HashMap<String, MI_UserCredentialsVO> getmInsightsUserCredentailMap() {
        return mInsightsUserCredentailMap;
    }

    public String getUserName(String visitorType) {
        return mInsightsUserCredentailMap.get(visitorType).getUsername();
    }

    public String getUserPassword(String visitorType) {
        return mInsightsUserCredentailMap.get(visitorType).getPassword();
    }

    private void setmInsightsUserCredentailMap(HashMap<String, MI_UserCredentialsVO> mInsightsUserCredentailMap) {
        this.mInsightsUserCredentailMap = mInsightsUserCredentailMap;
    }

    private Properties getObjProperties() {
        return objProperties;
    }

    private void setObjProperties(Properties objProperties) {
        this.objProperties = objProperties;
    }

    public String getProperty(String key){

        return this.objProperties.getProperty(key);
    }

    public String getApiUserName() {
        return this.objProperties.getProperty("api_username");
    }

    public String getApiUserPassword() {
        return this.objProperties.getProperty("api_password");
    }

    public String getApiHost(String node) {

        if(node.equalsIgnoreCase("node1")){

            return this.objProperties.getProperty("api_node1");

        }else if(node.equalsIgnoreCase("node2")){
            return this.objProperties.getProperty("api_node2");
        }

        return null;
    }


    public void getMetaMariaClient() {
        try {
            clients.getDbMetaMariaClient().openMariaDBConnection(objProperties.getProperty("MetaDB_url"),
                    objProperties.getProperty("MetaDB_Username"),objProperties.getProperty("MetaDB_Password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<HashMap<String, Object>> runMetaSQLStatment(String query) {
        try {
            return clients.getDbMetaMariaClient().queryVertica(query);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void closeMetaMariaClient() {
        try {
            clients.getDbMetaMariaClient().closeVerticaConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getPortalMariaClient() {
        try {
            clients.getDbPortalMariaClient().openMariaDBConnection(objProperties.getProperty("PortalDB_url"),
                    objProperties.getProperty("PortalDB_Username"),objProperties.getProperty("PortalDB_Password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<HashMap<String, Object>> runPortalSQLStatment(String query) {
        try {
            return clients.getDbPortalMariaClient().queryVertica(query);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void closePortalMariaClient() {
        try {
            clients.getDbPortalMariaClient().closeVerticaConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getVerticaBackEndClient() {
        try {
            clients.getDbVerticaBackendClient().openVerticaConnection(objProperties.getProperty("Vertica_Backend_Cluster"),
                    objProperties.getProperty("Vertica_Backend_Username"),objProperties.getProperty("Vertica_Backend_Password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<HashMap<String, Object>> runVerticaBackEndSQLStatment(String query) {
        try {
            return clients.getDbVerticaBackendClient().queryVertica(query);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void closeVerticaBackEndClient() {
        try {
            clients.getDbPortalMariaClient().closeVerticaConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getVerticaReportingClient() {
        try {
            clients.getDbVerticaReportingClient().openVerticaConnection(objProperties.getProperty("Vertica_Reporting_Cluster"),
                    objProperties.getProperty("Vertica_Reporting_Username"),objProperties.getProperty("Vertica_Reporting_Password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<HashMap<String, Object>> runVerticaReportingSQLStatment(String query) {
        try {
            return clients.getDbVerticaReportingClient().queryVertica(query);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public void closeVerticaReportingClient() {
        try {
            clients.getDbVerticaReportingClient().closeVerticaConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Get Couchbase Document

    public String getCouchbaseDocument(String bucketName,String docKey){

        String result = null;

        if(StringUtils.isNotBlank(bucketName) && StringUtils.isNotBlank(docKey) ) {

            if (bucketName.equalsIgnoreCase("Configurations")) {
                result = clients.getCouchbaseClient().openCouchbaseConnection(objProperties.getProperty("cb_configuration"));

                if(!result.equalsIgnoreCase("ERROR")){
                    result = clients.getCouchbaseClient().readCouchbase("Configurations", docKey);
                    return result;
                }

            }else if(bucketName.equalsIgnoreCase("Dynamic")){
                result = clients.getCouchbaseClient().openCouchbaseConnection(objProperties.getProperty("cb_dynamic"));

                if(!result.equalsIgnoreCase("ERROR")){
                    result = clients.getCouchbaseClient().readCouchbase("Dynamic", docKey);
                    return result;
                }
            }
        }else{
            return result;
        }

        return null;

    }
}
