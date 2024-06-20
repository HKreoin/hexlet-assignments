package exercise;

import java.util.HashMap;
import java.util.Map;

import static exercise.Utils.*;

// BEGIN
public class FileKV implements KeyValueStorage {
    private String path;
    private Map<String, String> map;

    public FileKV(String path, Map<String, String> map) {
        this.path = path;
        this.map = map;
        write();
    }

    @Override
    public void set(String key, String value) {
        map = toMap();
        map.put(key, value);
        write();
    }

    @Override
    public void unset(String key) {
        map = toMap();
        map.remove(key);
        write();
    }

    @Override
    public String get(String key, String defaultValue) {
        map = toMap();
        return map.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(unserialize(readFile(path)));
    }

    public void write() {
        writeFile(path, serialize(map));
    }
}
// END
