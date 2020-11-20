package services.graphServices;

import graph.Edge;
import graph.Graph;
import graph.TypeRoad;
import graph.Vertex;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraphService {

    private EdgeService edgeService = new EdgeService();

    private Iterable<Vertex> adjacent(Graph graph, Vertex vertex) {
        return graph.getAdjacencyMap().get(vertex);
    }

    public boolean addVertex(Graph graph, Vertex vertex) {
        Map<Vertex, Set<Vertex>> adjacencyMap = graph.getAdjacencyMap();
        Set<Vertex> vertices = graph.getVertices();
        vertex.setStationNumber(vertices.size());
        adjacencyMap.put(vertex, new HashSet<>());
        return vertices.add(vertex);
    }

    public Vertex getVertex(Graph graph, Integer stationNumber) {
        Set<Vertex> vertices = graph.getVertices();
        for (Vertex vertex : vertices) {
            if (vertex.getStationNumber().equals(stationNumber)) return vertex;
        }
        return null;
    }

//    private boolean isAdjacent(Graph graph, Vertex firstVertex, Vertex secondVertex, TypeRoad typeRoad) {
//        for (Vertex adjacentVertex : adjacent(graph, firstVertex)) {
//            if (hasPath(graph, firstVertex, secondVertex, typeRoad)) {
//                    return true;
//            }
//        }
//        return false;
//    }

    private boolean hasPath(Vertex firstVertex, Vertex secondVertex, TypeRoad typeRoad) {
        Map<TypeRoad, List<Edge>> roadMap = firstVertex.getRoadMap();
        List<Edge> edges = roadMap.get(typeRoad);
        for (Edge edge : edges) {
            Vertex startVertex = edge.getStartVertex();
            Vertex endVertex = edge.getEndVertex();
            return edgeService.contains(edge, startVertex, secondVertex);

        }
        return false;
    }

    public Edge getEdge(Graph graph, Vertex start, Vertex end, TypeRoad typeRoad) {
        List<Edge> edges = graph.getEdges();
        for (Edge edge : edges) {
            if (edgeService.contains(edge, start, end) && edge.getType().equals(typeRoad)) {
                return edge;
            }
        }
        return null;
    }

    public void addEdge(Graph graph, Edge edge) {
        Map<Vertex, Set<Vertex>> adjacencyMap = graph.getAdjacencyMap();
        Vertex startVertex = edge.getStartVertex();
        Vertex endVertex = edge.getEndVertex();
        if (!hasPath(startVertex, endVertex, edge.getType())) {
            adjacencyMap.get(startVertex).add(endVertex);
            adjacencyMap.get(endVertex).add(startVertex);
            Map<TypeRoad, List<Edge>> roadMap = startVertex.getRoadMap();
            List<Edge> edgeList = roadMap.get(edge.getType());
            edgeList.add(edge);
            endVertex.getRoadMap().get(edge.getType()).add(edge);
            graph.getEdges().add(edge);
        }
    }


    public Vertex getVertex(Graph graph, Vertex target) {
        Set<Vertex> vertices = graph.getVertices();
        for (Vertex current : vertices) {
            if (current.equals(target)) return current;
        }
        return null;
    }

    public void clear(Graph graph) {
        Map<Vertex, Set<Vertex>> adjacencyMap = graph.getAdjacencyMap();
        Set<Vertex> vertices = graph.getVertices();
        List<Edge> edges = graph.getEdges();
        if (adjacencyMap != null) adjacencyMap.clear();
        if (vertices != null) vertices.clear();
        if (edges != null) edges.clear();
    }
}
