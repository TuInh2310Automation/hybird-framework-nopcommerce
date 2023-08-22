package com.jquery;

import static org.testng.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManagement;
import pageObjects.jQuery.dataTable.HomePageObject;
import pageObjects.jQuery.dataTable.PageGeneratorManager;
import pageObjectsl.nopCommerce.user.User_AddressPageObject;
import pageObjectsl.nopCommerce.user.User_CustomerInfoPageObject;
import pageObjectsl.nopCommerce.user.User_HomePageObject;
import pageObjectsl.nopCommerce.user.User_LoginPageObject;
import pageObjectsl.nopCommerce.user.User_MyProductReviewsPageObject;
import pageObjectsl.nopCommerce.user.User_RegisterPageObject;
import pageObjectsl.nopCommerce.user.User_RewardPointsPageObject;

public class Level_10_Datatable_DataGrid extends BaseTest {

	private WebDriver driver;
	HomePageObject homePage;
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriverLocal(browserName, appUrl);
		homePage = PageGeneratorManager.getHomePage(driver);
	}

	//@Test
	
	public void Table_01_Paging() {
		homePage.openPagingByPageNumber("10");
		sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActivated("10"));
		
		homePage.openPagingByPageNumber("1");
		sleepInSecond(1);
		Assert.assertTrue(homePage.isPageNumberActivated("1"));
	}

	//@Test
	public void Table_02_EnterToHeader() {
		homePage.refreshCurrentPage(driver);
		
		homePage.enterToHeaderTextboxByLabel("Country", "Argentina");
		homePage.enterToHeaderTextboxByLabel("Females", "338282");
		homePage.enterToHeaderTextboxByLabel("Males", "349238");
		homePage.enterToHeaderTextboxByLabel("Total", "687522");
		
		sleepInSecond(3);
		
	}

	//@Test
	public void Table_03_getListData() {
		homePage.getCountryValueEachRowAtAllPage();
	}

	@Test
	public void Table_04_enter_To_TextBox_At_AnyRow() {
		homePage.clickOnLoadButton();
		sleepInSecond(2);
		// Nhập tại cột nào, dòng nào
//		homePage.enterToTextboxByCollumnNameAtRowNumber( "1" ,"Company", "LG");
//		homePage.enterToTextboxByCollumnNameAtRowNumber( "1" , "Contact Person","haipv");
//		homePage.enterToTextboxByCollumnNameAtRowNumber( "1" , "Order Placed","5");
//		homePage.enterToTextboxByCollumnNameAtRowNumber( "1" , "Member Since","23092013");
//		homePage.selectDropdown("1","Country","Germany");
//		homePage.clickOnCheckBox("1", "NPO?");
		homePage.clickToIconByRowNumber("1", "Remove Current Row");
		sleepInSecond(2);
		homePage.clickToIconByRowNumber("2", "Remove Current Row");
		sleepInSecond(2);
		homePage.clickToIconByRowNumber("3", "Remove Current Row");
	}

	private void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}
