package utils.HelperClass;

import InsuranceClaimPortal.Pages.LandingPage;

public class HelperClass {

    public static synchronized void login (String username, String password, String token, LandingPage landingPage) {
        landingPage.enterUsername(username);
        landingPage.enterPassword(password);
        landingPage.enterToken(token);
        landingPage.clickSignIntBtn();
    }
}
