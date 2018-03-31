package com.mplatform.framework.model.manalytics;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.mplatform.framework.model.ModelBase;

public class MA_61SummaryReportPageModel extends ModelBase{

	public MA_61SummaryReportPageModel(WebDriver driver) {
		super(driver);
	}
	
	//===================================Static Objects Identifiers===========================
	
	@FindBy(xpath=".//div[contains(text(),'Create 61. Summary Report')]")
	public WebElement createReportPageHeader;
	
	@FindBy(xpath=".//input[@name='reportName']")
	public WebElement reportNameInputBox;
	
	@FindBy(xpath=".//input[@name='reportFilename']")
	public WebElement outputFileName;

	@FindBy(xpath=".//input[@name='recipient_email_addresses']")
	public WebElement recipientEmail;
	
	@FindBy(id="lastdayinput")
	public WebElement lastDaysInput;
	
	@FindBy(id="lastmonthinput")
	public WebElement lastMonthInput;
	
	@FindBy(xpath=".//*[@id='dataSourceIds-row']/div/div/a[@class='select-all']")
	public WebElement selectAllDataResource;
	
	@FindBy(id="lastmonthinput")
	public WebElement timeZoneSelectBox;
	
	@FindBy(xpath=".//*[@class='ui bottom attached tab segment active']//*[contains(text(),'Time Zone')]/..//i[@class='dropdown icon']")
	public WebElement timeZoneBox;
	
	@FindBy(xpath=".//*[contains(text(),'Week Starting Day')]/..//i[@class='dropdown icon']")
	public WebElement weekStartingDayBox;
	
	@FindBy(name="dayOfWeekStart")
	public WebElement weekStartingDay;
	
	@FindBy(xpath="(.//*[contains(text(),'Agencies')]/..//input[@class='filter-mainlist'])[1]")
	public WebElement searchAgenciesFilter;
	
	@FindBy(xpath=".//*[@id='filterItemSelected_3']//div[@class='field selectLeft']/a[@class='select-all']")
	public WebElement selectAllFromLeftagencies;
	
	@FindBy(xpath=".//*[@id='filterItemSelected_3']//i[@class='big angle right icon addSelection']")
	public WebElement addAllSelectedFromLeftAgencies;
	
	@FindBy(xpath=".//select[@name='agencyIds-list']/option") 
	public List<WebElement> listOfSelectedAgencies;
	
	@FindBy(xpath=".//*[@id='filterItemSelected_4']//div[@class='field selectLeft']/a[@class='select-all']")
	public WebElement selectAllFromLeftAdvertisers;
	
	@FindBy(xpath=".//*[@id='filterItemSelected_4']//i[@class='big angle right icon addSelection']")
	public WebElement addAllSelectedFromLeftAdvertisers;
	
	@FindBy(xpath=".//*[@id='filterItemSelected_4']//select[@name='advertiserIds-list']/option") 
	public List<WebElement> listOfSelectedAdvertisers;
	
	@FindBy(xpath=".//*[@id='dateGroupingLevel-row']//i[@class='dropdown icon']") 
	public WebElement reportDisplayedOption;
	
	@FindBy(id="save-btn") 
	public WebElement saveReport;
	
//	private WebElement outputFileType;
//	public WebElement outputFileType(){
//		outputFileType = driver.findElement(By.xpath(".//*[@value='csv']"));
//		return outputFileType;
//	}
	
	
	//===================================Dynamic Objects Identifiers===========================
	
	public String _outputFileType = ".//*[@value='EXTERNALDATA']";
	public String _scheduling = ".//a[contains(text(),'EXTERNALDATA')]";
	public String _runStatus = ".//*[contains(text(),'EXTERNALDATA')]/../input";
	public String _dateRangeSelection = ".//a[contains(text(),'EXTERNALDATA')]";
	public String _dataSources = ".//*[@id='dataSourceIds-row']/div/div//*[contains(text(),'EXTERNALDATA')]";
	public String _relativeDateRangeSelection = ".//*[@value='EXTERNALDATA']";
	public String _timeZoneType = ".//*[@class='ui bottom attached tab segment active']//div[@class='menu transition visible']/div[contains(text(),'EXTERNALDATA')]";
	public String _weekStartingDay = ".//*[contains(text(),'Week Starting Day')]/..//div[@class='menu transition visible']/*[contains(text(),'EXTERNALDATA')]";
	public String _selectedAgencies = ".//*[@id='filterItemSelected_2']//option[contains(text(),'EXTERNALDATA')]";
	public String _reportDisplayOption = ".//*[@id='dateGroupingLevel-row']//div[@class='menu transition visible']/div[contains(text(),'EXTERNALDATA')]";
	public String _groupByAttributes = ".//*[@id='groupBy-row']//*[contains(text(),'EXTERNALDATA')]";
	public String _selectedMetricsForReports = ".//*[@id='metrics-row']//*[contains(text(),'EXTERNALDATA')]";
}
