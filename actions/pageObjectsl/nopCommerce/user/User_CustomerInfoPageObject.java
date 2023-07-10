package pageObjectsl.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUI.nopCommerce.user.CustomerPageUI;

public class User_CustomerInfoPageObject extends BasePage {
	private WebDriver driver;

	public User_CustomerInfoPageObject(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Check Customer Info page is displayed")
	public boolean isCustomerInfoPageDisplay() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, CustomerPageUI.CUSTOMER_INFO_HEADER);
		return isElementDisplay(driver, CustomerPageUI.CUSTOMER_INFO_HEADER);
	}
}
