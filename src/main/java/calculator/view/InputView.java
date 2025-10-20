package calculator.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    private static final String INPUT_PROMPT = "덧셈할 문자열을 입력해 주세요.";

    public String readInput() {
        System.out.print(INPUT_PROMPT);
        return Console.readLine();
    }
}
