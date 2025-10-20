package calculator.domain;

import java.util.List;

public class NumberValidator {
    public void validate(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException(ErrorMessages.NULL_LIST);
        }

        for (int number : numbers) {
            if (number < 0) {
                throw new IllegalArgumentException(ErrorMessages.NEGATIVE_NUMBER);
            }
        }
    }
}
