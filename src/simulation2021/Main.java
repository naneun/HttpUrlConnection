package simulation2021;

import simulation2021.dto.Command;
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
        logger.info(JSONParser.getLocations(httpClient.getLocations(authToken)).toString());
        logger.info(JSONParser.getTrucks(httpClient.getTrucks(authToken)).toString());

        List<Command> commands = List.of(Command.of(1L, List.of(1, 2)));
        logger.info(JSONParser.getSimulation(httpClient.simulate(authToken, commands)).toString());

        logger.info(JSONParser.getScore(httpClient.getScore(authToken)).toString());
    }
}