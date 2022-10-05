package simulation2021;

import simulation2021.dto.Command;
import simulation2021.dto.Location;
import simulation2021.dto.Simulation;
import simulation2021.dto.Truck;
import simulation2021.httpclient.HttpClient;
import simulation2021.httpclient.HttpConst;
import simulation2021.util.JSONParser;

import java.util.List;
import java.util.logging.Logger;

public class Main {

    private static Logger logger = Logger.getLogger(Main.class.getName());

    public static final String X_AUTH_TOKEN = "4c0d490dee592685fe0f791ba99b322f";

    public static void main(String[] args) {
        HttpClient httpClient = new HttpClient(HttpConst.BASE_URL);
        String authToken = httpClient.getAuthKey(X_AUTH_TOKEN, 1L);

        List<Location> location = JSONParser.getLocations(httpClient.getLocations(authToken));
        logger.info(location.toString());

        List<Truck> trucks = JSONParser.getTrucks(httpClient.getTrucks(authToken));
        logger.info(trucks.toString());

        List<Command> commands = List.of(Command.of(1L, List.of(1, 2)));
        Simulation simulation = JSONParser.getSimulation(httpClient.simulate(authToken, commands));
        logger.info(simulation.toString());

        Double score = JSONParser.getScore(httpClient.getScore(authToken));
        logger.info(score.toString());
    }
}