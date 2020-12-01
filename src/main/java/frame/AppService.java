package frame;

import drawer.World;
import utils.Defaults;
import map.GameMap;

import javax.swing.*;

public class AppService {
    private FileService fileService = new FileService();

    public void initApp(App app) {
        initFrame(app);
        initDrawPanel(app);
//        initLeftPanel(app);
        fileService.readDefaultGraph(app.getFileManager(), app.getWorld());

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

    public void repaint(App app) {
        app.getFileManager().getDrawPanel().repaint();
    }

    public void show(App app) {
        app.getMainFrame().setVisible(true);

    }
}
