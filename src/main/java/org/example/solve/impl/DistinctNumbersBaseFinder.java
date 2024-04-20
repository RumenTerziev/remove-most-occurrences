package org.example.solve.impl;

import org.example.model.InputData;
import org.example.solve.DistinctNumbersFinder;

import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public Map<Integer, Integer> getNumberOccurrences(List<Integer> array) {
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
}
