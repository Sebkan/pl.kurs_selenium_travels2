package pl.kurs_selenium.utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class SeleniumHelper {
    private static WebDriverWait wait;
    public static MediaEntityModelProvider getScreenshot(WebDriver driver) throws IOException {
        MediaEntityModelProvider modelProvider = MediaEntityBuilder
                                                .createScreenCaptureFromPath(takeScreenshot(driver))
                                                .build();
        return modelProvider;
    }
    private static String takeScreenshot(WebDriver driver) throws IOException {
        int randomNumber = (int)(Math.random()*1000);
        String pathName = "src/test/resources/sceenshots/screenshot"+randomNumber+".png";
        TakesScreenshot screenshot = (TakesScreenshot) driver;
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File(pathName));
        return pathName;
    }

    public static void waitForElementToBeVisible(WebDriver driver, WebElement element) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public static void waitForElementToBeClickable(WebDriver driver, WebElement element) {
        wait = new WebDriverWait(driver,Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public static void waitForElementToHaveText(WebDriver driver, By locator, String text) {
        wait = new WebDriverWait(driver,Duration.ofSeconds(3));
        wait.until(ExpectedConditions.textToBe(locator,text));
    }
    public static void waitForElementToBePresent(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver,Duration.ofSeconds(3));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
    public static void waitForElementToBeClickable(WebDriver driver, By locator) {
        wait = new WebDriverWait(driver,Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public static void waitForAlert(WebDriver driver) {
        wait = new WebDriverWait(driver,Duration.ofSeconds(3));
        wait.until(ExpectedConditions.alertIsPresent());
    }
}
