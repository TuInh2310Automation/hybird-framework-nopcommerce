package pageObjects.swagLabs;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.swagLab.LoginPageUI;

public class LoginPageObject extends BasePage {
	WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToUsernameTextBox(String username) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, LoginPageUI.USERNAME_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.USERNAME_TEXTBOX, username);

	}

	public void enterToPasswordTextBox(String password) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, LoginPageUI.PW_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PW_TEXTBOX, password);
	}

	public ProductPageObject clicToLoginButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManagement.getProductPage(driver);
	}

}
