package org.example.solve.impl;

import org.example.model.InputData;
import org.example.solve.DistinctNumbersFinder;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(expected, actual);
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
        assertEquals(expected, actual);
    }
}
