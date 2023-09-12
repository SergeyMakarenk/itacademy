package by.itacademy;

import by.itacademy.checker.TransportChecker;
import by.itacademy.checker.TransportCheckerWithAnnotationImp;
import by.itacademy.parser.TransportParserWithAnnotationImpl;
import by.itacademy.parser.TransportParser;
import by.itacademy.reader.TransportReader;
import by.itacademy.reader.TransportReaderImpl;
import by.itacademy.sorter.*;
import by.itacademy.transport.Transport;
import by.itacademy.writer.TransportWriter;
import by.itacademy.writer.TransportWriterToJsonWithAnnotationImpl;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public final class Autoservis {
    private static final String FILE = "transport.json";
    private static final File FILE_INVALID = Path.of("invalid-transport.json").toFile();
    private static final File FILE_SUCCESS = Path.of("processed-transport.json").toFile();
    private static final String SUCCESS_KEY = "success";
    private static final String INVALID_KEY = "invalid";

    public static void run() {
        System.out.println("Старт программы AutoservisApplication");

        try {
            final TransportParser parser = new TransportParserWithAnnotationImpl();
            final TransportReader reader = new TransportReaderImpl(FILE, parser);

            final List<Transport> transportList = reader.readTransport();

            final TransportChecker checker = new TransportCheckerWithAnnotationImp();
            final Map<String, List<Transport>> mapListTransport = checker.checkTransport(transportList, SUCCESS_KEY, INVALID_KEY);

            final TransportSorter sorter = new TransportSorterImpl();
            sorter.sortReader(mapListTransport.get(SUCCESS_KEY));

            final TransportWriter writer = new TransportWriterToJsonWithAnnotationImpl();
            writer.writeTransport(mapListTransport.get(INVALID_KEY), FILE_INVALID, INVALID_KEY);
            writer.writeTransport(mapListTransport.get(SUCCESS_KEY), FILE_SUCCESS, SUCCESS_KEY);

        } catch (final Exception e) {
            System.err.println("ошибка выыполнения программы" + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("окончание работы программы");
    }

    private Autoservis() {
    }
}
