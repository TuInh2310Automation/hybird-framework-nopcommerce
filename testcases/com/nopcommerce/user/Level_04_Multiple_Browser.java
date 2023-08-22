package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BasePage;
import commons.BaseTest;
import pageObjectsl.nopCommerce.user.User_HomePageObject;
import pageObjectsl.nopCommerce.user.User_RegisterPageObject;

public class Level_04_Multiple_Browser extends BaseTest{

	private WebDriver driver;
	
	private String emailAddress = "afc" + generateRandomNumber() + "@mail.vn";
	BasePage basePage;
	private User_HomePageObject homePage;
	private User_RegisterPageObject registerPage;
	private String firstName = "Automation";
	private String lastname = "FC";
	private String password = "123456";
	private String confirmPw = "123456";

	@Parameters( "browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriverLocal(browserName);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		homePage = new User_HomePageObject(driver);
		registerPage = new User_RegisterPageObject(driver);
	}

	@Test
	public void TC_01_Register_EmptyData() {
		System.out.println("HomePage - Step 01: Click to register Link");
		homePage.clickToRegisterLink();
		System.out.println("Register Page - Step 02: Click to register Button");
		registerPage.clickToRegisterButton();

		System.out.println("Register Page - Step 03: Verify error message displayed");

		Assert.assertEquals(registerPage.getErrorMessageAtFirstNameTextBox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastNameTextBox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextBox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPwTextBox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPwTextBox(), "Password is required.");
	}

	@Test
	public void TC_02_Invalid_Email() {
//		System.out.println("HomePage - Step 01: Click to register Link");
//		homePage.clickToRegisterLink();
//
//		System.out.println("HomePage - Step 02: Input to required field");
//		registerPage.inputToFirstNameTextBox(firstName);
//		registerPage.inputToLastNameTextBox(lastname);
//		registerPage.inputToEmailTextBox("123@457#*");
//		registerPage.inputToPasswordTextBox(password);
//		registerPage.inputToConfirmPasswordTextBox(confirmPw);
//
//		System.out.println("Register Page - Step 03: Click to register Button");
//		registerPage.clickToRegisterButton();
//
//		System.out.println("Register Page - Step 04: Verify error message displayed");
//		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextBox(), "Wrong email");

	}

	@Test
	public void TC_03_Register_Success() {
//		System.out.println("HomePage - Step 01: Click to register Link");
//		homePage.clickToRegisterLink();
//
//		System.out.println("HomePage - Step 02: Input to required field");
//		registerPage.inputToFirstNameTextBox(firstName);
//		registerPage.inputToLastNameTextBox(lastname);
//		registerPage.inputToEmailTextBox(emailAddress);
//		registerPage.inputToPasswordTextBox(password);
//		registerPage.inputToConfirmPasswordTextBox(confirmPw);
//
//		System.out.println("Register Page - Step 03: Click to register Button");
//		registerPage.clickToRegisterButton();
//
//		System.out.println("Register Page - Step 04: Verify success message displayed");
//		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

	}

	//@Test
	public void TC_04_Register_ExistingEmail() {

		System.out.println("HomePage - Step 01: Click to register Link");
		homePage.clickToRegisterLink();

		System.out.println("HomePage - Step 02: Input to required field");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastname);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(confirmPw);

		System.out.println("Register Page - Step 03: Click to register Button");
		registerPage.clickToRegisterButton();

		System.out.println("Register Page - Step 04: Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");

	}

	//@Test
	public void TC_05_Register_Password_Less_Than_6chars() {

		System.out.println("HomePage - Step 01: Click to register Link");
		homePage.clickToRegisterLink();

		System.out.println("HomePage - Step 02: Input to required field");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastname);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPasswordTextBox("123");
		registerPage.inputToConfirmPasswordTextBox("123");

		System.out.println("Register Page - Step 03: Click to register Button");
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getErrorMessageAtPwTextBox(), "Password must meet the following rules:\nmust have at least 6 characters");

	}

	//@Test
	public void TC_06_Register_Invalid_ConfirmPassword() {

		System.out.println("HomePage - Step 01: Click to register Link");
		homePage.clickToRegisterLink();

		System.out.println("HomePage - Step 02: Input to required field");
		registerPage.inputToFirstNameTextBox("Automation");
		registerPage.inputToLastNameTextBox("FC");
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPasswordTextBox("123456");
		registerPage.inputToConfirmPasswordTextBox("654321");

		System.out.println("Register Page - Step 03: Click to register Button");
		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPwTextBox(), "The password and confirmation password do not match.");

	}

	public int generateRandomNumber() {
		return new Random().nextInt(9999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
