package by.itacademy.checker;

import by.itacademy.Transport;
import by.itacademy.checker.impl.TransportCheckerWithAnnotationImp;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransportCheckerWithAnnotationImpTest {
    @Test
    void test_checkTransport_success() throws TransportCheckerException {
        //given
        final List<Transport> allTransportList = new ArrayList<>();
        final ArrayList<Transport> successTransportList = new ArrayList<>();
        final ArrayList<Transport> invalidTransportList = new ArrayList<>();

        final Transport successTransport = new Transport("bus", "Transporter T5", 30);
        final Transport invalidTransport = new Transport("bike", "Ninja **", 10);

        allTransportList.add(successTransport);
        allTransportList.add(invalidTransport);

        successTransportList.add(successTransport);
        invalidTransportList.add(invalidTransport);

        final TransportChecker checker = new TransportCheckerWithAnnotationImp();

        //when
        final var transportContainer = checker.checkTransport(allTransportList);

        //then
        assertNotNull(transportContainer, "transportContainer is null");
        assertEquals(transportContainer.getSuccessTransport(), successTransportList, "array success transport is empty");
        assertEquals(transportContainer.getInvalidTransport(), invalidTransportList, "array invalid transport is empty");
    }
}