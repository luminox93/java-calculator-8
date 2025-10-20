package calculator.domain;

import java.util.List;

public class NumberValidator {
    public void validate(List<Integer> numbers) {
        if (numbers == null) throw new IllegalArgumentException(ErrorMessages.NULL_LIST);
        if (numbers.stream().anyMatch(n -> n < 0)) throw new IllegalArgumentException(ErrorMessages.NEGATIVE_NUMBER);
    }
}
