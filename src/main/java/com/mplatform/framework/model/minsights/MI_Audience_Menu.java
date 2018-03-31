package com.mplatform.framework.model.minsights;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MI_Audience_Menu extends MI_MainHeader {

/**
 * Created by mohamed.abdulkadar on 6/13/2017.
 */


    public MI_Audience_Menu(WebDriver driver) {
        super(driver);
    }

    public WebElement Audience_Menu_Element(){
        return driver.findElement(By.xpath("//div[@class='ui five item attached mainmenu menu']//div[@class='ui container']/div[1]"));

    }


   // @FindBy(id="menuDataPartnerModel") public WebElement LookLikeModelMenu;

    //@FindBy(xpath="html/body/div[2]/div[2]/div[1]/div[2]/div/div/div[1]/div/a[2]") public WebElement
    // LookLikeModelMenu;

    @FindBy(xpath="//div/div[contains(@class,'menu')]/a[@id='menuLookalikeModel']") public WebElement LookLikeModelMenu;


    @FindBy(xpath="//div/div[contains(@class,'menu2')]/a[@id='menuLookalikeModel']") public WebElement DataPartnerModelMenu;

}