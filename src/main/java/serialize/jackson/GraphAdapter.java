package serialize.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import graph.Graph;

import java.io.IOException;

public class GraphAdapter extends JsonSerializer<Graph> {
    @Override
    public void serialize(Graph graph, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

    }
}
