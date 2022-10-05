package simulation2021.util;

import org.json.JSONArray;
import org.json.JSONObject;
import simulation2021.dto.Location;
import simulation2021.dto.Simulation;
import simulation2021.dto.Truck;

import java.util.ArrayList;
import java.util.List;

public class JSONParser {

    public static List<Location> getLocations(JSONObject jsonObject) {
        List<Location> locations = new ArrayList<>();
        JSONArray locationsArray = jsonObject.getJSONArray("locations");
        locationsArray.forEach(json -> locations.add(Location.from((JSONObject) json)));

        return locations;
    }

    public static List<Truck> getTrucks(JSONObject jsonObject) {
        List<Truck> trucks = new ArrayList<>();
        JSONArray trucksArray = jsonObject.getJSONArray("trucks");
        trucksArray.forEach(json -> trucks.add(Truck.from((JSONObject) json)));

        return trucks;
    }

    public static Simulation getSimulation(JSONObject jsonObject) {
        return Simulation.from(jsonObject);
    }

    public static Double getScore(JSONObject jsonObject) {
        return jsonObject.getDouble("score");
    }
}
