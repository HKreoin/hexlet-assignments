package exercise;

import lombok.SneakyThrows;

import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
public class App {
    public static void main(String[] args) {

    }
    @SneakyThrows
    public static void save(Path path, Car car) {
        Files.writeString(path, car.serialize(), StandardOpenOption.WRITE);
    }
    @SneakyThrows
    public static Car extract(Path path) {
        String json = Files.readString(path);
        return Car.unserialize(json);
    }
}
// END
