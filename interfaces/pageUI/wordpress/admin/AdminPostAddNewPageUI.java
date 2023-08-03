package pageUI.wordpress.admin;

public class AdminPostAddNewPageUI {
	public static final String POST_TITLE_TEXTBOX = "css=h1.wp-block-post-title";
	public static final String POST_BODY_BUTTON = "css=p[class*=block-editor-default]";
	public static final String POST_BODY_TEXTBOX = "css=p.block-editor-rich-text__editable";
	public static final String PUBLISH_BUTTON = "xpath=//div[@class='edit-post-header__settings']//button[text()='Publish']";
	public static final String UPDATE_BUTTON = "xpath=//div[@class='edit-post-header__settings']//button[text()='Update']";
	public static final String CONFIRM_PUBLISH_BUTTON = "xpath=//div[@class='editor-post-publish-panel']//button[text()='Publish']";
	public static final String PUBLISHED_OR_UPDATE_MESSAGE="xpath=//div[@class='components-snackbar__content' and text()='%s']" ;
}
