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

public class Level_12_Assert_Verify extends BaseTest {

	private WebDriver driver;
	private String projectPathString = System.getProperty("user.dir");
	BasePage basePage;
	private User_HomePageObject homePage;
	private String firstName, lastname, password, confirmPw, email;
	private User_RegisterPageObject registerPage;
	private User_LoginPageObject loginPage;
	private User_CustomerInfoPageObject customerInfoPage;
	private User_AddressPageObject addressPage;
	private User_MyProductReviewsPageObject myProductReviewsPage;
	User_RewardPointsPageObject rewardPointsPage;
	
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriverLocal(browserName, appUrl);

		firstName = "Automation";
		lastname = "FC";
		password = "123456";
		confirmPw = "123456";
		email = "afc" + generateRandomNumber() + "@mail.vn";

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
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		homePage = registerPage.gotoHomePage();
		
		loginPage = homePage.openLoginPage();
		loginPage.inputToEmailTextBox(email);
		loginPage.inputToPasswordTextBox(password);
		loginPage.clickLoginButton();
		homePage = new User_HomePageObject(driver);
		verifyFalse(homePage.isMyAccountLinkDisplayed());
		
		customerInfoPage = homePage.clickToMyAccountLink();
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
