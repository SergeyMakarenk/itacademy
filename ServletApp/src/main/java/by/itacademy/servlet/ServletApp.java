package by.itacademy.servlet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    private List<Transport> getTransports() {
        transports.add(new Transport("bike", "honda"));
        transports.add(new Transport("auto", "bmw"));
        transports.add(new Transport("bus", "iveco"));

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


    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        String requestBody = null;

        try (final BufferedReader reader = new BufferedReader(new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8))) {
            requestBody = reader.lines()
                    .reduce("", String::concat);
        }

        resp.setContentType("text/html");

        try (final PrintWriter writer = resp.getWriter()) {
            writer.println(parseStringToListTransport(requestBody));
        }
    }

    private List<Transport> parseStringToListTransport(final String content) throws JSONException {
        final List<Transport> listTransport = new ArrayList<>();
        final JSONArray jsonListTransport = new JSONArray(content);

        for (final Object transportInfoJson : jsonListTransport) {
            final JSONObject transportJsonObject = (JSONObject) transportInfoJson;
            final Transport transport = new Transport(transportJsonObject.getString("type"),
                    transportJsonObject.getString("model"));

            listTransport.add(transport);
        }
        return listTransport;
    }
}
