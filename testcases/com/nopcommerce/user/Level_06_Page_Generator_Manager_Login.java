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
import pageObjectsl.nopCommerce.user.User_CustomerInfoPageObject;
import pageObjectsl.nopCommerce.user.User_HomePageObject;
import pageObjectsl.nopCommerce.user.User_LoginPageObject;
import pageObjectsl.nopCommerce.user.User_RegisterPageObject;

public class Level_06_Page_Generator_Manager_Login extends BaseTest {

	private WebDriver driver;
	private String projectPathString = System.getProperty("user.dir");
	BasePage basePage;
	private User_HomePageObject homePage;
	private String firstName, lastname, password, confirmPw, existingEmail, invalidEmail, notRegisteredEmail, wrongPassword;
	private User_RegisterPageObject registerPage;
	private User_LoginPageObject loginPage;
	private User_CustomerInfoPageObject myAccountPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);

		firstName = "Automation";
		lastname = "FC";
		password = "123456";
		confirmPw = "123456";
		wrongPassword = "654321";
		existingEmail = "afc" + generateRandomNumber() + "@mail.vn";
		invalidEmail = "mailafc@FC@fc";
		notRegisteredEmail = "notRegister" + generateRandomNumber() + "@mail.vn";

		homePage = PageGeneratorManagement.getUserHomePageObject(driver);

		System.out.println("HomePage - Step 01: Click to register Link");
		registerPage = homePage.clickToRegisterLink();

		System.out.println("HomePage - Step 02: Input to required field");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastname);
		registerPage.inputToEmailTextBox(existingEmail);
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(confirmPw);

		System.out.println("Pre-Condition - Step 03: Click to register Button");
		registerPage.clickToRegisterButton();

		System.out.println("Pre-Condition - Step 04: Verify success message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		System.out.println("Pre-Condition - Step 05: Continue to HomePage");
		homePage = registerPage.gotoHomePage();

	}

	@Test
	public void TC_01_Login_EmptyData() {
		loginPage = homePage.openLoginPage();
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextBox(), "Please enter your email");

	}

	@Test
	public void TC_02_Login_Invalid_Email() {
		loginPage = homePage.openLoginPage();
		loginPage.inputToEmailTextBox(invalidEmail);

		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextBox(), "Wrong email");

	}

	@Test
	public void TC_03_Login_Not_Registered_Email() {
		loginPage = homePage.openLoginPage();
		loginPage.inputToEmailTextBox(notRegisteredEmail);
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getUnSuccessMessage(), "Login was unsuccessful. Please correct the errors and try again.\n" + "No customer account found");

	}

	@Test
	public void TC_04_Login_No_Password() {
		loginPage = homePage.openLoginPage();
		loginPage.inputToEmailTextBox(existingEmail);
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getUnSuccessMessage(), "Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect");
	}

	@Test
	public void TC_05_Login_Wrong_Password() {
		loginPage = homePage.openLoginPage();
		loginPage.inputToEmailTextBox(existingEmail);
		loginPage.inputToPasswordTextBox(wrongPassword);
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getUnSuccessMessage(), "Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect");
	}

	@Test
	public void TC_06_Login_Success() {
		loginPage = homePage.openLoginPage();
		loginPage.inputToEmailTextBox(existingEmail);
		loginPage.inputToPasswordTextBox(password);
		loginPage.clickLoginButton();
		homePage = new User_HomePageObject(driver);
		assertTrue(homePage.isMyAccountLinkDisplayed());
		myAccountPage = homePage.clickToMyAccountLink();
	}

	public int generateRandomNumber() {
		return new Random().nextInt(9999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
