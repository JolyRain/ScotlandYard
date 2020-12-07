package serialize.gson;

import com.google.gson.*;
import players.Detective;

import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DetectivesSquadConverter implements JsonDeserializer<Set<Detective>> {
    @Override
    public Set<Detective> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        Set<Detective> detectives = new HashSet<>();
        JsonObject object = jsonElement.getAsJsonObject();
        for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
            Detective detective = context.deserialize(entry.getValue(), Detective.class);
        }
        return null;
    }
}
