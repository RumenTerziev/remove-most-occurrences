package org.example.util;

import org.example.model.InputData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TestDataSupplier {

    public static InputData getTestInputData() {

        List<Integer> numbers = new ArrayList<>();
        for (int num = 0; num < 10000; num++) {
            int nextInt = ThreadLocalRandom.current().nextInt(1, 100);
            numbers.add(nextInt);
        }
        int countRemovals = ThreadLocalRandom.current().nextInt(1, 1000);
        return new InputData(numbers, countRemovals);
    }
}
