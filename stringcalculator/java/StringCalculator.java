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

        Scanner scanner = new Scanner(expression);
        String delimiters = findDelimiters(expression, scanner);
        String[] terms = splitByDelimiters(scanner, delimiters);
        return sum(terms);
    }

    private String[] splitByDelimiters(Scanner scanner, String delimiters) {
        String rest = readAll(scanner);
        return rest.split(delimiters);
    }

    private int sum(String[] terms) {
        int sum = 0;
        StringBuilder negatives = new StringBuilder();
        for (String term : terms) {
            int number = Integer.parseInt(term);
            if (number < 0) {
                negatives.append(number).append(" ");
            } else if (number <= MAX) {
                sum += number;
            }
        }
        ensureNonNegatives(negatives);
        return sum;
    }

    private String readAll(Scanner scanner) {
        scanner.useDelimiter("\\z");
        return scanner.next();
    }

    private String findDelimiters(String expression, Scanner scanner) {
        String delimiters = DEFAULT_DELIMITERS;
        if (expression.startsWith(CUSTOM_DELIMITER)) {
            String firstLine = scanner.nextLine();
            List<String> delimiterList = findCustomDelimiters(firstLine);
            if (delimiterList.isEmpty()) {
                delimiters = firstLine.replace(CUSTOM_DELIMITER, "").replaceAll("\\]|\\[", "");
                delimiters = Pattern.quote(delimiters);
            } else {
                delimiters = joinDelimiters(delimiterList);
            }
        }
        return delimiters;
    }

    private void ensureNonNegatives(StringBuilder negatives) {
        if (!negatives.toString().isEmpty()) {
            throw new IllegalArgumentException("Negatives not allowed: " + negatives.toString());
        }
    }

    private String joinDelimiters(List<String> delimiterList) {
        String delimiters;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < delimiterList.size() - 1; i++) {
            builder.append(Pattern.quote(delimiterList.get(i))).append("|");
        }
        builder.append(Pattern.quote(delimiterList.get(delimiterList.size() - 1)));
        delimiters = builder.toString();
        return delimiters;
    }

    private List<String> findCustomDelimiters(String firstLine) {
        List<String> delimiterList = new ArrayList<String>();
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(firstLine);
        while (matcher.find()) {
            delimiterList.add(matcher.group(1));
        }
        return delimiterList;
    }

}
