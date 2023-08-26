package by.itacademy.parser;

import by.itacademy.transport.Transport;
import org.json.JSONArray;

import java.util.List;

public interface ParserFromString {
    List<Transport> parseStringToListTransport(String content) throws TransportParserException;
}
