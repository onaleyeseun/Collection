package utils.extentReports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();

    public static String localDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
        LocalDateTime now = LocalDateTime.now();
        return (dtf.format(now));
    }
    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("./Reports/Market Comparison report "+localDate()+".html");
        reporter.config().setReportName("TAAS: USSD DATA SERVICE REGRESSION REPORT");
        extentReports.attachReporter(reporter);
        reporter.config().setTheme(Theme.DARK);
        extentReports.setSystemInfo("Author", "Olawale Olapetan");
        extentReports.setSystemInfo("Platform", "Android, Web");
        extentReports.setSystemInfo("Platform Version", "13.0");
        extentReports.setSystemInfo("Test Sim Tariff plan", "Youth_koolmax");
        return extentReports;
    }
}
