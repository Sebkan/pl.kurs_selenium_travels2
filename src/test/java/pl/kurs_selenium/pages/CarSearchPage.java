package pl.kurs_selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pl.kurs_selenium.utils.SeleniumHelper;

import java.util.List;
import java.util.stream.Collectors;

public class CarSearchPage {
    @FindBy(xpath = "//h4//b")
    private List<WebElement> carHeadings;
    private WebDriver driver;
    public CarSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    public CarDetailsPage chooseCar (String car) {
        SeleniumHelper.waitForElementToBeClickable(driver, By.xpath("//h4//b"));
        carHeadings.stream()
                .filter(e-> e.getText().contains(car))
                .findFirst()
                .ifPresent(WebElement::click);
        return new CarDetailsPage(driver);

    }
}
