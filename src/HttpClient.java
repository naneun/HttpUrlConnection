import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {

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

            JSONObject json = readMessage(connection);
            authKey = json.getString(HttpConst.AUTH_KEY);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return authKey;
    }

    // 2021
    public JSONObject getLocations(String authKey) {

        HttpURLConnection connection = null;
        JSONObject jsonObject = null;

        try {
            URL url = new URL(baseUrl + HttpConst.LOCATIONS_PATH);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(HttpMethod.GET);
            connection.setRequestProperty(HttpHeader.AUTHORIZATION, authKey);
            connection.setRequestProperty(HttpHeader.CONTENT_TYPE, HttpConst.APPLICATION_JSON);

            jsonObject = readMessage(connection);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return jsonObject;
    }

    public JSONObject getTrucks(String authKey) {

        HttpURLConnection connection = null;
        JSONObject jsonObject = null;

        try {
            URL url = new URL(baseUrl + HttpConst.TRUCKS_PATH);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(HttpMethod.GET);
            connection.setRequestProperty(HttpHeader.AUTHORIZATION, authKey);
            connection.setRequestProperty(HttpHeader.CONTENT_TYPE, HttpConst.APPLICATION_JSON);

            jsonObject = readMessage(connection);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return jsonObject;
    }

    public JSONObject simulate(String authKey) {

        HttpURLConnection connection = null;
        JSONObject jsonObject = null;

        try {
            URL url = new URL(baseUrl + HttpConst.SIMULATE_PATH);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(HttpMethod.GET);
            connection.setRequestProperty(HttpHeader.AUTHORIZATION, authKey);
            connection.setRequestProperty(HttpHeader.CONTENT_TYPE, HttpConst.APPLICATION_JSON);
            connection.setDoInput(true);
            connection.setDoOutput(true);

            jsonObject = readMessage(connection);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return jsonObject;
    }

    public JSONObject getScore(String authKey) {

        HttpURLConnection connection = null;
        JSONObject jsonObject = null;

        try {
            URL url = new URL(baseUrl + HttpConst.SCORE_PATH);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(HttpMethod.GET);
            connection.setRequestProperty(HttpHeader.AUTHORIZATION, authKey);
            connection.setRequestProperty(HttpHeader.CONTENT_TYPE, HttpConst.APPLICATION_JSON);

            jsonObject = readMessage(connection);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return jsonObject;
    }

    // 2022
}
