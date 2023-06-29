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

import commons.BasePage;
import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManagement;
import pageObjects.nopCommerce.admin.Admin_DasboardPageObject;
import pageObjects.nopCommerce.admin.Admin_LoginPageObject;
import pageObjectsl.nopCommerce.user.User_AddressPageObject;
import pageObjectsl.nopCommerce.user.User_CustomerInfoPageObject;
import pageObjectsl.nopCommerce.user.User_HomePageObject;
import pageObjectsl.nopCommerce.user.User_LoginPageObject;
import pageObjectsl.nopCommerce.user.User_MyProductReviewsPageObject;
import pageObjectsl.nopCommerce.user.User_RegisterPageObject;
import pageObjectsl.nopCommerce.user.User_RewardPointsPageObject;

public class Level_08_Switch_Role extends BaseTest {

	private WebDriver driver;
	private String projectPathString = System.getProperty("user.dir");
	BasePage basePage;
	private User_HomePageObject userHomePage;
	private String adminPassword, adminEmail, userPassword, userEmail;
	private User_RegisterPageObject userRegisterPageObject;
	private User_LoginPageObject userLoginPage;
	private User_CustomerInfoPageObject userCustomerInfoPage;
	private User_AddressPageObject addressPage;
	private User_MyProductReviewsPageObject myProductReviewsPage;
	private Admin_LoginPageObject admingLoginPage;
	private User_RewardPointsPageObject rewardPointsPage;
	private Admin_DasboardPageObject admin_DasboardPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		userHomePage = PageGeneratorManagement.getUserHomePageObject(driver);

		userPassword = "123456";
		userEmail = "afc123456FC@mail.vn";

		adminPassword = "admin";
		adminEmail = "admin@yourstore.com";
	}

	@Test
	public void Role_01_User_To_Admin() {
		userLoginPage = userHomePage.openLoginPage();
		// login as user role
		userHomePage = userLoginPage.loginAsUser(userEmail, userPassword);
		assertTrue(userHomePage.isMyAccountLinkDisplayed());

		// homepage -> customer info
		userCustomerInfoPage = userHomePage.clickToMyAccountLink();

		// customer info click logout ->home page
		userHomePage = userCustomerInfoPage.clickToLogoutLinkAtUserPage(driver);
		// open admin page
		userLoginPage.openPageUrl(driver, GlobalConstants.ADMIN_DEV_URL);
		admingLoginPage = PageGeneratorManagement.getAdminLoginPagee(driver);

		admin_DasboardPage = admingLoginPage.loginAsAdmin(adminEmail, adminPassword);
		assertTrue(admin_DasboardPage.isDashboardHeaderDisplayed());

		// dashboard page ->click logout -> login page
		admingLoginPage = admin_DasboardPage.clickToLogoutLinkAtAdminPage(driver);
	}

	@Test
	public void Role_02_Admin_To_User() {
		// login page(admin) ->open user url
		admingLoginPage.openPageUrl(driver, GlobalConstants.PORTAL_DEV_URL);
		userHomePage = PageGeneratorManagement.getUserHomePageObject(driver);

		// Home page -> login page user
		userLoginPage = userHomePage.openLoginPage();
		// login as user role
		userHomePage = userLoginPage.loginAsUser(userEmail, userPassword);
		assertTrue(userHomePage.isMyAccountLinkDisplayed());
	}



	public int generateRandomNumber() {
		return new Random().nextInt(9999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
