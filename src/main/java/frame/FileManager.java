package frame;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.Serializable;

public class FileManager  {
    private final FileFilter GRAPH_FILTER = new FileNameExtensionFilter("graph.Graph files (*.graph)", "graph");
    private final File DEFAULT_GRAPH_FILE = new File("./src/main/java/files/defaultMap.graph");
    private DrawPanel drawPanel;

    public FileManager() {
    }

    FileFilter getGRAPH_FILTER() {
        return GRAPH_FILTER;
    }

    DrawPanel getDrawPanel() {
        return drawPanel;
    }

    void setDrawPanel(DrawPanel drawPanel) {
        this.drawPanel = drawPanel;
    }

    File getDEFAULT_GRAPH_FILE() {
        return DEFAULT_GRAPH_FILE;
    }
}
