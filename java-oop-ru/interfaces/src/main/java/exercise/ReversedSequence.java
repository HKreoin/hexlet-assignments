package exercise;

// BEGIN
public class ReversedSequence implements CharSequence{
    private final String text;
    private final String reversedText;

    public ReversedSequence(String text) {
        this.text = text;
        var strBuilder = new StringBuilder(text);
        this.reversedText = strBuilder.reverse().toString();
    }

    @Override
    public int length() {
        return text.length();
    }

    @Override
    public char charAt(int index) {
        return reversedText.charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return reversedText.subSequence(start, end);
    }

    @Override
    public String toString() {
        return reversedText;
    }
}
// END
