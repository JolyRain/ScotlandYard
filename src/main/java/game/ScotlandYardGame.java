package game;

import graph.Graph;
import graph.TypeRoad;
import graph.Vertex;
import players.Detective;
import players.MisterX;
import players.Player;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ScotlandYardGame  {
    public transient final Map<TypeTicket, TypeRoad> TICKET_ROAD_MAP = new HashMap<>();
    public transient final Map<TypeTicket, TypeRoad> ROAD_TICKET_MAP = new HashMap<>();


    private void initMaps() {
        TICKET_ROAD_MAP.put(TypeTicket.TAXI, TypeRoad.TAXI);
        TICKET_ROAD_MAP.put(TypeTicket.BUS, TypeRoad.BUS);         //улучшить
        TICKET_ROAD_MAP.put(TypeTicket.METRO, TypeRoad.METRO);
        ROAD_TICKET_MAP.put(TypeTicket.METRO, TypeRoad.METRO);
        ROAD_TICKET_MAP.put(TypeTicket.BUS, TypeRoad.BUS);
        ROAD_TICKET_MAP.put(TypeTicket.TAXI, TypeRoad.TAXI);
    }

    private Integer moveAmount;
    private Integer currentMove;
    private Integer detectivesAmount;
    private GameState gameState;
    private Graph graph;
    private Player misterX;
    private List<Vertex> stations;


    private Map<Vertex, Player> vertexPlayerMap;
    private Map<Player, Vertex> playerVertexMap;
    private Set<Player> detectives;
    private transient Map<Player, Travel> travels;

    public ScotlandYardGame(Integer moveAmount, Integer detectivesAmount) {
        this.moveAmount = moveAmount;
        this.detectivesAmount = detectivesAmount;
        initMaps();
        currentMove = 0;
    }

    public ScotlandYardGame() {
        initMaps();
    }

    public Player getPlayer(Player player) {
        switch (player.getTypePlayer()) {
            case MISTER_X:
                return misterX;
            case DETECTIVE:
                return getDetective((Detective) player);
        }
        return null;
    }

    private Player getDetective(Detective detective) {
        for (Player player : detectives) {
            Detective currentDetective = (Detective) player;
            if (detective.getName().equals(currentDetective.getName()))
                return player;
        }
        return null;
    }

    public Integer getCurrentMove() {
        return currentMove;
    }

    public void setCurrentMove(Integer currentMove) {
        this.currentMove = currentMove;
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

    public Player getMisterX() {
        return misterX;
    }

    public void setMisterX(MisterX misterX) {
        this.misterX = misterX;
    }

    public List<Vertex> getStations() {
        return stations;
    }

    public void setStations(List<Vertex> stations) {
        this.stations = stations;
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

    public Map<Player, Travel> getTravels() {
        return travels;
    }

    public void setTravels(Map<Player, Travel> travels) {
        this.travels = travels;
    }

    public Map<TypeTicket, TypeRoad> getTICKET_ROAD_MAP() {
        return TICKET_ROAD_MAP;
    }

    public Map<TypeTicket, TypeRoad> getROAD_TICKET_MAP() {
        return ROAD_TICKET_MAP;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void setMisterX(Player misterX) {
        this.misterX = misterX;
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
