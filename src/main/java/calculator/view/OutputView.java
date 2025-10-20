package calculator.view;

public class OutputView {
    private static final String RESULT_PREFIX = "결과 : ";

    public void printResult(int result){
        System.out.println(RESULT_PREFIX + result);
    }

    public void printError(String message){
        System.out.println(message);
    }
}
