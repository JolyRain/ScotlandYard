package services.playerServices;

import game.ScotlandYardGame;
import game.Ticket;
import game.TypeTicket;
import graph.Vertex;
import players.Detective;
import players.Player;

import java.util.Map;

public interface PlayerService {

    void moveTo(Player player, ScotlandYardGame game, Vertex targetStation, Ticket ticket);

    default void removeTicket(Player player, Ticket ticket) {
        Map<TypeTicket, Integer> ticketsMap = player.getTicketsMap();
        Integer amountTickets = player.getTicketsMap().get(ticket.getType());
        ticketsMap.put(ticket.getType(), amountTickets - 1);
        player.getTickets().remove(ticket);
//        player.getTickets().removeIf(currentTicket -> currentTicket.equals(ticket));
    }

    default boolean hasTickets(Player player) {
        return player.getTickets().size() > 0;
    }

    default boolean hasTicket(Player player, TypeTicket typeTicket) {
        return player.getTicketsMap().get(typeTicket) > 0;
    }


}
