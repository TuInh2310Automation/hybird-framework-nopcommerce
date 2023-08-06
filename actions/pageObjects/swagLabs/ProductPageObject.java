package pageObjects.swagLabs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import commons.BasePage;
import pageUI.swagLab.ProductPageUI;

public class ProductPageObject extends BasePage {
	WebDriver driver;

	public ProductPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void selectItemInProductSortDropdown(String textItem) {
		// TODO Auto-generated method stub
		waitForElementClickable(driver, ProductPageUI.PRODUCT_CONTAINER_DROPDOWN);
		selectItemInDefaultDropDown(driver, ProductPageUI.PRODUCT_CONTAINER_DROPDOWN, textItem);
	}

	public boolean isProductNameSortByAscending() {
		// TODO Auto-generated method stub
		ArrayList<String> productList = new ArrayList<String>();
		List<WebElement> productNameLst = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);
		for (WebElement productName : productNameLst) {
			productList.add(productName.getText());
		}

		ArrayList<String> productSortList = new ArrayList<String>();
		for (String product : productList) {
			productSortList.add(product);
		}
		Collections.sort(productSortList);

		return productSortList.equals(productList);
	}

	public boolean isProductNameSortByDescending() {
		// TODO Auto-generated method stub
				ArrayList<String> productList = new ArrayList<String>();
				List<WebElement> productNameLst = getListWebElement(driver, ProductPageUI.PRODUCT_NAME_TEXT);
				for (WebElement productName : productNameLst) {
					productList.add(productName.getText());
				}

				ArrayList<String> productSortList = new ArrayList<String>();
				for (String product : productList) {
					productSortList.add(product);
				}
				Collections.sort(productSortList);
				Collections.reverse(productSortList);

				return productSortList.equals(productList);
	}

	public boolean isProductPriceSortByAscending() {
		// TODO Auto-generated method stub
		ArrayList<Float> productList = new ArrayList<Float>();
		List<WebElement> productPriceLst = getListWebElement(driver, ProductPageUI.PRODUCT_PRICE_TEXT);
		for (WebElement productPrice : productPriceLst) {
			productList.add(Float.parseFloat(productPrice.getText().replace("$", "")));
		}

		ArrayList<Float> productSortList = new ArrayList<Float>();
		for (Float product : productList) {
			productSortList.add(product);
		}
		Collections.sort(productSortList);

		return productSortList.equals(productList);
	}

	public boolean isProductPriceSortByDescending() {
		// TODO Auto-generated method stub
		ArrayList<Float> productList = new ArrayList<Float>();
		List<WebElement> productPriceLst = getListWebElement(driver, ProductPageUI.PRODUCT_PRICE_TEXT);
		for (WebElement productPrice : productPriceLst) {
			productList.add(Float.parseFloat(productPrice.getText().replace("$", "")));
		}

		ArrayList<Float> productSortList = new ArrayList<Float>();
		for (Float product : productList) {
			productSortList.add(product);
		}
		Collections.sort(productSortList);
		Collections.reverse(productSortList);

		return productSortList.equals(productList);
	}

}
