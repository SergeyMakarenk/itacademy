package by.itacademy.parser;

import by.itacademy.transport.Transport;
import by.itacademy.transport.TypeTransport;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TransportFromParserImpl implements TransportFromParser {
    @Override
    public List<Transport> parseStringToListTransport(final String content) throws TransportParserException {
        try {
            final List<Transport> listTransport = new ArrayList<>();
            final JSONArray jsonListTransport = new JSONArray(content);

            for (Object transportInfoJson : jsonListTransport) {
                final JSONObject transportJsonObject = (JSONObject) transportInfoJson;

                final String type = transportJsonObject.getString("type");
                final String model = transportJsonObject.getString("model");
                final TypeTransport typeTransport = TypeTransport.valueOf(type.toUpperCase());
                final Integer price = typeTransport.getPrice();

                listTransport.add(new Transport(type, model, price));
            }
            return listTransport;
        } catch (final JSONException ex) {
            throw new TransportParserException("Failed to parse from JSON to String", ex);
        }
    }
}
