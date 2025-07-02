package PageObjects;
// это вообще для РОМ
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver; // взаимодействие с браузером

    // Локатор кнопки "Заказать"
    private By topOrderButton = By.cssSelector("button.order-button");

    // Конструктор
    public HomePage(WebDriver driver) {
        this.driver = driver;

    }

    // Метод для клика по верхней кнопке заказа
    public void clickTopOrderButton() {
        WebElement button = driver.findElement(topOrderButton);
        button.click();
    }
}