package by.itacademy.writer;

import by.itacademy.transport.Transport;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class TransportWriterToJsonImpl implements TransportWriter {


    @Override
    public void writeTransport(final List<Transport> transportList, final File file, String validTypeTransport) throws WriterException {
        try (final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
            final JSONArray invalidTransport = new JSONArray();

            for (Transport transport : transportList) {
                final JSONObject transportJsonObject = new JSONObject();

                transportJsonObject.put("type", transport.getType());
                transportJsonObject.put("model", transport.getModel());

                if (validTypeTransport.equals("success")) {
                    transportJsonObject.put("price", transport.getPrice());
                }

                invalidTransport.put(transportJsonObject);
            }
            invalidTransport.write(bufferedWriter);
        } catch (final IOException | JSONException e) {
            throw new WriterException("Cant write file " + file.getName(), e);
        }
    }
}
