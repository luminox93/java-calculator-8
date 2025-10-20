package calculator.domain;

public class DelimiterExtractor {
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String CUSTOM_DELIMITER_SUFFIX = "\n";
    private static final String DEFAULT_DELIMITERS = ",|:";

    public String extract(String input) {
        if (input == null) throw new IllegalArgumentException(ErrorMessages.NULL_INPUT);
        if (!hasCustomDelimiter(input)) return DEFAULT_DELIMITERS;

        validateCustomDelimiterFormat(input);
        String customDelimiter = extractCustomDelimiter(input);

        if (customDelimiter.isEmpty()) throw new IllegalArgumentException(ErrorMessages.EMPTY_DELIMITER);
        if (containsDigit(customDelimiter)) throw new IllegalArgumentException(ErrorMessages.DELIMITER_CONTAINS_DIGIT);

        return customDelimiter;
    }

    private boolean hasCustomDelimiter(String input) {
        return input.startsWith(CUSTOM_DELIMITER_PREFIX) || input.contains(CUSTOM_DELIMITER_SUFFIX);
    }

    private String extractCustomDelimiter(String input) {
        int endIndex = input.indexOf(CUSTOM_DELIMITER_SUFFIX);
        return input.substring(CUSTOM_DELIMITER_PREFIX.length(), endIndex);
    }

    private void validateCustomDelimiterFormat(String input) {
        if (!input.startsWith(CUSTOM_DELIMITER_PREFIX)) throw new IllegalArgumentException(ErrorMessages.INVALID_PREFIX);
        if (!input.contains(CUSTOM_DELIMITER_SUFFIX)) throw new IllegalArgumentException(ErrorMessages.MISSING_NEWLINE);
        if (hasMultipleNewlines(input)) throw new IllegalArgumentException(ErrorMessages.MULTIPLE_NEWLINES);
    }

    private boolean hasMultipleNewlines(String input) {
        int firstNewline = input.indexOf(CUSTOM_DELIMITER_SUFFIX);
        return input.indexOf(CUSTOM_DELIMITER_SUFFIX, firstNewline + 1) != -1;
    }

    private boolean containsDigit(String delimiter) {
        return delimiter.chars().anyMatch(Character::isDigit);
    }
}
