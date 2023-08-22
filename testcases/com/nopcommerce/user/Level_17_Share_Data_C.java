package com.nopcommerce.user;

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

import com.nopcommerce.common.Common_01_Register_Cookies;
import com.nopcommerce.common.Common_01_Register_End_User;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManagement;
import pageObjectsl.nopCommerce.user.User_AddressPageObject;
import pageObjectsl.nopCommerce.user.User_CustomerInfoPageObject;
import pageObjectsl.nopCommerce.user.User_HomePageObject;
import pageObjectsl.nopCommerce.user.User_LoginPageObject;
import pageObjectsl.nopCommerce.user.User_MyProductReviewsPageObject;
import pageObjectsl.nopCommerce.user.User_RegisterPageObject;
import pageObjectsl.nopCommerce.user.User_RewardPointsPageObject;

public class Level_17_Share_Data_C extends BaseTest {

	private WebDriver driver;

	BasePage basePage;
	private User_HomePageObject homePage;
	private User_LoginPageObject loginPage;
	User_RewardPointsPageObject rewardPointsPage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriverLocal(browserName, appUrl);
		homePage = PageGeneratorManagement.getUserHomePageObject(driver);

		log.info("Precondition - Step 01 : Navigate to Login Page");
		loginPage = homePage.openLoginPage();

		log.info("Precondition - Step 02 : Set cookies and reload page");
		loginPage.setCookies(driver, Common_01_Register_Cookies.logCookies);
		loginPage.refreshCurrentPage(driver);

		log.info("Precondition - Step 04: Click to Login Button");
		homePage = loginPage.clickLoginButton();

		log.info("Precondition - Step 05: Verify 'MyAccount' link is display");
		verifyTrue(homePage.isMyAccountLinkDisplayed());

		
	}

	@Test
	public void Search_01_EmptyData() {
	}

	@Test
	public void Search_02_Relative_Product_Name() {
	}

	@Test
	public void Search_03_Absolute_Product_Name() {
	}

	@Test
	public void Search_04_Parent_Category() {
	}

	@Test
	public void Search_05_Absolute_Product_Name() {
	}

	@Test
	public void Search_06_Absolute_Product_Name() {
	}

	public int generateRandomNumber() {
		return new Random().nextInt(9999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
