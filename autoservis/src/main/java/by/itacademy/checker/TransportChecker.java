package by.itacademy.checker;

import by.itacademy.transport.Transport;

import java.util.List;
import java.util.Map;

public interface TransportChecker {
    Map<String, List<Transport>> checkTransport(List<Transport> listTransport, String success, String invalid);
}
