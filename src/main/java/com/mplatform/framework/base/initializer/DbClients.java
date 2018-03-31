package com.mplatform.framework.base.initializer;

import com.mplatform.framework.base.Helper;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created by mohamed.abdulkadar on 6/16/2017.
 */
public class DbClients {

    private static Connection conn = null;

    public static void main(String[] args)
    {
        @SuppressWarnings("unused")
        DbClients dbClients = new DbClients();

    }

    /*
     * Establish a connection to Vertica via JDBC
     * @throws SQLException
     */
    public void openVerticaConnection(String vjdbc, String vuserid, String vpassword) throws Exception
    {

        try {
            Class.forName("com.vertica.jdbc.ApiTestDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("[ERROR] Could not find the JDBC driver class.");
            e.printStackTrace();
//            stopEverything();
        }

        Properties myProp = new Properties();
        myProp.put("user", vuserid);
        myProp.put("password", vpassword);
        myProp.put("loginTimeout", "35");

        try {
            conn = DriverManager.getConnection(vjdbc, myProp);
            System.out.println("[INFO] Created connection to Vertica");
        } catch (SQLTransientConnectionException connException) {
            System.out.println("[ERROR] Network connection issue");
            System.out.println("[ERROR] " + connException.getMessage());
        } catch (SQLInvalidAuthorizationSpecException authException) {
            System.out.println("[ERROR] Invalid Vertica credentials");
            System.out.println("[ERROR] " + authException.getMessage());
        } catch (SQLException e) {
            System.err.println("[ERROR] Could not create Vertica connection");
            e.printStackTrace();
        }

    } // openVerticaConnection


    public void openMariaDBConnection(String mdbc, String muserid, String mpassword) throws Exception
    {

        try {
            Class.forName("org.mariadb.jdbc.ApiTestDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("[ERROR] Could not find the JDBC driver class.");
            e.printStackTrace();
//            stopEverything();
        }

        Properties myProp = new Properties();
        myProp.put("user", muserid);
        myProp.put("password", mpassword);
        myProp.put("loginTimeout", "35");

        try {
            conn = DriverManager.getConnection(mdbc, myProp);
            System.out.println("[INFO] Created connection to MariaDB");
        } catch (SQLTransientConnectionException connException) {
            System.out.println("[ERROR] Network connection issue");
            System.out.println("[ERROR] " + connException.getMessage());
        } catch (SQLInvalidAuthorizationSpecException authException) {
            System.out.println("[ERROR] Invalid MariaDB credentials");
            System.out.println("[ERROR] " + authException.getMessage());
        } catch (SQLException e) {
            System.err.println("[ERROR] Could not create Mariadb connection");
            e.printStackTrace();
        }

    } // openMariaConnection






    /*
     * Establish a connection to ORACLE via JDBC
     * @throws SQLException
     */
    public void openOracleConnection(String Oraclejdbc, String vuserid, String vpassword) throws Exception
    {

        String urlstr="jdbc:oracle:thin:@"+Oraclejdbc;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("[ERROR] Could not find the JDBC driver class.");
            e.printStackTrace();
//            stopEverything();
        }

        Properties myProp = new Properties();
        myProp.put("user", vuserid);
        myProp.put("password", vpassword);
        myProp.put("loginTimeout", "35");

        try {
            conn = DriverManager.getConnection(urlstr, myProp);
            System.out.println("[INFO] Created connection to Oracle");
        } catch (SQLTransientConnectionException connException) {
            System.out.println("[ERROR] Network connection issue");
            System.out.println("[ERROR] " + connException.getMessage());
        } catch (SQLInvalidAuthorizationSpecException authException) {
            System.out.println("[ERROR] Invalid Oracle credentials");
            System.out.println("[ERROR] " + authException.getMessage());
        } catch (SQLException e) {
            System.err.println("[ERROR] Could not create Oracle connection");
            e.printStackTrace();
        }

    } // openVerticaConnection

    /*
     * Close existing Vertica connection
     * @throws SQLException
     */
    public void closeVerticaConnection() throws SQLException
    {
        System.out.println("Vertica : Disconnecting");
        conn.close();

    } // closeVerticaConnection

    /*
     * Retrieve data rows from Vertica
     * @throws SQLException
     */
    public ArrayList<HashMap<String, Object>> queryVertica(String query) throws SQLException
    {

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = null;

            boolean Returning_Rows = stmt.execute(query);
            if (Returning_Rows)
            {
                rs = stmt.getResultSet();
            } else {
                return new ArrayList<HashMap<String,Object>>();
            }

            //get metadata
            ResultSetMetaData Meta = null;
            Meta = rs.getMetaData();

            //get column names
            int Col_Count = Meta.getColumnCount();
            ArrayList<String> Cols = new ArrayList<String>();
            for (int Index=1; Index<=Col_Count; Index++)
            {
                Cols.add(Meta.getColumnName(Index));
            }

            //fetch out rows
            ArrayList<HashMap<String,Object>> Rows =
                    new ArrayList<HashMap<String,Object>>();

            while (rs.next())
            {
                HashMap<String,Object> Row = new HashMap<String,Object>();
                for (String Col_Name:Cols)
                {
                    Object Val = rs.getObject(Col_Name);
                    Row.put(Col_Name,Val);
                }
                Rows.add(Row);
            }

            //close statement
            stmt.close();

            //pass back rows
            return Rows;
        } catch (Exception Ex) {
            System.out.print(Ex.getMessage());
            return new ArrayList<HashMap<String,Object>>();
        }

    } // queryVertica


    /*
     * Insert Batch Records into Vertica
     * @throws SQLException
     */
    public void InsertBulkRecords(ArrayList<String> insertStatementsArray,String threadName) throws SQLException
    {
        String Starttime = null;
        String bulkExecStarttime = null;

        System.out.println("["+threadName+"-Vertica ] - Starting  Vertica Bulk Insert. START TIME:"+Starttime);

        Statement stmt = null;

        try {
            conn.setAutoCommit(false);
            stmt = conn.createStatement();

            if(insertStatementsArray.size() > 0){

                for(String stmtStr : insertStatementsArray){

                    //System.out.println(stmtStr);
                    stmt.addBatch(stmtStr);


                }
            }

            bulkExecStarttime = Helper.generateStringTimeStampWithoutZone();

            System.out.println("["+threadName+"-Vertica ] - Start  Vertica Batch Excecution. START TIME:"+bulkExecStarttime);
            stmt.executeBatch();
            System.out.println("["+threadName+"-Vertica ] - End  Vertica Batch Excecution. SART TIME:"+bulkExecStarttime+", END TIME:"+ Helper.generateStringTimeStampWithoutZone());

            //close statement
            stmt.close();
            conn.commit();

            System.out.println("["+threadName+"-Vertica ] - End  Vertica Bulk Insert. START TIME:"+Starttime+", END TIME:"+Helper.generateStringTimeStampWithoutZone());

        }
        catch (Exception Ex) {
            //close statement
            stmt.close();
            conn.rollback();
            System.out.print("["+threadName+"-Vertica ] - Vertica General Exception "+Ex.getMessage());

        }



    }//Insert Batch Records


}
