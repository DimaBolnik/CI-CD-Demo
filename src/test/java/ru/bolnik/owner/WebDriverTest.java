package ru.bolnik.owner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import ru.bolnik.owner.config.WebDriverProvider;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WebDriverTest {
    private WebDriver driver;

    @BeforeEach
    void startDriver() {
        driver = new WebDriverProvider().get();
    }

    @AfterEach
    void stopDriver() {
        driver.quit();
    }

    @Test
    void testGithub() {
        String title = driver.getTitle();
        assertTrue(title.contains("GitHub"));
    }


}
