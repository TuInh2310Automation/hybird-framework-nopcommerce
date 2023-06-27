package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.nopCommerce.admin.Admin_DasboardPageObject;
import pageObjects.nopCommerce.admin.Admin_LoginPageObject;
import pageObjectsl.nopCommerce.user.User_AddressPageObject;
import pageObjectsl.nopCommerce.user.User_CustomerInfoPageObject;
import pageObjectsl.nopCommerce.user.User_HomePageObject;
import pageObjectsl.nopCommerce.user.User_LoginPageObject;
import pageObjectsl.nopCommerce.user.User_MyProductReviewsPageObject;
import pageObjectsl.nopCommerce.user.User_RegisterPageObject;
import pageObjectsl.nopCommerce.user.User_RewardPointsPageObject;

public class PageGeneratorManagement {
	public static User_HomePageObject getUserHomePageObject(WebDriver driver) {
		return new User_HomePageObject(driver);
	}
	
	public static User_LoginPageObject getUserLoginPage(WebDriver driver) {
		return new User_LoginPageObject(driver);
	}
	
	public static User_RegisterPageObject getUserRegisterPage(WebDriver driver) {
		return new User_RegisterPageObject(driver);
	}

	public static User_CustomerInfoPageObject getUserCustomerInfoPage(WebDriver driver) {
		// TODO Auto-generated method stub
		return new User_CustomerInfoPageObject(driver);
	}
	
	public static User_AddressPageObject getUserAddressPage(WebDriver driver) {
		// TODO Auto-generated method stub
		return new User_AddressPageObject(driver);
	}
	
	public static User_MyProductReviewsPageObject getUserMyProductReviewPage(WebDriver driver) {
		// TODO Auto-generated method stub
		return new User_MyProductReviewsPageObject(driver);
	}
	
	public static User_RewardPointsPageObject getUserRewardPointsPage(WebDriver driver) {
		// TODO Auto-generated method stub
		return new User_RewardPointsPageObject(driver);
	}

	public static Admin_DasboardPageObject getAdminDashboardPageObject(WebDriver driver) {
		// TODO Auto-generated method stub
		return new Admin_DasboardPageObject(driver);
	}

	public static Admin_LoginPageObject getAdminLoginPagee(WebDriver driver) {
		// TODO Auto-generated method stub
		return new Admin_LoginPageObject(driver);
	}
}
