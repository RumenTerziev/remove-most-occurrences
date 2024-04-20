package org.example.solve;

import org.example.model.InputData;

import java.util.List;
import java.util.Map;

public interface DistinctNumbersFinder {

    int getDistinctNumbersCount(InputData inputData);

    Map<Integer, Integer> getNumberOccurrences(List<Integer> array);
}
