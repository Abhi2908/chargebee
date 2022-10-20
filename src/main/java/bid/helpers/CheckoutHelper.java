package bid.helpers;

import org.apache.log4j.Logger;

import bid.pages.ChargebeePage;
import bid.pages.CheckoutPage;
import bid.pages.CommonLocatorPage;
import bid.pages.SigninPage;
import bid.web.CommonUtils;
import bid.web.TestData;

public class CheckoutHelper extends CommonUtils {

	SigninPage signinPage = new SigninPage();
	CommonLocatorPage commonPage = new CommonLocatorPage();
	ChargebeePage chargebeePage = new ChargebeePage();
	PricingHelper pricingHelper = new PricingHelper();
	CheckoutPage checkoutPage = new CheckoutPage();

	static final Logger logger = Logger.getLogger(CheckoutHelper.class.getName());
	double reginPaidYearly;

	/**
	 * @param checkout scenario
	 * @throws Exception
	 */
	public void checkoutBuynow() throws Exception {

		// wait to load page
		waitForPageLoad();
		Thread.sleep(4000);

		if (checkElementPresence(commonPage.common_text("Login Here"))) {

			moveToWebElementAndClick(commonPage.common_button("Login Here"));

		}
		
		pricingHelper.annualPricing();

		// click on buy now
		waitForElementToBeClickable(checkoutPage.buy_now(TestData.ANNUAL_BASIC, "Buy Now"));
		javaScriptClickUsingBy(checkoutPage.buy_now(TestData.ANNUAL_BASIC, "Buy Now"));

		Thread.sleep(4000);

		// check presence iFrames
		iframeToBeAvailableAndSwitchToIt(checkoutPage.firstiFrame);

		waitUntilLoadingPage("Order Summary", commonPage.common_text("Order Summary"));

		// Enter values in text fields
		waitForElementToBeClickable(checkoutPage.select_regionDropdown);
		javaScriptClickUsingBy(checkoutPage.select_regionDropdown);
		Thread.sleep(4000);
		javaScriptClickUsingBy(checkoutPage.select_europe);

		// Enter values in text fields
		Thread.sleep(7000);
		javaScriptClickUsingBy(checkoutPage.select_PaidRegionDropdown);
		Thread.sleep(4000);
		javaScriptClickUsingBy(checkoutPage.select_additionRegion);

		// Get paid region
		String getIntroductoryOffer = textFromApplication(checkoutPage.value_additionRegion);
		double reginPaidMonthly = removeSpecialCharAndGetValueInDouble(getIntroductoryOffer, 0);
		reginPaidYearly = (reginPaidMonthly * 12);

		// click on Add on Feature
		waitForElementToBeClickable(checkoutPage.add_onFeature);
		javaScriptClickUsingBy(checkoutPage.add_onFeature);

		// Adding Billing Details
		billingDetails();

		// Get Basic value
		String getBasicValue = textFromApplication(checkoutPage.get_basicValue);
		double basicValue = removeFirstValuesAndGetValueInDouble(getBasicValue, 1);

		// Get Discount value
		String getDiscountValue = textFromApplication(checkoutPage.get_discountValue);
		double discountValue = removeFirstValuesAndGetValueInDouble(getDiscountValue, 3);

		// Get Tender data value
		double tenderInExcel = 96.00;

		double expTotalValue = (basicValue + tenderInExcel + reginPaidYearly) - (discountValue);

		// Get Total Amt value
		String gettotalAmount = textFromApplication(checkoutPage.get_totalValue);
		double totalValue = removeFirstValuesAndGetValueInDouble(gettotalAmount, 1);

		// Asserting values
		assertDouble(expTotalValue, totalValue);

		// click on pay now
		waitForElementToBeClickable(checkoutPage.payNow);
		javaScriptClickUsingBy(checkoutPage.payNow);

		// second iframe
		iframeToBeAvailableAndSwitchToIt(checkoutPage.secondiFrame);

		// checkout
		Thread.sleep(20000);

		waitForElementToBeClickable(checkoutPage.proceedToCheckout);
		javaScriptClickUsingBy(checkoutPage.proceedToCheckout);
		
		// Payment details
		paymentDetails();
		
	}

	public void billingDetails() throws Exception {

		// billing detail form
		waitFindEnterTextAsList(checkoutPage.companyName, TestData.COMPANY_NAME);
		waitFindEnterTextAsList(checkoutPage.companyPostal, TestData.COMPANY_POSTAL);
		waitFindEnterTextAsList(checkoutPage.companyAddress, TestData.COMPANY_ADDRESS);
		waitFindEnterTextAsList(checkoutPage.companyProvince, TestData.COMPANY_PROVINCE);
		waitFindEnterTextAsList(checkoutPage.companyCity, TestData.COMPANY_CITY);

		// Enter country
		waitFindEnterTextAsList(checkoutPage.enter_countryDropdown, "Germany");
		Thread.sleep(2000);
		javaScriptClickUsingBy(checkoutPage.select_germany);
		

	}

	public void paymentDetails() throws Exception {

		// billing detail form

		waitFindEnterTextAsList(checkoutPage.cardNumber, TestData.CARD_NUMBER);
		waitFindEnterTextAsList(checkoutPage.firstNameCard, TestData.FIRST_NAME);
		waitFindEnterTextAsList(checkoutPage.lastNameCard, TestData.LAST_NAME);
		Thread.sleep(4000);
		javaScriptClickUsingBy(checkoutPage.expiryMonth);
		waitFindEnterTextAsList(checkoutPage.expiryMonth, TestData.EXP_MONTH);
		waitFindEnterTextAsList(checkoutPage.expiryYr, TestData.EXP_YR);
		waitFindEnterTextAsList(checkoutPage.cvvCard, TestData.CVV);
		
		// click on Next
		waitForElementToBeClickable(commonPage.common_button_span("Next"));
		javaScriptClickUsingBy(commonPage.common_button_span("Next"));
		
		// Pay and Subscribe
		Thread.sleep(4000);
		waitForElementToBeClickable(checkoutPage.payAndSubscribe);
		javaScriptClickUsingBy(checkoutPage.payAndSubscribe);
		
		moveOutSideFromiframe();
		moveOutSideFromiframe();
		
		waitUntilLoadingPage("Transaction Successful", commonPage.common_text("Transaction Successful"));
		
		// click on close
		waitForElementToBeClickable(commonPage.common_button("OK"));
		javaScriptClickUsingBy(commonPage.common_button("OK"));

	}

}
