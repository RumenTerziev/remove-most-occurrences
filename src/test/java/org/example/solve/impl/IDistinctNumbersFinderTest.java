package org.example.solve.impl;

import org.example.model.InputData;
import org.example.solve.IDistinctNumbersFinder;
import org.example.solve.impl.util.TestDataSupplier;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class IDistinctNumbersFinderTest {

    private final IDistinctNumbersFinder baseFinder = new DistinctNumbersBaseFinder();
    private final IDistinctNumbersFinder advancedFinder = new DistinctNumbersAdvancedFinder();
    private static final List<InputData> INPUT_DATA = TestDataSupplier.getInputData();
    private static final InputData FIRST_TEST_DATA = INPUT_DATA.getFirst();
    private static final InputData SECOND_TEST_DATA = INPUT_DATA.get(1);

    @Test
    void testBaseGetDistinctNumbersCount_whenGivenCountRemovalsAreGreaterThanListSize() {
        //Given
        int countRemovals = 10;
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(2);
        InputData inputData = new InputData(numbers, countRemovals);
        int expected = 1;

        //When
        //Then
        doTestGetDistinctNumbersFinder(baseFinder, inputData, expected);
    }


    @Test
    void testBaseGetDistinctNumbersCount_whenGivenCountRemovalsAreLessThanListSize() {
        //Given
        int countRemovals = 6;
        List<Integer> numbers = new ArrayList<>();
        numbers.add(2);
        numbers.add(2);
        numbers.add(2);
        numbers.add(2);
        numbers.add(2);
        numbers.add(1);
        numbers.add(3);
        numbers.add(3);
        numbers.add(1);
        numbers.add(4);
        numbers.add(4);
        numbers.add(4);
        numbers.add(4);
        InputData inputData = new InputData(numbers, countRemovals);
        int expected = 3;

        //When
        //Then
        doTestGetDistinctNumbersFinder(baseFinder, inputData, expected);
    }

    @Test
    void testBaseGetDistinctNumbersCount_whenGivenDifferentNumberOfOccurrences_shouldPrioritiseTheGraterNumberOfOccurrences() {
        //Given
        int countRemovals = 6;
        List<Integer> numbers = new ArrayList<>();
        numbers.add(4);
        numbers.add(3);
        numbers.add(3);
        numbers.add(2);
        numbers.add(2);
        numbers.add(2);
        numbers.add(1);
        numbers.add(1);
        numbers.add(1);
        numbers.add(1);
        InputData inputData = new InputData(numbers, countRemovals);
        int expected = 3;

        //When
        //Then
        doTestGetDistinctNumbersFinder(baseFinder, inputData, expected);
    }

    @Test
    void testBaseGetNumbersOccurrences_whenGivenInputData() {
        //Given
        List<Integer> numbers = List.of(1, 1, 2, 2, 3, 3, 3);
        Map<Integer, Integer> expectedEntries = new HashMap<>();
        expectedEntries.put(1, 2);
        expectedEntries.put(2, 2);
        expectedEntries.put(3, 3);

        //When
        //Then
        doTestGetNumberOccurrences(baseFinder, numbers, expectedEntries);
    }

    @Test
    void testAdvancedGetDistinctNumbersCount_whenGivenCountRemovalsAreGreaterThanListSize() {
        //Given
        int countRemovals = 10;
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(2);
        InputData inputData = new InputData(numbers, countRemovals);
        int expected = 1;

        //When
        //Then
        doTestGetDistinctNumbersFinder(advancedFinder, inputData, expected);
    }

    @Test
    void testAdvancedGetDistinctNumbersCount_whenGivenCountRemovalsAreLessThanListSize() {
        //Given
        int countRemovals = 6;
        List<Integer> numbers = new ArrayList<>();
        numbers.add(2);
        numbers.add(2);
        numbers.add(2);
        numbers.add(2);
        numbers.add(2);
        numbers.add(1);
        numbers.add(3);
        numbers.add(3);
        numbers.add(1);
        numbers.add(4);
        numbers.add(4);
        numbers.add(4);
        numbers.add(4);
        InputData inputData = new InputData(numbers, countRemovals);
        int expected = 3;

        //When
        //Then
        doTestGetDistinctNumbersFinder(advancedFinder, inputData, expected);
    }

    @Test
    void testAdvancedGetDistinctNumbersCount_whenGivenDifferentNumberOfOccurrences_shouldPrioritiseTheGraterNumberOfOccurrences() {
        //Given
        int countRemovals = 6;
        List<Integer> numbers = new ArrayList<>();
        numbers.add(4);
        numbers.add(3);
        numbers.add(3);
        numbers.add(2);
        numbers.add(2);
        numbers.add(2);
        numbers.add(1);
        numbers.add(1);
        numbers.add(1);
        numbers.add(1);
        InputData inputData = new InputData(numbers, countRemovals);
        int expected = 3;

        //When
        //Then
        doTestGetDistinctNumbersFinder(advancedFinder, inputData, expected);
    }

    @Test
    void testAdvancedGetNumbersOccurrences_whenGivenInputData() {
        //Given
        List<Integer> numbers = List.of(1, 1, 2, 2, 3, 3, 3);
        Map<Integer, Integer> expectedEntries = new HashMap<>();
        expectedEntries.put(1, 2);
        expectedEntries.put(2, 2);
        expectedEntries.put(3, 3);

        //When
        //Then
        doTestGetNumberOccurrences(advancedFinder, numbers, expectedEntries);
    }

    @Test
    void logBaseFinderExecutionTime() {
        Runnable runnable = () -> {
            System.out.println("Count numbers before removals is: " + FIRST_TEST_DATA.numbers().size());
            System.out.println("Allowed count removals are: " + FIRST_TEST_DATA.countRemovals());
            Map<Integer, Integer> numberOccurrences = baseFinder.getNumberOccurrences(FIRST_TEST_DATA.numbers());
            System.out.println(numberOccurrences.size());
            int distinctNumbersCount = baseFinder.getDistinctNumbersCount(FIRST_TEST_DATA, baseFinder.getNumberOccurrences(FIRST_TEST_DATA.numbers()));
            System.out.printf("%s found total %d distinct numbers after removal%n", baseFinder.getClass().getSimpleName(), distinctNumbersCount);
        };
        logExecutionMillis(baseFinder, runnable);
    }

    @Test
    void logAdvancedFinderExecutionTime() {
        Runnable runnable = () -> {
            System.out.println("Count numbers before removals is: " + SECOND_TEST_DATA.numbers().size());
            System.out.println("Allowed count removals are: " + SECOND_TEST_DATA.countRemovals());
            Map<Integer, Integer> numberOccurrences = advancedFinder.getNumberOccurrences(SECOND_TEST_DATA.numbers());
            System.out.println(numberOccurrences.size());
            int distinctNumbersCount = advancedFinder.getDistinctNumbersCount(SECOND_TEST_DATA, advancedFinder.getNumberOccurrences(SECOND_TEST_DATA.numbers()));
            System.out.printf("%s found total %d distinct numbers after removal%n", advancedFinder.getClass().getSimpleName(), distinctNumbersCount);
        };
        logExecutionMillis(advancedFinder, runnable);
    }

    private void doTestGetDistinctNumbersFinder(IDistinctNumbersFinder distinctNumbersFinder, InputData inputData, int expected) {
        int actual = distinctNumbersFinder.getDistinctNumbersCount(inputData, distinctNumbersFinder.getNumberOccurrences(inputData.numbers()));
        assertThat(actual).isEqualTo(expected);
    }

    private void doTestGetNumberOccurrences(IDistinctNumbersFinder distinctNumbersFinder, List<Integer> numbers, Map<Integer, Integer> expectedEntries) {
        Map<Integer, Integer> result = distinctNumbersFinder.getNumberOccurrences(numbers);
        assertThat(result).hasSize(expectedEntries.size());
        expectedEntries.forEach((key, value) -> assertThat(result).containsEntry(key, value));
    }

    private void logExecutionMillis(IDistinctNumbersFinder distinctNumbersFinder, Runnable runnable) {
        long currentTimeMillis = System.currentTimeMillis();
        runnable.run();
        System.out.printf("%s took %d millis for execution%n%n", distinctNumbersFinder.getClass().getSimpleName(), System.currentTimeMillis() - currentTimeMillis);
    }
}
