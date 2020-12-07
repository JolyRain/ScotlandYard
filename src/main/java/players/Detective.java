package players;

import game.Ticket;
import game.TypePlayer;
import game.TypeTicket;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Detective extends Player  {

    private Integer maxAmountTaxiTickets;

    private Integer maxAmountBusTickets;

    private Integer maxAmountMetroTickets;

    private String name;

    public Detective(String name) {
        super(TypePlayer.DETECTIVE);
        this.name = name;
        maxAmountTaxiTickets = 10;
        maxAmountBusTickets = 8;
        maxAmountMetroTickets = 4;
    }

    public Detective(TypePlayer typePlayer, Map<TypeTicket, Integer> ticketsMap, List<Ticket> tickets, Integer maxAmountTaxiTickets, Integer maxAmountBusTickets, Integer maxAmountMetroTickets, String name) {
        super(typePlayer, ticketsMap, tickets);
        this.maxAmountTaxiTickets = maxAmountTaxiTickets;
        this.maxAmountBusTickets = maxAmountBusTickets;
        this.maxAmountMetroTickets = maxAmountMetroTickets;
        this.name = name;
    }

    public Detective() {
    }

    public void setTypePlayer(TypePlayer typePlayer) {
        super.setTypePlayer(typePlayer);
    }

    public Integer getMaxAmountTaxiTickets() {
        return maxAmountTaxiTickets;
    }

    public void setMaxAmountTaxiTickets(Integer maxAmountTaxiTickets) {
        this.maxAmountTaxiTickets = maxAmountTaxiTickets;
    }

    public Integer getMaxAmountBusTickets() {
        return maxAmountBusTickets;
    }

    public void setMaxAmountBusTickets(Integer maxAmountBusTickets) {
        this.maxAmountBusTickets = maxAmountBusTickets;
    }

    public Integer getMaxAmountMetroTickets() {
        return maxAmountMetroTickets;
    }

    public void setMaxAmountMetroTickets(Integer maxAmountMetroTickets) {
        this.maxAmountMetroTickets = maxAmountMetroTickets;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return this.getTypePlayer().toString() + "-" + name;
    }
}
