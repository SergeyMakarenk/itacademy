package by.itacademy;

import by.itacademy.annotations.JsonField;
import by.itacademy.annotations.Validation;

import java.util.Objects;

public class Transport {

    @JsonField
    private String type;

    @Validation(pattern = "^[A-z]+[A-z-\\s0-9]+[A-z0-9]$")
    @JsonField
    private String model;

    private Integer price;

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

    public Transport() {
    }

    public void setPrice(final Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Тип ТС  " + type + ", модель " + model + ", цена диагностики - " + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Transport transport = (Transport) o;
        return Objects.equals(type, transport.type) && Objects.equals(model, transport.model) && Objects.equals(price, transport.price);
    }
}