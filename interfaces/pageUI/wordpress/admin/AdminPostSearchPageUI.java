package pageUI.wordpress.admin;

public class AdminPostSearchPageUI {
	public static final String ADD_NEW_POST_BUTTON = "xpath=//h1[contains(text(),'Posts')]//following-sibling::a";
	public static final String SEARCH_TEXTBOX = "css=input#post-search-input";
	public static final String SEARCH_POST_BUTTON = "css=input#search-submit";
	public static final String TABLE_HEADER_COLLUMN_INDEX_BY_HEADER_NAME = "xpath=//table[contains(@class,'table-view-list')] //th[@id='%s']/preceding-sibling::*";
	public static final String TABLE_CONTENT_BY_COLLUM_INDEX = "xpath=//table[contains(@class,'table-view-list')] //tbody//tr/*[%s]//a[text()='%s']";
	public static final String TITLE_LINK_DETAIL = "xpath=//table[contains(@class,'table-view-list')] //tbody//tr//a[@class='row-title' and text()='%s']";
	public static final String ROW_CHECKBOX = "xpath=//table[contains(@class,'table-view-list')] //tbody//tr//a[@class='row-title' and text()='%s']/ancestor::td/preceding-sibling::th/input";
	public static final String ACTION_DROPDOWN = "css=select#bulk-action-selector-top";
	public static final String APPLY_BUTTON = "css=input#doaction";
	public static final String MOVE_TO_TRASH_MESSAGE = "xpath=//div[@id='message']/p[contains(text(),'1 post moved to the Trash. ')]";
	public static final String NO_POST_FOUND = "xpath=//table[contains(@class,'table-view-list')]//tbody//td[text()='%s']";

}
