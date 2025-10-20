package calculator.domain;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DelimiterExtractorTest {
    private final DelimiterExtractor extractor = new DelimiterExtractor();

    @Test
    void 커스텀_구분자_없으면_기본_구분자_반환() {
        assertThat(extractor.extract("1,2:3")).isEqualTo(",|:");
        assertThat(extractor.extract("")).isEqualTo(",|:");
        assertThat(extractor.extract("123")).isEqualTo(",|:");
    }

    @Test
    void 올바른_형식이면_구분자_추출() {
        assertThat(extractor.extract("//;\n1;2;3")).isEqualTo(";");
        assertThat(extractor.extract("//.\n1.2.3")).isEqualTo(".");
        assertThat(extractor.extract("//:::\n1:::2:::3")).isEqualTo(":::");
        assertThat(extractor.extract("///\n1/2/3")).isEqualTo("/");
        assertThat(extractor.extract("////\n1//2//3")).isEqualTo("//");
        assertThat(extractor.extract("//abc\n1abc2")).isEqualTo("abc");
        assertThat(extractor.extract("//!@#\n1!@#2")).isEqualTo("!@#");
    }

    @Test
    void 슬래시_2개_미만이면_예외() {
        assertThatThrownBy(() -> extractor.extract(";\n1;2;3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("'//'로 시작해야 합니다");

        assertThatThrownBy(() -> extractor.extract("/;\n1;2;3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("'//'로 시작해야 합니다");
    }

    @Test
    void 개행문자_없으면_예외() {
        assertThatThrownBy(() -> extractor.extract("//;1;2;3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("'\\n'이 없습니다");

        assertThatThrownBy(() -> extractor.extract("//abc"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("'\\n'이 없습니다");
    }

    @Test
    void 개행문자_여러개면_예외() {
        assertThatThrownBy(() -> extractor.extract("//;\n\n1;2;3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("'\\n'이 여러 개입니다");

        assertThatThrownBy(() -> extractor.extract("//abc\n\n\n1"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("'\\n'이 여러 개입니다");
    }

    @Test
    void 구분자_비어있으면_예외() {
        assertThatThrownBy(() -> extractor.extract("//\n1,2,3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("구분자가 비어있습니다");
    }

    @Test
    void null_입력시_예외() {
        assertThatThrownBy(() -> extractor.extract(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 다중_구분자_추출() {
        // 여러 문자를 각각 구분자로 사용
        assertThat(extractor.extract("//;,|\n1;2|3,4")).isEqualTo(";,|");
        assertThat(extractor.extract("//;:\n1;2:3")).isEqualTo(";:");
        assertThat(extractor.extract("//abc\n1a2b3c4")).isEqualTo("abc");
    }

    @Test
    void 구분자에_숫자가_포함되면_예외() {
        assertThatThrownBy(() -> extractor.extract("//1\n213"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("구분자에 숫자를 사용할 수 없습니다");

        assertThatThrownBy(() -> extractor.extract("//5\n152535"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("구분자에 숫자를 사용할 수 없습니다");

        assertThatThrownBy(() -> extractor.extract("//1a\n1a2a3"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("구분자에 숫자를 사용할 수 없습니다");

        assertThatThrownBy(() -> extractor.extract("//a1b\n1a1b2"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("구분자에 숫자를 사용할 수 없습니다");

        assertThatThrownBy(() -> extractor.extract("//123\n1231234"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("구분자에 숫자를 사용할 수 없습니다");
    }
}
