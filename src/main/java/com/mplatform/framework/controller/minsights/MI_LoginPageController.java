package com.mplatform.framework.controller.minsights;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import com.mplatform.framework.base.Utils;
import com.mplatform.framework.data.minsights.MI_LoginPageData;
import com.mplatform.framework.model.minsights.MI_LoginPageModel;


public class MI_LoginPageController extends Utils {
	
	//Initializing the models and data class files <<----
	public MI_LoginPageData loginData = null;
	MI_LoginPageModel loginPage = null;

	// We Need to pass the model class file only in the constructor, While don't
	// need to pass the data class files.
	public MI_LoginPageController(WebDriver driver) throws Exception {
		super(driver);
		loginPage = PageFactory.initElements(driver, MI_LoginPageModel.class);
	}
	
	public void login() throws Exception{
		launchURL(loginData.strDTURL);
		typeValue(loginPage.userName, "User Name", loginData.strDTUserName);
		typeValue(loginPage.password, "Password", loginData.strDTPassword);
		clickObject(loginPage.loginButton, "MI_Login Button");
	}
	
}
