package by.itacademy.checker;

import by.itacademy.Transport;
import by.itacademy.transport.TransportContainer;

import java.util.List;

public interface TransportChecker {
    TransportContainer checkTransport(List<Transport> listTransport) throws TransportCheckerException;
}
