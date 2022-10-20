package bid.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class ChargebeePage {

	static final Logger logger = Logger.getLogger(ChargebeePage.class.getName());

	/**
	 * my profile page locators
	 */
	public By search_customer = By.xpath("//input[@class=\"cn-menubar__search\"]");
	public By company = By.xpath("//tr/td//div[@class=\"cn-profile__lead\"]/a");
	public By basicPlan = By.xpath("//*[@class=\"c-list\"]//b");

}
