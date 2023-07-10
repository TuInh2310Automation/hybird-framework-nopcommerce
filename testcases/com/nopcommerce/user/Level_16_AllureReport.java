package com.nopcommerce.user;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;
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

import com.aventstack.extentreports.Status;

//import com.relevantcodes.extentreports.ExtentTest;
//import com.relevantcodes.extentreports.LogStatus;

import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManagement;
import io.qameta.allure.Description;
import pageObjectsl.nopCommerce.user.User_AddressPageObject;
import pageObjectsl.nopCommerce.user.User_CustomerInfoPageObject;
import pageObjectsl.nopCommerce.user.User_HomePageObject;
import pageObjectsl.nopCommerce.user.User_LoginPageObject;
import pageObjectsl.nopCommerce.user.User_MyProductReviewsPageObject;
import pageObjectsl.nopCommerce.user.User_RegisterPageObject;
import pageObjectsl.nopCommerce.user.User_RewardPointsPageObject;
//import reportConfig.ExtentManager;
import reportConfig.ExtentTestManager;

public class Level_16_AllureReport extends BaseTest {

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

	@Description("Register to system")
	@Test
	public void User_01_Register(Method method) {
	
		registerPage = homePage.clickToRegisterLink();

	
		registerPage.inputToFirstNameTextBox(firstName);

		
		registerPage.inputToLastNameTextBox(lastname);

		registerPage.inputToEmailTextBox(email);

		registerPage.inputToPasswordTextBox(password);

		registerPage.inputToConfirmPasswordTextBox(confirmPw);

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		homePage = registerPage.gotoHomePage();
	}

	@Description("Login to system")
	@Test
	public void User_02_Login(Method method) {
	
		loginPage = homePage.openLoginPage();
		
		
		loginPage.inputToEmailTextBox(email);
		
		
		loginPage.inputToPasswordTextBox(password);
		
		
		homePage = loginPage.clickLoginButton();
		
		
		assertTrue(homePage.isMyAccountLinkDisplayed());

		
		customerInfoPage = homePage.clickToMyAccountLink();
		
		
		assertFalse(customerInfoPage.isCustomerInfoPageDisplay());
	}

	public int generateRandomNumber() {
		return new Random().nextInt(9999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
