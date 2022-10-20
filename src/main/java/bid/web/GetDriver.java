package bid.web;

import org.openqa.selenium.WebDriver;

public class GetDriver {
	public static WebDriver driver = null;

	public static WebDriver getDriver() {
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		GetDriver.driver = driver;
	}
}
