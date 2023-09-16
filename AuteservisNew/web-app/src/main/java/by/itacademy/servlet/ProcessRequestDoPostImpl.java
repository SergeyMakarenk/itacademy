package by.itacademy.servlet;

import by.itacademy.Transport;
import by.itacademy.checker.TransportChecker;
import by.itacademy.checker.TransportCheckerException;
import by.itacademy.checker.TransportCheckerWithAnnotationImp;
import by.itacademy.parser.TransportParser;
import by.itacademy.parser.TransportParserWithAnnotationImpl;
import by.itacademy.reader.ReaderException;
import by.itacademy.reader.TransportReader;
import by.itacademy.reader.TransportReaderImpl;

import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static by.itacademy.servlet.Constants.*;

public class ProcessRequestDoPostImpl implements ProcessRequest {

    @Override
    public Map<String, List<Transport>> convertStreamToMapTransports(
            final InputStreamReader inputStreamReader, final String nameSorting
    ) throws ReaderException, TransportCheckerException {

        final TransportParser parser = new TransportParserWithAnnotationImpl();
        final TransportChecker checker = new TransportCheckerWithAnnotationImp();
        final TransportReader reader = new TransportReaderImpl(inputStreamReader, parser);

        final List<Transport> transports = reader.readTransport();
        final Map<String, List<Transport>> mapTransports = checker.checkTransport(transports, SUCCESS_KEY, INVALID_KEY);
        sortTransports(mapTransports.get(SUCCESS_KEY), nameSorting);

        return mapTransports;
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
