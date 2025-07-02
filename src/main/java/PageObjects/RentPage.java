package PageObjects;
// заполняем форму аренды на сайте, отправляем заказ, проверяем
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import models.RentData;

public class RentPage {
    private final WebDriver driver;

    private final By rentStartDateSelector = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By orderButtonSelector = By.xpath(".//*[starts-with(@class,'Order_Content')]//button[text()='Заказать']");

    private RentLengthPeriod rentLengthPeriod;
    private SubmitOrderModal submitOrderModal; // подтверждение заказа

    public RentPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setRentStartDate(String date) { // проставляем дату
        WebElement element = driver.findElement(this.rentStartDateSelector);
        element.sendKeys(date);
        element.sendKeys(Keys.ENTER);
    }

    public void setRentLengthPeriod(String rentLengthPeriod) {
        this.rentLengthPeriod = new RentLengthPeriod(this.driver);
        this.rentLengthPeriod.setRentLengthPeriod(rentLengthPeriod);
    }

    public void clickOrderButton() {
        WebElement element = this.driver.findElement(this.orderButtonSelector);
        element.click(); // "Заказать" - ТЫЦ
    }

    public void fillInForm(RentData data) { // имитируем заполнение дата и срок аренды
        this.setRentStartDate(data.getRentStartDate());
        this.setRentLengthPeriod(data.getRentLengthPeriod());

        this.clickOrderButton();
    }

    public void confirmOrder() { // подтверждаем заказ
        this.submitOrderModal = new SubmitOrderModal(this.driver);
        this.submitOrderModal.confirmOrder();
    }

    public boolean isOrderCreated() { // проверяем создание заказа
        this.submitOrderModal = new SubmitOrderModal(this.driver);

        return this.submitOrderModal.isOrderCreated();
    }
}
