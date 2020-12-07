package services.gameServices;

import console.Printer;
import game.*;
import graph.Graph;
import graph.Vertex;
import map.GameMap;
import players.Detective;
import players.MisterX;
import players.Player;
import serialize.ISerializer;
import serialize.gson.GsonSerializer;
import services.graphServices.GraphService;
import services.playerOnGameServices.DetectiveOnGameService;
import services.playerOnGameServices.MisterXOnGameService;
import services.playerServices.DetectiveService;
import services.playerServices.MisterXService;
import utils.Defaults;

import java.io.IOException;
import java.util.*;

import static console.Printable.ANSI_CYAN;
import static game.GameState.*;

public class GameService {

    private MisterXService misterXService = new MisterXService();
    private DetectiveService detectiveService = new DetectiveService();
    private MisterXOnGameService misterXOnGameService = new MisterXOnGameService();
    private DetectiveOnGameService detectiveOnGameService = new DetectiveOnGameService();
    private GraphService graphService = new GraphService();
    private Printer printer = new Printer();
    private ISerializer serializer = new GsonSerializer();

    public void initGame(ScotlandYardGame game) {
//        initGraph(game);
        game.setMisterX(new MisterX());
        initDetectives(game);
        setLocationMaps(game);
        setStartStations(game);
        ticketsDistribution(game);
        game.setTravels(new HashMap<>());
        game.setStations(new LinkedList<>(game.getGraph().getVertices()));
        game.setState(PLAYING);
    }

    public void initGraph(GameMap gameMap, ScotlandYardGame game) {
        Graph graph = gameMap.getGraph();
        game.setGraph(graph);
        game.setStations(new LinkedList<>(graph.getVertices()));
    }

    private void initDetectives(ScotlandYardGame game) {
        Set<Player> detectives = new HashSet<>();
        for (int i = 0; i < game.getDetectivesAmount(); i++) {
            detectives.add(new Detective(String.valueOf(i)));
        }
        game.setDetectives(detectives);
    }

    public void clear(ScotlandYardGame game) {
        game.setGraph(null);
        game.setDetectives(null);
        game.setMisterX(null);
        game.setPlayerVertexMap(null);
        game.setStations(null);
        game.setVertexPlayerMap(null);
    }

    private void setState(ScotlandYardGame game) {
        if (game.getDetectives().size() == 0) {
            game.setState(MISTER_X_VICTORY);
            return;
        }
        Map<Player, Vertex> playerVertexMap = game.getPlayerVertexMap();
        Vertex misterXStation = playerVertexMap.get(game.getMisterX());
        for (Map.Entry<Player, Vertex> playerVertexEntry : playerVertexMap.entrySet()) {
            Player currentPlayer = playerVertexEntry.getKey();
            Vertex currentVertex = playerVertexEntry.getValue();
            if (currentVertex.equals(misterXStation) && currentPlayer.getTypePlayer().equals(TypePlayer.DETECTIVE)) {
                game.setState(DETECTIVES_VICTORY);
                return;
            } else game.setState(PLAYING);
        }
    }

    private boolean hasMove(Player player, ScotlandYardGame game) {
        Detective detective = (Detective) player;
        Map<Player, Vertex> playerVertexMap = game.getPlayerVertexMap();
//        Map<TypeTicket, Integer> tickets = player.getTicketsMap();
        List<Ticket> tickets = detective.getTickets();
        return false;
    }

    public void play(ScotlandYardGame game) throws IOException {
        for (int i = game.getCurrentMove(); game.getCurrentMove() < game.getMoveAmount(); i++) {
            game.setCurrentMove(i);
            if (game.getCurrentMove() == 10) {
//                serializer.serialize(game, Defaults.FILE_PATH_GSON);
            }
            move(game);
            if (game.getState().equals(GameState.DETECTIVES_VICTORY)) {
                break;
            }
        }
        if (game.getState().equals(GameState.PLAYING)) {
            game.setState(GameState.MISTER_X_VICTORY);
            printer.printGameState(game);
        }
    }

    private void move(ScotlandYardGame game) {
        printer.printGameState(game);
        printer.printPlayers(game);
        printer.printLocations(game);
        printer.print("MOVE", ANSI_CYAN);
        misterXMove(game);
        detectivesMove(game);
        setState(game);
        if (game.getState().equals(DETECTIVES_VICTORY)) {
            printer.printGameState(game);
        }
    }

    private void detectivesMove(ScotlandYardGame game) {
        Set<Player> detectives = game.getDetectives();
        for (Player detective : detectives) {
            if (!detectiveService.hasTickets(detective)) continue;
            Ticket currentTicket = null;
            Vertex targetStation = null;
//            while (targetStation == null) {
            currentTicket = detectiveOnGameService.getCurrentTicket(detective);
            targetStation = detectiveOnGameService.setTargetStation(detective, game, currentTicket);
//            }
            if (targetStation == null) return;
//            Travel travel = getTravel(detective, targetStation, currentTicket, game);
//            game.getTravels().put(detective, travel);
            detectiveService.moveTo(detective, game, targetStation, currentTicket);
        }
    }

