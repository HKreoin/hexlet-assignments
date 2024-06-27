package exercise;

import java.util.*;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag{
    private String body;
    private List<Tag> children;

    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> children) {
        super(name, attributes);
        this.body = body;
        this.children = children;
    }

    public String childrenToString() {
        return children.stream().map(Tag::toString).collect(Collectors.joining());
    }

    @Override
    public String toString() {
        return getAttrsString() + body + childrenToString() + "</" + getName() + ">";
    }
}
// END
