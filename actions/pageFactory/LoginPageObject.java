package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;


public class LoginPageObject extends BasePageFactory{

	private WebDriver driver;
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@class='email']")
	private WebElement emailTextBoxElement;
	
	@FindBy(xpath = "//input[@class='password']")
	private WebElement pwTextBoxElement;
	
	@FindBy(xpath = "//button[@class='button-1 login-button']")
	private WebElement loginButtonElement;
	
	@FindBy(xpath = "//span[@id='Email-error']")
	private WebElement emailErrorMessageElement;
	
	@FindBy(xpath = "//div[@class='message-error validation-summary-errors']")
	private WebElement unsuccessMessageElement;
	

	public void clickLoginButton() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, loginButtonElement);
		clickToElement(driver, loginButtonElement);
	}

	public String getErrorMessageAtEmailTextBox() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, emailErrorMessageElement);
		return getElementText(driver, emailErrorMessageElement);
	}

	public void inputToEmailTextBox(String email) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, emailTextBoxElement);
		sendkeyToElement(driver, emailTextBoxElement, email);
		
	}

	public String getUnSuccessMessage() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, unsuccessMessageElement);
		return getElementText(driver, unsuccessMessageElement);
	}

	public void inputToPasswordTextBox(String password) {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, pwTextBoxElement);
		sendkeyToElement(driver, pwTextBoxElement, password);
	}

}
