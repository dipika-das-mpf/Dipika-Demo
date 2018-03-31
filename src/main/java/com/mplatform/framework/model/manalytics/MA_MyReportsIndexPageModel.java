package com.mplatform.framework.model.manalytics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.mplatform.framework.model.ModelBase;

public class MA_MyReportsIndexPageModel extends ModelBase{

    public MA_MyReportsIndexPageModel(WebDriver driver) {
        super(driver);
    }


    //===================================Static Objects Identifiers===========================


//    public WebElement cancel(){
//        return driver.findElement(By.xpath("//*[@id='reset-password-form']//button[contains(text(),'Cancel')]"));
//    }

    @FindBy(xpath=".//*[@id='content']//a[@title='Create new report']") 
    public WebElement createReport;
    
    @FindBy(xpath=".//*[@id='nav-view']//i[@class='content icon large']") 
    public WebElement hamburgerMenu;
    
    @FindBy(xpath=".//*[@id='nav-view']//a[@title='Analyze']") 
    public WebElement analyzeNavMenu;
    
    
    
    
  //===================================Dynamic Objects Identifiers===========================
    
    
}
