package calculator.domain;

import java.util.List;

public class Calculator {
    public int sum(List<Integer> numbers) {
        new NumberValidator().validate(numbers);
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }
}
