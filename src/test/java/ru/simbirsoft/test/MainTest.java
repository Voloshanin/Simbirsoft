package ru.simbirsoft.test;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.simbirsoft.test.config.ConfProperties;
import ru.simbirsoft.test.pages.EmailFolderInbox;
import ru.simbirsoft.test.pages.LoginPage;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Тесты авторизации на сайте и отправке нового письма")
class MainTest {

    private RemoteWebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage;
    private EmailFolderInbox emailFolderInbox;
    private static String startURL = ConfProperties.getProperty("startURL");
    private static String EMAIL = ConfProperties.getProperty("EMAIL");
    private static String password = ConfProperties.getProperty("password");
    private static String subjectLetter = "Simbirsoft Тестовое задание. Волошанин";


    @BeforeEach()
    public void setup() throws MalformedURLException {
        ChromeOptions chromeOptions = new ChromeOptions();
        driver =new RemoteWebDriver(new URL("http://localhost:4444"), chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
        loginPage = PageFactory.initElements(driver, LoginPage.class);
        emailFolderInbox = PageFactory.initElements(driver, EmailFolderInbox.class);
        driver.get(startURL);
    }

    @Test
    @DisplayName("Тест по авторизации и отправке нового письма.")
    public void sendNewEmail() {
        loginPage.inputLogin(EMAIL);
        loginPage.inputPassword(password);
        emailFolderInbox.clickButtonNewLetter();
        int numberBefore = emailFolderInbox.numberEmailInbox() + 1;
        emailFolderInbox.enterRecipientsAddress(EMAIL);
        emailFolderInbox.enterSubjectLetter(subjectLetter);
        emailFolderInbox.enterTextLetter(emailFolderInbox.findLetters("Simbirsoft Тестовое задание"));
        emailFolderInbox.clickButtonSendEmail();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='bsU']")));
        int numberAfter = emailFolderInbox.numberEmailInbox();
        assertEquals(numberBefore, numberAfter);

    }

    @AfterEach
    public void quit(){
        if(driver != null) {
            driver.quit();
        }
    }
}