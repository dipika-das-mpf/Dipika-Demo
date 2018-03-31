package com.mplatform.framework.model.manalytics;

import com.mplatform.framework.model.ModelBase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by ahmed.neinae on 5/18/17.
 */


public class MA_LoginPageModel extends ModelBase{

    public MA_LoginPageModel(WebDriver driver) {
        super(driver);
    }


    //===================================Static Objects Identifiers===========================


//    public WebElement cancel(){
//        return driver.findElement(By.xpath("//*[@id='reset-password-form']//button[contains(text(),'Cancel')]"));
//    }

    @FindBy(name="email") public WebElement userName;
    @FindBy(name="password") public WebElement password;
    public WebElement login(){
      return driver.findElement(By.id("login-btn"));
  }
    public String userAccount = ".//*[@id='nav-view']/div[@class='username-container']/span";
    public String switchAccountBtn = ".//*[@id='accountID-dropdown']";


  //===================================Dynamic Objects Identifiers===========================


    public String _reportNmae = ".//span[contains(text(),'EXTERNALDATA1')]//a[contains(text(),'EXTERNALDATA2')]";
    public String _account = ".//*[@id='accountID-dropdown']//a[contains(text(),'EXTERNALDATA')]";
}
