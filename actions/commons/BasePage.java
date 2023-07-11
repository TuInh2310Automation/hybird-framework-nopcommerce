package commons;

import static org.testng.Assert.assertNotNull;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.nopcommerce.common.Common_01_Register_Cookies;

import pageObjects.nopCommerce.admin.Admin_LoginPageObject;
import pageObjectsl.nopCommerce.user.User_AddressPageObject;
import pageObjectsl.nopCommerce.user.User_CustomerInfoPageObject;
import pageObjectsl.nopCommerce.user.User_HomePageObject;
import pageObjectsl.nopCommerce.user.User_MyProductReviewsPageObject;
import pageObjectsl.nopCommerce.user.User_RewardPointsPageObject;
import pageUI.jQuery.uploadFiles.BasePagejQueryUI;
import pageUI.nopCommerce.user.BasePageNopCommerceUI;

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

	public void setCookies(WebDriver driver,Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
			sleepInSecond(3);
		}
	}
	
	public Set<Cookie> getAllCookies(WebDriver driver){
		return driver.manage().getCookies();
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
			if (subStringLocator.length > 2) {
				for (int i = 2; i < subStringLocator.length; i++) {
					locatorString += "=" + subStringLocator[i];
				}
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

	private String getDynamicXpath(String locatorType, String... dynamicValues) {
		String[] subStringLocator = locatorType.split("=");
		if (subStringLocator.length >= 2 && subStringLocator[0].toLowerCase().equals("xpath")) {
			locatorType = String.format(locatorType, (Object[]) dynamicValues);
		}
		return locatorType;

	}

	public WebElement getWebElement(WebDriver driver, String locatorString) {
		return driver.findElement(getByLocator(locatorString));
	}

	public List<WebElement> getListWebElement(WebDriver driver, String locatorString) {
		return driver.findElements(getByLocator(locatorString));
	}

	public void clickToElement(WebDriver driver, String locatorString) {
		getWebElement(driver, locatorString).click();
	}

	public void clickToElement(WebDriver driver, String locatorString, String... dynamicValues) {
		getWebElement(driver, getDynamicXpath(locatorString, dynamicValues)).click();
	}

	public void sendkeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}

	public void sendkeyToElement(WebDriver driver, String locatorType, String textValue) {
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}

	public void selectItemInDefaultDropDown(WebDriver driver, String locatorType, String textItem, String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
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

	public String getElementText(WebDriver driver, String locatorString, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorString, dynamicValues)).getText();
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

	public int getElementSize(WebDriver driver, String xpathLocator, String... dynamicValues) {
		return getListWebElement(driver, getDynamicXpath(xpathLocator, dynamicValues)).size();
	}

	public void checkToDefaultCheckboxRadio(WebDriver driver, String locatorString) {
		WebElement element = getWebElement(driver, locatorString);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void checkToDefaultCheckboxRadio(WebDriver driver, String locatorString, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorString, dynamicValues));
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToDefaultCheckboxRadio(WebDriver driver, String locatorString, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorString, dynamicValues));
		if (element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToDefaultCheckboxRadio(WebDriver driver, String locatorString) {
		WebElement element = getWebElement(driver, locatorString);
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplay(WebDriver driver, String locatorString, String... dynamicValues) {
		try {
			// tìm thấy element
			// case 1: display: trả về true
			// case 2: undisplay: trả về false
			return getWebElement(driver, getDynamicXpath(locatorString, dynamicValues)).isDisplayed();
		} catch (NoSuchElementException e) {
			// case 3: ko thấy element
			return false;
		}
	}

	public boolean isElementDisplay(WebDriver driver, String locatorString) {
		try {
			// tìm thấy element
			// case 1: display: trả về true
			// case 2: undisplay: trả về false
			return getWebElement(driver, locatorString).isDisplayed();
		} catch (NoSuchElementException e) {
			// case 3: ko thấy element
			return false;
		}
	}

	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		System.out.println("Start Time = " + new Date().toString());
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> elementes = getListWebElement(driver, locator);
		// Neu như gán = 5, thì apply cho tất cả các step về sau của hàm findElement/ findElements
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
		if (elementes.size() == 0) {
			System.out.println("Case 3, Element ko có trong DOM");
			System.out.println("End Time = " + new Date().toString());
			return true;
		} else if (elementes.size()>0 && !elementes.get(0).isDisplayed()) {
			System.out.println("Case 2: element có trong dom nhưng ko visible, displayed");
			System.out.println("End Time = " + new Date().toString());
			return true;
		} else {
			System.out.println("case 1: element có trong DOM and displayed");
			return false;
		}
	}

	public void overrideGlobalTimeout(WebDriver driver, long shortTimeout) {
		driver.manage().timeouts().implicitlyWait(shortTimeout, TimeUnit.SECONDS);
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

	public void hoverMouseToElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}

	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
		// TODO Auto-generated method stub
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locatorType), key).perform();

	}

	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String dynamicValues) {
		// TODO Auto-generated method stub
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)), key).perform();
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
		return status;
	}

	public boolean isImageLoaded(WebDriver driver, String locatorString, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(driver, getDynamicXpath(locatorString, dynamicValues)));
		return status;
	}

	public void waitForElementVisible(WebDriver driver, String locatorString, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorString, dynamicValues))));
	}

	public void waitForElementVisible(WebDriver driver, String locatorString) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorString)));
	}

	public void waitForAllElementVisible(WebDriver driver, String locatorString, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorString, dynamicValues))));
	}

	public void waitForAllElementVisible(WebDriver driver, String locatorString) {
		WebDriverWait explicitWait = new WebDriverWait(driver,  GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorString)));
	}

	// wait for element undisplay in DOM or not in DOM. override explicit timeout
	public void waitForElementUndisplayed(WebDriver driver, String locatorString) {
		WebDriverWait explicitWait=new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
		overrideGlobalTimeout(driver,  GlobalConstants.SHORT_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorString)));
		overrideGlobalTimeout(driver,  GlobalConstants.LONG_TIMEOUT);
	}
	public void waitForElementInvisible(WebDriver driver, String locatorString, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorString, dynamicValues))));
	}

	public void waitForElementInvisible(WebDriver driver, String locatorString) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorString)));
	}

	public void waitForAllElementInvisible(WebDriver driver, String locatorString, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, getDynamicXpath(locatorString, dynamicValues))));
	}

	public void waitForAllElementInvisible(WebDriver driver, String locatorString) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locatorString)));
	}

	public void waitForElementClickable(WebDriver driver, String locatorString, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		System.out.println("locator" + getByLocator(getDynamicXpath(locatorString, dynamicValues)));
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorString, dynamicValues))));
	}

	public void waitForElementClickable(WebDriver driver, String locatorString) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorString)));
	}

	public void uploadMultipleFiles(WebDriver driver, String... fileNames) {
		String filePath = GlobalConstants.UPLOAD_FILE;
		String fullFileName = "";
		for (String fileName : fileNames) {
			fullFileName += filePath + fileName + "\n";
		}
		fullFileName = fullFileName.trim();
		getWebElement(driver, BasePagejQueryUI.UPLOAD_FILE).sendKeys(fullFileName);
	}

	public BasePage openPageAtMyAccountByName(WebDriver driver, String pageName) {
		waitForElementVisible(driver, BasePageNopCommerceUI.DYNAMIC_PAGE_AT_MYACCOUNT_LINK, pageName);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_PAGE_AT_MYACCOUNT_LINK, pageName);
		switch (pageName) {
		case "Customer info":
			return PageGeneratorManagement.getUserCustomerInfoPage(driver);
		case "Addresses":
			return PageGeneratorManagement.getUserAddressPage(driver);
		case "My product reviews":
			return PageGeneratorManagement.getUserMyProductReviewPage(driver);
		case "Reward points":
			return PageGeneratorManagement.getUserRewardPointsPage(driver);
		default:
			throw new RuntimeException("Invalid page name at my account area");
		}
	}

	public void openPageAtMyAccountByPageName(WebDriver driver, String pageName) {
		waitForElementVisible(driver, BasePageNopCommerceUI.DYNAMIC_PAGE_AT_MYACCOUNT_LINK, pageName);
		clickToElement(driver, BasePageNopCommerceUI.DYNAMIC_PAGE_AT_MYACCOUNT_LINK, pageName);
	}

	public User_AddressPageObject openAddressPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.ADDRESS_LINK);
		clickToElement(driver, BasePageNopCommerceUI.ADDRESS_LINK);
		return PageGeneratorManagement.getUserAddressPage(driver);
	}

	public User_RewardPointsPageObject openRewardPointPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.REWARD_POINT_LINK);
		clickToElement(driver, BasePageNopCommerceUI.REWARD_POINT_LINK);
		return PageGeneratorManagement.getUserRewardPointsPage(driver);
	}

	public User_MyProductReviewsPageObject openMyProductReviewsPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.MY_PRODUCT_LINK);
		clickToElement(driver, BasePageNopCommerceUI.MY_PRODUCT_LINK);
		return PageGeneratorManagement.getUserMyProductReviewPage(driver);
	}

	public User_CustomerInfoPageObject openCustomerInfoPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.CUSTOMER_INFO_LINK);
		clickToElement(driver, BasePageNopCommerceUI.CUSTOMER_INFO_LINK);
		return PageGeneratorManagement.getUserCustomerInfoPage(driver);
	}

	public User_HomePageObject clickToLogoutLinkAtUserPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.LOGOUT_USER_LINK);
		clickToElement(driver, BasePageNopCommerceUI.LOGOUT_USER_LINK);
		return PageGeneratorManagement.getUserHomePageObject(driver);
	}

	public Admin_LoginPageObject clickToLogoutLinkAtAdminPage(WebDriver driver) {
		waitForElementClickable(driver, BasePageNopCommerceUI.LOGOUT_ADMIN_LINK);
		clickToElement(driver, BasePageNopCommerceUI.LOGOUT_ADMIN_LINK);
		return PageGeneratorManagement.getAdminLoginPagee(driver);
	}

	private long longTimeout = GlobalConstants.LONG_TIMEOUT;

}