package pageObjects.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManagement;
import pageObjectsl.nopCommerce.user.User_HomePageObject;
import pageUI.nopCommerce.admin.AdminLoginPageUI;
import pageUI.nopCommerce.user.UserLoginPageUI;

public class Admin_LoginPageObject extends BasePage{

	WebDriver driver;

	public Admin_LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public Admin_DasboardPageObject clickLoginButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManagement.getAdminDashboardPageObject(driver);
	}


	public void inputToEmailTextBox(String email) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, AdminLoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, AdminLoginPageUI.EMAIL_TEXTBOX, email);

	}


	public void inputToPasswordTextBox(String password) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, password);
	}

	public Admin_DasboardPageObject loginAsAdmin(String email, String password) {
		inputToEmailTextBox(email);
		inputToPasswordTextBox(password);
		return clickLoginButton();
	}

}
