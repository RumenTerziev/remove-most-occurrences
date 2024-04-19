package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class RemoveFunction {

    public static void main(String[] args) {

        String firstTestPath = "src/main/resources/test-one.txt";
        String secondTestPath = "src/main/resources/test-two.txt";
        String thirdTestPath = "src/main/resources/test-three.txt";

        InputData inputData = getInputData(thirdTestPath);
        List<Integer> numbers = inputData.numbers();
        System.out.println("The initial numbers are:\n" + numbers);
        int countRemovals = inputData.countRemovals();
        System.out.printf("Allowed removals: %d%n%n", countRemovals);

        Map<Integer, Integer> numberOccurrences = getNumberOccurrences(numbers);
        numberOccurrences.forEach((k, v) -> System.out.printf("Number %d has %d occurrences.\n", k, v));

        int distinguishedNumbersCount = getDistinguishedNumbersCount(numberOccurrences, numbers, countRemovals);
        System.out.printf("%nNumbers after removals: %s%n", numbers);
        System.out.printf("The distinguished numbers' count is: %d", distinguishedNumbersCount);
    }

    private static int getDistinguishedNumbersCount(Map<Integer, Integer> numberOccurrences, List<Integer> numbers, int countRemovals) {
        Iterator<Map.Entry<Integer, Integer>> iterator = numberOccurrences.entrySet().iterator();
        while (!numbers.isEmpty() && countRemovals > 0 && iterator.hasNext()) {
            Map.Entry<Integer, Integer> currentEntry = iterator.next();
            Integer searchedNumber = currentEntry.getKey();
            int occurrences = currentEntry.getValue();
            while (occurrences > 0) {
                numbers.remove(searchedNumber);
                occurrences--;
                countRemovals--;
                if (countRemovals == 0) {
                    break;
                }
            }
        }
        return Set.copyOf(numbers).size();
    }

    private static Map<Integer, Integer> getNumberOccurrences(List<Integer> array) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int currentNum : array) {
            map.putIfAbsent(currentNum, 0);
            map.put(currentNum, map.get(currentNum) + 1);
        }
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue((f, s) -> s - f))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    private static InputData getInputData(String testFilePath) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(testFilePath);
        } catch (FileNotFoundException exception) {
            throw new RuntimeException("Could not read input data", exception);
        }

        Scanner scanner = new Scanner(fileInputStream);
        List<Integer> numbers = Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        int countRemovals = Integer.parseInt(scanner.nextLine());

        try {
            fileInputStream.close();
        } catch (IOException exception) {
            throw new RuntimeException("Error closing file", exception);
        }
        scanner.close();
        return new InputData(numbers, countRemovals);
    }
}
