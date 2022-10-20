package bid.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public class CheckoutPage {

	static final Logger logger = Logger.getLogger(CheckoutPage.class.getName());

	/**
	 * Checkout page locators
	 */
	public By buy_now(String select_name, String select_name2) {
		By webElement = By.xpath("//*[contains(text(),\"" + select_name + "\")]/../..//*[contains(text(),\"" + select_name2 + "\")]");
		return webElement;
	}
	public By firstiFrame = By.xpath("//*[@id=\"iframe-payment\"]");
	public By select_regionDropdown = By.xpath("//*[@id=\"base-region-select\"]/../div/button");
	public By select_europe = By.xpath("/html/body/div[4]/div/ul/li[@id=\"base-region-select-option-4\"]");
	public By select_PaidRegionDropdown = By.xpath("//*[@id=\":r6:\"]/../div/button");
	public By select_additionRegion = By.xpath("//*[@id=\":r6:-option-3\"]");
	public By value_additionRegion = By.xpath("//p[contains(text(),\"Balkans\")]/../../div[3]/p");
	public By add_onFeature = By.xpath("//*[contains(text(),\"Get Tender Data in Excel\")]/../../button");
	public By companyName = By.xpath("//input[@id=\"bootstrap-input\" and @placeholder=\"Enter company name\"]");
	public By companyPostal = By.xpath("//input[@id=\"bootstrap-input\" and @placeholder=\"Enter postal code\"]");
	public By companyAddress = By.xpath("//input[@id=\"bootstrap-input\" and @placeholder=\"Enter Address Line 1\"]");
	public By companyProvince = By.xpath("//input[@id=\"bootstrap-input\" and @placeholder=\"Enter State\"]");
	public By companyCity = By.xpath("//input[@id=\"bootstrap-input\" and @placeholder=\"Enter city\"]");
	public By enter_countryDropdown = By.xpath("//*[@id=\"country-select-demo\"]");
	public By select_germany = By.xpath("//*[@id=\"country-select-demo-option-0\"]");
	public By get_basicValue = By.xpath("//*[contains(text(),\"Order Summary\")]/../../div[2]/p[1]");
	public By get_discountValue = By.xpath("//*[@class=\"css-10rd4rg\"]/p");
	public By get_totalValue = By.xpath("//*[contains(text(),\"Total Amount\")]/../p[2]");
	public By payNow = By.xpath("//*[@class=\"css-1aji7io\"]/button");
	public By secondiFrame = By.xpath("//*[@id=\"cb-frame\"]");
	public By proceedToCheckout = By.xpath("//*[@id=\"cb-body\"]/div/div[2]/div/button");
	public By firstNameCard = By.xpath("//*[@id=\"first_name\"]");
	public By lastNameCard = By.xpath("//*[@id=\"last_name\"]");
	public By cardNumber = By.xpath("//*[@id=\"number\"]");
	public By expiryMonth = By.xpath("//*[@id=\"exp_month\"]");
	public By expiryYr = By.xpath("//*[@id=\"exp_year\"]");
	public By cvvCard = By.xpath("//*[@id=\"cvv\"]");
	public By payAndSubscribe = By.xpath("//*[@id=\"cb-body\"]//button/span");
	
}
