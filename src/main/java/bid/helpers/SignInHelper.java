package bid.helpers;

import org.apache.log4j.Logger;
import bid.pages.CommonLocatorPage;
import bid.pages.SigninPage;
import bid.web.CommonUtils;
import bid.web.TestData;

public class SignInHelper extends CommonUtils {

	SigninPage signinPage = new SigninPage();
	CommonLocatorPage commonPage = new CommonLocatorPage();

	static final Logger logger = Logger.getLogger(SignInHelper.class.getName());

	/**
	 * @param signin scenario
	 * @throws Exception
	 */
	public void signInPage(String country, String mobileNumber) throws Exception {

		// wait to load page
		waitForPageLoad();

		waitUntilLoadingPage("Sign In", commonPage.common_button_a("Sign In"));

		// click on sign-in button
		waitForElementToBeClickable(commonPage.common_button_a("Sign In"));
		moveToWebElementAndClick(commonPage.common_button_a("Sign In"));

		// select value from drop-down
		waitForElementToBeClickable(signinPage.dropDown_country);
		moveToWebElementAndClick(signinPage.dropDown_country);

		// select value
		moveToWebElementAndClick(signinPage.select_country(country));

		// Enter values in text fields
		waitFindEnterTextAsList(commonPage.common_text_inputField("mobile"), mobileNumber);

		// click on OTP button
		moveToWebElementAndClick(commonPage.common_button("Get OTP"));

		// wait for submit OTP
		waitUntilLoadingPage("Submit OTP", commonPage.common_button("Submit OTP"));

		// Enter OTP values in text fields
		waitFindEnterTextAsList(commonPage.common_text_inputField("otp"), TestData.MOBILE_OTP);
		moveToWebElementAndClick(commonPage.common_button("Submit OTP"));

		if (checkElementPresence(commonPage.common_text("Login Limits Reached"))) {

			moveToWebElementAndClick(commonPage.common_button("OK"));

		}

	}

	/**
	 * @param set Geographies
	 * @throws Exception
	 */
	public void setGeographies() {

		// wait to load page
		waitForPageLoad();

		waitUntilLoadingPage("Set Your Geographies", commonPage.common_text("Set Your Geographies"));

		if (checkElementPresence(signinPage.set_geographies)) {

			// select another country
			waitForElementToBeClickable(signinPage.set_geographies);
			moveToWebElementAndClick(signinPage.set_geographies);

			// save Geographies
			moveToWebElementAndClick(commonPage.common_button("Save"));

		} else {

			System.out.println("Geographies is set");
		}
	}

	/**
	 * @param set your keywords
	 * @throws Exception
	 */
	public void setKeywords() throws Exception {

		// wait to load page
		waitForPageLoad();

		waitUntilLoadingPage("Select Keywords", commonPage.common_text("Select Keywords"));

		if (checkElementPresence(signinPage.set_keywords)) {

			// select Keywords
			waitForElementToBeClickable(signinPage.set_keywords);
			moveToWebElementAndClick(signinPage.set_keywords);

			// select Keywords
			waitForElementToBeClickable(signinPage.set_keywords);
			moveToWebElementAndClick(signinPage.set_keywords);

			// save Geographies
			moveToWebElementAndClick(commonPage.common_button("Save"));

		} else {

			System.out.println("Keywords is set");
		}

	}

	/**
	 * @param set business profile
	 * @throws Exception
	 */
	public void setBusinessProfile() throws Exception {

		// wait to load page
		waitForPageLoad();

		waitUntilLoadingPage("Set Business Profile", commonPage.common_text("Set Business Profile"));

		// enter email and company name
		waitFindEnterTextAsList(commonPage.common_text_inputField("email"), TestData.EMAIL_ID);
		waitFindEnterTextAsList(commonPage.common_text_inputField("companyName"), TestData.COMPANY_NAME);

		// select business type
		// moveToWebElementAndClick(commonPage.common_text("Select Business Types"));
		waitFindEnterTextAsList(signinPage.business_type, "Consulting");
		waitForElementToBeClickable(signinPage.select_consulting);
		moveToWebElementAndClick(signinPage.select_consulting);

		// select business type
		focusStop(4);
		// waitForElementToBeClickable(commonPage.common_text("Select Business Types"));
		// moveToWebElementAndClick(commonPage.common_text("Select Business Types"));
		waitFindEnterTextAsList(signinPage.business_type, "Contractor");
		waitForElementToBeClickable(signinPage.select_contractor);
		moveToWebElementAndClick(signinPage.select_contractor);

		// wait for button
		waitForElementToBeClickable(signinPage.business_type_dropDown);
		moveToWebElementAndClick(signinPage.business_type_dropDown);

		// submit profile
		moveToWebElementAndClick(commonPage.common_button("Submit Profile "));

		// close pop up
		Thread.sleep(7000);
		if (checkElementPresence(signinPage.plan_activate_popUp)) {
			focusStop(4);
			moveToWebElementAndClick(signinPage.plan_activate_popUp);
		}

	}
	
}
