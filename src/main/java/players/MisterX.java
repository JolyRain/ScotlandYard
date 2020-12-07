package players;

import game.Ticket;
import game.TypePlayer;
import game.TypeTicket;
import game.WayBill;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MisterX extends Player  {

    private Integer maxAmountTaxiTickets;

    private Integer maxAmountBusTickets;

    private Integer maxAmountMetroTickets;

    private Integer maxAmountDoubleTickets;

    private WayBill wayBill;

    public MisterX() {
        super(TypePlayer.MISTER_X);
        wayBill = new WayBill(new HashMap<>());
        maxAmountTaxiTickets = 4;
        maxAmountBusTickets = 3;
        maxAmountMetroTickets = 3;
        maxAmountDoubleTickets = 2;
    }

    public MisterX(TypePlayer typePlayer, Map<TypeTicket, Integer> ticketsMap, List<Ticket> tickets, Integer maxAmountTaxiTickets, Integer maxAmountBusTickets, Integer maxAmountMetroTickets, Integer maxAmountDoubleTickets, WayBill wayBill) {
        super(typePlayer, ticketsMap, tickets);
        this.maxAmountTaxiTickets = maxAmountTaxiTickets;
        this.maxAmountBusTickets = maxAmountBusTickets;
        this.maxAmountMetroTickets = maxAmountMetroTickets;
        this.maxAmountDoubleTickets = maxAmountDoubleTickets;
        this.wayBill = wayBill;
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

    public Integer getMaxAmountDoubleTickets() {
        return maxAmountDoubleTickets;
    }

    public void setMaxAmountDoubleTickets(Integer maxAmountDoubleTickets) {
        this.maxAmountDoubleTickets = maxAmountDoubleTickets;
    }

    public WayBill getWayBill() {
        return wayBill;
    }

    public void setWayBill(WayBill wayBill) {
        this.wayBill = wayBill;
    }


}
