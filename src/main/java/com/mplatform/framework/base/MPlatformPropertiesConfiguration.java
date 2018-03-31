package com.mplatform.framework.base;

import org.testng.Reporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class MPlatformPropertiesConfiguration {

	private static final Properties CONFIG_PROPERTIES = new Properties();
	private static final MPlatformConfigurationConstants MPLATFORM_CONFIG_CONSTANTS =
		      new MPlatformConfigurationConstants();

	static {
			loadProperties("./Config/CONFIG.properties"); // Loads all the selenium server
			loadProperties("./Config/log4j.properties");
		  }
	private static void loadProperties(String propertyFileName) {
	    // property file is located within the package hierarchy and this works
	    // fine
	    // even when a jar is created of all classes. locating the file on a
	    // file
	    // system path using -Dxxx arguments to JVM has been avoided.
	    Properties configProperties = new Properties();
	    (new MPlatformPropertiesConfiguration()).getClass().getClassLoader()
	        .getResourceAsStream(propertyFileName);
	    File file = new File(propertyFileName);

	    try {
	      if (file.exists()) {
			configProperties.load(new FileInputStream("./Config/CONFIG.properties"));
			configProperties.load(new FileInputStream("./Config/log4j.properties"));
	        Set<Map.Entry<Object, Object>> entrySet = configProperties.entrySet();
	        if (entrySet != null) {
	          for (Map.Entry<Object, Object> entry : entrySet) {
	            CONFIG_PROPERTIES.setProperty((String) entry.getKey(), (String) entry.getValue());
	          }
	        }
	      }
	    } catch (IOException e) {
	    	Reporter.log(e.getMessage());
	    }
	  }

	  /**
	   * Returns the value of the property corresponding to key.
	   * 
	   * @param key the name of the property
	   * @return the value of the property
	   */
	  public static String getProperty(String key) {
	    return CONFIG_PROPERTIES.getProperty(key);
	  }

	  /**
	   * Sets the value of the property corresponding to key.
	   * 
	   * @param key
	   * @param value
	   */
	  public static void setProperty(String key, String value) {
	    CONFIG_PROPERTIES.setProperty(key, value);
	  }

	  /**
	   * Gets the Config Constants instance
	   * 
	   * @return MEDIALETS_CONFIG_CONSTANTS
	   */
	  public static MPlatformConfigurationConstants getConfigConstants() {
	    return MPlatformPropertiesConfiguration.MPLATFORM_CONFIG_CONSTANTS;
	  }
	
}
