import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaExample {

    public static void main(String args[]) {

        usePredicates();

        useReduceForMap();

    }

    public static void useLambdaForInterface(){
        new Thread( () -> System.out.println("In Java8, Lambda expression rocks !!") ).start();
    }

    public static void useLambdaForIteraction(){
        List<String> features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(System.out::println );
        //or
        features.forEach(n-> {
            System.out.println(n);
        });
    }

    public static void usePredicates() {
        System.out.println("###### usePredicates ");
        List languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp", "JavaScript");
        Predicate<String> startsWithJ = (n) -> n.startsWith("J");
        Predicate<String> moreThanfourLetterLong = (n) -> n.length() > 4;
        Predicate<String> scriptLanguage = (n) -> n.contains("Script");
        languages.stream().filter(startsWithJ.and(moreThanfourLetterLong).and(scriptLanguage))
                .forEach((n) -> System.out.println("\nName, which starts with 'J' , more than 4 letters , and contain Script is : " + n));
    }

    public static void useReduceForMap() {
        System.out.println("###### useReduceForMap ");
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        costBeforeTax.stream().map((cost) -> cost + 0.12 * cost).forEach(System.out::println);
        //if a collection contains only one element, reduce will not even be invoked, because it needs at least two element in the collection
        double bill = costBeforeTax.stream().map(cost -> cost + 0.12 * cost).reduce(
                (sum, cost) -> {
                    System.out.println("sum:" + sum);
                    System.out.println("cost:" + cost);
                    return sum + cost;
                }).get();
        System.out.println(bill);
    }

    // Use Filter to create a Sub-Collection
    public static void useFiltertoCreateSubList(){
        // a collect original with 5 langauges
        // use filter to filter out language which less then 4 chars
        // the rest language will be put at a sub Collection
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");
        List<String> filtered = languages.stream().filter(x -> x.length()> 4).collect(Collectors.toList());
        System.out.printf("Original List : %s, filtered list : %s %n", languages, filtered);
    }

    //Apply Operation on List example1
    public static void applyOperation1(){

        //Use map to apply operation on each element in list
        // make element uppercase
        // use Collects to join elements as String
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy","U.K.","Canada");
        String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
        System.out.println(G7Countries);
    }

    //Apply Operation on List example2
    public static void applyOperation2(){

        //Use map to apply square operation on each element in list
        // use distinct to remove duplicated result
        // use Collects to create a subList
        List<Integer> numbers = Arrays.asList(9, 10, 3, 4, 7, 3, 4);
        List<Integer> distinct = numbers.stream().map( i ->i*i).distinct().collect(Collectors.toList());
        System.out.printf("Original List : %s, Square Without duplicates : %s %n", numbers, distinct);
    }

    public static  void useSummaryStatistics(){
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());

    }
}
