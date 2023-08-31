package by.itacademy.transport;

public enum TypeTransport {
    BIKE(10),
    AUTO(20),
    BUS(30);

    private final Integer price;

    TypeTransport(final Integer price){
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

}