package drawer;

import game.ScotlandYardGame;
import game.Travel;
import graph.Edge;
import graph.Graph;
import graph.TypeRoad;
import graph.Vertex;
import map.Circle;
import map.GameMap;
import map.Line;
import map.Node;
import players.Player;
import services.graphServices.EdgeService;
import services.graphServices.GraphService;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorldDrawer implements Drawable {
    private GraphService graphService = new GraphService();
    private EdgeService edgeService = new EdgeService();

    public WorldDrawer() {
    }

    @Override
    public void clear(World world) {
        graphService.clear(world.getMap().getGraph());
        world.getMap().getNodes().clear();
        world.getMap().getLines().clear();
    }

    private void drawPlayers(World world) {
        Map<Vertex, Player> locations = world.getGame().getVertexPlayerMap();
        List<Node> nodes = world.getMap().getNodes();
        for (Node node : nodes) {
            Vertex vertex = node.getVertex();
            Circle circle = node.getCircle();
            if (locations.containsKey(vertex)) {
                switch (locations.get(vertex).getTypePlayer()) {
                    case MISTER_X:
                        circle.setColor(Color.RED);
                        break;
                    case DETECTIVE:
                        circle.setColor(Color.BLUE);
                        break;
                }
            } else circle.setColor(Color.WHITE);
        }
    }

    private void drawTravel(World world) {
        ScotlandYardGame game = world.getGame();
        GameMap map = world.getMap();
        List<Line> lines = map.getLines();
        Map<Player, Travel> travels = game.getTravels();
        for (Map.Entry<Player, Travel> entry : travels.entrySet()) {
            Travel travel = entry.getValue();
            Vertex start = travel.getStartStation();
            Vertex target = travel.getTargetStation();
            TypeRoad typeRoad = game.TICKET_ROAD_MAP.get(travel.getTicket().getType());
            for (Line road : lines) {
                if (edgeService.contains(road.getEdge(), start, target, typeRoad)) {
                    switch (entry.getKey().getTypePlayer()) {
                        case MISTER_X:
                            road.setColor(Color.RED);
                            break;
                        case DETECTIVE:
                            road.setColor(Color.BLUE);
                            break;
                    }
                    break;
                }
            }
        }
    }

    private void setColoredLine(Line line) {
        switch (line.getEdge().getType()) {
            case TAXI:
                line.setColor(new Color(255,240,90));
                break;
            case BUS:
                line.setColor(new Color(120, 180, 250));
                break;
            case METRO:
                line.setColor(new Color(200, 0, 100));
                break;
        }
    }


    @Override
    public void drawAll(Graphics2D graphics2D, World world) {
        drawLines(graphics2D, world);
        drawVertices(graphics2D, world);
    }

    private void drawLines(Graphics2D graphics2D, World world) {
        drawTravel(world);
        for (Line road : world.getMap().getLines()) {
            Line2D line = road.getLine();
            graphics2D.setColor(road.getColor());
            graphics2D.draw(line);
            setColoredLine(road);
        }
    }

    private void drawVertices(Graphics2D graphics2D, World world) {
        Circle circle;
        Vertex vertex;
        drawPlayers(world);
        for (Node node : world.getMap().getNodes()) {
            circle = node.getCircle();
            vertex = node.getVertex();
            graphics2D.setColor(Color.BLACK);
            graphics2D.draw(circle);
            graphics2D.setColor(node.getCircle().getColor());
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

    private void readEdgeFromFile(World world, String string) throws Exception {
        int startVertexNumber, endVertexNumber;
        Graph graph = world.getMap().getGraph();
        List<Node> nodes = world.getMap().getNodes();
        List<Line> lines = world.getMap().getLines();
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
        lines.add(setNewLine(world, firstNode, secondNode, typeRoad));
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

    public void readGraphFromFile(World world, Scanner scanner) throws Exception {
        clear(world);
        List<Node> nodes = world.getMap().getNodes();
        Graph graph = world.getMap().getGraph();
        String string;
        while (scanner.hasNextLine()) {
            string = scanner.nextLine();
            if (string.startsWith("Node:")) {
                Node newNode = readNodeFromFile(string);
                Vertex newVertex = newNode.getVertex();
//                readAdjacentListFromFile(graph, newVertex, string);
                graphService.addVertex(graph, newVertex);
                nodes.add(newNode);
            } else if (string.startsWith("Line:")) readEdgeFromFile(world, string);
        }
    }

    private Polygon getArrow(Line2D line) {
        int c = 5;
        Polygon polygon = new Polygon();
        polygon.addPoint((int) line.getX2(), (int) line.getY2());
        polygon.addPoint((int) line.getX2() - c, (int) line.getY2());
        return polygon;
    }

    private Line setNewLine(World world, Node startNode, Node endNode, TypeRoad typeRoad) {
        Line newLine;
        Graph graph = world.getMap().getGraph();
        int horizontalOffset = Circle.RADIUS;
        int verticalOffset = 0;
//        Color color = Color.YELLOW;
        Circle circleStart = startNode.getCircle();
        Circle circleEnd = endNode.getCircle();
        Edge edge = graphService.getEdge(graph, startNode.getVertex(), endNode.getVertex(), typeRoad);
        int dx = (int) Math.abs(circleStart.getX() - circleEnd.getX());
        int dy = (int) Math.abs(circleStart.getY() - circleEnd.getY());
        switch (typeRoad) {
            case TAXI:
                verticalOffset = horizontalOffset;
                break;
            case BUS:
                verticalOffset = 3 * horizontalOffset / 2;
                break;
            case METRO:
                verticalOffset = horizontalOffset / 2;
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
        setColoredLine(newLine);
        return newLine;
    }

    private int swap(int first, int second) {
        return first;
    }


}
