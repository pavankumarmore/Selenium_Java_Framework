package com.automation.testcases;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import com.automation.utilities.ReadConfig;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	ReadConfig readConfig = new ReadConfig();

	public String url=readConfig.getBaseUrl();
	public String browser = readConfig.getBrowser();
	public String username = readConfig.getUsername();
	public String password = readConfig.getPassword();


	public  WebDriver driver;
	public  Logger logger;



	@BeforeClass
	public void setup()
	{

		//launch browser
		switch(browser.toLowerCase())
		{
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "msedge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			driver = null;
			break;

		}

		//implicit wait of 10 secs
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


		//for logging
		logger = LogManager.getLogger("automation");

		//open url
		driver.get(url);
		logger.info("url opened");

		driver.manage().window().maximize();

	}



	@AfterClass
	public void tearDown()
	{
		driver.close();
		driver.quit();
	}


	//user method to capture screen shot
	public void captureScreenShotT(WebDriver driver,String tname) throws IOException
	{
		//step1: convert webdriver object to TakesScreenshot interface
		TakesScreenshot screenshot = (TakesScreenshot)driver;

		//step2: call getScreenshotAs method to create image file

		File src = screenshot.getScreenshotAs(OutputType.FILE);

		File dest = new File(System.getProperty("user.dir") + "//screenshots//" + tname + ".png");

		//step3: copy image file to destination
		FileUtils.copyFile(src, dest);
	}

	//screenshot by robot class
	public void captureScreenShotR(WebDriver driver,String tname) throws IOException, AWTException
	{
		Robot robot=new Robot();
		Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle rect=new Rectangle(dimension);
		BufferedImage bufferedImage=robot.createScreenCapture(rect);
		String screenshotPath=System.getProperty("user.dir") + "//screenshots//" + tname + ".png";
		ImageIO.write(bufferedImage,"png",new File(screenshotPath));
	}

}
