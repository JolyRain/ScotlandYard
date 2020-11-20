package services.playerServices;

import console.Printer;
import game.ScotlandYardGame;
import game.Ticket;
import game.TypeTicket;
import game.WayBill;
import graph.Vertex;
import players.MisterX;
import players.Player;

import java.util.Map;

public class MisterXService implements PlayerService {

    private Printer printer = new Printer();

    @Override
    public void moveTo(Player player, ScotlandYardGame game, Vertex targetStation, Ticket ticket) {
        MisterX misterX = (MisterX) player;
        WayBill wayBill = misterX.getWayBill();
        Map<Vertex, TypeTicket> stepsMap = wayBill.getStepsMap();
        Integer stepsToShow = wayBill.getStepsToShow();
        stepsMap.put(targetStation, ticket.getType());
        removeTicket(misterX, ticket);
        stepsToShow++;
        wayBill.setStepsToShow(stepsToShow);
        Map<Player, Vertex> playerVertexMap = game.getPlayerVertexMap();
        Map<Vertex, Player> vertexPlayerMap = game.getVertexPlayerMap();
        playerVertexMap.put(misterX, targetStation);
        vertexPlayerMap.put(targetStation, misterX);
        printer.printMove(player, targetStation, ticket);
    }

    public void addTicket(MisterX misterX, Ticket ticket) {
        Map<TypeTicket, Integer> ticketsMap = misterX.getTicketsMap();
        Integer amountTickets = ticketsMap.get(ticket.getType());
        ticketsMap.put(ticket.getType(), amountTickets + 1);
        misterX.getTickets().add(ticket);
    }

    public void showCurrentStation(MisterX misterX, ScotlandYardGame game) {
        WayBill wayBill = misterX.getWayBill();
        Integer stepsToShow = wayBill.getStepsToShow();
        if (stepsToShow % 3 == 0) wayBill.setShowedStation(game.getPlayerVertexMap().get(misterX));
    }

}
