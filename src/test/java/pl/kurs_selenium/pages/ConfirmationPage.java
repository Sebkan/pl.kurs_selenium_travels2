package pl.kurs_selenium.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.kurs_selenium.utils.SeleniumHelper;

public class ConfirmationPage {
    @FindBy(xpath = "//input[@class='RTL form-control coupon']")
    private WebElement couponInput;
    @FindBy(xpath = "//span[@class='btn btn-danger applycoupon btn-block']")
    private WebElement couponSubmit;
    @FindBy(name= "logged")
    private WebElement confirmBookingButton;
    private WebDriver driver;
    private static Logger logger = LogManager.getLogger();
    public ConfirmationPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }
    public ConfirmationPage couponCode() {
        SeleniumHelper.waitForElementToBeVisible(driver,couponInput);
        logger.info("Filling coupon input");
        couponInput.click();
        couponInput.sendKeys("test");
        couponSubmit.click();
        SeleniumHelper.waitForAlert(driver);
        logger.info("Alert pop-up");
        Alert alert = driver.switchTo().alert();
        alert.accept();
        logger.info("Alert accepted");
        return this;
    }
    public InvoicePage confrimBooking() {
        confirmBookingButton.click();
        return new InvoicePage(driver);
    }
}
