package players;

import game.TypePlayer;

public class Detective extends Player {

    private Integer amountTaxiTickets;
    private Integer amountBusTickets;
    private Integer amountMetroTickets;

    private String name;

    public Detective(String name) {
        super(TypePlayer.DETECTIVE);
        this.name = name;
        amountTaxiTickets = 0; //10
        amountBusTickets = 0; //8
        amountMetroTickets = 1; //4
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return this.getTYPE().toString() + "-" + name;
    }
}
