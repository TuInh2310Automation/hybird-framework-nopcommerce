package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.wordpress.admin.UserHomePageUI;

public class UserHomePO extends BasePage{
	private WebDriver driver;
	public UserHomePO(WebDriver driver) {
		this.driver = driver;
	}
	
	public UserPostDetailPO clickToPostTitle(String postTitle) {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, UserHomePageUI.POST_TITLE_TEXT, postTitle);
		clickToElement(driver, UserHomePageUI.POST_TITLE_TEXT, postTitle);
		return PageGeneratorManagement.getUserDetailPostPage(driver);
	}
	public boolean isPostInfoDisplayedWithTitle(String postTitle) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, UserHomePageUI.POST_TITLE_TEXT, postTitle);
		return isElementDisplay(driver, UserHomePageUI.POST_TITLE_TEXT, postTitle);
	}

	public boolean isPostInfoDisplayedWithBody(String postTitle, String postBodyValue) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, UserHomePageUI.POST_BODY_TEXT_BY_POST_TITLE, postTitle, postBodyValue);
		return isElementDisplay(driver, UserHomePageUI.POST_BODY_TEXT_BY_POST_TITLE, postTitle, postBodyValue);
	}

	public boolean isPostInfoDisplayedWithAuthor(String postTitle, String authorName) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, UserHomePageUI.POST_AUTHOR_TEXT_BY_POST_TITLE, postTitle, authorName);
		return isElementDisplay(driver, UserHomePageUI.POST_AUTHOR_TEXT_BY_POST_TITLE, postTitle, authorName);
	}

	public boolean isPostInfoDisplayedWithDay(String postTitle, String currentDay) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, UserHomePageUI.POST_TIME_TEXT_BY_POST_TITLE, postTitle, currentDay);
		return isElementDisplay(driver, UserHomePageUI.POST_TIME_TEXT_BY_POST_TITLE, postTitle, currentDay);
	}

	public boolean isPostInfoUndisplayedWithPostTitle(String editPostTitle) {
		// TODO Auto-generated method stub
		return isElementUndisplayed(driver, UserHomePageUI.POST_TITLE_TEXT,editPostTitle);
	}

	public void enterToSearchTextbox(String editPostTitle) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, UserHomePageUI.SEARCH_TEXTBOX, editPostTitle);
		sendkeyToElement(driver, UserHomePageUI.SEARCH_TEXTBOX, editPostTitle);
	}

	public UserSearchPostPO clickToSearchButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, UserHomePageUI.SEARCH_BUTTON);
		clickToElement(driver, UserHomePageUI.SEARCH_BUTTON);
		return PageGeneratorManagement.getUserSearchPostPOPage(driver);
	}
}
