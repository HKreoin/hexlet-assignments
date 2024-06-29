package exercise;

import lombok.SneakyThrows;
import lombok.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

// BEGIN
@Value
// END
public class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    @SneakyThrows
    public String serialize() {
        var mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
    @SneakyThrows
    public static Car unserialize(String json) {
        var mapper = new ObjectMapper();
        return mapper.readValue(json, Car.class);
    }
    // END
}
