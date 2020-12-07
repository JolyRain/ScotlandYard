package serialize.gson;

import com.google.gson.*;
import game.TypePlayer;
import players.Detective;
import players.MisterX;
import players.Player;

import java.lang.reflect.Type;

public class PlayerConverter implements JsonDeserializer<Player>, JsonSerializer<Player> {

    private static final String CLASS_NAME = "PLAYER";

    @Override
    public Player deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonElement playerElement = jsonElement.getAsJsonObject().get(CLASS_NAME);
        JsonObject object = playerElement.getAsJsonObject();
        TypePlayer typePlayer = context.deserialize(object.get("typePlayer"), TypePlayer.class);
        switch (typePlayer) {
            case MISTER_X:
                return context.deserialize(playerElement, MisterX.class);
            case DETECTIVE:
                return context.deserialize(playerElement, Detective.class);
        }
        return null;
    }

    @Override
    public JsonElement serialize(Player player, Type type, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        switch (player.getTypePlayer()) {
            case MISTER_X:
                MisterX misterX = (MisterX) player;
                JsonElement element = context.serialize(misterX);
                result.add(CLASS_NAME, element);
                break;
            case DETECTIVE:
                Detective detective = (Detective) player;
                JsonElement element1 =  context.serialize(detective);
                result.add(CLASS_NAME, element1);
                break;
        }
        return result;
    }
}
