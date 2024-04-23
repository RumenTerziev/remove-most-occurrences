package org.example.solve.impl;

import org.example.model.InputData;
import org.example.solve.DistinctNumbersFinder;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DistinctNumbersBaseFinder implements DistinctNumbersFinder {

    @Override
    public int getDistinctNumbersCount(InputData inputData) {
        List<Integer> numbers = inputData.numbers();
        int countRemovals = inputData.countRemovals();
        Map<Integer, Integer> numberOccurrences = getNumberOccurrences(numbers);
        Iterator<Map.Entry<Integer, Integer>> iterator = numberOccurrences.entrySet().iterator();
        while (numbers.size() > 1 && countRemovals > 0 && iterator.hasNext()) {
            Map.Entry<Integer, Integer> currentEntry = iterator.next();
            Integer searchedNumber = currentEntry.getKey();
            int occurrences = currentEntry.getValue();
            while (occurrences > 0) {
                numbers.remove(searchedNumber);
                occurrences--;
                countRemovals--;
                if (numbers.size() == 1 || countRemovals == 0) {
                    break;
                }
            }
        }
        return Set.copyOf(numbers).size();
    }
}
