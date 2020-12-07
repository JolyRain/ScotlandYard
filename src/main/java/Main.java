import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import drawer.World;
import frame.App;
import frame.AppService;
import frame.FileManager;
import game.ScotlandYardGame;
import graph.Graph;
import map.GameMap;
import serialize.gson.GsonSerializer;
import services.gameServices.GameService;
import utils.Defaults;

import java.io.IOException;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        GsonSerializer gsonSerializer = new GsonSerializer();
        GameService gameService = new GameService();
//        ScotlandYardGame game = new ScotlandYardGame(22, 1);
        ScotlandYardGame game = gsonSerializer.deserialize(Defaults.FILE_PATH_GSON);

        AppService appService = new AppService();
        GameMap gameMap = new GameMap(new LinkedList<>(), new LinkedList<>(), new Graph());
        World world = new World(game, gameMap);
        App app = new App(new FileManager(), world);
        appService.initApp(app);
//        gameService.initGraph(gameMap, game);
//        gameService.initGame(game);
//        appService.show(app);
        gameService.play(game);

    }
}