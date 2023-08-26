package by.itacademy.writer;

import by.itacademy.transport.Transport;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class TransportWriterToJsonImpl implements TransportWriter {

    private final File fileInvalid;
    private final File fileSuccess;

    public TransportWriterToJsonImpl(final File fileInvalid, final File fileSuccess) {
        this.fileInvalid = fileInvalid;
        this.fileSuccess = fileSuccess;
    }

    @Override
    public void writeInvalidTransport(final List<Transport> invalidTransportList) throws WriterException {
        try (final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileInvalid, StandardCharsets.UTF_8))) {
            final JSONArray invalidTransport = new JSONArray();

            for (Transport transport : invalidTransportList) {
                final JSONObject transportJsonObject = new JSONObject();

                transportJsonObject.put("type", transport.getType());
                transportJsonObject.put("model", transport.getModel());

                invalidTransport.put(transportJsonObject);
            }
            invalidTransport.write(bufferedWriter);
        } catch (final IOException | JSONException e) {
            throw new WriterException("Cant write file " + fileInvalid.getName(), e);
        }
    }

    @Override
    public void writeSuccessTransport(final List<Transport> successTransportList) throws WriterException {
        try (final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileSuccess, StandardCharsets.UTF_8))) {
            final JSONArray successTransport = new JSONArray();

            for (Transport transport : successTransportList) {
                final JSONObject transportJsonObject = new JSONObject();

                transportJsonObject.put("type", transport.getType());
                transportJsonObject.put("model", transport.getModel());
                transportJsonObject.put("price", transport.getPrice());

                successTransport.put(transportJsonObject);
            }
            successTransport.write(bufferedWriter);
        } catch (final IOException | JSONException e) {
            throw new WriterException("Cant write file " + fileSuccess.getName(), e);
        }
    }
}
