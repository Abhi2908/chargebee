package utils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.xml.XmlSuite;

import bid.web.TestBase;



public class CustomReport implements IReporter {
	
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

		// Iterating over each suite included in the test
		for (ISuite suite : suites) {

			// Following code gets the suite name
			String suiteName = suite.getName();

			// Getting the results for the said suite
			Map<String, ISuiteResult> suiteResults = suite.getResults();
			for (ISuiteResult sr : suiteResults.values()) {
				ITestContext tc = sr.getTestContext();
				System.out.println(
						"Passed tests for suite '" + suiteName + "' is:" + tc.getPassedTests().getAllResults().size());
				System.out.println(
						"Failed tests for suite '" + suiteName + "' is:" + tc.getFailedTests().getAllResults().size());
				System.out.println("Skipped tests for suite '" + suiteName + "' is:"
						+ tc.getSkippedTests().getAllResults().size());
			}
		}
		
		try {
//          String timeStamp = new SimpleDateFormat("yyyymmdd_hh-mm-ss").format(new Date());
          String timeStamp = LocalDateTime.now().toString();
          File reportZip = Utilities.zipDirectory(outputDirectory, "AutomationTestReport_" + timeStamp + ".zip");
          TestBase.sendAutomationReportToSlack(reportZip);
      } catch (IOException e) {
          e.printStackTrace();
      }
	}
}