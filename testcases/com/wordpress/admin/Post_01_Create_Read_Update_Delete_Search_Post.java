package com.wordpress.admin;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import freemarker.cache.StrongCacheStorage;
import pageObjects.wordpress.admin.AdminDashboardPO;
import pageObjects.wordpress.admin.AdminLoginPO;
import pageObjects.wordpress.admin.AdminPostAddNewPO;
import pageObjects.wordpress.admin.AdminPostSearchPO;
import pageObjects.wordpress.admin.PageGeneratorManagement;
import pageObjects.wordpress.admin.UserHomePO;
import pageObjects.wordpress.admin.UserPostDetailPO;
import pageObjects.wordpress.admin.UserSearchPostPO;

public class Post_01_Create_Read_Update_Delete_Search_Post extends BaseTest {

	private WebDriver driver;
	private AdminLoginPO adminLoginPage;
	private AdminDashboardPO adminDashboardPage;
	private AdminPostSearchPO adminPostSearchPage;
	private AdminPostAddNewPO adminPostAddNewPage;
	private UserHomePO userHomePage;
	private UserPostDetailPO userPostDetailPage;
	private UserSearchPostPO userSearchPostPage;
	String adminUsername = "automationVa";
	String adminPw = "automationVa";
	String searchPostUrl;
	String authorName = "automationVa";
	int randomNumber = generateRandomNumber();
	String postTitle = "Live Coding Title" + randomNumber;
	String postBodyValue = "Live Coding Body" + randomNumber;
	String editPostTitle = "Edit Coding Title"+ randomNumber;
	String editPostBody = "Edit Coding Body"+ randomNumber;
	String adminUrl, endUserUrl, currentDay;

	@Parameters({ "browser", "adminUrl", "userUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String adminUrl, String endUserUrl) {
		log.info("Precondition - Step 01 : Open browser and admin URL");
		if (log == null) {
			System.out.println("Log null");
		} else {
			System.out.println("Log OK");
		}
		this.adminUrl = adminUrl;
		this.endUserUrl = endUserUrl;
		currentDay = getCurrentDay();
		driver = getBrowserDriverLocal(browserName, adminUrl);
		adminLoginPage = PageGeneratorManagement.getAdminLoginPage(driver);
		log.info("Precondition - RANDOM NUMBER" + randomNumber);
		log.info("Precondition - Step 02 : Enter to username textbox with value" + adminUsername);
		adminLoginPage.enterToUserNameTextBox(adminUsername);

		log.info("Precondition - Step 03 : Enter to pw textbox with value" + adminPw);
		adminLoginPage.enterToPasswordTextBox(adminPw);

		log.info("Precondition - Step 04 : Click to login button");
		adminDashboardPage = adminLoginPage.clickToLoginButton();

	}

	@Test
	public void Post_01_Create_New_Post() {
		log.info("Create Post - Step 01 :Click to 'Post' menu link ");
		adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();

		searchPostUrl = adminPostSearchPage.getPageUrl(driver);

		log.info("Create Post - Step 02 :Click to ' Add new' Button");
		adminPostAddNewPage = adminPostSearchPage.clickToAddNewButton();

		log.info("Create Post - Step 03 : Enter post title " + postTitle);
		adminPostAddNewPage.enterToAddNewPostTitle(postTitle);

		log.info("Create Post - Step 04: Enter post body " + postBodyValue);
		adminPostAddNewPage.enterToAddNewPostBody(postBodyValue);

		log.info("Create Post - Step 06 : Click to Publish button");
		adminPostAddNewPage.clickToPublishButton();

		log.info("Create Post - Step 07 : Click to Confirm Publish button");
		adminPostAddNewPage.clickToConfirmPublishButton();

		log.info("Create Post - Step 08 : Verify 'Post Published' message is displayed");
		verifyTrue(adminPostAddNewPage.isPostPublishMessageDisplayed("Post published."));
	}

	@Test
	public void Post_02_Search_And_View_Post() {
		// open search Post page
		log.info("Search Post - Step 01 : Open 'Search Post' page");
		adminPostSearchPage = adminPostAddNewPage.openSearchPostPageUrl(searchPostUrl);

		log.info("Search Post - Step 02 : Enter to Search Post");
		adminPostSearchPage.enterToSearchTextBox(postTitle);

		log.info("Search Post - Step 03 : Click to 'Search Post' button");
		adminPostSearchPage.clickToSearchPostButton();

		log.info("Search Post - Step 04 : Verify Search table contains " + postTitle);
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("title", postTitle));

		log.info("Search Post - Step 05 : Verify Search table contains " + authorName);
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("author", authorName));

