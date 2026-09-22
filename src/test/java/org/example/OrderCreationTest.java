package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.example.pages.MainPage;
import org.example.pages.OrderCreationPage;
import static org.example.constants.Data.*;


import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderCreationTest extends AfterAndBeforeTest {

    // Тест верхней и нижней кнопки Заказать
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
}
