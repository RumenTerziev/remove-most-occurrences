package org.example.solve.impl;

import org.example.model.InputData;
import org.example.solve.DistinctNumbersFinder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class DistinctNumbersBaseFinderTest {

    private final DistinctNumbersFinder distinctNumbersFinder = new DistinctNumbersBaseFinder();

    @Test
    void testGetDistinctNumbersCount_whenGivenCountRemovalsAreGreaterThanListSize() {
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
        int actual = distinctNumbersFinder.getDistinctNumbersCount(inputData);

        //Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testGetDistinctNumbersCount_whenGivenCountRemovalsAreLessThanListSize() {
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
        int actual = distinctNumbersFinder.getDistinctNumbersCount(inputData);

        //Then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testGetDistinctNumbersCount_whenGivenDifferentNumberOfOccurrences_shouldPrioritiseTheGraterNumberOfOccurrences() {
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
        int actual = distinctNumbersFinder.getDistinctNumbersCount(inputData);

        //Then
        assertThat(actual).isEqualTo(expected);
        assertThat(numbers).contains(3);
    }

    @Test
    void testGetNumbersOccurrences_whenGivenInputData() {
        //Given
        List<Integer> numbers = List.of(1, 1, 2, 2, 3, 3, 3);

        //When
        Map<Integer, Integer> result = distinctNumbersFinder.getNumberOccurrences(numbers);

        //Then
        assertThat(result).hasSize(3);
        assertThat(result).containsEntry(1, 2);
        assertThat(result).containsEntry(2, 2);
        assertThat(result).containsEntry(3, 3);
    }
}
