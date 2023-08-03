package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.wordpress.admin.AdminLoginPageUI;


public class AdminLoginPO extends BasePage{
	private WebDriver driver;

	public AdminLoginPO(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToUserNameTextBox(String adminUsername) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, AdminLoginPageUI.USERNAME_TEXTBOX);
		sendkeyToElement(driver, AdminLoginPageUI.USERNAME_TEXTBOX, adminUsername);
		
	}

	public void enterToPasswordTextBox(String adminPw) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, AdminLoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, AdminLoginPageUI.PASSWORD_TEXTBOX, adminPw);
	}

	public AdminDashboardPO clickToLoginButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, AdminLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, AdminLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManagement.getAdminDashboardPage(driver);
	}
}
