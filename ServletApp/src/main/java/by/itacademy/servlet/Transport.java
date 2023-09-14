package by.itacademy.servlet;

import lombok.Data;

@Data
public class Transport {
    private final String type;
    private final String model;

    @Override
    public String
    toString() {
        return  "type = " + type + ", model = " + model ;
    }
}
