package factoryEnviroments;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;

import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LocalFactory {
	private WebDriver driver;
	private String browserName;
	private String projectPathString = System.getProperty("user.dir");

	public LocalFactory(String browserName) {
		this.browserName = browserName;
	}
	public WebDriver creaDriver() {
		switch (browserName) {
		case "chrome":
			/*
			 * WebDriverManager.chromedriver().setup(); //Add extension to Chrome File translateChrome= new
			 * File(GlobalConstants.PROJECT_PATH+"\\browserExtensions\\extension_2_0_13_0.crx"); ChromeOptions chromeOptions = new ChromeOptions();
			 * chromeOptions.addExtensions(translateChrome); driver = new ChromeDriver(chromeOptions); break;
			 */

			WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.args", "--disable--logging");
			System.setProperty("webdriver.chrome.silentOutput", "true");
			driver = new ChromeDriver();
			break;
		case "h_chrome":
			// System.setProperty("webdriver.chrome.driver", projectPathString + "\\browserDrivers\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptionH = new ChromeOptions();
			chromeOptionH.addArguments("-headless");
			chromeOptionH.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(chromeOptionH);
			break;
		case "firefox":
			/*
			 * WebDriverManager.firefoxdriver().setup(); // System.setProperty("webdriver.gecko.driver", projectPathString + "\\browserDrivers\\geckodriver.exe"); //Add
			 * extension to Firefox FirefoxProfile profile=new FirefoxProfile(); File translate= new
			 * File(GlobalConstants.PROJECT_PATH+"\\browserExtensions\\to_google_translate-4.2.0.xpi"); profile.addExtension(translate);
			 * profile.setAcceptUntrustedCertificates(true); profile.setAssumeUntrustedCertificateIssuer(true); FirefoxOptions options=new FirefoxOptions();
			 * options.setProfile(profile); driver = new FirefoxDriver(options); break;
			 */
			WebDriverManager.firefoxdriver().setup();
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, GlobalConstants.PROJECT_PATH + "\\browserLogs\\FirefoxLog.log");
			driver = new FirefoxDriver();
			break;
		case "h_firefox":
			WebDriverManager.firefoxdriver().setup();
			// System.setProperty("webdriver.gecko.driver", projectPathString + "\\browserDrivers\\geckodriver.exe");
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.addArguments("-headless");
			firefoxOptions.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(firefoxOptions);
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			// System.setProperty("webdriver.edge.driver", projectPathString + "\\browserDrivers\\msedgedriver.exe");
			driver = new EdgeDriver();
			break;

		case "opera":
			WebDriverManager.operadriver().setup();
			System.setProperty("webdriver.opera.driver", projectPathString + "\\browserDrivers\\operadriver.exe");
			driver = new OperaDriver();
			break;
		case "coccoc":

			// coccoc browser tru di 5,6 version thi ra version chrome driver
			// WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver", projectPathString + "\\browserDrivers\\chromedriver.exe");
			ChromeOptions coccocOption = new ChromeOptions();
			if (GlobalConstants.OS_NAME.startsWith("Windows")) {
				coccocOption.setBinary("C:\\Program Files\\CocCoc\\Browser\\Application\\browser.exe");
			} else {
				coccocOption.setBinary("");
			}
			driver = new ChromeDriver(coccocOption);
			break;
		case "brave":
			WebDriverManager.chromedriver().setup();
			// System.setProperty("webdriver.chrome.driver", projectPathString + "\\browserDrivers\\chromedriver.exe");
			ChromeOptions braveOption = new ChromeOptions();
			braveOption.setBinary("C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe");
			driver = new ChromeDriver(braveOption);
			break;
		default:
			break;
		}
		return driver;
	}
}
