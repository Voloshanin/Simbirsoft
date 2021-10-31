package ru.simbirsoft.test.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(xpath = "//input[@type='email']")
    private WebElement inpLogin;
    @FindBy(xpath = "//input[@type='password']")
    private WebElement inpPassword;

    public LoginPage(WebDriver driver){this.driver = driver;}

    public void inputLogin(String login) {
       inpLogin.sendKeys(login, Keys.ENTER);
       wait =new WebDriverWait(driver, Duration.ofSeconds(10));
       wait.until(ExpectedConditions.visibilityOf(inpPassword));
    }

    public void inputPassword(String password) {;
        inpPassword.sendKeys(password, Keys.ENTER);
    }

    public void logIn(String EMAIL, String password) {
        inputLogin(EMAIL);
        inputPassword(password);
    }
}
