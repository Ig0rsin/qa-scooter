import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {
    protected WebDriver driver;

    @BeforeEach
    public void setup() {
        this.driver = new ChromeDriver(); // выбор браузера
        //this.driver = new FirefoxDriver();
    }
    @AfterEach
    public void tearDown() {
        this.driver.quit(); // закрылы браузер
    }
}
