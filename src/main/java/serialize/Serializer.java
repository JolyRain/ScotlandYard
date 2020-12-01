package serialize;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import game.ScotlandYardGame;

import java.io.File;
import java.io.IOException;

public class Serializer {

    public Serializer() {

    }
    public void toJSON(ScotlandYardGame game) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("./src/main/java/files/game.json"), game);
    }

    public ScotlandYardGame toJavaObject() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper.readValue(new File("./src/main/java/files/game.json"), ScotlandYardGame.class);
    }
}