package calculator.domain;

import java.util.List;

public class Calculator {
    private final NumberValidator numberValidator;

    public Calculator() {
        this.numberValidator = new NumberValidator();
    }

    public int sum(List<Integer> numbers) {
        numberValidator.validate(numbers);
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}
