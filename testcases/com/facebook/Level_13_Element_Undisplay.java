package com.facebook;

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
import pageObjects.faceBook.LoginPageObject;
import pageObjects.faceBook.PageGeneratorManager;

public class Level_13_Element_Undisplay extends BaseTest {

	private WebDriver driver;

	LoginPageObject loginPage;

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGeneratorManager.getLoginPage(driver);

	}

	@Test
	public void TC_01_Verify_Element_Display() {
		loginPage.clickToCreateNewAccountButton();
		verifyTrue(loginPage.isEmailTextBoxDisplayed());

		// Nếu như mình mong đợi 1 hàm vừa verify, displayed/undisplayed thì ko dc kết hợp wait
		// verify tru - mong đợi confirm email display
		loginPage.enterToEmailTextBox("automationfc@gmail.com");
		sleepInSecond(3);
		verifyTrue(loginPage.isConfirmEmailAddressTextBoxDisplay());

	}

	@Test
	public void TC_02_Verify_Element_UnDisplay_InDOM() {

		loginPage.enterToEmailTextBox("");
		sleepInSecond(3);
		verifyFalse(loginPage.isConfirmEmailAddressTextBoxDisplay());
	}

	@Test
	public void TC_03_Verify_Element_UnDisplay_NotInDOM() {
		loginPage.closeRegisterPopup();
		sleepInSecond(3);
		verifyTrue(loginPage.isConfirmEmailAddressTextBoxUnDisplay());
		
	}

	private void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int generateRandomNumber() {
		return new Random().nextInt(9999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
