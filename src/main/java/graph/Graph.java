package graph;

import java.util.*;

public class Graph {

    private Map<Vertex, Set<Vertex>> adjacentVerticesMap = new HashMap<>();
    private Set<Vertex> vertices = new HashSet<>();
    private List<Edge> edges = new LinkedList<>();

    public Set<Vertex> getVertices() {
        return vertices;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public Map<Vertex, Set<Vertex>> getAdjacencyMap() {
        return adjacentVerticesMap;
    }
}
