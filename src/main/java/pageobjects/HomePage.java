package pageobjects;
// это вообще для РОМ
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver; // взаимодействие с браузейро

    // верхняя кнопка "Заказать"
    private By topOrderButton = By.className("Button_Button__ra12g");

    // нижняя кнопка "Заказать"
    private final By secondOrderButton = By.className("Button_Middle__1CSJM");

    // Конструктор
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // верхняя "Заказать"
    public void clickTopOrderButton() {
        WebElement button = driver.findElement(topOrderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        button.click();
    }

    // нижняя "Заказать"
    public void clickSecondOrderButton() {
        WebElement button = driver.findElement(secondOrderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        button.click();
    }
}