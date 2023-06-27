package pageObjectsl.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopCommerce.user.CustomerPageUI;

public class User_OrdersPageObject extends BasePage {
	private WebDriver driver;

	public User_OrdersPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isCustomerInfoPageDisplay() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, CustomerPageUI.CUSTOMER_INFO_HEADER);
		return isElementDisplay(driver, CustomerPageUI.CUSTOMER_INFO_HEADER);
	}
}
