package serialize.gson;

import com.google.gson.*;
import graph.Vertex;

import java.lang.reflect.Type;

public class VertexConverter implements JsonSerializer<Vertex>, JsonDeserializer<Vertex> {
    @Override
    public Vertex deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        int number = context.deserialize(object.get("stationNumber"), Integer.class);
        return new Vertex(number);
    }

    @Override
    public JsonElement serialize(Vertex vertex, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();
        result.addProperty("stationNumber", vertex.getStationNumber().toString());
        return result;
    }
}
