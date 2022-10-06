package pl.kurs_selenium.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pl.kurs_selenium.utils.DriverFactory;

public class BaseTest {
    protected static WebDriver driver;

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
