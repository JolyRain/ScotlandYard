package game;

import graph.Graph;
import graph.TypeRoad;
import graph.Vertex;
import players.MisterX;
import players.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ScotlandYardGame {
    public static final Map<TypeTicket, TypeRoad> TICKET_ROAD_MAP = new HashMap<>();
    public static final Map<TypeTicket, TypeRoad> ROAD_TICKET_MAP = new HashMap<>();
    private Integer moveAmount;
    private Integer detectivesAmount;

    static {
        TICKET_ROAD_MAP.put(TypeTicket.TAXI, TypeRoad.TAXI);
        TICKET_ROAD_MAP.put(TypeTicket.BUS, TypeRoad.BUS);         //улучшить
        TICKET_ROAD_MAP.put(TypeTicket.METRO, TypeRoad.METRO);
        ROAD_TICKET_MAP.put(TypeTicket.METRO, TypeRoad.METRO);
        ROAD_TICKET_MAP.put(TypeTicket.BUS, TypeRoad.BUS);
        ROAD_TICKET_MAP.put(TypeTicket.TAXI, TypeRoad.TAXI);
    }

    private Graph graph;
    private GameState gameState;
    private Map<Vertex, Player> vertexPlayerMap;
    private Map<Player, Vertex> playerVertexMap;
    private Set<Player> detectives;
    private MisterX misterX;
    private List<Vertex> stations;

    public ScotlandYardGame(Integer moveAmount, Integer detectivesAmount) {
        this.moveAmount = moveAmount;
        this.detectivesAmount = detectivesAmount;
    }

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public GameState getState() {
        return gameState;
    }

    public void setState(GameState gameState) {
        this.gameState = gameState;
    }

    public Map<Vertex, Player> getVertexPlayerMap() {
        return vertexPlayerMap;
    }

    public void setVertexPlayerMap(Map<Vertex, Player> vertexPlayerMap) {
        this.vertexPlayerMap = vertexPlayerMap;
    }

    public Map<Player, Vertex> getPlayerVertexMap() {
        return playerVertexMap;
    }

    public void setPlayerVertexMap(Map<Player, Vertex> playerVertexMap) {
        this.playerVertexMap = playerVertexMap;
    }

    public Set<Player> getDetectives() {
        return detectives;
    }

    public void setDetectives(Set<Player> detectives) {
        this.detectives = detectives;
    }

    public MisterX getMisterX() {
        return misterX;
    }

    public void setMisterX(MisterX misterX) {
        this.misterX = misterX;
    }

    public List<Vertex> getStations() {
        return stations;
    }

    public Integer getMoveAmount() {
        return moveAmount;
    }

    public void setMoveAmount(Integer moveAmount) {
        this.moveAmount = moveAmount;
    }

    public Integer getDetectivesAmount() {
        return detectivesAmount;
    }

    public void setDetectivesAmount(Integer detectivesAmount) {
        this.detectivesAmount = detectivesAmount;
    }

    public void setStations(List<Vertex> stations) {
        this.stations = stations;
    }

    @Override
    public String toString() {
        return "ScotlandYardGame{" +
                "gameState = " + gameState +
                ", vertexPlayerMap = " + vertexPlayerMap +
                ", playerVertexMap = " + playerVertexMap +
                ", detectives = " + detectives +
                ", misterX = " + misterX +
                ", stations = " + stations +
                '}';
    }
}
