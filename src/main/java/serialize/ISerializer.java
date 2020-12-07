package serialize;

import game.ScotlandYardGame;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ISerializer {

    void serialize(ScotlandYardGame game, String fileName) throws IOException;

    ScotlandYardGame deserialize(String fileName) throws FileNotFoundException;

}
