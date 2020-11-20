import frame.App;
import frame.AppService;
import frame.FileManager;
import game.ScotlandYardGame;
import graph.Graph;
import map.GameMap;
import services.gameServices.GameService;

import javax.swing.*;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        GameService gameService = new GameService();
        ScotlandYardGame game = new ScotlandYardGame(22, 1);
        AppService appService = new AppService();
        GameMap gameMap = new GameMap(new LinkedList<>(), new LinkedList<>(), new Graph());
        App app  = new App(new FileManager(), gameMap);

        appService.initApp(app);
        gameService.initGraph(gameMap, game);
        gameService.initGame(game);
        appService.show(app);
        gameService.play(game);
    }
}
//        gameService.initGraph(gameMap, game);
//        appService.initApp(app);
//        appService.show(app);
//        gameService.initGame(game);
//        gameService.play(game);