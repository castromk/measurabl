package tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import utilities.ConfigurationReader;
import utilities.Driver;

public class BaseTest {

	static String url = "";
	static String browser = "";
	private static final Logger console = LoggerFactory.getLogger(BaseTest.class);

	@BeforeMethod
	public void beforeMethod() {
		url = ConfigurationReader.get("baseUrl");
		browser = ConfigurationReader.get("browser");
		Driver.get().get(url);
		console.info("Opening {} url in {}  browser successfully",url,browser);
	}

	@AfterMethod
	public void afterMethod() {
		Driver.closeDriver();
		console.info("closing browser");
	}

}
