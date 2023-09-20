package by.itacademy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransportTest {
    private final String expectedType = "bike";
    private final String expectedModel = "honda";
    private final Integer expectedPrice = 10;
    private final Transport transport = new Transport(expectedType, expectedModel, expectedPrice);

    @Test
    void testGetPrice_success() {
        //when
        final Integer actualPrice = transport.getPrice();

        //then
        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    void testGetType_success() {
        //when
        final String actualType = transport.getType();

        //then
        assertEquals(expectedType, actualType);
    }

    @Test
    void testGetModel_success() {
        //when
        final String actualModel = transport.getModel();

        //then
        assertEquals(expectedModel, actualModel);
    }
}