package calculator.view;

public class OutputView {
    private static final String Result_PREFIX = "결과 : ";

    public void printResult(int result){
        System.out.println(Result_PREFIX + result);
    }

    public void printError(String message){
        System.out.println(message);
    }
}
