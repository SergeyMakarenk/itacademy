package by.itacademy.servlet;

import by.itacademy.parser.TransportParser;
import by.itacademy.parser.TransportParserException;
import by.itacademy.parser.TransportParserWithAnnotationImpl;
import by.itacademy.reader.TransportReader;
import by.itacademy.reader.TransportReaderImpl;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServletApp extends HttpServlet {
    private final List<Transport> transports = new ArrayList<>(3);

    private List<Transport> getTransports(){
        transports.add(new Transport("bike", "honda", 10));
        transports.add(new Transport("auto", "bmw", 20));
        transports.add(new Transport("bus", "iveco", 30));

        return transports;
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        final Optional<String> transportsValue = getTransports().stream()
                .map(Transport::toString)
                .reduce(String::concat);

        resp.setContentType("text/html");

        try (final PrintWriter writer = resp.getWriter()) {
            transportsValue.ifPresent(writer::println);
        }
    }

    @SneakyThrows
    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        String requestBody = null;

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8))) {
            requestBody = reader.lines()
                    .reduce("", String::concat);
        }

        final TransportParser parser = new TransportParserWithAnnotationImpl();
        final List<by.itacademy.transport.Transport> transports = parser.parseStringToListTransport(requestBody);

        resp.setContentType("text/html");

        try (final PrintWriter writer = resp.getWriter()) {
            writer.println(transports);
        }
    }
}
