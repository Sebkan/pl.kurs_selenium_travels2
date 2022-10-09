package pl.kurs_selenium.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.kurs_selenium.user.User;
import pl.kurs_selenium.utils.SeleniumHelper;

public class LoggedUserPage {
    @FindBy(xpath = "//i[@class='fa fa-car']")
    private WebElement carsButton;
    private WebDriver driver;
    private static Logger logger = LogManager.getLogger();
    public LoggedUserPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }
    public WebElement confirmHiText(User user) {
        String xpath = String.format("//h3[contains(text(),'%s %s')]",user.getFirstName(),user.getLastName());
        SeleniumHelper.waitForElementToBePresent(driver,By.xpath(xpath));
        return driver.findElement(By.xpath(xpath));
    }
    public HomePage carButtonClick() {
        logger.info("Performing click on Car Button");
        SeleniumHelper.waitForElementToBeClickable(driver,carsButton);
        carsButton.click();
        logger.info("Button Cars clicked");
        return new HomePage(driver);
    }

}
