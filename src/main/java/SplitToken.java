import org.xml.sax.helpers.AttributeListImpl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class SplitToken implements SplitTool {
    public String split(String line) {
        final List<String> number = new ArrayList<>();
        final List<String> sing = new ArrayList<>();
        setList(line, number, sing);
        final ListIterator<String> singBig = sing.listIterator();

        if (isInteger(line)) {
            final ListIterator<BigInteger> numberBig = getBigInteger(number);
            BigInteger start = numberBig.next();
            while (numberBig.hasNext())
                start = singsInt.get(singBig.next()).apply(start, numberBig.next());
            return start.toString();
        } else {
            final ListIterator<BigDecimal> numberBig = getBigDecimal(number);
            BigDecimal start = numberBig.next();
            while (numberBig.hasNext())
                start = sings.get(singBig.next()).apply(start, numberBig.next());
            return start.toString();
        }
    }

    private boolean isInteger(String number) {
        return !number.contains(".");
    }
}
