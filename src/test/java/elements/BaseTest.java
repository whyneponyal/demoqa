package elements;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.FileDownloadMode;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Configuration.browser;

public class BaseTest {
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        browser = "chrome";
        Configuration.headless = false;
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.downloadsFolder = "target/downloads";
        Configuration.fileDownload = FileDownloadMode.FOLDER;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true).includeSelenideSteps(true));

    }

    @BeforeAll
    public static void init() {
        setUp();
    }

    @AfterAll
    public static void tearDown() {
        Selenide.closeWebDriver();
    }
}
