package simulation2021.httpclient;

import org.json.JSONArray;
import org.json.JSONObject;
import simulation2021.dto.Command;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.logging.Logger;

public class HttpClient {

    private static Logger logger = Logger.getLogger(HttpClient.class.getName());

    private final String baseUrl;

    public HttpClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    private JSONObject readMessage(HttpURLConnection connection) {

        JSONObject jsonObject = null;

        int responseCode = -1;
        try {
            responseCode = connection.getResponseCode();
            if (responseCode == HttpConst.OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line = "";
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                jsonObject = new JSONObject(sb.toString());
            }
            else if (responseCode == HttpConst.BAD_REQUEST) {
                throw new RuntimeException("BAD_REQUEST");
            }
            else if (responseCode == HttpConst.UNAUTHORIZED) {
                throw new RuntimeException("UNAUTHORIZED");
            }
            else if (responseCode == HttpConst.NOT_FOUND) {
                throw new RuntimeException("NOT_FOUND");
            }
            else if (responseCode == HttpConst.INTERNAL_SERVER_ERROR) {
                throw new RuntimeException("INTERNAL_SERVER_ERROR");
            }
            else {
                throw new RuntimeException("UNKNOWN");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return jsonObject;
    }

    public String getAuthKey(String xAuthToken, Long problemId) {

        HttpURLConnection connection = null;
        String queryParams = "problem=" + problemId;
        String authKey = null;

        try {
            URL url = new URL(baseUrl + HttpConst.START_PATH + HttpConst.QUERY_DELIMITER + queryParams);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(HttpMethod.POST);
            connection.setRequestProperty(HttpHeader.X_AUTH_TOKEN, xAuthToken);
            connection.setRequestProperty(HttpHeader.CONTENT_TYPE, HttpConst.APPLICATION_JSON);

            JSONObject responseJson = readMessage(connection);
            authKey = responseJson.getString(HttpConst.AUTH_KEY);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return authKey;
    }

    public JSONObject getLocations(String authKey) {

        HttpURLConnection connection = null;
        JSONObject responseJson = null;

        try {
            URL url = new URL(baseUrl + HttpConst.LOCATIONS_PATH);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(HttpMethod.GET);
            connection.setRequestProperty(HttpHeader.AUTHORIZATION, authKey);
            connection.setRequestProperty(HttpHeader.CONTENT_TYPE, HttpConst.APPLICATION_JSON);

            responseJson = readMessage(connection);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseJson;
    }

    public JSONObject getTrucks(String authKey) {

        HttpURLConnection connection = null;
        JSONObject responseJson = null;

        try {
            URL url = new URL(baseUrl + HttpConst.TRUCKS_PATH);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(HttpMethod.GET);
            connection.setRequestProperty(HttpHeader.AUTHORIZATION, authKey);
            connection.setRequestProperty(HttpHeader.CONTENT_TYPE, HttpConst.APPLICATION_JSON);

            responseJson = readMessage(connection);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseJson;
    }

    public JSONObject simulate(String authKey, List<Command> commands) {

        HttpURLConnection connection = null;
        JSONObject responseJson = null;

        try {
            URL url = new URL(baseUrl + HttpConst.SIMULATE_PATH);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(HttpMethod.PUT);
            connection.setRequestProperty(HttpHeader.AUTHORIZATION, authKey);
            connection.setRequestProperty(HttpHeader.CONTENT_TYPE, HttpConst.APPLICATION_JSON);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            // Write
            BufferedWriter bw = new BufferedWriter((new OutputStreamWriter(connection.getOutputStream())));
            JSONObject requestJson = new JSONObject();

            JSONArray commandArray = new JSONArray();
            commands.forEach(command -> commandArray.put(command.convertToJSONObject()));
            requestJson.put("commands", commandArray);

            bw.write(requestJson.toString());
            bw.flush();

            responseJson = readMessage(connection);

            logger.info(responseJson.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseJson;
    }

    public JSONObject getScore(String authKey) {

        HttpURLConnection connection = null;
        JSONObject responseJson = null;

        try {
            URL url = new URL(baseUrl + HttpConst.SCORE_PATH);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(HttpMethod.GET);
            connection.setRequestProperty(HttpHeader.AUTHORIZATION, authKey);
            connection.setRequestProperty(HttpHeader.CONTENT_TYPE, HttpConst.APPLICATION_JSON);

            responseJson = readMessage(connection);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseJson;
    }
}
