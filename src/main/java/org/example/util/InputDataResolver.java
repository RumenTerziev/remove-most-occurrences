package org.example.util;

import org.example.model.InputData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputDataResolver {

    public InputData getInputData(String testFilePath) {
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(testFilePath);
        } catch (FileNotFoundException exception) {
            throw new RuntimeException("Could not read input data", exception);
        }

        Scanner scanner = new Scanner(fileInputStream);
        List<Integer> numbers = Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        int countRemovals = Integer.parseInt(scanner.nextLine());

        try {
            fileInputStream.close();
        } catch (IOException exception) {
            throw new RuntimeException("Error closing file", exception);
        }
        scanner.close();
        return new InputData(numbers, countRemovals);
    }
}
