package utilities;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	private static WebDriver driver;
	private static Properties configFile;

	public static void setDriver(String browser) {

		switch (browser) {

		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;

		case "chrome-headless":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
			break;

		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;

		case "firefox-headless":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver(new FirefoxOptions().setHeadless(true));
			break;

		case "edge":
			if (!System.getProperty("os.name").toLowerCase().contains("windows")) {
				throw new WebDriverException("Your OS doesn't support Edge");
			}
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;

		case "safari":
			if (!System.getProperty("os.name").toLowerCase().contains("mac")) {
				throw new WebDriverException("Your OS doesn't support Safari");
			}
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
			break;

		}
		driver.get(getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PageInitializer.initialize();

	}

	public static WebDriver getDriver() {
		return driver;
	}

	public static void tearDown() {

		if (driver != null) {
//			driver.close();
			driver.quit();
			driver = null;
		}
	}

	static {
		try {

			String filePath = "src/test/resources/propertyFiles/configData.properties";

			FileInputStream input = new FileInputStream(filePath);

			configFile = new Properties();
			configFile.load(input);

			input.close();

		} catch (Exception e) {

		}

	}

	public static String getProperty(String keyName) {
		return configFile.getProperty(keyName);
	}

	public static WebDriver setParallelDriver() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
//		driver.get(getProperty("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		PageInitializer.initialize();
		
		return driver;

	}

}
