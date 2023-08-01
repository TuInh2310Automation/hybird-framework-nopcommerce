package pageUI.wordpress.admin;

public class UserHomePageUI {
	public static final String POST_TITLE_TEXT = "xpath=//article//header//a[text()='%s']";
	public static final String POST_TIME_TEXT_BY_POST_TITLE = "xpath=//article//header//a[text()='%s']/parent::h2/following-sibling::div//time[text()='%s']";
	public static final String POST_BODY_TEXT_BY_POST_TITLE = "xpath=//article//header//a[text()='%s']//ancestor::header//following-sibling::div[@class='entry-content']/p[text()='%s']";
	
	public static final String SEARCH_BUTTON = "css=section.widget_search input.search-submit";
	public static final String SEARCH_TEXTBOX = "css=section.widget_search input.search-field";
	
	
	
	public static final String POST_AUTHOR_TEXT_BY_POST_TITLE = "xpath=//article//header//a[text()='%s']/parent::h2/following-sibling::div//span[@class='author vcard']/a[text()='%s']";
}
