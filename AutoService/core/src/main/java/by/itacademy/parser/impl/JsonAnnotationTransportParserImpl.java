package by.itacademy.parser.impl;

import by.itacademy.Transport;
import by.itacademy.TypeTransport;
import by.itacademy.annotations.JsonField;

import by.itacademy.parser.TransportParser;
import by.itacademy.parser.TransportParserException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class JsonAnnotationTransportParserImpl implements TransportParser {
    @Override
    public List<Transport> parseStringToListTransport(final String content) throws TransportParserException {
        try {
            final List<Transport> listTransport = new ArrayList<>();
            final JSONArray jsonListTransport = new JSONArray(content);

            for (final Object transportInfoJson : jsonListTransport) {
                final JSONObject transportJsonObject = (JSONObject) transportInfoJson;

                final Transport transport = convertJsonToTransport(transportJsonObject, Transport.class);

                final TypeTransport typeTransport = TypeTransport.valueOf(transport.getType().toUpperCase());
                transport.setPrice(typeTransport.getPrice());

                listTransport.add(transport);
            }
            return listTransport;
        } catch (final JSONException ex) {
            throw new TransportParserException("Failed to parse from JSON to String", ex);
        }
    }

    private <T> T convertJsonToTransport(final JSONObject jsonObject, final Class<T> clazz) throws TransportParserException {
        try {
            final T result = clazz.getDeclaredConstructor().newInstance();

            for (final Field field : Transport.class.getDeclaredFields()) {
                for (final Annotation annotation : field.getDeclaredAnnotations()) {

                    if (!(annotation instanceof JsonField)) {
                        continue;
                    }
                    field.setAccessible(true);

                    final Object fieldValue = jsonObject.get(field.getName());
                    field.set(result, fieldValue);
                }
            }
            return result;
        } catch (final InvocationTargetException | NoSuchMethodException | InstantiationException |
                       IllegalAccessException e) {
            throw new TransportParserException("Can't convert JSONObject to Transport", e);
        }
    }
}
