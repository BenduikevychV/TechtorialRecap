package utilities;

import java.util.HashMap;
import java.util.Map;

public class DataStorage {

    //               apiName ,   responseBody
    public static Map<String, Map<String, Object>> jsonPayloads = new HashMap<>();

    private static DataStorage dataStorage;

    private DataStorage() {
    };

    public static DataStorage getInstance() {
        if (dataStorage == null) {
            dataStorage = new DataStorage();
        }

        return dataStorage;
    }

    public static Object getValue(String apiName, String key) {
        return jsonPayloads.get(apiName).get(key);
    }
}
