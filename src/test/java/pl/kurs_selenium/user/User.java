package pl.kurs_selenium.user;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class User {

    private String firstName = "Sebastian";
    private String lastName = "Sebuskowy";
    private String phoneNumber = "666666666";
    private String email;
    private String password = "test123";


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
