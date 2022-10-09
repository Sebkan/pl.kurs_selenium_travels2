package pl.kurs_selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.kurs_selenium.utils.SeleniumHelper;

public class CarDetailsPage {
    @FindBy(css = "[class='dismissButton']")
    private WebElement mapAlertOk;
    @FindBy(xpath = "//button[@class='gm-control-active gm-fullscreen-control']")
    private WebElement mapFullScreen;
    @FindBy(css = "button[class='btn btn-block btn-action btn-lg']")
    private WebElement bookNowButton;

    private WebDriver driver;
    public CarDetailsPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
        this.driver = driver;
    }
    public WebElement getCarBrandText(String carBrand) {
        String locator = String.format("//span[contains(text(),'%s')]", carBrand);
        SeleniumHelper.waitForElementToBePresent(driver,By.xpath(locator));
        return driver.findElement(By.xpath(locator));
    }
    public CarDetailsPage mapFullOnOff() {
        mapAlertOk.click();
        SeleniumHelper.waitForElementToBeClickable(driver,mapFullScreen);
        mapFullScreen.click();
        mapFullScreen.click();
        return this;
    }
    public ConfirmationPage bookNowButtonClick() {
        SeleniumHelper.waitForElementToBeVisible(driver,bookNowButton);
        bookNowButton.click();
        return new ConfirmationPage(driver);
    }

}
