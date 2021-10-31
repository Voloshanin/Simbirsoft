package ru.simbirsoft.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ru.simbirsoft.test.config.ConfProperties;
import ru.simbirsoft.test.config.WebDriverLibrary;
import ru.simbirsoft.test.pages.EmailFolderInbox;
import ru.simbirsoft.test.pages.LoginPage;
import java.time.Duration;

public class Main {
    private static String startURL = ConfProperties.getProperty("startURL");
    private static String EMAIL = ConfProperties.getProperty("EMAIL");
    private static String password = ConfProperties.getProperty("password");
    private static String subjectLetter = "Simbirsoft Тестовое задание. Волошанин";

    public static void main(String[] args) {
        WebDriver driver = WebDriverLibrary.getChrome();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
        EmailFolderInbox emailFolderInbox = PageFactory.initElements(driver, EmailFolderInbox.class);
        driver.get(startURL);
        loginPage.logIn(EMAIL, password);
        emailFolderInbox.sendNewEmail(EMAIL, subjectLetter);
    }
}
