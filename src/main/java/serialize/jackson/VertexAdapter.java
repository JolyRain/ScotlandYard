package serialize.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import graph.Vertex;

import java.io.IOException;

public class VertexAdapter extends JsonSerializer<Vertex> {
    @Override
    public void serialize(Vertex vertex, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("stationNumber", vertex.getStationNumber());
        jsonGenerator.writeEndObject();
    }
}
