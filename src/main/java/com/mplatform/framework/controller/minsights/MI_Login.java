package com.mplatform.framework.controller.minsights;

import com.mplatform.framework.base.Utils;
import com.mplatform.framework.data.minsights.MI_DataVO;
import com.mplatform.framework.model.minsights.MI_LoginPage;
import com.mplatform.framework.model.minsights.MI_MainHeader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by mohamed.abdulkadar on 6/18/2017.
 */
public class MI_Login extends Utils {

    //Load Test Data
    public MI_DataVO testDataVO = null;

    static MI_LoginPage loginpage = null;

    //Added by vaibhav for account select from the dropdown - 08/16/2017
    static MI_MainHeader accountSelectFromMainHeader = null;

    public MI_Login(WebDriver driver) throws Exception {
        super(driver);
        loginpage = PageFactory.initElements(driver, MI_LoginPage.class);
    }

    public  void login() throws Exception{
        launchURL(testDataVO.getProperty("url_hostname_node1"));
        clickObject(loginpage.userName, "Click on UserName Field");
        clickObject(loginpage.password, "Click on Password Field");
        typeValue(loginpage.userName, "User Name", testDataVO.getUserName("SUPERUSER"));

        clickObject(loginpage.userName, "Click on UserName Field");
        typeValue(loginpage.password, "Password", testDataVO.getUserPassword("SUPERUSER"));
        clickObject(loginpage.userName, "Click on UserName Field");
        clickObject(loginpage.loginButton, "MI_Login Button");

    }

    /**
     * Created by Vaibhav on 08/10/2017.
     * Desc : This method created for existing launch browser with mInsight UI
     */
    public  void loginOnExistingLaunchPage() throws Exception{
      //  launchURL(testDataVO.getProperty("url_hostname_node1"));
        clickObject(loginpage.userName, "Click on UserName Field");
        clickObject(loginpage.password, "Click on Password Field");
        typeValue(loginpage.userName, "User Name", testDataVO.getUserName("SUPERUSER"));

        clickObject(loginpage.userName, "Click on UserName Field");
        typeValue(loginpage.password, "Password", testDataVO.getUserPassword("SUPERUSER"));
        clickObject(loginpage.userName, "Click on UserName Field");
        clickObject(loginpage.loginButton, "MI_Login Button");

    }


    /**
     * Created by Vaibhav on 08/10/2017.
     * Desc : This method created for OKTA User forgot password verification
     */
    public void forgotPassword() throws Exception{
        launchURL(testDataVO.getProperty("url_hostname_node1"));
        clickObject(loginpage.forgotPassword,"Click on Forgot Password");
        elementExistance(loginpage.emailId,"EmailId displayed",3);
        clickObject(loginpage.emailId,"click on the EmailId Field");
        typeValue(loginpage.emailId,"EmailId",testDataVO.getUserName("OKTAUSER1"));
        clickObject(loginpage.sendEmail,"Click on Send Email Button");
        elementExistance(loginpage.emailSentMsg,"Email Sent Message displayed",2);
        verifyTextInElement(loginpage.emailSentMsg,"Email Sent Message verification","An email has been sent to "+testDataVO.getUserName("OKTAUSER1")+", please check your inbox for instructions");
        clickObject(loginpage.backToLoginSentMsgPage,"Back to Login page to enter userid");
        elementExistance(loginpage.userName,"Login page displayed",60);
    }

    /**
     * Created by Vaibhav on 08/10/2017.
     * Desc : This method created for Account Selection from the Dropdown
     */
    public void accountSelectionFromHeader(String accountToSelect) throws Exception{
        String accountName = accountToSelect;
        if (accountSelectFromMainHeader == null) {
            accountSelectFromMainHeader = new MI_MainHeader(driver);
        }
        // Wait for 60 seconds to close the create screen after click on the save button
        elementExistance(accountSelectFromMainHeader.AccountField ,"Account selection Present",60);
        clickObject(accountSelectFromMainHeader.AccountSelect_Icon,"Click on the Account selection dropdown icon");
        clickObject(accountSelectFromMainHeader._AccountList+"--"+accountName,"Select Account from drop down");
        refreshPage();
    }
}
