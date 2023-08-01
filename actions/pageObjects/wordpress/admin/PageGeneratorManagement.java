package pageObjects.wordpress.admin;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManagement {
	public static AdminLoginPO getAdminLoginPage(WebDriver driver) {
		return new AdminLoginPO(driver);
	}

	public static AdminDashboardPO getAdminDashboardPage(WebDriver driver) {
		return new AdminDashboardPO(driver);
	}

	public static AdminPostAddNewPO getAdminPostAddNewPage(WebDriver driver) {
		return new AdminPostAddNewPO(driver);
	}

	public static AdminPostCategoriesPO getAdminPostCategoriesPage(WebDriver driver) {
		return new AdminPostCategoriesPO(driver);
	}

	public static AdminPostDeletePO getAdminPostDeletePage(WebDriver driver) {
		return new AdminPostDeletePO(driver);
	}

	public static AdminPostEditPO getAdminPostEditPage(WebDriver driver) {
		return new AdminPostEditPO(driver);
	}

	public static AdminPostSearchPO getAdminPostSearchPage(WebDriver driver) {
		return new AdminPostSearchPO(driver);
	}

	public static AdminPostTagPO getAdminPostTagPage(WebDriver driver) {
		return new AdminPostTagPO(driver);
	}
	
	public static UserPostDetailPO getUserDetailPostPage(WebDriver driver) {
		return new UserPostDetailPO(driver);
	}

	public static UserSearchPostPO getUserSearchPostPOPage(WebDriver driver) {
		return new UserSearchPostPO(driver);
	} 
	public static UserHomePO getUserHomePage(WebDriver driver) {
		return new UserHomePO(driver);
	}
}
