package InsuranceClaimPortal.Tests.UserPortal;

import InsuranceClaimPortal.BaseClasses.TestBase;
import InsuranceClaimPortal.Pages.DashboardPage;
import InsuranceClaimPortal.Pages.LandingPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static utils.extentReports.ExtentTestManager.startTest;

public class Login extends TestBase {

    LandingPage landingPage;
    DashboardPage dashboardPage;

    public void initializer() {
        landingPage = new LandingPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    @Test(priority = 1, description = "Verify that the user can launch Insurance Portal successfully")
    public void validate_user_can_launch_URL(Method method) {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Login");

        initializer();
        Assert.assertTrue(driver.getCurrentUrl().startsWith(url));
    }

    @Test(priority = 2, description = "Verify that the system displays \"Welcome, please enter your details\"", dependsOnMethods = "validate_user_can_launch_URL")
    public void validate_system_displays_welcome_message(Method method) {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Login");

        validateText(landingPage.getSignInElement(), "Sign In");
        validateText(landingPage.getWelcomeMessage(), "Welcome. Please enter your details");
    }

    @Test(priority = 3, description = "Verify that the user cannot login to the application with blank credentials", dependsOnMethods = "validate_user_can_launch_URL")
    public void verify_user_cannot_login_with_blank_credentials(Method method) {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Login");

        scrollToElement(landingPage.getSignInBtn());
        landingPage.clickSignIntBtn();
        sleep(2);
        validateText(landingPage.getWelcomeMessage(), "Welcome. Please enter your details");
    }

    @Test(priority = 4, description = "Verify that user cannot login with incomplete credentials - empty username", dependsOnMethods = "validate_user_can_launch_URL")
    public void verify_user_cannot_login_with_empty_username_field(Method method) {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Login");

        landingPage.enterPassword(testData.getProperty("validPassword"));
        landingPage.enterToken("123456");
        landingPage.clickSignIntBtn();
        sleep(2);
        validateText(landingPage.getWelcomeMessage(), "Welcome. Please enter your details");
    }

    @Test(priority = 5, description = "Verify that user cannot login with incomplete credentials - empty password", dependsOnMethods = "validate_user_can_launch_URL")
    public void verify_user_cannot_login_with_empty_password_field(Method method) {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Login");

        landingPage.getPasswordField().clear();
        landingPage.enterUsername(testData.getProperty("validUsername"));
        landingPage.clickSignIntBtn();
        sleep(2);
        validateText(landingPage.getWelcomeMessage(), "Welcome. Please enter your details");
    }

    @Test(priority = 6, description = "Verify user is unable to login with incorrect username/password", dependsOnMethods = "validate_user_can_launch_URL")
    public void verify_user_cannot_login_with_invalid_username_and_password(Method method) {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Login");

        landingPage.getUsernameField().clear();
        landingPage.enterUsername(testData.getProperty("invalidUsername"));
        landingPage.enterPassword(testData.getProperty("validPassword"));
        landingPage.clickSignIntBtn();
        validateText(landingPage.getErrorMsg(), "Oops! An error occurred");
    }
    @Test(priority = 7, description = "Verify that the user can log in successfully")
    public void verify_user_can_login_successfully(Method method) {
        startTest(method.getName(), method.getAnnotation(Test.class).description(), "Login");

        landingPage.enterUsername(testData.getProperty("validUsername"));
        landingPage.enterPassword(testData.getProperty("validPassword"));
        landingPage.enterToken("123456");
        landingPage.clickSignIntBtn();
        Assert.assertTrue(dashboardPage.getPageDesc().getText().contains("Process your insurance claims from any branch or region"));
        sleep(10);
    }
}
