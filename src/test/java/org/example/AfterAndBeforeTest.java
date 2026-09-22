package org.example;

import io.github.bonigarcia.wdm.managers.EdgeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AfterAndBeforeTest {

    protected WebDriver driver;
    private static final Logger log = LoggerFactory.getLogger(AfterAndBeforeTest.class);

    @BeforeEach
    void setUp() {
        EdgeDriverManager.getInstance().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
