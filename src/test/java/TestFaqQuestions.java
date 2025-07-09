import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFaqQuestions extends TestBase {

    static class QuestionAnswer {
        By questionLocator;
        String expectedAnswerText;

        QuestionAnswer(By questionLocator, String expectedAnswerText) {
            this.questionLocator = questionLocator;
            this.expectedAnswerText = expectedAnswerText;
        }
    }

    static Stream<QuestionAnswer> questionsProvider() {
        return Stream.of(
                new QuestionAnswer(
                        By.xpath("//div[@class='accordion__button' and text()='Сколько это стоит? И как оплатить?']"),
                        "Сутки — 400 рублей. Оплата курьеру — наличными или картой."
                ),
                new QuestionAnswer(
                        By.xpath("//div[@class='accordion__button' and text()='Хочу сразу несколько самокатов! Так можно?']"),
                        "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."
                ),
                new QuestionAnswer(
                        By.xpath("//div[@class='accordion__button' and text()='Как рассчитывается время аренды?']"),
                        "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру."
                ),
                new QuestionAnswer(
                        By.xpath("//div[@class='accordion__button' and text()='Можно ли заказать самокат прямо на сегодня?']"),
                        "Только начиная с завтрашнего дня. Но скоро станем расторопнее."
                ),
                new QuestionAnswer(
                        By.xpath("//div[@class='accordion__button' and text()='Можно ли продлить заказ или вернуть самокат раньше?']"),
                        "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."
                ),
                new QuestionAnswer(
                        By.xpath("//div[@class='accordion__button' and text()='Вы привозите зарядку вместе с самокатом?']"),
                        "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."
                ),
                new QuestionAnswer(
                        By.xpath("//div[@class='accordion__button' and text()='Можно ли отменить заказ?']"),
                        "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."
                ),
                new QuestionAnswer(
                        By.xpath("//div[@class='accordion__button' and text()='Я жизу за МКАДом, привезёте?']"),
                        "Да, обязательно. Всем самокатов! И Москве, и Московской области."
                )
        );
    }

    @ParameterizedTest
    @MethodSource("questionsProvider")
    public void testFaqQuestion(QuestionAnswer qa) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));

        driver.get("https://qa-scooter.praktikum-services.ru/");

        // ищем кнопку вопроса
        WebElement questionButton = wait.until(ExpectedConditions.elementToBeClickable(qa.questionLocator));

        // прокрутка
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", questionButton);

        // жждём, пока станет кликабельным
        wait.until(ExpectedConditions.elementToBeClickable(qa.questionLocator));

        questionButton.click(); // ТЫЦ

        // локатор для ответа
        String answerXpath = "//div[contains(@class,'accordion__panel')]//p[contains(text(),'" + qa.expectedAnswerText.substring(0, Math.min(50, qa.expectedAnswerText.length())) + "')]";

        // ждём ответ
        WebElement answerElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(answerXpath)));

        String actualText = answerElement.getText();
        assertTrue(actualText.contains(qa.expectedAnswerText),
                "Ожидаемый ответ не найден. Нашли: " + actualText);
    }
}