package models;

public class RentData {
    private final String rentStartDate; // дата начала аренды
    private final String rentLengthPeriod; // срок аренды

    public RentData(String rentStartDate, String rentLengthPeriod) {
        this.rentStartDate = rentStartDate;
        this.rentLengthPeriod = rentLengthPeriod;
    }

    public String getRentStartDate() { // получаем данные извне
        return rentStartDate;
    }

    public String getRentLengthPeriod() {
        return rentLengthPeriod;
    }
}
