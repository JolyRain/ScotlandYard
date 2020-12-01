package map;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

public class Circle extends Ellipse2D.Double implements Serializable {
    public static final int RADIUS = 15;
    private Color color = Color.WHITE;
    private int x;
    private int y;


    public Circle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public double getWidth() {
        return RADIUS * 2;
    }

    @Override
    public double getHeight() {
        return RADIUS * 2;
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported");
    }

    @Override
    public void setFrame(double x, double y, double w, double h) {
        throw new UnsupportedOperationException("Not supported");           // что делать
    }

    @Override
    public Rectangle2D getBounds2D() {
        throw new UnsupportedOperationException("Not supported");
    }
}