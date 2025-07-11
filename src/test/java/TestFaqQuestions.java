import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobjects.FaqQuestionPage;
import pageobjects.HomePage;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFaqQuestions extends TestBase {

    static class QuestionAnswer {
        String questionText; // Текст вопроса
        String expectedAnswerText; // Ожидаемый ответ

        QuestionAnswer(String questionText, String expectedAnswerText) {
            this.questionText = questionText;
            this.expectedAnswerText = expectedAnswerText;
        }
    }

    static Stream<QuestionAnswer> questionsProvider() {
        return Stream.of(
                new QuestionAnswer(
                        "Сколько это стоит? И как оплатить?",
                        "Сутки — 400 рублей. Оплата курьеру — наличными или картой."
                ),
                new QuestionAnswer(
                        "Хочу сразу несколько самокатов! Так можно?",
                        "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."
                ),
                new QuestionAnswer(
                        "Как рассчитывается время аренды?",
                        "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру."
                ),
                new QuestionAnswer(
                        "Можно ли заказать самокат прямо на сегодня?",
                        "Только начиная с завтрашнего дня. Но скоро станем расторопнее."
                ),
                new QuestionAnswer(
                        "Можно ли продлить заказ или вернуть самокат раньше?",
                        "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."
                ),
                new QuestionAnswer(
                        "Вы привозите зарядку вместе с самокатом?",
                        "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."
                ),
                new QuestionAnswer(
                        "Можно ли отменить заказ?",
                        "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."
                ),
                new QuestionAnswer(
                        "Я жизу за МКАДом, привезёте?",
                        "Да, обязательно. Всем самокатов! И Москве, и Московской области."
                )
        );
    }

    @ParameterizedTest
    @MethodSource("questionsProvider")
    public void testFaqQuestion(QuestionAnswer qa) {
        WebDriver driver = getWebDriver();
        FaqQuestionPage faqPage = new FaqQuestionPage(driver);

        driver.get(HomePage.MAIN_URL);


        By questionLocator = faqPage.getQuestionLocatorByText(qa.questionText);


        faqPage.clickQuestion(questionLocator); // ТЫЦ по вопросу

        // ответ по части текста
        String snippet = qa.expectedAnswerText.length() > 50 ? qa.expectedAnswerText.substring(0, 50) : qa.expectedAnswerText;

        WebElement answerElement = faqPage.getAnswerElement(snippet);
        String actualText = answerElement.getText();

        assertTrue(actualText.contains(qa.expectedAnswerText),
                "Ожидаемый ответ не найден. Нашли: " + actualText);
    }

}