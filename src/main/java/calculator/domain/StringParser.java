package calculator.domain;

import java.util.List;

public class StringParser {
    private final DelimiterHandler delimiterHandler;
    private final NumberParser numberParser;

    public StringParser() {
        this.delimiterHandler = new DelimiterHandler();
        this.numberParser = new NumberParser();
    }

    public List<Integer> parse(String input) {
        if (input == null) throw new IllegalArgumentException(ErrorMessages.NULL_INPUT);
        if (input.isEmpty()) return List.of();

        return delimiterHandler.parseInput(input.replace("\\n", "\n"), numberParser);
    }
}
