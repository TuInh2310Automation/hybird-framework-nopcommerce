package pageObjectsl.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManagement;
import io.qameta.allure.Step;
import pageUI.nopCommerce.user.HomePageUI;
import pageUI.nopCommerce.user.UserLoginPageUI;
import pageUI.nopCommerce.user.RegisterPageUI;

public class User_LoginPageObject extends BasePage {

	WebDriver driver;

	public User_LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public User_HomePageObject clickLoginButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManagement.getUserHomePageObject(driver);
	}

	public String getErrorMessageAtEmailTextBox() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, UserLoginPageUI.EMAIL_ERROR_MESSAGE);
	}

	@Step("Enter to email textbox with value is {0}")
	public void inputToEmailTextBox(String email) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, UserLoginPageUI.EMAIL_TEXTBOX, email);

	}

	public String getUnSuccessMessage() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, UserLoginPageUI.UNSUCCESS_MESSAGE);
		return getElementText(driver, UserLoginPageUI.UNSUCCESS_MESSAGE);
	}

	@Step("Enter to password textbox with value is {0}")
	public void inputToPasswordTextBox(String password) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, UserLoginPageUI.PASSWORD_TEXTBOX, password);
	}

	public User_HomePageObject loginAsUser(String email, String password) {
		inputToEmailTextBox(email);
		inputToPasswordTextBox(password);
		return clickLoginButton();
	}

}
