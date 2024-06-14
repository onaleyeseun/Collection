package utils.listeners;


import InsuranceClaimPortal.BaseClasses.TestBase;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.extentReports.ExtentManager;
import utils.logs.Log;

import java.util.Objects;

import static utils.extentReports.ExtentTestManager.getTest;

public class TestListener extends TestBase implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        Log.info("Process is onStart method" + iTestContext.getName());
//        iTestContext.setAttribute("Webdriver", getDriver());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        Log.info("Process is onFinish method" + iTestContext.getName());
        ExtentManager.extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + "test is starting...");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test has succeeded.");
        String successScreenShot =
                "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
        //ExtentReports log operation for passed tests.
        getTest().log(Status.PASS, "Test passed!",
                getTest().addScreenCaptureFromBase64String(successScreenShot).getModel().getMedia().get(0));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + "test has failed");
//        Object testClass = iTestResult.getInstance();
        String failedScreenShot =
                "data:image/png;base64," + ((TakesScreenshot) Objects.requireNonNull(driver)).getScreenshotAs(OutputType.BASE64);
        getTest().log(Status.FAIL, "Test has failed",
                getTest().addScreenCaptureFromBase64String(failedScreenShot).getModel().getMedia().get(0));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Log.info(getTestMethodName(iTestResult) + " test is skipped");
        getTest().log(Status.SKIP, "Test Skipped");
    }
}
