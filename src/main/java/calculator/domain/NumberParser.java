package calculator.domain;

import java.util.ArrayList;
import java.util.List;

public class NumberParser {
    public List<Integer> parse(String numbers, String delimiter) {
        if (numbers == null) throw new IllegalArgumentException(ErrorMessages.NULL_INPUT);
        if (numbers.isEmpty()) return new ArrayList<>();

        return java.util.Arrays.stream(numbers.split(delimiter))
            .filter(token -> !token.isEmpty())
            .map(this::parseInteger)
            .toList();
    }

    private int parseInteger(String token) {
        try {
            int number = Integer.parseInt(token.trim());
            if (number < 0) throw new IllegalArgumentException(ErrorMessages.NEGATIVE_NUMBER);
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_NUMBER);
        }
    }
}
