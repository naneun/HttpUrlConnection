package simulation2021.dto;

import org.json.JSONObject;

public class Truck {

    private final Long id;
    private final Long locationId;
    private final Integer loadedBikesCount;

    private Truck(Long id, Long locationId, Integer loadedBikesCount) {
        this.id = id;
        this.locationId = locationId;
        this.loadedBikesCount = loadedBikesCount;
    }

    public static Truck from(JSONObject jsonObject) {
        return new Truck(
                jsonObject.getLong("id"),
                jsonObject.getLong("location_id"),
                jsonObject.getInt("loaded_bikes_count")
        );
    }
}
