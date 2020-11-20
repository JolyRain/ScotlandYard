package services.playerOnGameServices;

import game.ScotlandYardGame;
import game.Ticket;
import game.TypeTicket;
import graph.Edge;
import graph.TypeRoad;
import graph.Vertex;
import players.Player;
import services.graphServices.EdgeService;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MisterXOnGameService implements PlayerOnGameService {

    public Ticket getCurrentTicket(Player player) {
        List<Ticket> tickets = player.getTickets();
        int randomIndex = (int) (Math.random() * tickets.size());
        return player.getTickets().get(randomIndex);
    }

    @Override
    public List<Edge> getAvailableRoads(Player player, ScotlandYardGame game, Ticket ticket) {
        Vertex currentStation = getCurrentStation(player, game);
        TypeTicket typeTicket = ticket.getType();
        Map<TypeRoad, List<Edge>> roadMap = currentStation.getRoadMap();

        if (typeTicket.equals(TypeTicket.BLACK) || typeTicket.equals(TypeTicket.DOUBLE)) {
            List<Edge> allRoads = new LinkedList<>();
            allRoads.addAll(roadMap.get(TypeRoad.METRO));
            allRoads.addAll(roadMap.get(TypeRoad.TAXI));
            allRoads.addAll(roadMap.get(TypeRoad.BUS));
            return allRoads;
        }
        return roadMap.get(ScotlandYardGame.TICKET_ROAD_MAP.get(typeTicket));
    }

    @Override
    public List<Vertex> getAvailableStations(Player player, ScotlandYardGame game, Ticket ticket) {
        Vertex currentStation = getCurrentStation(player, game);
        List<Edge> availableRoads = getAvailableRoads(player, game, ticket);
        EdgeService edgeService = new EdgeService();
        List<Vertex> availableStations = new LinkedList<>();
        for (Edge road : availableRoads) {
            availableStations.add(edgeService.getNeededVertex(road, currentStation));
        }
        return availableStations;
    }

    //тупая логика - бот рандомно выбирает следующую вершину
    @Override
    public Vertex setTargetStation(Player player, ScotlandYardGame game, Ticket ticket) {
        List<Vertex> availableStations = getAvailableStations(player, game, ticket);
        if (availableStations.size() == 0) return null;
        int randomIndex = (int) (Math.random() * availableStations.size());
        return availableStations.get(randomIndex);
    }
}
