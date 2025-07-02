import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertTrue;
import models.OrderData;
import models.RentData;
import PageObjects.OrderPage;
import PageObjects.RentPage;
import PageObjects.HomePage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
// сопсна, тестируем
public class TestLoginFlow extends TestBase {

    @ParameterizedTest // тесты для разных наборов данных
    @CsvSource({
            "Петя, Ромашкин, ул. Отрадная, д. 10, Отрадное, 79998887766, 1, сутки",
            "Уасся, Пупкин, ул. Льва Толстого, д. 16, Парк Культуры, 79990001122, 2, двое суток"
    })
    public void testPositiveLoginFromHeaderFlow(String name, String surname,
                                                String address, String subwayStation,
                                                String phone, int daysToAdd,
                                                String rentLengthPeriod) {
        OrderData orderdata = new OrderData(
                name,
                surname,
                address,
                subwayStation,
                phone
        );

        RentData rentData = new RentData(
                getDateInDaysFromNow(daysToAdd),
                rentLengthPeriod
        );

        HomePage homePage = new HomePage(this.driver); // открывает страницы (реально , открывает!)
        homePage.clickTopOrderButton();

        OrderPage orderPage = new OrderPage(this.driver); // заполняет данные бешеного самокатчика
        orderPage.fillInForm(orderdata);

        RentPage rentPage = new RentPage(this.driver); // заполняет дату, срок
        rentPage.fillInForm(rentData);
        rentPage.confirmOrder();

        assertTrue(rentPage.isOrderCreated(), "Заказ не был создан"); // заказ создан/не создан
    }

    @ParameterizedTest
    @CsvSource({
            "Петя, Ромашкин, ул. Отрадная, д. 10, Отрадное, 79998887766, 1, сутки",
            "Уасся, Пупкин, ул. Льва Толстого, д. 16, Парк Культуры, 79990001122, 2, двое суток"
    })
    public void testPositiveLoginFromBodyFlow(String name, String surname,
                                              String address, String subwayStation,
                                              String phone, int daysToAdd,
                                              String rentLengthPeriod) {
        OrderData orderdata = new OrderData(
                name,
                surname,
                address,
                subwayStation,
                phone
        );

        RentData rentData = new RentData(
                getDateInDaysFromNow(daysToAdd),
                rentLengthPeriod
        );

        HomePage homePage = new HomePage(this.driver);
        homePage.clickTopOrderButton();

        OrderPage orderPage = new OrderPage(this.driver);
        orderPage.fillInForm(orderdata);

        RentPage rentPage = new RentPage(this.driver);
        rentPage.fillInForm(rentData);
        rentPage.confirmOrder();

        assertTrue(rentPage.isOrderCreated(), "Заказ не был создан");
    }

    private String getDateInDaysFromNow(int daysToAdd) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.now().plusDays(daysToAdd).format(formatter);
    }
}

