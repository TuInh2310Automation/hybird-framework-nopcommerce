package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.wordpress.admin.UserHomePageUI;
import pageUI.wordpress.admin.UserPostDetailPageUI;

public class UserPostDetailPO extends BasePage{
	private WebDriver driver;

	public UserPostDetailPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isPostInfoDisplayedWithTitle(String postTitle) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, UserPostDetailPageUI.POST_TITLE_TEXT, postTitle);
		return isElementDisplay(driver, UserPostDetailPageUI.POST_TITLE_TEXT, postTitle);
	}

	public boolean isPostInfoDisplayedWithBody(String postTitle, String postBodyValue) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, UserPostDetailPageUI.POST_BODY_TEXT_BY_POST_TITLE, postTitle, postBodyValue);
		return isElementDisplay(driver, UserPostDetailPageUI.POST_BODY_TEXT_BY_POST_TITLE, postTitle, postBodyValue);
	}

	public boolean isPostInfoDisplayedWithAuthor(String postTitle, String authorName) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, UserPostDetailPageUI.POST_AUTHOR_TEXT_BY_POST_TITLE, postTitle, authorName);
		return isElementDisplay(driver, UserPostDetailPageUI.POST_AUTHOR_TEXT_BY_POST_TITLE, postTitle, authorName);
	}

	public boolean isPostInfoDisplayedWithDay(String postTitle, String currentDay) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, UserPostDetailPageUI.POST_TIME_TEXT_BY_POST_TITLE, postTitle, currentDay);
		return isElementDisplay(driver, UserPostDetailPageUI.POST_TIME_TEXT_BY_POST_TITLE, postTitle, currentDay);
	}

	

}
