package javaTC;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import commons.BaseTest;

@Listeners(commons.MethodListener.class)
public class Level21_AssertandVerify extends BaseTest {

	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com/");
	}

	@Test
	public void TC_01_ValidateCurrentURL() {
		System.out.println("Assert 01 - Pass");
		String loginPageUrlString = driver.getCurrentUrl();
		verifyEquals(loginPageUrlString, "https://www.facebook.com/");

		System.out.println("Assert 01 - Failed");
		String loginPageTitle = driver.getTitle();
		verifyEquals(loginPageTitle, "Facebook – log in or sign up....");

		System.out.println("Assert 03 - Pass");
		verifyTrue(driver.findElement(By.xpath("//form[@data-testid='royal_login_form']")).isDisplayed());

		System.out.println("Assert 04 - Fail");
		verifyTrue(driver.findElement(By.xpath("//input[@name='login_source']")).isDisplayed());

		System.out.println("Assert 05 - Pass");
		verifyTrue(driver.findElement(By.xpath("//div[@id='reg_pages_msg']")).isDisplayed());

	}
}
