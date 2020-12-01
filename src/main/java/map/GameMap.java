package map;

import graph.Graph;

import java.io.Serializable;
import java.util.List;

public class GameMap  implements Serializable {
    private List<Node> nodes;
    private List<Line> lines;
    private Graph graph;

    public GameMap(List<Node> nodes, List<Line> lines, Graph graph) {
        this.nodes = nodes;
        this.lines = lines;
        this.graph = graph;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Node node : nodes) {
            stringBuilder.append(node);
            stringBuilder.append("\n");
        }
        for (Line line : lines) {
            stringBuilder.append(line);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
