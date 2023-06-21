package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.HomePageUI;

public class HomePageObject extends BasePage{

	private WebDriver driver;
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	public void clickToRegisterLink() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
		
		
	}
	public void clickToLoginLink() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver, HomePageUI.LOGIN_LINK);
		
	}
	public boolean isMyAccountLinkDisplayed() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);
		return isElementDisplay(driver, HomePageUI.MY_ACCOUNT_LINK);
	}

}
