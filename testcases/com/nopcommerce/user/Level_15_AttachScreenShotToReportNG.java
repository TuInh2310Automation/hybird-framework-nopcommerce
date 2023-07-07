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

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

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
import reportConfig.ExtentManager;

public class Level_15_AttachScreenShotToReportNG extends BaseTest {

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
	public void User_01_Register(Method method) {
		ExtentManager.startTest(method.getName(), "TC01_NewCustomer");
		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 01 : Navigate to register Page");
		registerPage = homePage.clickToRegisterLink();

		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 02 : Enter to FirstName textbox with value is " + firstName);
		registerPage.inputToFirstNameTextBox(firstName);

		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 03 : Enter to LastName textbox with value is " + lastname);
		registerPage.inputToLastNameTextBox(lastname);

		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 04 : Enter to email textbox with value is " + email);
		registerPage.inputToEmailTextBox(email);

		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 05 : Enter to password textbox with value is " + password);
		registerPage.inputToPasswordTextBox(password);

		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 06 : Enter to confirmPw textbox with value is " + confirmPw);
		registerPage.inputToConfirmPasswordTextBox(confirmPw);

		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 07: Click to Register Button");
		registerPage.clickToRegisterButton();

		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 08 : Verify register success");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 09 : Go to HomePage");
		homePage = registerPage.gotoHomePage();
	}

	@Test
	public void User_02_Login() {
		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 01 : Navigate to Login Page");
		loginPage = homePage.openLoginPage();
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 02 : email  to FirstName textbox with value is " + email);
		loginPage.inputToEmailTextBox(email);
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 03 : Enter to password textbox with value is " + password);
		loginPage.inputToPasswordTextBox(password);
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 04: Click to Login Button");
		homePage = loginPage.clickLoginButton();
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 05: Verify 'MyAccount' link is display");
		assertTrue(homePage.isMyAccountLinkDisplayed());

		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 06: Navigate to 'My Account' Page");
		customerInfoPage = homePage.clickToMyAccountLink();
		
		ExtentManager.getTest().log(LogStatus.INFO, "Register - Step 07: Verify  'My Account' Page is displayed");
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
