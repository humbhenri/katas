import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    private static final int MAX = 1000;
    private static final String CUSTOM_DELIMITER = "//";
    private static final String DEFAULT_DELIMITERS = ",|\n";

    public int add(String expression) {
        if (expression.isEmpty()) {
            return 0;
        }

        String delimiters = DEFAULT_DELIMITERS;

        Scanner scanner = new Scanner(expression);

        if (expression.startsWith(CUSTOM_DELIMITER)) {
            String firstLine = scanner.nextLine();
            List<String> delimiterList = new ArrayList<String>();
            Pattern pattern = Pattern.compile("\\[(.*?)\\]");
            Matcher matcher = pattern.matcher(firstLine);
            while (matcher.find()) {
                delimiterList.add(matcher.group(1));
            }
            if (delimiterList.isEmpty()) {
                delimiters = firstLine.replace(CUSTOM_DELIMITER, "").replaceAll("\\]|\\[", "");
                delimiters = Pattern.quote(delimiters);
            } else {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < delimiterList.size() - 1; i++) {
                    builder.append(Pattern.quote(delimiterList.get(i))).append("|");
                }
                builder.append(Pattern.quote(delimiterList.get(delimiterList.size() - 1)));
                delimiters = builder.toString();
            }
        }

        scanner.useDelimiter("\\z");
        return performSum(scanner.next(), delimiters);
    }

    private int performSum(String expression, String delimiters) {
        int sum = 0;
        StringBuilder negatives = new StringBuilder();
        for (String term : expression.split(delimiters)) {
            int number = Integer.parseInt(term);
            if (number < 0) {
                negatives.append(number).append(" ");
            } else if (number <= MAX) {
                sum += number;
            }
        }
        if (!negatives.toString().isEmpty()) {
            throw new IllegalArgumentException("Negatives not allowed: " + negatives.toString());
        }
        return sum;
    }
}
