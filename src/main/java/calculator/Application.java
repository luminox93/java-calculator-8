package calculator;

import calculator.view.InputView;

public class Application {
    public static void main(String[] args) {
    InputView inputView = new InputView();
    String input = inputView.readInput();

    System.out.println("결과 : " +  input);

    }
}
