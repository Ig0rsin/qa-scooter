import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TestBase {
    protected WebDriver driver;

    @BeforeEach
    public void setup() {
        //this.driver = new ChromeDriver(); // выбор браузера
        this.driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        /*WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id("secondOrderButton")));
        button.click();*/

    }
    @AfterEach
    public void tearDown() {
        this.driver.quit(); // закрылы браузер
    }
}
