import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class TestBase {
    protected WebDriver driver;

    // Локатор для кукухи
    private By cookieConsentButton = By.id("rcc-confirm-button");
    // Или так: private By cookieConsentButton = By.cssSelector("button.App_CookieButton__3cvqF");

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

        driver.get("https://qa-scooter.praktikum-services.ru/");

        // ТЫЦ по кукухе
        try {
            WebElement consentBtn = driver.findElement(cookieConsentButton);
            consentBtn.click();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            // Если нет, то и нет
        }

        //driver.navigate().refresh(); // очистка

        // ждём, пока отдуплится
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));
    }

    @AfterEach
    public void tearDown() {
        this.driver.quit(); // закрыли браузейро
    }
}
