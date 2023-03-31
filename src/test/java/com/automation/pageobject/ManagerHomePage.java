package com.automation.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ManagerHomePage {
	
WebDriver ldriver;
	
	public ManagerHomePage(WebDriver rdriver)
	{
		ldriver=rdriver;
		PageFactory.initElements(rdriver, this);
	}
	//a[text()='Log out']


	@FindBy(xpath="//tr[@class='heading3']//td[1]")
	@CacheLookup
	WebElement managerid;

	@FindBy(xpath="//a[text()='Log out']")
	@CacheLookup
	WebElement logout;
	
	
	
	public String getManagerId()
	{
		return managerid.getText();
	}
	
	public void clickOnLogout()
	{
		logout.click();
	}
}
