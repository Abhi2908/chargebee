package bid.helpers;

import org.apache.log4j.Logger;
import org.testng.Assert;

import bid.pages.CommonLocatorPage;
import bid.pages.MyProfilePage;
import bid.web.CommonUtils;
import bid.web.TestData;

public class MyProfileHelper extends CommonUtils {

	CommonLocatorPage commonPage = new CommonLocatorPage();
	MyProfilePage myProfile = new MyProfilePage();

	static final Logger logger = Logger.getLogger(MyProfileHelper.class.getName());

	/**
	 * @param my profile scenario
	 * @throws Exception
	 */
	public void myProfile(String country, String countryCode, String mobileNumber) throws Exception {

		// wait to load page
		waitForPageLoad();
		Thread.sleep(3000);

//		Boolean counter = Boolean.TRUE;
//		
//		while (counter != Boolean.FALSE) {
//
//			String str = "Set Your Business Profile";
//			System.out.println(str);
//			if(str.equalsIgnoreCase("Set Your Business Profile")) {
//
//
//				moveToWebElementAndClick(myProfile.profile_button);
//				moveToWebElementAndClick(commonPage.common_text("My Profile"));
//				counter = Boolean.FALSE;
//
//			} else {
//
//				moveToWebElementAndClick(myProfile.profile_button);
//				moveToWebElementAndClick(commonPage.common_text("My Profile"));
//			}
//		}

		moveToWebElementAndClick(myProfile.profile_button);
		moveToWebElementAndClick(commonPage.common_text("My Profile"));

		waitUntilLoadingPage("Set Your Business Profile", commonPage.common_text("Set Your Business Profile"));

		if (checkElementPresence(commonPage.common_text("Set Your Business Profile"))) {

			// Assert verification
			assertString(TestData.COMPANY_NAME.toUpperCase(),
					textFromApplication(commonPage.verify_profile("Company Name", TestData.COMPANY_NAME.toUpperCase()))
							.toUpperCase());
			assertString(country.toUpperCase(),
					textFromApplication(commonPage.verify_profile("Operating Countries", country)).toUpperCase());
			assertString(TestData.EMAIL_ID.toUpperCase(),
					textFromApplication(commonPage.verify_profile("Email", TestData.EMAIL_ID)).toUpperCase());
			assertString(countryCode + mobileNumber.toUpperCase(),
					textFromApplication(commonPage.verify_profile("Contact Number", countryCode + mobileNumber))
							.toUpperCase());
			assert true;
		} else {

			assert false;
			System.out.println("assertion fail on my profile");
		}

	}

	/**
	 * @param Active Plan scenario
	 * @throws Exception
	 */
	public void activePlan() throws Exception {

		// wait to load page
		waitForPageLoad();
		Thread.sleep(3000);

		// clicking on active plan
		waitForElementToBeClickable(commonPage.common_button_a("Active Plan"));
		javaScriptClickUsingBy(commonPage.common_button_a("Active Plan"));

		waitForPresenceOfElement(commonPage.common_text("Free Trial Plan Activated"));

		// Get date using date format
		String actualDate = dateFormat();

		// get date from DOM
		String getDate = textFromApplication(myProfile.activeOn);
		String dateReplace = getDate.replaceAll("[^a-zA-Z0-9]", " ");
		String expectedDate = dateReplace.substring(11);

		// assert actual and expected dates
		//assertString(actualDate, expectedDate);

		// check presence Manage Subscriptions
		if (checkElementPresence(commonPage.common_button("Manage Subscriptions"))) {

			// clicking on Manage Subscriptions
			waitForElementToBeClickable(commonPage.common_button("Manage Subscriptions"));
			javaScriptClickUsingBy(commonPage.common_button("Manage Subscriptions"));

			// move to i-frame
			iframeToBeAvailableAndSwitchToIt(myProfile.iFrame);

			Thread.sleep(20000);

			waitUntilLoadingPage("global-all-regions", commonPage.common_cbSection("global-all-regions"));

			// check presence of global-all-regions text
			if (checkElementPresence(commonPage.common_cbSection("global-all-regions"))) {

				// clicking on button
				waitForElementToBeClickable(commonPage.common_cbSection("global-all-regions"));
				moveToWebElementAndClick(commonPage.common_cbSection("global-all-regions"));

				Thread.sleep(15000);

				// get plan value
				String trialPlan = textFromApplication(myProfile.trialMoney);
				String expectedMoney = trialPlan.substring(1);
				double valueOfTrialPlan = Double.parseDouble(expectedMoney);

				// clicking on View breakdown link
				waitForElementToBeClickable(commonPage.common_cbSection("View breakdown"));
				javaScriptClickUsingBy(commonPage.common_cbSection("View breakdown"));

				waitUntilLoadingPage("global-all-regions", commonPage.common_cbSection("global-all-regions"));

				// verifying values
				double subscriptionValues = getSumOfDoubleDigits(myProfile.listOfTrialMoney());
				System.out.println(subscriptionValues);

				// Introductory Offer
				String getIntroductoryOffer = textFromApplication(myProfile.introductoryOffer);
				String substringIntroductoryOffer = getIntroductoryOffer.substring(2);
				double introductoryOffer = Double.parseDouble(substringIntroductoryOffer);

				subscriptionValues = (subscriptionValues - introductoryOffer);

				// assert actual and expected money
				Assert.assertEquals(subscriptionValues, valueOfTrialPlan);

				// close pop-up
				waitForElementToBeClickable(myProfile.closePopup);
				javaScriptClickUsingBy(myProfile.closePopup);

				moveOutSideFromiframe();

				assert true;
			} else {

				assert false;
				System.out.println("Manage Subscriptions functionality is not working");
			}
			assert true;
		} else {
			assert false;
		}

	}

}
