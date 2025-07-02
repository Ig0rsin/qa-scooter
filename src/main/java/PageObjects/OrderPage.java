package PageObjects;
                                        // реализация РОМ
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import models.OrderData;

public class OrderPage {
    private final WebDriver driver;
                                // локаторы элементов формы заказа самоката
    private final By nameInputSelector = By.xpath(".//input[@placeholder='* Имя']");
    private final By surnameInputSelector = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressInputSelector = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By subwayInputSelector = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By phoneInputSelector = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButtonSelector = By.xpath(".//*[starts-with(@class,'Order_Content')]//button");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }
// заполнение формы заказа
        public void setName(String name) {
            WebElement element = driver.findElement(this.nameInputSelector);
            element.sendKeys(name);
        }

        public void setSurname(String surname) {
            WebElement element = driver.findElement(this.surnameInputSelector);
            element.sendKeys(surname);
        }

        public void setAddress(String address) {
            WebElement element = driver.findElement(this.addressInputSelector);
            element.sendKeys(address);
        }

        public void setSubway(String subway) {
            WebElement element = driver.findElement(this.subwayInputSelector);
            element.sendKeys(subway);
            element.sendKeys(Keys.ARROW_DOWN);
            element.sendKeys(Keys.ENTER);
        }

        public void setPhone(String phone) {
            WebElement element = driver.findElement(this.phoneInputSelector);
            element.sendKeys(phone);
        }

        public void clickNextButton() {
            WebElement element = this.driver.findElement(this.nextButtonSelector);
            element.click();
        }
// ТЫЦ по кнопке "Далее"
        public void fillInForm(OrderData data) {
            this.setName(data.getName());
            this.setSurname(data.getSurname());
            this.setAddress(data.getAddress());
            this.setSubway(data.getSubwayStation());
            this.setPhone(data.getPhone());

            this.clickNextButton();
        }
    }
