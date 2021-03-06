package console;

import game.ScotlandYardGame;
import game.Ticket;
import game.TypePlayer;
import graph.Vertex;
import players.MisterX;
import players.Player;

import java.util.Map;
import java.util.Set;

public class Printer implements Printable {

    public void printGameState(ScotlandYardGame game) {
        printLongSeparator();
        print("MOVE--" + game.getCurrentMove() + " " + game.getState().toString(), ANSI_GREEN );
        printLongSeparator();
    }

    private void printPlayer(Player player, ScotlandYardGame game) {
        String playerString = player.toString().concat(player.ticketsToString());

        if (player.getTypePlayer().equals(TypePlayer.DETECTIVE)) {
            print(playerString, ANSI_BLUE);
        }
        if (player.getTypePlayer().equals(TypePlayer.MISTER_X)) {
            print(playerString, ANSI_RED);
        }
    }

    public void printPlayers(ScotlandYardGame game) {
        print("PLAYERS", ANSI_CYAN);
        MisterX misterX = (MisterX) game.getMisterX();
        Set<Player> detectives = game.getDetectives();
        printPlayer(misterX, game);
        for (Player detective : detectives) {
            printPlayer(detective, game);
        }
        printShortSeparator();
    }

    public void printMove(Player player, Vertex targetStation, Ticket ticket) {

        print(player.toString() + " ---" + ticket.getType() + "---> " + targetStation, ANSI_RESET);
    }

    public void printLocations(ScotlandYardGame game) {
        print("CURRENT LOCATIONS", ANSI_CYAN);
        Map<Player, Vertex> locationsMap = game.getPlayerVertexMap();
        for (Map.Entry<Player, Vertex> location : locationsMap.entrySet()) {
            Player player = location.getKey();
            Vertex station = location.getValue();
//            String availableStations = game.getGraph().getAdjacencyMap().get(game.getPlayerVertexMap().get(player)).toString();
            print(player.toString() + " <---> " + station, ANSI_RESET);
        }
        printShortSeparator();
    }



//    private String getAvailableStations(Player player, ScotlandYardGame game) {
//        StringBuilder stringBuilder = new StringBuilder();
//        Graph graph = game.getGraph();
//        Map<Vertex, Set<Vertex>> adjacencyMap = graph.getAdjacencyMap();
//        Vertex currentStation = game.getPlayerVertexMap().get(player);
//        for (Vertex station : adjacencyMap.get(currentStation)) {
//
//
//        }
//    }



    private void printLongSeparator() {
        print("-----------------------------------------------------------------------------" +
                "---------------------------------------------------------", ANSI_BLACK);
    }

    public void printShortSeparator() {
        print("----------------------------------", ANSI_WHITE);
    }

}
