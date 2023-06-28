package commons;

import static org.testng.Assert.assertNotNull;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.nopCommerce.admin.Admin_LoginPageObject;
import pageObjectsl.nopCommerce.user.User_AddressPageObject;
import pageObjectsl.nopCommerce.user.User_CustomerInfoPageObject;
import pageObjectsl.nopCommerce.user.User_HomePageObject;
import pageObjectsl.nopCommerce.user.User_MyProductReviewsPageObject;
import pageObjectsl.nopCommerce.user.User_RewardPointsPageObject;
import pageUI.nopCommerce.user.BasePageUI;

public class BasePage {

	public static BasePage getBasePageObject() {
		return new BasePage();
	}

	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	public void sendKeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}

	// only 2 tabs
	public void switchToWindowFromID(WebDriver driver, String currentWindowID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(currentWindowID)) {
				driver.switchTo().window(id);
			}
		}
	}

	// More than 2 window/tab
	public void switchToWindowByPageTitle(WebDriver driver, String expectedPageTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String actualPageTitle = driver.getTitle();
			if (actualPageTitle.equals(expectedPageTitle)) {
				break;
			}
		}
	}

	public void closeAllWindowsWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindowSet = driver.getWindowHandles();
		for (String id : allWindowSet) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parentID);
	}

	private By getByLocator(String locatorType) {
		By by = null;
		String[] subStringLocator = locatorType.split("=");
		if (subStringLocator.length >= 2) {
			String type = subStringLocator[0].toLowerCase();
			String locatorString = subStringLocator[1];
			if (subStringLocator.length == 3) {
				locatorString += "=" + subStringLocator[2];
			}
			switch (type) {
			case "id":
				by = By.id(locatorString);
				break;
			case "class":
				by = By.className(locatorString);
				break;
			case "name":
				by = By.name(locatorString);
				break;
			case "css":
				by = By.cssSelector(locatorString);
				break;
			case "xpath":
				by = By.xpath(locatorString);
				break;
			default:
				throw new RuntimeException("Locator type is not support");
			}
		} else {
			throw new RuntimeException("Wrong format of locator");
		}
		return by;
	}

	private WebElement getWebElement(WebDriver driver, String locatorString) {
		return driver.findElement(getByLocator(locatorString));
	}

	private List<WebElement> getListWebElement(WebDriver driver, String locatorString) {
		return driver.findElements(getByLocator(locatorString));
	}

	public void clickToElement(WebDriver driver, String locatorString) {
		getWebElement(driver, locatorString).click();
	}

	public void sendkeyToElement(WebDriver driver, String locatorString, String textValue) {
		WebElement element = getWebElement(driver, locatorString);
		element.clear();
		element.sendKeys(textValue);
	}

	public void selectItemInDefaultDropDown(WebDriver driver, String locatorString, String textItem) {
		Select select = new Select(getWebElement(driver, locatorString));
		select.selectByValue(textItem);
	}

	public String getSelectItemDefaultDropDown(WebDriver driver, String locatorString) {
		Select select = new Select(getWebElement(driver, locatorString));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isMultipleDropDown(WebDriver driver, String locatorString) {
		Select select = new Select(getWebElement(driver, locatorString));
		return select.isMultiple();
	}

	public void selectItemInDropDown(WebDriver driver, String parentXpath, String childPath, String expectedTextItem) {
		// 1 Click vào 1 thẻ bất kì để n xổ hết ra các item của dropdown
		getWebElement(driver, parentXpath).click();
		// driver.findElement(By.cssSelector(parentCss)).click();

		// 2 Chờ cho tất cả các item được load ra thành công
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childPath)));
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedTextItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true)", item);
				sleepInSecond(1);
				item.click();
				break;
			}
		}
	}

	private void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getElementAttribut(WebDriver driver, String xpathLocator, String attributeName) {
		return getWebElement(driver, xpathLocator).getAttribute(attributeName);
	}

	public String getElementText(WebDriver driver, String locatorString) {
		return getWebElement(driver, locatorString).getText();
	}

	public String getElementCssValue(WebDriver driver, String locatorString, String propertyName) {
		return getWebElement(driver, locatorString).getCssValue(propertyName);
	}

	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	public int getElementSize(WebDriver driver, String xpathLocator) {
		return getListWebElement(driver, xpathLocator).size();
	}

	public void checkToDefaultCheckboxRadio(WebDriver driver, String locatorString) {
		WebElement element = getWebElement(driver, locatorString);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToDefaultCheckboxRadio(WebDriver driver, String locatorString) {
		WebElement element = getWebElement(driver, locatorString);
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplay(WebDriver driver, String locatorString) {
		return getWebElement(driver, locatorString).isDisplayed();
	}

	public boolean isElementEnabled(WebDriver driver, String locatorString) {
		return getWebElement(driver, locatorString).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String locatorString) {
		return getWebElement(driver, locatorString).isSelected();
	}

	public void switchToFrameIFrame(WebDriver driver, String locatorString) {
		driver.switchTo().frame(getWebElement(driver, locatorString));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String locatorString) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorString)).perform();
	}

	public Object executeForBrowser(WebDriver driver, String javaScript) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(WebDriver driver, String url) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, String locatorString) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locatorString);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locatorString) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorString));
	}

	public void scrollToElement(WebDriver driver, String locatorString) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorString));
	}

	public void sendkeyToElementByJS(WebDriver driver, String locatorString, String value) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locatorString));
	}

	public void removeAttributeInDOM(WebDriver driver, String locatorString, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorString));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locatorString) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorString));
	}

	public boolean isImageLoaded(WebDriver driver, String locatorString) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locatorString));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void waitForElementVisible(WebDriver driver, String locatorString) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorString)));
	}

	public void waitForAllElementVisible(WebDriver driver, String locatorString) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorString)));
	}

	public void waitForElementInvisible(WebDriver driver, String locatorString) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorString)));
	}

	public void waitForAllElementInvisible(WebDriver driver, String locatorString) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorString)));
	}

	public void waitForElementClickable(WebDriver driver, String locatorString) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorString)));
	}

	public User_AddressPageObject openAddressPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.ADDRESS_LINK);
		clickToElement(driver, BasePageUI.ADDRESS_LINK);
		return PageGeneratorManagement.getUserAddressPage(driver);
	}

	public User_RewardPointsPageObject openRewardPointPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.REWARD_POINT_LINK);
		clickToElement(driver, BasePageUI.REWARD_POINT_LINK);
		return PageGeneratorManagement.getUserRewardPointsPage(driver);
	}

	public User_MyProductReviewsPageObject openMyProductReviewsPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.MY_PRODUCT_LINK);
		clickToElement(driver, BasePageUI.MY_PRODUCT_LINK);
		return PageGeneratorManagement.getUserMyProductReviewPage(driver);
	}

	public User_CustomerInfoPageObject openCustomerInfoPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.CUSTOMER_INFO_LINK);
		clickToElement(driver, BasePageUI.CUSTOMER_INFO_LINK);
		return PageGeneratorManagement.getUserCustomerInfoPage(driver);
	}

	public User_HomePageObject clickToLogoutLinkAtUserPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.LOGOUT_USER_LINK);
		clickToElement(driver, BasePageUI.LOGOUT_USER_LINK);
		return PageGeneratorManagement.getUserHomePageObject(driver);
	}

	public Admin_LoginPageObject clickToLogoutLinkAtAdminPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.LOGOUT_ADMIN_LINK);
		clickToElement(driver, BasePageUI.LOGOUT_ADMIN_LINK);
		return PageGeneratorManagement.getAdminLoginPagee(driver);
	}

	private long longTimeout = 30;

}