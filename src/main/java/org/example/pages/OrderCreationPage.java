package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class OrderCreationPage {

    private final WebDriver driver;

    // "Для кого самокат"
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private final By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroField = By.className("select-search__input");
    private final By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//button[contains(text(), 'Далее')]");

    // "Про аренду"
    private final By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodField = By.xpath(".//div[@class='Dropdown-control']");
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[contains(text(), 'Заказать')]");

    // Модальное окно подтверждения
    private final By yesButton = By.xpath("//button[normalize-space()='Да']");
    private final By orderConfirmedHeader = By.xpath(".//*[contains(text(), 'Заказ оформлен')]");

    public OrderCreationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Методы шага 1
    // Заполнение поля имени
    public OrderCreationPage enterName(String name) {
        driver.findElement(nameField).sendKeys(name);
        return this;
    }
    //  Зполнение поля фамилии
    public OrderCreationPage enterSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
        return this;
    }
    // Заполенине поля адреса
    public OrderCreationPage enterAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
        return this;
    }
    // Заполнение поля метро
    public OrderCreationPage selectMetro(String stationName) {
        driver.findElement(metroField).sendKeys(stationName);
        // Ждём появления списка и кликаем по нужной станции
        By stationLocator = By.xpath(".//div[contains(text(), '" + stationName + "')]");
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(stationLocator));
        driver.findElement(stationLocator).click();
        return this;
    }

    public OrderCreationPage enterPhone(String phone) {
        driver.findElement(phoneField).sendKeys(phone);
        return this;
    }

    public OrderCreationPage clickNextButton() {
        driver.findElement(nextButton).click();
        return this;
    }

    // Заполнение всего шага 1 одним вызовом
    public OrderCreationPage fillFirstStep(String name, String surname, String address, String metro, String phone) {
        enterName(name);
        enterSurname(surname);
        enterAddress(address);
        selectMetro(metro);
        enterPhone(phone);
        clickNextButton();
        return this;
    }

    // Методы шага 2
    // Выбор даты
    public OrderCreationPage enterDate(String date) {
        driver.findElement(dateField).sendKeys(date);
        // Закрываем календарь кликом по пустой области
        driver.findElement(By.xpath(".//div[contains(@class, 'Order_Header__')]")).click();
        return this;
    }
    // Выбор срока аренды
    public OrderCreationPage selectRentalPeriod(String periodText) {
        driver.findElement(rentalPeriodField).click();
        By option = By.xpath(".//div[@class='Dropdown-option' and contains(text(), '" + periodText + "')]");
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(option));
        driver.findElement(option).click();
        return this;
    }

    // Ввод комментария курьеру
    public OrderCreationPage enterComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
        return this;
    }
    // Нажатие кнопки Заказать
    public OrderCreationPage clickOrderButton() {
        driver.findElement(orderButton).click();
        return this;
    }
    // Метод для выбора цвета самоката
    public OrderCreationPage selectColour(String colour) {
        By colourScooter = By.id(colour);
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(colourScooter));
        driver.findElement(colourScooter).click();
        return this;
    }

    // Заполнение всего шага 2 одним вызовом
    public OrderCreationPage fillSecondStep(String date, String rentalPeriod, String colour, String comment) {
        enterDate(date);
        selectRentalPeriod(rentalPeriod);
        selectColour(colour);
        enterComment(comment);
        clickOrderButton();
        return this;
    }

    // Подтверждение заказа
    public OrderCreationPage clickYesButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(yesButton));
        WebElement buttonYes = driver.findElement(yesButton);
        new Actions(driver).moveToElement(buttonYes).click().build().perform();
        return this;
    }

    public boolean isConfirmOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(orderConfirmedHeader));
        return driver.findElement(orderConfirmedHeader).isDisplayed();
    }
}
