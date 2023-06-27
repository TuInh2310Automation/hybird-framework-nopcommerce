package pageObjects.nopCommerce.admin;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopCommerce.admin.AdminDashboardPageUI;

public class Admin_DasboardPageObject extends BasePage {
	private WebDriver driver;
	public Admin_DasboardPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public boolean isDashboardHeaderDisplayed() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, AdminDashboardPageUI.DASHBOARD_HEADER);
		return isElementDisplay(driver, AdminDashboardPageUI.DASHBOARD_HEADER);
	}
}
