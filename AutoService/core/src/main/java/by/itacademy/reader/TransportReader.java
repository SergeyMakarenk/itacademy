package by.itacademy.reader;

import by.itacademy.Transport;

import java.util.List;

public interface TransportReader {
    List<Transport> readTransport() throws ReaderException;
}
