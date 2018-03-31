package com.mplatform.framework.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Reporter;

public class CustomIEDriver extends InternetExplorerDriver{
	
	private static final String NAVIGATION_BLOCKED_STRING = "Certificate Error: Navigation Blocked";
	private static final String UNBLOCKING_JAVA_SCRIPT = "javascript:document.getElementById('overridelink').click()";		
			
/**
 * Customizes the Certification error for Internet Explorer
 */
	
	public CustomIEDriver(){
		super();
	}
	
	public void internetExplorerDriver(){}

	@Override
	public void get(String url){
		super.get(url);
		checkForNavigationBlockingMessage();
	}
	
	@Override
	public Object executeScript(String scriptText, Object... args){
		Object result = super.executeScript(scriptText, args);
		checkForNavigationBlockingMessage();
		return result;
	}
	
	@Override
	public void close(){
		this.manage().window().maximize();
		try{
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_F4);
			robot.keyRelease(KeyEvent.VK_F4);
			robot.keyRelease(KeyEvent.VK_ALT);
		} catch (AWTException e){
			Reporter.log("Close call for IE Driver ends with exception : "+e.getMessage());
		}
		super.close();
	}
	
	public void checkForNavigationBlockingMessage(){
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String text = getTitle();
		if(NAVIGATION_BLOCKED_STRING.equalsIgnoreCase(text)){
			super.get(UNBLOCKING_JAVA_SCRIPT);
		}
		Reporter.log("IE Log File Path:" +System.getProperty(ScriptBase.IE_DRIVER_LOG_FILE_PROPERTY));
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	
}
