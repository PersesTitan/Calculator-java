import org.xml.sax.helpers.AttributeListImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public interface SplitTool {
    Map<String, BiFunction<BigDecimal, BigDecimal, BigDecimal>> sings = new HashMap<>() {{
        put("+", BigDecimal::add);
        put("-", BigDecimal::subtract);
        put("*", BigDecimal::multiply);
        put("%", BigDecimal::remainder);
        put("/", (a, b) -> a.divide(b, MathContext.DECIMAL64));
    }};

    Map<String, BiFunction<BigInteger, BigInteger, BigInteger>> singsInt = new HashMap<>() {{
        put("+", BigInteger::add);
        put("-", BigInteger::subtract);
        put("*", BigInteger::multiply);
        put("%", BigInteger::remainder);
        put("/", BigInteger::divide);
    }};

    /**
     * (example) <br>
     * @param line 10+20*50
     * @param number [10, 20, 50]
     * @param sing [+, *]
     */
    default void setList(String line, List<String> number, List<String> sing) {
        String singToken = String.join("", sings.keySet());
        StringTokenizer tokenizer = new StringTokenizer(line, singToken, true);
        while (tokenizer.hasMoreTokens()) {
            number.add(tokenizer.nextToken().strip());
            if (!tokenizer.hasMoreTokens()) break;
            sing.add(tokenizer.nextToken());
        }
    }

    default ListIterator<BigDecimal> getBigDecimal(List<String> number) {
        return number
                .stream()
                .map(BigDecimal::new)
                .toList()
                .listIterator();
    }

    default ListIterator<BigInteger> getBigInteger(List<String> number) {
        return number
                .stream()
                .map(BigInteger::new)
                .toList()
                .listIterator();
    }
}
