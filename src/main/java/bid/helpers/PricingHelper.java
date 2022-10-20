package bid.helpers;

import org.apache.log4j.Logger;
import bid.pages.CommonLocatorPage;
import bid.pages.PricingPage;
import bid.web.CommonUtils;
import bid.web.TestData;

public class PricingHelper extends CommonUtils {

	CommonLocatorPage commonPage = new CommonLocatorPage();
	PricingPage pricingPage = new PricingPage();

	static final Logger logger = Logger.getLogger(PricingHelper.class.getName());

	/**
	 * @param Annual Pricing scenario
	 * @throws Exception
	 */
	public void annualPricing() throws Exception {

		// wait to load page
		waitForPageLoad();
		Thread.sleep(3000);

		waitUntilLoadingPage("Pricing", commonPage.common_text("Pricing"));

		// click on pricing
		waitForElementToBeClickable(commonPage.common_text("Pricing"));
		javaScriptClickUsingBy(commonPage.common_text("Pricing"));

		waitUntilLoadingPage("Guaranteed value-for-money plans to get latest tender documents",
				commonPage.common_text("Guaranteed value-for-money plans to get latest tender documents"));

		// check presence iFrames
		iframeToBeAvailableAndSwitchToIt(pricingPage.iFrameSubscription);

		waitUntilLoadingPage("Basic", commonPage.common_text("Basic"));

		// verifying Basic description
		if (checkElementPresence(commonPage.common_text("Basic"))) {

			assertString(TestData.ANNUAL_BASIC.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.ANNUAL_BASIC)).toUpperCase());
			assertString(TestData.BILLED_YEARLY_BASIC.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.BILLED_YEARLY_BASIC))
							.toUpperCase());
			assertString(TestData.REGION_PLAN_BASIC.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.REGION_PLAN_BASIC))
							.toUpperCase());
			assertString(TestData.UNLIMITED_KEYWORD.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.UNLIMITED_KEYWORD))
							.toUpperCase());
			assertString(TestData.DAILY_TENDER.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.DAILY_TENDER))
							.toUpperCase());
			assertString(TestData.TENDER_DATA.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.TENDER_DATA))
							.toUpperCase());
			assertString(TestData.TENDER_RESULT.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.TENDER_RESULT))
							.toUpperCase());

			assert true;
		} else {

			assert false;
			System.out.println("Basic Pricing assert fail");
		}

		// verifying Premium description
		if (checkElementPresence(commonPage.common_text("Premium"))) {

			assertString(TestData.ANNUAL_PREMIUM.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.ANNUAL_PREMIUM)).toUpperCase());
			assertString(TestData.BILLED_YEARLY_BASIC.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.BILLED_YEARLY_BASIC))
							.toUpperCase());
			assertString(TestData.REGION_PREMIUM.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.REGION_PREMIUM))
							.toUpperCase());
			assertString(TestData.UNLIMITED_KEYWORD.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.UNLIMITED_KEYWORD))
							.toUpperCase());
			assertString(TestData.DAILY_TENDER.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.DAILY_TENDER))
							.toUpperCase());
			assertString(TestData.TENDER_DATA.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.TENDER_DATA))
							.toUpperCase());
			assertString(TestData.TENDER_RESULT.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.TENDER_RESULT))
							.toUpperCase());

			assert true;
		} else {

			assert false;
			System.out.println("Premium Pricing assert fail");
		}
		
		// verifying custom description
		if (checkElementPresence(commonPage.common_text("Custom"))) {

			assertString(TestData.ANNUAL_PREMIUM.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.ANNUAL_PREMIUM)).toUpperCase());
			assertString(TestData.AS_REQUIREMENT.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.AS_REQUIREMENT))
							.toUpperCase());
			assertString(TestData.REGION_PLAN_BASIC.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.REGION_PLAN_BASIC))
							.toUpperCase());
			assertString(TestData.UNLIMITED_KEYWORD.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.UNLIMITED_KEYWORD))
							.toUpperCase());
			assertString(TestData.DAILY_TENDER.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.DAILY_TENDER))
							.toUpperCase());
			assertString(TestData.TENDER_DATA.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.TENDER_DATA))
							.toUpperCase());
			assertString(TestData.TENDER_RESULT.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.TENDER_RESULT))
							.toUpperCase());
			assertString(TestData.UNLIMITED_LOGIN.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.UNLIMITED_LOGIN))
							.toUpperCase());
			assertString(TestData.UNLIMITED_USERS.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.UNLIMITED_USERS))
							.toUpperCase());

			assert true;
		} else {

			assert false;
			System.out.println("Custom Pricing assert fail");
		}
		
		
	}
	
	/**
	 * @param Quarterly Pricing scenario
	 * @throws Exception
	 */
	public void quarterlyPricing() throws Exception {

		// wait to load page
		waitForPageLoad();
		Thread.sleep(3000);

		waitUntilLoadingPage("Quarterly", commonPage.common_text("Quarterly"));

		// click on pricing
		waitForElementToBeClickable(commonPage.common_text("Quarterly"));
		javaScriptClickUsingBy(commonPage.common_text("Quarterly"));

		waitUntilLoadingPage(TestData.BILLED_YEARLY_QUARTERLY,
				commonPage.common_text(TestData.BILLED_YEARLY_QUARTERLY));


		// verifying Basic description Quarterly
		if (checkElementPresence(commonPage.common_text("Basic"))) {

			assertString(TestData.ANNUAL_BASIC.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.ANNUAL_BASIC)).toUpperCase());
			assertString(TestData.BILLED_YEARLY_QUARTERLY.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.BILLED_YEARLY_QUARTERLY))
							.toUpperCase());
			assertString(TestData.REGION_PLAN_BASIC.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.REGION_PLAN_BASIC))
							.toUpperCase());
			assertString(TestData.UNLIMITED_KEYWORD.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.UNLIMITED_KEYWORD))
							.toUpperCase());
			assertString(TestData.DAILY_TENDER.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.DAILY_TENDER))
							.toUpperCase());
			assertString(TestData.TENDER_DATA.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.TENDER_DATA))
							.toUpperCase());
			assertString(TestData.TENDER_RESULT.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.TENDER_RESULT))
							.toUpperCase());

			assert true;
		} else {

			assert false;
			System.out.println("Basic Pricing assert fail");
		}

		// verifying Premium description
		if (checkElementPresence(commonPage.common_text("Premium"))) {

			assertString(TestData.ANNUAL_PREMIUM.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.ANNUAL_PREMIUM)).toUpperCase());
			assertString(TestData.BILLED_YEARLY_QUARTERLY.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.BILLED_YEARLY_QUARTERLY))
							.toUpperCase());
			assertString(TestData.REGION_PREMIUM.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.REGION_PREMIUM))
							.toUpperCase());
			assertString(TestData.UNLIMITED_KEYWORD.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.UNLIMITED_KEYWORD))
							.toUpperCase());
			assertString(TestData.DAILY_TENDER.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.DAILY_TENDER))
							.toUpperCase());
			assertString(TestData.TENDER_DATA.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.TENDER_DATA))
							.toUpperCase());
			assertString(TestData.TENDER_RESULT.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.TENDER_RESULT))
							.toUpperCase());

			assert true;
		} else {

			assert false;
			System.out.println("Premium Pricing assert fail");
		}
		
		// verifying custom description
		if (checkElementPresence(commonPage.common_text("Custom"))) {

			assertString(TestData.ANNUAL_PREMIUM.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.ANNUAL_PREMIUM)).toUpperCase());
			assertString(TestData.AS_REQUIREMENT.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.AS_REQUIREMENT))
							.toUpperCase());
			assertString(TestData.REGION_PLAN_BASIC.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.REGION_PLAN_BASIC))
							.toUpperCase());
			assertString(TestData.UNLIMITED_KEYWORD.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.UNLIMITED_KEYWORD))
							.toUpperCase());
			assertString(TestData.DAILY_TENDER.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.DAILY_TENDER))
							.toUpperCase());
			assertString(TestData.TENDER_DATA.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.TENDER_DATA))
							.toUpperCase());
			assertString(TestData.TENDER_RESULT.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.TENDER_RESULT))
							.toUpperCase());
			assertString(TestData.UNLIMITED_LOGIN.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.UNLIMITED_LOGIN))
							.toUpperCase());
			assertString(TestData.UNLIMITED_USERS.toUpperCase(),
					textFromApplication(commonPage.common_text(TestData.UNLIMITED_USERS))
							.toUpperCase());
			
			moveOutSideFromiframe();

			assert true;
		} else {

			assert false;
			System.out.println("Custom Pricing assert fail");
		}
	}

}
