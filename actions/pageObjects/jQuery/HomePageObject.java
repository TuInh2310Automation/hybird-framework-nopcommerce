package pageObjects.jQuery;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Sleeper;

import commons.BasePage;
import pageUI.jQuery.HomePageUI;

public class HomePageObject extends BasePage {
	WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void openPagingByPageNumber(String pageNumber) {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, pageNumber);
		clickToElement(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, pageNumber);
	}

	public void enterToHeaderTextboxByLabel(String headerLabel, String value) {
		waitForElementVisible(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL, headerLabel);
		sendkeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL, value, headerLabel);
		pressKeyToElement(driver, HomePageUI.HEADER_TEXTBOX_BY_LABEL, Keys.ENTER, headerLabel);
	}

	public boolean isPageNumberActivated(String pageNumber) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, HomePageUI.PAGINATION_PAGE_ACTIVED_BY_NUMBER, pageNumber);
		return isElementDisplay(driver, HomePageUI.PAGINATION_PAGE_ACTIVED_BY_NUMBER, pageNumber);
	}

	public List<String> getValueEachRowAtAllPage() {
		// TODO Auto-generated method stub
		int pageTotal = getElementSize(driver, HomePageUI.PAGE_TOTAL);
		System.out.println("page total" + pageTotal);
		List<String> allRowValueAllPage = new ArrayList<String>();
		for (int i = 0; i < pageTotal; i++) {
			clickToElement(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, String.valueOf(i + 1));
			sleepInSecond(1);
			// get test all row mỗi page
			List<WebElement> allRowElementEachPage = getListWebElement(driver, HomePageUI.ALL_ROW_EACH_PAGE);
			for (WebElement eachRow : allRowElementEachPage) {
				allRowValueAllPage.add(eachRow.getText());
				System.out.println(eachRow.getText());
			}
		}
		return allRowValueAllPage;
	}

	public List<String> getCountryValueEachRowAtAllPage() {
		// TODO Auto-generated method stub
		int pageTotal = getElementSize(driver, HomePageUI.PAGE_TOTAL);
		List<String> allCountryRowValueAllPage = new ArrayList<String>();
		for (int i = 0; i < pageTotal; i++) {
			clickToElement(driver, HomePageUI.PAGINATION_PAGE_BY_NUMBER, String.valueOf(i + 1));
			sleepInSecond(1);
			// get test all row mỗi page
			List<WebElement> allRowElementEachPage = getListWebElement(driver, HomePageUI.ALL_ROW_COUNTRY_EACH_PAGE);
			for (WebElement eachRow : allRowElementEachPage) {
				allCountryRowValueAllPage.add(eachRow.getText());
				System.out.println(eachRow.getText());
			}
		}
		return allCountryRowValueAllPage;
	}

	private void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void enterToTextboxByCollumnNameAtRowNumber(String rowIndex, String columnName, String value) {
		// TODO Auto-generated method stub
		int collumnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		waitForElementVisible(driver, HomePageUI.TEXTBOX_POSITION_BY_ROW_AND_COLLUMN, rowIndex, String.valueOf(collumnIndex));
		sendkeyToElement(driver, HomePageUI.TEXTBOX_POSITION_BY_ROW_AND_COLLUMN, value, rowIndex, String.valueOf(collumnIndex));
	}

	public void selectDropdown(String rowIndex, String columnName, String value) {
		// TODO Auto-generated method stub
		int collumnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		waitForElementClickable(driver, HomePageUI.DROPDOWN_POSITION_BY_ROW_AND_COLLUMN, rowIndex, String.valueOf(collumnIndex));
		selectItemInDefaultDropDown(driver, HomePageUI.DROPDOWN_POSITION_BY_ROW_AND_COLLUMN, value, rowIndex, String.valueOf(collumnIndex));
	}

	public void clickOnCheckBox(String rowIndex, String columnName) {
		int collumnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		waitForElementClickable(driver, HomePageUI.CHECKBOX_POSITION_BY_ROW_AND_COLLUMN, rowIndex, String.valueOf(collumnIndex));
		checkToDefaultCheckboxRadio(driver, HomePageUI.CHECKBOX_POSITION_BY_ROW_AND_COLLUMN, rowIndex, String.valueOf(collumnIndex));
	}

	public void unclickOnCheckBox(String rowIndex, String columnName) {
		int collumnIndex = getElementSize(driver, HomePageUI.COLUMN_INDEX_BY_NAME, columnName) + 1;
		waitForElementClickable(driver, HomePageUI.CHECKBOX_POSITION_BY_ROW_AND_COLLUMN, rowIndex, String.valueOf(collumnIndex));
		uncheckToDefaultCheckboxRadio(driver, HomePageUI.CHECKBOX_POSITION_BY_ROW_AND_COLLUMN, rowIndex, String.valueOf(collumnIndex));
	}

	public void clickOnLoadButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, HomePageUI.LOAD_BUTTON);
		clickToElement(driver, HomePageUI.LOAD_BUTTON);
	}
	public void clickToIconByRowNumber(String rowNumber, String iconName) {
		waitForElementClickable(driver, HomePageUI.ICON_NAME_BY_ROW, rowNumber, iconName);
		clickToElement(driver, HomePageUI.ICON_NAME_BY_ROW, rowNumber, iconName);
	}
}
