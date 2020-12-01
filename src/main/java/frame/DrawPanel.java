package frame;

import drawer.World;
import drawer.WorldDrawer;
import services.graphServices.GraphService;
import utils.Defaults;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class DrawPanel extends JPanel {

    private GraphService graphService = new GraphService();
    private World world;  //вынужденная необходимость
    private WorldDrawer worldDrawer = new WorldDrawer();

    public DrawPanel(World world) {
        this.world = world;
    }

    DrawPanel() {
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    @Override
    public void paint(Graphics graphics) {
        if (world.getMap() == null) return;
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        Graphics2D graphics2D = (Graphics2D) graphics;
        setPaintSettings(graphics2D);
        worldDrawer.drawAll(graphics2D, world);
    }

    private void setPaintSettings(Graphics2D graphics2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setStroke(new BasicStroke(Defaults.STROKE_WIDTH));
        graphics2D.setFont(Defaults.FONT_PAINT_PANEL);
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, this.getWidth(), this.getHeight());
        graphics2D.setColor(Color.BLACK);
    }

    void clear(World world) {
        worldDrawer.clear(world);
        repaint();
    }

}

