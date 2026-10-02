import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamExample {

    static class Employee {
        private String name;
        private String department;
        private double salary;
        private int age;

        public Employee(String name, String department, double salary, int age) {
            this.name = name;
            this.department = department;
            this.salary = salary;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public String getDepartment() {
            return department;
        }

        public double getSalary() {
            return salary;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "["+ name + " , " + department + " , " + salary + " , " + age + "]";
        }
    }

    public static void main(String[] args) {
        /*1. Filter and sort even numbers
        Given List nums = List.of(5, 3, 8, 1, 9, 2), return only the even numbers sorted in ascending order.*/
        List<Integer> nums = List.of(5, 3, 8, 1, 9, 2);
        List<Integer> sorted = nums.stream().filter(n -> n % 2 == 0).sorted().toList();
        System.out.println("1. Given List nums = List.of(5, 3, 8, 1, 9, 2), return only the even numbers sorted in ascending order.");
        System.out.println("Result : " + sorted);

        System.out.println("**********************************************************************************************************************************************");

        /*2. Count character frequency
        Given the String "programming", count how many times each character appears.*/
        System.out.println("2. Given the String \"programming\", count how many times each character appears.");
        String s = "programming";
        Map<Character, Long> frequencyMap = s.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("result : \n" + frequencyMap);

        System.out.println("**********************************************************************************************************************************************");

        /*3. Uppercase and sort by length
        Given List words = List.of("banana", "kiwi", "apple", "fig"), convert the words to uppercase and sort by length, shortest first.*/
        System.out.println("3. Given List words = List.of(\"banana\", \"kiwi\", \"apple\", \"fig\"), convert the words to uppercase and sort by length, shortest first.");
        List<String> words = List.of("banana", "kiwi", "apple", "fig");
        List<String> modifiedList = words.stream().map(String::toUpperCase).sorted(Comparator.comparingInt(String::length)).toList();
        System.out.println("result : " + modifiedList);

        System.out.println("**********************************************************************************************************************************************");

       /* 4. Count names starting with A
        Given List names = List.of("Tushar", "Laxman", "Akash", "akshay", "Shubham", "Sanket", "Kshitij", "Aadinath"), count how many names start with "A", case-insensitive. Do not use loops.*/

        System.out.println("4. Given List names = List.of(\"Tushar\", \"Laxman\", \"Akash\", \"akshay\", \"Shubham\", \"Sanket\", \"Kshitij\", \"Aadinath\"), count how many names start with \"A\", case-insensitive. Do not use loops.");
        List<String> names = List.of("Tushar", "Laxman", "Akash", "akshay", "Shubham", "Sanket", "Kshitij", "Aadinath");
        List<String> namesResult = names.stream().filter(l -> l != null && !l.isBlank()).filter(f -> f.startsWith("A") || f.startsWith("a")).toList();
        System.out.println("Result : " + namesResult);

        System.out.println("**********************************************************************************************************************************************");

        /*5. Sum of squares of odd numbers
        Given List numbers = List.of(1,2,3,4,5,6,7,8,9,10), find the sum of squares of all odd numbers.*/

        System.out.println("5. Given List numbers = List.of(1,2,3,4,5,6,7,8,9,10), find the sum of squares of all odd numbers.");
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("Sum : " + numbers.stream().filter(n -> n % 2 != 0).mapToInt(sq -> sq * sq).sum());

        System.out.println("**********************************************************************************************************************************************");

        /*6. Unique characters using flatMap
        Given List words = List.of("banana", "kiwi", "apple", "fig"), return a Set of all unique characters across all words, ignoring case.*/

        System.out.println("6. Given List words = List.of(\"banana\", \"kiwi\", \"apple\", \"fig\"), return a Set of all unique characters across all words, ignoring case.");
        System.out.println(words.stream().flatMap(a -> a.chars().mapToObj(b -> (char) b)).collect(Collectors.toSet()));

        System.out.println("**********************************************************************************************************************************************");

        /*7. Partition odd and even numbers
        Given List.of(1,2,3,4,5,6,7,8,9), produce [[1,3,5,7,9],[2,4,6,8]] using Streams.*/
        System.out.println("7. Given List.of(1,2,3,4,5,6,7,8,9), produce [[1,3,5,7,9],[2,4,6,8]] by partitioning odd even numbers using Streams.");
        List<List<Integer>> partitionedList = new ArrayList<>(
                numbers.stream().collect(Collectors.partitioningBy(c -> c % 2 == 0)).values()
        );
        System.out.println("Result : " + partitionedList);

        System.out.println("**********************************************************************************************************************************************");

        /*8. Group words by length
        Given List names = List.of("Tushar", "Laxman", "Akash", "akshay", "Shubham", "Sanket", "Kshitij", "Aadinath"), group them into Map by word length.*/
        System.out.println("8. Given List names = List.of(\"Tushar\", \"Laxman\", \"Akash\", \"akshay\", \"Shubham\", \"Sanket\", \"Kshitij\", \"Aadinath\"), group them into Map> by word length.");
        Map<Integer, List<String>> groupedByLengthMap = names.stream().collect(Collectors.groupingBy(String::length));
        System.out.println(groupedByLengthMap);

        System.out.println("**********************************************************************************************************************************************");

        /*9. Average salary per department
        Given Employee objects with department and salary, find the average salary per department as Map.*/
        System.out.println("9. Given Employee objects with department and salary, find the average salary per department as Map.");
        List<Employee> employees = List.of(
                new Employee("Alice", "IT", 20000, 32),
                new Employee("Bob", "Finance", 85000, 45),
                new Employee("Charlie", "IT", 60000, 25),
                new Employee("David", "HR", 90000, 39),
                new Employee("Eve", "IT", 75000, 29)
        );

        employees.forEach(e -> System.out.println(e.toString()));

        Map<String, Double> avgSalaryPerDept = employees.stream().collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("Average Salary Per Department : " + avgSalaryPerDept);

        System.out.println("**********************************************************************************************************************************************");

        /*10. Top 3 highest-paid employees
        Given Employee(name, department, salary), return the names of the top 3 highest-paid employees.*/

        System.out.println("10. Given Employee Objects, return the names of the top 3 highest-paid employees.");
        List<String> topPaidEmployeesList = employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).limit(3).map(Employee::getName).toList();
        System.out.println(topPaidEmployeesList);

        System.out.println("**********************************************************************************************************************************************");

        /*11. Convert int[] to List
        Given int[] arr = {4, 8, 15, 16, 23, 42}, convert it to List.*/

        System.out.println("11. Given int[] arr = {4, 8, 15, 16, 23, 42}, convert it to List.");
        int[] arr = {4, 8, 15, 16, 23, 42};
        List<Integer> resultList = Arrays.stream(arr).boxed().toList();
        System.out.println("Result List : " + resultList);

        System.out.println("**********************************************************************************************************************************************");

        /*12. Find the 3rd largest number
        Given int[] array = {2, 3, 1, 9, 5, 7}, find the 3rd largest number using Streams. Explain each method.*/

        System.out.println("12. Given int[] array = {2, 3, 1, 9, 5, 7}, find the 3rd largest number using Streams. Explain each method.");
        int[] array = {2, 3, 1, 9, 5, 7};
        System.out.println("Third Largest Number : " + Arrays.stream(array).boxed().sorted(Comparator.reverseOrder()).skip(2).findFirst().orElseThrow());

        System.out.println("**********************************************************************************************************************************************");

        /*13. Find the second highest salary
        Given Employee objects, find the second highest DISTINCT salary using Streams.*/

        System.out.println("13. Given Employee objects, find the second highest DISTINCT salary using Streams.");
        double secondHighestDistinctSalary = employees.stream().map(Employee::getSalary).distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElseThrow();
        System.out.println("Second Highest Distinct Salary : " + secondHighestDistinctSalary);

        System.out.println("**********************************************************************************************************************************************");

        /*14. Remove duplicates from a list
        Given List nums = List.of(4,2,7,2,4,9,7,1), return unique values while preserving encounter order.*/

        System.out.println("14. Given List nums = List.of(4,2,7,2,4,9,7,1), return unique values while preserving encounter order.");
        List<Integer> numsList = List.of(4, 2, 7, 2, 4, 9, 7, 1);
        List<Integer> uniqueNumsList = numsList.stream().distinct().toList();
        System.out.println("Unique List : " + uniqueNumsList);

        System.out.println("**********************************************************************************************************************************************");

        /*15. Highest-paid employee in each department
        Given List, find the highest-paid employee in each department.*/

        System.out.println("15. Given List, find the highest-paid employee in each department.");
        Map<String, Map<String, Double>> deptWiseHighSalaryMap = employees.stream()
                .collect(
                        Collectors.groupingBy(
                                Employee::getDepartment, Collectors.collectingAndThen(
                                        Collectors.maxBy(
                                                Comparator.comparingDouble(Employee::getSalary)
                                        ), e -> Map.of(
                                                e.get().getName(), e.get().getSalary()
                                        )
                                )
                        )
                );

        System.out.println("Department wise Highest paid Salary : " + deptWiseHighSalaryMap);

        System.out.println("**********************************************************************************************************************************************");

        /*16. Find the first non-repeated character
        Given a String, find its first non-repeated character using Streams.*/

        String test = "Sakshi";
        Map<Character, Long> frequency = s.chars()
                .mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(
                        Function.identity(), LinkedHashMap::new, Collectors.counting()
                ));

        Character firstNonRepeatedChar = frequency.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);

        System.out.println("First Non Repeated Character : "+firstNonRepeatedChar);

        System.out.println("**********************************************************************************************************************************************");

        /*17. Find duplicate elements
        Given List nums, return the values that occur more than once.*/

        List<Integer> numberList = List.of(1,2,3,2,4,5,3,4,5,6,7,5,6,0);
        List<Integer> duplicateList = numberList.stream()
                .collect(Collectors.groupingBy(
                       Function.identity(), Collectors.counting()
                ))
                .entrySet().stream()
                .filter(count -> count.getValue() > 1)
                .map(Map.Entry::getKey)
                .toList();
        System.out.println("Duplicate Values : "+duplicateList);

        System.out.println("**********************************************************************************************************************************************");

        /*18. Flatten a nested list
        Given List> nestedLists, flatten it into a single List.*/

        List<List<String>> nestedList = List.of(
                List.of("Pranay", "Namdev", "Masugade"),
                List.of("Tushar", "Dnyandev", "Adivarekar"),
                List.of("Laxman", "Bhushan", "Shirsat")
        );

        List<String> flattenList = nestedList.stream().flatMap(Collection::stream).toList();
        System.out.println("Result : "+flattenList);

        System.out.println("**********************************************************************************************************************************************");

        /*19. Find the longest String using reduce
        Given List words, use reduce() to find the longest word.*/

        List<String> wordsList = List.of(
                "Java",
                "Spring",
                "Microservices",
                "AWS",
                "Backend"
        );

        System.out.println(wordsList);

        String longest = wordsList.stream()
                .reduce((word1, word2) ->
                        word1.length() >= word2.length() ? word1 : word2
                )
                .orElse("");

        System.out.println("longest word : "+longest);

        System.out.println("**********************************************************************************************************************************************");

        /*20. Find maximum and minimum
        Given List nums, find the maximum and minimum values using Streams.*/

        System.out.println("Input Number list : "+nums);

        int max = nums.stream().max(Integer::compareTo).orElseThrow();

        int min = nums.stream().min(Integer::compareTo).orElseThrow();

        //Alternate approach
        /*int max = nums.stream()
                .reduce((n1, n2) ->
                        n1 > n2 ? n1 : n2
                ).get();*/

        System.out.println("Maximum number : "+max);
        System.out.println("Minimum number : "+min);
    }
}
