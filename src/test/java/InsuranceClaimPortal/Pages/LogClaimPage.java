package InsuranceClaimPortal.Pages;

import InsuranceClaimPortal.BaseClasses.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LogClaimPage extends PageBase {
    public LogClaimPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "customRadio2")
    private WebElement motorClaimRadioInput;
    @FindBy(id = "customRadio1")
    private WebElement insuranceClaimRadioInput;
    @FindBy(xpath = "//label[@for='customRadio2']")
    private WebElement motorClaimLabel;
    @FindBy(id = "motorCategory")
    private WebElement motorClaimSelect;
    @FindBy(id = "insuranceCategory")
    private WebElement insuranceClaimSelect;
    @FindBy(id = "submitBTN")
    private WebElement proceedBtn;

    // Methods
    public void clickMotorClaimRadioInput() {
//        waitForElement(40).until(ExpectedConditions.elementToBeClickable(motorClaimRadioInput));
        jsExecutor.executeScript("arguments[0].click();", motorClaimRadioInput);
//        motorClaimRadioInput.click();
    }
    public void clickInsuranceClaimRadioInput() {
        jsExecutor.executeScript("arguments[0].click();", insuranceClaimRadioInput);
//        click(insuranceClaimRadioInput);
    }

    public WebElement getInsuranceClaimRadioInput() {
        return insuranceClaimRadioInput;
    }

    public WebElement getMotorClaimRadioInput() {
        return motorClaimRadioInput;
    }
    public WebElement getMotorClaimLabel() {
        return motorClaimLabel;
    }
    public WebElement getMotorClaimSelect() {
        return motorClaimSelect;
    }
    public void clickMotorClaimSelect() {
        click(motorClaimSelect);
    }
    public WebElement getInsuranceClaimSelect() {
        return insuranceClaimSelect;
    }
    public void clickInsuranceClaimSelect() {
        click(insuranceClaimSelect);
    }
    public WebElement getProceedBtn() {
        return proceedBtn;
    }
    public void clickProceedBtn() {
        click(proceedBtn);
    }
}
