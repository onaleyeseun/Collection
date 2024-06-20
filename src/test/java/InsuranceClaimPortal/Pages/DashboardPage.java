package InsuranceClaimPortal.Pages;

import InsuranceClaimPortal.BaseClasses.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends PageBase {
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//p[text()='Insurance Claim Portal']/../*[2]")
    private WebElement pageDesc;
    @FindBy(xpath = "//a[@href='log_claim']")
    private WebElement logClaimBtn;

    public WebElement getPageDesc(){
        return pageDesc;
    }
    public void clickLogClaimBtn() {
        click(logClaimBtn);
    }
    public WebElement getLogClaimBtn() {
        return logClaimBtn;
    }
}
