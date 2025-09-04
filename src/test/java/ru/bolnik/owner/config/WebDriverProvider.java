package ru.bolnik.owner.config;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.function.Supplier;

public class WebDriverProvider implements Supplier<WebDriver> {

    private final WebDriverConfig config = ConfigFactory.create(WebDriverConfig.class);



    @Override
    public WebDriver get() {
        WebDriver driver = createDriver();
        driver.get(config.baseUrl());
        return driver;
    }

    public WebDriver createDriver() {
        String browser = config.browser().toUpperCase();
        return switch (browser) {
            case "CHROME" -> new ChromeDriver();
            case "FIREFOX" -> new FirefoxDriver();
            default -> throw new IllegalArgumentException("Unknown browser: " + browser);
        };
    }
}
