package com.nopcommerce.common;

import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.BasePage;
import commons.BaseTest;
import commons.PageGeneratorManagement;
import pageObjectsl.nopCommerce.user.User_CustomerInfoPageObject;
import pageObjectsl.nopCommerce.user.User_HomePageObject;
import pageObjectsl.nopCommerce.user.User_LoginPageObject;
import pageObjectsl.nopCommerce.user.User_RegisterPageObject;
import pageObjectsl.nopCommerce.user.User_RewardPointsPageObject;

public class Common_01_Register_End_User extends BaseTest {

	private WebDriver driver;

	BasePage basePage;
	private User_HomePageObject homePage;
	private String firstName, lastname, confirmPw;
	public static String email, password;
	private User_RegisterPageObject registerPage;
	User_RewardPointsPageObject rewardPointsPage;

	@Parameters( "browser")
	@BeforeTest(description = "Create new common User for all classes test")
	public void User_01_Register(String browserName) {
		firstName = "Automation";
		lastname = "FC";
		password = "123456";
		confirmPw = "123456";
		driver = getBrowserDriverLocal(browserName);
		homePage = PageGeneratorManagement.getUserHomePageObject(driver);
		email = "afc" + generateRandomNumber() + "@mail.vn";
		log.info("PreCondition - Step 01 : Navigate to register Page");
		registerPage = homePage.clickToRegisterLink();

		log.info("PreCondition - Step 02 : Enter to FirstName textbox with value is " + firstName);
		registerPage.inputToFirstNameTextBox(firstName);

		log.info("PreCondition - Step 03 : Enter to LastName textbox with value is " + lastname);
		registerPage.inputToLastNameTextBox(lastname);

		log.info("PreCondition - Step 04 : Enter to email textbox with value is " + email);
		registerPage.inputToEmailTextBox(email);

		log.info("PreCondition - Step 05 : Enter to password textbox with value is " + password);
		registerPage.inputToPasswordTextBox(password);

		log.info("PreCondition - Step 06 : Enter to confirmPw textbox with value is " + confirmPw);
		registerPage.inputToConfirmPasswordTextBox(confirmPw);

		log.info("PreCondition - Step 07: Click to Register Button");
		registerPage.clickToRegisterButton();

		log.info("PreCondition - Step 08 : Verify register success");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		log.info("PreCondition - Step 09 : Go to HomePage");
		homePage = registerPage.gotoHomePage();
		
		driver.quit();
	}

	public int generateRandomNumber() {
		return new Random().nextInt(9999);
	}

}
