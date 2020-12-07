package services.playerOnGameServices;

import game.ScotlandYardGame;
import game.Ticket;
import game.TypeTicket;
import game.TypePlayer;
import graph.Edge;
import graph.TypeRoad;
import graph.Vertex;
import players.Detective;
import players.Player;
import services.graphServices.EdgeService;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DetectiveOnGameService implements PlayerOnGameService {

    public Ticket getCurrentTicket(Player player) {
        List<Ticket> tickets = player.getTickets();
        int randomIndex = (int) (Math.random() * tickets.size());
        return player.getTickets().get(randomIndex);
    }

    @Override
    public Vertex getCurrentStation(Player player, ScotlandYardGame game) {
        Detective detective = (Detective) player;
        Map<Player, Vertex> playerVertexMap = game.getPlayerVertexMap();
        return playerVertexMap.get(detective);
    }

    @Override
    public List<Edge> getAvailableRoads(Player player, ScotlandYardGame game, Ticket ticket) {
        Vertex currentStation = getCurrentStation(player, game);
        TypeTicket typeTicket = ticket.getType();
        Map<TypeRoad, List<Edge>> roadMap = currentStation.getRoadMap();
        return roadMap.get(game.TICKET_ROAD_MAP.get(typeTicket));
    }

    @Override
    public List<Vertex> getAvailableStations(Player player, ScotlandYardGame game, Ticket ticket) {
        Vertex currentStation = getCurrentStation(player, game);
        EdgeService edgeService = new EdgeService();
        List<Edge> availableRoads = getAvailableRoads(player, game, ticket);
        List<Vertex> availableStations = new LinkedList<>();
        for (Edge road : availableRoads) {
            Vertex availableStation = edgeService.getNeededVertex(road, currentStation);
            Player playerOnStation = getPlayerOnStation(game, availableStation);
            if (gameService.isFreeStation(availableStation, game) || playerOnStation.getTypePlayer().equals(TypePlayer.MISTER_X)) {
                availableStations.add(availableStation);
            }
        }
        return availableStations;
    }

    @Override
    public Vertex setTargetStation(Player player, ScotlandYardGame game, Ticket ticket) {
        List<Vertex> availableStations = getAvailableStations(player, game, ticket);
        if (availableStations.size() == 0) return null;
        int randomIndex = (int) (Math.random() * availableStations.size());
        return availableStations.get(randomIndex);
    }
}
