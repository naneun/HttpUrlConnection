package simulation2021.httpclient;

public class HttpConst {

    // Base URL
    public static final String BASE_URL = "https://kox947ka1a.execute-api.ap-northeast-2.amazonaws.com/prod/users";

    // Header Value
    public static final String APPLICATION_JSON = "application/json";

    // Path
    public static final String START_PATH = "/start";
    public static final String LOCATIONS_PATH = "/locations";
    public static final String TRUCKS_PATH = "/trucks";
    public static final String SIMULATE_PATH = "/simulate";
    public static final String SCORE_PATH = "/score";
    public static final String QUERY_DELIMITER = "?";

    // Parameter
    public static final String AUTH_KEY = "auth_key";

    // Response Code
    public static final int OK = 200;
    public static final int BAD_REQUEST = 400;
    public static final int UNAUTHORIZED = 401;
    public static final int NOT_FOUND = 404;
    public static final int INTERNAL_SERVER_ERROR = 500;
}
