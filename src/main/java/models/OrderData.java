package models;

public class OrderData { // приватные поля
    private final String name;
    private final String surname;
    private final String address;
    private final String subwayStation;
    private final String phone;

    public OrderData(String name, String surname, String address, String subwayStation, String phone) {

        this.name = name; // конструктор для формирования заказа
        this.surname = surname;
        this.address = address;
        this.subwayStation = subwayStation;
        this.phone = phone;
    }
// получаем значение полей извне
    public String getName() {

        return name;
    }

    public String getSurname() {

        return surname;
    }

    public String getAddress() {

        return address;
    }

    public String getSubwayStation() {

        return subwayStation;
    }

    public String getPhone() {

        return phone;
    }

}
