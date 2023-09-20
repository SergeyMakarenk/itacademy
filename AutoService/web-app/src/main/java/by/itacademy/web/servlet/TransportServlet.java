package by.itacademy.web.servlet;

import by.itacademy.checker.TransportCheckerException;
import by.itacademy.reader.ReaderException;
import by.itacademy.transport.TransportContainer;
import by.itacademy.web.service.impl.ProcessBodyRequestImpl;
import by.itacademy.web.service.ProcessBodyRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import static by.itacademy.web.util.Constants.*;

public class TransportServlet extends HttpServlet {

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        final TransportContainer transportContainer;
        final ProcessBodyRequest process = new ProcessBodyRequestImpl();
        final String nameSorting = req.getParameter("sorting");

        try (final InputStreamReader inputStreamReader = new InputStreamReader(req.getInputStream(), DEFAULT_CHARSET)) {
            try {
                transportContainer = process.convertStreamToMapTransports(inputStreamReader, nameSorting);
            } catch (final ReaderException | TransportCheckerException e) {
                throw new IOException("Failed to process content", e);
            }
        }

        if (transportContainer != null) {
            req.setAttribute("successTransport", transportContainer.getSuccessTransport());
            req.setAttribute("invalidTransport", transportContainer.getInvalidTransport());
            req.setAttribute("nameSorting", nameSorting);

            try {
                req.getRequestDispatcher("ShowTransports.jsp").forward(req, resp);
            } catch (ServletException e) {
                throw new IOException("Failed to show transport", e);
            }
        } else {
            resp.setContentType("text/html");
            resp.setCharacterEncoding(DEFAULT_CHARSET.name());

            try (final PrintWriter writer = resp.getWriter()) {
                writer.println("You sent empty body");
            }
        }
    }
}
