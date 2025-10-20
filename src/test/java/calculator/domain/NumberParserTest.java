package calculator.domain;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NumberParserTest {
    private final NumberParser parser = new NumberParser();

    @Test
    void 빈_문자열_입력시_빈_리스트_반환() {
        assertThat(parser.parse("", ",")).isEmpty();
        assertThat(parser.parse("", ";")).isEmpty();
        assertThat(parser.parse("", "any")).isEmpty();
    }

    @Test
    void 구분자로_올바르게_분리하여_숫자_파싱() {
        assertThat(parser.parse("1,2,3", ",")).containsExactly(1, 2, 3);
        assertThat(parser.parse("10:20:30", ":")).containsExactly(10, 20, 30);
        assertThat(parser.parse("100;200;300", ";")).containsExactly(100, 200, 300);
        assertThat(parser.parse("5:::10:::15", ":::")).containsExactly(5, 10, 15);
        assertThat(parser.parse("7abc9abc11", "abc")).containsExactly(7, 9, 11);
    }

    @Test
    void 공백은_trim_처리() {
        assertThat(parser.parse(" 1 , 2 , 3 ", ",")).containsExactly(1, 2, 3);
        assertThat(parser.parse("  10  :  20  ", ":")).containsExactly(10, 20);
    }

    @Test
    void 빈_토큰은_무시() {
        assertThat(parser.parse("1,,2", ",")).containsExactly(1, 2);
        assertThat(parser.parse("1,,,3", ",")).containsExactly(1, 3);
        assertThat(parser.parse(",,1,,2,,", ",")).containsExactly(1, 2);
    }

    @Test
    void null_입력시_예외() {
        assertThatThrownBy(() -> parser.parse(null, ","))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 음수_포함시_예외() {
        assertThatThrownBy(() -> parser.parse("-1,2,3", ","))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> parser.parse("1,-2,3", ","))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> parser.parse("-10", ","))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 숫자가_아닌_값_포함시_예외() {
        assertThatThrownBy(() -> parser.parse("1,a,3", ","))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> parser.parse("abc", ","))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> parser.parse("1.5,2", ","))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 정수_범위_초과시_예외() {
        String overMax = "2147483648"; // Integer.MAX_VALUE + 1
        String underMin = "-2147483649"; // Integer.MIN_VALUE - 1

        assertThatThrownBy(() -> parser.parse(overMax, ","))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> parser.parse(underMin, ","))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
