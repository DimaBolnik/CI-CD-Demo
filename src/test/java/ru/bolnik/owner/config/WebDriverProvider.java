package ru.bolnik.owner.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.function.Supplier;

public class WebDriverProvider implements Supplier<WebDriver> {

    private final WebDriverConfig config;


    public WebDriverProvider() {
        this.config = new WebDriverConfig();
    }

    @Override
    public WebDriver get() {
        WebDriver driver = createDriver();
        driver.get(config.getBaseUrl());
        return driver;
    }

    public WebDriver createDriver() {
        return switch (config.getBrowser()) {
            case CHROME -> new ChromeDriver();
            case FIREFOX -> new FirefoxDriver();
        };
    }
}
