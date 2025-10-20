package calculator.domain;

import java.util.List;

public class Calculator {

    public int sum(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("숫자 리스트가 null입니다");
        }
        validateNegativeNumbers(numbers);
        return numbers.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    private void validateNegativeNumbers(List<Integer> numbers) {
        for (int number : numbers) {
            if (number < 0) {
                throw new IllegalArgumentException("음수는 입력할 수 없습니다.");
            }
        }
    }
}
