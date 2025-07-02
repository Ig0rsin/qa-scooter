package PageObjects;
// здесь выбираем срок и дату аренды
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RentLengthPeriod {
    private final WebDriver driver;

    private final By rentLengthPeriodSelector = By.cssSelector(".Dropdown-root"); // локатор выбора периода аренды
    private By rentLengthPeriodItemSelector;

    public RentLengthPeriod(WebDriver driver) {
        this.driver = driver;
    }

        public void setRentLengthPeriod(String rentLengthPeriod) {
            this.expandMenu(); // имитация выбора срока аренды

            this.rentLengthPeriodItemSelector = By.xpath(".//*[@class='Dropdown-option' and text()='"+rentLengthPeriod+"']");
            WebElement element = driver.findElement(this.rentLengthPeriodItemSelector);
            this.scrollToElement(element);
            element.click(); // нашли элемент
        }

        public void expandMenu(){ // менюшка со сроками
            WebElement element = driver.findElement(this.rentLengthPeriodSelector);
            element.click(); // ТЫЦ
        }

        private void scrollToElement(WebElement element) { // прокрутка
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView();", element);
        }
    }
