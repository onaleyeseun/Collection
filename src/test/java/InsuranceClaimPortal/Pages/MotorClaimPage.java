package InsuranceClaimPortal.Pages;

import InsuranceClaimPortal.BaseClasses.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MotorClaimPage extends PageBase {
    public MotorClaimPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='bg-claim-insurance rounded']")
    private WebElement pageBanner;
    @FindBy(id = "home-tab")
    private WebElement ownDamageTab;
    @FindBy(id = "incident_date")
    private WebElement incidentDate;
    @FindBy(id = "garage_name")
    private WebElement garageName;
    @FindBy(id = "address")
    private WebElement garageAddress;
    @FindBy(xpath = "(//button[@data-toggle='modal'])[1]")
    private WebElement uploadInvoiceBtn;
    @FindBy(id = "item-name")
    private WebElement itemName;
    @FindBy(xpath = "//button[@data-type='plus']")
    private WebElement addQuantity;
    @FindBy(id = "amount")
    private WebElement amount;

    //Methods
    public WebElement getPageBanner() {
        return pageBanner;
    }
    public WebElement getOwnDamageTab() {
        return ownDamageTab;
    }
    public void clickOwnDamageTab() {
        click(ownDamageTab);
    }
    public WebElement getIncidentDate() {
        return incidentDate;
    }
    public void setIncidentDate(String attrName, String attrValue) {
        setAttribute(incidentDate, attrName, attrValue);
    }
    public void enterGarageName(String garageName) {
        enterText(this.garageName, garageName);
    }
    public WebElement getGarageAddress() {
        return garageAddress;
    }
    public void enterAddress(String address) {
        enterText(garageAddress, address);
    }
    public void clickUploadInvoiceBtn() {
        click(uploadInvoiceBtn);
    }
    public void enterItemName(String itemName) {
        enterText(this.itemName, itemName);
    }
    public void clickAddQuantity() {
        click(addQuantity);
    }
    public void enterAmount(String amount) {
        enterText(this.amount, amount);
    }
}

