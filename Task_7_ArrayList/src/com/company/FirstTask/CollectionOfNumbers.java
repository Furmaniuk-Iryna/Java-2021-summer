package com.company.FirstTask;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Iryna Furmaniuk on 20.07.2021.
 */
public class CollectionOfNumbers {

    Map<Integer, Integer> amountOfNumbers = new TreeMap<>();

    public Map<Integer, Integer> countAmountOfNumbers(ArrayList<Integer> numbers) {
        for (Integer number : numbers) {

            if (amountOfNumbers.containsKey(number)) {
                amountOfNumbers.replace(number, amountOfNumbers.get(number) + 1);
            } else amountOfNumbers.put(number, 1);
        }

        return amountOfNumbers;
    }

    public void printAmountOfNumbers(Map<Integer, Integer> amountOfNumbers) {
        for (Map.Entry entry : amountOfNumbers.entrySet()) {
            System.out.println(entry.getKey() + " — "
                    + entry.getValue());
        }
    }

    public void getAmountOfNumbers(ArrayList<Integer> numbers) {
        printAmountOfNumbers(countAmountOfNumbers(numbers));
    }
}
