package simulation2021;

import simulation2021.httpclient.HttpClient;
import simulation2021.httpclient.HttpConst;

public class Main {

    public static final String X_AUTH_TOKEN = "b962488a874839ea30db93b7d096edaf";

    public static void main(String[] args) {
        HttpClient httpClient = new HttpClient(HttpConst.BASE_URL);
        String authToken = httpClient.getAuthKey(X_AUTH_TOKEN, 1L);
        System.out.println(httpClient.getLocations(authToken));
        System.out.println(httpClient.getTrucks(authToken));
        System.out.println(httpClient.getScore(authToken));
    }
}