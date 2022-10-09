package pl.kurs_selenium.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.kurs_selenium.pages.HomePage;
import pl.kurs_selenium.user.User;

import java.util.List;
import java.util.stream.Collectors;

public class SignUpTest extends BaseTest {
    @Test
    public void registerAccountTest(){
        WebElement signUp = new HomePage(driver)
                .signUp()
                .getSignUpText();
        Assert.assertEquals(signUp.getText(),"SIGN UP");
    }
    @Test
    public void registerAccountWithoutData() {
        List<WebElement> alerts = new HomePage(driver)
                .signUp()
                .sendForm()
                .getAlertFullList();
        Assert.assertTrue(alerts.size() == 5);
    }
    @Test
    public void registerAccountWithoutInvalidEmail() {
        User user = new User();
        user.setEmail("test.pl");
        List<String> wrongEmailAlert = new HomePage(driver)
                .signUp()
                .sendFormWithInvalidData(user)
                .sendForm()
                .getAlertFullList()
                .stream()
                .filter(WebElement::isDisplayed)
                .map(WebElement::getText)
                .collect(Collectors.toList());
        Assert.assertTrue(wrongEmailAlert.get(0).contains("valid email"));
    }
    @Test
    public void registerAccountWithShortPassword() {
        User user = new User();
        user.setEmail("test@test.pl");
        user.setPassword("wrong");
        List<String> shortPasswordAlert = new HomePage(driver)
                .signUp()
                .sendFormWithInvalidData(user)
                .sendForm()
                .getAlertFullList()
                .stream()
                .filter(WebElement::isDisplayed)
                .map(WebElement::getText)
                .collect(Collectors.toList());
        Assert.assertTrue(shortPasswordAlert.get(0).contains("Password field must be at least 6"));
    }
    @Test
    public void registerAccountWithValidData() {
        int randomNum = (int)(Math.random()*10000);
        User user = new User();
        user.setEmail("test"+randomNum+"@test.pl");
         WebElement hiText = new HomePage(driver)
                .signUp()
                .sendFormWithValidData(user)
                .confirmHiText(user);
         Assert.assertTrue(hiText.getText().contains(user.getFirstName()), user.getLastName());
    }
}
