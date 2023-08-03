package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.wordpress.admin.AdminLoginPageUI;
import pageUI.wordpress.admin.AdminPostAddNewPageUI;

public class AdminPostAddNewPO  extends BasePage{
	private WebDriver driver;

	public AdminPostAddNewPO(WebDriver driver) {
		this.driver = driver;
	}

	public void enterToAddNewPostTitle(String postTitle) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, AdminPostAddNewPageUI.POST_TITLE_TEXTBOX);
		System.out.println("post");
		sendkeyToElement(driver, AdminPostAddNewPageUI.POST_TITLE_TEXTBOX, postTitle);
		
	}

	public void enterToAddNewPostBody(String postBodyValue) {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, AdminPostAddNewPageUI.POST_BODY_BUTTON);
		clickToElement(driver, AdminPostAddNewPageUI.POST_BODY_BUTTON);
		sleepInSecond(1);
		waitForElementVisible(driver, AdminPostAddNewPageUI.POST_BODY_TEXTBOX);
		sendkeyToElement(driver, AdminPostAddNewPageUI.POST_BODY_TEXTBOX, postBodyValue);
		
	}

	public void clickToPublishButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, AdminPostAddNewPageUI.PUBLISH_BUTTON);
		clickToElement(driver, AdminPostAddNewPageUI.PUBLISH_BUTTON);
	}
	
	public void clickToUpdateButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, AdminPostAddNewPageUI.UPDATE_BUTTON);
		clickToElement(driver, AdminPostAddNewPageUI.UPDATE_BUTTON);
	}
	
	public void clickToConfirmPublishButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, AdminPostAddNewPageUI.CONFIRM_PUBLISH_BUTTON);
		clickToElement(driver, AdminPostAddNewPageUI.CONFIRM_PUBLISH_BUTTON);
	}

	public boolean isPostPublishMessageDisplayed(String postPublistMessage) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, AdminPostAddNewPageUI.PUBLISHED_OR_UPDATE_MESSAGE, postPublistMessage);
		return isElementDisplay(driver, AdminPostAddNewPageUI.PUBLISHED_OR_UPDATE_MESSAGE, postPublistMessage);
		
	}

	public AdminPostSearchPO openSearchPostPageUrl(String searchPostUrl) {
		// TODO Auto-generated method stub
		openPageUrl(driver, searchPostUrl);
		return PageGeneratorManagement.getAdminPostSearchPage(driver);
		
	}

	public void enterToEditPostBody(String postBodyValue) {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, AdminPostAddNewPageUI.POST_BODY_TEXTBOX);
		clickToElement(driver, AdminPostAddNewPageUI.POST_BODY_TEXTBOX);
		sleepInSecond(1);
		clearValueInElementByPressKey(driver, AdminPostAddNewPageUI.POST_BODY_TEXTBOX);
		waitForElementVisible(driver, AdminPostAddNewPageUI.POST_BODY_TEXTBOX);
		sendkeyToElement(driver, AdminPostAddNewPageUI.POST_BODY_TEXTBOX, postBodyValue);
	}
}
