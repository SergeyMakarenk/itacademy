package by.itacademy.reader;

import by.itacademy.parser.TransportParser;
import by.itacademy.parser.TransportParserException;
import by.itacademy.Transport;

import java.io.*;
import java.util.List;

public class TransportReaderImpl implements TransportReader {
    private final InputStreamReader inputStreamReader;
    private final TransportParser parser;

    public TransportReaderImpl(final InputStreamReader inputStreamReader, final TransportParser parser) {
        this.inputStreamReader = inputStreamReader;
        this.parser = parser;
    }

    @Override
    public List<Transport> readTransport() throws ReaderException {
        try (final BufferedReader reader = new BufferedReader(inputStreamReader)) {
            final String content = reader.lines()
                    .reduce("", String::concat);

            return parser.parseStringToListTransport(content);
        } catch (final IOException e) {
            throw new ReaderException("Can't read content ", e);
        } catch (final TransportParserException e) {
            throw new ReaderException("Can't parse content", e);
        }
    }
    }
