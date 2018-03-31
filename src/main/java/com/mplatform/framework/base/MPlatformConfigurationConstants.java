package com.mplatform.framework.base;

public class MPlatformConfigurationConstants {


  /*****************************************************************************************
   * Application constants
   * ***************************************************************************************/


  /**
   * Environment URL
   */
  public String getURL() {

    return MPlatformPropertiesConfiguration.getProperty("url");
  }

  /**
   * Environment URL
   */
  public String functionalLog() {

    return MPlatformPropertiesConfiguration.getProperty("log4j.appender.TTCC.File");
  }

  /**
   * Environment URL
   */
  public String performanceLog() {

    return MPlatformPropertiesConfiguration.getProperty("log4j.appender.R.File");
  }

  public String dataSheetPath(){
    return MPlatformPropertiesConfiguration.getProperty("InputDatasheetPath");
  }

}