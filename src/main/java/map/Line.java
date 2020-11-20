package map;

import graph.Edge;

import java.awt.*;
import java.awt.geom.Line2D;

public class Line {
    private Line2D line;
    private Edge edge;
    private Color color;

    public Line(Line2D line, Edge edge) {
        this.line = line;
        this.edge = edge;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Line2D getLine() {
        return line;
    }

    Edge getEdge() {
        return edge;
    }

    @Override
    public String toString() {
        return "Line: " + edge;
    }
}
