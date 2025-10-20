package calculator.domain;

public class ErrorMessages {
    public static final String NULL_INPUT = "입력값이 null입니다";
    public static final String EMPTY_DELIMITER = "구분자가 비어있습니다";
    public static final String INVALID_PREFIX = "커스텀 구분자는 '//'로 시작해야 합니다";
    public static final String MISSING_NEWLINE = "커스텀 구분자 형식에 '\\n'이 없습니다";
    public static final String MULTIPLE_NEWLINES = "커스텀 구분자 형식에 '\\n'이 여러 개입니다";
    public static final String DELIMITER_CONTAINS_DIGIT = "구분자에 숫자를 사용할 수 없습니다";
    public static final String NEGATIVE_NUMBER = "음수는 입력할 수 없습니다.";
    public static final String INVALID_NUMBER = "숫자가 아닌 값이 포함되어 있습니다";
    public static final String NULL_LIST = "숫자 리스트가 null입니다";

    private ErrorMessages() {
        // 유틸리티 클래스이므로 인스턴스화 방지
    }
}
