package by.itacademy.web.service.impl;

import by.itacademy.Transport;
import by.itacademy.checker.TransportChecker;
import by.itacademy.checker.TransportCheckerException;
import by.itacademy.checker.impl.TransportCheckerWithAnnotationImp;
import by.itacademy.parser.TransportParser;
import by.itacademy.parser.impl.JsonAnnotationTransportParserImpl;
import by.itacademy.reader.ReaderException;
import by.itacademy.reader.TransportReader;
import by.itacademy.reader.impl.TransportReaderImpl;
import by.itacademy.transport.TransportContainer;
import by.itacademy.web.service.ProcessBodyRequest;

import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;

public class ProcessBodyRequestImpl implements ProcessBodyRequest {

    @Override
    public TransportContainer convertStreamToMapTransports(
            final InputStreamReader inputStreamReader, final String nameSorting
    ) throws ReaderException, TransportCheckerException {

        final TransportParser parser = new JsonAnnotationTransportParserImpl();
        final TransportChecker checker = new TransportCheckerWithAnnotationImp();
        final TransportReader reader = new TransportReaderImpl(inputStreamReader, parser);

        final List<Transport> transports = reader.readTransport();
        final TransportContainer transportContainer = checker.checkTransport(transports);
        sortTransports(transportContainer.getSuccessTransport(), nameSorting);

        return transportContainer;
    }

    private void sortTransports(final List<Transport> transports, final String nameSorting) {
        switch (nameSorting) {
            case "type" -> transports.sort(Comparator.comparing(Transport::getType));
            case "model" -> transports.sort(Comparator.comparing(Transport::getModel));
            case "price" -> transports.sort(Comparator.comparing(Transport::getPrice));
            default -> {
                return;
            }
        }
    }
}
