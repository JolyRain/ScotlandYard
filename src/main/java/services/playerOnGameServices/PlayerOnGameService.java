package services.playerOnGameServices;

import game.ScotlandYardGame;
import game.Ticket;
import game.TypeTicket;
import game.TypePlayer;
import graph.Edge;
import graph.Vertex;
import players.Player;
import services.gameServices.GameService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public interface PlayerOnGameService {

    GameService gameService = new GameService();

    default void shuffleTickets(Player player) {
        Collections.shuffle(player.getTickets());
    }

    default Vertex getCurrentStation(Player player, ScotlandYardGame game) {
        Map<Player, Vertex> playerVertexMap = game.getPlayerVertexMap();
//        System.out.println("object " + player.hashCode());
//
//        for (Map.Entry<Player, Vertex> entry : playerVertexMap.entrySet()) {
//            System.out.println("entry  " + entry.getKey().hashCode() + " " + player.equals(entry.getKey()));
//        }
//        Vertex vertex = playerVertexMap.get(player);

        return playerVertexMap.get(player);
    }

    List<Edge> getAvailableRoads(Player player, ScotlandYardGame game, Ticket ticket);

    List<Vertex> getAvailableStations(Player player, ScotlandYardGame game, Ticket ticket); // ???

    Vertex setTargetStation(Player player, ScotlandYardGame game, Ticket ticket);

    default Player getPlayerOnStation(ScotlandYardGame game, Vertex station) {
        if (!gameService.isFreeStation(station, game)) {
            return game.getVertexPlayerMap().get(station);
        }
        return null;
    }
}
