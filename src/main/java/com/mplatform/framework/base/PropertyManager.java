package com.mplatform.framework.base;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import org.testng.Reporter;

public class PropertyManager {

	private static PropertyManager _manager = null;

	private final static String CONFIG_FILE_PATH = "config/config.properties";

	private Properties _configProperties = new Properties();

	private PropertyManager(String configFilePath) {

		configFilePath = CONFIG_FILE_PATH;

		loadProperties(_configProperties, configFilePath);

	}

	public synchronized static PropertyManager getInstance(String filePath) {

		if (_manager == null) {

			synchronized (PropertyManager.class) {

				if (_manager == null) {

					_manager = new PropertyManager(filePath);

				}

			}

		}

		return _manager;

	}

	public static PropertyManager getInstance() {

		return _manager;

	}

	public String getConfigProperty(String key) {

		return _configProperties.getProperty(key);

	}

	private static void loadProperties(Properties prop, String filePath) {

		if (filePath == null) {

			Reporter.log("File Path is not available to load the data");

			return;

		}

		try {

			prop.load(new FileReader(filePath.trim()));

		} catch (IOException e) {

			// System.out.println(" Exception "+e.getMessage()+
			// " File Path "+filePath);

			Reporter.log("Exception occured while loading config file from "

			+ filePath + ". Exception Message:" + e.getMessage());

		}

	}

	public void cleanup() {

		_configProperties.clear();

	}

}
