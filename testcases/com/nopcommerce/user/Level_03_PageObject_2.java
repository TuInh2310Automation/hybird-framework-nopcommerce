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
import org.testng.annotations.Test;

import commons.BasePage;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class Level_03_PageObject_2 {

	private WebDriver driver;
	private String projectPathString = System.getProperty("user.dir");
	BasePage basePage;
	private HomePageObject homePage;
	private String firstName, lastname, password, confirmPw, existingEmail, invalidEmail, notRegisteredEmail, wrongPassword;
	private RegisterPageObject registerPage;
	private LoginPageObject loginPage;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPathString + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");

		homePage = new HomePageObject(driver);
		registerPage = new RegisterPageObject(driver);

		firstName = "Automation";
		lastname = "FC";
		password = "123456";
		confirmPw = "123456";
		wrongPassword = "654321";
		existingEmail = "afc" + generateRandomNumber() + "@mail.vn";
		invalidEmail = "mailafc@FC@fc";
		notRegisteredEmail = "notRegister" + generateRandomNumber() + "@mail.vn";

		System.out.println("HomePage - Step 01: Click to register Link");
		homePage.clickToRegisterLink();

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
		registerPage.gotoHomePage();

		// bussiness quay về trang HomePage
		homePage = new HomePageObject(driver);
	}

	@Test
	public void TC_01_Login_EmptyData() {
		homePage.clickToLoginLink();

		loginPage = new LoginPageObject(driver);
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextBox(), "Please enter your email");

	}

	@Test
	public void TC_02_Login_Invalid_Email() {
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTextBox(invalidEmail);

		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextBox(), "Wrong email");

	}

	@Test
	public void TC_03_Login_Not_Registered_Email() {
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTextBox(notRegisteredEmail);
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getUnSuccessMessage(), "Login was unsuccessful. Please correct the errors and try again.\n" + "No customer account found");

	}

	@Test
	public void TC_04_Login_No_Password() {
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTextBox(existingEmail);
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getUnSuccessMessage(), "Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect");
	}

	@Test
	public void TC_05_Login_Wrong_Password() {
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTextBox(existingEmail);
		loginPage.inputToPasswordTextBox(wrongPassword);
		loginPage.clickLoginButton();
		Assert.assertEquals(loginPage.getUnSuccessMessage(), "Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect");
	}

	 @Test
	public void TC_06_Register_Invalid_ConfirmPassword() {
		homePage.clickToLoginLink();
		loginPage = new LoginPageObject(driver);
		loginPage.inputToEmailTextBox(existingEmail);
		loginPage.inputToPasswordTextBox(password);
		loginPage.clickLoginButton();
		homePage = new HomePageObject(driver);
		assertTrue(homePage.isMyAccountLinkDisplayed());
	}

	public int generateRandomNumber() {
		return new Random().nextInt(9999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
