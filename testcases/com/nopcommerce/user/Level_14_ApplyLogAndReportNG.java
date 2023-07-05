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
import commons.PageGeneratorManagement;
import pageObjectsl.nopCommerce.user.User_AddressPageObject;
import pageObjectsl.nopCommerce.user.User_CustomerInfoPageObject;
import pageObjectsl.nopCommerce.user.User_HomePageObject;
import pageObjectsl.nopCommerce.user.User_LoginPageObject;
import pageObjectsl.nopCommerce.user.User_MyProductReviewsPageObject;
import pageObjectsl.nopCommerce.user.User_RegisterPageObject;
import pageObjectsl.nopCommerce.user.User_RewardPointsPageObject;

public class Level_14_ApplyLogAndReportNG extends BaseTest {

	private WebDriver driver;

	BasePage basePage;
	private User_HomePageObject homePage;
	private String firstName, lastname, password, confirmPw, email;
	private User_RegisterPageObject registerPage;
	private User_LoginPageObject loginPage;
	private User_CustomerInfoPageObject customerInfoPage;
	User_RewardPointsPageObject rewardPointsPage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);

		firstName = "Automation";
		lastname = "FC";
		password = "123456";
		confirmPw = "123456";
		email = "afc" + generateRandomNumber() + "@mail.vn";

		homePage = PageGeneratorManagement.getUserHomePageObject(driver);

	}

	@Test
	public void User_01_Register() {
		log.info("Register - Step 01 : Navigate to register Page");
		registerPage = homePage.clickToRegisterLink();

		log.info("Register - Step 02 : Enter to FirstName textbox with value is " + firstName);
		registerPage.inputToFirstNameTextBox(firstName);

		log.info("Register - Step 03 : Enter to LastName textbox with value is " + lastname);
		registerPage.inputToLastNameTextBox(lastname);

		log.info("Register - Step 04 : Enter to email textbox with value is " + email);
		registerPage.inputToEmailTextBox(email);

		log.info("Register - Step 05 : Enter to password textbox with value is " + password);
		registerPage.inputToPasswordTextBox(password);

		log.info("Register - Step 06 : Enter to confirmPw textbox with value is " + confirmPw);
		registerPage.inputToConfirmPasswordTextBox(confirmPw);

		log.info("Register - Step 07: Click to Register Button");
		registerPage.clickToRegisterButton();

		log.info("Register - Step 08 : Verify register success");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		log.info("Register - Step 09 : Go to HomePage");
		homePage = registerPage.gotoHomePage();
	}

	@Test
	public void User_02_Login() {
		log.info("Register - Step 01 : Navigate to Login Page");
		loginPage = homePage.openLoginPage();
		
		log.info("Register - Step 02 : email  to FirstName textbox with value is " + email);
		loginPage.inputToEmailTextBox(email);
		
		log.info("Register - Step 03 : Enter to password textbox with value is " + password);
		loginPage.inputToPasswordTextBox(password);
		
		log.info("Register - Step 04: Click to Login Button");
		homePage = loginPage.clickLoginButton();
		
		log.info("Register - Step 05: Verify 'MyAccount' link is display");
		verifyTrue(homePage.isMyAccountLinkDisplayed());

		log.info("Register - Step 06: Navigate to 'My Account' Page");
		customerInfoPage = homePage.clickToMyAccountLink();
		
		log.info("Register - Step 07: Verify  'My Account' Page is displayed");
		verifyTrue(customerInfoPage.isCustomerInfoPageDisplay());
	}

	public int generateRandomNumber() {
		return new Random().nextInt(9999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
