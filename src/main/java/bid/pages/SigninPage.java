package bid.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class SigninPage {

	static final Logger logger = Logger.getLogger(SigninPage.class.getName());

	/**
	 * signIn page locators
	 */
	public By close_popup = By.xpath("//a[@class=\"reactModalClose icon-cross\"]");
	public By dropDown_country = By.xpath("//*[@id=\"mobile-num\"]//span[@class=\"dropdown-icon\"]");
	public By set_geographies = By.xpath("//*[@id=\"search-bodyGEOGRAPHY\"]//*[contains(text(),\"+\")]");
	public By set_keywords = By.xpath("//*[@id=\"search-bodyKEYWORDS\"]//*[contains(text(),\"+\")]");
	public By plan_activate_popUp = By.xpath("//a[@class=\"reactModalClose icon-cross\"]");
	public By business_type = By.xpath("//*[@id=\"react-select-businessTypes-input\"]");
	public By select_consulting = By.xpath("//div[@class=\"select-one-option\"]//*[contains(text(),\"Consulting\")]");
	public By select_contractor = By.xpath("//div[@class=\"select-one-option\"]//*[contains(text(),\"Contractor\")]");
	public By business_type_dropDown = By.xpath("//*[contains(text(),\"Business Types\")]/../../..//div[@class=\"css-1wy0on6 ba-select__indicators\"]");
	public By submit_button = By.xpath("//*[@id=\"sign-in-submit\"]");
	

	public By select_country(String select_name) {
		By webElement = By.xpath("//div[@class=\"dropdown-content\"]//*[contains(text(),\"" + select_name + "\")]");
		return webElement;
	}
	
}
