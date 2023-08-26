package by.itacademy.writer;

import by.itacademy.transport.Transport;

import java.util.List;

public interface TransportWriter {
    void writeInvalidTransport(List<Transport> invalidTransportList) throws WriterException;

    void writeSuccessTransport(List<Transport> successTransportList) throws WriterException;
}
