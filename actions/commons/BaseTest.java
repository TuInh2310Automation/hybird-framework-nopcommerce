package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;

public class BaseTest {
	private WebDriver driver;
	private String projectPathString = System.getProperty("user.dir");
	protected WebDriver getBrowserDriver(String browserName) {
		switch (browserName) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver", projectPathString + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		case "h_chrome":
			System.setProperty("webdriver.chrome.driver", projectPathString + "\\browserDrivers\\chromedriver.exe");
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("-headless");
			chromeOptions.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(chromeOptions);
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", projectPathString + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			break;
		case "h_firefox":
			System.setProperty("webdriver.gecko.driver", projectPathString + "\\browserDrivers\\geckodriver.exe");
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("-headless");
			firefoxOptions.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(firefoxOptions);
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver", projectPathString + "\\browserDrivers\\msedgedriver.exe");
			driver = new EdgeDriver();
			break;

		case "opera":
			System.setProperty("webdriver.opera.driver", projectPathString + "\\browserDrivers\\operadriver.exe");
			driver = new OperaDriver();
			break;
		case "coccoc":
			
			// coccoc browser tru di 5,6 version thi ra version chrome driver
			System.setProperty("webdriver.chrome.driver", projectPathString + "\\browserDrivers\\chromedriver.exe");
			ChromeOptions coccocOption = new ChromeOptions();
			coccocOption.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
			driver = new ChromeDriver(coccocOption);
			break;
		case "brave":
			System.setProperty("webdriver.chrome.driver", projectPathString + "\\browserDrivers\\chromedriver.exe");
			ChromeOptions braveOption = new ChromeOptions();
			braveOption.setBinary("\"C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
			driver = new ChromeDriver(braveOption);
			break;
		default:
			break;
		}
		return driver;
	}

}
