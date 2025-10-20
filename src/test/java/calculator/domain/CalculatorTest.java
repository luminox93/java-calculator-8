package calculator.domain;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CalculatorTest {
    private final Calculator calculator = new Calculator();

    @Test
    void 빈_리스트_합계는_0() {
        assertThat(calculator.sum(List.of())).isEqualTo(0);
    }

    @Test
    void 단일_숫자_합계() {
        assertThat(calculator.sum(List.of(5))).isEqualTo(5);
        assertThat(calculator.sum(List.of(100))).isEqualTo(100);
    }

    @Test
    void 여러_숫자_합계() {
        assertThat(calculator.sum(List.of(1, 2, 3))).isEqualTo(6);
        assertThat(calculator.sum(List.of(10, 20, 30))).isEqualTo(60);
    }

    @Test
    void 숫자_0_포함_합계() {
        assertThat(calculator.sum(List.of(0, 1, 2))).isEqualTo(3);
        assertThat(calculator.sum(List.of(0))).isEqualTo(0);
    }

    @Test
    void 음수_포함시_예외() {
        assertThatThrownBy(() -> calculator.sum(List.of(-1, 2, 3)))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> calculator.sum(List.of(1, -2, 3)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void null_리스트면_예외() {
        assertThatThrownBy(() -> calculator.sum(null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
