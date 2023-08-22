package factoryEnviroments;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import commons.GlobalConstants;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SauceLabsFactory {
	private WebDriver driver;
	private String browserName;
	private String osName;

	public SauceLabsFactory(String browserName, String osName) {
		this.browserName = browserName;
		this.osName = osName;
	}

	public WebDriver creaDriver() {
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
		return driver;
	}
}
