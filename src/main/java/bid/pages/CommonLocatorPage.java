package bid.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class CommonLocatorPage {
	
	static final Logger logger = Logger.getLogger(CommonLocatorPage.class.getName());

	/**
	 * common page locators
	 */

	public By common_text(String select_name) {
		By webElement = By.xpath("//*[contains(text(),\"" + select_name + "\")]");
		return webElement;
	}
	
	public By common_button_a(String select_name) {
		By webElement = By.xpath("//a[contains(text(),\"" + select_name + "\")]");
		return webElement;
	}
	
	public By common_button(String select_name) {
		By webElement = By.xpath("//button[contains(text(),\"" + select_name + "\")]");
		return webElement;
	}
	
	public By common_companyInfo(String select_name) {
		By webElement = By.xpath("//td[contains(text(),\"" + select_name + "\")]");
		return webElement;
	}
	
	public By common_listInfo(String select_name) {
		By webElement = By.xpath("//li[contains(text(),\"" + select_name + "\")]");
		return webElement;
	}
	
	public By common_button_span(String select_name) {
		By webElement = By.xpath("//span[contains(text(),\"" + select_name + "\")]");
		return webElement;
	}

	public By common_text_inputField(String select_name) {
		By webElement = By.xpath("//input[@name=\"" + select_name + "\"]");
		return webElement;
	}
	
	public By common_text_inputValueField(String select_name) {
		By webElement = By.xpath("//input[(@value=\""+ select_name + "\")]");
		return webElement;
	}

	public By common_field_value(String select_name) {
		By webElement = By.xpath("//*[contains(text(),\"" + select_name + "\")]/../..//input");
		return webElement;
	}

	public By verify_profile(String select_name, String select_name2) {
		By webElement = By.xpath("//*[contains(text(),\"" + select_name + "\")]/..//*[contains(text(),\"" + select_name2 + "\")]");
		return webElement;
	}
	
	public By common_cbSection(String select_name) {
		By webElement = By.xpath("//*[@id=\"cb-body\"]//*[contains(text(),\"" + select_name + "\")]");
		return webElement;
	}
	
}
