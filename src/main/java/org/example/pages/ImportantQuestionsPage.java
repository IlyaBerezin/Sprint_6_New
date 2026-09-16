package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ImportantQuestionsPage {

    private final WebDriver driver;
    //переменная с url адресом
    private String url = "https://qa-scooter.education-services.ru/";
    //элемент страницы "Вопросы о важном"
    private By titleOfQuestions = By.xpath("//div[contains(text(), 'Вопросы о важном']");
    // локатор стрелок из блока «Вопросы о важном»
    private final String accordionHeading = "accordion__heading-";
    // локатор текстов с ответами на вопросы из блока «Вопросы о важном»
    private final String textOfQuestions = "accordion__panel-";

    public ImportantQuestionsPage(WebDriver driver) {
        this.driver = driver;
    }

    //скролл к «Вопросы о важном»
    public void scrollToHomeFaq() {
        WebElement homeFaqElement = driver.findElement(titleOfQuestions);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", homeFaqElement);
    }

    // метод клика на вопрос из раздела «Вопросы о важном»
    public void clickOnHeading(String id) {
        By headingLocator = By.id(accordionHeading + id);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(headingLocator));

        driver.findElement((headingLocator)).click();
    }

    // метод получения ответа
    public String getTextAnswer(String id) {
        By answerOfQuestions = By.id(textOfQuestions + id);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(answerOfQuestions));

        return driver.findElement(answerOfQuestions).getText();
    }

}
