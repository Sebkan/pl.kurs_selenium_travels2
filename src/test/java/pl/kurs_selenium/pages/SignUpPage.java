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

import java.util.List;

public class SignUpPage {
    @FindBy(xpath = "//div[text()='Sign Up']")
    private WebElement signUpText;
    @FindBy(name = "firstname")
    private WebElement firstNameInput;
    @FindBy(name = "lastname")
    private WebElement lastNameInput;
    @FindBy(name = "phone")
    private WebElement phoneInput;
    @FindBy(name = "email")
    private WebElement emailInput;
    @FindBy(name = "password")
    private WebElement passwordInput;
    @FindBy(name = "confirmpassword")
    private WebElement confirmPassInput;
    @FindBy(css = "button[class='signupbtn btn_full btn btn-action btn-block btn-lg']")
    private WebElement signUpButton;
    @FindBy(xpath = "//div[@class='alert alert-danger']//p")
    private List<WebElement> alertFullList;
    private WebDriver driver;
    private static Logger logger = LogManager.getLogger();
    public SignUpPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }
    public WebElement getSignUpText() {
        return signUpText;
    }
    public SignUpPage sendForm() {
        logger.info("Performing the click on Sign Up Button on Sign Up Page");
        signUpButton.click();
                return this;
    }
    public List<WebElement> getAlertFullList() {
        SeleniumHelper.waitForElementToBePresent(driver, By.xpath("//div[@class='alert alert-danger']//p"));
        return alertFullList;
    }
    public SignUpPage sendFormWithInvalidData(User user) {
        logger.info("Commenced inserting the User data");
        firstNameInput.sendKeys(user.getFirstName());
        lastNameInput.sendKeys(user.getLastName());
        phoneInput.sendKeys(user.getPhoneNumber());
        emailInput.sendKeys(user.getEmail());
        passwordInput.sendKeys(user.getPassword());
        confirmPassInput.sendKeys(user.getPassword());
        logger.info("Completed inserting User data");
        return this;
    }
    public LoggedUserPage sendFormWithValidData(User user) {
        logger.info("Commenced inserting the User data");
        firstNameInput.sendKeys(user.getFirstName());
        lastNameInput.sendKeys(user.getLastName());
        phoneInput.sendKeys(user.getPhoneNumber());
        emailInput.sendKeys(user.getEmail());
        passwordInput.sendKeys(user.getPassword());
        confirmPassInput.sendKeys(user.getPassword());
        logger.info("Completed inserting User data");
        logger.info("Performing Sign Up button click");
        signUpButton.click();
        logger.info("Button click completed");
        return new LoggedUserPage(driver);
    }

}
