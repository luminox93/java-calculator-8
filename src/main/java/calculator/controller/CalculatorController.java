package calculator.controller;

import calculator.domain.Calculator;
import calculator.domain.StringParser;
import calculator.view.InputView;
import calculator.view.OutputView;

public class CalculatorController {
    private final InputView inputView;
    private final OutputView outputView;
    private final StringParser parser;
    private final Calculator calculator;

    public CalculatorController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.parser = new StringParser();
        this.calculator = new Calculator();
    }

    public void run() {
        String input = inputView.readInput();
        int result = calculator.sum(parser.parse(input));
        outputView.printResult(result);
    }
}
