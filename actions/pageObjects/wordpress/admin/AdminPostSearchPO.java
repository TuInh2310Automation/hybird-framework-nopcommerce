package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.wordpress.admin.AdminDashboardPageUI;
import pageUI.wordpress.admin.AdminPostSearchPageUI;

public class AdminPostSearchPO extends BasePage {
	private WebDriver driver;

	public AdminPostSearchPO(WebDriver driver) {
		this.driver = driver;
	}

	public AdminPostAddNewPO clickToAddNewButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, AdminPostSearchPageUI.ADD_NEW_POST_BUTTON);
		clickToElement(driver, AdminPostSearchPageUI.ADD_NEW_POST_BUTTON);
		return PageGeneratorManagement.getAdminPostAddNewPage(driver);

	}

	public void enterToSearchTextBox(String postTitle) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, AdminPostSearchPageUI.SEARCH_TEXTBOX);
		sendkeyToElement(driver, AdminPostSearchPageUI.SEARCH_TEXTBOX, postTitle);
	}

	public void clickToSearchPostButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, AdminPostSearchPageUI.SEARCH_POST_BUTTON);
		clickToElement(driver, AdminPostSearchPageUI.SEARCH_POST_BUTTON);
	}

	public boolean isPostSearchTableDisplayed(String headerID, String cellValue) {
		// TODO Auto-generated method stub
		int collumnIndex = getElementSize(driver, AdminPostSearchPageUI.TABLE_HEADER_COLLUMN_INDEX_BY_HEADER_NAME, headerID) + 1;
		waitForElementVisible(driver, AdminPostSearchPageUI.TABLE_CONTENT_BY_COLLUM_INDEX, String.valueOf(collumnIndex), cellValue);
		return isElementDisplay(driver, AdminPostSearchPageUI.TABLE_CONTENT_BY_COLLUM_INDEX, String.valueOf(collumnIndex), cellValue);
	}

	public AdminPostAddNewPO clickToPostTitleLink(String postTitle) {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, AdminPostSearchPageUI.TITLE_LINK_DETAIL, postTitle);
		clickToElement(driver, AdminPostSearchPageUI.TITLE_LINK_DETAIL, postTitle);
		return PageGeneratorManagement.getAdminPostAddNewPage(driver);
	}

	public void selectTextItemInActionDropdown(String dropdownItem) {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, AdminPostSearchPageUI.ACTION_DROPDOWN);
		selectItemInDefaultDropDown(driver, AdminPostSearchPageUI.ACTION_DROPDOWN, dropdownItem);
	}

	public void selectPostCheckboxbyTitle(String editPostTitle) {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, AdminPostSearchPageUI.ROW_CHECKBOX, editPostTitle);
		clickToElement(driver, AdminPostSearchPageUI.ROW_CHECKBOX, editPostTitle);
	}

	public void clickToApplyButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, AdminPostSearchPageUI.APPLY_BUTTON);
		clickToElement(driver, AdminPostSearchPageUI.APPLY_BUTTON);
	}

	public boolean isMoveToTrashMessageDisplayed(String message) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, AdminPostSearchPageUI.MOVE_TO_TRASH_MESSAGE,message);
		return isElementDisplay(driver, AdminPostSearchPageUI.MOVE_TO_TRASH_MESSAGE,message);
	}

	public boolean isNoPostFoundMessageDisplayed(String message) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, AdminPostSearchPageUI.NO_POST_FOUND,message);
		return isElementDisplay(driver, AdminPostSearchPageUI.NO_POST_FOUND,message);
	}

}
