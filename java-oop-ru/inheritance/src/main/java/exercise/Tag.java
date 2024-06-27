package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public class Tag {
    private String name;
    private Map<String, String> attributes;

    public Tag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public String getName() {
        return name;
    }

    public String getAttrsString() {
        var builder = new StringBuilder();
        for (var att : getAttributes().entrySet()) {
            builder.append(String.format(" %s=\"%s\"", att.getKey(), att.getValue()));
        }
        return "<" + getName() + builder + ">";
    }
}
// END
