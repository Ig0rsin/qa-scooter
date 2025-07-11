package pageobjects;
// локаторы Вопросов о влажном
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FaqQuestionPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public FaqQuestionPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    // локатора вопроса класс + текст
    public By getQuestionLocatorByText(String questionText) {
        return By.xpath("//div[@class='accordion__button' and text()='" + questionText + "']");
    }

    // прокрутка
    public void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    // найти вопрос - ТЫЦ
    public void clickQuestion(By questionLocator) {
        WebElement questionButton = wait.until(ExpectedConditions.elementToBeClickable(questionLocator));
        scrollIntoView(questionButton);
        wait.until(ExpectedConditions.elementToBeClickable(questionLocator));
        questionButton.click();
    }

    // найти ответ - ТЫЦ
    public WebElement getAnswerElement(String answerTextSnippet) {
        String answerXpath = "//div[contains(@class,'accordion__panel')]//p[contains(text(),'" + answerTextSnippet + "')]";
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(answerXpath)));
    }
}
