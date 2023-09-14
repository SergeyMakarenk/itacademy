package by.itacademy.reader;

import by.itacademy.parser.TransportParser;
import by.itacademy.parser.TransportParserException;
import by.itacademy.Transport;

import java.io.*;
import java.util.List;

public class TransportReaderImpl implements TransportReader {
    private final String fileTransport;
    private final TransportParser parser;

    public TransportReaderImpl(final String fileTransport, final TransportParser parser) {
        this.fileTransport = fileTransport;
        this.parser = parser;
    }

    @Override
    public List<Transport> readTransport() throws ReaderException {
        try (final BufferedReader reader = getReader()) {
            final String content = reader.lines()
                    .reduce(String::concat)
                    .orElse(null);

            return parser.parseStringToListTransport(content);
        } catch (final FileNotFoundException e) {
            throw new ReaderException("File not exist " + fileTransport, e);
        } catch (final IOException e) {
            throw new ReaderException("Can't read file " + fileTransport, e);
        } catch (final TransportParserException e) {
            throw new ReaderException("Can't parse content", e);
        }
    }
    private BufferedReader getReader() throws ReaderException {
        final var in = getClass().getClassLoader().getResourceAsStream(fileTransport);

        if (in != null) {
            return new BufferedReader(new InputStreamReader(in));
        }
        throw new ReaderException("Resource from file is null");
    }
}
