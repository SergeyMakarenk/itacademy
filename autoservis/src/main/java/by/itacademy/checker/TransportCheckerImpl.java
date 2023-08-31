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
    public Map<String, List<Transport>> checkTransport(final List<Transport> listTransport, final String successKey, final String invalidKey) {
        final List<Transport> invalidTransportList = new ArrayList<>();
        final List<Transport> successTransportList = new ArrayList<>();
        final Map<String, List<Transport>> mapListTransport = new HashMap<>(2);

        for (final Transport transport : listTransport) {
            if (PATTERN.matcher(transport.getModel()).matches()) {
                successTransportList.add(transport);
            } else {
                invalidTransportList.add(transport);
            }
        }

        mapListTransport.put(successKey, successTransportList);
        mapListTransport.put(invalidKey, invalidTransportList);

        return mapListTransport;
    }
}
