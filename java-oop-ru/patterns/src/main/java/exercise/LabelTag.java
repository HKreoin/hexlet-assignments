package exercise;

// BEGIN
public class LabelTag implements TagInterface {
    private final String label;
    private final TagInterface tag;

    public LabelTag(String label, TagInterface tag) {
        this.label= label;
        this.tag = tag;
    }

    public String render() {
        return "<label>" + label + tag.render() + "</label>";
    }
}
// END
