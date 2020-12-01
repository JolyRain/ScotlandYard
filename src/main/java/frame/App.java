package frame;

import drawer.World;
import map.GameMap;

import javax.swing.*;
import java.io.Serializable;

public class App  {
    private JFrame mainFrame;
    private DrawPanel drawPanel;
    private FileManager fileManager;
    private World world;

    public App(FileManager fileManager, World world) {
        this.fileManager = fileManager;
        this.world = world;
    }

    FileManager getFileManager() {
        return fileManager;
    }


    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public DrawPanel getDrawPanel() {
        return drawPanel;
    }

    JFrame getMainFrame() {
        return mainFrame;
    }

    void setMainFrame(JFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    void setDrawPanel(DrawPanel drawPanel) {
        this.drawPanel = drawPanel;
    }



}
