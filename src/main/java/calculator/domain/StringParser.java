package calculator.domain;

import java.util.ArrayList;
import java.util.List;

public class StringParser {
    private static final String CUSTOM_DELIMITER_SUFFIX = "\n";
    private final DelimiterExtractor delimiterExtractor;
    private final NumberParser numberParser;

    public StringParser() {
        this.delimiterExtractor = new DelimiterExtractor();
        this.numberParser = new NumberParser();
    }

    public List<Integer> parse(String input) {
        if (input == null) {
            throw new IllegalArgumentException("입력값이 null입니다");
        }
        if (input.isEmpty()) {
            return new ArrayList<>();
        }

        input = input.replace("\\n", "\n");

        String delimiter = delimiterExtractor.extract(input);
        String numbers = extractNumbers(input);
        String escapedDelimiter = escapeRegexSpecialCharacters(delimiter);

        return numberParser.parse(numbers, escapedDelimiter);
    }

    private String extractNumbers(String input) {
        if (input.contains(CUSTOM_DELIMITER_SUFFIX)) {
            int delimiterEndIndex = input.indexOf(CUSTOM_DELIMITER_SUFFIX);
            return input.substring(delimiterEndIndex + 1);
        }
        return input;
    }

    private String escapeRegexSpecialCharacters(String delimiter) {
        // 기본 구분자는 이미 정규식 형태이므로 그대로 반환
        if (delimiter.equals(",|:")) {
            return delimiter;
        }

        // 각 문자를 escape하고 |로 연결
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < delimiter.length(); i++) {
            if (i > 0) {
                result.append("|");
            }
            String ch = String.valueOf(delimiter.charAt(i));
            result.append(ch.replaceAll("([\\\\+*?\\[\\](){}|^$.\\-])", "\\\\$1"));
        }
        return result.toString();
    }
}
