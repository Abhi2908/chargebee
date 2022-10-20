package bid.helpers;

import org.apache.log4j.Logger;

import bid.pages.CommonLocatorPage;
import bid.pages.MyProfilePage;
import bid.web.CommonUtils;

public class LogoutHelper extends CommonUtils {

	CommonLocatorPage commonPage = new CommonLocatorPage();
	MyProfilePage myProfile = new MyProfilePage();
	
	static final Logger logger = Logger.getLogger(LogoutHelper.class.getName());

	/**
	 * @param logOut scenario
	 * @throws Exception
	 */
	public void logOut() throws Exception {

		// wait to load page
		waitForPageLoad();
		Thread.sleep(4000);
		
		// log out scenario
		moveToWebElementAndClick(myProfile.profile_button);
		moveToWebElementAndClick(commonPage.common_button_span("Logout"));

	}

}
