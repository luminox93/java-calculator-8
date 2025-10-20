package calculator.domain;

import java.util.List;

public class DelimiterHandler {
    private static final String CUSTOM_DELIMITER_SUFFIX = "\n";
    private static final String DEFAULT_DELIMITERS = ",|:";

    private final DelimiterExtractor delimiterExtractor;

    public DelimiterHandler() {
        this.delimiterExtractor = new DelimiterExtractor();
    }

    public List<Integer> parseInput(String input, NumberParser numberParser) {
        return numberParser.parse(extractNumbers(input), extractDelimiterPattern(input));
    }

    public String extractDelimiterPattern(String input) {
        return toRegexPattern(delimiterExtractor.extract(input));
    }

    public String extractNumbers(String input) {
        return input.contains(CUSTOM_DELIMITER_SUFFIX)
            ? input.substring(input.indexOf(CUSTOM_DELIMITER_SUFFIX) + 1)
            : input;
    }

    private String toRegexPattern(String delimiter) {
        if (delimiter.equals(DEFAULT_DELIMITERS)) return delimiter;

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < delimiter.length(); i++) {
            if (i > 0) result.append("|");
            result.append(escapeRegexCharacter(String.valueOf(delimiter.charAt(i))));
        }
        return result.toString();
    }

    private String escapeRegexCharacter(String ch) {
        return ch.replaceAll("([\\\\+*?\\[\\](){}|^$.\\-])", "\\\\$1");
    }
}
