package players;

import game.Ticket;
import game.TypeTicket;
import game.TypePlayer;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public abstract class Player {

    private final TypePlayer TYPE;
    private Map<TypeTicket, Integer> ticketsMap;
    private List<Ticket> tickets;

    Player(TypePlayer type) {
        this.TYPE = type;
        ticketsMap = new HashMap<>();
        tickets = new LinkedList<>();
    }

    public TypePlayer getTYPE() {
        return TYPE;
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
        return TYPE.toString();
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