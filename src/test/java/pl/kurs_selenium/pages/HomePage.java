package pl.kurs_selenium.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import pl.kurs_selenium.utils.SeleniumHelper;

import java.util.List;

public class HomePage {
    @FindBy(xpath = "//a[contains(text(),'My Account')]")
    private List<WebElement> myAccountButton;
    @FindBy(xpath = "//a[contains(text(),'Sign Up')]")
    private List<WebElement> signUpButton;
    @FindBy(xpath = "//span[contains(text(),'Pick')]")
    private WebElement pickUpLocationSpan;
    @FindBy(xpath = "//div[@id='select2-drop']//input")
    private WebElement pickUpLocationInput;
    @FindBy(id = "departcar")
    private WebElement departDate;
    @FindBy(name = "pickupTime")
    private WebElement departTime;
    @FindBy(name = "dropoffTime")
    private WebElement returnTime;
    @FindBy(id = "returncar")
    private WebElement returnDate;
    @FindBy(xpath = "//button[contains(text(),'Search')]")
    private List<WebElement> searchButton;
    private static Logger logger = LogManager.getLogger();
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }
    public SignUpPage signUp() {
        logger.info("Searching for the My Account button and performing click");
        myAccountButton.stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        logger.info("Click My Account button performed");
        logger.info("Searching for the Sign Up button on Home Page and performing click");
        signUpButton.stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        logger.info("Click performed");
        return new SignUpPage(driver);
    }
    public CarSearchPage performCarSearch(String setCity,String startDate, String startTime, String endDate, String endTime) {
        pickUpLocationSpan.click();
        logger.info("Setting the city");
        pickUpLocationInput.sendKeys(setCity);
        String locator = String.format("//span[contains(text(),'%s')]",setCity);
        SeleniumHelper.waitForElementToBePresent(driver,By.xpath(locator));
        logger.info("Clicking the city name");
        driver.findElement(By.xpath(locator)).click();
        logger.info("Click performed");
        logger.info("Setting start date and time");
        departDate.clear();
        departDate.sendKeys(startDate);
        Select depart = new Select(departTime);
        depart.selectByVisibleText(startTime);
        logger.info("Setting end date");
        returnDate.clear();
        returnDate.sendKeys(endDate);
        Select dropOff = new Select(returnTime);
        dropOff.selectByVisibleText(endTime);
        logger.info("Clicking search Button");
        searchButton.stream()
                .filter(WebElement::isDisplayed)
                .findFirst()
                .ifPresent(WebElement::click);
        return new CarSearchPage(driver);
    }
}
