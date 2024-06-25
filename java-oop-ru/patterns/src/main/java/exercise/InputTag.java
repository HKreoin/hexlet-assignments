package exercise;

// BEGIN
public class InputTag implements TagInterface {
    private final String inputType;
    private final String value;

    public InputTag(String inputType, String value) {
        this.inputType = inputType;
        this.value = value;
    }

    public String render() {
        return "<input type=\"" + inputType + "\" value=\"" + value + "\">";
    }
}
// END
