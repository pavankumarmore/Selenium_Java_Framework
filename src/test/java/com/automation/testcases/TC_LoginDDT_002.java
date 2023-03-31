package com.automation.testcases;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.pageobject.LoginPage;
import com.automation.pageobject.ManagerHomePage;
import com.automation.utilities.ReadExcelFile;




public class TC_LoginDDT_002 extends BaseClass
{

	@Test(dataProvider="LoginData")
	public void loginDDT(String user,String pwd) throws InterruptedException, IOException, AWTException
	{
		LoginPage lp=new LoginPage(driver);

		lp.enterUsername(user);
		logger.info("user name provided");

		lp.enterPassword(pwd);
		logger.info("password provided");

		lp.clicOnLogin();
		logger.info("click on login");

		Thread.sleep(3000);
		ManagerHomePage mg=new ManagerHomePage(driver);


		if(isAlertPresent()==true)
		{
			logger.info("Login failed");
			String wind= driver.getWindowHandle();
			driver.switchTo().window(wind);

			captureScreenShotR(driver,"loginDDT");

			driver.switchTo().alert().accept();//close alert
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);

		}
		else
		{
			captureScreenShotR(driver,"loginDDT");
			Assert.assertTrue(true);
			logger.info("Login passed");
			mg.clickOnLogout();
			Thread.sleep(3000);
			driver.switchTo().alert().accept();//close logout alert
			driver.switchTo().defaultContent();
		}
	}


	public boolean isAlertPresent() //user defined method created to check alert is presetn or not
	{
		try
		{
			driver.switchTo().alert();
			return true;
		}
		catch(NoAlertPresentException e)
		{
			return false;
		}

	}



	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException
	{
		//System.out.println(System.getProperty("user.dir"));
		String fileName = System.getProperty("user.dir") + "\\TestData\\Book1.xlsx";


		int ttlRows = ReadExcelFile.getRowCount(fileName, "Sheet1");
		System.out.println("ttlrows="+ttlRows);
		int ttlColumns = ReadExcelFile.getColCount(fileName, "Sheet1");
		System.out.println("ttlColumns="+ttlColumns);


		String data[][]=new String[ttlRows-1][ttlColumns];

		for(int i=1;i<ttlRows;i++)//rows =1,2
		{
			for(int j=0;j<ttlColumns;j++)//col=0, 1,2
			{

				data[i-1][j]=ReadExcelFile.getCellValue(fileName,"Sheet1", i,j);
			}

		}
		return data;
	}


}
