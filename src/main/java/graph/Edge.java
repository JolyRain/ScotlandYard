package graph;

import java.io.Serializable;
import java.util.Objects;

public class Edge  {
    private Vertex startVertex;
    private Vertex endVertex;
    private TypeRoad type;

    public Edge(Vertex startVertex, Vertex endVertex, TypeRoad type) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.type = type;
    }

    public Edge() {
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public Vertex getEndVertex() {
        return endVertex;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Edge edge = (Edge) object;
        return ((startVertex.equals(edge.startVertex) && endVertex.equals(edge.endVertex))
                || (startVertex.equals(edge.endVertex) && endVertex.equals(edge.startVertex)))
                && type.equals(edge.type);
    }

    public TypeRoad getType() {
        return type;
    }

    public void setType(TypeRoad type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startVertex, endVertex, type);
    }

    @Override
    public String toString() {
        return "<" + startVertex.getStationNumber() + ", " + endVertex.getStationNumber() + ">" + " {" + type + "}";
    }
}
