package bid.helpers;

import org.apache.log4j.Logger;

import bid.pages.CommonLocatorPage;
import bid.pages.SigninPage;
import bid.web.CommonUtils;


public class LandingHomeScreenHelper extends CommonUtils {

	static final Logger logger = Logger.getLogger(LandingHomeScreenHelper.class.getName());
	SigninPage signinPage = new SigninPage();
	CommonLocatorPage commonPage = new CommonLocatorPage();
	
	/**
	 * @param Verifying Home screen 
	 * @throws InterruptedException 
	 */
	public void landingPage(String sending_url) throws InterruptedException {

		// wait to load page
		waitForPageLoad();
			
		// verifying the home page URL
	//	verifyingLoadApplicationPageUrl(sending_url);
		
		waitUntilLoadingPage("Let's Get Started", commonPage.common_text("Let's Get Started"));
		
		// close pop-up window
		waitForElementToBeClickable(signinPage.close_popup);
		moveToWebElementAndClick(signinPage.close_popup);
		 
	}

}
