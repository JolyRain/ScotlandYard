package players;

import com.google.gson.annotations.SerializedName;
import game.Ticket;
import game.TypePlayer;
import game.TypeTicket;

import java.util.*;

public abstract class Player {

    private TypePlayer typePlayer;

    @SerializedName("amount of tickets")
    private Map<TypeTicket, Integer> ticketsMap;

    private List<Ticket> tickets;

    Player(TypePlayer type) {
        this.typePlayer = type;
        ticketsMap = new HashMap<>();
        tickets = new LinkedList<>();
    }

    public Player(TypePlayer typePlayer, Map<TypeTicket, Integer> ticketsMap, List<Ticket> tickets) {
        this.typePlayer = typePlayer;
        this.ticketsMap = ticketsMap;
        this.tickets = tickets;
    }

    public Player() {
    }

    public void setTypePlayer(TypePlayer typePlayer) {
        this.typePlayer = typePlayer;
    }

    public TypePlayer getTypePlayer() {
        return typePlayer;
    }

    public Map<TypeTicket, Integer> getTicketsMap() {
        return ticketsMap;
    }

    public void setTicketsMap(Map<TypeTicket, Integer> ticketsMap) {
        this.ticketsMap = ticketsMap;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return typePlayer.toString();
    }

    public String ticketsToString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" TICKETS = [ ");
        for (Map.Entry<TypeTicket, Integer> entry : ticketsMap.entrySet()) {
            stringBuilder.append(entry.getKey()).append(" = ").append(entry.getValue()).append(" ");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }


}