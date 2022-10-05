package simulation2021;

import org.json.JSONObject;

public class Simulation {

    private final String status;
    private final Integer time;
    private final Integer failedRequestsCount;
    private final Double distance;

    private Simulation(String status, Integer time, Integer failedRequestsCount, Double distance) {
        this.status = status;
        this.time = time;
        this.failedRequestsCount = failedRequestsCount;
        this.distance = distance;
    }

    public static Simulation from(JSONObject jsonObject) {
        return new Simulation(
                jsonObject.getString("status"),
                jsonObject.getInt("time"),
                jsonObject.getInt("failed_request_count"),
                jsonObject.getDouble("distance")
        );
    }
}
