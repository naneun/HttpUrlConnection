package simulation2021.dto;

import org.json.JSONObject;

public class Simulation {

    private final String status;
    private final Integer time;
    private final Double failedRequestsCount;
    private final Double distance;

    private Simulation(String status, Integer time, Double failedRequestsCount, Double distance) {
        this.status = status;
        this.time = time;
        this.failedRequestsCount = failedRequestsCount;
        this.distance = distance;
    }

    public static Simulation from(JSONObject jsonObject) {
        return new Simulation(
                jsonObject.getString("status"),
                jsonObject.getInt("time"),
                jsonObject.getDouble("failed_requests_count"),
                jsonObject.getDouble("distance")
        );
    }

    @Override
    public String toString() {
        return "Simulation{" +
                "status='" + status + '\'' +
                ", time=" + time +
                ", failedRequestsCount=" + failedRequestsCount +
                ", distance=" + distance +
                '}';
    }
}
