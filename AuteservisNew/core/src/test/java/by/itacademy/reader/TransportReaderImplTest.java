package by.itacademy.reader;

import by.itacademy.parser.TransportParser;
import by.itacademy.parser.TransportParserException;
import by.itacademy.parser.TransportParserWithAnnotationImpl;
import by.itacademy.Transport;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

class TransportReaderImplTest {

    private static final String CONTENT = "[  {    \"type\": \"bike\",    \"model\": \"Ninja ZX-14\"  },  {    \"type\": \"auto\",    \"model\": \"Audi Q7\"  }]";
    private TransportParser parser;
    private TransportReader reader;

    @BeforeEach
    void prepare() {
        parser = Mockito.mock(TransportParserWithAnnotationImpl.class);
        InputStream inputStream = new ByteArrayInputStream(CONTENT.getBytes());
        InputStreamReader in = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        reader = new TransportReaderImpl(in, parser);
    }

    @AfterEach
    void Test() throws TransportParserException {
        Mockito.verify(parser).parseStringToListTransport(CONTENT);
        Mockito.verifyNoMoreInteractions(parser);
    }

    @Test
    void test_readTransport_success() throws TransportParserException, ReaderException {
        final List<Transport> listTransportActual = new ArrayList<>();
        listTransportActual.add(new Transport("bike", "Ninja ZX-14", 10));
        listTransportActual.add(new Transport("auto", "Audi Q7", 20));

        Mockito.when(parser.parseStringToListTransport(CONTENT)).thenReturn(listTransportActual);

        Assertions.assertEquals(reader.readTransport(), listTransportActual);
    }

    @Test
    void test_readTransport_throwsException() throws TransportParserException {
        Mockito.when(parser.parseStringToListTransport(CONTENT)).thenThrow(TransportParserException.class);

        final var readerException = Assertions.assertThrows(ReaderException.class, reader::readTransport);
        Assertions.assertEquals(readerException.getMessage(), "Can't parse content");
    }
}