package com.company;

import java.util.stream.IntStream;

/**
 * Created by Iryna Furmaniuk on 22.07.2021.
 */
public class PracticeStream {
    public void average(int[] array) {
        double average = IntStream.of(array).average().getAsDouble();
        System.out.println(average);
    }

    public void minElement(int[] array) {
        int minElement = IntStream.of(array).min().getAsInt();
        System.out.println(minElement);
    }

    public void amountOfZeroElement(int[] array) {
        int amountOfZeroElement = (int) IntStream.of(array).filter(i -> i == 0).count();
        System.out.println(amountOfZeroElement);
    }

    public void amountOfElementsMoreThanZero(int[] array) {
        int amountOfElementsMoreThanZero = (int) IntStream.of(array).filter(i -> i > 0).count();
        System.out.println(amountOfElementsMoreThanZero);
    }

    public void multiplicationByNumber(int[] array, Integer integerNumber) {
        IntStream.of(array).map(i -> i * integerNumber).forEach((i) -> System.out.print(i + "  "));
    }

}
