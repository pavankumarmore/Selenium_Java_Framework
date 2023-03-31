package com.automation.testcases;


import java.io.IOException;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.pageobject.LoginPage;
import com.automation.pageobject.ManagerHomePage;


public class TC_LoginTest_001 extends BaseClass
{

	@Test
	public void loginTest() throws IOException 
	{

		logger.info("URL is opened");

		LoginPage lp=new LoginPage(driver);

		lp.enterUsername(username);
		logger.info("Entered username");

		lp.enterPassword(password);
		logger.info("Entered password");

		lp.clicOnLogin();
		logger.info("click on login button");

		ManagerHomePage mg=new ManagerHomePage(driver);

		if(mg.getManagerId().equals("Manger Id : mngr484427"))
		{
			Assert.assertTrue(true);
			logger.info("Login test passed");
		}
		else
		{
			//captureScreen(driver,"loginTest");
			Assert.assertTrue(false);
			logger.info("Login test failed");
		}

	}
}
