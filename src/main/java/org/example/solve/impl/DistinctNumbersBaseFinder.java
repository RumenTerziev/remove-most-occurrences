package org.example.solve.impl;

import org.example.model.InputData;
import org.example.solve.IDistinctNumbersFinder;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DistinctNumbersBaseFinder implements IDistinctNumbersFinder {

    @Override
    public int getDistinctNumbersCount(InputData inputData, Map<Integer, Integer> numberOccurrences) {
        List<Integer> numbers = inputData.numbers();
        int countRemovals = inputData.countRemovals();
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
