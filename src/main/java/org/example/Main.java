package org.example;

import org.example.model.InputData;
import org.example.solve.DistinctNumbersFinder;
import org.example.solve.impl.DistinctNumbersAdvancedFinder;
import org.example.util.InputDataResolver;

import java.util.List;
import java.util.Map;

import static org.example.util.TestDataSupplier.getTestInputData;

public class Main {

    private static final InputDataResolver inputDataResolver = new InputDataResolver();

    private static final DistinctNumbersFinder DISTINCT_NUMBERS_FINDER = new DistinctNumbersAdvancedFinder();

    public static void main(String[] args) {

        String firstTestPath = "src/main/resources/test-one.txt";
        String secondTestPath = "src/main/resources/test-two.txt";
        String thirdTestPath = "src/main/resources/test-three.txt";

//        InputData inputData = inputDataResolver.getInputData(thirdTestPath);
        InputData inputData = getTestInputData();
        List<Integer> numbers = inputData.numbers();
        System.out.println("The initial numbers are:\n" + numbers);
        int countRemovals = inputData.countRemovals();
        System.out.printf("Allowed removals: %d%n%n", countRemovals);

        Map<Integer, Integer> numberOccurrences = DISTINCT_NUMBERS_FINDER.getNumberOccurrences(numbers);
        System.out.println("Distinct numbers found before removals: " + numberOccurrences.size());
        numberOccurrences.forEach((k, v) -> System.out.printf("Number %d has %d occurrences.\n", k, v));

        long millis = System.currentTimeMillis();
        int distinctNumbersCount = DISTINCT_NUMBERS_FINDER.getDistinctNumbersCount(inputData);
        System.out.println("Time for execution is " + (System.currentTimeMillis() - millis) + " milliseconds.");
        System.out.printf("%nNumbers after removals: %s%n", numbers);
        System.out.printf("The distinct numbers' count is: %d", distinctNumbersCount);
    }
}
