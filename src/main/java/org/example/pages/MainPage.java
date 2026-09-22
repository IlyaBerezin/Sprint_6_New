package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.example.constants.Constants.*;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;

    // кнопка "Заказать"  вверху страницы
    private final By upperOrder = By.xpath(".//button[@class = 'Button_Button__ra12g']");
    // кнопка "да все привыкли"
    private final By agreeCookie = By.xpath("//button[contains(text(), 'да все привыкли')]");
    // кнопка "Заказать" внизу страницы
    private final By downOrder = By.xpath(".//div[@class = 'Home_FinishButton__1_cWm']/button[contains(text(), 'Заказать')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }


    // Нажатие на кнопку "Заказать" вверху
    public MainPage clickOrderUp() {
        driver.findElement(upperOrder).click();
        return this;
    }

    // Скролл до кнопки "Заказать" внизу
    public void scrollToOrderDown() {
        WebElement orderDownElement = driver.findElement(downOrder);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", orderDownElement);
    }

    // Нажатие на вопрос по индексу
    public MainPage clickAccordionItem(int id) {
        scrollToOrderDown();
        By questionLocator = By.id("accordion__heading-" + id);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(questionLocator));
        driver.findElement(questionLocator).click();
        return this;
    }

    // Получение текста ответа по индексу
    public String getAccordionPanelText(int id) {
        By panelLocator = By.id("accordion__panel-" + id);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(panelLocator));
        return driver.findElement(panelLocator).getText();
    }

    // Нажатие на кнопку "да все привыкли"
    public MainPage clickApproval() {
        driver.findElement(agreeCookie).click();
        return this;
    }

    // Нажатие на кнопку "Заказать" внизу страницы
    public MainPage clickOrderDown() {
        scrollToOrderDown();
        driver.findElement(downOrder).click();
        return this;
    }

    // Открытие главной страницы
    public MainPage open() {
        driver.get(BASE_URL);
        return this;
    }
}
