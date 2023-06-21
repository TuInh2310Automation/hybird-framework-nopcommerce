package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.HomePageUI;
import pageUI.LoginPageUI;
import pageUI.RegisterPageUI;

public class LoginPageObject extends BasePage{

	WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void clickLoginButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
	}

	public String getErrorMessageAtEmailTextBox() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, LoginPageUI.EMAIL_ERROR_MESSAGE);
	}

	public void inputToEmailTextBox(String email) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, email);
		
	}

	public String getUnSuccessMessage() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, LoginPageUI.UNSUCCESS_MESSAGE);
		return getElementText(driver, LoginPageUI.UNSUCCESS_MESSAGE);
	}

	public void inputToPasswordTextBox(String password) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
	}

}
