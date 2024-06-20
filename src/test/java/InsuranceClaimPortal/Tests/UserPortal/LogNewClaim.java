package InsuranceClaimPortal.Tests.UserPortal;

import InsuranceClaimPortal.BaseClasses.TestBase;
import InsuranceClaimPortal.Pages.DashboardPage;
import InsuranceClaimPortal.Pages.LandingPage;
import InsuranceClaimPortal.Pages.LogClaimPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static utils.HelperClass.HelperClass.login;
import static utils.extentReports.ExtentTestManager.startTest;

public class LogNewClaim extends TestBase {

    LandingPage landingPage;
    DashboardPage dashboardPage;
    LogClaimPage logClaimPage;

    public void initializer() {
        landingPage = new LandingPage(driver);
        dashboardPage = new DashboardPage(driver);
        logClaimPage = new LogClaimPage(driver);
    }

    @Test(priority = 1, description = "Verify user can select a type of claim to log")
    public void verify_user_can_select_type_of_claim(Method method) {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Log-A-Claim");
        initializer();
        String username = testData.getProperty("validUsername");
        String password = testData.getProperty("validPassword");
        login(username, password, "123456", landingPage);

        System.out.println("The enable attribute of claim button is " + dashboardPage.getLogClaimBtn().isEnabled());
        Assert.assertTrue(dashboardPage.getLogClaimBtn().isEnabled());
    }

    @Test(priority = 2, description = "Verify that user can select from the claim options successfully")
    public void verify_user_can_select_claim_options(Method method) {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Log-A-Claim");

        dashboardPage.clickLogClaimBtn();
        boolean testPassed = true;
        try {
            turnOffImplicitWait();
            dynamicWait(logClaimPage.getMotorClaimLabel(), 40);
            logClaimPage.clickMotorClaimRadioInput();
            turnOffImplicitWait();
            validateAttribute(logClaimPage.getMotorClaimRadioInput(), "checked", true);
        } catch (Exception e) {
            testPassed = false;
        }
        try {
            turnOffImplicitWait();
            dynamicWait(logClaimPage.getMotorClaimLabel(), 20);
            logClaimPage.clickInsuranceClaimRadioInput();
            turnOffImplicitWait();
            validateAttribute(logClaimPage.getInsuranceClaimRadioInput(), "checked", true);
        }catch (Exception e) {
            e.printStackTrace();
            testPassed = false;
        }
        if(!testPassed) {
            Assert.fail("An error has occurred, check logs for more information");
        }

        sleep(10);
    }
}


