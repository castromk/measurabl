package listeners;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import utilities.BrowserUtils;

public class Listeners implements ITestListener {

	private static final Logger console = LoggerFactory.getLogger(Listeners.class);

	@Override
	public void onTestStart(ITestResult result) {
		console.info("######Starting {} test#########", result.getMethod().getMethodName());

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		console.info("######{} test passed#########",result.getMethod().getMethodName());

	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodname = result.getName();
		try {
			BrowserUtils.takeScreenshot(methodname);
		} catch (IOException e) {

			e.printStackTrace();
			console.info("######{} test failed due to {}#########", result.getMethod().getMethodName());
		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		console.info("######{} test skipped due to {}#########", result.getTestName(),
				result.getSkipCausedBy().toString());

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		console.info("######{} test is getting started #########", context.getName());

	}

	@Override
	public void onFinish(ITestContext context) {
		console.info("######{} test is getting ended #########", context.getName());

	}

}
