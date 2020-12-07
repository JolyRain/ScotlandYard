package serialize.gson;

import com.google.gson.*;
import graph.Edge;
import graph.Graph;
import graph.TypeRoad;
import graph.Vertex;
import services.graphServices.GraphService;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class GraphConverter implements JsonDeserializer<Graph> {
    private EdgeConverter edgeConverter = new EdgeConverter();
    private VertexConverter vertexConverter = new VertexConverter();
    private GraphService graphService = new GraphService();

    @Override
    public Graph deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        Graph graph = new Graph();
        Set<Vertex> vertices = new HashSet<>();

        JsonArray verticesArray = object.get("vertices").getAsJsonArray();
        for (JsonElement vertexElement : verticesArray) {
            Vertex vertex = vertexConverter.deserialize(vertexElement, Vertex.class, context);
            vertices.add(vertex);
        }
        graph.setVertices(vertices);

        JsonArray edgesArray = object.get("edges").getAsJsonArray();
        for (JsonElement edgesElement : edgesArray) {
            JsonObject edgeObject = edgesElement.getAsJsonObject();
            Vertex start = vertexConverter.deserialize(edgeObject.get("startVertex"), Vertex.class, context);
            Vertex end = vertexConverter.deserialize(edgeObject.get("endVertex"), Vertex.class, context);
            TypeRoad typeRoad = context.deserialize(edgeObject.get("type"), TypeRoad.class);
            Integer startNumber = start.getStationNumber();
            Integer endNumber = end.getStationNumber();
            Edge edge = new Edge(graphService.getVertex(graph, startNumber), graphService.getVertex(graph, endNumber), typeRoad);
            graphService.addVertex(graph, start);
            graphService.addVertex(graph, end);
            graphService.addEdge(graph, edge);
        }
        return graph;
    }
}
