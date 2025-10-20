package calculator.domain;

public class DelimiterExtractor {
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String CUSTOM_DELIMITER_SUFFIX = "\n";
    private static final String DEFAULT_DELIMITERS = ",|:";

    public String extract(String input) {
        if (input == null) {
            throw new IllegalArgumentException("입력값이 null입니다");
        }

        boolean startsWithPrefix = input.startsWith(CUSTOM_DELIMITER_PREFIX);
        boolean containsSuffix = input.contains(CUSTOM_DELIMITER_SUFFIX);

        // //로 시작하거나 \n이 포함되어 있으면 커스텀 구분자 형식으로 간주
        if (startsWithPrefix || containsSuffix) {
            validateCustomDelimiterFormat(input);

            int delimiterEndIndex = input.indexOf(CUSTOM_DELIMITER_SUFFIX);
            String customDelimiter = input.substring(CUSTOM_DELIMITER_PREFIX.length(), delimiterEndIndex);

            if (customDelimiter.isEmpty()) {
                throw new IllegalArgumentException("구분자가 비어있습니다");
            }

            return customDelimiter;
        }

        // 커스텀 구분자 형식이 아니면 기본 구분자 반환
        return DEFAULT_DELIMITERS;
    }

    private void validateCustomDelimiterFormat(String input) {
        if (!input.startsWith(CUSTOM_DELIMITER_PREFIX)) {
            throw new IllegalArgumentException("커스텀 구분자는 '//'로 시작해야 합니다");
        }

        if (!input.contains(CUSTOM_DELIMITER_SUFFIX)) {
            throw new IllegalArgumentException("커스텀 구분자 형식에 '\\n'이 없습니다");
        }

        int firstNewline = input.indexOf(CUSTOM_DELIMITER_SUFFIX);
        int secondNewline = input.indexOf(CUSTOM_DELIMITER_SUFFIX, firstNewline + 1);
        if (secondNewline != -1) {
            throw new IllegalArgumentException("커스텀 구분자 형식에 '\\n'이 여러 개입니다");
        }
    }
}
