package com.mplatform.framework.controller.manalytics;

import com.mplatform.framework.base.Utils;
import com.mplatform.framework.data.manalytics.MA_LoginPageData;
import com.mplatform.framework.model.manalytics.MA_LoginPageModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class MA_LoginPageController extends Utils{

    MA_LoginPageModel loginPage = null;
    public MA_LoginPageData maLoginData = null;

    public MA_LoginPageController(WebDriver driver) throws Exception {
        super(driver);
        loginPage = PageFactory.initElements(driver, MA_LoginPageModel.class);
    }

	public void loginNdVerifyLandingPage() throws Exception{
		launchURL(maLoginData.strDTURL);
		delayFor(50);
		typeValue(loginPage.userName, "Email", maLoginData.strDTUserName);
		typeValue(loginPage.password, "password", maLoginData.strDTPassword);
		clickObject(loginPage.login(), "Login Button");
		verifyPageTitle("My Reports Index", maLoginData.strDTLandingPageName);
		verifyTextInElement(loginPage.userAccount, "Welcome User Message Verificarion", "Welcome, "+maLoginData.strDTUserName);
		clickObject(loginPage.switchAccountBtn, "Switch Account Dropdown Btn");
		clickObject(loginPage._account+"--"+maLoginData.strDTAccount, "Switch to => "+maLoginData.strDTAccount);
	}

}
