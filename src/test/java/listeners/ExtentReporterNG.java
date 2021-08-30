package listeners;

import java.io.IOException;
import java.net.Inet4Address;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import utilities.ConfigurationReader;

public class ExtentReporterNG implements IReporter {
	private ExtentReports extent;
	ExtentHtmlReporter htmlReporter;

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "\\Reports\\htmlreport.html");
		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("OS", System.getProperty("os.name"));
		extent.setSystemInfo("Java", System.getProperty("java.version"));
		extent.setSystemInfo("User name", System.getProperty("user.name"));
		extent.setSystemInfo("URL", System.getProperty("baseUrl")==null?ConfigurationReader.get("baseUrl"):System.getProperty("baseUrl"));
		extent.setSystemInfo("Browser", System.getProperty("browser")==null?ConfigurationReader.get("browser"):System.getProperty("browser"));
		try {
			extent.setSystemInfo("IP Address", Inet4Address.getLocalHost().getHostAddress());
		} catch (Exception e) {
			// TODO: handle exception
		}
		for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();

			for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();
				try {
					buildTestNodes(context.getPassedTests(), Status.PASS);
					buildTestNodes(context.getFailedTests(), Status.FAIL);
					buildTestNodes(context.getSkippedTests(), Status.SKIP);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		extent.flush();

	}

	private void buildTestNodes(IResultMap tests, Status status) throws IOException {
		ExtentTest test = null;

		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults()) {
				if (result.getParameters().length > 0) {
					Object[] object = result.getParameters();
					String params = "{";
					for (int i = 0; i < object.length; i++)
						params = params + "params[" + i + "]:" + object[i]+"&";
					params = params + "}";
					test = extent.createTest(result.getMethod().getMethodName() + params);
				} else {
					test = extent.createTest(result.getMethod().getMethodName());
				}

				/*
				 * test.getTest(). = getTime(result.getStartMillis()); test.getTest().endedTime
				 * = getTime(result.getEndMillis());
				 */

				for (String group : result.getMethod().getGroups())
					test.assignCategory(group);

				String message = "Test " + status.toString().toLowerCase() + "ed";

				if (result.getThrowable() != null)
					message = result.getThrowable().getMessage();

				if (result.getStatus() == result.FAILURE) {
					test.log(status, message).addScreenCaptureFromPath(
							System.getProperty("user.dir") + "/screenshots/" + result.getName() + ".PNG");
				} else {
					test.log(status, message);
				}

				// extent.endTest(test);
			}
		}
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
}