package bid.web;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import atu.testrecorder.exceptions.ATUTestRecorderException;
import bid.helpers.ChargebeeSigninHelper;
import bid.helpers.CheckoutHelper;
import bid.helpers.LandingHomeScreenHelper;
import bid.helpers.LogoutHelper;
import bid.helpers.MyProfileHelper;
import bid.helpers.PricingHelper;
import bid.helpers.SignInHelper;


public class WebTest extends TestBase {

	static final Logger logger = Logger.getLogger(WebTest.class.getName());
	
	String germanyCode = "+49";
	String ZimbabweCode = "+263";

	SignInHelper signIn;
	LandingHomeScreenHelper landingHomePage;
	MyProfileHelper verifyMyProfile;
	PricingHelper pricingScenarios;
	LogoutHelper logoutScenario;
	ChargebeeSigninHelper chargebeeSignin;
	CheckoutHelper checkoutTest;
	
	@BeforeSuite
	@Parameters("browser")
	public void setup(String browser) throws IOException {
		super.setup(browser);

		signIn = new SignInHelper();
		landingHomePage = new LandingHomeScreenHelper();
		verifyMyProfile = new MyProfileHelper();
		pricingScenarios = new PricingHelper();
		logoutScenario = new LogoutHelper();
		chargebeeSignin = new ChargebeeSigninHelper();
		checkoutTest = new CheckoutHelper();
	}

	@BeforeMethod
	public void beforeMethodSetup(Method method) {
		test = extent.startTest(method.getName().toString());
		test.assignCategory(browser);
		try {
			// String browserName = CommonUtils.getBrowserDetails();
			startRecording(method.getName().toString());

		} catch (ATUTestRecorderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// *************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************************

	/**
	 * Verify signIn page test scenario 
	 */
	@Test(priority=0)
	public void verifyEndToEndScenarioForEURUser() {
		try {
			loadUrl(getProps().getProperty("PageUrl"), getProps().getProperty("Sitetitle"));
			landingHomePage.landingPage(getProps().getProperty("PageUrl"));
			signIn.signInPage(TestData.OPERATING_COUNTRY_GER, TestData.MOBILE_NUMBER6);
			signIn.setGeographies();
			signIn.setKeywords();
			signIn.setBusinessProfile();
			verifyMyProfile.myProfile(TestData.OPERATING_COUNTRY_GER, germanyCode, TestData.MOBILE_NUMBER6);
			verifyMyProfile.activePlan();
			pricingScenarios.annualPricing();
			pricingScenarios.quarterlyPricing();
			
		//	logoutScenario.logOut();
			test.log(LogStatus.PASS, "SUCCESSFUL!! End to End test case");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "FAIL!! End to End test case");
			assert (false);
		}
		
	}
	
	/**
	 * Verify User In Charge bee 
	 */
	@Test(priority=1)
	public void verifyEURUserInChargebee() {
		try {
			loadUrl(getProps().getProperty("ChargebeePageUrl"), getProps().getProperty("Sitetitle"));
			chargebeeSignin.chargebeeSignInPage();
			chargebeeSignin.companyInfo(TestData.MOBILE_NUMBER6, TestData.PREFFERED_CURRENCY_EUR);
			chargebeeSignin.subscriptions();
			loadUrl(getProps().getProperty("PageUrl"), getProps().getProperty("Sitetitle"));
			signIn.signInPage(TestData.OPERATING_COUNTRY_GER, TestData.MOBILE_NUMBER6);   
			checkoutTest.checkoutBuynow();
			test.log(LogStatus.PASS, "SUCCESSFUL!! Chargebee functionality");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "FAIL!! Chargebee functionality");
			assert (false);
		}
		
	}
	

	/**
	 * Verify Basic Subscription Charge bee 
	 */
	@Test(priority=2)
	public void verifyBaiscSubscriptionInChargebee() {
		try {
			loadUrl(getProps().getProperty("ChargebeePageUrl"), getProps().getProperty("Sitetitle"));
			chargebeeSignin.chargebeeSignInPage();
			chargebeeSignin.newBasicSubscriptions();
			
			test.log(LogStatus.PASS, "SUCCESSFUL!! Chargebee functionality");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "FAIL!! Chargebee functionality");
			assert (false);
		}
		
	}
	
	
	/**
	 * Verify End to End test scenario for USD Used 
	 */
	//@Test(priority=3)
	public void verifyEndToEndScenarioForUSDUser() {
		try {
			loadUrl(getProps().getProperty("PageUrl"), getProps().getProperty("Sitetitle"));
			landingHomePage.landingPage(getProps().getProperty("PageUrl"));
			signIn.signInPage(TestData.OPERATING_COUNTRY_ZIM, TestData.ZIM_NUMBER5);
			signIn.setGeographies();
			signIn.setKeywords();
			signIn.setBusinessProfile();
			verifyMyProfile.myProfile(TestData.OPERATING_COUNTRY_ZIM, ZimbabweCode, TestData.ZIM_NUMBER5);
			verifyMyProfile.activePlan();
			pricingScenarios.annualPricing();
			pricingScenarios.quarterlyPricing();
			logoutScenario.logOut();
			test.log(LogStatus.PASS, "SUCCESSFUL!! End to End test case");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "FAIL!! End to End test case");
			assert (false);
		}
		
	}
	
	/**
	 * Verify User In Charge bee 
	 */
	//@Test(priority=4)
	public void verifyUSDUserInChargebee() {
		try {
			loadUrl(getProps().getProperty("ChargebeePageUrl"), getProps().getProperty("Sitetitle"));
			chargebeeSignin.chargebeeSignInPage();
			chargebeeSignin.companyInfo(TestData.ZIM_NUMBER5, TestData.PREFFERED_CURRENCY_USD);
			chargebeeSignin.subscriptions();
			test.log(LogStatus.PASS, "SUCCESSFUL!! Chargebee functionality");
		} catch (Exception e) {
			test.log(LogStatus.FAIL, "FAIL!! Chargebee functionality");
			assert (false);
		}
		
	}

}
