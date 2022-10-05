package simulation2021.dto;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Command {

    private final Long truckId;
    private final List<Integer> commands = new ArrayList<>();

    private Command(Long truckId, List<Integer> commands) {
        this.truckId = truckId;
        this.commands.addAll(commands);
    }

    public static Command of(Long truckId, List<Integer> commands) {
        return new Command(truckId, commands);
    }

    public JSONObject convertToJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("truck_id", truckId);
        if (commands != null) {
            jsonObject.put("command", commands);
        }
        return jsonObject;
    }

    @Override
    public String toString() {
        return "Command{" +
                "truckId=" + truckId +
                ", commands=" + commands +
                '}';
    }
}
