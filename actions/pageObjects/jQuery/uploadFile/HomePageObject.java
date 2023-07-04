package pageObjects.jQuery.uploadFile;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUI.jQuery.uploadFiles.HomePageUI;

public class HomePageObject extends BasePage {
	WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isFileLoadedByName(String filename) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver,HomePageUI.FILE_NAME_LOADED, filename);
		return isElementDisplay(driver, HomePageUI.FILE_NAME_LOADED, filename);
	}

	
	public boolean isFileUpLoadedByName(String filename) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver,HomePageUI.FILE_NAME_UPLOADED_LINK, filename);
		return isElementDisplay(driver, HomePageUI.FILE_NAME_UPLOADED_LINK, filename);
	}
	public boolean isFileImageUpLoadedByName(String filename) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver,HomePageUI.FILE_IMAGE_UPLOADED_LINK, filename);
		return isImageLoaded(driver, HomePageUI.FILE_IMAGE_UPLOADED_LINK, filename);
	}
	
	public void clickToStartButton() {
		List<WebElement> startDowloadButtons= getListWebElement(driver, HomePageUI.DOWNLOAD_BUTTON_LIST);
		for (WebElement startButton : startDowloadButtons) {
			startButton.click();
			sleepInSecond(2);
		}
	}
	private void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
