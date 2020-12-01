package players;

import game.TypePlayer;
import game.WayBill;

import java.io.Serializable;
import java.util.HashMap;

public class MisterX extends Player  {

    private Integer amountTaxiTickets;
    private Integer amountBusTickets;
    private Integer amountMetroTickets;
    private Integer amountDoubleTickets;

    private WayBill wayBill;

    public MisterX() {
        super(TypePlayer.MISTER_X);
        wayBill = new WayBill(new HashMap<>());
        amountTaxiTickets = 4;
        amountBusTickets = 3;
        amountMetroTickets = 3;
        amountDoubleTickets = 2;
    }

    public Integer getAmountTaxiTickets() {
        return amountTaxiTickets;
    }

    public void setAmountTaxiTickets(Integer amountTaxiTickets) {
        this.amountTaxiTickets = amountTaxiTickets;
    }

    public Integer getAmountBusTickets() {
        return amountBusTickets;
    }

    public void setAmountBusTickets(Integer amountBusTickets) {
        this.amountBusTickets = amountBusTickets;
    }

    public Integer getAmountMetroTickets() {
        return amountMetroTickets;
    }

    public void setAmountMetroTickets(Integer amountMetroTickets) {
        this.amountMetroTickets = amountMetroTickets;
    }

    public Integer getAmountDoubleTickets() {
        return amountDoubleTickets;
    }

    public void setAmountDoubleTickets(Integer amountDoubleTickets) {
        this.amountDoubleTickets = amountDoubleTickets;
    }

    public WayBill getWayBill() {
        return wayBill;
    }

    public void setWayBill(WayBill wayBill) {
        this.wayBill = wayBill;
    }

}
