package pl.kurs_selenium.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumHelper {
    private WebDriver driver;
    private WebDriverWait wait;

    public void waitForElementToBeVisible(WebDriver driver, WebElement element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitForElementToBeClickable(WebDriver driver, WebElement element) {
        wait = new WebDriverWait(driver,Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public void waitForElementToHaveText(WebDriver driver, By locator, String text) {
        wait = new WebDriverWait(driver,Duration.ofSeconds(3));
        wait.until(ExpectedConditions.textToBe(locator,text));
    }
    public void waitForElementToBePresent(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver,Duration.ofSeconds(3));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
