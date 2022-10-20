package bid.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class PricingPage {

	static final Logger logger = Logger.getLogger(PricingPage.class.getName());

	/**
	 * my Pricing page locators
	 */
	public By iFrameSubscription = By.xpath("//*[@id=\"plans\"]");
	
	
}
