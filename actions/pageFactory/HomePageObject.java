package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commons.BasePage;
import commons.BasePageFactory;
import pageUI.nopCommerce.user.HomePageUI;

public class HomePageObject extends BasePageFactory{

	private WebDriver driver;
	public HomePageObject(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Page UI
	@FindBy(how = How.XPATH, using = "//a[@class='ico-register']")
	private WebElement registerLinkElement;

	@FindBy(xpath = "//a[@class='ico-login']")
	private WebElement loginLinkElement;
	
	@FindBy(css =  "a.ico-register")
	private WebElement myAccountLinkElement;
	
	public void clickToRegisterLink() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, registerLinkElement);
		clickToElement(driver, registerLinkElement);
		
		
	}
	public void clickToLoginLink() {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, loginLinkElement);
		clickToElement(driver,loginLinkElement);
		
	}
	public boolean isMyAccountLinkDisplayed() {
		// TODO Auto-generated method stub
		waitForElementVisible(driver, myAccountLinkElement);
		return isElementDisplay(driver, myAccountLinkElement);
	}

}
