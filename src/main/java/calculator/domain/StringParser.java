package calculator.domain;
import java.util.ArrayList;
import java.util.List;

public class StringParser {
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String CUSTOM_DELIMITER_SUFFIX = "\n";
    private static final String DEFAULT_DELIMITERS = ",|:" ;

    public List<Integer> parse(String input) {
        if (input == null || input.isEmpty()) {
            return new ArrayList<>();
        }

        input = input.replace("\\n", "\n");
        String delimiter = DEFAULT_DELIMITERS;
        String numbers = input;

        if(input.startsWith(CUSTOM_DELIMITER_PREFIX)){
            int delimiterEndIndex = input.indexOf(CUSTOM_DELIMITER_SUFFIX);
            String customDelimiter = input.substring(CUSTOM_DELIMITER_PREFIX.length(), delimiterEndIndex);
            delimiter = escapeRegexSpecialCharacters(customDelimiter);
            numbers = input.substring(delimiterEndIndex+1);
        }
        return parseNumbers(numbers, delimiter);
    }

    private String escapeRegexSpecialCharacters(String delimiter){
        return delimiter.replaceAll("([\\\\+*?\\[\\](){}|^$.\\-])", "\\\\$1");
    }

    private List<Integer> parseNumbers(String numbers, String delimiter) {
        String[] tokens = numbers.split(delimiter);
        List<Integer> result = new ArrayList<>();

        for (String token : tokens) {
            if (!token.isEmpty()) {
                result.add(parseInteger(token));
            }
        }
        return result;
    }

    private int parseInteger(String token) {
        try {
            return Integer.parseInt(token.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다: " + token);
        }
    }

}
