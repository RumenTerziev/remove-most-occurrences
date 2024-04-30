package org.example.solve.impl.util;

import org.example.model.InputData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TestDataSupplier {

    private static List<InputData> inputDataList;

    public static synchronized List<InputData> getInputData() {
        if (inputDataList == null) {
            inputDataList = doGetData();
        }
        return inputDataList;
    }

    private static List<InputData> doGetData() {
        List<Integer> firstNumbers = new ArrayList<>();
        List<Integer> secondNumbers = new ArrayList<>();
        for (int num = 0; num < 100000; num++) {
            int nextInt = ThreadLocalRandom.current().nextInt(1, 100);
            firstNumbers.add(nextInt);
            secondNumbers.add(nextInt);
        }

        int countRemovals = ThreadLocalRandom.current().nextInt(1, 10000);
        List<InputData> inputDataList = new ArrayList<>();
        inputDataList.add(new InputData(firstNumbers, countRemovals));
        inputDataList.add(new InputData(secondNumbers, countRemovals));
        return inputDataList;
    }
}
