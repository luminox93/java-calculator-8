package calculator;

import calculator.domain.Calculator;
import calculator.domain.StringParser;
import calculator.view.InputView;
import calculator.view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        StringParser parser = new StringParser();
        Calculator calculator = new Calculator();

        String input = inputView.readInput();
        List<Integer> numbers = parser.parse(input);
        int result = calculator.sum(numbers);
        outputView.printResult(result);
    }
}
