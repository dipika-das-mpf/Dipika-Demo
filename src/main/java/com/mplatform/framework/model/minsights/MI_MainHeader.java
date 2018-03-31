package com.mplatform.framework.model.minsights;

import com.mplatform.framework.model.ModelBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by mohamed.abdulkadar on 7/6/2017.
 */
public class MI_MainHeader extends ModelBase {

    public  MI_MainHeader (WebDriver driver) {
        super(driver);
    }

    @FindBy(how = How.XPATH, using="//div[@class='ui search dropdown item js-account-dd']/span")
    public WebElement AccountField;

    @FindBy(how = How.XPATH, using="//div[contains(@class,'ui search dropdown item js-account-dd')]//input[@class='search']")
    public WebElement AccountSelect_Icon;

    public String _AccountList="//div[contains(@class,'ui search dropdown item js-account-dd')]//div[contains(@class,'ui menu transition visible')]/div[contains(text(),'EXTERNALDATA')]";

    /*Close Icon*/
    @FindBy(how = How.XPATH, using="//div[contains(@class,'turbine2 ui')]/i[@class='close icon']")
    public WebElement CrossIcon_Close_button;
}
