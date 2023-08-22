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

public class Level_07_Switch_Page_UI extends BaseTest {

	private WebDriver driver;
	private String projectPathString = System.getProperty("user.dir");
	BasePage basePage;
	private User_HomePageObject homePage;
	private String firstName, lastname, password, confirmPw, email, invalidEmail, notRegisteredEmail, wrongPassword;
	private User_RegisterPageObject registerPage;
	private User_LoginPageObject loginPage;
	private User_CustomerInfoPageObject customerInfoPage;
	private User_AddressPageObject addressPage;
	private User_MyProductReviewsPageObject myProductReviewsPage;
	User_RewardPointsPageObject rewardPointsPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriverLocal(browserName);

		firstName = "Automation";
		lastname = "FC";
		password = "123456";
		confirmPw = "123456";
		wrongPassword = "654321";
		email = "afc" + generateRandomNumber() + "@mail.vn";
		invalidEmail = "mailafc@FC@fc";
		notRegisteredEmail = "notRegister" + generateRandomNumber() + "@mail.vn";

		homePage = PageGeneratorManagement.getUserHomePageObject(driver);

		

	}

	@Test
	public void User_01_Register() {
		
		registerPage = homePage.clickToRegisterLink();
		System.out.println("HomePage - Step 02: Input to required field");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastname);
		registerPage.inputToEmailTextBox(email);
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(confirmPw);

		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		homePage = registerPage.gotoHomePage();
	}

	@Test
	public void User_02_Login() {
		loginPage = homePage.openLoginPage();
		loginPage.inputToEmailTextBox(email);
		loginPage.inputToPasswordTextBox(password);
		loginPage.clickLoginButton();
		homePage = new User_HomePageObject(driver);
		assertTrue(homePage.isMyAccountLinkDisplayed());
		
	}

	@Test
	public void User_03_MyAccount() {
		customerInfoPage = homePage.clickToMyAccountLink();
		assertTrue(customerInfoPage.isCustomerInfoPageDisplay());
	}

	@Test
	public void User_04_Switch_Page() {
		//customer info -> address
		addressPage = customerInfoPage.openAddressPage(driver);
		
		//address -> my product review
		myProductReviewsPage = addressPage.openMyProductReviewsPage(driver);
		// my product review -> reward point
		rewardPointsPage = myProductReviewsPage.openRewardPointPage(driver);
		//reward point -> Address
		addressPage = rewardPointsPage.openAddressPage(driver);
		// address ->reward point
		rewardPointsPage = addressPage.openRewardPointPage(driver);
		// reward point -> my product review
		myProductReviewsPage = rewardPointsPage.openMyProductReviewsPage(driver);
		//my product review -> address
		addressPage = myProductReviewsPage.openAddressPage(driver);
		
		customerInfoPage = addressPage.openCustomerInfoPage(driver);
	}

	@Test
	public void User_05_Switch_Role() {
		
	}

	

	public int generateRandomNumber() {
		return new Random().nextInt(9999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
