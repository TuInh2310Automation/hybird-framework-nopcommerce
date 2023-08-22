package commons;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.org.eclipse.jdt.internal.core.BecomeWorkingCopyOperation;
import org.bouncycastle.est.EnrollmentResponse;
import org.joda.time.DateTime;
import org.joda.time.DateTime.Property;
import org.joda.time.format.DateTimeFormat;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import factoryEnviroments.BrowserStackFactory;
import factoryEnviroments.GridFactory;
import factoryEnviroments.LocalFactory;
import factoryEnviroments.SauceLabsFactory;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	private String projectPathString = System.getProperty("user.dir");
	protected Log log;

	@BeforeSuite
	public void initBeforeSuite() {
		deleteAllFilePreviousAllureInFolder();
	}

	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	protected WebDriver getBrowserDriver(String envName, String serverName, String browserName, String ipAddress, String portNumber, String osName, String osVersion) {
		switch (envName) {
		case "local":
			driver = new LocalFactory(browserName).creaDriver();
			break;
		case "grid":
			driver= new GridFactory(browserName, ipAddress, portNumber).creaDriver();
			break;
		case "browserStack":
			driver= new BrowserStackFactory(browserName, osName, osVersion).creaDriver();
			break;
		case "sauceLab":
			driver = new SauceLabsFactory(browserName, osName).creaDriver();
			break;

		default:
			driver = new LocalFactory(browserName).creaDriver();
			break;
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(getEnviromentUrlString(serverName));
		driver.manage().window().maximize();
		return driver;
	}

	protected WebDriver getBrowserDriverLocal(String browserName) {
		switch (browserName) {
		case "chrome":
			// System.setProperty("webdriver.chrome.driver", projectPathString + "\\browserDrivers\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "h_chrome":
			// System.setProperty("webdriver.chrome.driver", projectPathString + "\\browserDrivers\\chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("-headless");
			chromeOptions.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(chromeOptions);
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			// System.setProperty("webdriver.gecko.driver", projectPathString + "\\browserDrivers\\geckodriver.exe");
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
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(GlobalConstants.ADMIN_DEV_URL);
		driver.manage().window().maximize();
		return driver;
	}

	protected WebDriver getBrowserDriverLocal(String browserName, String appUrl) {
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
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(appUrl);
		driver.manage().window().maximize();
		return driver;
	}

	protected WebDriver getBrowserDriverGRID(String browserName, String appUrl, String osName, String ipAddress, String portNumber) {
		DesiredCapabilities capability = null;
		Platform platform = null;

		if (osName.contains("windows")) {
			platform = Platform.WINDOWS;
		} else {
			platform = Platform.MAC;
		}

		switch (browserName) {
		case "firefox":
			capability = DesiredCapabilities.firefox();
			capability.setBrowserName("firefox");
			capability.setPlatform(platform);

			FirefoxOptions fOptions = new FirefoxOptions();
			fOptions.merge(capability);
			break;
		case "chrome":
			capability = DesiredCapabilities.chrome();
			capability.setBrowserName("chrome");
			capability.setPlatform(platform);

			ChromeOptions cOptions = new ChromeOptions();
			cOptions.merge(capability);
			break;
		case "edge":
			capability = DesiredCapabilities.edge();
			capability.setBrowserName("edge");
			capability.setPlatform(platform);

			EdgeOptions eOptions = new EdgeOptions();
			eOptions.merge(capability);
			break;
		default:
			throw new RuntimeException("Browser is not valid!");
		}

		try {
			driver = new RemoteWebDriver(new URL(String.format("http://%s:%s/wd/hub", ipAddress, portNumber)), capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.get(appUrl);
		return driver;
	}

	protected WebDriver getBrowserDriverBrowserStack(String browserName, String appUrl, String osName, String osVersion) {
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("os", osName);
		capability.setCapability("os_version", osVersion);
		capability.setCapability("browser", browserName);
		capability.setCapability("browser_version", "latest");
		capability.setCapability("browserstack.debug", "true");
		capability.setCapability("project", "Nopcommerce");
		capability.setCapability("name", "Run on " + osName + " | " + osVersion + " | " + browserName);
		if (osName.contains("Windows")) {
			capability.setCapability("resolution", "1920x1080");
		} else {
			capability.setCapability("resolution", "1920x1440");
		}
		try {
			driver = new RemoteWebDriver(new URL(GlobalConstants.BROWSER_STACK_URL), capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(appUrl);
		return driver;
	}

	protected WebDriver getBrowserDriverSauceLabs(String browserName, String appUrl, String osName) {
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("platformName", osName);
		capability.setCapability("browserName", browserName);
		capability.setCapability("browserVersion", "latest");
		capability.setCapability("name", "Run on " + osName + " | " + browserName);

		Map<String, Object> sauceOptionsMap = new HashMap<>();
		if (osName.contains("Windows")) {
			sauceOptionsMap.put("screenSolution", "1920x1080");
		} else {
			sauceOptionsMap.put("screenSolution", "1920x1440");
		}

		capability.setCapability("sauce:options", sauceOptionsMap);

		try {
			driver = new RemoteWebDriver(new URL(GlobalConstants.SAUCE_LABS_URL), capability);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(appUrl);
		return driver;
	}

	protected WebDriver getBrowserDriverWithEnviroment(String browserName, String enviromentName) {
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
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(getEnviromentUrlString(enviromentName));
		driver.manage().window().maximize();
		return driver;
	}

	protected String getEnviromentUrlString(String serverName) {
		String envUrlString = null;
		EnviromentList enviroment = EnviromentList.valueOf(serverName.toUpperCase());
		switch (enviroment) {
		case DEV:
			envUrlString = "https://demo.nopcommerce.com/";
			break;
		case TESTING:
			envUrlString = "https://testing.nopcommerce.com/";
			break;
		case STAGING:
			envUrlString = "https://staging.nopcommerce.com/";
			break;
		case PRE_PRODUCT:
			envUrlString = "https://pre-prod.nopcommerce.com/";
			break;
		case PRODUCT:
			envUrlString = "https://prod.nopcommerce.com/";
			break;

		default:
			envUrlString = null;
			break;
		}
		return envUrlString;
	}

	protected String getCurrentDate() {
		DateTime nowUTC = new DateTime();
		int day = nowUTC.getDayOfMonth();
//		if (day < 10) {
//			return "0" + day;
//		}
		return String.valueOf(day);
	}

	protected String getCurrentMonth() {
		DateTime nowUTC = new DateTime();
		String month = nowUTC.toString("MMMM");
		return month;
//		if (month < 10) {
//			return "0" + month;
//		}
//		return String.valueOf(month);
	}

	protected String getCurrentYear() {
		DateTime nowUTC = new DateTime();
		return String.valueOf(nowUTC.getYear());
	}

	protected String getCurrentDay() {
		return getCurrentMonth() + " " + getCurrentDate() + ", " + getCurrentYear();
	}

	protected boolean verifyTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertFalse(condition);
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			log.info(" -------------------------- FAILED -------------------------- ");
			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			System.out.println(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			System.out.println(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	public void deleteAllFilePreviousAllureInFolder() {
		try {
			String workingDir = GlobalConstants.PROJECT_PATH + "/allure-json";
			// String pathFolderDownload = workingDir + "\\ReportNGScreenshots";
			String pathFolderDownload = workingDir;
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					System.out.println(listOfFiles[i].getName());
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
	}

	protected void closeBrowserDriver() {
		String cmd = null;
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			String driverInstanceName = driver.toString().toLowerCase();
			log.info("Driver instance name = " + driverInstanceName);

			String browserDriverName = null;

			if (driverInstanceName.contains("chrome")) {
				browserDriverName = "chromedriver";
			} else if (driverInstanceName.contains("internetexplorer")) {
				browserDriverName = "IEDriverServer";
			} else if (driverInstanceName.contains("firefox")) {
				browserDriverName = "geckodriver";
			} else if (driverInstanceName.contains("edge")) {
				browserDriverName = "msedgedriver";
			} else if (driverInstanceName.contains("opera")) {
				browserDriverName = "operadriver";
			} else {
				browserDriverName = "safaridriver";
			}

			if (osName.contains("window")) {
				cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
			} else {
				cmd = "pkill " + browserDriverName;
			}

			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
