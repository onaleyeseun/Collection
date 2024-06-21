package InsuranceClaimPortal.Tests.UserPortal;

import InsuranceClaimPortal.BaseClasses.TestBase;
import InsuranceClaimPortal.Pages.DashboardPage;
import InsuranceClaimPortal.Pages.LandingPage;
import InsuranceClaimPortal.Pages.LogClaimPage;
import InsuranceClaimPortal.Pages.MotorClaimPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static utils.HelperClass.HelperClass.login;
import static utils.extentReports.ExtentTestManager.startTest;

public class LogNewClaim extends TestBase {

    private final Logger log = LogManager.getLogger(Test.class);
    LandingPage landingPage;
    DashboardPage dashboardPage;
    LogClaimPage logClaimPage;
    MotorClaimPage motorClaimPage;

    public void initializer() {
        landingPage = new LandingPage(driver);
        dashboardPage = new DashboardPage(driver);
        logClaimPage = new LogClaimPage(driver);
        motorClaimPage = new MotorClaimPage(driver);
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

    @Test(priority = 2, description = "Verify that user can select motor claim and insurance claim successfully")
    public void verify_user_can_check_claim_radio_button(Method method) {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Log-A-Claim");

        dashboardPage.clickLogClaimBtn();
        boolean testPassed = true;
        try {
            turnOffImplicitWait();
            dynamicWait(logClaimPage.getMotorClaimLabel(), 40);
            logClaimPage.clickMotorClaimRadioInput();
            turnOnImplicitWait();
            validateAttribute(logClaimPage.getMotorClaimRadioInput(), "checked", true);
        } catch (Exception e) {
            testPassed = false;
        }
        try {
            turnOffImplicitWait();
            dynamicWait(logClaimPage.getMotorClaimLabel(), 20);
            logClaimPage.clickInsuranceClaimRadioInput();
            turnOnImplicitWait();
            validateAttribute(logClaimPage.getInsuranceClaimRadioInput(), "checked", true);
        }catch (Exception e) {
            log.error("There is an unexpected error with the following message {}", e.getMessage());
            testPassed = false;
        }
        if(!testPassed) {
            Assert.fail("An error has occurred, check logs for more information");
        }
    }
    @Test(priority = 3, description = "Verify that user can see all motor claim options successfully")
    public void verify_user_can_see_motor_claim_options(Method method) {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Log-A-Claim");

        logClaimPage.clickMotorClaimRadioInput();
        logClaimPage.clickMotorClaimSelect();
        List<WebElement> motorClaimOptions = select(logClaimPage.getMotorClaimSelect()).getOptions();
        String[] motorClaimText = testData.getProperty("motorClaimOptions").split(":");
        for(int i = 0; i < motorClaimOptions.size(); i += 1) {
            validateText(motorClaimOptions.get(i), motorClaimText[i]);
        }
    }
    @Test(priority = 4, description = "Verify that user can see insurance claim options successfully")
    public void verify_user_can_see_insurance_claim_options(Method method) {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Log-A-Claim");

        logClaimPage.clickInsuranceClaimRadioInput();
        logClaimPage.clickInsuranceClaimSelect();
        List<WebElement> insuranceClaimOptions = select(logClaimPage.getInsuranceClaimSelect()).getOptions();
        String[] insuranceClaimText = testData.getProperty("insuranceClaimOptions").split(":");
        for(int i = 0; i < insuranceClaimOptions.size(); i += 1) {
            validateText(insuranceClaimOptions.get(i), insuranceClaimText[i]);
        }
    }
    @Test(priority = 5, description = "Verify that the user can select Pool vehicles as a claim type successfully")
    public void verify_user_can_select_pool_vehicle(Method method) {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Log-A-Claim");

        logClaimPage.clickMotorClaimRadioInput();
        logClaimPage.clickMotorClaimSelect();
        select(logClaimPage.getMotorClaimSelect()).selectByVisibleText("Pool Vehicles");
        scrollToElement(logClaimPage.getProceedBtn());
        logClaimPage.clickProceedBtn();
        Assert.assertTrue(motorClaimPage.getPageBanner().isDisplayed());
    }
    @Test(priority = 6, description = "Verify that the user can fill claim details generated from own damage successfully")
    public void verify_user_can_fill_own_damage_claim_form(Method method) {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Log-A-Claim");

        scrollToElement(motorClaimPage.getOwnDamageTab());
        motorClaimPage.clickOwnDamageTab();
        Assert.assertTrue(motorClaimPage.getOwnDamageTab().isDisplayed());
    }
    @Test(priority = 7, description = "Verify that the user can select date of incident successfully")
    public void verify_user_can_input_date_of_incident(Method method) {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Log-A-Claim");

        scrollToElement(motorClaimPage.getIncidentDate());
        motorClaimPage.setIncidentDate("value", "2023-12-23");
        Assert.assertEquals(motorClaimPage.getIncidentDate().getAttribute("value"), "2023-12-23");
    }
    @Test(priority = 8, description = "Verify that the user can input name of Garage successfully")
    public void verify_user_can_enter_garage_name(Method method) {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Log-A-Claim");

        motorClaimPage.enterGarageName("Gbagada");
        motorClaimPage.enterAddress("24 oops Street, bolade, Lagos");
        Assert.assertEquals((motorClaimPage.getGarageAddress().getAttribute("value")), "24 oops Street, bolade, Lagos");
    }
    @Test(priority = 9, description = "Verify that the user can upload invoice successfully")
    public void verify_user_can_upload_invoice_successfully(Method method) {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Log-A-Claim");

        motorClaimPage.clickUploadInvoiceBtn();
        motorClaimPage.enterItemName("Head Light");
        motorClaimPage.clickAddQuantity();
        motorClaimPage.enterAmount("210000");

        sleep(10);
    }
}


