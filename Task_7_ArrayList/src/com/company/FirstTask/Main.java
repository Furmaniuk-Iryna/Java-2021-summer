package com.company.FirstTask;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(4);
        numbers.add(5);
        numbers.add(-6);
        numbers.add(4);
        numbers.add(5);
        numbers.add(3);
        numbers.add(4);
        numbers.add(2);
        numbers.add(4);
        numbers.add(5);
        numbers.add(7);

CollectionOfNumbers collectionOfNumbers = new CollectionOfNumbers();
collectionOfNumbers.getAmountOfNumbers(numbers);

    }
}
