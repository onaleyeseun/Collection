package InsuranceClaimPortal.Pages;

import InsuranceClaimPortal.BaseClasses.PageBase;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LandingPage extends PageBase {

    WebDriver driver;
    public LandingPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy(css = ".card-title.mt-4.mb-2")
    private WebElement signInElement;
    @FindBy(xpath = "//span[contains (text(), 'Welcome.')]")
    private WebElement welcomeMessage;
    @FindBy(id = "exampleInputEmail1")
    private WebElement usernameField;
    @FindBy(id = "exampleInputPassword1")
    private WebElement passwordField;
    @FindBy(id = "exampleInputToken")
    private WebElement tokenField;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement signInBtn;
    @FindBy(xpath = "//div[@role='alert']/b")
    private WebElement errorMsg;


    // Methods
    public WebElement getSignInElement() {
        return signInElement;
    }

    public WebElement getWelcomeMessage() {
        return welcomeMessage;
    }

    public WebElement getUsernameField() {
        return usernameField;
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public WebElement getTokenField() {
        return tokenField;
    }

    public WebElement getSignInBtn() {
        return signInBtn;
    }

    public void enterUsername(String username) {
        enterText(usernameField, username);
    }
    public void enterPassword(String password) {
        enterText(passwordField, password);
    }
    public void enterToken(String token) {
        enterText(tokenField, token);
    }

    public void clickSignIntBtn() {
        JavascriptExecutor jsExecutor = ((JavascriptExecutor) driver);
        waitForElement(signInBtn);
        jsExecutor.executeScript("arguments[0].click();", signInBtn);
//        click(signInBtn);
    }

    public WebElement getErrorMsg() {
        return errorMsg;
    }
}
