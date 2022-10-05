package simulation2021.dto;

import org.json.JSONObject;

public class Location {

    private final Long id;
    private final Integer locatedBikesCount;

    private Location(Long id, Integer locatedBikesCount) {
        this.id = id;
        this.locatedBikesCount = locatedBikesCount;
    }

    public static Location from(JSONObject jsonObject) {
        return new Location(
                jsonObject.getLong("id"),
                jsonObject.getInt("located_bikes_count")
        );
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", locatedBikesCount=" + locatedBikesCount +
                '}';
    }
}
