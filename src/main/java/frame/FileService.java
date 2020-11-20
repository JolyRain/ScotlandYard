package frame;

import map.GameMap;

import javax.swing.*;
import java.io.File;
import java.util.Scanner;

public class FileService {

    FileService() {
    }

    public String checkFileName(String fileName) {
        if (!fileName.endsWith(".graph")) {
            fileName += ".graph";
        }
        return fileName;
    }

    void readDefaultGraph(FileManager fileManager, GameMap gameMap) {
        DrawPanel drawPanel = fileManager.getDrawPanel();
        try (Scanner scanner = new Scanner(fileManager.getDEFAULT_GRAPH_FILE()))  {
            drawPanel.readGraphFromFile(gameMap, scanner);
            drawPanel.setGameMap(gameMap);
            drawPanel.repaint();
        } catch (Exception exc) {
            drawPanel.clear(gameMap);
            JOptionPane.showMessageDialog(null, "Wrong file!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    void readGraphFromFile(FileManager fileManager, GameMap gameMap) {
        JFileChooser fileChooser = new JFileChooser();
        DrawPanel drawPanel = fileManager.getDrawPanel();
        fileChooser.setCurrentDirectory(new File("./src/mapsFiles"));
        fileChooser.addChoosableFileFilter(fileManager.getGRAPH_FILTER());
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);

        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            try (Scanner scanner = new Scanner(fileChooser.getSelectedFile())) {
                drawPanel.readGraphFromFile(gameMap, scanner);
                drawPanel.setGameMap(gameMap);
                drawPanel.repaint();
            } catch (Exception exc) {
                drawPanel.clear(gameMap);
                JOptionPane.showMessageDialog(null, "Wrong file!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
