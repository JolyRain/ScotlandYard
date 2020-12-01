import console.Printer;
import drawer.World;
import frame.App;
import frame.AppService;
import frame.FileManager;
import game.GameState;
import game.ScotlandYardGame;
import graph.Graph;
import map.GameMap;
import services.gameServices.GameService;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        GameService gameService = new GameService();
        Printer printer = new Printer();
        ScotlandYardGame game = new ScotlandYardGame(22, 1);
        AppService appService = new AppService();
        GameMap gameMap = new GameMap(new LinkedList<>(), new LinkedList<>(), new Graph());
        World world = new World(game, gameMap);
        App app = new App(new FileManager(), world);

        appService.initApp(app);
        gameService.initGraph(gameMap, game);
        gameService.initGame(game);
        appService.show(app);
//        Serializator serializator = new Serializator();
        try {
            for (int i = 0; i < game.getMoveAmount(); i++) {
                Thread.sleep(3000);
                gameService.play(game);
                appService.repaint(app);
                if (game.getState().equals(GameState.DETECTIVES_VICTORY)) {
                    break;
                }
            }
//            serializator.toJSON(game);
            if (game.getState().equals(GameState.PLAYING)) {
                game.setState(GameState.MISTER_X_VICTORY);
                printer.printGameState(game);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}