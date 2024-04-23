package org.example.solve.impl;

import org.example.model.InputData;
import org.example.solve.DistinctNumbersFinder;

import java.util.*;

public class DistinctNumbersAdvancedFinder implements DistinctNumbersFinder {

    @Override
    public int getDistinctNumbersCount(InputData inputData) {
        List<Integer> numbers = inputData.numbers();
        int countRemovals = inputData.countRemovals();
        Map<Integer, Integer> numberOccurrences = getNumberOccurrences(numbers);
        Iterator<Map.Entry<Integer, Integer>> iterator = numberOccurrences.entrySet().iterator();

        Map<Integer, Integer> occurrencesToUpdate = new HashMap<>();
        List<Integer> keysToRemove = new ArrayList<>();
        while (iterator.hasNext() && countRemovals > 0 && keysToRemove.size() < numberOccurrences.size() - 1) {
            Map.Entry<Integer, Integer> nextEntry = iterator.next();
            if (nextEntry.getValue() > countRemovals) {
                occurrencesToUpdate.put(nextEntry.getKey(), nextEntry.getValue() - countRemovals);
                countRemovals = 0;
            } else {
                keysToRemove.add(nextEntry.getKey());
                countRemovals -= nextEntry.getValue();
            }
        }

        numberOccurrences.putAll(occurrencesToUpdate);

        for (Integer currentKey : keysToRemove) {
            numberOccurrences.remove(currentKey);
        }
        return numberOccurrences.size();
    }
}
