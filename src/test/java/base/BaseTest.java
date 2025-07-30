package base;

import config.APIConfig;
import config.BrowserStackConfig;
import config.EnvConfig;
import io.restassured.RestAssured;
import utils.TestContext;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.MutableCapabilities;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class BaseTest {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	public static WebDriver getDriver() {
	    return driver.get();
	}

	@Parameters({ "device", "browserstack" })
	@BeforeClass
	public void setUp(@Optional("desktop-chrome") String device, @Optional("false") String browserstack) {
		TestContext.setDevice(device);
		TestContext.setBrowserStack(browserstack);
		
		RestAssured.baseURI = APIConfig.get("base.url");
		
		try {
			if (browserstack.equalsIgnoreCase("true")) {
				driver.set(getRemoteDriverW3C(device));
			} else {
				if (device.equalsIgnoreCase("desktop-chrome")) {
					System.setProperty("webdriver.chrome.driver", "D:\\Work\\SeleniumWD\\driver\\chromedriver.exe");
					driver.set(new ChromeDriver());
				}
				getDriver().manage().window().maximize();
				getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			}

			getDriver().get(EnvConfig.get("url"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to set up WebDriver for device: " + device, e);
		}
	}

	private WebDriver getRemoteDriverW3C(String device) throws MalformedURLException {
		MutableCapabilities capabilities = new MutableCapabilities();
		HashMap<String, Object> bstackOptions = new HashMap<>();

		switch (device) {
		case "desktop-chrome":
			capabilities.setCapability("browserName", "Chrome");
			capabilities.setCapability("browserVersion", "latest");
			bstackOptions.put("os", "Windows");
			bstackOptions.put("osVersion", "11");
			break;

		case "desktop-firefox":
			capabilities.setCapability("browserName", "Firefox");
			capabilities.setCapability("browserVersion", "latest");
			bstackOptions.put("os", "Windows");
			bstackOptions.put("osVersion", "11");
			break;

		case "desktop-safari":
			capabilities.setCapability("browserName", "Safari");
			capabilities.setCapability("browserVersion", "latest");
			bstackOptions.put("os", "OS X");
			bstackOptions.put("osVersion", "Monterey");
			break;

		case "mobile-android":
			capabilities.setCapability("browserName", "Chrome");
			bstackOptions.put("deviceName", "Samsung Galaxy S20 Ultra");
			bstackOptions.put("realMobile", "true");
			break;

		case "mobile-ios":
			capabilities.setCapability("browserName", "Safari");
			bstackOptions.put("deviceName", "iPhone 14");
			bstackOptions.put("realMobile", "true");
			break;

		default:
			throw new IllegalArgumentException("Unsupported device: " + device);
		}

		// Set credentials and common options
		bstackOptions.put("userName", BrowserStackConfig.get("browserstack.user"));
		bstackOptions.put("accessKey", BrowserStackConfig.get("browserstack.key"));
		bstackOptions.put("sessionName", "Test on " + device);
		bstackOptions.put("buildName", "Run - " + System.currentTimeMillis());

		// Attach bstack:options to W3C capabilities
		capabilities.setCapability("bstack:options", bstackOptions);

		return new RemoteWebDriver(new URL("https://hub-cloud.browserstack.com/wd/hub"), capabilities);
	}

	@AfterClass
	public void tearDown() {
		if (driver.get() != null) {
	        driver.get().quit();
	        driver.remove();
	    }
		TestContext.clear();
	}
}