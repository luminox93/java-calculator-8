package calculator.domain;

import java.util.ArrayList;
import java.util.List;

public class NumberParser {
    public List<Integer> parse(String numbers, String delimiter) {
        if (numbers == null) {
            throw new IllegalArgumentException(ErrorMessages.NULL_INPUT);
        }

        if (numbers.isEmpty()) {
            return new ArrayList<>();
        }

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
            int number = Integer.parseInt(token.trim());
            if (number < 0) {
                throw new IllegalArgumentException(ErrorMessages.NEGATIVE_NUMBER);
            }
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessages.INVALID_NUMBER);
        }
    }
}
