package by.itacademy.writer;

import by.itacademy.annotations.JsonTransportConvertMarker;
import by.itacademy.annotations.WriteFieldMarker;
import by.itacademy.transport.Transport;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class TransportWriterToJsonWithAnnotationImpl implements TransportWriter {


    @Override
    public void writeTransport(final List<Transport> transportList, final File file, final String key) throws WriterException {
        try (final BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, StandardCharsets.UTF_8))) {
            final JSONArray jsonTransports = new JSONArray();

            for (Transport transport : transportList) {
                final JSONObject transportJsonObject = convertTransportToJson(transport, key);

                jsonTransports.put(transportJsonObject);
            }
            jsonTransports.write(bufferedWriter);
        } catch (final IOException | JSONException e) {
            throw new WriterException("Cant write file " + file.getName(), e);
        }
    }

    private JSONObject convertTransportToJson(final Transport transport, final String key) throws WriterException {
        final JSONObject jsonObject = new JSONObject();
        try {
            for (final Field field : transport.getClass().getDeclaredFields()) {
                for (final Annotation annotation : field.getDeclaredAnnotations()) {

                    if (annotation instanceof JsonTransportConvertMarker) {
                        field.setAccessible(true);

                        final String fieldName = field.getName();
                        final Object fieldValue = field.get(transport);
                        jsonObject.put(fieldName, fieldValue);
                    }
                    if (annotation instanceof WriteFieldMarker) {
                        field.setAccessible(true);

                        if (key.equals("success")) {

                            final String fieldName = field.getName();
                            final Object fieldValue = field.get(transport);
                            jsonObject.put(fieldName, fieldValue);
                        }
                    }
                }
            }
        } catch (final IllegalAccessException e) {
            throw new WriterException("Can't create JSONObject from Transport", e);
        }
        return jsonObject;
    }
}
