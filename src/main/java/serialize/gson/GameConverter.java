package serialize.gson;

import com.fasterxml.jackson.databind.type.PlaceholderForType;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import game.GameState;
import game.ScotlandYardGame;
import game.TypePlayer;
import graph.Graph;
import graph.Vertex;
import players.Player;
import services.gameServices.GameService;
import services.graphServices.GraphService;

import java.lang.reflect.Type;
import java.util.*;

public class GameConverter implements JsonDeserializer<ScotlandYardGame> {
    private GraphConverter graphConverter = new GraphConverter();
    private PlayerConverter playerConverter = new PlayerConverter();
    private GraphService graphService = new GraphService();
    private GameService gameService = new GameService();

    @Override
    public ScotlandYardGame deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        ScotlandYardGame game = new ScotlandYardGame();

//        Map<TypeTicket, TypeRoad> ticketRoadMap = context.deserialize(object.get("TICKET_ROAD_MAP"), Map.class);
//        Map<TypeRoad, TypeTicket> roadTicketMap = context.deserialize(object.get("ROAD_TICKET_MAP"), Map.class);

        Integer moveAmount = context.deserialize(object.get("moveAmount"), Integer.class);
        Integer currentMove = context.deserialize(object.get("currentMove"), Integer.class);
        Integer detectivesAmount = context.deserialize(object.get("detectivesAmount"), Integer.class);
        GameState gameState = context.deserialize(object.get("gameState"), GameState.class);
        game.setMoveAmount(moveAmount);
        game.setCurrentMove(currentMove);
        game.setDetectivesAmount(detectivesAmount);
        game.setGameState(gameState);

        Graph graph = graphConverter.deserialize(object.get("graph"), Graph.class, context);
        game.setGraph(graph);

        Set<Player> detectives = new HashSet<>();
        JsonArray jsDetectives = object.getAsJsonArray("detectives");
        for (JsonElement playerElement : jsDetectives) {
            Player detective = playerConverter.deserialize(playerElement, Player.class,context);
            detectives.add(detective);
        }
        game.setDetectives(detectives);

        Player misterX = playerConverter.deserialize(object.get("misterX"), Player.class, context);
        game.setMisterX(misterX);

        List<Vertex> stations = new LinkedList<>(graph.getVertices());
        game.setStations(stations);

        Type typeToken = new TypeToken<Map<Vertex, Player>>() {}.getType();
        Map<Vertex, Player> vertexPlayerMap = context.deserialize(object.get("vertexPlayerMap"), typeToken);
        Map<Vertex, Player> newVertexPlayerMap = new HashMap<>();
        Map<Player, Vertex> playerVertexMap = new HashMap<>();

        for (Map.Entry<Vertex, Player> entry : vertexPlayerMap.entrySet()) {
            Player entryPlayer = entry.getValue();
            Vertex entryVertex = entry.getKey();

            Player gamePlayer = game.getPlayer(entryPlayer);
            Vertex gameVertex = graphService.getVertex(graph, entryVertex);
            newVertexPlayerMap.put(gameVertex, gamePlayer);
            playerVertexMap.put(gamePlayer, gameVertex);
        }
        game.setVertexPlayerMap(newVertexPlayerMap);
        game.setPlayerVertexMap(playerVertexMap);

        return game;
    }
}
