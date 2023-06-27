package pageObjectsl.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManagement;
import pageUI.nopCommerce.user.HomePageUI;

public class User_HomePageObject extends BasePage {

	private WebDriver driver;

	public User_HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public User_RegisterPageObject clickToRegisterLink() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
		return PageGeneratorManagement.getUserRegisterPage(driver);

	}

	public User_LoginPageObject openLoginPage() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver, HomePageUI.LOGIN_LINK);
		return PageGeneratorManagement.getUserLoginPage(driver);

	}

	public boolean isMyAccountLinkDisplayed() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);
		return isElementDisplay(driver, HomePageUI.MY_ACCOUNT_LINK);
	}

	public User_CustomerInfoPageObject clickToMyAccountLink() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManagement.getUserCustomerInfoPage(driver);
	}

}
