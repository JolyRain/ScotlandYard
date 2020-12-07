package graph;


import java.util.*;

public class Graph   {

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

    public void setVertices(Set<Vertex> vertices) {
        this.vertices = vertices;
    }
}
