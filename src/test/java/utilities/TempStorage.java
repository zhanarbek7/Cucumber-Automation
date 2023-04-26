package utilities;

import java.util.HashMap;
import java.util.Map;

public class TempStorage {

    private static Map<String, String> storage = new HashMap<>();

    public static void addData(String key, String value) {
        storage.put(key, value);
    }

    public static String getData(String key){
        return storage.get(key);
    }

}
