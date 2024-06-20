package InsuranceClaimPortal.BaseClasses;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TestBase {

    public static WebDriver driver;
    public static FileInputStream fis;
    public static Properties testData;
    String testData_filePath = "src/test/java/testData.properties";
    private static final Logger log = LogManager.getLogger(TestBase.class);
    public static EdgeOptions edgeOptions = new EdgeOptions();
    public static ChromeOptions chromeOptions = new ChromeOptions();
    public static FirefoxOptions firefoxOptions = new FirefoxOptions();
    public JavascriptExecutor jsExecutor;
    public String browser;
    public String url;

    public TestBase() {
        loadPropFile();
    }

    public void loadPropFile() {
        testData = new Properties();
        try {
            fis = new FileInputStream(testData_filePath);
            testData.load(fis);
        } catch (IOException e) {
            log.error("Please make sure the property file location is accurate");
        }
    }

    @BeforeClass
    public void setup(ITestContext context) {
        url = null;
        if(context.getName().contains("User")) {
            url = testData.getProperty("userPortal");
        } else if (context.getName().contains("Admin")) {
            url = testData.getProperty("adminPortal");
        }
//        url = testData.getProperty("userPortal");
        System.out.println("The current url is " + url);
        browser = testData.getProperty("browser");
        System.out.println("The browser is " + browser);

        switch (browser) {
            case "chrome":
                chromeOptions.setAcceptInsecureCerts(true);
                chromeOptions.addArguments("--remote-allow-origins=*");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(chromeOptions);
                break;
            case "edge":
                edgeOptions.setAcceptInsecureCerts(true);
                edgeOptions.addArguments("--remote-allow-origins=*");
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver(edgeOptions);
                break;
            case "firefox":
                firefoxOptions.setAcceptInsecureCerts(true);
                firefoxOptions.addArguments("--remote-allow-origins=*");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver(firefoxOptions);
                break;
            default:
                throw new IllegalArgumentException("The browser selected is not configured");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        driver.manage().window().maximize();
        getURL(url);
    }
    public void getURL(String url) {
        if(url == null) {
            throw new NullPointerException("The url cannot be empty, please make sure you have supplied valid URL");
        }else{
            driver.get(url);
        }

    }
    public void turnOffImplicitWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    public void turnOnImplicitWait() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
    }
    public void scrollToElement(WebElement element) {
        jsExecutor = (JavascriptExecutor) driver;
        if (browser.equalsIgnoreCase("chrome")) {
            jsExecutor.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", element);
        } else {
            jsExecutor.executeScript("arguments[0].scrollIntoView(true);" + "window.scrollBy(0,-100);", element);
        }
    }
    public void validateText(WebElement element, String message) {
        String actualMessage = "";
        try {
            actualMessage = element.getText();
            System.out.println("The message is: " + actualMessage);
            Assert.assertTrue(actualMessage.contains(message));
        } catch (AssertionError | NoSuchElementException e) {
            throw new AssertionError(e);
        }
    }
    public void validateAttribute(WebElement element, String attribute, boolean value) {
        try {
            Assert.assertEquals(Boolean.parseBoolean(element.getAttribute(attribute)), value);
        }catch (NoSuchElementException e) {
            throw new NoSuchElementException("The element is not available on the screen");
        }
    }
    public void dynamicWait(WebElement element, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        }catch (InterruptedException e) {
            log.info(e.getCause());
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
