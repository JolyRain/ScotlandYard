package frame;

import map.GameMap;

import javax.swing.*;

public class App {
    private JFrame mainFrame;
    private DrawPanel drawPanel;
    private JPanel leftPanel;
    private FileManager fileManager;
    private GameMap gameMap;

    public App(JFrame mainFrame, DrawPanel drawPanel, JPanel leftPanel, FileManager fileManager, GameMap gameMap) {
        this.mainFrame = mainFrame;
        this.drawPanel = drawPanel;
        this.leftPanel = leftPanel;
        this.fileManager = fileManager;
        this.gameMap = gameMap;
    }

    public App(FileManager fileManager, GameMap gameMap) {
        this.fileManager = fileManager;
        this.gameMap = gameMap;
    }

    public App(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public App(JFrame mainFrame, DrawPanel drawPanel, JPanel leftPanel) {
        this.mainFrame = mainFrame;
        this.drawPanel = drawPanel;
        this.leftPanel = leftPanel;
    }

    FileManager getFileManager() {
        return fileManager;
    }


    GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
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

    void setLeftPanel(JPanel leftPanel) {
        this.leftPanel = leftPanel;
    }


}
