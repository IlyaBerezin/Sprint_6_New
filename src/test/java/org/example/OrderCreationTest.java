package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.example.pages.MainPage;
import org.example.pages.OrderCreationPage;
import static org.example.constants.Data.*;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;

import org.junit.jupiter.api.AfterEach;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderCreationTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        //driver = new ChromeDriver();
        System.setProperty("webdriver.edge.driver", "D:\\AutomationQA\\Projects\\Berezin_samokat_sprint6\\msedgedriver.exe");
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }
    // Тест верхней кнопки Заказать
    @ParameterizedTest
    @CsvSource(delimiter = '|', value = {
            "upperBottom|Березин|Илья|Москва, ул. Тверская, 1|Маяковская|79991234009",
            "downBottom|Василий|Петров|Москва, ул. Пушкина, 11|Сокольники|79997674321"
    })
    public void orderScooterTest(String buttonType, String name, String surname,
                                 String address, String metro, String phone) {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        mainPage.clickApproval();

        // Выбор кнопки «Заказать» в зависимости от параметра
        if ("upperBottom".equals(buttonType)) {
            mainPage.clickOrderUp();
        } else {
            mainPage.clickOrderDown();
        }

        OrderCreationPage orderPage = new OrderCreationPage(driver);

        // Шаг 1
        orderPage.fillFirstStep(name, surname, address, metro, phone);

        // Шаг 2
        orderPage.fillSecondStep(DATE, ONE_DAY, BLACK_COLOR, COMMENT_ORDER);

        // Подтверждение
        orderPage.clickYesButton();
        boolean confirmed = orderPage.isConfirmOrder();

        assertTrue(confirmed, "Заказ не оформлен через кнопку: " + buttonType);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
