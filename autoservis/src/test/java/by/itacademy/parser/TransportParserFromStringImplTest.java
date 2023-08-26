package by.itacademy.parser;

import by.itacademy.transport.Transport;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransportParserFromStringImplTest {

    @Test
    void test_parseStringToListTransport_success() throws TransportParserException {
        final List<Transport> listTransportActual = new ArrayList<>();
        listTransportActual.add(new Transport("bike", "Ninja ZX-14", 10));
        listTransportActual.add(new Transport("auto", "Audi Q7", 20));
        final String content = " [ { \"type\": \"bike\", \"model\": \"Ninja ZX-14\"}, { \"type\": \"auto\", \"model\": \"Audi Q7\"}] ";
        final ParserFromString parser = new TransportParserFromStringImpl();
        final List<Transport> listTransportExpected = parser.parseStringToListTransport(content);
        assertNotNull(listTransportExpected, "array transport is null");
        assertEquals(listTransportExpected, listTransportActual);
    }

    @Test
    void test_parseStringToListTransport_usingJsonArray_wrongKey_throwsException() {
        final String content = " [ { \"type\": \"bike\", \"modell\": \"Ninja ZX-14\"}, { \"type\": \"auto\", \"model\": \"Audi Q7\"}] ";
        final ParserFromString parser = new TransportParserFromStringImpl();
        final Exception transportParserException = assertThrows(TransportParserException.class, () -> parser.parseStringToListTransport(content));
        assertNotNull(transportParserException, "transportParserException is null");
        assertEquals("Failed to parse from JSON to String", transportParserException.getMessage());
    }
}