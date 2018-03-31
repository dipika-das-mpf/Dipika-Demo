package com.mplatform.framework.model.minsights;

import com.mplatform.framework.model.ModelBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MI_LoginPageModel extends ModelBase {

	public MI_LoginPageModel(WebDriver driver) {
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
	
	
}
