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
        try {
            JSONArray locationsArray = jsonObject.getJSONArray("locations");
            locationsArray.forEach(json -> locations.add(Location.from((JSONObject) json)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return locations;
    }

    public static List<Truck> getTrucks(JSONObject jsonObject) {
        List<Truck> trucks = new ArrayList<>();
        try {
            JSONArray trucksArray = jsonObject.getJSONArray("trucks");
            trucksArray.forEach(json -> trucks.add(Truck.from((JSONObject) json)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trucks;
    }

    public static Simulation getSimulation(JSONObject jsonObject) {
        Simulation simulation = null;
        try {
            simulation = Simulation.from(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return simulation;
    }

    public static Double getScore(JSONObject jsonObject) {
        Double score = 0.0;
        try {
            score = jsonObject.getDouble("score");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return score;
    }
}
