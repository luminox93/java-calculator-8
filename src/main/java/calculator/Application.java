package calculator;

import calculator.domain.Calculator;
import calculator.domain.StringParser;
import calculator.view.InputView;
import calculator.view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        OutputView outputView = new OutputView();
        Calculator calculator = new Calculator();

        String input = new InputView().readInput();
        List<Integer> numbers = new StringParser().parse(input);
        outputView.printResult(calculator.sum(numbers));
    }
}
