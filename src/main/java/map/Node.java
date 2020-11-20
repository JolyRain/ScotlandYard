package map;

import graph.Vertex;

public class Node {
    private Vertex vertex;
    private Circle circle;

    public Node(Vertex vertex, Circle circle) {
        this.vertex = vertex;
        this.circle = circle;
    }

    public int getNumberX() {
        return (int) (circle.getX() +  2 * Circle.RADIUS / 3);
    }   //magic number

    public int getNumberY() {
        return (int) (circle.getY() + 4 * Circle.RADIUS / 3);
    }

    public Vertex getVertex() {
        return vertex;
    }

    public Circle getCircle() {
        return circle;
    }

    @Override
    public String toString() {
        return "Node: "
                + vertex.toString()
                + " (" + (int) circle.getX() + ", " + (int) circle.getY() + ") ";
    }
}
