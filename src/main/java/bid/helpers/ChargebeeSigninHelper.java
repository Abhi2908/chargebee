package bid.helpers;

import org.apache.log4j.Logger;

import bid.pages.ChargebeePage;
import bid.pages.CommonLocatorPage;
import bid.pages.SigninPage;
import bid.web.CommonUtils;
import bid.web.TestData;

public class ChargebeeSigninHelper extends CommonUtils {

	SigninPage signinPage = new SigninPage();
	CommonLocatorPage commonPage = new CommonLocatorPage();
	ChargebeePage chargebeePage = new ChargebeePage();

	static final Logger logger = Logger.getLogger(SignInHelper.class.getName());

	/**
	 * @param signin scenario
	 * @throws Exception
	 */
	public void chargebeeSignInPage() throws Exception {

		// wait to load page
		waitForPageLoad();
		Thread.sleep(4000);
		
		waitUntilLoadingPage("Sign in to Chargebee!", commonPage.common_text("Sign in to Chargebee!"));

		// Enter values in text fields
		waitFindEnterTextAsList(commonPage.common_text_inputField("email"), TestData.CHARGEBEE_USERID);
		waitFindEnterTextAsList(commonPage.common_text_inputField("password"), TestData.CHARGEBEE_PASSWORD);

		// click on submit button
		waitForElementToBeClickable(signinPage.submit_button);
		moveToWebElementAndClick(signinPage.submit_button);

		// wait till user name found
		Thread.sleep(4000);
		waitUntilLoadingPage(TestData.USER_NAME, commonPage.common_text(TestData.USER_NAME));

		// click on name from list
		waitFindEnterTextAsList(chargebeePage.search_customer, TestData.COMPANY_NAME);

		// click on selected company
		Thread.sleep(4000);
		waitForElementToBeClickable(chargebeePage.company);
		javaScriptClickUsingBy(chargebeePage.company);

	}

	/**
	 * @param Assert company info
	 * @throws Exception
	 */
	public void companyInfo(String mobileNumber, String preferredCurrency) throws Exception {

		// wait to load page
		waitForPageLoad();
		Thread.sleep(7000);
		
		waitUntilLoadingPage(TestData.EMAIL_ID, commonPage.common_text(TestData.EMAIL_ID));

		// assert values
		assertString(TestData.COMPANY_NAME.toUpperCase(),
				textFromApplication(commonPage.common_companyInfo(TestData.COMPANY_NAME)).toUpperCase());
		assertString(TestData.EMAIL_ID.toUpperCase(),
				textFromApplication(commonPage.common_companyInfo(TestData.EMAIL_ID)).toUpperCase());
		String mobileNbr = textFromApplication(commonPage.common_companyInfo(mobileNumber)).substring(3);
		assertString(mobileNumber.toUpperCase(), mobileNbr.toUpperCase());
		assertString(preferredCurrency.toUpperCase(),
				textFromApplication(commonPage.verify_profile("Preferred currency", preferredCurrency.toUpperCase())).toUpperCase());

	}
	

	/**
	 * @param Assert subscriptions info
	 * @throws Exception
	 */
	public void subscriptions() throws Exception {

		// wait to load page
		waitForPageLoad();
		Thread.sleep(4000);
		
		// click on submit button
		waitForElementToBeClickable(commonPage.common_text("Subscriptions"));
		moveToWebElementAndClick(commonPage.common_text("Subscriptions"));
		
		Thread.sleep(4000);
		
		// click on name from list
		waitFindEnterTextAsList(chargebeePage.search_customer, TestData.COMPANY_NAME);

		// click on selected company
		Thread.sleep(4000);
		waitForElementToBeClickable(chargebeePage.company);
		javaScriptClickUsingBy(chargebeePage.company);
		
		// wait to load page
		waitForPageLoad();
		Thread.sleep(7000);
		
		waitUntilLoadingPage(TestData.EMAIL_ID, commonPage.common_text(TestData.EMAIL_ID));

		// assert values
		assertString(TestData.COMPANY_NAME.toUpperCase(),
				textFromApplication(commonPage.common_companyInfo(TestData.COMPANY_NAME)).toUpperCase());
		assertString(TestData.EMAIL_ID.toUpperCase(),
				textFromApplication(commonPage.common_companyInfo(TestData.EMAIL_ID)).toUpperCase());
		
		// click on subscription button
		moveToWebElementAndClick(commonPage.common_button_a("Premium-All Regions EUR Yearly"));
		
		
		// click on cancel subscription button
		Thread.sleep(7000);
		waitForElementToBeClickable(commonPage.common_text("Cancel Subscription"));
		javaScriptClickUsingBy(commonPage.common_text("Cancel Subscription"));
		
		// click on cancel now button
		Thread.sleep(5000);
		waitForPresenceOfElement(commonPage.common_text("Dismiss"));
		waitForElementToBeClickable(commonPage.common_text_inputValueField("Cancel Now"));
		moveToWebElementAndClick(commonPage.common_text_inputValueField("Cancel Now"));

		// assert value
		assertString(TestData.CANCELLED_SUBSCRIPTION,
				textFromApplication(commonPage.common_text(TestData.CANCELLED_SUBSCRIPTION)).toUpperCase());
		
	}
	
	/**
	 * @param Assert new subscriptions info
	 * @throws Exception
	 */
	public void newBasicSubscriptions() throws Exception {

		// wait to load page
		waitForPageLoad();
		Thread.sleep(4000);
		
		// click on submit button
		waitForElementToBeClickable(commonPage.common_text("Subscriptions"));
		moveToWebElementAndClick(commonPage.common_text("Subscriptions"));
		
		Thread.sleep(4000);
		
		// click on name from list
		waitFindEnterTextAsList(chargebeePage.search_customer, TestData.COMPANY_NAME);

		// click on selected company
		Thread.sleep(4000);
		waitForElementToBeClickable(chargebeePage.company);
		javaScriptClickUsingBy(chargebeePage.company);
		
		// wait to load page
		waitForPageLoad();
		Thread.sleep(7000);
		
		waitUntilLoadingPage(TestData.EMAIL_ID, commonPage.common_text(TestData.EMAIL_ID));

		// assert values
		assertString(TestData.COMPANY_NAME.toUpperCase(),
				textFromApplication(commonPage.common_companyInfo(TestData.COMPANY_NAME)).toUpperCase());
		assertString(TestData.EMAIL_ID.toUpperCase(),
				textFromApplication(commonPage.common_companyInfo(TestData.EMAIL_ID)).toUpperCase());
		
		// click on subscription button
		moveToWebElementAndClick(commonPage.common_button_a("Basic-One Region EUR Yearly"));
		
		waitUntilLoadingPage("Basic-One Region", commonPage.common_text("Basic-One Region"));
		
		// Assert Active
		assertString("ACTIVE", textFromApplication(commonPage.common_text("ACTIVE")).toUpperCase());
		
		// verify Basic plan
		assertString(TestData.BASIC_PLAN.toUpperCase(),
				textFromApplication(chargebeePage.basicPlan).toUpperCase());
		
	}

}
