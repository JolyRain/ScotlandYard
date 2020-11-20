package services.playerServices;

import console.Printer;
import game.ScotlandYardGame;
import game.Ticket;
import graph.Vertex;
import players.Detective;
import players.MisterX;
import players.Player;

import java.util.Map;

public class DetectiveService implements PlayerService {

    private MisterXService misterXService = new MisterXService();
    private Printer printer = new Printer();

    @Override
    public void moveTo(Player player, ScotlandYardGame game, Vertex targetStation, Ticket ticket) {
        Detective detective = (Detective) player;
        Map<Player, Vertex> playerVertexMap = game.getPlayerVertexMap();
        Map<Vertex, Player> vertexPlayerMap = game.getVertexPlayerMap();
        playerVertexMap.put(detective, targetStation);
//        vertexPlayerMap.remove(playerVertexMap.get(player));
        vertexPlayerMap.put(targetStation, detective);
        removeTicket(detective, game.getMisterX(), ticket);
        printer.printMove(player, targetStation, ticket);
    }

    private void removeTicket(Player player, MisterX misterX, Ticket ticket) {
        removeTicket(player, ticket);
        misterXService.addTicket(misterX, ticket);
    }

    private void setCurrentStation(Detective detective, ScotlandYardGame game, Vertex targetStation) {

    }
}
