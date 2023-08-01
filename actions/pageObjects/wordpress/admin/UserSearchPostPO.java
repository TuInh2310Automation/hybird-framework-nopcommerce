package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.wordpress.admin.UserHomePageUI;
import pageUI.wordpress.admin.UserPostDetailPageUI;
import pageUI.wordpress.admin.UserSearchPostPageUI;

public class UserSearchPostPO extends BasePage{
	private WebDriver driver;

	public UserSearchPostPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isNothingFoundMessageDisplayed(String message) {
		// TODO Auto-
		waitForElementVisible(driver, UserSearchPostPageUI.NOTHING_FOUND_MESSAGE, message);
		return isElementDisplay(driver, UserSearchPostPageUI.NOTHING_FOUND_MESSAGE, message);
	}

	

}
