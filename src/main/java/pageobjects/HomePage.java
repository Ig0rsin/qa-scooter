package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    // финальная переменная с URL
    public static final String MAIN_URL = "https://qa-scooter.praktikum-services.ru/";

    private WebDriver driver; // взаимодействие с браузейро

    // локаторы для кнопок "Заказать"
    private By topOrderButton = By.className("Button_Button__ra12g");
    private final By secondOrderButton = By.className("Button_Middle__1CSJM");
    // локатор кукухи
    private By cookieConsentButton = By.id("rcc-confirm-button");

    // Конструктор
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Методы для кнопок
    public void clickTopOrderButton() {
        WebElement button = driver.findElement(topOrderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        button.click();
    }

    public void clickSecondOrderButton() {
        WebElement button = driver.findElement(secondOrderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        button.click();
    }

    // ищем куку
    public WebElement waitForElement(By locator, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // ТЫЦ по кукухе
    public void acceptCookiesIfPresent() {
        try {
            WebElement consentBtn = waitForElement(cookieConsentButton, 5);
            consentBtn.click();
        } catch (org.openqa.selenium.TimeoutException e) { // или ничего
        }
    }
}