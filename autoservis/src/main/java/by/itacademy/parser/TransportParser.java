package by.itacademy.parser;

import by.itacademy.transport.Transport;

import java.util.List;

public interface TransportParser {
    List<Transport> parseStringToListTransport(String content) throws TransportParserException;
}
