package by.itacademy.checker;

import by.itacademy.transport.Transport;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransportCheckerImplTest {

    @Test
    void test_checkTransport_success(){
        final List<Transport> allTransportList = new ArrayList<>();
        final ArrayList<Transport> successTransportList = new ArrayList<>();
        final ArrayList<Transport> invalidTransportList = new ArrayList<>();

        final TransportChecker checker = new TransportCheckerImpl();

        final Transport successTransport = new Transport("bus", "Transporter T5", 30);
        final Transport invalidTransport = new Transport("bike", "Ninja **", 10);

        allTransportList.add(successTransport);
        allTransportList.add(invalidTransport);

        successTransportList.add(successTransport);
        invalidTransportList.add(invalidTransport);

        final var mapTransport = checker.checkTransport(allTransportList, "success", "invalid");

        assertNotNull(mapTransport, "mapTransport is null");
        assertEquals(mapTransport.get("success"), successTransportList, "array success transport is empty");
        assertEquals(mapTransport.get("invalid"), invalidTransportList, "array invalid transport is empty");
    }
}