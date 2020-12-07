package services.graphServices;

import game.ScotlandYardGame;
import game.Ticket;
import game.TypeTicket;
import graph.Edge;
import graph.TypeRoad;
import graph.Vertex;

import java.util.List;
import java.util.Map;

public class VertexService {

    public void addEdge(Vertex vertex, Edge edge) {
        Map<TypeRoad, List<Edge>> roadMap = vertex.getRoadMap();
        List<Edge> edges = roadMap.get(edge.getType());
        edges.add(edge);
        roadMap.put(edge.getType(), edges);
    }

}
