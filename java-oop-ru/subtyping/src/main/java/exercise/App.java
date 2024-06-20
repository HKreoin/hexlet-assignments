package exercise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
public class App {
    public static void swapKeyValue(KeyValueStorage storage) {
        var map = storage.toMap();
        for(var entry : map.entrySet()) {
            var key = entry.getKey();
            var value = entry.getValue();
            storage.unset(key);
            storage.set(value, key);
        }
    }
}
// END
