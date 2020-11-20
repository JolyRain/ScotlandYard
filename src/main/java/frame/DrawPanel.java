package frame;

import utils.Defaults;
import graph.Edge;
import graph.Graph;
import graph.TypeRoad;
import graph.Vertex;
import map.Circle;
import map.GameMap;
import map.Line;
import map.Node;
import services.graphServices.GraphService;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DrawPanel extends JPanel {

    private GraphService graphService = new GraphService();
    private GameMap gameMap;  //вынужденная необходимость

    void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public DrawPanel(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    DrawPanel() {
    }

    @Override
    public void paint(Graphics graphics) {
        if (gameMap == null) return;
        Graphics2D graphics2D = (Graphics2D) graphics;
        setPaintSettings(graphics2D);
        paintLines(graphics2D, gameMap);
        paintVertices(graphics2D, gameMap);
    }

    private void setPaintSettings(Graphics2D graphics2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setStroke(new BasicStroke(Defaults.STROKE_WIDTH));
        graphics2D.setFont(Defaults.FONT_PAINT_PANEL);
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0, 0, this.getWidth(), this.getHeight());
        graphics2D.setColor(Color.BLACK);
    }

    void clear(GameMap gameMap) {
        graphService.clear(gameMap.getGraph());
        gameMap.getNodes().clear();
        gameMap.getLines().clear();
        repaint();
    }

    private void paintLines(Graphics2D graphics2D, GameMap gameMap) {
        for (Line road : gameMap.getLines()) {
            Line2D line = road.getLine();
            graphics2D.setColor(road.getColor());
            graphics2D.draw(line);
        }
    }

    private void paintVertices(Graphics2D graphics2D, GameMap gameMap) {
        Circle circle;
        Vertex vertex;
        for (Node node : gameMap.getNodes()) {
            circle = node.getCircle();
            vertex = node.getVertex();
            graphics2D.setColor(Color.BLACK);
            graphics2D.draw(circle);
            graphics2D.setColor(Color.WHITE);
            graphics2D.fill(node.getCircle());
            graphics2D.setColor(Color.BLACK);
            graphics2D.drawString(
                    String.valueOf(vertex.getStationNumber()),
                    node.getNumberX(), node.getNumberY());
        }
    }

    private Node readNodeFromFile(String string) throws Exception {
        int x, y, vertexNumber;
        Pattern pattern = Pattern.compile("\\{([\\d]+)}");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            vertexNumber = Integer.parseInt(matcher.group(1));
        } else throw new Exception("Wrong format");
        pattern = Pattern.compile("\\(([\\d]+), ([\\d]+)\\)");
        matcher = pattern.matcher(string);
        if (matcher.find()) {
            x = Integer.parseInt(matcher.group(1));
            y = Integer.parseInt((matcher.group(2)));
        } else throw new Exception("Wrong format");
        Vertex newVertex = new Vertex(vertexNumber);
        Circle newCircle = new Circle(x, y);
        return new Node(newVertex, newCircle);
    }

    private void readAdjacentListFromFile(Graph graph, Vertex vertex, String string) throws Exception {
        Pattern pattern = Pattern.compile("\\[]");
        Matcher matcher = pattern.matcher(string);
        if (!matcher.find()) {
            pattern = Pattern.compile("\\[(\\{\\d*}|(, ))*]");
            matcher = pattern.matcher(string);
            if (matcher.find()) {
                pattern = Pattern.compile("\\{([\\d]+)}[, \\]]");
                matcher = pattern.matcher(matcher.group(1));
                while (matcher.find()) {
                    Map<Vertex, Set<Vertex>> adjacencyMap = graph.getAdjacencyMap();
                    Set<Vertex> vertices = adjacencyMap.get(vertex);
                    vertices.add(new Vertex(Integer.parseInt(matcher.group(1))));
                }
            } else throw new Exception("Wrong format");
        }
    }

    private void readEdgeFromFile(GameMap gameMap, String string) throws Exception {
        int startVertexNumber, endVertexNumber;
        Graph graph = gameMap.getGraph();
        List<Node> nodes = gameMap.getNodes();
        List<Line> lines = gameMap.getLines();
        TypeRoad typeRoad;
        Pattern pattern = Pattern.compile("<\\{([\\d]+)}, \\{([\\d]+)}>");
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            startVertexNumber = Integer.parseInt(matcher.group(1));
            endVertexNumber = Integer.parseInt(matcher.group(2));
        } else throw new Exception("Wrong format");
        pattern = Pattern.compile("\\{(METRO|TAXI|BUS)}");
        matcher = pattern.matcher(string);
        if (matcher.find()) {
            typeRoad = getType(matcher.group(1));
        } else throw new Exception("Wrong Format");
        Node firstNode = nodes.get(startVertexNumber);
        Node secondNode = nodes.get(endVertexNumber);
        Vertex firstVertex = graphService.getVertex(graph, startVertexNumber);
        Vertex secondVertex = graphService.getVertex(graph, endVertexNumber);
        Edge newEdge = new Edge(firstVertex, secondVertex, typeRoad);
        graphService.addEdge(graph, newEdge);
        lines.add(setNewLine(gameMap, firstNode, secondNode, typeRoad));
    }

    private TypeRoad getType(String string) {
        switch (string) {
            case "METRO":
                return TypeRoad.METRO;
            case "TAXI":                        //говнокод
                return TypeRoad.TAXI;
            case "BUS":
                return TypeRoad.BUS;
        }
        return null;
    }

    void readGraphFromFile(GameMap gameMap, Scanner scanner) throws Exception {
        clear(gameMap);
        List<Node> nodes = gameMap.getNodes();
        Graph graph = gameMap.getGraph();
        String string;
        while (scanner.hasNextLine()) {
            string = scanner.nextLine();
            if (string.startsWith("Node:")) {
                Node newNode = readNodeFromFile(string);
                Vertex newVertex = newNode.getVertex();
//                readAdjacentListFromFile(graph, newVertex, string);
                graphService.addVertex(graph, newVertex);
                nodes.add(newNode);
            } else if (string.startsWith("Line:")) readEdgeFromFile(gameMap, string);
        }
    }

    private Line setNewLine(GameMap gameMap, Node startNode, Node endNode, TypeRoad typeRoad) {
        Line newLine;
        Graph graph = gameMap.getGraph();
        int horizontalOffset = Circle.RADIUS;
        int verticalOffset = 0;
        Color color = Color.YELLOW;
        Circle circleStart = startNode.getCircle();
        Circle circleEnd = endNode.getCircle();
        Edge edge = graphService.getEdge(graph, startNode.getVertex(), endNode.getVertex(), typeRoad);
        int dx = (int) Math.abs(circleStart.getX() - circleEnd.getX());
        int dy = (int) Math.abs(circleStart.getY() - circleEnd.getY());
        switch (typeRoad) {
            case TAXI:
                verticalOffset = horizontalOffset;
                color = Color.BLACK;
                break;
            case BUS:
                verticalOffset = 3 * horizontalOffset / 2;
                color = Color.BLUE;
                break;
            case METRO:
                verticalOffset = horizontalOffset / 2;
                color = Color.RED;
                break;
        }
        if (dx < dy) {
            horizontalOffset = swap(verticalOffset, verticalOffset = horizontalOffset);
        }
        newLine = new Line(new Line2D.Double(
                circleStart.getX() + horizontalOffset,
                circleStart.getY() + verticalOffset,
                circleEnd.getX() + horizontalOffset,
                circleEnd.getY() + verticalOffset),
                edge);
        newLine.setColor(color);
        return newLine;
    }

    private int swap(int first, int second) {
        return first;
    }


}

