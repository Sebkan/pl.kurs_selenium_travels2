package pl.kurs_selenium.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pl.kurs_selenium.utils.DriverFactory;

public class BaseTest {
    public static ExtentHtmlReporter extentHtmlReporter;
    public static ExtentReports extentReports;

    protected WebDriver driver;

    @BeforeSuite
    public static void beforeSuite(){
        extentHtmlReporter = new ExtentHtmlReporter("index.html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentHtmlReporter);
    }
    @AfterSuite
    public static void afterSuite() {
        extentHtmlReporter.flush();
        extentReports.flush();
    }
    @BeforeMethod
    public  void setup() {
        driver = DriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.get("http://kurs-selenium.pl");
    }
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
