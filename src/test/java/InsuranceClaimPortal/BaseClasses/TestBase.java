package InsuranceClaimPortal.BaseClasses;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestBase {

    public static WebDriver driver;
    public static FileInputStream fis;
    public static Properties testData;
    String testData_filePath = "src/test/java/testData.properties";
    private static Logger log = LogManager.getLogger(TestBase.class);
    public static EdgeOptions edgeOptions = new EdgeOptions();
    public static ChromeOptions chromeOptions = new ChromeOptions();
    public static FirefoxOptions firefoxOptions = new FirefoxOptions();

    public TestBase() {
        loadPropFile();
    }

    public void loadPropFile() {
        testData = new Properties();
        try {
            fis = new FileInputStream(testData_filePath);
            testData.load(fis);
        }catch (IOException e) {
            log.error("Please make sure the property file location is accurate");
        }
    }
    @BeforeClass
    public void setup() {
        String browser = testData.getProperty("browser");

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
            default: throw new IllegalArgumentException("The browser selected is not configured");
        }
    }




}