    private void misterXMove(ScotlandYardGame game) {
        Player misterX = game.getMisterX();
        if (!misterXService.hasTickets(misterX)) return;
        Ticket currentTicket = null;
        Vertex targetStation = null;
//        while (targetStation == null) {
        currentTicket = misterXOnGameService.getCurrentTicket(misterX);
        targetStation = misterXOnGameService.setTargetStation(misterX, game, currentTicket);
//        }
        if (targetStation == null) return;
//        Travel travel = getTravel(misterX, targetStation, currentTicket, game);
//        game.getTravels().put(misterX, travel);
        misterXService.moveTo(misterX, game, targetStation, currentTicket);
    }

    private Travel getTravel(Player player, Vertex targetStation, Ticket currentTicket, ScotlandYardGame game) {
        return new Travel(game.getPlayerVertexMap().get(player), targetStation, currentTicket);
    }

    public void setStartStations(ScotlandYardGame game) {
        int board = game.getStations().size();
        List<Vertex> stations = game.getStations();
        Collections.shuffle(stations);
        Set<Player> detectives = game.getDetectives();
        MisterX misterX = (MisterX) game.getMisterX();
        Vertex misterXStartStation = stations.get((int) (Math.random() * board));
        setStartStation(misterX, misterXStartStation, game);
        board--;
        stations.removeIf(station -> station.equals(misterXStartStation));
        for (Player detective : detectives) {
            Vertex startStation = stations.get((int) (Math.random() * board));
            setStartStation(detective, startStation, game);
            board--;
            stations.removeIf(station -> station.equals(startStation));
//        Vertex start = graphService.getVertex(game.getGraph(), 7);
//        setStartStation(detective, start, game);
        }
    }

    private void setLocationMaps(ScotlandYardGame game) {
        game.setPlayerVertexMap(new HashMap<>());
        game.setVertexPlayerMap(new HashMap<>());
    }

    private void setStartStation(Player player, Vertex startStation, ScotlandYardGame game) {
        Map<Player, Vertex> playerVertexMap = game.getPlayerVertexMap();
        Map<Vertex, Player> vertexPlayerMap = game.getVertexPlayerMap();
        playerVertexMap.put(player, startStation);
        vertexPlayerMap.put(startStation, player);
    }

    //оставить здесь
    public boolean isFreeStation(Vertex station, ScotlandYardGame game) {
        return game.getVertexPlayerMap().get(station) == null;
    }

    public void ticketsDistribution(ScotlandYardGame game) {
        MisterX misterX = (MisterX) game.getMisterX();
        Set<Player> detectives = game.getDetectives();
        giveTicketsToMisterX(misterX, game);
        for (Player player : detectives) {
            giveTicketsToDetective((Detective) player);
        }
    }

    private void giveTickets(List<Ticket> tickets, Map<TypeTicket, Integer> ticketsMap, TypeTicket typeTicket, Integer ticketsAmount) {
        for (int i = 0; i < ticketsAmount; i++) {
            tickets.add(new Ticket(typeTicket));
            ticketsMap.put(typeTicket, ticketsMap.get(typeTicket) == null ? 1 : ticketsMap.get(typeTicket) + 1);
        }
    }

    private void giveTicketsToMisterX(MisterX misterX, ScotlandYardGame game) {
        List<Ticket> misterXTickets = misterX.getTickets();
        Map<TypeTicket, Integer> ticketsMap = misterX.getTicketsMap();
        Set<Player> detectives = game.getDetectives();
        giveTickets(misterXTickets, ticketsMap, TypeTicket.TAXI, misterX.getMaxAmountTaxiTickets());
        giveTickets(misterXTickets, ticketsMap, TypeTicket.BUS, misterX.getMaxAmountBusTickets());
        giveTickets(misterXTickets, ticketsMap, TypeTicket.METRO, misterX.getMaxAmountMetroTickets());     //нужно улучшить
        giveTickets(misterXTickets, ticketsMap, TypeTicket.DOUBLE, misterX.getMaxAmountDoubleTickets());
        giveTickets(misterXTickets, ticketsMap, TypeTicket.BLACK, detectives.size());
        misterXOnGameService.shuffleTickets(misterX);
    }

    private void giveTicketsToDetective(Detective detective) {
        List<Ticket> detectiveTickets = detective.getTickets();
        Map<TypeTicket, Integer> ticketsMap = detective.getTicketsMap();
        giveTickets(detectiveTickets, ticketsMap, TypeTicket.BUS, detective.getMaxAmountBusTickets());
        giveTickets(detectiveTickets, ticketsMap, TypeTicket.TAXI, detective.getMaxAmountTaxiTickets());
        giveTickets(detectiveTickets, ticketsMap, TypeTicket.METRO, detective.getMaxAmountMetroTickets());
        detectiveOnGameService.shuffleTickets(detective);
    }


}
