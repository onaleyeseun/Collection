package utils.listeners;


import InsuranceClaimPortal.BaseClasses.TestBase;
import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.extentReports.ExtentManager;

import java.util.Objects;

import static utils.extentReports.ExtentTestManager.getTest;

public class TestListener extends TestBase implements ITestListener {

    private final Logger Log = LogManager.getLogger(TestListener.class);

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        Log.info("{} tests are starting", iTestContext.getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        Log.info("{} tests are ending", iTestContext.getName());
        ExtentManager.extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log.info("{} test is starting...", getTestMethodName(iTestResult));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Log.info("{} test has succeeded.", getTestMethodName(iTestResult));
        getTest().log(Status.PASS, "Test passed!");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Log.info("{}test has failed", getTestMethodName(iTestResult));
        String failedScreenShot =
                "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
        getTest().log(Status.FAIL, "Test has failed",
                getTest().addScreenCaptureFromBase64String(failedScreenShot).getModel().getMedia().get(0));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Log.info("{} test is skipped", getTestMethodName(iTestResult));
        getTest().log(Status.SKIP, "Test Skipped");
    }
}
