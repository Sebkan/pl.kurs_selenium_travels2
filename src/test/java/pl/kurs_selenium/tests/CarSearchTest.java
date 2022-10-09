package pl.kurs_selenium.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pl.kurs_selenium.pages.CarDetailsPage;
import pl.kurs_selenium.pages.HomePage;
import pl.kurs_selenium.pages.LoggedUserPage;
import pl.kurs_selenium.user.User;
import pl.kurs_selenium.utils.SeleniumHelper;

import java.io.IOException;
import java.util.List;

public class CarSearchTest extends BaseTest{
    @Test
    public void carSearch() throws IOException {
        ExtentTest test = extentReports.createTest("Car Search Test");
        int randomNum = (int)(Math.random()*10000);
        String car = "Kia";
        User user = new User();
        user.setEmail("test"+randomNum+"@test.pl");
        WebElement userName = new HomePage(driver)
                .signUp()
                .sendFormWithValidData(user)
                .confirmHiText(user);
        Assert.assertEquals(userName.getText(), "Hi, "+user.getFirstName()+" "+user.getLastName());
        test.log(Status.PASS,"Greeting text equals name and lastname of the user",SeleniumHelper.getScreenshot(driver));

         WebElement carText = new LoggedUserPage(driver)
                .carButtonClick()
                .performCarSearch("Manchester","08/10/2022","08:00","10/10/2022","20:00")
                .chooseCar(car)
                .getCarBrandText(car);
        Assert.assertTrue(carText.getText().contains(car.toUpperCase()));
        test.log(Status.PASS,"Assertion of car name chosen", SeleniumHelper.getScreenshot(driver));

        WebElement reservedCar = new CarDetailsPage(driver)
                .mapFullOnOff()
                .bookNowButtonClick()
                .couponCode().
                confrimBooking()
                .reserveCar()
                .getReservedText();
        Assert.assertEquals(reservedCar.getText(),"RESERVED");
        test.log(Status.PASS,"Assertion of Car Reserved passed",SeleniumHelper.getScreenshot(driver));
    }
}
