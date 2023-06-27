package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import commons.BasePage;
import commons.BasePageFactory;
import pageUI.nopCommerce.user.HomePageUI;
import pageUI.nopCommerce.user.RegisterPageUI;

public class RegisterPageObject extends BasePageFactory{

	private WebDriver driver;
	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public static final String REGISTER_BUTTON = "//button[@id='register-button']";
	public static final String EMAIL_TEXTBOX = "//input[@id='Email']";
	public static final String FIRSTNAME_TEXTBOX = "//input[@id='FirstName']";
	public static final String LASTNAME_TEXTBOX = "//input[@id='LastName']";
	public static final String PASSWORD_TEXTBOX = "//input[@id='Password']";
	public static final String CONFRIM_PASSWORD_TEXTBOX = "//input[@id='ConfirmPassword']";
	public static final String FIRSTNAME_ERROR_MESSAGE = "//span[@id='FirstName-error']";
	public static final String LASTNAME_ERROR_MESSAGE = "//span[@id='LastName-error']";
	public static final String EMAIL_ERROR_MESSAGE = "//span[@id='Email-error']";
	public static final String PASSWORD_ERROR_MESSAGE = "//span[@id='Password-error']";
	public static final String CONFIRM_PASSWORD_ERROR_MESSAGE = "//span[@id='ConfirmPassword-error']";
	public static final String REGISTER_SUCCESS_MESSAGE = "//div[@class='result']";
	public static final String LOGOUT_LINK = "";
	public static final String EXISTING_EMAIL_ERROR="//div[@class='message-error validation-summary-errors']";
	public static final String CONTINUE_BUTTON = "//a[@class='button-1 register-continue-button']";
	
	@FindBy(xpath = "//button[@id='register-button']")
	private WebElement registerButtonElement;
	
	@FindBy(xpath = "//input[@id='Email']")
	private WebElement emailElement;
	
	@FindBy(xpath = "//input[@id='FirstName']")
	private WebElement firstNameTextBoxElement;
	
	@FindBy(xpath = "//input[@id='LastName']")
	private WebElement lastNameTextBoxElement;
	
	@FindBy(xpath = "//input[@id='Password']")
	private WebElement passwordTextBoxElement;
	
	@FindBy(xpath = "//input[@id='ConfirmPassword']")
	private WebElement confirmPasswordTextBoxElement;
	
	@FindBy(xpath = "//span[@id='FirstName-error']")
	private WebElement firstNameErrorElement;
	
	@FindBy(xpath = "//span[@id='LastName-error']")
	private WebElement lastNameErrorElement;
	
	@FindBy(xpath = "//span[@id='Email-error']")
	private WebElement emailErrorMsgElement;
	
	@FindBy(xpath = "//span[@id='Password-error']")
	private WebElement passwordErrorMsgElement;
	
	@FindBy(xpath = "//span[@id='ConfirmPassword-error']")
	private WebElement confirmPasswordErrorMsgElement;

	@FindBy(xpath = "//div[@class='result']")
	private WebElement registerSuccessMsgElement;
	
	@FindBy(xpath = "//div[@class='message-error validation-summary-errors']")
	private WebElement existingEmailErrorMsgElement;
	
	@FindBy(xpath = "//a[@class='button-1 register-continue-button']")
	private WebElement continueButtonMsgElement;
	
	public void clickToRegisterButton() {
		waitForElementClickable(driver, registerButtonElement);
		clickToElement(driver, registerButtonElement);
		// TODO Auto-generated method stub

	}

	public String getErrorMessageAtFirstNameTextBox() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, firstNameErrorElement);
		return getElementText(driver, firstNameErrorElement);
	}

	public String getErrorMessageAtLastNameTextBox() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, lastNameErrorElement);
		return getElementText(driver, lastNameErrorElement);
	}

	public String getErrorMessageAtEmailTextBox() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, emailErrorMsgElement);
		return getElementText(driver, emailErrorMsgElement);
	}

	public String getErrorMessageAtPwTextBox() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, passwordErrorMsgElement);
		return getElementText(driver, passwordErrorMsgElement);
	}

	public String getErrorMessageAtConfirmPwTextBox() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, confirmPasswordErrorMsgElement);
		return getElementText(driver, confirmPasswordErrorMsgElement);
	}

	public void inputToConfirmPasswordTextBox(String confirmPassword) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, confirmPasswordTextBoxElement);
		sendkeyToElement(driver, confirmPasswordTextBoxElement, confirmPassword);

	}

	public void inputToFirstNameTextBox(String firstName) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, firstNameTextBoxElement);
		sendkeyToElement(driver, firstNameTextBoxElement, firstName);

	}

	public void inputToLastNameTextBox(String lastName) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, lastNameTextBoxElement);
		sendkeyToElement(driver, lastNameTextBoxElement, lastName);

	}

	public void inputToEmailTextBox(String email) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, emailElement);
		sendkeyToElement(driver, emailElement, email);

	}

	public void inputToPasswordTextBox(String password) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, passwordTextBoxElement);
		sendkeyToElement(driver, passwordTextBoxElement, password);

	}

	public String getRegisterSuccessMessage() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, registerSuccessMsgElement);
		return getElementText(driver, registerSuccessMsgElement);
	}

	public String getErrorExistingEmailMessage() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, existingEmailErrorMsgElement);
		return getElementText(driver, existingEmailErrorMsgElement);
	}

	public void gotoHomePage() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, continueButtonMsgElement);
		clickToElement(driver, continueButtonMsgElement);
	}
}
