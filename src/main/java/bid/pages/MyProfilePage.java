package bid.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import bid.web.GetDriver;

public class MyProfilePage extends GetDriver {

	static final Logger logger = Logger.getLogger(MyProfilePage.class.getName());

	/**
	 * my profile page locators
	 */
	public By profile_button = By.xpath("//div[@class=\"dropdown\"]/div[@class=\"dropdown-toggle\"]/span[@data-vars-event-action=\"view my account menu\"]");
	public By activeOn = By.xpath("//*[contains(text(),\"Active on\")]/../div[2]");
	public By iFrame = By.xpath("//*[@id=\"cb-container\"]/iframe");
	public By trialMoney = By.xpath("//div[@class=\"cb-section\"]//div[@class=\"cb-section__left\"]/span");
	public By valueOfGlobalAllRegions = By.xpath("//*[@id=\"cb-body\"]//*[@class=\"cb-cart cb-cart--readonly cb-cart--multiple\"]/div[1]//h2");	
	public By valueOfTenderResults = By.xpath("//*[@id=\"cb-body\"]//*[@class=\"cb-cart cb-cart--readonly cb-cart--multiple\"]/div[2]//h2");
	public By valueOfDataInExcel = By.xpath("//*[@id=\"cb-body\"]//*[@class=\"cb-cart cb-cart--readonly cb-cart--multiple\"]/div[3]//h2");
	public By introductoryOffer = By.xpath("//*[@id=\"cb-body\"]//*[contains(text(),\"Introductory Offer\")]/../div[2]");
	public By closePopup = By.xpath("//*[@id=\"app\"]/div[2]");
	
	public List<WebElement> listOfTrialMoney() {
		List<WebElement> webElement = driver.findElements(By.xpath("//*[@id=\"cb-body\"]//div[@class=\"cb-cart cb-cart--readonly cb-cart--multiple\"]//div/div/div/h2/span"));
		return webElement;
	}
	
}
