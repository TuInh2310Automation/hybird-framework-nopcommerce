package pageUI.jQuery;

public class HomePageUI {
	public static final String  PAGINATION_PAGE_BY_NUMBER = "xpath=//li[@class='qgrd-pagination-page']/a[text()='%s']";
	public static final String  HEADER_TEXTBOX_BY_LABEL = "xpath=//div[@class='qgrd-header-text' and text()='%s']/parent::div/following-sibling::input";
	public static final String PAGINATION_PAGE_ACTIVED_BY_NUMBER = "xpath=//a[@class='qgrd-pagination-page-link active' and text() = '%s']";
	public static final String PAGE_TOTAL = "css=ul.qgrd-pagination-ul li";
	public static final String ALL_ROW_EACH_PAGE = "css=tbody tr";
	public static final String ALL_ROW_COUNTRY_EACH_PAGE = "css=tbody tr td[data-key='country']";
	
	public static final String COLUMN_INDEX_BY_NAME = "xpath=//tr/th[text()='%s']/preceding-sibling::th";
	public static final String TEXTBOX_POSITION_BY_ROW_AND_COLLUMN = "xpath=//tbody/tr[%s]/td[%s]/input";
	public static final String DROPDOWN_POSITION_BY_ROW_AND_COLLUMN = "xpath=//tbody/tr[%s]/td[%s]//select";
	public static final String CHECKBOX_POSITION_BY_ROW_AND_COLLUMN = "xpath=//tbody/tr[%s]/td[%s]//input[@type='checkbox']";
	public static final String DATE_POSITION_BY_ROW_AND_COLLUMN = "xpath=//tbody/tr[2]/td[7]//input[@type='date']";
	public static final String ICON_NAME_BY_ROW ="xpath=//tbody/tr[%s]//button[@title='%s']";
	public static final String LOAD_BUTTON ="css=button#load";
	
}
