package serialize.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import game.ScotlandYardGame;
import graph.Edge;
import graph.Graph;
import graph.Vertex;
import players.Player;
import serialize.ISerializer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GsonSerializer implements ISerializer {

    public GsonSerializer() {
    }

    public void serialize(ScotlandYardGame game, String fileName) throws IOException {
        Gson gson = initGSon();
        FileWriter fileWriter = new FileWriter(fileName);
        gson.toJson(game, fileWriter);
        fileWriter.close();
    }

    public ScotlandYardGame deserialize(String fileName) throws FileNotFoundException {
        Gson gson = initGSon();
        FileReader fileReader = new FileReader(fileName);
        JsonReader jsonReader = new JsonReader(fileReader);
        return gson.fromJson(jsonReader, ScotlandYardGame.class);
    }

    private Gson initGSon() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Vertex.class, new VertexConverter())
                .registerTypeAdapter(Edge.class, new EdgeConverter())
                .registerTypeAdapter(Player.class, new PlayerConverter())
                .registerTypeAdapter(Graph.class, new GraphConverter())
                .registerTypeAdapter(ScotlandYardGame.class, new GameConverter())
                .enableComplexMapKeySerialization()
                .create();
    }
}
