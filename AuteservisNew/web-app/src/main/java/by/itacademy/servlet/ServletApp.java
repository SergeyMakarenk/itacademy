package by.itacademy.servlet;

import by.itacademy.Transport;
import by.itacademy.checker.TransportChecker;
import by.itacademy.checker.TransportCheckerException;
import by.itacademy.checker.TransportCheckerWithAnnotationImp;
import by.itacademy.parser.TransportParser;
import by.itacademy.parser.TransportParserException;
import by.itacademy.parser.TransportParserWithAnnotationImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class ServletApp extends HttpServlet {
    private List<Transport> transports = new ArrayList<>();
    private Map<String, List<Transport>> mapTransports;
    private final String successKey = "success";
    private final String invalidKey = "invalid";

    private List<Transport> getTransports() {
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
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try (final PrintWriter writer = resp.getWriter()) {
            transportsValue.ifPresent(writer::println);
        }
    }


    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        String requestBody = null;

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8))) {
            requestBody = reader.lines()
                    .reduce("", String::concat);
        }
        final String nameSorting = req.getParameter("sorting");

        mapTransports = convertContentBodyToMapTransports(requestBody, nameSorting);

        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (final PrintWriter writer = resp.getWriter()) {
            writer.println("Транспорт, пригодный к диагностике, отсортированный по " + nameSorting);
            printTransportsInTable(writer, mapTransports.get(successKey));
            writer.println("Транспорт, непригодный к диагностике");
            printTransportsInTable(writer, mapTransports.get(invalidKey));
        }
    }

    private Map<String, List<Transport>> convertContentBodyToMapTransports(final String content, final String nameSorting) {
        final TransportParser parser = new TransportParserWithAnnotationImpl();
        final TransportChecker checker = new TransportCheckerWithAnnotationImp();
        try {
            transports = parser.parseStringToListTransport(content);
            mapTransports = checker.checkTransport(transports, successKey, invalidKey);
            sortTransports(mapTransports.get(successKey), nameSorting);
            return mapTransports;

        } catch (final TransportCheckerException | TransportParserException e) {
            throw new RuntimeException(e);
        }
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

    private void printTransportsInTable(PrintWriter writer, List<Transport> transports){
        writer.println("<table border=1>");
        writer.println("<tr>");
        writer.println("<th> тип </th>");
        writer.println("<th> модель </th>");
        writer.println("<th> цена </th>");
        writer.println("</tr>");
        for (final Transport transport : transports) {
            writer.println("<tr>");
            writer.println("<td>" + transport.getType() + "</td>");
            writer.println("<td>" + transport.getModel() + "</td>");
            writer.println("<td>" + transport.getPrice().toString() + "</td>");
            writer.println("</tr>");
        }
        writer.println("</table>");
    }
}
