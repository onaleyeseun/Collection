package InsuranceClaimPortal.BaseClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageBase {

    WebDriver driver;
    public PageBase(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void waitForElement(WebElement el) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOf(el));
    }
    public void click(WebElement element) {
        waitForElement(element);
        element.click();
    }
    public void enterText(WebElement el, String text) {
        waitForElement(el);
        el.sendKeys(text);
    }
}
