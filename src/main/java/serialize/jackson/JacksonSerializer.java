package serialize.jackson;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.module.SimpleModule;
import game.ScotlandYardGame;
import graph.Vertex;
import serialize.ISerializer;
import utils.Defaults;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class JacksonSerializer implements ISerializer {


    @Override
    public void serialize(ScotlandYardGame game, String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
        writer.writeValue(new File(Defaults.FILE_PATH_JACKSON), game);
    }

    @Override
    public ScotlandYardGame deserialize(String fileName) throws FileNotFoundException {
        return null;
    }


}
