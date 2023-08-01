package pageUI.wordpress.admin;

public class UserPostDetailPageUI {
	public static final String POST_TITLE_TEXT = "xpath=//article//header//h1[text()='%s']";
	public static final String POST_TIME_TEXT_BY_POST_TITLE = "xpath=//article//header//h1[text()='%s']/parent::header//time[text()='%s']";
	public static final String POST_BODY_TEXT_BY_POST_TITLE = "xpath=//article//header//h1[text()='%s']/parent::header//following-sibling::div[@class='entry-content']/p[text()='%s']";
	public static final String POST_AUTHOR_TEXT_BY_POST_TITLE = "xpath=//article//header//h1[text()='%s']/parent::header//span[@class='author vcard']//a[text()='%s']";
}
