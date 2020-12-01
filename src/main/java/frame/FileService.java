package frame;

import drawer.World;
import drawer.WorldDrawer;
import map.GameMap;

import javax.swing.*;
import java.io.File;
import java.util.Scanner;

public class FileService {

    private WorldDrawer worldDrawer = new WorldDrawer();

    FileService() {
    }

    public String checkFileName(String fileName) {
        if (!fileName.endsWith(".graph")) {
            fileName += ".graph";
        }
        return fileName;
    }

    void readDefaultGraph(FileManager fileManager, World world) {
        DrawPanel drawPanel = fileManager.getDrawPanel();
        try (Scanner scanner = new Scanner(fileManager.getDEFAULT_GRAPH_FILE()))  {
            worldDrawer.readGraphFromFile(world, scanner);
            drawPanel.setWorld(world);

            drawPanel.repaint();
        } catch (Exception exc) {
            drawPanel.clear(world);
            JOptionPane.showMessageDialog(null, "Wrong file!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    void readGraphFromFile(FileManager fileManager, World world) {
        JFileChooser fileChooser = new JFileChooser();
        DrawPanel drawPanel = fileManager.getDrawPanel();
        fileChooser.setCurrentDirectory(new File("./src/files"));
        fileChooser.addChoosableFileFilter(fileManager.getGRAPH_FILTER());
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try (Scanner scanner = new Scanner(fileChooser.getSelectedFile())) {
                worldDrawer.readGraphFromFile(world, scanner);
                drawPanel.setWorld(world);
                drawPanel.repaint();
            } catch (Exception exc) {
                drawPanel.clear(world);
                JOptionPane.showMessageDialog(null, "Wrong file!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
