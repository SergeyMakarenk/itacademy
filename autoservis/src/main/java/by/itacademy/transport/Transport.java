package by.itacademy.transport;

import java.util.Objects;

public class Transport {
    private final String type;
    private final String model;
    private final Integer price;

    public Integer getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public String getModel() {
        return model;
    }

    public Transport(final String type, final String model, final Integer price) {
        this.type = type;
        this.model = model;
        this.price = price;
    }

    @Override
    public String toString() {
        return "��� ��:  " + type + ", ������: " + model+ ", ��������� ����������� - " + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transport transport = (Transport) o;
        return Objects.equals(type, transport.type) && Objects.equals(model, transport.model) && Objects.equals(price, transport.price);
    }
}