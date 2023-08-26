package by.itacademy.checker;

import by.itacademy.transport.Transport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class TransportCheckerImpl implements TransportChecker {
    private static final Pattern PATTERN = Pattern.compile("^[A-z]+[A-z-\\s0-9]+[A-z0-9]$");

    @Override
    public Map<String, List<Transport>> checkTransport(List<Transport> listTransport, String success, String invalid) {
        final List<Transport> invalidTransportList = new ArrayList<>();
        final List<Transport> successTransportList = new ArrayList<>();
        final Map<String, List<Transport>> mapListTransport = new HashMap<>();

        for (Transport transport : listTransport) {
            if (PATTERN.matcher(transport.getModel()).matches()) {
                successTransportList.add(transport);
            } else {
                invalidTransportList.add(transport);
            }
        }

        mapListTransport.put(success, successTransportList);
        mapListTransport.put(invalid, invalidTransportList);

        return mapListTransport;
    }
}
