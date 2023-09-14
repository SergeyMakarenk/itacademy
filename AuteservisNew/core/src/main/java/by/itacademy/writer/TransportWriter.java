package by.itacademy.writer;

import by.itacademy.Transport;

import java.io.File;
import java.util.List;

public interface TransportWriter {
    void writeTransport(List<Transport> invalidTransportList, File file, String validTypeTransport) throws WriterException;
}
