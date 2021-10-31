package ru.simbirsoft.test.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverLibrary {
    public static WebDriver getChrome(){
       System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        WebDriver driver = new ChromeDriver();
        return driver;
    }
}
