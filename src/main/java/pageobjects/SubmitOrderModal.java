package pageobjects;
// имитируем подтверждение заказа
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SubmitOrderModal {
    private final WebDriver driver;

    private final By confirmButtonSelector = By.xpath(".//div[starts-with(@class, 'Order_Modal')]//button[text()='Да']");
    private final By confirmedOrderLabelSelector = By.xpath(".//div[starts-with(@class, 'Order_Modal')]//div[text()='Заказ оформлен']");
    private final By checkStatusButtonSelector = By.xpath(".//div[starts-with(@class, 'Order_Modal')]//button[text()='Посмотреть статус']");

    public SubmitOrderModal(WebDriver driver) {
        this.driver = driver;
    }

    public void confirmOrder() {
        WebElement element = this.driver.findElement(this.confirmButtonSelector);
        element.click();
    }

    public boolean isOrderCreated() { // заказ оформлен - посмотреть статус если видит элементы
        WebElement labelElement = this.driver.findElement(this.confirmedOrderLabelSelector);
        WebElement buttonElement = this.driver.findElement(this.checkStatusButtonSelector);
        return labelElement.isDisplayed()
                && buttonElement.isDisplayed();
    }
}
