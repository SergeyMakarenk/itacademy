package by.itacademy.transport;

import by.itacademy.Transport;

import java.util.List;

public class TransportContainer {
    private final List<Transport> successTransport;
    private final List<Transport> invalidTransport;

    public TransportContainer(final List<Transport> successTransport, final List<Transport> invalidTransport) {
        this.successTransport = successTransport;
        this.invalidTransport = invalidTransport;
    }

    public List<Transport> getSuccessTransport() {
        return successTransport;
    }

    public List<Transport> getInvalidTransport() {
        return invalidTransport;
    }
}
