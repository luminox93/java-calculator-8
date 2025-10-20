package calculator.domain;

import java.util.ArrayList;
import java.util.List;

public class NumberParser {

    public List<Integer> parse(String numbers, String delimiter) {
        if (numbers == null) {
            throw new IllegalArgumentException("입력값이 null입니다");
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
                throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
            }
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다: " + token);
        }
    }
}
