package com.swaglabs;

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
import pageObjects.swagLabs.LoginPageObject;
import pageObjects.swagLabs.ProductPageObject;
import pageObjectsl.nopCommerce.user.User_AddressPageObject;
import pageObjectsl.nopCommerce.user.User_CustomerInfoPageObject;
import pageObjectsl.nopCommerce.user.User_HomePageObject;
import pageObjectsl.nopCommerce.user.User_LoginPageObject;
import pageObjectsl.nopCommerce.user.User_MyProductReviewsPageObject;
import pageObjectsl.nopCommerce.user.User_RegisterPageObject;
import pageObjectsl.nopCommerce.user.User_RewardPointsPageObject;

public class Level_20_Sort_Asc_Desc extends BaseTest {

	private WebDriver driver;
	private LoginPageObject loginPage;
	private ProductPageObject productPage;

	@Parameters({ "browser", "appUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String swaglabUrl) {
		driver = getBrowserDriverLocal(browserName, swaglabUrl);
		loginPage = pageObjects.swagLabs.PageGeneratorManagement.getLoginPage(driver);
		loginPage.enterToUsernameTextBox("standard_user");
		loginPage.enterToPasswordTextBox("secret_sauce");
		productPage = loginPage.clicToLoginButton();
	}

	@Test
	public void Sort_01_Name() {
		//ascending 
		productPage.selectItemInProductSortDropdown("Name (A to Z)");
		productPage.sleepInSecond(3);
		assertTrue(productPage.isProductNameSortByAscending());
		//descending
		productPage.selectItemInProductSortDropdown("Name (Z to A)");
		productPage.sleepInSecond(3);
		assertTrue(productPage.isProductNameSortByDescending());
	
	}

	@Test
	public void Sort_02_Price() {
		// ascending
		productPage.selectItemInProductSortDropdown("Price (low to high)");
		productPage.sleepInSecond(3);
		assertTrue(productPage.isProductPriceSortByAscending());
		// descending
		productPage.selectItemInProductSortDropdown("Price (high to low)");
		productPage.sleepInSecond(3);
		assertTrue(productPage.isProductPriceSortByDescending());
	}

	public int generateRandomNumber() {
		return new Random().nextInt(9999);
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		// driver.quit();
		closeBrowserDriver();
	}

}
