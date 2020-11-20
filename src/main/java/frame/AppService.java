package frame;

import utils.Defaults;
import map.GameMap;

import javax.swing.*;

public class AppService {
    private FileService fileService = new FileService();

    public void initApp(App app) {
        initFrame(app);
        initDrawPanel(app);
//        initLeftPanel(app);
        fileService.readDefaultGraph(app.getFileManager(), app.getGameMap());

    }

    private void initFrame(App app) {
        JFrame mainFrame = new JFrame();
        mainFrame.setName("Scotland Yard");
        mainFrame.setSize(Defaults.FRAME_WIDTH, Defaults.FRAME_HEIGHT);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);    //magic number
        mainFrame.setLayout(null);
        mainFrame.setResizable(false);
        app.setMainFrame(mainFrame);
    }

    private void initDrawPanel(App app) {
        DrawPanel drawPanel = new DrawPanel();
        drawPanel.setLayout(null);
        drawPanel.setBounds(0, 0,
                Defaults.FRAME_WIDTH, Defaults.FRAME_HEIGHT);
        app.getMainFrame().add(drawPanel);
        app.setDrawPanel(drawPanel);
        app.getFileManager().setDrawPanel(drawPanel);
    }

    private void initLeftPanel(App app) {
        JPanel leftPanel = new JPanel();
        GameMap gameMap = app.getGameMap();
        FileManager fileManager = app.getFileManager();
        JButton button = new JButton("Read file");
        button.setFont(Defaults.FONT_BUTTON);
        button.addActionListener( e -> fileService.readGraphFromFile(fileManager, gameMap));
        leftPanel.add(button);
        leftPanel.setBounds(0, 0, Defaults.LEFT_PANEL_WIDTH, Defaults.FRAME_HEIGHT);
        app.getMainFrame().add(leftPanel);
        app.setLeftPanel(leftPanel);
    }

    public void show(App app) {
        app.getMainFrame().setVisible(true);

    }
}
