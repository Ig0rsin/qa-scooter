import models.OrderData;
import models.RentData;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pageobjects.HomePage;
import pageobjects.OrderPage;
import pageobjects.RentPage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLoginFlow extends TestBase {

    @ParameterizedTest
    @CsvSource({
            "Петя, Ромашкин, ул. Отрадная д. 10, Отрадное, +79998887766, 15, сутки", //ДОЛБАНАЯ ЗАПЯТАЯ!!!!!
            "Уасся, Пупкин, ул. Льва Толстого д. 16, Парк Культуры, +79990001122, 12, двое суток" // АААААААААААААААААА!!!!!
    })
    public void testOrderWithFirstButton(String name, String surname,
                                         String address, String subwayStation,
                                         String phone, String daysToAdd,
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
        homePage.clickTopOrderButton(); // верхняя

// добавлена закрывашка для кукухи в TestBase

        OrderPage orderPage = new OrderPage(this.driver);
        orderPage.fillInForm(orderdata);

        RentPage rentPage = new RentPage(this.driver);
        rentPage.fillInForm(rentData);
        rentPage.confirmOrder();

        assertTrue(rentPage.isOrderCreated(), "Заказ не был создан (нижняя кнопка)");
    }

    @ParameterizedTest
    @CsvSource({
            "Петя, Ромашкин, ул. Отрадная д. 10, Отрадное, +79998887766, 15, сутки",
            "Уасся, Пупкин, ул. Льва Толстого д. 16, Парк Культуры, +79990001122, 12, двое суток"
    })
    public void testOrderWithSecondButton(String name, String surname,
                                          String address, String subwayStation,
                                          String phone, String daysToAdd,
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
        homePage.clickSecondOrderButton(); // нижняя

        OrderPage orderPage = new OrderPage(this.driver);
        orderPage.fillInForm(orderdata);

        RentPage rentPage = new RentPage(this.driver);
        rentPage.fillInForm(rentData);
        rentPage.confirmOrder();

        assertTrue(rentPage.isOrderCreated(), "Заказ не был создан (верхняя кнопка)");
    }

    private String getDateInDaysFromNow(String daysToAdd) {
        long days = Long.parseLong(daysToAdd);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.now().plusDays(days).format(formatter);
    }

}
