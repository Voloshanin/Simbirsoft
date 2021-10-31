package ru.simbirsoft.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class EmailFolderInbox {
    private WebDriver driver;
    private WebDriverWait wait;
    @FindBy(xpath = "//div[@class='T-I T-I-KE L3']")
    private WebElement buttonNewLetter;
    @FindBy(xpath = "//textarea[@aria-label='Кому']")
    private WebElement recipientAddress;
    @FindBy(xpath = "//input[@name='subjectbox']")
    private WebElement subjectLetter;
    @FindBy(xpath = "//div[@aria-label='Текст письма']")
    private WebElement textLetter;
    @FindBy(xpath = "//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3']")
    private WebElement buttonSendEmail;

    public EmailFolderInbox(WebDriver driver){
        this.driver=driver;
    }

    public int findLetters(String subjectLetter){
        List<WebElement> letters = driver.findElements(By.xpath("//span[text()='" +
                                                                           subjectLetter +
                                                                                            "']"));
        return letters.size()/2;
    }

    public void clickButtonNewLetter() {
        buttonNewLetter.click();
    }

    public void  enterRecipientsAddress(String EMAIL) {
        recipientAddress.sendKeys(EMAIL);
    }

    public void enterSubjectLetter(String subject) {
        subjectLetter.sendKeys(subject);
    }

    public void enterTextLetter(int findLetters) {
        textLetter.sendKeys("Всего с темой 'Simbirsoft Тестовое задание' " +
                                               "найдено " + findLetters("Simbirsoft Тестовое задание")
                                                                                                         + " писем.");
    }

    public void clickButtonSendEmail() {
        buttonSendEmail.click();
    }

    public int numberEmailInbox() {
         return Integer.parseInt(driver.findElement(By.xpath("//div[@class='bsU']")).getText());

    }

    public void sendNewEmail(String recipientAddress, String subjectLetter){
        clickButtonNewLetter();
        enterRecipientsAddress(recipientAddress);
        enterSubjectLetter(subjectLetter);
        enterTextLetter(findLetters("Simbirsoft Тестовое задание"));
        clickButtonSendEmail();
    }
}
