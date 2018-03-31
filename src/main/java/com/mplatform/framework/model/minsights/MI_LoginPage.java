package com.mplatform.framework.model.minsights;

import com.mplatform.framework.model.ModelBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by mohamed.abdulkadar on 6/21/2017.
 */
public class MI_LoginPage extends ModelBase {

    public MI_LoginPage(WebDriver driver) {
        super(driver);
    }

    //===================================Static Objects Identifiers===========================


    public WebElement cancelReset(){
        return driver.findElement(By.xpath("//*[@id='reset-password-form']//button[contains(text(),'Cancel')]"));
    }

    @FindBy(id="username") public WebElement userName;
    @FindBy(id="password") public WebElement password;
    @FindBy(id="submit") public WebElement loginButton;


    //===================================Dynamic Objects Identifiers===========================


    public String _selectAnInstance = ".//*[@id='user-nav']//a[contains(text(),'EXTERNALDATA')]";

    /*
      Created : VaibhavS
      Date : 11th Aug 2017
      Desc : OKTA User Integration verification
     */
    //===================================Static Objects Identifiers Vaibhav===========================
    @FindBy(xpath="//a[text()=\'Forgot Password?\']")  public WebElement forgotPassword;
    @FindBy(id="emailAddress") public WebElement emailId;
    @FindBy(id="submit") public WebElement sendEmail;
    @FindBy(xpath="//div[@class=\'seven wide field\']/a") public WebElement backToLoginEmailIdPage;
    @FindBy(xpath="//form[@id=\'success\']//div[@class=\'content\']") public WebElement emailSentMsg;
    @FindBy(xpath="//form[@id='success']//a") public WebElement backToLoginSentMsgPage;
}

