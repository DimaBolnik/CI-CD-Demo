package ru.bolnik.owner.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config.properties"})
public interface WebDriverConfig extends Config {
    @Key("browser")
    @DefaultValue("CHROME")
    String browser();

    @Key("baseUrl")
    @DefaultValue("https://github.com")
    String baseUrl();
}
