package org.example.solve;

import org.example.model.InputData;

import java.util.*;
import java.util.stream.Collectors;

public interface IDistinctNumbersFinder {

    int getDistinctNumbersCount(InputData inputData, Map<Integer, Integer> numberOccurrences);

    default Map<Integer, Integer> getNumberOccurrences(List<Integer> array) {
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

    default Set<Integer> getDistinctNumbers(Map<Integer, Integer> occurrencesMap) {
        return occurrencesMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }
}
