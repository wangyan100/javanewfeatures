import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

public class LambdaExample {

    public static void main(    String args[]){
        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp" , "JavaScript");
        Predicate<String> startsWithJ = (n) -> n.startsWith("J");
        Predicate<String> moreThanfourLetterLong = (n) -> n.length() >4;
        Predicate<String> scriptLanguage = (n) -> n.contains("Script");
        languages.stream().filter(startsWithJ.and(moreThanfourLetterLong).and(scriptLanguage))
                . forEach((n) -> System.out.print("\nName, which starts with 'J' and four letter long is : " + n));

        test6();
    }

    public static void test6() {
        //List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        List<Integer> costBeforeTax = Arrays.asList(100,200);
        costBeforeTax.stream().map((cost) -> cost + 0.12 * cost).forEach(System.out::println);
        double bill = costBeforeTax.stream().map(cost -> cost + 0.12 * cost).reduce(
                (sum, cost) -> {
                    System.out.println("sum:"+sum);
                    System.out.println("cost:"+cost);
                    return sum + cost;}).get();
        System.out.println(bill);


    }

}
