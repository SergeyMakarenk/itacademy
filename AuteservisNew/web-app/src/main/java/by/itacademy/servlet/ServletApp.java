package by.itacademy.servlet;

import by.itacademy.Transport;
import by.itacademy.checker.TransportCheckerException;
import by.itacademy.reader.ReaderException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static by.itacademy.servlet.Constants.*;

public class ServletApp extends HttpServlet {

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        Map<String, List<Transport>> mapTransports = null;
        final ProcessRequest process = new ProcessRequestDoPostImpl();
        final String nameSorting = req.getParameter("sorting");
        try (final InputStreamReader inputStreamReader = new InputStreamReader(req.getInputStream(), StandardCharsets.UTF_8)) {
            try {
                mapTransports = process.convertStreamToMapTransports(inputStreamReader, nameSorting);
            } catch (final ReaderException | TransportCheckerException e) {
                throw new RuntimeException(e);
            }
        }

        if (mapTransports != null) {
            req.setAttribute("successTransport", mapTransports.get(SUCCESS_KEY));
            req.setAttribute("invalidTransport", mapTransports.get(INVALID_KEY));
            req.setAttribute("nameSorting", nameSorting);
            try {
                req.getRequestDispatcher("show-transports.jsp").forward(req, resp);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        } else {
            resp.setContentType("text/html");
            resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
            try (final PrintWriter writer = resp.getWriter()) {
                writer.println("You sent empty body");
            }
        }
    }
}
