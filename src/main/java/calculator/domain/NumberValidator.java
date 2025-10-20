package calculator.domain;

import java.util.List;

public class NumberValidator {
    private static final String ERROR_NULL_LIST = "숫자 리스트가 null입니다";
    private static final String ERROR_NEGATIVE_NUMBER = "음수는 입력할 수 없습니다.";

    public void validate(List<Integer> numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException(ERROR_NULL_LIST);
        }

        for (int number : numbers) {
            if (number < 0) {
                throw new IllegalArgumentException(ERROR_NEGATIVE_NUMBER);
            }
        }
    }
}