		log.info("Search Post - Step 06 : Open End user site");
		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);

		log.info("Search Post - Step 07 : Verify Post info displayed at HomePage");
		verifyTrue(userHomePage.isPostInfoDisplayedWithTitle(postTitle));
		verifyTrue(userHomePage.isPostInfoDisplayedWithBody(postTitle, postBodyValue));
		verifyTrue(userHomePage.isPostInfoDisplayedWithAuthor(postTitle, authorName));
		verifyTrue(userHomePage.isPostInfoDisplayedWithDay(postTitle, currentDay));

		log.info("Search Post - Step 08 : Click to post title");
		userPostDetailPage = userHomePage.clickToPostTitle(postTitle);
		verifyTrue(userPostDetailPage.isPostInfoDisplayedWithTitle(postTitle));
		verifyTrue(userPostDetailPage.isPostInfoDisplayedWithBody(postTitle,postBodyValue));
		verifyTrue(userPostDetailPage.isPostInfoDisplayedWithAuthor(postTitle,authorName));
		verifyTrue(userPostDetailPage.isPostInfoDisplayedWithDay(postTitle,currentDay));

	}

	@Test
	public void Post_03_Edit_Post() {
		log.info("Edit Post - Step 01 :Open Admin site");
		adminLoginPage = userPostDetailPage.openAdminSite(driver, this.adminUrl);
		
		log.info("Precondition - Step 02 : Enter to username textbox with value " + adminUsername);
		adminLoginPage.enterToUserNameTextBox(adminUsername);

		log.info("Precondition - Step 03 : Enter to pw textbox with value " + adminPw);
		adminLoginPage.enterToPasswordTextBox(adminPw);

		log.info("Precondition - Step 04 : Click to login button");
		adminDashboardPage = adminLoginPage.clickToLoginButton();
		
		log.info("Edit Post - Step 02 : Click to Post menu link");
		adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();
		
		log.info("Edit Post - Step 03 :Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextBox(postTitle);
		
		log.info("Search Post - Step 04 : Click to 'Search Post' button");
		adminPostSearchPage.clickToSearchPostButton();
		
		log.info("Edit Post - Step 05 : Click to post title link and navigate to Edit post page");
		adminPostAddNewPage = adminPostSearchPage.clickToPostTitleLink(postTitle);
		
		
		log.info("Edit Post - Step 06 :Enter to Post title" + editPostTitle);
		adminPostAddNewPage.enterToAddNewPostTitle(editPostTitle);
		
		log.info("Edit Post - Step 04: Enter post body " + editPostBody);
		adminPostAddNewPage.enterToEditPostBody(editPostBody);
		
		
		log.info("Edit Post - Step 07 : Click to update Publish button");
		adminPostAddNewPage.clickToUpdateButton();
		
		log.info("Edit Post - Step 08 : Verify 'Post Updated' message is displayed");
		verifyTrue(adminPostAddNewPage.isPostPublishMessageDisplayed("Post updated."));
		
		// Search with new info
		
		log.info("Edit Post - Step 01 : Open 'Search Post' page");
		adminPostSearchPage = adminPostAddNewPage.openSearchPostPageUrl(searchPostUrl);

		log.info("Edit Post - Step 02 : Enter to Search Post");
		adminPostSearchPage.enterToSearchTextBox(editPostTitle);

		log.info("Edit Post - Step 03 : Click to 'Search Post' button");
		adminPostSearchPage.clickToSearchPostButton();

		log.info("Edit Post - Step 04 : Verify Search table contains " + editPostTitle);
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("title", editPostTitle));

		log.info("Edit Post - Step 05 : Verify Search table contains " + authorName);
		verifyTrue(adminPostSearchPage.isPostSearchTableDisplayed("author", authorName));

		log.info("Edit Post - Step 06 : Open End user site");
		userHomePage = adminPostSearchPage.openEndUserSite(driver, this.endUserUrl);

		log.info("Edit Post - Step 07 : Verify Post info displayed at HomePage");
		verifyTrue(userHomePage.isPostInfoDisplayedWithTitle(editPostTitle));
		verifyTrue(userHomePage.isPostInfoDisplayedWithBody(editPostTitle, editPostBody));
		verifyTrue(userHomePage.isPostInfoDisplayedWithAuthor(editPostTitle, authorName));
		verifyTrue(userHomePage.isPostInfoDisplayedWithDay(editPostTitle, currentDay));

		log.info("Edit Post - Step 08 : Click to post title");
		userPostDetailPage = userHomePage.clickToPostTitle(editPostTitle);
		verifyTrue(userPostDetailPage.isPostInfoDisplayedWithTitle(editPostTitle));
		verifyTrue(userPostDetailPage.isPostInfoDisplayedWithBody(editPostTitle,editPostBody));
		verifyTrue(userPostDetailPage.isPostInfoDisplayedWithAuthor(editPostTitle,authorName));
		verifyTrue(userPostDetailPage.isPostInfoDisplayedWithDay(editPostTitle,currentDay));
	
	}

	@Test
	public void Post_04_Delete_Post() {
		log.info("Edit Post - Step 01 :Open Admin site");
		adminLoginPage = userPostDetailPage.openAdminSite(driver, this.adminUrl);
		
		log.info("Precondition - Step 02 : Enter to username textbox with value " + adminUsername);
		adminLoginPage.enterToUserNameTextBox(adminUsername);

		log.info("Precondition - Step 03 : Enter to pw textbox with value " + adminPw);
		adminLoginPage.enterToPasswordTextBox(adminPw);

		log.info("Precondition - Step 04 : Click to login button");
		adminDashboardPage = adminLoginPage.clickToLoginButton();
		
		log.info("Edit Post - Step 05 : Click to Post menu link");
		adminPostSearchPage = adminDashboardPage.clickToPostMenuLink();
		
		log.info("Edit Post - Step 06 :Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextBox(editPostTitle);
		
		log.info("Search Post - Step 07 : Click to 'Search Post' button");
		adminPostSearchPage.clickToSearchPostButton();
		
		log.info("Search Post - Step 08 : Select Post detail checkbox");
		adminPostSearchPage.selectPostCheckboxbyTitle(editPostTitle);
		
		log.info("Search Post - Step 09 : Select 'Move to Trash' item in dropdown");
		adminPostSearchPage.selectTextItemInActionDropdown("Move to Trash");
		
		log.info("Search Post - Step 10 : Click to 'Apply' Button");
		adminPostSearchPage.clickToApplyButton();
		
		
		log.info("Search Post - Step 11 : Verify '1 post moved to Trash.' message is displayed");
		verifyTrue(adminPostSearchPage.isMoveToTrashMessageDisplayed("1 post moved to Trash."));
		
		log.info("Edit Post - Step 12 :Enter to Search textbox");
		adminPostSearchPage.enterToSearchTextBox(editPostTitle);
		
		log.info("Search Post - Step 13 : Click to 'Search Post' button");
		adminPostSearchPage.clickToSearchPostButton();
		
		log.info("Search Post - Step 14 : Verify 'No posts found.' message is displayed");
		verifyTrue(adminPostSearchPage.isNoPostFoundMessageDisplayed("No posts found."));
		
		log.info("Search Post - Step 15 : Open End User site");
		userHomePage=adminPostSearchPage.openEndUserSite(driver, endUserUrl);
		
		log.info("Search Post - Step 16 : Verify post title undisplayed at home page");
		verifyTrue(userHomePage.isPostInfoUndisplayedWithPostTitle(editPostTitle));
		
		log.info("Search Post - Step 17 : Enter to search textbox" + editPostTitle);
		userHomePage.enterToSearchTextbox(editPostTitle);
		
		log.info("Search Post - Step 18 : Click to 'search' Button");
		userSearchPostPage = userHomePage.clickToSearchButton();
		
		log.info("Search Post - Step 19 : Verify 'Nothing found' message is displayed");
		verifyTrue(userSearchPostPage.isNothingFoundMessageDisplayed(editPostTitle));
		
	}

	@Test
	public void Post_05_Delete_Post() {

	}

	public int generateRandomNumber() {
		return new Random().nextInt(9999);
	}

	@AfterClass
	public void afterClass() {
		// driver.quit();
	}

}
