package com.nopcommerce.user;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
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

public class Level_18_Custom_Close_Browser extends BaseTest {

	private WebDriver driver;

	BasePage basePage;
	private User_HomePageObject homePage;
	private String password, email;
	private String firstName, lastname, confirmPw;
	private User_LoginPageObject loginPage;
	User_RewardPointsPageObject rewardPointsPage;
	private User_RegisterPageObject registerPage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriverLocal(browserName, appUrl);
		homePage = PageGeneratorManagement.getUserHomePageObject(driver);

		firstName = "Automation";
		lastname = "FC";
		password = "123456";
		confirmPw = "123456";
		email = "afc" + generateRandomNumber() + "@mail.vn";

		log.info("Pre-Condition - Step 01 : Navigate to register Page");
		registerPage = homePage.clickToRegisterLink();

		log.info("Pre-Condition - Step 02 : Enter to FirstName textbox with value is " + firstName);
		registerPage.inputToFirstNameTextBox(firstName);

		log.info("Pre-Condition - Step 03 : Enter to LastName textbox with value is " + lastname);
		registerPage.inputToLastNameTextBox(lastname);

		log.info("Pre-Condition - Step 04 : Enter to email textbox with value is " + email);
		registerPage.inputToEmailTextBox(email);

		log.info("Pre-Condition - Step 05 : Enter to password textbox with value is " + password);
		registerPage.inputToPasswordTextBox(password);

		log.info("Pre-Condition - Step 06 : Enter to confirmPw textbox with value is " + confirmPw);
		registerPage.inputToConfirmPasswordTextBox(confirmPw);

		log.info("Pre-Condition - Step 07: Click to Register Button");
		registerPage.clickToRegisterButton();

		driver = null;
		log.info("Pre-Condition - Step 08 : Verify register success");
		assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed...");

		log.info("Pre-Condition - Step 09 : Go to HomePage");
		homePage = registerPage.gotoHomePage();

		log.info("Condition - Step 10 : Navigate to Login Page");
		loginPage = homePage.openLoginPage();

		log.info("Condition - Step 11 : email  to FirstName textbox with value is " + email);
		loginPage.inputToEmailTextBox(email);

		log.info("Condition - Step 12 : Enter to password textbox with value is " + password);
		loginPage.inputToPasswordTextBox(password);

		log.info("Condition - Step 13: Click to Login Button");
		homePage = loginPage.clickLoginButton();

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

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();
	}

}
