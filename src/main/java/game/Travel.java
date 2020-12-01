package game;

import graph.Vertex;

import java.io.Serializable;

public class Travel {
    private Vertex startStation;
    private Vertex targetStation;
    private Ticket ticket;

    public Travel(Vertex startStation, Vertex targetStation, Ticket ticket) {
        this.startStation = startStation;
        this.targetStation = targetStation;
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Vertex getStartStation() {
        return startStation;
    }

    public void setStartStation(Vertex startStation) {
        this.startStation = startStation;
    }

    public Vertex getTargetStation() {
        return targetStation;
    }

    public void setTargetStation(Vertex targetStation) {
        this.targetStation = targetStation;
    }
}
