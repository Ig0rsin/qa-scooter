import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static pageobjects.HomePage.MAIN_URL;

public class TestBase {
    protected WebDriver driver;

    /* Локатор для куки
    private By cookieConsentButton = By.id("rcc-confirm-button");*/

    @BeforeEach
    public void setup() {
        String browser = "firefox"; // или "chrome"
        if ("chrome".equalsIgnoreCase(browser)) {
            driver = new ChromeDriver();
        } else if ("firefox".equalsIgnoreCase(browser)) {
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Неизвестный браузер: " + browser);
        }

        driver.get(MAIN_URL); // URL автоматом

        pageobjects.HomePage homePage = new pageobjects.HomePage(driver); // объект homePage

        homePage.acceptCookiesIfPresent(); // отрабатываем куку

        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25)); // (НЕ)ждём
    }
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // закрываем браузер
        }
    }

    public WebDriver getWebDriver() {
        return this.driver;
    }
}
