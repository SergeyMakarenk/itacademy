package by.itacademy;

import by.itacademy.checker.TransportChecker;
import by.itacademy.checker.TransportCheckerImpl;
import by.itacademy.parser.TransportParserFromStringImpl;
import by.itacademy.parser.ParserFromString;
import by.itacademy.reader.TransportReader;
import by.itacademy.reader.TransportReaderImpl;
import by.itacademy.sorter.*;
import by.itacademy.transport.Transport;
import by.itacademy.writer.TransportWriter;
import by.itacademy.writer.TransportWriterToJsonImpl;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public final class Autoservis {
    private static final String FILE = "transport.json";
    private static final File FILE_INVALID = Path.of( "invalid-transport.json").toFile();
    private static final File FILE_SUCCESS = Path.of( "processed-transport.json").toFile();
    private static final String SUCCESS = "success";
    private static final String INVALID = "invalid";

    public static void run() {
        System.out.println("Старт программы Автосервис");

        try {
            final ParserFromString parser = new TransportParserFromStringImpl();
            final TransportReader reader = new TransportReaderImpl(FILE, parser);

            final List<Transport> transportList = reader.readTransport();

            final TransportChecker checker = new TransportCheckerImpl();
            final Map<String, List<Transport>> mapListTransport = checker.checkTransport(transportList, SUCCESS, INVALID);

            final TransportSorter sorter = new TransportSorterImpl();
            sorter.sortReader(mapListTransport.get(SUCCESS));

            final TransportWriter writer = new TransportWriterToJsonImpl();
            writer.writeTransport(mapListTransport.get(INVALID), FILE_INVALID, INVALID);
            writer.writeTransport(mapListTransport.get(SUCCESS), FILE_SUCCESS, SUCCESS);

        } catch (final Exception e) {
            System.err.println("Ошибка работы программы" + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("Завершение программы Автосервис");
    }

    private Autoservis() {
    }
}
