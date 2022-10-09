package pl.kurs_selenium.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.kurs_selenium.utils.SeleniumHelper;

public class InvoicePage {
    @FindBy(css = "button[class='btn btn-default arrivalpay']")
    private WebElement payOnArrivalButton;
    @FindBy (xpath = "//b[text()='Reserved']")
    private WebElement reservedText;
    private WebDriver driver;
    private static Logger logger = LogManager.getLogger();

    public InvoicePage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }
    public InvoicePage reserveCar() {
        SeleniumHelper.waitForElementToBeVisible(driver,payOnArrivalButton);
        logger.info("Clicking on Pay On Arrival Button");
        payOnArrivalButton.click();
        SeleniumHelper.waitForAlert(driver);
        logger.info("Accepting the alert");
        Alert alert = driver.switchTo().alert();
        alert.accept();
        logger.info("Alert accepted");
        return this;
    }
    public WebElement getReservedText() {
        SeleniumHelper.waitForElementToBeVisible(driver,reservedText);
        return reservedText;
    }
}
