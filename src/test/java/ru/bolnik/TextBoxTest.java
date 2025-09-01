package ru.bolnik;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.MutableCapabilities;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

//@Disabled
public class TextBoxTest {


    @BeforeAll
    public static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";

        // Читаем значения из Gradle
        String browser = System.getProperty("browser", "chrome");
        String browserVersion = System.getProperty("browserVersion", "");
        String browserSize = System.getProperty("browserSize", "1920x1080");

        Configuration.browser = browser;
        Configuration.browserSize = browserSize;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        // capabilities для Selenoid
        MutableCapabilities capabilities = new MutableCapabilities();
        capabilities.setCapability("browserName", browser);
        if (!browserVersion.isEmpty()) {
            capabilities.setCapability("browserVersion", browserVersion);
        }

        Map<String, Object> selenoidOptions = new HashMap<>();
        selenoidOptions.put("enableVNC", true);
        selenoidOptions.put("enableVideo", true);
        capabilities.setCapability("selenoid:options", selenoidOptions);

        Configuration.browserCapabilities = capabilities;

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true));
    }

    @Test
    public void testTextBox() {
        String userName = "Dima";
        String mail = "DimaBolnik@gmail.com";

        step("Открываем страницу Text Box", () -> {
            open("/text-box");
            $("h1.text-center").shouldHave(text("Text Box"));
        });

        step("Заполняем форму", () -> {
            $("#userName").setValue(userName);
            $("#userEmail").setValue(mail);
            $("#currentAddress").setValue("Russia, Penza");
            $("#permanentAddress").setValue("Russia, Penza");
        });

        step("Отправляем форму", () -> {
            $("#submit.btn-primary").click();
        });

        step("Проверяем результат", () -> {
            $("#output").$("#name").shouldHave(text(userName));
            $("#output #email").shouldHave(text(mail));
        });
    }
}
