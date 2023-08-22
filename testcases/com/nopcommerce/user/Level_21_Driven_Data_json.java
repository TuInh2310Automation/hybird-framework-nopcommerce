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

import com.nopcommerce.data.UserDataMapper;

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

public class Level_21_Driven_Data_json extends BaseTest {

	private WebDriver driver;

	BasePage basePage;
	private User_HomePageObject homePage;
	private String firstName, lastname, password, confirmPw, email, date, month, year;
	private User_RegisterPageObject registerPage;
	private User_LoginPageObject loginPage;
	private User_CustomerInfoPageObject customerInfoPage;
	User_RewardPointsPageObject rewardPointsPage;
	UserDataMapper userData;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriverLocal(browserName, appUrl);
		userData = UserDataMapper.getUserData();
		firstName = userData.getFirstName();
		lastname =userData.getLastName();
		password = userData.getPassword();
		confirmPw = userData.getPassword();
		email = userData.getEnailAddress() + generateRandomNumber() + "@fakemail.vn";
		date = userData.getDate();
		month = userData.getMonth();
		year = userData.getYear();

		homePage = PageGeneratorManagement.getUserHomePageObject(driver);

	}

	@Test
	public void User_01_Register() {
		log.info("Register - Step 01 : Navigate to register Page");
		registerPage = homePage.clickToRegisterLink();

		registerPage.clickToRadioButtonByLabel(driver, "Female");
		log.info("Register - Step 02 : Enter to FirstName textbox with value is " + firstName);
		registerPage.inputTextBoxById(driver, "FirstName", firstName);

		log.info("Register - Step 03 : Enter to LastName textbox with value is " + lastname);
		registerPage.inputTextBoxById(driver, "LastName", lastname);

		registerPage.selectToDropDownByName(driver, "DateOfBirthDay", date);
		registerPage.selectToDropDownByName(driver, "DateOfBirthMonth", month);
		registerPage.selectToDropDownByName(driver, "DateOfBirthYear", year);
		log.info("Register - Step 04 : Select birthday " + date + "/" + month + "/" + year);

		registerPage.clickToCheckBoxByLabel(driver, "Newsletter:");
		log.info("Register - Step 04 : Enter to email textbox with value is " + email);
		registerPage.inputTextBoxById(driver, "Email", email);

		log.info("Register - Step 05 : Enter to password textbox with value is " + password);
		registerPage.inputTextBoxById(driver, "Password", password);

		log.info("Register - Step 06 : Enter to confirmPw textbox with value is " + confirmPw);
		registerPage.inputTextBoxById(driver, "ConfirmPassword", confirmPw);

		log.info("Register - Step 07: Click to Register Button");
		registerPage.clickButtonByText(driver, "Register");

		log.info("Register - Step 08 : Verify register success");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		log.info("Register - Step 09 : Go to HomePage");
		homePage = registerPage.gotoHomePage();
	}

	@Test
	public void User_02_Login() {
		log.info("Register - Step 01 : Navigate to Login Page");
		loginPage = homePage.openLoginPage();

		log.info("Register - Step 02 : email  to Email textbox with value is " + email);
		registerPage.inputTextBoxById(driver, "Email", email);

		log.info("Register - Step 03 : Enter to password textbox with value is " + password);
		registerPage.inputTextBoxById(driver, "Password", password);

		log.info("Register - Step 04: Click to Login Button");
		loginPage.clickButtonByText(driver, "Log in");
		PageGeneratorManagement.getUserHomePageObject(driver);

		log.info("Register - Step 05: Verify 'MyAccount' link is display");
		verifyTrue(homePage.isMyAccountLinkDisplayed());

	}

	@Test
	public void User_03_MyAccount() {
		log.info("My Account - Step 01: Navigate to 'My Account' Page");
		customerInfoPage = homePage.clickToMyAccountLink();

		log.info("My Account - Step 02: Verify  'My Account' Page is displayed");
		verifyTrue(customerInfoPage.isCustomerInfoPageDisplay());

		log.info("My Account - Step 03: Verify  'My Account' Page is displayed");
		Assert.assertEquals(customerInfoPage.getTextBoxValueById(driver, "FirstName"), firstName);

		log.info("My Account - Step 04: Verify  lastname is displayed correct");
		Assert.assertEquals(customerInfoPage.getTextBoxValueById(driver, "LastName"), lastname);

		log.info("My Account - Step 05: Verify  email is displayed correct");
		Assert.assertEquals(customerInfoPage.getTextBoxValueById(driver, "Email"), email);
	}

	public int generateRandomNumber() {
		return new Random().nextInt(9999);
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}
