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

    public WebElement getPageDesc(){
        return pageDesc;
    }
}
