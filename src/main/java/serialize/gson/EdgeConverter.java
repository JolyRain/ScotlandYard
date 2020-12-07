package serialize.gson;

import com.google.gson.*;
import graph.Edge;
import graph.TypeRoad;
import graph.Vertex;
import services.graphServices.VertexService;

import java.lang.reflect.Type;

public class EdgeConverter implements JsonDeserializer<Edge> {
    private VertexService vertexService = new VertexService();

    @Override
    public Edge deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsObject = jsonElement.getAsJsonObject();
        Vertex start = context.deserialize(jsObject.get("startVertex"), Vertex.class);
        Vertex end = context.deserialize(jsObject.get("endVertex"), Vertex.class);
        TypeRoad typeRoad = context.deserialize(jsObject.get("type"), TypeRoad.class);
        return new Edge(start, end, typeRoad);
    }
}
