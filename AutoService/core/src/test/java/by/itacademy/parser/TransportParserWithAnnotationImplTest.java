package by.itacademy.parser;

import by.itacademy.Transport;
import by.itacademy.parser.impl.JsonAnnotationTransportParserImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransportParserWithAnnotationImplTest {

    @Test
    void test_parseStringToListTransport_success() throws TransportParserException {
        //given
        final List<Transport> listTransportActual = new ArrayList<>();
        listTransportActual.add(new Transport("bike", "Ninja ZX-14", 10));
        listTransportActual.add(new Transport("auto", "Audi Q7", 20));

        final String content = " [ { \"type\": \"bike\", \"model\": \"Ninja ZX-14\"}, { \"type\": \"auto\", \"model\": \"Audi Q7\"}] ";

        final TransportParser parser = new JsonAnnotationTransportParserImpl();

        //when
        final List<Transport> listTransportExpected = parser.parseStringToListTransport(content);

        //then
        assertNotNull(listTransportExpected, "array transport is null");
        assertEquals(listTransportExpected, listTransportActual);
    }

    @Test
    void test_parseStringToListTransport_usingJsonArray_wrongKey_throwsException() {
        //given
        final String content = " [ { \"type\": \"bike\", \"modell\": \"Ninja ZX-14\"}, { \"type\": \"auto\", \"model\": \"Audi Q7\"}] ";

        final TransportParser parser = new JsonAnnotationTransportParserImpl();

        //when
        final Exception transportParserException = assertThrows(TransportParserException.class, () -> parser.parseStringToListTransport(content));

        //then
        assertNotNull(transportParserException, "transportParserException is null");
        assertEquals("Failed to parse from JSON to String", transportParserException.getMessage());
    }
}