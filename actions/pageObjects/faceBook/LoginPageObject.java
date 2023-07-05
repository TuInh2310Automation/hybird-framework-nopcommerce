package pageObjects.faceBook;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUI.facebook.LoginPageUI;
import pageUI.jQuery.uploadFiles.HomePageUI;

public class LoginPageObject extends BasePage {
	WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
	}

	private void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickToCreateNewAccountButton() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, LoginPageUI.CREATE_NEW_ACCOUNT_BUTTON);
		clickToElement(driver, LoginPageUI.CREATE_NEW_ACCOUNT_BUTTON);
	}

	public boolean isEmailTextBoxDisplayed() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
		return isElementDisplay(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
	}

	public void enterToEmailTextBox(String email) {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX);
		System.out.println("email là " + email);
		
		getWebElement(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX).clear();
		getWebElement(driver, LoginPageUI.EMAIL_ADDRESS_TEXTBOX).sendKeys(email);
		
		
	}

	public boolean isConfirmEmailAddressTextBoxDisplay() {
		// TODO Auto-generated method stub
		return isElementDisplay(driver, LoginPageUI.EMAIL_ADDRESS_CONFIRM_TEXTBOX);
	}

	public void closeRegisterPopup() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, LoginPageUI.CLOSE_REGISTER_BUTTON);
		clickToElement(driver, LoginPageUI.CLOSE_REGISTER_BUTTON);
	}

	public boolean isConfirmEmailAddressTextBoxUnDisplay() {
		// TODO Auto-generated method stub
		// 5s
		waitForElementUndisplayed(driver, LoginPageUI.EMAIL_ADDRESS_CONFIRM_TEXTBOX);
		//5s
		return isElementUndisplayed(driver,LoginPageUI.EMAIL_ADDRESS_CONFIRM_TEXTBOX);
	}

}
