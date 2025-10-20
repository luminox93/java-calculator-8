package calculator.domain;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatCode;

public class NumberValidatorTest {
    private final NumberValidator validator = new NumberValidator();

    @Test
    void 양수_리스트는_검증_통과() {
        assertThatCode(() -> validator.validate(List.of(1, 2, 3)))
                .doesNotThrowAnyException();

        assertThatCode(() -> validator.validate(List.of(100, 200, 300)))
                .doesNotThrowAnyException();
    }

    @Test
    void 0_포함된_리스트는_검증_통과() {
        assertThatCode(() -> validator.validate(List.of(0, 1, 2)))
                .doesNotThrowAnyException();

        assertThatCode(() -> validator.validate(List.of(0)))
                .doesNotThrowAnyException();
    }

    @Test
    void 빈_리스트는_검증_통과() {
        assertThatCode(() -> validator.validate(List.of()))
                .doesNotThrowAnyException();
    }

    @Test
    void 음수_포함시_예외() {
        assertThatThrownBy(() -> validator.validate(List.of(-1, 2, 3)))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> validator.validate(List.of(1, -2, 3)))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> validator.validate(List.of(-1)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void null_리스트면_예외() {
        assertThatThrownBy(() -> validator.validate(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
