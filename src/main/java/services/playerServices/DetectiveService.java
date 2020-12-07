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
       setCurrentStations(player, targetStation, game);
        removeTicket(detective, game.getMisterX(), ticket);
        printer.printMove(player, targetStation, ticket);
    }

    private void removeTicket(Player player, Player misterX, Ticket ticket) {
        removeTicket(player, ticket);
        misterXService.addTicket(misterX, ticket);
    }

}
