package com.mplatform.framework.model.manalytics;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.mplatform.framework.model.ModelBase;

public class MA_AnalyzePageModel extends ModelBase{

	public MA_AnalyzePageModel(WebDriver driver) {
		super(driver);
	}
	
	//===================================Static Objects Identifiers===========================


//  public WebElement cancel(){
//      return driver.findElement(By.xpath("//*[@id='reset-password-form']//button[contains(text(),'Cancel')]"));
//  }

  @FindBy(xpath=".//*[@class='content']//div[@class='header']") 
  public List<WebElement> allReports;
  
  @FindBy(xpath=".//*[@class='content']//div[@class='header' and contains(text(),'61. Summary Report')]") 
  public WebElement summaryReport61;

	
	//===================================Dynamic Objects Identifiers===========================
}
