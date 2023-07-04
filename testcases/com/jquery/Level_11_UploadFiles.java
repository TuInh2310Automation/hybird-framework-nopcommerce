package com.jquery;

import org.apache.hc.core5.http.nio.entity.StringAsyncEntityProducer;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObjects.jQuery.uploadFile.HomePageObject;
import pageObjects.jQuery.uploadFile.PageGeneratorManager;

public class Level_11_UploadFiles extends BaseTest {

	private WebDriver driver;
	HomePageObject homePage;
	String aoFileName = "ao.jpg";
	String quanFileName = "quan.jpg";
	String monFileName = "mon.jpg";
	String[] multipleFiles = { aoFileName, quanFileName, monFileName };

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	@Test

	public void Upload_01_OneFile_PerTime() {
		// Step 01 Load Single File
		homePage.uploadMultipleFiles(driver, aoFileName);

		// step 02 : verify single file loaded success
		Assert.assertTrue(homePage.isFileLoadedByName(aoFileName));

		// step 3: click start button
		homePage.clickToStartButton();
		sleepInSecond(2);
		// step 4: Verify single file link uploaded success
		Assert.assertTrue(homePage.isFileUpLoadedByName(aoFileName));
		// step 5: Verify single file image uploaded success
		Assert.assertTrue(homePage.isFileImageUpLoadedByName(aoFileName));
	}

	@Test
	public void Upload_02_Multiples_PerTime() {
		homePage.uploadMultipleFiles(driver, multipleFiles);
		
		Assert.assertTrue(homePage.isFileLoadedByName(aoFileName));
		Assert.assertTrue(homePage.isFileLoadedByName(quanFileName));
		Assert.assertTrue(homePage.isFileLoadedByName(monFileName));
		
		homePage.clickToStartButton();
		
		Assert.assertTrue(homePage.isFileUpLoadedByName(aoFileName));
		Assert.assertTrue(homePage.isFileUpLoadedByName(quanFileName));
		Assert.assertTrue(homePage.isFileUpLoadedByName(monFileName));
		
		Assert.assertTrue(homePage.isFileImageUpLoadedByName(aoFileName));
		Assert.assertTrue(homePage.isFileImageUpLoadedByName(quanFileName));
		Assert.assertTrue(homePage.isFileImageUpLoadedByName(monFileName));

	}

	private void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}
