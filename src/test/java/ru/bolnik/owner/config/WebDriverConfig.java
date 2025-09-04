package ru.bolnik.owner.config;

public class WebDriverConfig {

    public String getBaseUrl() {
        return "https://github.com";
    }

    public Browser getBrowser() {
        return Browser.CHROME;
    }
}
