package com.mplatform.framework.model.minsights;

import com.mplatform.framework.model.ModelBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by mohamed.abdulkadar on 6/13/2017.
 */
public class MI_Setup_and_Admin_Menu extends MI_MainHeader {

    public MI_Setup_and_Admin_Menu(WebDriver driver) {
        super(driver);
    }

    public WebElement Setup_And_Admin_Menu_Element(){

        return driver.findElement(By.xpath("//div[@class='ui five item attached mainmenu menu']//div[@class='ui container']/div[4]"));
    }

    @FindBy(xpath="//div/div[contains(@class,'menu')]/a[@id='menuAdvertiser']") public WebElement Advertiser;

    @FindBy(xpath="//div/div[contains(@class,'menu')]/a[@id='menuDataPartner']") public WebElement DataPartnerMenu;
    @FindBy(xpath="//div/div[contains(@class,'menu')]/a[@id='menuLookalikeModel']") public WebElement LookLikeModelMenu;
}
