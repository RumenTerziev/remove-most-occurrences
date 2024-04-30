package org.example.solve.impl;

import org.example.model.InputData;
import org.example.solve.IDistinctNumbersFinder;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DistinctNumbersAdvancedFinder implements IDistinctNumbersFinder {

    @Override
    public int getDistinctNumbersCount(InputData inputData, Map<Integer, Integer> numberOccurrences) {
        List<Integer> numbers = inputData.numbers();
        int countRemovals = inputData.countRemovals();
        Iterator<Map.Entry<Integer, Integer>> iterator = numberOccurrences.entrySet().iterator();

        int countUpdated = 0;
        while (iterator.hasNext() && countRemovals > 0 && countUpdated < numberOccurrences.size() - 1) {
            Map.Entry<Integer, Integer> nextEntry = iterator.next();
            if (nextEntry.getValue() > countRemovals) {
                numberOccurrences.put(nextEntry.getKey(), nextEntry.getValue() - countRemovals);
                countRemovals = 0;
            } else {
                countRemovals -= nextEntry.getValue();
                numberOccurrences.put(nextEntry.getKey(), 0);
            }
            countUpdated++;
        }

        return getDistinctNumbers(numberOccurrences).size();
    }
}
