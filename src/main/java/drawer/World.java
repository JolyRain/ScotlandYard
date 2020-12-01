package drawer;

import game.ScotlandYardGame;
import map.GameMap;

import java.io.Serializable;

public class World implements Serializable {
    private ScotlandYardGame game;
    private GameMap map;

    public World(ScotlandYardGame game, GameMap map) {
        this.game = game;
        this.map = map;
    }

    public ScotlandYardGame getGame() {
        return game;
    }

    public void setGame(ScotlandYardGame game) {
        this.game = game;
    }

    public GameMap getMap() {
        return map;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }
}
