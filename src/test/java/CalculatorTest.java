import java.util.Arrays;
import java.util.List;

public class CalculatorTest {
    public static void main(String[] args) {
        List<String> exampleList = Arrays.asList(
                "1+1",
                "10/5",
                "10/3",
                "3.14 + 5",
                "3.14 - 5"
        );

        SplitToken splitToken = new SplitToken();
        exampleList.stream().map(splitToken::split).forEach(System.out::println);
    }
}
