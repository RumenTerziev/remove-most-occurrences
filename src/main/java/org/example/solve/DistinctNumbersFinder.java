package org.example.solve;

import org.example.model.InputData;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface DistinctNumbersFinder {

    int getDistinctNumbersCount(InputData inputData);

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
}
