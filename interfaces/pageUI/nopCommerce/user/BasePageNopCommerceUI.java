package pageUI.nopCommerce.user;

public class BasePageNopCommerceUI {
 public static final String CUSTOMER_INFO_LINK = "xpath=//div[contains(@class, 'account-navigation')]//a[text()='Customer info']";
 public static final String ADDRESS_LINK = "xpath=//div[contains(@class, 'account-navigation')]//a[text()='Addresses']";
 public static final String ODERS_LINK = "xpath=//div[contains(@class, 'account-navigation')]//a[text()='Orders']";
 public static final String DOWNLOAD_PRODUCTS_LINK = "xpath=//div[contains(@class, 'account-navigation')]//a[text()='Downloadable products']";
 public static final String BACK_IN_STOCK_SUBCRIPTIONS_LINK = "xpath=//div[contains(@class, 'account-navigation')]//a[text()='Back in stock subscriptions']";
 public static final String REWARD_POINT_LINK = "xpath=//div[contains(@class, 'account-navigation')]//a[text()='Reward points']";
 public static final String CHANGE_PW_LINK = "xpath=//div[contains(@class, 'account-navigation')]//a[text()='Change password']";
 public static final String MY_PRODUCT_LINK = "xpath=//div[contains(@class, 'account-navigation')]//a[text()='My product reviews']";

 
 public static final String LOGOUT_USER_LINK = "class=ico-logout";
 public static final String LOGOUT_ADMIN_LINK = "xpath=//a[text()='Logout']";
 
 //object pattern
 public static final String DYNAMIC_TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
 public static final String DYNAMIC_BUTTON_BY_TEXT = "xpath=//button[text()='%s']";
 public static final String DYNAMIC_DROPDOWN_BY_NAME = "xpath=//select[@name='%s']";
 public static final String DYNAMIC_CHECKBOX_BUTTON_BY_NAME = "xpath=//label[text()='%s']//following-sibling::input";
 public static final String DYNAMIC_RADIO_BUTTON_BY_NAME = "xpath=//label[text()='%s']//preceding-sibling::input";
 public static final String DYNAMIC_PAGE_AT_MYACCOUNT_LINK = "xpath=//div[contains(@class, 'account-navigation')]//a[text()='%s']";
}
